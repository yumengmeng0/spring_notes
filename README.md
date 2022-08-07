# Spring Notes

# 一、Spring概述

    * Spring概念介绍
    * IOC
    * DBUtils
    * Spring注解开发
    * Spring整合Junit

## 1.1 什么是Spring


    Spring是分成的Java SE/EE应用的full-stack（全栈式）轻量级开源框架。
    
    full-stack（全栈式）：对各种主流技术和框架进行整合，同时对三层框架都提供解决方案
    轻量级：轻量级和重量级划分依据是使用了多少服务，启动时需要加载的资源多少以及耦合度等
    
    表现层：SpringMVC
    持久层：Spring JDBC Template
    业务层；事务管理
    
    Spring两大核心：
        IOC（Inverse Of Control）：控制反转，把对象的创建权交给Spring
        AOP（Aspect Oriented Programming）：面向切面编程，在不修改源码的情况下，对方法进行增强（动态代理）



## 1.2 Spring发展历程

## 1.3 Spring优势

    1. 方便解耦，简化开发
        Spring就是一个容器，可以将所有对象创建和关系维护交给Spring管理。
        耦合度：对象之间的关系。当一个模块（对象）更改时也需要更改其他模块（对象），这就是耦合
        耦合度高会使代码维护成本增加，要尽量解耦。
        解耦：降低程序间的依赖
            体现：编译器不依赖，运行期依赖
            解耦思路：配置文件 + 反射    
    2. AOP编程支持
        方便实现程序进行权限拦截，运行监控
    3. 声明式事务支持
        通过配置完成事务管理，无需手动编程
    4. 方便测试，降低JavaEE API的使用
        Junit4支持，使用注解测试
    5. 方便集成各种框架
    
## 1.4 Spring体系结构


# IOC

## 2.1 概述
“控制反转”是一种设计思想，目的是指导我们设计出更加松耦合的程序。  
控制：Java中指对象的控制权（创建、销毁）  
反转：对象的控制权原来*由的开发者在类中手动控制*反转到*由Spring容器控制*  

## 2.2 自定义IOC


# 四、Spring相关API
## 4.1 API继承体系
## 4.2 BeanFactory
    BeanFactory是IOC容器的核心接口，定义了IOC的基本功能。
    特点：在第一次调用getBean时，创建对象实例
         
    BeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));

## 4.3 ApplicationContext
    代表应用上下文，获得Spring中容器IOC容器的Bean对象
    特点：在Spring启动时，加载并创建所有对象的实例
    
常用实现类

    ClassPathXmlApplicationContext
    FileSystemXmlApplicationContext
    AnnotationConfigApplicationContext


# 五、Spring配置文件
## 5.1 Bean标签配置
    
    <bean id="" class="" scope=""></bean>
    默认情况下调用无参构造，没有无参构造函数不能创建成功

scope属性是对象的作用范围

| scope | 说明 |
| --- | --- |
| singleton | 默认，单例 |
| prototype | 多例 |
| request | WEB项目中，Spring创建一个Bean对象，将对象存入request域中 |
| session | |
| global session | WEB项目中，应用在Portlet环境，若没有则相当于session |


| - | singleton | prototype |
| --- | --- | --- |
| 实例化时机 | Spring核心文件被加载| getBean() |
| Bean的生命周期| 对象创建：应用加载，创建容器时<br>对象运行：只要容器在，对象一直活着<br>对象销毁：应用卸载销毁容器时|对象创建：使用对象时<br>对象运行：对象使用中<br>对象销毁：长时间不用，被gc回收|
        
## 5.4 Bean实例化的3种方式
* 无参构造方法
* 工厂静态方法
* 工厂普通方法

### 5.4.1 无参构造方法

### 5.4.2 工厂静态方法
    应用场景： 依赖jar包中工厂静态方法创建的对象，将对象的创建权交给Spring的IOC容器。
### 5.4.3 工厂普通方法
    应用场景： 依赖jar包中工厂普通方法创建的对象，将对象的创建权交给Spring的IOC容器。

