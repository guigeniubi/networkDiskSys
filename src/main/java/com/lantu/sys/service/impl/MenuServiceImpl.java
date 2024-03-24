package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.mapper.MenuMapper;
import com.lantu.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getAllMenu() {
       //一级菜单
        LambdaQueryWrapper <Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0);
        List<Menu>menuList=this.list(wrapper);
        //填充子菜单
        setMenuChildren(menuList);
        return  menuList;
    }



    private void setMenuChildren(List<Menu> menuList) {
        if(menuList !=null){
            for (Menu menu : menuList) {
                LambdaQueryWrapper <Menu> subwrapper = new LambdaQueryWrapper<>();
                subwrapper.eq(Menu::getParentId,menu.getMenuId());
                List<Menu>subMenuList=this.list(subwrapper);
                menu.setChildren(subMenuList);
                //递归
                setMenuChildren(subMenuList);
            }
        }
    }
    @Override
    public List<Menu> getMenuListByUserId(Integer userId) {

        //一级菜单
        List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, 0);
        //字菜单
        setMenuChildrenByUserId(userId, menuList);

        return menuList;
    }

    private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
        if(menuList !=null){
            for (Menu menu : menuList) {
                List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());
                menu.setChildren(subMenuList);
                //递归
                setMenuChildrenByUserId(userId,subMenuList);
            }
        }
    }
}
