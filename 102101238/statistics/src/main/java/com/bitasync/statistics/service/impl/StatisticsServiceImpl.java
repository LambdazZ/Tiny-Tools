package com.bitasync.statistics.service.impl;

import com.bitasync.statistics.dao.StatisticsMapper;
import com.bitasync.statistics.model.Statistics;
import com.bitasync.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/12
 */
@Service
public class StatisticsServiceImpl implements StatisticsService
{
    final StatisticsMapper statisticsMapper;

    public StatisticsServiceImpl(@Autowired StatisticsMapper statisticsMapper)
    {
        this.statisticsMapper = statisticsMapper;
    }

    @Override
    public Optional<Statistics> getStatistics(Integer id)
    {
        return Optional.ofNullable(statisticsMapper.getStatistics(id));
    }

    @Override
    public Optional<Statistics> getStatisticsByToken(String token)
    {
        return Optional.ofNullable(statisticsMapper.getStatisticsByToken(token));
    }

    @Override
    public void insertStatistics(Statistics statistics)
    {

    }

    @Override
    public void updateStatistics(Statistics statistics)
    {

    }

    @Override
    public void clearStatistics(Integer id)
    {

    }
}
