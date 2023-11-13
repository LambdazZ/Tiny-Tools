package com.bitasync.statistics.controller;

import com.bitasync.statistics.model.Statistics;
import com.bitasync.statistics.service.StatisticsService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/12
 */
@RestController
public class StatisticsController
{
    final StatisticsService statisticsService;

    public StatisticsController(@Autowired StatisticsService statisticsService)
    {
        this.statisticsService = statisticsService;
    }

    @PostMapping(value = "/statistics/get")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @ResponseBody
    Statistics getStatistics(@RequestBody Statistics statistics)
    {
        return statisticsService.getStatistics(statistics.getId()).get();
    }

    @PostMapping(value = "/statistics/getByToken")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @ResponseBody
    Statistics getStatisticsByToken(@RequestBody Statistics statistics)
    {
        return statisticsService.getStatisticsByToken(statistics.getToken()).get();
    }
}
