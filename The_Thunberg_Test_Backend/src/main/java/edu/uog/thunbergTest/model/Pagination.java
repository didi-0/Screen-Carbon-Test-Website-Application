package edu.uog.thunbergTest.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/11:46
 */
@Data
public class  Pagination<T> {

    private int page;

    private int pageSize = 20;

    private int totalPages;

    private int totalResults;

    private List<T> results;

}
