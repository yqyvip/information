package com.yqy.email.controller;
import com.alibaba.fastjson.JSON;
import com.yqy.domain.Email;
import com.yqy.email.uitls.SendUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yqy
 * @date 2022-11-22-14:55
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private SendUtils sendUtils;

    @PostMapping("/login/email")
    @ResponseBody
    public Boolean loginEmail(@RequestBody Email email) {
        log.info("邮件为：" + JSON.toJSONString(email));
        sendUtils.send(email);
        return true;
    }
}
