# AOP

    转账案例
    Proxy优化转账案例
    初识AOP
    基于XML的AOP开发
    基于注解的AOP开发
    AOP优化转账案例
    
# 一、转账案例
 
## 3.2 传统事务
    1.编写线程绑定工具类
    2.编写事务管理器
    3.修改service层代码
    4.修改到层代码
    
### 问题分析
    通过对业务层改造，可以实现事务控制，由于添加事务控制，产生了一个新的问题：
    业务方法变得臃肿，充斥很多重复代码，并且业务和事务控制方法耦合，违背了面向对象的思想。
    
# 二、Proxy优化转账案例
将业务代码和事务代码进行拆分，通过动态代理的方式，对业务方法进行事务的增强。这样就不会对业务层产生影响，解决耦合问题。

## 常用的动态代理计技术 
[JDK代理](./spring_transfer/src/main/java/org/example/proxy/JDKProxyFactory.java)：

        基于接口的动态代理技术，利用拦截器（必须实现invocationHandler）加上反射机制生成一个代理接口的匿名类，
    在调用具体方法前调用InvokeHandler来处理，从而实现方法增强（只能增强接口的方法）。

[CGLIB代理](./spring_transfer/src/main/java/org/example/proxy/CglibProxyFactory.java)：
   
        基于父类的动态代理技术，动态生成一个要代理的子类，子类要重写代理类所有非final的方法。
    在子类中采用方法拦截技术拦截所有父类订单的调用，顺势织入横切逻辑，对订单进行增强。
    
# 三、初识AOP
 
    * Target（目标对象）：代理的目标对象（被代理类） AccountService
    * Proxy（代理）：生成的代理对象 Proxy.newInstance()
    * JointPoint（连接点）：可以被拦截增强的方法
    * PointCut（切入点）：要对哪些JoinPoint进行拦截定义（真正被增强的方法） transfer()
    * Advice（通知）：增强的业务逻辑。分类：前置通知、后置通知、异常通知、最终通知、环绕通知
        环绕通知：通过代码的方式手动控制通知的类型
    * 切面（Aspect）：是切入点和通知（引介）的结合
    * Weaving（织入）：把增强应用到目标对象创建新的代理对象的过程。
        Spring采用动态代理织入，AspectJ采用编译期织入和类装载织入。


 
## 3.4 AOP开发明确失效

### 3.4.3 底层实现原理
    在Spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理方式
    * 当Bean实现接口时，会用JDK代理模式
    * 当Bean没有实现接口，用cglib实现（可以强制使用cglib）

# 四、基于XML的AOP开发

## 4.2 XML配置AOP详解
### 4.2.1 切点表达式
[AOP配置](spring_aop_xml/src/main/resources/applicationContext.xml)

### 4.2.2 通知类型
通知配置语法：
```xml
<aop:通知类型 method="通知值方法名" pointcut="切点表达式"></aop:通知类型>
```

| 名称 | 标签 | 说明 |
| --- | --- | --- |
| 前置通知 | \<aop:before> | 指定增强的方法在切入点方法之前执行 |
| 后置通知 | \<aop:afterReturning> | 指定增强方法在切入点方法之后执行 |
| 异常通知 | \<aop:afterThrowing> | 指定增强方法出现异常后执行 |
| 最终通知 | \<aop:after> | 无论切入点方法执行时是否有异常，都会执行 |
| 环绕通知 | \<aop:around> | 开发者可以手动控制增强代码在什么时候执行 |

* afterReturning和afterThrowing只有一个执行
* 通常情况下，环绕通知都是独立使用

# 五、基于注解的AOP开发

    1.创建Java项目，导入AOP相关坐标
    2.创建目标接口和目标实现类
    3.创建通知类（定义通知）
    4.将目标类和通知类对象创建权交给Spring
    5.在通知类中使用注解配置织入关系，升级为切面类
    6.在配置文件中开启组件扫描和AOP的自动代理
    7.编写测试代码   
    



# 通知的执行顺序

| | xml | 注解 |
| --- | --- | --- |
| 无异常 |  before->afterReturning->after | @before->@afterReturning->@after |
| 有异常 | before->afterThrowing->after | @before->@afterThrowing->@after |

* 注解的执行顺序：@before->@after->@afterReturning的BUG貌似被修复了

# [Exception的传递](./java_exception_demo)

```java
public class A {
    public void functionA(){
        try {
            int i = 1 / 0;
            System.out.println("functionA");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("a Exception");
        }
    }
}


public class B {
    public void functionB() {
        try {
            A a = new A();
            a.functionA(); // a.functionA内部异常，在内部被捕获
            System.out.println("functionB");
        } catch (Exception e) {
            System.out.println("b Exception");
            e.printStackTrace();
        }
    }
}

public class TestException {

    public static void main(String[] args) {
        B b = new B();
        b.functionB(); 
    }
/**
最终打印：
a Exception
functionB
*/

}


```



    
