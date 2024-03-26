package com.lantu.sys.service;

import com.lantu.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    User getUserByToken(String token);

    void logout(String token);

    void addUser(User user);

    User getUserById(Integer id);

    void upDateUser(User user);

    void deleteUserById(Integer id);

    void register(User user);
}
