package com.lantu.sys.service;

import com.lantu.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Integer id);

    void upDateRole(Role role);

    void deleteRoleById(Integer id);
}
