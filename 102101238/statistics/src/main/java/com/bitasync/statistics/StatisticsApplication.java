package com.bitasync.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.bitasync.statistics.dao"})
public class StatisticsApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(StatisticsApplication.class, args);
    }

}
