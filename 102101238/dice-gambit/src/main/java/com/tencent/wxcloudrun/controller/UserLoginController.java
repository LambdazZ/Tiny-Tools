package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.UserLogin;
import com.tencent.wxcloudrun.service.UserLoginService;
import com.tencent.wxcloudrun.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/2
 */

@RestController
public class UserLoginController
{
    private static final String appid = "wxd6ba3b2f90fc12d3";
    private static final String secret = "1898c1d5410133371b4e3a213097605c";

    final UserLoginService userLoginService;

    public UserLoginController(@Autowired UserLoginService userLoginService)
    {
        this.userLoginService = userLoginService;
    }


    @GetMapping(value = "/isLogin")
    boolean isLogin(@RequestParam("token") String token)
    {
        //System.out.println(token);
        Optional<UserLogin> userLogin = userLoginService.getUserLoginByToken(token);
        //System.out.println(userLogin.isPresent());
        return userLogin.isPresent();
    }

    @GetMapping(value = "/login")
    String login(@RequestParam("code") String code, @RequestParam("nickname") String nickname, @RequestParam("avatarurl") String avatarurl)
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        System.out.println(url);
        String res = HttpUtil.doGet(url);
        System.out.println(res);
        String openid = "null";
        String session_key = "null";
        if(!res.contains("errcode"))
        {
            openid = res.substring(res.indexOf("openid") + 9, res.lastIndexOf("\""));
            session_key = res.substring(res.indexOf("session_key") + 14, res.indexOf("openid") - 3);
        }
        String token = Long.toHexString(System.currentTimeMillis());
        UserLogin userLogin = new UserLogin();
        userLogin.setOpenid(openid);
        userLogin.setSession_key(session_key);
        userLogin.setToken(token);
        userLogin.setNickname(nickname);
        userLogin.setAvatarurl(avatarurl);
        userLoginService.upsertUserLogin(userLogin);

//        System.out.println(token);
//        System.out.println(res);
//        System.out.println(session_key);
//        System.out.println(openid);
//        System.out.println(nickname);
//        System.out.println(avatarurl);

        return token;
    }

    @GetMapping(value = "/getProfile")
    UserLogin getProfile(@RequestParam("token") String token)
    {
        return userLoginService.getUserLoginByToken(token).get();
    }
}

