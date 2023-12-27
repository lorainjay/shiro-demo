package cn.tjut.shirosp.mapper;

import cn.tjut.shirosp.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

import static sun.net.ftp.FtpDirEntry.Permission.USER;

/**
* @author a0000
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-12-12 20:20:23
* @Entity generator.domain.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {
}




