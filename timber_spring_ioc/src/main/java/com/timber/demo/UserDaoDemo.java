package com.timber.demo;

import com.timber.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoDemo {

  public static void main(String[] args) {
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserDao userDao = (UserDao) app.getBean("userDao");
    userDao.save();
  }
}
