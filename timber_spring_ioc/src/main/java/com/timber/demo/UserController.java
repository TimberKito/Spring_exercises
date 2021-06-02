package com.timber.demo;

import com.timber.service.UserService;
import com.timber.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
  public static void main(String[] args) {
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = (UserService) app.getBean("userService");
    userService.save();

    //    UserService userService = new UserServiceImpl();
    //    userService.save(); // NullPointerException

  }
}
