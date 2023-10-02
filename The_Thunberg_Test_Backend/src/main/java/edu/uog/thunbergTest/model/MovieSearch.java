package edu.uog.thunbergTest.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import lombok.Data;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;

import java.io.Serializable;

/**
 * @Description: MovieSearch
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/11:39
 */
@Data
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class MovieSearch implements Serializable {
    @ExcelIgnore
    private Integer id;
    @ColumnWidth(35)
    @ExcelProperty("Movie Title")
    private String title;
    @ColumnWidth(25)
    @ExcelProperty("Release Year")
    private String releaseYear;
    @ExcelProperty("Score")
    private Double score;
    @ColumnWidth(25)
    @ExcelProperty("Director")
    private String director;
    @ColumnWidth(25)
    @ExcelProperty("Producer")
    private String producer;
}
