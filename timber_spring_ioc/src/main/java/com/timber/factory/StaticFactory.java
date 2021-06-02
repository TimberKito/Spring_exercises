package com.timber.factory;

import com.timber.dao.UserDao;
import com.timber.dao.impl.UserDaoImpl;

public class StaticFactory{

    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }

}
