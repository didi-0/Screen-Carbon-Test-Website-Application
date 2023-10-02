import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import edu.uog.thunbergTest.model.*;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/16:06
 */
public class Test {
    @org.junit.Test
    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/person/234352/combined_credits?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E")
                .build();

        Response response = client.newCall(request).execute();

        String data = response.body().string();

        PersonCreditsDto dto = JSON.parseObject(data, PersonCreditsDto.class);

        ArrayList<String> tvList = new ArrayList();
        ArrayList<String> movieList = new ArrayList();

        List<Integer> tvCastIds = dto.getCast().stream().filter(castSingle -> castSingle.getMedia_type().equals("tv")).map(castSingle -> castSingle.getId()).distinct().collect(Collectors.toList());
        List<Integer> movieCastIds = dto.getCast().stream().filter(castSingle -> castSingle.getMedia_type().equals("movie")).map(castSingle -> castSingle.getId()).distinct().collect(Collectors.toList());

        List<Integer> tvCrewIds = dto.getCrew().stream().filter(crewSingle -> crewSingle.getMedia_type().equals("tv")).map(crewSingle -> crewSingle.getId()).distinct().collect(Collectors.toList());
        List<Integer> movieCrewIds = dto.getCrew().stream().filter(crewSingle -> crewSingle.getMedia_type().equals("movie")).map(crewSingle -> crewSingle.getId()).distinct().collect(Collectors.toList());

        tvList.containsAll(tvCastIds);
        movieList.containsAll(movieCastIds);
        tvList.containsAll(tvCrewIds);
        movieList.containsAll(movieCrewIds);

        List<String> tvLists = tvList.stream().distinct().collect(Collectors.toList());
        List<String> movieLists = movieList.stream().distinct().collect(Collectors.toList());


    }

    @org.junit.Test
    public void test1() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_keywords=Barbies")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E")
                .build();

        Response response = client.newCall(request).execute();

        String data = response.body().string();

        ArrayList<MovieDiscovery> movieDiscoveries = new ArrayList<>();
        Pagination pagination = JSON.parseObject(data, Pagination.class);
        pagination.getResults().stream().forEach(result -> {
            MovieDiscovery movieDiscovery = JSON.parseObject(JSONObject.toJSONString(result), MovieDiscovery.class);
            movieDiscoveries.add(movieDiscovery);
        });



    }

}
