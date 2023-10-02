package edu.uog.thunbergTest.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/13:09
 */
@Data
public class Credit {
    private Integer id;

    private List<Cast> cast;

    private List<Crew> crew;
}
