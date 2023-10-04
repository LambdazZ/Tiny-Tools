package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserLoginMapper;
import com.tencent.wxcloudrun.model.UserLogin;
import com.tencent.wxcloudrun.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/2
 */

@Service
public class UserLoginServiceImpl implements UserLoginService
{

    final UserLoginMapper userLoginMapper;

    public UserLoginServiceImpl(@Autowired UserLoginMapper userLoginMapper) {
        this.userLoginMapper = userLoginMapper;
    }

    @Override
    public Optional<UserLogin> getUserLogin(Integer id)
    {
        return Optional.ofNullable(userLoginMapper.getUserLogin(id));
    }

    @Override
    public Optional<UserLogin> getUserLoginByToken(String token)
    {
        return Optional.ofNullable(userLoginMapper.getUserLoginByToken(token));
    }

    @Override
    public void upsertUserLogin(UserLogin userLogin)
    {
        userLoginMapper.upsertUserLogin(userLogin);
    }

    @Override
    public void clearUserLogin(Integer id)
    {
        userLoginMapper.clearUserLogin(id);
    }
}
