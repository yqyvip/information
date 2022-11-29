package com.yqy.redis.service;

/**
 * @author yqy
 * @date 2022-11-22-16:30
 */
public interface LoginRedisService {
    Boolean verifyCode(String email,String code);

    void setCode(String email, String code);
}
