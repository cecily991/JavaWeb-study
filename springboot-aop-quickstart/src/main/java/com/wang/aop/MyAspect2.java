package com.wang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
@Order(2)
public class MyAspect2 {

    @Before("execution(* com.wang.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        log.info("before...2");
    }

    @After("execution(* com.wang.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        log.info("after...2");
    }
}
