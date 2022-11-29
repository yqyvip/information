package com.yqy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqy.domain.User;
import com.yqy.service.UserService;
import com.yqy.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




