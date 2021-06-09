# AOP相关概念

> Spring的AOP实现底层就是对上面的动态代理的代码进行了封装，封装后我们只需要对需要关注的部分进行代码编写，并通过配置的方式完成指定目标的方法增强。

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623042725264.png)
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623043852641.png)
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623043982204.png)
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623044331906.png)

- **Target（目标对象）**：代理的目标对象
- **Proxy（代理）**：一个类被AOP织入增强后产生的结果代理类
- **Joinpoint（连接点）**：在程序执行过程中的某个阶段点，指可以被增强的方法
- **Pointcut（切入点）**：切面与程序流程的交叉点，即那些需要处理的连接点，程序运行中被增强的方法
- **Advice（通知/增强）**：所谓通知是指拦截到Joinpoint之后所要做的事情
- **Aspect（切面）**：封装的用于横向插入系统功能（如事务、日志等）的类（描述切入点与通知的关系）
- **Weaving（织入）**：将切面代码插入到目标对象上，从而生成代理对象的**过程**
- **Introduction（引入/引介）**：对原始对象无中生有的添加成员变量或者成员方法

# 代码实现案例
## 项目结构
![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623049417459.png)

## 目标对象

```java
public class UserServiceImpl implements UserService{
    public void save(){
        //0.抽取共性功能
        //System.out.println("共性功能...");
        System.out.println("user service running ...");
    }
}
```

## 制作通知类

```java
//1.制作通知类，在类中定义一个方法用于完成共性功能
public class AOPAdvice{
    public void function(){
        System.out.println("共性功能...");
    }
}
```

## 配置XML

```xml
<!-- 3.开启AOP命名空间 -->
<bean id="userService" class="com.UserServiceImpl"/>
<!-- 2.配置共性功能为Spring控制的资源 -->
<bean id="myAdvice" class="com.AOPAdvice"/>
<!-- 4.配置AOP -->
<aop:config>
    <!-- 5.配置切入点 -->
    <aop:pointcut id="pt" expression="execution(* *..*(..))"/>
    <!-- 6.配置切面（切入点与通知的关系） -->
    <aop:aspect ref="myAdvice">
        <!-- 7.配置具体的切入点对应通知中那个操作方法 -->
        <aop:before method="function" pointcut-ref="pt"/>
    </aop:aspect>
</aop:config>
```

## 运行测试

```java
public class UserController{
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
    }
}
```

## 运行结果

![](https://timber.oss-cn-chengdu.aliyuncs.com/img/utool_up/1623050041870.png)