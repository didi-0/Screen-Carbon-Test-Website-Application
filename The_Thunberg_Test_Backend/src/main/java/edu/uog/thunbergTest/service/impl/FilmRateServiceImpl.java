package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.uog.thunbergTest.mapper.FilmRateMapper;
import edu.uog.thunbergTest.model.entity.FilmRate;
import edu.uog.thunbergTest.service.FilmRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/13:51
 */
@Service
public class FilmRateServiceImpl implements FilmRateService {

    @Autowired
    private FilmRateMapper filmRateMapper;

    @Override
    public FilmRate select(Integer filmId, String filmType) {
        QueryWrapper<FilmRate> wrapper = new QueryWrapper<>();
        wrapper.eq("film_id", filmId);
        wrapper.eq("film_type", filmType);
        FilmRate filmRate = filmRateMapper.selectOne(wrapper);
        return filmRate;
    }
}
