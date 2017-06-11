package com.fsmeeting.test.cache.cache_aside;

import com.fsmeeting.cache.cache_aside.AppConfig;
import com.fsmeeting.cache.cache_aside.UserService2;
import com.fsmeeting.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserService2Test {

    @Autowired
    private UserService2 userService;

    @Autowired
    private CacheManager cacheManager;

    private Cache userCache;

    @Before
    public void setUp() {
        userCache = cacheManager.getCache("user");
    }


    @Test
    public void testCache() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setEmail("test@test.com");
        user.setUsername("test");
        userService.save(user);

        //一定要复制一个 否则cache了(因为同一个JVM测试的)
        User user2 = new User();
        user.setId(id);
        user.setEmail("test2@test2.com");
        user.setUsername("test2");

        userService.conditionUpdate(user2);

        userService.findById(id);
        userService.findByUsername("test2");
    }

}