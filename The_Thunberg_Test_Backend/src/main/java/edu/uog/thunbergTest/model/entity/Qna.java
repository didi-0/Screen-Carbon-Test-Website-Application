package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/09:21
 */
@Data
@TableName("qna")
public class Qna {
    @TableId
    private Integer questionId;
    private String question;
    private String answer;
}
