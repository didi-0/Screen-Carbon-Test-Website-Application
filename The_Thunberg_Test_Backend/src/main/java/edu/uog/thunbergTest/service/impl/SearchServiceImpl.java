package edu.uog.thunbergTest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import edu.uog.thunbergTest.model.*;
import edu.uog.thunbergTest.model.entity.FilmRate;
import edu.uog.thunbergTest.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/10:07
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private FilmRateServiceImpl filmRateService;

    public Page<MovieSearch> selectMovie(String certification, Integer page, String sort, String genres, String keywords) throws IOException {
        Page<MovieSearch> movieSearchPage = new Page<>(page, 20);

        String dataDiscovery = discovery(certification, page, sort, genres, keywords);

        ArrayList<MovieDiscovery> movieDiscoveries = new ArrayList<>();

        Pagination pagination = JSON.parseObject(dataDiscovery, Pagination.class);
        movieSearchPage.setPages(pagination.getTotalPages());
        movieSearchPage.setTotal(pagination.getTotalResults());
        pagination.getResults().stream().forEach(result -> {
            MovieDiscovery movieDiscovery = JSON.parseObject(JSONObject.toJSONString(result), MovieDiscovery.class);
            movieDiscoveries.add(movieDiscovery);
        });

        ArrayList<MovieSearch> movieSearches = new ArrayList<>();

        movieDiscoveries.stream().forEach(movie -> {
            MovieSearch movieSearch = new MovieSearch();
            movieSearch.setId(movie.getId());
            movieSearch.setTitle(movie.getTitle());
            movieSearch.setReleaseYear(movie.getReleaseDate());
            FilmRate filmRate = filmRateService.select(movie.getId(), "movie");
            if (filmRate == null) {
                movieSearch.setScore(0.0);
            } else {
                movieSearch.setScore(filmRate.getScore());
            }
            ArrayList<Crew> crews = new ArrayList();
            String dataCredit = null;
            try {
                dataCredit = creditApi(movie.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Credit credit = JSON.parseObject(dataCredit, Credit.class);
            credit.getCrew().stream().forEach(crew -> {
                Crew crewSingle = JSON.parseObject(JSONObject.toJSONString(crew), Crew.class);
                crews.add(crewSingle);
            });
            Optional<Crew> firstDirector = crews.stream().filter(crew -> "Director".equals(crew.getJob())).findFirst();
            if (firstDirector.isPresent()) {
                movieSearch.setProducer(firstDirector.get().getName());
            }
            Optional<Crew> firstProducer = crews.stream().filter(crew -> "Producer".equals(crew.getJob())).findFirst();
            if (firstProducer.isPresent()) {
                movieSearch.setDirector(firstProducer.get().getName());
            }
            movieSearches.add(movieSearch);
        });
        movieSearchPage.setRecords(movieSearches);
        return movieSearchPage;
    }

    private String creditApi(Integer id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" + id + "/credits?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E")
                .build();

        Response response = client.newCall(request).execute();

        String data = response.body().string();
        return data;
    }

    //movie discovery tmdb api
    private String discovery(String certification, Integer page, String sort, String genres, String keywords) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?certification=" + certification + "&certification_country=GB&include_adult=false&include_video=false&language=en-US&page=" + page + "&release_date.gte=2019-01-01&sort_by=" + sort + "&with_genres=" + genres + "&with_keywords=" + keywords)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E")
                .build();
        Response response = client.newCall(request).execute();

        String data = response.body().string();
        return data;
    }

    @Override
    public Page<TvSearch> selectTv(String certification, Integer page, String sort, String genres) throws IOException {
        Page<TvSearch> tvSearchPage = new Page<>(page, 20);

        String dataDiscovery = discoveryTV(certification, page, sort, genres);

        ArrayList<TvDiscovery> tvDiscoveries = new ArrayList<>();

        Pagination pagination = JSON.parseObject(dataDiscovery, Pagination.class);
        tvSearchPage.setPages(pagination.getTotalPages());
        tvSearchPage.setTotal(pagination.getTotalResults());
        pagination.getResults().stream().forEach(result -> {
            TvDiscovery tvDiscovery = JSON.parseObject(JSONObject.toJSONString(result), TvDiscovery.class);
            tvDiscoveries.add(tvDiscovery);
        });

        ArrayList<TvSearch> tvSearches = new ArrayList<>();

        tvDiscoveries.stream().forEach(tv -> {
            TvSearch tvSearch = new TvSearch();
            tvSearch.setId(tv.getId());
            tvSearch.setTitle(tv.getName());
            tvSearch.setFirstAirDate(tv.getFirstAirDate());
            FilmRate filmRate = filmRateService.select(tv.getId(), "tv");
            if (filmRate == null) {
                tvSearch.setScore(0.0);
            } else {
                tvSearch.setScore(filmRate.getScore());
            }
            tvSearches.add(tvSearch);
        });
        tvSearchPage.setRecords(tvSearches);
        return tvSearchPage;
    }

    //tv discovery tmdb api
    private String discoveryTV(String certification, Integer page, String sort, String genres) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/tv?include_adult=false&include_null_first_air_dates=false&language=en-US&page=" + page + "&sort_by=" + sort + "&with_genres=" + genres)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E")
                .build();

        Response response = client.newCall(request).execute();

        String data = response.body().string();
        return data;
    }
}
