package com.yqy.redis.service.impl;

import com.yqy.redis.service.LoginRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.yqy.utils.Common.USER_LOGIN_EMAIL;
import static com.yqy.utils.Common.USER_LOGIN_EMAIL_TIME;

/**
 * @author yqy
 * @date 2022-11-22-16:32
 */
@Service
@Slf4j
public class LoginRedisServiceImpl implements LoginRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean verifyCode(String email, String code) {
        String redisCode = stringRedisTemplate.opsForValue().get(USER_LOGIN_EMAIL + email);
        if (Objects.isNull(redisCode)) {
            return false;
        }
        log.info("redis中的login code：" + redisCode);
        if (Objects.equals(redisCode, code)) {
            return true;
        }
        return false;
    }

    @Override
    public void setCode(String email, String code) {
        stringRedisTemplate.opsForValue().set(USER_LOGIN_EMAIL + email, code, USER_LOGIN_EMAIL_TIME, TimeUnit.MINUTES);

    }
}
