package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.entity.CelebRate;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/15:26
 */
public interface CelebRateService {
    CelebRate select(Integer celebId);

    int updateAndInsert(CelebRate celebRate) throws Exception;

    List<CelebRate> selectBatch(List<Integer> actorIds);
}
