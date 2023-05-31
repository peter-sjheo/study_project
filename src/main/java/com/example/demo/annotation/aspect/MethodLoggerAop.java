package com.example.demo.annotation.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MethodLoggerAop {
        
    @Before("@annotation(com.example.demo.annotation.MethodLogger)")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("[" + method.getName() + "] start!");
        
        Object[] args = joinPoint.getArgs();		        
        for(Object obj : args) {
            log.info("[" + method.getName() + "][parameter]: type:%s, value:%s", obj.getClass().getName(), obj.toString());
        }
    }
    
    
    @AfterReturning(value = "@annotation(com.example.demo.annotation.MethodLogger)", returning = "obj")
    public void after(JoinPoint joinPoint, Object obj) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        log.info("[" + method.getName() + "][return]: type:%s, value:%s", obj.getClass().getName(), obj.toString());
        log.info("[" + method.getName() + "] end!");        
    }    
}
