package com.wang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect  //AOP类 面向切面(特定方法)编程
@Order(1) //数字越小 before越先执行 after越后执行
public class MyAspect3 {

    @Before("execution(* com.wang.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        log.info("before...3");
    }

    @After("execution(* com.wang.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        log.info("after...3");
    }
}
