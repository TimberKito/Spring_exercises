# AOP通知类型与XML配置

## AOP的通知类型共5种

- **前置通知**:原始方法执行前执行,如果通知中抛出异常,阻止原始方法运行
  应用:数据校验
- **后置通知**:原始方法执行后执行,无论原始方法中是否出现异常,都将执行通知
  应用:现场清理
- **返回后通知**:原始方法正常执行完毕并返回结果后执行,如果原始方法中抛出异常,无法执行
  应用:返回值相关数据处理
- **抛出异常后通知**:原始方法抛出异常后执行,如果原始方法没有抛出异常,无法执行
  应用:对原始方法中出现的异常信息进行处理
- **环绕通知**:在原始方法执行前后均有对应执行执行,还可以阻止原始方法的执行
  应用:十分强大,可以做任何事情



### AOPAdvice通知类

```java
package com.timber.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//1.制作通知类，在类中定义一个方法用于完成共性功能
public class AOPAdvice{
    public void function(){
        System.out.println("共性功能...");
    }

    public void before(){
        System.out.println("before ...");
    }

    public void after(){
        System.out.println("after ...");
    }

    public void afterReturing(){
        System.out.println("afterReturing ...");
    }

    public void afterThrowing(){
        System.out.println("afterThrowing ...");
    }

    public void around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("around ... before");
        //对原始方法的调用
        pjp.proceed();
        System.out.println("around ... after");
    }
}
```



### 前置通知

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:before method="before" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134397657.png)

### 后置通知

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:after method="after" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134460339.png)

### 返回后通知

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:after-returning method="afterReturing" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134489705.png)

> **<u>抛出异常后不执行</u>**
>
> ![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134530388.png)

### 抛出异常后通知

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:after-throwing method="afterThrowing" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134577902.png)

### 环绕通知

```xml
<aop:config>
    <aop:pointcut id="pt" expression="execution(* *(..))"/>
    <aop:aspect ref="myAdvice">
        <aop:around method="around" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```

>
> 环绕通知是在原始方法的前后添加功能，在环绕通知中，存在对原始方法的显示调用

```java
public Object around(ProceedingJoinPoint pjp) throws Throwable{
    System.out.println("around ... before");
    //对原始方法的调用
    Object ret = pjp.proceed();
    System.out.println("around ... after");
    return ret;
}
```
- 方法须设定 Object类型的返回值,否则会拦截原始方法的返回。如果原始方法返回值类型为
  void,通知方法也可以设定返回值类型为void,最终返回null。
- 方法需在**<u>第一个参数位置设定ProceedingJoinPoint对象</u>**,通过该对象调用 proceed()方法,实
  现对原始方法的调用。如省略该参数,原始方法将无法执行。
- 使用 proceed()方法调用原始方法时,因无法预知原始方法运行过程中是否会出现异常,强制抛
  出 Throwable对象,封装原始方法中可能出现的异常信息。



## 基于XML配置的通知顺序

> **<u>当同一个切入点配置了多个通知时,通知会存在运行的先后顺序,该顺序以通知配置的顺序为准。</u>**

```xml
<aop:before method="before3" pointcut-ref="pt"/>
<aop:before method="before" pointcut-ref="pt"/>
<aop:before method="before2" pointcut-ref="pt"/>
```

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623134290813.png)



## 通知获取参数数据

### 1.设定通知方法第一个参数为JoinPoint，通过该对象调用getArgs（）方法，获取原始方法的参数数组

```java
public void before(JoinPoint jp){
    Object[] args = jp.getArgs();
    System.out.println("before ..." + args[0]);
}
```

### 2.设定切入点表达式为通知方法传递参数（改变通知变量名的定义顺序）

#### 原始方法

```java
public class UserServiceImpl implements UserService{
    public void save(int i, int j){
        System.out.println("user service running ..." + i + "," + j);
    }
}
```

#### XML配置

```xml
    <aop:config>
        <aop:pointcut id="pt" expression="execution(* *(..))"/>
        <aop:aspect ref="myAdvice">
           <aop:after method="after"
                       arg-names="y,x"
                       pointcut="execution(* *(int,int)) &amp;&amp; args(x,y) "/>
        </aop:aspect>
    </aop:config>
```

> arg-names="y,x" 改变参数传入通知的顺序

#### 通知类

```java
public void after(int x , int y){
    System.out.println("after ..."+ x+","+y);
}
```

####　输出

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623137017485.png)



## 通知获取返回值数据

### 1.适用于返回后通知（ after-returning ）

#### 原始方法

```java
public int update(){
    System.out.println("user service update ...");
    return 200;
}
```

####　XML配置

> returning="ret" 配置属性变量名

```xml
    <aop:config>
        <aop:pointcut id="pt" expression="execution(* *(..))"/>
        <aop:aspect ref="myAdvice">
			<aop:after-returning method="afterReturing"-->
                                pointcut-ref="pt" returning="ret"/>
        </aop:aspect>
    </aop:config>
```

####　通知类

```java
public void afterReturing(Object ret){
    System.out.println("afterReturing ..." + ret);
}
```

#### 输出

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623138718506.png)



### 2.适用于环绕通知（around） 

#### 原始方法

```java
public int update(){
    System.out.println("user service update ...");
    return 200;
}
```

#### XML配置

```xml
    <aop:config>
        <aop:pointcut id="pt" expression="execution(* *(..))"/>
        <aop:aspect ref="myAdvice">
            <aop:around method="around" pointcut-ref="pt"/>
        </aop:aspect>
    </aop:config>
```

#### 通知类

```java
public Object around(ProceedingJoinPoint pjp) throws Throwable{
    System.out.println("around ... before");
    //对原始方法的调用
    Object ret = pjp.proceed();
    System.out.println("around ... after" + ret);
    return ret;
}
```

#### 输出

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623138618627.png)



## 通知获取异常数据

### 1.适用于返回后通知（after-throwing）

> **<u>设定异常对象变量名</u>**

#### 原始方法

```java
public void delete(){
    System.out.println("user service delete ...");
    int i = 1/0; //抛异常
}
```

#### XML配置

```xml
        <aop:config>
            <aop:pointcut id="pt" expression="execution(* *(..))"/>
            <aop:aspect ref="myAdvice">
                <aop:after-throwing method="afterThrowing"
                                    pointcut-ref="pt" throwing="t"/>
            </aop:aspect>
        </aop:config>
```

> throwing="t" 设定异常对象名传入通知类

#### 通知类

```java
public void afterThrowing(Throwable t){
    System.out.println("afterThrowing ..." + t.getMessage());
}
```

#### 输出

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623139150479.png)



### 2.适用于环绕通知（around）

> **<u>在通知类中的方法中调用原始方法捕获异常</u>**

####　原始方法

```java
public void delete(){
    System.out.println("user service delete ...");
    int i = 1/0; //抛异常
}
```

#### XML配置

```xml
        <aop:config>
            <aop:pointcut id="pt" expression="execution(* *(..))"/>
            <aop:aspect ref="myAdvice">
                <aop:around method="around" pointcut-ref="pt"/>
            </aop:aspect>
        </aop:config>
```

#### 通知类

```java
public Object around(ProceedingJoinPoint pjp){
    System.out.println("around ... before");
    //对原始方法捕获异常并且处理
    Object ret = null;
    try {
        ret = pjp.proceed();
    } catch (Throwable throwable) {
        System.out.println("around... exception..." + throwable.getMessage());
    }
    System.out.println("around ... after" + ret);
    return ret;
}
```

#### 输出

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623139716755.png)