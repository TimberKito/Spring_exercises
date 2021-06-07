package com.timber.aop;

//1.制作通知类，在类中定义一个方法用于完成共性功能
public class AOPAdvice{
    public void function(){
        System.out.println("共性功能...");
    }

    public void before(){
        System.out.println("before ...");
    }
}
