# SpringJdbcTemplate & 声明式事务
* Spring的JDBCTemplate
* Spring的事务
* Spring集成Web环境

# 一、Spring的JdbcTemplate
## 1.1 JdbcTemplate是什么？
JdbcTemplate是Spring框架中提供的一个模版对象，对原始繁琐的JDBC API对象的简单封装

## 核心对象
```java
    JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource dataSource);
```

## 核心方法
```java
    int update(); //增、删、改
    List<T> query(); // 查询多个
    T queryForObject(); // 查询一个
    new BeanPropertyRowMapper<>(); // 实现ORM映射封装    
```

## 1.2 Spring整合JDBCTemplate


# 二、Spring事务
## 2.1 Spring中的事务控制方式
    编程式：直接把事务代码和业务代码耦合到一起，在实际开发中不用。
    声明式：采用配置的方式实现的业务控制，业务代码与事务实现解耦合，使用AOP思想。

## 2.2 编程式事务控制对象
### 2.2.1 PlatformTransactionManager接口
PlatformTransactionManager接口，是Spring的事务管理器，里面提供了常用操作事务的方法。

| 方法 | 说明 |
| --- | --- |
| TransactionStatus getTransaction(Transaction definition) | 获取事务的状态信息 |
| void commit(TransactionStatus status) | 提交事物 | 
| void rollback(TransactionStatus status) | 回滚事物 | 

#### 注意
    PlatformTransactionManager是接口类型，不同的dao层技术有不同的实现类：
        Dao层是JdbcTemplate或mybatis时：DataSourceTransactionManager
        Dao层是Hibernate时：HibernateTransactionManager
        Dao层是JPA时：JpaTransactionManager

### 2.2.2 TransactionDefinition接口
TransactionDefinition接口提供事务的定义信息（事务隔离级别、事务传播行为等等）

| 方法 | 说明 |
| --- | --- |
| int getIsolation() | 获取事务隔离级别 |
| int getPropagationBehavior() | 获取事务的传播行为 |
| int getTimeout() | 获取超时时间 |
| boolean isReadOnly() | 是否只读 |

 (1) 事务隔离级别  
 
 设置隔离级别，可以解决事务并发产生的问题，如脏读、不可重复读和虚读（幻读）。
    
| 隔离级别 | 说明 |
| --- | --- |   
| ISOLATION_DEFAULT | 使用数据库默认级别 |
| ISOLATION_READ_UNCOMMITTED | 读未提交 |
| ISOLATION_READ_COMMITTED | 读已提交（Oracle默认） |
| ISOLATION_REPEATABLE_READ | 可重复发读（MySql默认） |
| ISOLATION_SERIALIZABLE | 串行化 |
    
(2) 事务传播行为
   
事务传播行为指当一个业务方法被另一个业务方法调用时，如何进行事务控制。

| 参数 | 说明 |
| --- | --- |
| REQUIRED | 如果当前没有事务，就新建一个事务。<br>如果已存在一个事务中，加入到这个事务中。<br>默认值。<br>当前*被调用的方法*必须进行事务控制<br>增删改操作 |
| SUPPORTS | 支持当前事务，如果当前没有事务，就以非事务方式执行（没有事务）<br> 查询操作 |
| MANDATORY 强制的 | 使用当事务，如果当前没有事务，抛出异常 |
| REQUERS_NEW | 新建事务，如果当前在事务中，把当前事务挂起 |
| NOT_SUPPORT | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起 |
| NEVER | 以非事务方式运行，如果当前存在事务，抛出异常 |
| NESTED | 如果当前存在事务，则嵌套在事务内执行。如果当前没有事务，则执行REQUIRED类似操作 |

* read-only（是否只读）：建议查询设置为只读
* timeout（超时时间）：默认-1，没有超时限制，单位s

### 2.2.3 TransactionStatus接口
TransactionStatus接口提供事务的运行状态。

| 方法 | 说明 |
| --- | --- |
| boolean isNewTransaction() | 是否是新事务 |
| boolean haveSavePoint() | 是否是回滚点 |
| boolean isRollbackOnly() | 事务是否回滚 |
| boolean isCompleted() | 事务是否完成 |


### 2.2.4 实现代码

```java
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    /**
     * 转账
     *
     * @param outUser 转出账户id
     * @param inUser  转入账户id
     * @param money
     */
    @Override
    public void transfer(Integer outUser, Integer inUser, Double money) {
        // 事务定义对象
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // 设置为只读，false支持事务
        transactionDefinition.setReadOnly(false);
        // 设置隔离级别 
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        // 设置事务传播行为
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 配置事务管理器
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);


        try {
            accountDao.out(outUser, money);
            accountDao.in(inUser, money);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        } 
    }
}
```

### 2.2.5 小结
    Spring的事务管理主要通过三个API实现：
    PlatformTransactionManager负责事务的管理，它是个接口，其子类负责具体工作
    TransactionDefinition定义了事务的一些相关参数
    TransactionStatus代表事务运行的一个实时状态
    
三者关系：***事务管理器***通过读取***事务定义参数***进行事务管理，然后产生一系列的***事务状态***


## 2.3 基于XML的声明式事务控制
在Spring配置文件中声明式处理事务代替代码式处理事务。底层采用AOP思想来实现。

声明式事务控制明确实现：
* 核心业务代码（目标对象）
* 事务增强代码（Spring已提供事务管理器）
* 切面配置

### [事务参数配置](spring_transfer_tx/src/main/resources/applicationContext.xml)



## 2.4 [基于注解的声明式事务控制](spring_transfer_tx_annotation)


## 小结
* 平台事务管理器配置（xml、注解）
* 事务通知配置（@Transactional注解配置）
* 事务注解驱动配置（\<tx:annotation-driven/>、@EnableTransactionManagement）


# 三、Spring集成Web环境
## 3.1 ApplicationContext应用上下文获取
        应用上下文对象是通过 new ClasspathXmlApplicationContext(spring配置文件) 方式获取的，但
    是每次从容器中获得Bean时都要编写 new ClasspathXmlApplicationContext(spring配置文件)，这样的弊端是
    配置文件加载多次，应用上下文对象创建多次

### 解决思路分析：
        在Web项目中，可以使用ServletContextListener监听Web应用的启动，我们可以在Web应用启动
    时，就加载Spring的配置文件，创建应用上下文对象ApplicationContext，在将其存储到最大的域
    servletContext域中，这样就可以在任意位置从域中获得应用上下文ApplicationContext对象了。
    
## 3.2 Spring提供获取应用上下文的工具
    上面的分析不用手动实现，Spring提供了一个监听器ContextLoaderListener就是对上述功能的封
    装，该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到ServletContext域中，提供
    了一个客户端工具WebApplicationContextUtils供使用者获得应用上下文对象。

所以我们需要做的只有两件事：
1. 在web.xml中配置ContextLoaderListener监听器（导入spring-web坐标）
2. 使用WebApplicationContextUtils获得应用上下文对象ApplicationContext

```xml
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <!--    Spring监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```
在Servlet中获取：
```java
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Account account = (Account)webApplicationContext.getBean("account");
        System.out.println("account = " + account);

```

    