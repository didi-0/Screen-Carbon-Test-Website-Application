package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/21:45
 */
@Data
@TableName("`option`")
public class Option {
    @TableId
    private Integer optionId	;

    private Integer questionId	;

    private String content;
}
