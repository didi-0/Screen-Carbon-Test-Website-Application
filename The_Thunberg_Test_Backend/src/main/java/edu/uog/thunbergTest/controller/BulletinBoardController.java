package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.BulletinBoard;
import edu.uog.thunbergTest.service.impl.BulletinBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    @Autowired
    private BulletinBoardServiceImpl bulletinBoardServiceImpl;

    @RequestMapping(path = "/selectBulletinBoard", method = RequestMethod.GET)
    public List<BulletinBoard> selectBulletinBoard() {
        List<BulletinBoard> bulletinBoards = bulletinBoardServiceImpl.selectBulletinBoard();
        return bulletinBoards;
    }
}
