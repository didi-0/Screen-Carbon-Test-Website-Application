package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/15:26
 */
@Data
@TableName("celeb_rate")
public class CelebRate {
    private Integer id;
    private Double score;
    private Integer celebId;
    private String ratingDate;
}
