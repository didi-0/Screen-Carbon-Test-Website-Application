package edu.uog.thunbergTest.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.uog.thunbergTest.model.MovieSearch;
import edu.uog.thunbergTest.model.TvSearch;
import edu.uog.thunbergTest.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/10:04
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServiceImpl searchServiceImpl;

    @RequestMapping(path = "/selectMovie", method = RequestMethod.GET)
    public Page<MovieSearch> selectMovie(@RequestParam(name = "certification") String certification,
                                         @RequestParam(name = "page") Integer page,
                                         @RequestParam(name = "sort_by") String sort,
                                         @RequestParam(name = "with_genres", required = false) String genres,
                                         @RequestParam(name = "with_keywords" ,required = false) String keywords) {
        Page<MovieSearch> movieSearchPage = new Page<>();
        try {
            movieSearchPage = searchServiceImpl.selectMovie(certification, page, sort, genres, keywords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movieSearchPage;
    }

    @RequestMapping(path = "/selectTv", method = RequestMethod.GET)
    public Page<TvSearch> selectTv(@RequestParam(name = "certification") String certification,
                                   @RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "sort_by") String sort,
                                   @RequestParam(name = "with_genres") String genres) {
        Page<TvSearch> tvSearchPage = new Page<>();
        try {
        tvSearchPage = searchServiceImpl.selectTv(certification, page, sort, genres);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tvSearchPage;
    }
}
