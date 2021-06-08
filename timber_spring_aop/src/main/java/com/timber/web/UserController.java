package com.timber.web;

import com.timber.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController{
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.save(996,888);
//        int ret = userService.update();
//        System.out.println("app...返回值" + ret);
        userService.delete();
    }
}
