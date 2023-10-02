package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.entity.FilmRate;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/13:51
 */
public interface FilmRateService {
    public FilmRate select(Integer filmId, String filmType);
}
