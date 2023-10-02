package edu.uog.thunbergTest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.uog.thunbergTest.model.entity.CelebRate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/15:26
 */
@Mapper
public interface CelebRateMapper extends BaseMapper<CelebRate> {

    int updateAndInsert(CelebRate celebRate);
}
