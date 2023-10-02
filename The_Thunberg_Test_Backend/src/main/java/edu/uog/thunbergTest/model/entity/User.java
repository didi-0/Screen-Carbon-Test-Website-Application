package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/27/22:14
 */
@Data
@TableName("user")
public class User {
    private String username;
    private String password;
    private String bio;
    private Integer userRole;
    private String registrationDate;
}