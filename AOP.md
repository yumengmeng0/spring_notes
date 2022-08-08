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



    
