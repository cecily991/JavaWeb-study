package com.itheima.config;

import com.itheima.service.DeptService;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //配置类
public class CommonConfig {

    //声明第三方Bean
    @Bean(name = "reader") //将当前方法的返回值对象交给IOC容器管理，成为IOC容器bean
          //可以通过@Bean的name/value属性来指定bean对象的名称。若未指定，默认为方法名
    public SAXReader saxReader(DeptService deptService){
        System.out.println("自动装配"+deptService);
        return new SAXReader();
    }

}
