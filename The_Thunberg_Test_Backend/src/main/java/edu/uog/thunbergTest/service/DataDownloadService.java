package edu.uog.thunbergTest.service;

import edu.uog.thunbergTest.model.MovieSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/16:12
 */
public interface DataDownloadService {
    List<MovieSearch> download(String certification, String sort, String genres, String keywords) throws IOException;
}
