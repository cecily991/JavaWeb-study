package com.wang.aop;

import com.alibaba.fastjson2.JSONObject;
import com.wang.mapper.OperateLogMapper;
import com.wang.pojo.OperateLog;
import com.wang.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.wang.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        //执行原始方法
        Object result = joinPoint.proceed();

        //记录操作日志
        OperateLog operateLog = new OperateLog();
        //操作人ID
        operateLog.setOperateUser((Integer) JwtUtils.parseJWT(httpServletRequest.getHeader("token")).get("id"));
        //操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        //操作类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        //操作方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());
        //操作方法参数
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        //操作方法返回值
        operateLog.setReturnValue(JSONObject.toJSONString(result));
        //操作耗时
        long end = System.currentTimeMillis();
        operateLog.setCostTime(end - begin);

        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}",operateLog);

        return result;
    }
}
