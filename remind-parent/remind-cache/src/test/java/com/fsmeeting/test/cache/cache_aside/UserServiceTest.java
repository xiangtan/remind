package com.fsmeeting.test.cache.cache_aside;


import com.fsmeeting.cache.cache_aside.AppConfig;
import com.fsmeeting.cache.cache_aside.UserService;
import com.fsmeeting.model.User;
import junit.framework.Assert;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

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
        Assert.assertNotNull(userCache.get(id).get());
        userService.findById(id);
    }
}