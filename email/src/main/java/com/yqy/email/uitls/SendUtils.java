package com.yqy.email.uitls;

import com.yqy.domain.Email;
import com.yqy.email.service.SimpleMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yqy
 * @date 2022-11-11-11:02
 */
@Slf4j
@Component
public class SendUtils {
    @Value("${email.username:yqy6@foxmail.com}")
    private String username;

    @Value("${email.password:cyqntslzqarzdjdj}")
    private String password;

    public Boolean send(Email email){
        // 设置邮件服务器信息
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.qq.com");// 发送邮件的服务器的IP(或主机地址)
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);

        // 邮箱用户名(根据自己情况设置)
        mailInfo.setUserName(username);
        // 邮箱密码(根据自己情况设置)(注意此处密码为授权码)
        mailInfo.setPassword(password);
        // 发件人邮箱(根据自己情况设置,如果你没对邮箱进行特别设置,应该和邮箱用户名一致)
        mailInfo.setFromAddress(username);
        // 收件人邮箱(根据自己情况设置)
        //设置为发送给多人
//        mailInfo.setToAddress("2225194102@163.com,2225194102@qq.com");
        mailInfo.setToAddress(email.getUser());

        // 邮件标题
        mailInfo.setSubject(email.getTitle());
        // 邮件内容
        StringBuffer buffer = new StringBuffer();
        buffer.append(email.getMessage());
        mailInfo.setContent(buffer.toString());
        // 发送邮件
        SimpleMailSender sms = new SimpleMailSender();

        // 发送文体格式
        sms.sendTextMail(mailInfo);
        // 发送html格式
//        SimpleMailSender.sendHtmlMail(mailInfo);

        log.info("邮件反射完毕");
        return true;
    }
}
