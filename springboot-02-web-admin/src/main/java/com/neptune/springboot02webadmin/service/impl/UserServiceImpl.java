package com.neptune.springboot02webadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neptune.springboot02webadmin.bean.User;
import com.neptune.springboot02webadmin.mapper.UserMapper;
import com.neptune.springboot02webadmin.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
