package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Statistics;
import com.tencent.wxcloudrun.model.UserLogin;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lambda
 * @date 2023/10/11
 */
public interface StatisticsMapper
{
    Statistics getStatistics(@Param("id") Integer id);

    Statistics getStatisticsByToken(@Param("token") String token);

    void insertStatistics(Statistics statistics);

    void updateStatistics(Statistics statistics);

    void clearStatistics(@Param("id") Integer id);
}
