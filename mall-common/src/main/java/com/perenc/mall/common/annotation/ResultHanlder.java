package com.perenc.mall.common.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultHanlder {

    int code() default 0;

    String msg() default "";
}
