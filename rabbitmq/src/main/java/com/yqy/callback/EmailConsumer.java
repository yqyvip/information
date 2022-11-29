package com.yqy.callback;

import com.alibaba.fastjson.JSON;
import com.yqy.domain.Email;
import com.yqy.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.yqy.utils.Common.CONFIRM_QUEUE_NAME;

/**
 * @author yqy
 * @date 2022-11-29-10:47
 */
@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    private EmailService loginEmailService;

    @RabbitListener(queues = CONFIRM_QUEUE_NAME)
    public void processEmail(@Payload String msg) {
        log.info("接收的邮件为："+msg);
        Email email = JSON.parseObject(msg, Email.class);
        loginEmailService.loginEmail(email);

    }
}
