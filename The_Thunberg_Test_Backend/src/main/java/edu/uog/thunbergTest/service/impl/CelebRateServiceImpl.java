package edu.uog.thunbergTest.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import edu.uog.thunbergTest.mapper.CelebRateMapper;
import edu.uog.thunbergTest.mapper.UserRateMapper;
import edu.uog.thunbergTest.model.PersonCreditsDto;
import edu.uog.thunbergTest.model.entity.CelebRate;
import edu.uog.thunbergTest.model.entity.UserRate;
import edu.uog.thunbergTest.service.CelebRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/15:26
 */
@Service
public class CelebRateServiceImpl implements CelebRateService {

    @Autowired
    private CelebRateMapper celebRateMapper;

    @Autowired
    private UserRateMapper userRateMapper;

    @Override
    public CelebRate select(Integer celebId) {
        QueryWrapper<CelebRate> wrapper = new QueryWrapper<>();
        wrapper.eq("celeb_id", celebId);
        CelebRate celebRate = celebRateMapper.selectById(wrapper);
        return celebRate;
    }

    @Override
    public int updateAndInsert(CelebRate celebRate) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/person/" + celebRate.getCelebId() + "/combined_credits?language=en-US")
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

        ArrayList<UserRate> rates = new ArrayList<>();
        QueryWrapper<UserRate> tvWrapper = new QueryWrapper<>();
        tvWrapper.in("film_id", tvLists);
        tvWrapper.eq("film_type", "tv");
        List<UserRate> tvRates = userRateMapper.selectList(tvWrapper);
        if (!CollectionUtils.isEmpty(tvRates)) {
            rates.addAll(tvRates);
        }
        QueryWrapper<UserRate> mvWrapper = new QueryWrapper<>();
        mvWrapper.in("film_id", movieLists);
        mvWrapper.eq("film_type", "movie");
        List<UserRate> movieRates = userRateMapper.selectList(mvWrapper);
        if (!CollectionUtils.isEmpty(movieRates)) {
            rates.addAll(movieRates);
        }
        double score = rates.stream().mapToDouble(UserRate::getScore).average().orElse(-1);
        if (score < 0){
            throw new Exception("No film rates");
        }

        celebRate.setScore(score);
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        celebRate.setRatingDate(format.format(new Date()));

        int rows = celebRateMapper.updateAndInsert(celebRate);
        return rows;
    }

    @Override
    public List<CelebRate> selectBatch(List<Integer> actorIds) {
        QueryWrapper<CelebRate> wrapper = new QueryWrapper<>();
        wrapper.in("celeb_id", actorIds);
        List<CelebRate> celebRates = celebRateMapper.selectList(wrapper);
        return celebRates;
    }
}
