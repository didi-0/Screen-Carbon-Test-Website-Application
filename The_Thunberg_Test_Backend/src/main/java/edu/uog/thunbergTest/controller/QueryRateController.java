package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.Survey;
import edu.uog.thunbergTest.service.impl.QueryRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/21:48
 */
@RestController
@CrossOrigin
@RequestMapping("/queryRate")
public class QueryRateController {

    @Autowired
    private QueryRateServiceImpl queryRateService;

    @RequestMapping(path = "/select", method = RequestMethod.POST)
    public List<Survey> select(@RequestBody List<Integer> surveyIds) {
        List<Survey> surveys = queryRateService.select(surveyIds);
        return surveys;
    }

}
