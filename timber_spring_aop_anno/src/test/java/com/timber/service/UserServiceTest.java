package com.timber.service;

import com.timber.config.SpringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest{
    @Autowired
    private UserService userService;

    @Test
    public void testUpdate(){
        int ret = userService.update(996, 888);
        Assert.assertEquals(200,ret);
    }

}
