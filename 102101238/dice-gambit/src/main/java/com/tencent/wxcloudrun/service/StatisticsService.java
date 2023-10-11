package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Statistics;
import com.tencent.wxcloudrun.model.UserLogin;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/11
 */
public interface StatisticsService
{
    Optional<Statistics> getStatistics(Integer id);
    Optional<Statistics> getStatisticsByToken(String token);

    void insertStatistics(Statistics statistics);

    void updateStatistics(Statistics statistics);

    void clearStatistics(Integer id);
}
