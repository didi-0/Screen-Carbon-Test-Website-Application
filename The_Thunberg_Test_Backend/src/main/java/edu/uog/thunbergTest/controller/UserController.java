package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.User;
import edu.uog.thunbergTest.service.impl.UserServiceImpl;
import edu.uog.thunbergTest.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/27/22:11
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        String token = JwtUtils.createToken(user.getUsername());
        return token;
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public User info(@RequestParam("token") String token) {
        String username = JwtUtils.parseToken(token).getSubject();
        User user = userService.select(username);
        return user;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Integer register(@RequestBody User user) {
        int row = userService.insert(user);
        return row;
    }
}
