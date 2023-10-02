package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.UserRateVo;
import edu.uog.thunbergTest.model.entity.UserRate;
import edu.uog.thunbergTest.service.impl.UserRateServiceImpl;
import edu.uog.thunbergTest.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/14:33
 */
@RestController
@CrossOrigin
@RequestMapping("/userRate")
public class UserRateController {

    @Autowired
    private UserRateServiceImpl userRateServiceImpl;

    @RequestMapping(path = "/select", method = RequestMethod.GET)
    public List<UserRate> select(@RequestParam("filmId") Integer filmId, @RequestParam("filmType") Integer filmType) {
        List<UserRate> userRates = userRateServiceImpl.select(filmId, filmType);
        return userRates;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public int update(@RequestBody UserRate userRate) {
        int rows = userRateServiceImpl.update(userRate);
        return rows;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public int delete(@RequestParam("filmId") Integer filmId, @RequestParam("userId") Integer userId) {
        int rows = userRateServiceImpl.delete(filmId, userId);
        return rows;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public int insert(@RequestBody UserRateVo userRateVo, HttpServletRequest request) {
        System.out.println("userRateVo: " + userRateVo);
        String username = getUsername(request);
        List<Integer> values = userRateVo.getValues();
        values.set(3 - 1, 10 - values.get(3 - 1));
        values.set(5 - 1, 10 - values.get(5 - 1));
        values.set(7 - 1, 10 - values.get(7 - 1));
        values.set(8 - 1, 10 - values.get(8 - 1));
        values.set(11 - 1, 10 - values.get(11 - 1));
        values.set(12 - 1, 10 - values.get(12 - 1));
        values.set(15 - 1, 10 - values.get(15 - 1));
        int rows = userRateServiceImpl.insert(userRateVo, username);
        return rows;
    }

    private static String getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("token".equals(name)) {
                    String token = cookie.getValue();
                    username = JwtUtils.parseToken(token).getSubject();
                }
            }
        }
        return username;
    }
}
