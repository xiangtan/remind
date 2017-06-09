package com.fsmeeting.guava.cache;

import com.fsmeeting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 16:41 2017/6/9
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserIdCacheService userIdCacheService;

    @Override
    public User getById(Long userId) {
        return userIdCacheService.getCache(userId);
    }

}
