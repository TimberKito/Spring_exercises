package com.timber.aop;

import org.aspectj.lang.annotation.Pointcut;

public class AOPPointcut{
    @Pointcut("execution(* *..update(..))")
    public void pt1(){
    }
}
