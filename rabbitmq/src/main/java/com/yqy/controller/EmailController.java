package com.yqy.controller;

import com.alibaba.fastjson.JSON;
import com.yqy.domain.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.yqy.utils.Common.CONFIRM_QUEUE_NAME;

/**
 * @author yqy
 * @date 2022-11-29-10:33
 */
@RestController
@Slf4j
public class EmailController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send() {
        Email email = new Email();
        email.setTitle("yqy");
        email.setMessage("来啦");
        email.setUser("2225194102@qq.com");
        String s = JSON.toJSONString(email);
        rabbitTemplate.convertAndSend(CONFIRM_QUEUE_NAME, s);
        return "ss";
    }

    @PostMapping("/send/email")
    @ResponseBody
    public Boolean sendEmail(@RequestBody Email email) {
        String s = JSON.toJSONString(email);
        try {
            rabbitTemplate.convertAndSend(CONFIRM_QUEUE_NAME, s);
        } catch (Exception exception) {
            log.error("远程调用的rabbitmq中邮件服务失败");
            return false;
        }
        return true;
    }
}
