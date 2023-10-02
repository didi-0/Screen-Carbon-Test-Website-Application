package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/14:36
 */
@Data
@TableName("user_rate")
public class UserRate {
    private Double score;
    private Integer filmId;
    private String filmType;
    private String username;
    private String ratingDate;
    private String editAt;
}
