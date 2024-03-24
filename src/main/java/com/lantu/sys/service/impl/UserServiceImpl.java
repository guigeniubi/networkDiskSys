package com.lantu.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.common.utils.JwtUtil;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.entity.User;
import com.lantu.sys.entity.UserRole;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.mapper.UserRoleMapper;
import com.lantu.sys.service.IMenuService;
import com.lantu.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Autowired
    private IMenuService menuService;
    @Override
    public Map<String, Object> login(User user) {
        //先查询用户名和
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser=this.baseMapper.selectOne(wrapper);
        //结果不为空且密码和传入密码一致，生成token,并存入redis
        if(loginUser!=null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
            //暂时用UUID
//            String key="user:"+ UUID.randomUUID();

            //存入redis
            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            //创建JWT
            String token = jwtUtil.createToken(loginUser);

            //返回数据
            Map<String,Object>data=new HashMap<>();
            data.put("token",token);
            return data;
        }

        return null;
    }
//    @Override
//    public Map<String, Object> login(User user) {
//        //先查询用户名和密码
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUsername,user.getUsername());
//        wrapper.eq(User::getPassword,user.getPassword());
//        User loginUser=this.baseMapper.selectOne(wrapper);
//        //结果不为空，生成token,并存入redis
//        if(loginUser!=null){
//            //暂时用UUID
//            String key="user:"+ UUID.randomUUID();
//            //存入redis
//            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
//            //返回数据
//            Map<String,Object>data=new HashMap<>();
//            data.put("token",key);
//            return data;
//        }
//
//        return null;
//    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据token获取用户信息,return redis
//        Object obj= redisTemplate.opsForValue().get(token);
        User loginuser=null;
        try {
            loginuser = jwtUtil.parseToken(token, User.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(loginuser !=null){
//             User loginuser= JSON.parseObject(JSON.toJSONString(obj),User.class);
             Map<String,Object>data=new HashMap<>();
             data.put("name",loginuser.getUsername());
             data.put("avatar",loginuser.getAvatar());
             //角色
            List<String>roleList=this.baseMapper.getRoleNameByUserId(loginuser.getId());
             data.put("roles",roleList);

             //权限列表
            List<Menu> menuList = menuService.getMenuListByUserId(loginuser.getId());
            data.put("menuList",menuList);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {

//        redisTemplate.delete(token);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        //写入用户表
        this.baseMapper.insert(user);
        //写入用户角色表
        List<Integer> roleIdList=user.getRoleIdList();
        if (roleIdList!=null){
            for(Integer roleId:roleIdList){
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.baseMapper.selectById(id);

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);
        List<Integer> roleIdList= userRoleList.stream()
                            .map(userRole ->{ return  userRole.getRoleId();})
                            .collect(Collectors.toList());
        user.setRoleIdList(roleIdList);
        return user;

    }

    @Override
    @Transactional
    public void upDateUser(User user) {
        //更新用户表
        this.baseMapper.updateById(user);
        //清除原有角色
        LambdaQueryWrapper<UserRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getRoleId,user.getId());
        userRoleMapper.delete(wrapper);
        //设置新的角色
        List<Integer> roleIdList=user.getRoleIdList();
        if (roleIdList!=null){
            for(Integer roleId:roleIdList){
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }

    }

    @Override
    public void deleteUserById(Integer id) {
        this.baseMapper.deleteById(id);
        //清除原有角色
        LambdaQueryWrapper<UserRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getRoleId,id);
        userRoleMapper.delete(wrapper);
    }

    @Override
    public void register(User user) {
        this.baseMapper.insert(user);
        //写入用户角色表
        List<Integer> roleIdList=user.getRoleIdList();
        if (roleIdList!=null){
            for(Integer roleId:roleIdList){
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }
}
