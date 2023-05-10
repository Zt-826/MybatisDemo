package com.example.mybatisdemo.aop;

import com.example.mybatisdemo.context.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("@within(com.example.mybatisdemo.aop.Mysql) || @annotation(com.example.mybatisdemo.aop.Mysql)")
    public void mysqlPointcut() {

    }

    @Pointcut("@within(com.example.mybatisdemo.aop.Postgresql) || @annotation(com.example.mybatisdemo.aop.Postgresql)")
    public void postgresqlPointcut() {

    }

    @Before("mysqlPointcut()")
    public void mysql() {
        DataSourceContextHolder.mysql();
    }

    @Before("postgresqlPointcut()")
    public void postgresql() {
        DataSourceContextHolder.postgresql();
    }
}
