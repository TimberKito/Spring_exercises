package com.timber.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(5)
public class AOPAdvice2{

//    @Pointcut("execution(* *..*(..))")
//    public void pt(){
//    }



//    @After("AOPPointcut.pt1()")
//    public void after(){
//        System.out.println("后置...after ...");
//    }
//
//    @AfterReturning(value = "AOPPointcut.pt1()", returning = "ret")
//    public void afterReturing(Object ret){
//        System.out.println("返回后...afterReturing ..." + ret);
//    }
//
//
//    @AfterThrowing(value = "AOPPointcut.pt1()", throwing = "t")
//    public void afterThrowing(Throwable t){
//        System.out.println("抛出异常后...afterThrowing ..." + t.getMessage());
//    }
//
//    @Around("AOPPointcut.pt1()")
//    public Object around(ProceedingJoinPoint pjp){
//        System.out.println("环绕前...around ... before");
//        //对原始方法的调用
//        Object ret = null;
//        try {
//            ret = pjp.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("环绕抛原方法异常...around... exception..." + throwable.getMessage());
//        }
//        System.out.println("环绕后...around ... after" + ret);
//        return ret;
//    }

//    @Before("AOPPointcut.pt1()")
//    public void aop001log(){
//        System.out.println("前置...before2 ..." );
//    }

}
