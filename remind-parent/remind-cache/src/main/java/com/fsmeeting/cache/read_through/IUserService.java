package com.fsmeeting.cache.read_through;

import com.fsmeeting.model.User;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 16:43 2017/6/9
 */
public interface IUserService {

    User getById(Long userId);
}
