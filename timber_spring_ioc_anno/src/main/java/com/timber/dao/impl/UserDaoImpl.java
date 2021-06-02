package com.timber.dao.impl;

import com.timber.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// <bean id="userDao" class="com.timber.dao.impl.UserDaoImpl"/>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
  @Override
  public void save() {
    System.out.println("save ... running ...");
  }
}
