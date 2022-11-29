package com.yqy.service;

import com.yqy.domain.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yqy
 * @date 2022-11-29-15:27
 */
@FeignClient("rabbitmq-service")
public interface LoginEmailMqService {
    @PostMapping("/send/email")
    @ResponseBody
    Boolean sendEmail(@RequestBody Email email);
}
