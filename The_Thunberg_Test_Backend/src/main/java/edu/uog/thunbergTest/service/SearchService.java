package edu.uog.thunbergTest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.uog.thunbergTest.model.MovieSearch;
import edu.uog.thunbergTest.model.TvSearch;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/10:07
 */
public interface SearchService {
    public Page<MovieSearch> selectMovie(String certification, Integer page, String sort, String genres, String keywords) throws IOException;

    public Page<TvSearch> selectTv(String certification, Integer page, String sort, String genres) throws IOException;

}
