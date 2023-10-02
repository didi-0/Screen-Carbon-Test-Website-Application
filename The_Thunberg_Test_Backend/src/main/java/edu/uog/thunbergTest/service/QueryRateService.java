package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.entity.Survey;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/21:50
 */
public interface QueryRateService {
    List<Survey> select(List<Integer> surveyIds);
}
