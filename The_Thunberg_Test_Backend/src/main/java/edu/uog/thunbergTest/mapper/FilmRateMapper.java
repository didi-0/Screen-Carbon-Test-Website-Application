package edu.uog.thunbergTest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.uog.thunbergTest.model.entity.FilmRate;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/13:50
 */
public interface FilmRateMapper  extends BaseMapper<FilmRate> {
    int updateAndInsert(FilmRate filmRate);
}
