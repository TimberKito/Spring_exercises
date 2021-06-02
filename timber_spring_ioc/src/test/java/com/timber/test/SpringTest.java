package com.timber.test;

import com.timber.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest{
    @Test
    //测试 scope 属性
    /*
      singleton 单例模式 加载配置文件创建Spring容器时，创建对象（生命周期伴随容器）
      prototype 多例模式 getBean时，创建对象（生命周期交给Java）
     */
    public void test1(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
//        UserDao userDao2 = (UserDao) app.getBean("userDao");
        System.out.println(userDao1);
//        System.out.println(userDao2);
//        ((ClassPathXmlApplicationContext)app).close();
    }
}
