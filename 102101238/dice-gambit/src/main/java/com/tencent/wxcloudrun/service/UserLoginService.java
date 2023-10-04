package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.UserLogin;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/2
 */
public interface UserLoginService
{
    Optional<UserLogin> getUserLogin(Integer id);

    Optional<UserLogin> getUserLoginByToken(String token);

    void upsertUserLogin(UserLogin userLogin);

    void clearUserLogin(Integer id);
}
