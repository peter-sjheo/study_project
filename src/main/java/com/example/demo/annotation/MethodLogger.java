package com.example.demo.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Inherited;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


/**
 * 이 어노테이션을 부여하면 해당 메소드의 시작(파라미터)과 종료(리턴값)를 log로 남깁니다.
 */
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodLogger {
    
}
