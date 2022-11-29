package com.yqy.redis.controller;

import com.yqy.redis.service.LoginRedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yqy
 * @date 2022-11-22-17:22
 */
@RestController
public class LoginRedisController {
    @Resource
    private LoginRedisService loginRedisService;

    @GetMapping("/login/setCode/{code}/{email}")
    public void setRedisCode(@PathVariable("code") String code, @PathVariable("email") String email) {
        loginRedisService.setCode(email, code);
    }
    @GetMapping("/login/verifyCode/{code}/{email}")
    public Boolean verifyRedisCode(@PathVariable("code") String code, @PathVariable("email") String email) {
        return loginRedisService.verifyCode(email, code);
    }

}
