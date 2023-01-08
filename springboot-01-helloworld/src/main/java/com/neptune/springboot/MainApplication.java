package com.neptune.springboot;

import com.neptune.springboot.bean.Pet;
import com.neptune.springboot.bean.User;
import com.neptune.springboot.config.MyConfig;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主程序类
 *
 * @springBootApplication;这是一个SpringBoot应用
 */

@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.neptune.springboot")
public class MainApplication {

    public static void main(String[] args) {

        //1、返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }

/*
        //3、从容器中获取组件
        Pet tomcat1 = run.getBean("tomcat", Pet.class);
        Pet tomcat2 = run.getBean("tomcat", Pet.class);

        System.out.println("组件："+(tomcat1==tomcat2));//true

        //4、获取配置类
        MyConfig bean = run.getBean(MyConfig.class);
        //@configuration( proxyBeanMethods = true)
        //获取到代理对象com.neptune.springboot.config.MyConfig$$EnhancerBySpringCGLIB$$4b8186f7@60baef24

        //@configuration( proxyBeanMethods = false)
        //获取到对象com.neptune.springboot.config.MyConfig@67207d8a
        System.out.println("配置类："+bean);

        //如果@configuration( proxyBeanMethods = true) 就是代理对象调用方法。
        //SpringBoot总会检查这个组件是否在容器中，保持组件单实例

        //如果@configuration( proxyBeanMethods = false)。
        //每次调用组件的方法都会产生新对象
        User user1 = bean.user01();
        User user2 = bean.user01();
        System.out.println("对组件注册方法调用多次：" + (user1==user2));

        //@configuration( proxyBeanMethods = true)
        User user01 = run.getBean("user01", User.class);
        Pet tomcat = run.getBean("tomcat", Pet.class);
        System.out.println("组件调用方法创建的对象与容器对象："+(user01.getPet()==tomcat));//true

        //5、获取@Import导入的组件

        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println("user = " + s);
        }

        StaticLoggerBinder staticLoggerBinder = run.getBean(StaticLoggerBinder.class);
        System.out.println("staticLoggerBinder = " + staticLoggerBinder);
*/
        boolean tomcat = run.containsBean("tomcat");
        System.out.println("容器中是否存在tomcat组件：" + tomcat);

        boolean user01 = run.containsBean("user01");
        System.out.println("容器中是否存在user01组件：" + user01);

        boolean tom22 = run.containsBean("tom22");
        System.out.println("容器中是否存在tom22组件：" + tom22);

        boolean myConfig = run.containsBean("myConfig");
        System.out.println("容器中是否存在myConfig组件：" + myConfig);

        boolean haha = run.containsBean("haha");
        System.out.println("haha：" + haha);

        boolean hehe = run.containsBean("hehe");
        System.out.println("hehe：" + hehe);

        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount = " + beanDefinitionCount);

    }

}
