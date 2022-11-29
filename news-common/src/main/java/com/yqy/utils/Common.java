package com.yqy.utils;

/**
 * @author yqy
 * @date 2022-11-22-16:44
 */
public interface Common {

    /** redis */
    String USER_LOGIN_EMAIL = "user.login.email";
    Long USER_LOGIN_EMAIL_TIME = 10L;

    /** rabbitmq*/
    String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    String CONFIRM_QUEUE_NAME = "confirm.queue.email";
    String NOTEBOOK_NAME = "notebook_name";
    String HELLO_QUEUE_NAME = "hello_queue_name";
    String WORK_QUEUE_NAME = "work_queue_name";
}
