package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.uog.thunbergTest.mapper.FilmRateMapper;
import edu.uog.thunbergTest.mapper.UserRateMapper;
import edu.uog.thunbergTest.model.UserRateVo;
import edu.uog.thunbergTest.model.entity.FilmRate;
import edu.uog.thunbergTest.model.entity.UserRate;
import edu.uog.thunbergTest.service.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/14:35
 */
@Service
public class UserRateServiceImpl implements UserRateService {

    @Autowired
    private UserRateMapper userRateMapper;

    @Autowired
    private FilmRateMapper filmRateMapper;

    @Override
    public List<UserRate> select(Integer fileId, Integer fileType) {
        QueryWrapper<UserRate> wrapper = new QueryWrapper<>();
        wrapper.eq("file_id",fileId).eq("file_type",fileType);
        List<UserRate> userRates = userRateMapper.selectList(wrapper);
        return userRates;
    }

    @Override
    public int update(UserRate fileRate) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        fileRate.setEditAt(format.format(new Date()));
        int rows = userRateMapper.update(fileRate,null);
        return rows;
    }

    @Override
    public int delete(Integer fileId, Integer userId) {
        QueryWrapper<UserRate> wrapper = new QueryWrapper<>();
        wrapper.eq("file_id",fileId).eq("user_id",userId);
        int rows = userRateMapper.delete(wrapper);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserRateVo userRateVo, String username) {
        UserRate userRate = new UserRate();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        userRate.setRatingDate(format.format(new Date()));
        userRate.setEditAt(format.format(new Date()));
        userRate.setFilmId(userRateVo.getId());
        userRate.setFilmType(userRateVo.getMediaType());
        userRate.setUsername(username);
        OptionalDouble average = userRateVo.getValues().stream().mapToDouble(Integer::doubleValue).average();
        if (average.isPresent()){
            userRate.setScore(average.getAsDouble());
        } else {
            return 0;
        }
        int rows = userRateMapper.insert(userRate);

        FilmRate filmRate = new FilmRate();
        QueryWrapper<UserRate> wrapper = new QueryWrapper<>();
        wrapper.eq("film_id", userRate.getFilmId());
        wrapper.eq("film_type", userRate.getFilmType());
        List<UserRate> rates = userRateMapper.selectList(wrapper);
        OptionalDouble scoreFilmOptional = rates.stream().mapToDouble(UserRate::getScore).average();
        if (scoreFilmOptional.isPresent()){
            filmRate.setScore(scoreFilmOptional.getAsDouble());
        }
        filmRate.setFilmType(userRate.getFilmType());
        filmRate.setFilmId(userRate.getFilmId());
        filmRate.setRatingDate(format.format(new Date()));
        int i = filmRateMapper.updateAndInsert(filmRate);
        if (i == 0){
            System.out.println("filmRate insert fail");
        } else {
            System.out.println("filmRate insert success");
        }
        return rows;
    }
}
