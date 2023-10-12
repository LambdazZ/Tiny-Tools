package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.Statistics;
import com.tencent.wxcloudrun.model.UserLogin;
import com.tencent.wxcloudrun.service.StatisticsService;
import com.tencent.wxcloudrun.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/11
 */

@RestController
public class StatisticsController
{
    final StatisticsService statisticsService;

    public StatisticsController(@Autowired StatisticsService statisticsService)
    {
        this.statisticsService = statisticsService;
    }

    @PostMapping(value = "/statistics/update")
    void updateStatistics(@RequestBody Statistics statistics)
    {
        if(!statisticsService.getStatisticsByToken(statistics.getToken()).isPresent())
            insert(statistics.getToken(), "");

        statisticsService.updateStatistics(statistics);
    }

    @GetMapping(value = "/statistics/get")
    Statistics getStatistics(@RequestParam("token") String token, @RequestParam("openid") String openid)
    {
        Optional<Statistics> statisticsByToken = statisticsService.getStatisticsByToken(token);
        if(!statisticsByToken.isPresent())
            insert(token, openid);

        return statisticsService.getStatisticsByToken(token).get();
    }

    void insert(String token, String openid)
    {
        Statistics statistics = new Statistics();
        statistics.setToken(token);
        statistics.setOpenid(openid);
        statistics.setEasyRobotWin(0);
        statistics.setMediumRobotWin(0);
        statistics.setHardRobotWin(0);
        statistics.setPattern1(0);
        statistics.setPattern2(0);
        statistics.setPattern3(0);
        statistics.setPattern4(0);
        statistics.setPattern5(0);
        statistics.setPattern6(0);
        statistics.setPattern7(0);
        statistics.setPattern8(0);
        statisticsService.insertStatistics(statistics);
    }
}
