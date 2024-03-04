package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Users;
import com.lantu.sys.mapper.UsersMapper;
import com.lantu.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuange
 * @since 2024-03-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
