package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.FilmRate;
import edu.uog.thunbergTest.service.impl.FilmRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/21/01:23
 */
@RestController
@CrossOrigin
@RequestMapping("/filmRate")
public class FilmRateController {

    @Autowired
    private FilmRateServiceImpl filmRateService;

    @RequestMapping(path = "/select", method = RequestMethod.GET)
    public FilmRate select(@RequestParam("filmId") Integer filmId, @RequestParam("filmType") String filmType) {
        FilmRate filmRate = filmRateService.select(filmId, filmType);
        return filmRate;
    }
}
