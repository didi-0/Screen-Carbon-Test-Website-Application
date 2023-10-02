package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.Qna;
import edu.uog.thunbergTest.service.impl.QnaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: QnaController
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/09:19
 */
@RestController
@CrossOrigin
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaServiceImpl qnaServiceImpl;

    @RequestMapping(path = "/selectQna", method = RequestMethod.GET)
    public List<Qna> selectBulletinBoard(@RequestParam(name = "id", required = false) Integer id) {
        List<Qna> qnas = qnaServiceImpl.selectQna(id);
        return qnas;
    }

    @RequestMapping(path = "/updateQna", method = RequestMethod.POST)
    public int updateBulletinBoard(@RequestBody Qna qna) {
        int rows = qnaServiceImpl.updateQna(qna);
        return rows;
    }

    @RequestMapping(path = "/deleteQna", method = RequestMethod.GET)
    public int deleteBulletinBoard(@RequestParam("id") int id) {
        int rows =  qnaServiceImpl.deleteQna(id);
        return rows;
    }

    @RequestMapping(path = "/insertQna", method = RequestMethod.POST)
    public int insertBulletinBoard(@RequestBody Qna qna) {
        int rows = qnaServiceImpl.insertQna(qna);
        return rows;
    }
}
