package com.yqy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yqy
 * @date 2022-11-22-18:24
 */
@FeignClient("service-redis")
public interface LoginVerifyCodeRedisService {
    @GetMapping("/login/setCode/{code}/{email}")
    void setRedisCode(@PathVariable("code") String code, @PathVariable("email") String email);

    @GetMapping("/login/verifyCode/{code}/{email}")
    Boolean verifyRedisCode(@PathVariable("code") String code, @PathVariable("email") String email);
}
