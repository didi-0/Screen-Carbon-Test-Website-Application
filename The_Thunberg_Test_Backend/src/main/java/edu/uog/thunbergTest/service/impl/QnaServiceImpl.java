package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.uog.thunbergTest.mapper.QnaMapper;
import edu.uog.thunbergTest.model.entity.Qna;
import edu.uog.thunbergTest.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/09:21
 */
@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaMapper qnaMapper;

    @Override
    public List<Qna> selectQna(Integer id) {
        QueryWrapper<Qna> wrapper = new QueryWrapper<>();
        wrapper.eq(id != null,"question_id",id);
        List<Qna> qnas = qnaMapper.selectList(wrapper);
        return qnas;
    }

    @Override
    public int updateQna(Qna qna) {
        int rows = qnaMapper.updateById(qna);
        return rows;
    }

    @Override
    public int deleteQna(Integer id) {
        int rows = qnaMapper.deleteById(id);
        return rows;
    }

    @Override
    public int insertQna(Qna qna) {
        int rows = qnaMapper.insert(qna);
        return rows;
    }
}
