package com.fsmeeting.guava.cache;

import com.fsmeeting.model.User;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 17:45 2017/6/9
 */
@Component
public class UserIdCacheService extends AbstractCacheService<Long, User> {

    @Override
    protected User loadData(Long id) {
        return loadUserFromDB(id);
    }

    /**
     * 从DB加载数据
     *
     * @param id
     * @return
     */
    private User loadUserFromDB(Long id) {
        User user = new User();
        Random random = new Random();
        user.setId(id);
        user.setAge(random.nextInt(120));
        user.setName(new String[]{"sky", "fly", "remind", ""}[random.nextInt(4)]);
        return user;
    }
}
