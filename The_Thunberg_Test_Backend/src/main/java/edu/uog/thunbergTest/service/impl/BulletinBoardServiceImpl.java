package edu.uog.thunbergTest.service.impl;


import edu.uog.thunbergTest.mapper.BulletinBoardMapper;
import edu.uog.thunbergTest.model.entity.BulletinBoard;
import edu.uog.thunbergTest.service.BulletinBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xinzhou
 */
@Service
public class BulletinBoardServiceImpl implements BulletinBoardService {

    @Autowired
    private BulletinBoardMapper bulletinBoardMapper;

    @Override
    public List<BulletinBoard> selectBulletinBoard() {
        List<BulletinBoard> bulletinBoards = bulletinBoardMapper.selectList(null);
        return bulletinBoards;
    }
}
