package com.allan.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.allan.model.po.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2023-03-09
 */
public interface UserService extends IService<User> {

    User login(User user);

    User register(User user);

}
