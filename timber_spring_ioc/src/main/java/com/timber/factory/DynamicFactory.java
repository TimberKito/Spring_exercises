package com.timber.factory;

import com.timber.dao.UserDao;
import com.timber.dao.impl.UserDaoImpl;

public class DynamicFactory{

    public UserDao getUserDao(){
        return new UserDaoImpl();
    }

}
