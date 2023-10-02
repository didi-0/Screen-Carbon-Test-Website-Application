package edu.uog.thunbergTest.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName("bulletin_board")
public class BulletinBoard implements Serializable {
    private int id;
    private String title;
    private String subtitle;
    private String content;
    private int number;
    private String editUser;
}
