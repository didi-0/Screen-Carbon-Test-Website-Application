package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.uog.thunbergTest.mapper.FilmRateMapper;
import edu.uog.thunbergTest.mapper.UserMapper;
import edu.uog.thunbergTest.model.entity.User;
import edu.uog.thunbergTest.model.entity.UserRate;
import edu.uog.thunbergTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/28/10:06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public int insert(User user) {
        user.setUserRole(0);
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        String format = dtf.format(new Date());
        user.setRegistrationDate(format);
        int row = userMapper.insert(user);
        return row;
    }
}
