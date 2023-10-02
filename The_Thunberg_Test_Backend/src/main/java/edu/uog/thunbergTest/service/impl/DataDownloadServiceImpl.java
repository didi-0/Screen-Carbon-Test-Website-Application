package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.uog.thunbergTest.model.MovieSearch;
import edu.uog.thunbergTest.service.DataDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/16:11
 */
@Service
public class DataDownloadServiceImpl implements DataDownloadService {

    @Autowired
    private SearchServiceImpl searchService;

    @Override
    public List<MovieSearch> download(String certification, String sort, String genres, String keywords) throws IOException {

        ArrayList<MovieSearch> movieSearches = new ArrayList<>();

        System.out.println("selectMovie: 1");
        Page<MovieSearch> movieSearchPage = searchService.selectMovie(certification, 1, sort, genres, keywords);
        System.out.println("selectMovie: 1  end");
        if (movieSearchPage.getTotal() == 0){
            return movieSearches;
        }
        movieSearches.addAll(movieSearchPage.getRecords());
        long pages = movieSearchPage.getPages();
        System.out.println("total :" + movieSearchPage.getTotal());
        System.out.println("pages: " + pages);
        for (int i = 2; i <= pages; i++) {
            System.out.println("selectMovie: " + i);
            Page<MovieSearch> movies = searchService.selectMovie(certification, i, sort, genres, keywords);
            movieSearches.addAll(movies.getRecords());
            System.out.println("selectMovie: " + i + "  end");
        }
        return movieSearches;
    }
}
