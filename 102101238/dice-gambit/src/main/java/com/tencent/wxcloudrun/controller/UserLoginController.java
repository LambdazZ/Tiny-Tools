package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.UserLogin;
import com.tencent.wxcloudrun.service.UserLoginService;
import com.tencent.wxcloudrun.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/10/2
 */

@RestController
public class UserLoginController
{
    private static final String appid = "";
    private static final String secret = "";

    final UserLoginService userLoginService;
    final Logger logger;

    public UserLoginController(@Autowired UserLoginService userLoginService)
    {
        this.userLoginService = userLoginService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }


    @GetMapping(value = "/isLogin")
    boolean isLogin(@RequestParam("token") String token)
    {
        //System.out.println(token);
        Optional<UserLogin> userLogin = userLoginService.getUserLoginByToken(token);
        //System.out.println(userLogin.isPresent());
        return userLogin.isPresent();
    }

    @PostMapping(value = "/login")
    String login(@RequestParam("code") String code, @RequestParam("nickname") String nickname, @RequestParam("avatarurl") String avatarurl)
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String res = HttpUtil.doGet(url);
        String openid = res.substring(res.indexOf("openid") + 9, res.lastIndexOf("\""));
        String session_key = res.substring(res.indexOf("session_key") + 14, res.indexOf("openid") - 3);
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

