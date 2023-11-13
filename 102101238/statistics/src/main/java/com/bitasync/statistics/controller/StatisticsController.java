package com.bitasync.statistics.controller;

import com.bitasync.statistics.config.ApiResponse;
import com.bitasync.statistics.model.Statistics;
import com.bitasync.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @PostMapping(value = "/statistics/inc")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ApiResponse updateStatistic(@RequestBody Statistics statistics, @RequestParam String fieldName)
    {
        Optional<Statistics> res = statisticsService.getStatisticsByToken(statistics.getToken());
        if (res.isEmpty())
            return ApiResponse.error(404, "请求的对象不存在");

        try
        {
            resetFields(statistics);

            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Method setterMethod = Statistics.class.getMethod(setterName, Integer.class);
            setterMethod.invoke(statistics, 1);

            statisticsService.updateStatistics(statistics);
            return ApiResponse.ok(200);

        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            return ApiResponse.error(500, "服务器内部错误");
        }
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

    private void resetFields(Statistics statistics)
    {
        statistics.setEscapes(0);
        statistics.setTimeouts(0);
        statistics.setTotalOrders(0);
        statistics.setPositiveReviews(0);
        statistics.setNegativeReviews(0);
    }
}
