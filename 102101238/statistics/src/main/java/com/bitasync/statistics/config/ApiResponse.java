package com.bitasync.statistics.config;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Lambda
 * @date 2023/11/13
 */
@Data
public class ApiResponse
{
    private Integer code;
    private String errorMsg;
    private Object data;

    private ApiResponse(int code, String errorMsg, Object data)
    {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static ApiResponse ok(Integer code)
    {
        return new ApiResponse(code, "", new HashMap<>());
    }

    public static ApiResponse ok(Integer code, Object data)
    {
        return new ApiResponse(code, "", data);
    }

    public static ApiResponse error(Integer code, String errorMsg)
    {
        return new ApiResponse(code, errorMsg, new HashMap<>());
    }
}
