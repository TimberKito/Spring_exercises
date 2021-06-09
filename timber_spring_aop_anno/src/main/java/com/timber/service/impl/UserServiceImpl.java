package com.timber.service.impl;

import com.timber.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    public int update(int i ,int j){
        System.out.println("user service update ..." + i +".and." + j);
        return 200;
    }

}
