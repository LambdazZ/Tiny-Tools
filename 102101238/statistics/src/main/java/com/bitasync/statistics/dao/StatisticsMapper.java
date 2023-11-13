package com.bitasync.statistics.dao;

import com.bitasync.statistics.model.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lambda
 * @date 2023/11/12
 */
public interface StatisticsMapper
{
    Statistics getStatistics(@Param("id") Integer id);

    Statistics getStatisticsByToken(@Param("token") String token);

    void insertStatistics(Statistics statistics);

    void updateStatistics(Statistics statistics);

    void clearStatistics(@Param("id") Integer id);
}
