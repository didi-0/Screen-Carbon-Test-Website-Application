package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/21:45
 */
@Data
@TableName("question")
public class Question {
    @TableId
    private Integer questionId	;

    private Integer surveyId;

    private String content;

    @TableField(exist = false)
    private List<Option> optionList;
}
