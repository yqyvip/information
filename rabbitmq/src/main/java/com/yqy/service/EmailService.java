package com.yqy.service;

import com.yqy.domain.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yqy
 * @date 2022-11-22-15:41
 */

@FeignClient("service-email")
public interface EmailService {

    @PostMapping( "/login/email")
    @ResponseBody
    Boolean loginEmail(@RequestBody Email email);
}
