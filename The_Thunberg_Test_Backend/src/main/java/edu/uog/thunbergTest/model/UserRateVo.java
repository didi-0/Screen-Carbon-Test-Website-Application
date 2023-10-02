package edu.uog.thunbergTest.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/23:10
 */
@Data
public class UserRateVo {
    private Integer id;
    private String mediaType;
    private List<Integer> values;
}
