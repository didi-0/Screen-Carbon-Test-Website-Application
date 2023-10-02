package edu.uog.thunbergTest.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/16:58
 */
@Data
public class PersonCreditsDto {
    List<CastSingle> cast;
    List<CrewSingle> crew;
}
