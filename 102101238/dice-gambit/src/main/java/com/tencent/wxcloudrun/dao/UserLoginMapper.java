package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lambda
 * @date 2023/10/2
 */

@Mapper
public interface UserLoginMapper
{
    UserLogin getUserLogin(@Param("id") Integer id);

    UserLogin getUserLoginByToken(@Param("token") String token);

    void upsertUserLogin(UserLogin userLogin);

    void clearUserLogin(@Param("id") Integer id);
}
