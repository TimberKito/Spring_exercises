package com.timber.service.impl;

import com.timber.dao.UserDao;
import com.timber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

// <bean id="userService" class="com.timber.service.impl.UserServiceImpl">
// @Component("userService")
@Service("userService")
@Scope("singleton")
// @Scope("singleton") 单例和多例模式注解
public class UserServiceImpl implements UserService {

  //  注入数据类型
  @Value("${jdbc.driver}")
  private String driver;

  // <property name="userDao" ref="userDao"></property>
  @Autowired // 按照数据类型从 Spring 容器中进行匹配
  @Qualifier("userDao") // 是按照 ID 指从容器中进行匹配，但此处要结合 @Autowired 来使用
  //  @Resource(name="userDao") // @Autowired + @Qualifier("userDao")
  private UserDao userDao;

  // 使用注解方式，可以不用写 set 方法
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void save() {
    System.out.println(driver);
    userDao.save();
  }


  // 构造函数后执行
  @PostConstruct
  public void init() {
    System.out.println("对象初始化方法 ...");
  }

  @PreDestroy
  public void destory() {
    System.out.println("对象的销毁方法 ...");
  }
}
