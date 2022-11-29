package com.yqy.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yqy
 * @date 2022-11-22-14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String user;
    private String title;
    private String message;

}
