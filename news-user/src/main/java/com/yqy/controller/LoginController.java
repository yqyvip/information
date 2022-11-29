package com.yqy.controller;

import com.yqy.domain.ActionResult;
import com.yqy.domain.Email;
import com.yqy.domain.User;
import com.yqy.service.LoginEmailMqService;
import com.yqy.service.LoginEmailService;
import com.yqy.service.LoginVerifyCodeRedisService;
import com.yqy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yqy
 * @date 2022-11-22-11:45
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginEmailService loginEmailService;

    @Autowired
    private LoginEmailMqService loginEmailMqService;

    @Autowired
    private LoginVerifyCodeRedisService loginVerifyCodeRedisService;

    @GetMapping("/enroll/{email}")
    public ActionResult setVerifyCode(@PathVariable String email) {
/*
TODO:
 1.验证邮箱正确性
 2.生成验证码
 3.调用redis服务，设置3分钟时效
 4.调用rabbitmq服务与注册服务解耦
 5.通过rabbitmq的消息调用email服务
 */
        Email e = new Email();
        e.setUser(email);
        e.setTitle("验证码:");
        String code = RandomUtils.nextInt(9999) + 1000 + "";
        log.info("验证码：" + code);
        loginVerifyCodeRedisService.setRedisCode(code, email);
        e.setMessage(code);
        loginEmailMqService.sendEmail(e);
        return ActionResult.defaultFail(222, "验证码已经发送到邮箱");
    }

    @GetMapping("/verify/{name}/{password}/{email}/{code}")
    public ActionResult enroll(@PathVariable("name") String name,
                               @PathVariable("password") String password,
                               @PathVariable("email") String email,
                               @PathVariable("code") String code
    ) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        Boolean isVerify = loginVerifyCodeRedisService.verifyRedisCode(code, email);
        if (isVerify) {
            log.info("注册用户：" + user);
            boolean isSave = userService.save(user);
            if (isSave) {
                return ActionResult.defaultOk(211, user);
            }
        }

        return ActionResult.defaultFail();
    }
}
