package com.bitasync.statistics.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lambda
 * @date 2023/11/12
 */
@Data
public class Statistics implements Serializable
{
    private Integer id;
    private String token;
    private Integer escapes;
    private Integer timeouts;
    private Integer totalOrders;
    private Integer positiveReviews;
    private Integer negativeReviews;
}
