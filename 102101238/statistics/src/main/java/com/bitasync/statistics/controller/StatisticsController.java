package com.bitasync.statistics.controller;

import com.bitasync.statistics.config.ApiResponse;
import com.bitasync.statistics.model.Statistics;
import com.bitasync.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/statistics/create")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse create(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isPresent())
            return ApiResponse.error(404, "目标已存在");

        insert(statistics.getToken());
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/get")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @ResponseBody
    ApiResponse getStatistics(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatistics(statistics.getId());
        return res.map(value -> ApiResponse.ok(200, value)).orElseGet(() -> ApiResponse.error(404, "请求的对象不存在"));
    }

    @PostMapping(value = "/statistics/getByToken")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @ResponseBody
    ApiResponse getStatisticsByToken(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        return res.map(value -> ApiResponse.ok(200, value)).orElseGet(() -> ApiResponse.error(404, "请求的对象不存在"));
    }

    @PostMapping(value = "/statistics/update")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse updateStatistics(@RequestBody Statistics statistics)
    {
        if(!statisticsService.getStatisticsByToken(statistics.getToken()).isPresent())
            insert(statistics.getToken());

        statistics.setEscapes(statistics.getEscapes() == null ? 0: statistics.getEscapes());
        statistics.setTimeouts(statistics.getTimeouts() == null ? 0: statistics.getTimeouts());
        statistics.setTotalOrders(statistics.getTotalOrders() == null ? 0: statistics.getTotalOrders());
        statistics.setPositiveReviews(statistics.getPositiveReviews() == null ? 0: statistics.getPositiveReviews());
        statistics.setNegativeReviews(statistics.getNegativeReviews() == null ? 0: statistics.getNegativeReviews());
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/incEscapes")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse incEscapes(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        statistics.setEscapes(1);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(0);
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/incTimeouts")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse incTimeouts(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        statistics.setEscapes(0);
        statistics.setTimeouts(1);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(0);
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/incTotalOrders")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse incTotalOrders(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        statistics.setEscapes(0);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(1);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(0);
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/incPositiveReviews")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse incPositiveReviews(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        statistics.setEscapes(0);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(1);
        statistics.setNegativeReviews(0);
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
    }

    @PostMapping(value = "/statistics/incNegativeReviews")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    ApiResponse incNegativeReviews(@RequestBody Statistics statistics)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if(res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        statistics.setEscapes(0);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(1);
        statisticsService.updateStatistics(statistics);
        return ApiResponse.ok(200);
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
