package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/13:48
 */
@Data
@TableName("film_rate")
public class FilmRate {
    private Double score;
    private Integer filmId;
    private String filmType;
    private String ratingDate;
}
