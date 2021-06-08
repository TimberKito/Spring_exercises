package com.timber.service.impl;

import com.timber.service.UserService;

public class UserServiceImpl implements UserService{
    public void save(int i, int j){
        //0.抽取共性功能
        //System.out.println("共性功能...");

        System.out.println("user service running ..." + i + "," + j);

//        int i = 1/0; //抛异常
    }

    public int update(){
        System.out.println("user service update ...");
        return 200;
    }

    public void delete(){
        System.out.println("user service delete ...");
        int i = 1/0; //抛异常
    }
}
