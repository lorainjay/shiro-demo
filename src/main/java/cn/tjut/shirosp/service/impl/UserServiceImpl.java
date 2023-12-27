package cn.tjut.shirosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tjut.shirosp.domain.User;
import cn.tjut.shirosp.service.UserService;
import cn.tjut.shirosp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author a0000
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-12-12 20:20:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    UserMapper userMapper;
    @Override
    public User getUserInfoByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,name);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}




