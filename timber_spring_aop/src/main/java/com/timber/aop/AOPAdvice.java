package com.timber.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//1.制作通知类，在类中定义一个方法用于完成共性功能
public class AOPAdvice{
    public void function(){
        System.out.println("共性功能...");
    }

    public void before(JoinPoint jp){
        Object[] args = jp.getArgs();
        System.out.println("before ..." + args[0]);
    }

    public void after(int x , int y){
        System.out.println("after ..."+ x+","+y);
    }

    public void afterReturing(Object ret){
        System.out.println("afterReturing ..." + ret);
    }

    public void afterThrowing(Throwable t){
        System.out.println("afterThrowing ..." + t.getMessage());
    }

    public Object around(ProceedingJoinPoint pjp){
        System.out.println("around ... before");
        //对原始方法的调用
        Object ret = null;
        try {
            ret = pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println("around... exception..." + throwable.getMessage());
        }
        System.out.println("around ... after" + ret);
        return ret;
    }


    //    public void before2(){
//        System.out.println("before ...2");
//    }
//    public void before3(){
//        System.out.println("before ...3");
//    }
}
