package edu.uog.thunbergTest.controller;

import com.alibaba.excel.EasyExcel;
import edu.uog.thunbergTest.model.MovieSearch;
import edu.uog.thunbergTest.service.impl.DataDownloadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/16:05
 */
@RestController
@CrossOrigin
@RequestMapping("/download")
public class DataDownloadController {

    @Autowired
    private DataDownloadServiceImpl dataDownloadService;

    @RequestMapping(path = "/downloadMovie", method = RequestMethod.GET)
    public void downloadMovie(@RequestParam(name = "certification") String certification,
                                         @RequestParam(name = "sort_by") String sort,
                                         @RequestParam(name = "with_genres", required = false) String genres,
                                         @RequestParam(name = "with_keywords" ,required = false) String keywords, HttpServletResponse response) throws IOException {
        List<MovieSearch> movieSearches = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        System.out.println("startTime" + startTime);
        try {
            movieSearches = dataDownloadService.download(certification, sort, genres, keywords);
        } catch (IOException e) {
            System.out.println("异常！！！");
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime" + endTime);
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        // 改成你想要的文件名
        String fileName = "movie-green-rate" + format.format(new Date()) + ".xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        EasyExcel.write(response.getOutputStream(), MovieSearch.class).sheet("sheet1").doWrite(movieSearches);
    }
}
