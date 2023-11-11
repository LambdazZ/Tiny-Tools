package com.bitasync.statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lambda
 * @date 2023/11/11
 */
@RestController
public class HelloController
{
    @GetMapping("/")
    public String hello(){
        return "Hello, World!";
    }
}
