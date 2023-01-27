package com.neptune.springboot02webadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.neptune.springboot02webadmin.mapper")
@ServletComponentScan
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class Springboot02WebAdminApplication {

    public static void main(String[] args) {
       SpringApplication.run(Springboot02WebAdminApplication.class, args);
/*        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("please input: ");
            String next = scanner.next();
            boolean matches = next.matches("[^.]+\\.+[\\w\\d]+$");
            System.out.println(matches);
        }*/
    }
}
