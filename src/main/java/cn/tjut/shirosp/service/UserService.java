package cn.tjut.shirosp.service;

import cn.tjut.shirosp.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author a0000
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-12-12 20:20:23
*/
public interface UserService extends IService<User> {
    User getUserInfoByName(String name);
}
