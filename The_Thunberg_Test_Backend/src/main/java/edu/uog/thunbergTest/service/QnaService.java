package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.entity.Qna;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/09:21
 */
public interface QnaService {
    List<Qna> selectQna(Integer id);

    int updateQna(Qna qna);

    int deleteQna(Integer id);

    int insertQna(Qna qna);
}
