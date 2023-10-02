package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.entity.User;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/28/10:06
 */
public interface UserService {
    public User select(String username);

    int insert(User user);
}
