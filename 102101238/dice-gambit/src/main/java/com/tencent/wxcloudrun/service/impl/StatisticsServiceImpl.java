package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.StatisticsMapper;
import com.tencent.wxcloudrun.dao.UserLoginMapper;
import com.tencent.wxcloudrun.model.Statistics;
import com.tencent.wxcloudrun.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/11
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
        statisticsMapper.insertStatistics(statistics);
    }

    @Override
    public void updateStatistics(Statistics statistics)
    {
        statisticsMapper.updateStatistics(statistics);
    }

    @Override
    public void clearStatistics(Integer id)
    {
        statisticsMapper.clearStatistics(id);
    }
}
