package com.timber.web;

import com.timber.config.SpringConfiguration;
import com.timber.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
  public static void main(String[] args) {
    //    使用类配置
//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//    applicationContext.register(SpringConfiguration.class);
//    applicationContext.refresh();
//    UserService userService = applicationContext.getBean("userService", UserService.class);
//    userService.save();

    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = (UserService) app.getBean("userService");
    userService.save();

  }
}
