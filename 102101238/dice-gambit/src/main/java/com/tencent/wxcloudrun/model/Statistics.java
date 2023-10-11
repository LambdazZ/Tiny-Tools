package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lambda
 * @date 2023/10/11
 */

@Data
public class Statistics implements Serializable
{
    private Integer id;
    private String token;
    private String openid;
    private Integer easyRobotWin;
    private Integer mediumRobotWin;
    private Integer hardRobotWin;
    private Integer pattern1;
    private Integer pattern2;
    private Integer pattern3;
    private Integer pattern4;
    private Integer pattern5;
    private Integer pattern6;
    private Integer pattern7;
    private Integer pattern8;
}
