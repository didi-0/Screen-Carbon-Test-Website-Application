package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.UserRateVo;
import edu.uog.thunbergTest.model.entity.UserRate;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/14:35
 */
public interface UserRateService {

    List<UserRate> select(Integer fileId, Integer fileType);

    int update(UserRate fileRate);

    int delete(Integer fileId, Integer userId);

    int insert(UserRateVo userRateVo, String username);
}
