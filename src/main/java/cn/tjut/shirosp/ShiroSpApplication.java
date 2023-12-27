package cn.tjut.shirosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tjut.shirosp.mapper")
public class ShiroSpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroSpApplication.class, args);
    }

}