## 5.5 Bean依赖注入
**依赖注入（Dependency Injection）**：Spring框架核心IOC的具体实现  

        在编写程序时，通过控制反转，把对象创建交给了Spring，但代码中不可能出现没有依赖的情况。
    IOC解耦只是降低它们的依赖关系，但不会消除。例如：业务层调用持久层的方法。
        业务层和持久层的关系，在使用Spring之后，就让Spring维护，通过框架把持久层对象对象传入业务层，而不用我们自己去获取。
        
## 5.6 Bean依赖注入的方式
* 构造方法
* set方法
* p命名空间注入（本质也是set方法）

## 5.6.1 构造方法
## 5.6.2 set方法
## 5.6.3 p命名空间注入


## 5.7 Bean依赖注入数据类型

### 5.7.1 注入普通数据类型
* 普通数据类型
* 引用数据类型
* 集合数据类型


### 5.7.2 注入集合数据类型
* List
* Set
* Array
* Map
* Properties


## 5.8 配置文件模块化
    * 按层拆分（dao、service）
    * 按业务模块拆分（user、product）

配置文件模块化：
1. 并列多个配置文件
```java

ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml", "bean2.xml", ...);

```
2. 主从配置文件 
 ```xml
    <import resource="classpath:application-xxx.xml"></import> 
 ```
    注意：
        同一个xml中不能出现相同名称的bean，否则会报错
        多个xml出现相同名称的bean，不会保存，后加载的会覆盖前加载的bean
        
# 6. DbUtils
DbUtils是Apache的一款用于简化Dao代码的工具类，它底层封装了JDBC技术。  

核心对象：

    QueryRunner queryRunner = new QueryRunner(DataSource dataSource);
    
核心方法：
    
    int update()：执行增删改语句
    T query()：执行查询语句
    ResultSetHandler<T>将数据库返回的对象封装到实体的接口
    
## [6.2 Spring的xml整合DbUtils](./spring_dbutils)

# 七、Spring注解开发

## 7.1 Spring常用注解

### 7.1.1 介绍
Spring常用注解主要替代<bean>的配配置

| 注解 | 说明 |
| --- | --- |
| @Component | 使用在类上，用于实例化Bean |
| @Controller | 使用在Web层类上，用于实例化Bean |
| @Service | 使用在Service层类上，用于实例化Bean |
| @Repository | 使用在dao层类上，用于实例化类 |
| @AutoWired | 使用在字段上，用于根据类型依赖注入 |
| @Qualifier | 结合@Autowired一起使用（不能单独使用），根据名称进行依赖注入 |
| @Resource | 相当于@AutoWired+@Qualifier，按照名称进行注入 |
| @Value | 注入普通属性 |
| @Scope | 标注Bean的作用范围 |
| @PostConstruct | 使用在方法上，标注该方法是Bean的初始方法 |
| @PreDestroy | 使用在方法上，标注该方法是Bean的销毁方法 |

    @Component、@Controller、@Service、@Repository作用相同，相当于配置<bean>，生成实例对象，存到IOC容器
    @Autowired、@Qualifier、@Resource、@Value相当于配置<property>，要进行依赖注入
    
jdk11之后完全移除了javax扩展，需要引入才能使用@Resource
```xml
        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>javax.annotation-api</artifactId>
            <version>1.3.2</version>
        </dependency>
```

注意：使用注解开发，需要在applicationContext.xml中配置组件扫描，指定哪个包及其子包下的Bean进行扫描，
以便识别使用注解配置的类、字段和方法。

```xml
<context:component-scan base-package="package-name"></context:component-scan>
```      

## 7.3 Spring新注解
使用上面注解还不能全部替代xml配置文件，需要使用注解替代的的配置：
    * 非自定义的Bean配置  
    * 加载properties文件的配置：\<context:properties-placeholder>  
    * 组件扫描的配置：\<context:component-scan>
    * 引入其他文件：<import>
   

| 注解 | 说明 |
| --- | --- |
| @Configuration | 指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解 |
| @Bean | 使用在方法上，标注该方法的返回值存储到Spring容器 |
| @PropertySource | 加载properties文件中的配置 |
| @ComponentScan | 指定Spring在初始化容器时要扫描的包 |
| @Import | 导入其他配置类 |

# 八、Spring整合Junit

    1.导入Spring继承Junit左表
    2.使用@RunWith注解替换原理的运行器
    3.使用@ContextConfiguration指定配置文件类
    4.使用@Autowired注入要测试的对象
    5.创建测试方法 



