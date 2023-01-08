package com.neptune.springboot.config;

import com.neptune.springboot.bean.Car;
import com.neptune.springboot.bean.Pet;
import com.neptune.springboot.bean.User;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的对象
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法(默认为true)
 *      Full(proxyBeanMethods = true)【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式（默认）。其他默认设为Lite模式
 *      Full启动慢，Lite启动快
 * 4、@Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置文件
 *
 */
//@ConditionalOnBean(name="tomcat")//容器中有tomcat组件时注入整个配置类（包括类中的组件）
@ConditionalOnMissingBean(name="tomcat")//容器中没有tomcat组件时注入整个配置类（包括类中的组件）
@Import({User.class, StaticLoggerBinder.class})
@Configuration(proxyBeanMethods=true)//告诉Spring boot这是一个配置类
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
//1、开启Car配置绑定功能
//2、把这个Car这个组件自动注册到容器中
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例
     * @return
     */
//    @ConditionalOnBean(name="tomcat")//容器中有tomcat组件时注入user01
    @Bean //给容器添加组件，以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        User user = new User("zhangsan", 32);
        user.setPet(tom());
        return user;
    }

    @Bean("tomcat")//可自定义组件id
    public Pet tom(){
        return new Pet("tom");
    }

    @Bean
    public Pet tom22(){
        return new Pet("tom");
    }
}
