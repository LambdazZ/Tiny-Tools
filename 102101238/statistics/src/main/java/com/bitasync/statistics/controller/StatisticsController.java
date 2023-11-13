package com.bitasync.statistics.controller;

import com.bitasync.statistics.model.Statistics;
import com.bitasync.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/statistics/update")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    void updateStatistics(@RequestBody Statistics statistics)
    {
        if(!statisticsService.getStatisticsByToken(statistics.getToken()).isPresent())
            insert(statistics.getToken());

        statistics.setEscapes(statistics.getEscapes() == null ? 0: statistics.getEscapes());
        statistics.setTimeouts(statistics.getTimeouts() == null ? 0: statistics.getTimeouts());
        statistics.setTotalOrders(statistics.getTotalOrders() == null ? 0: statistics.getTotalOrders());
        statistics.setPositiveReviews(statistics.getPositiveReviews() == null ? 0: statistics.getPositiveReviews());
        statistics.setNegativeReviews(statistics.getNegativeReviews() == null ? 0: statistics.getNegativeReviews());
        statisticsService.updateStatistics(statistics);
    }

    void insert(String token)
    {
        Statistics statistics = new Statistics();
        statistics.setToken(token);
        statistics.setEscapes(0);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(0);
        statisticsService.insertStatistics(statistics);
    }
}
