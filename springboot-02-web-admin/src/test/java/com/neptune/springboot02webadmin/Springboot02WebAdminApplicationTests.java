package com.neptune.springboot02webadmin;

import com.neptune.springboot02webadmin.bean.User;
import com.neptune.springboot02webadmin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@MapperScan("com.neptune.springboot02webadmin.mapper")
@Slf4j
@SpringBootTest
class Springboot02WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Long count = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from student");
        log.info("当前记录数：{}", count);
        maps.forEach(System.out::println);
        System.out.println("dataSource.getClass() = " + dataSource.getClass());
    }

    @Test
    void testUser(){
        User user = userMapper.selectById(1);
        log.info(user.toString());
    }

    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        //设置值到redis
        operations.set("hello2","world2");

        //从redis获取数据
        String hello = operations.get("hello2");
        System.out.println(hello);
    }

}
