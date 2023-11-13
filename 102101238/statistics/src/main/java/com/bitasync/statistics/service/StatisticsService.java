package com.bitasync.statistics.service;

import com.bitasync.statistics.model.Statistics;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/12
 */
public interface StatisticsService
{
    Optional<Statistics> getStatistics(Integer id);
    Optional<Statistics> getStatisticsByToken(String token);

    void insertStatistics(Statistics statistics);

    void updateStatistics(Statistics statistics);

    void clearStatistics(Integer id);
}
