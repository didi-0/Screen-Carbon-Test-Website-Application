package edu.uog.thunbergTest.controller;

import edu.uog.thunbergTest.model.entity.CelebRate;
import edu.uog.thunbergTest.service.impl.CelebRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/15:24
 */
@RestController
@CrossOrigin
@RequestMapping("/celebRate")
public class CelebRateController {
    @Autowired
    private CelebRateServiceImpl celebRateServiceImpl;

    @RequestMapping(path = "/select", method = RequestMethod.GET)
    public CelebRate select(@RequestParam("celebId") Integer celebId) {
        CelebRate rate = celebRateServiceImpl.select(celebId);
        return rate;
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    public int updateAndInsert(@RequestBody CelebRate celebRate) {
        int rows = 0;
        try {
            rows = celebRateServiceImpl.updateAndInsert(celebRate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    @RequestMapping(path = "/selectBatch", method = RequestMethod.POST)
    public List<CelebRate> selectBatch(@RequestBody List<Integer> actorIds) {
        System.out.println("acotorIds: " + actorIds);
        List<CelebRate> celebRates = celebRateServiceImpl.selectBatch(actorIds);
        return celebRates;
    }
}
