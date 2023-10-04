package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lambda
 * @date 2023/10/2
 */

@Data
public class UserLogin implements Serializable
{
    private Integer id;

    private String token;

    private String session_key;

    private String openid;

    private String nickname;

    private String avatarurl;
}
