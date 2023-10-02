package edu.uog.thunbergTest.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/17:02
 */
@Data
public class CrewSingle {
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private Integer id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private Double vote_average;
    private Integer vote_count;
    private String credit_id;
    private String department;
    private String job;
    private String media_type;
}
