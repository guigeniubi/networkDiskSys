package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.sys.entity.File;
import com.lantu.sys.mapper.FileMapper;
import com.lantu.sys.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {


    @Resource
    private FileMapper FileMapper;
//    @Override
//    @Transactional
//    public  void addRole(Role role){
//        //写入角色表
//        this.baseMapper.insert(role);
//        //写入角色菜单表
//        if(null!=role.getMenuIdList()){
//            for(Integer menuId:role.getMenuIdList()){
//                roleMenuMapper.insert(new RoleMenu(null,role.getRoleId(),menuId));
//            }
//        }
//    }
//
//    @Override
//    public Role getRoleById(Integer id) {
//        Role role = this.baseMapper.selectById(id);
//        List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleId(id);
//        role.setMenuIdList(menuIdList);
//        return role;
//    }
//
//    @Override
//    @Transactional
//    public void upDateRole(Role role) {
//        //修改角色表
//        this.baseMapper.updateById(role);
//        //删除原有权限
//        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RoleMenu::getRoleId,role.getRoleId());
//        roleMenuMapper.delete(wrapper);
//        //新增权限
//        if(null!=role.getMenuIdList()){
//            for(Integer menuId:role.getMenuIdList()){
//                roleMenuMapper.insert(new RoleMenu(null,role.getRoleId(),menuId));
//            }
//        }
//
//
//    }
//
//    @Override
//    @Transactional
//    public void deleteRoleById(Integer id) {
//        this.baseMapper.deleteById(id);
//        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RoleMenu::getRoleId,id);
//        roleMenuMapper.delete(wrapper);
//    }
}
