package com.tencent.wxcloudrun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Lambda
 * @date 2023/10/3
 */
public class HttpUtil
{
    public static String doGet(String httpurl)
    {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try
        {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(30000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200)
            {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // 存放数据
                StringBuffer sb = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null)
                {
                    sb.append(temp);
                    sb.append("\r\n");
                }
                result = sb.toString();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭资源
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                connection.disconnect();// 关闭远程连接
            }
        }
        return result;
    }
}
