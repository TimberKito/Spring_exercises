# AOP配置（注解）

## 基本的注解配置

### @Aspect

- 位置：类定义上方
- 作用：设置当前类为切面类

### @Pointcut

- 位置：方法定义上方
- 作用：使用**<u>当前的方法名</u>**作为切入点引用名称

```java
@Pointcut("execution(* *..*(..))")
public void pt(){ }
```

- 可以写在独立类中

```java
public class AOPPointcut{
    @Pointcut("execution(* *..*(..))")
    public void pt1(){
    }
}
```

### @Before

- 位置：方法定义上方
- 作用：标注该方法作为前置通知

```java
@Before("AOPPointcut.pt1()")
public void before(JoinPoint jp){
    Object[] args = jp.getArgs();
    System.out.println("前置...before ..." + args[0] + args[1]);
}
```

### @After

- 位置：方法定义上方
- 作用：标注该方法作为后置通知

```java
@After("AOPPointcut.pt1()")
public void after(){
    System.out.println("后置...after ...");
}
```

### @AfterReturning

- 位置：方法定义上方
- 作用：标注该方法作为返回后通知

```java
@AfterReturning("AOPPointcut.pt1()")
public void afterReturing(){
    System.out.println("返回后...afterReturing ...");
}
```

### @AfterThrowing

- 位置：方法定义上方
- 作用：标注该方法作为异常后通知

```java
@AfterThrowing("AOPPointcut.pt1()")
public void afterThrowing(){
    System.out.println("抛出异常后...afterThrowing ...");
}
```

### @Around

- 位置：方法定义上方
- 作用：标注该方法作为环绕通知

```java
@Around("AOPPointcut.pt1()")
public Object around(ProceedingJoinPoint pjp){
    System.out.println("环绕前...around ... before");
    //对原始方法的调用
    Object ret = null;
    try {
        ret = pjp.proceed();
    } catch (Throwable throwable) {
        System.out.println("环绕抛原方法异常...around... exception..." + throwable.getMessage());
    }
    System.out.println("环绕后...around ... after" + ret);
    return ret;
}
```

## 注解后添加参数

### 注解配置：

```java
@AfterReturning(value = "AOPPointcut.pt1()",returning = "ret")
public void afterReturing(Object ret){
    System.out.println("返回后...afterReturing ..." + ret);
}
```

```java
@AfterThrowing(value = "AOPPointcut.pt1()", throwing = "t")
public void afterThrowing(Throwable t){
    System.out.println("抛出异常后...afterThrowing ..." + t.getMessage());
}
```

### XML配置：

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *..*(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:after-returning method="afterReturing" pointcut-ref="pt" returning="ret"/>
        <aop:after-throwing method="afterThrowing" pointcut-ref="pt" throwing="t"/>
    </aop:aspect>
</aop:config>
```

## AOP注解开发通知执行顺序控制

### Spring AOP运行机制

- 同个通知类中，相同通知类型以方法名排序为准
- 不同通知类中，以类名排序为准
- 使用@Order()注解通过变更bean的加载顺序改变通知的加载顺序

### 企业开发经验

> 通知方法名由3部分组成，分别是**前缀、顺序编码、功能描述**
> 前缀为固定字符串，例如 baidu、 timber等，无实际意义
> 顺序编码为6位以内的整数，通常3位即可，不足位补0
> 功能描述为该方法对应的实际通知功能，例如 exception、 strlenCheck、log
> 控制通知执行顺序使用顺序编码控制，使用时做定空间预留
>
> - 003使用,006使用,预留001、002、004、005、007、008
> - 使用时从中段开始使用,方便后期做前置迫加或后置迫加
> - **<u>最终顺序以运行顺序为准,以测试结果为准,不以设定规则为准</u>**

```java
@Before("AOPPointcut.pt1()")
public void aop002log(){
    System.out.println("前置...before2 ..." );
}

@Before("AOPPointcut.pt1()")
public void aop003log(){
    System.out.println("前置...before1 ..." );
}
```

