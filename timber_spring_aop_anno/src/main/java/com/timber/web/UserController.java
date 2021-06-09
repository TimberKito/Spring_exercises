package com.timber.web;

import com.timber.config.SpringConfig;
import com.timber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController{
    public static void main(String[] args){
//        ApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("");
//        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.update(996,888);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.update(996,888);

    }
}
