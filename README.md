**1、IDEA插件**
````
（1）、Cloud Toolkit

（2）、Lombok

（3）、Alibaba Java Code Guidelines
````

**2、微服务（Microservice Architecture，简称 MSA）**
````
    它不是框架，也不是系统，只是一种架构思想。
    
    In short, the microservice architectural style is an approach to developing a single application as a suite of small
    services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API.
    These services are built around business capabilities and independently deployable by fully automated deployment 
    machinery. There is a bare minimum of centralized management of these services, which may be written in different 
    programming languages and use different data storage technologies.
    
    谷歌翻译如下：
    
    简而言之，微服务架构风格是一种将单个应用程序作为一套小型服务开发的方法，每种应用程序都在自己的进程中运行，并与轻量级机制（通常是HTTP资源
    API）进行通信。 这些服务是围绕业务功能构建的，可以通过全自动部署机制独立部署。 这些服务的集中管理最少，可以用不同的编程语言编写，并使用不
    同的数据存储技术。
    
    
    微服务特点：
    
    (1).独立部署，灵活扩展。
        传统的单体架构是以整个系统为单位进行部署，而微服务则是以每一个独立组件（例如用户服务，商品服务）为单位进行部署。
    (2).资源的有效隔离。
        微服务设计的原则之一，就是每一个微服务拥有独立的数据源，假如微服务A想要读写微服务B的数据库，只能调用微服务B对外暴露的接口来完成。这样
        有效避免了服务之间争用数据库和缓存资源所带来的问题。
    (3).团队组织架构的调整。
        微服务设计的思想也改变了原有的企业研发团队组织架构。传统的研发组织架构是水平架构，前端有前端的团队，后端有后端的团队，DBA有DBA的团队，
        测试有测试的团队。
        而微服务的设计思想对团队的划分有着一定的影响，使得团队组织架构的划分更倾向于垂直架构，比如用户业务是一个团队来负责，支付业务是一个团队
        来负责。（实际在企业中并不会把团队组织架构拆分得这么）
````
**服务拆分**
````
实现单一职责，独立部署。
````
**分布式**
````
相对于单体系统，分布式是指将一个业务拆分不同的子业务，分布在不同的服务器上执行，不同服务器之间可以互相通信和协调。

如商城系统：分为商品模块、订单模块等，共同完成购物功能，属于分布式。
````
**集群**
````
是指多台服务器集中在一起，实现同一业务（同一个业务代码部署多套到不同机子上，而非多台服务器共同实现一个业务），集合起来提供服务的（一般会配上负
载均衡）。理论上，分布式的系统一般都是带集群的。

如商城系统中的订单模块，该订单模块分别部署在多台服务器上，属于集群。
````
**负载均衡（spring-cloud-loadbalancer）**
````
负载均衡（Server Load Balancer）是将访问流量根据转发策略分发到后端多台云服务器（ECS实例）的流量分发控制服务。负载均衡扩展了应用的服务能力，
增强了应用的可用性。

解决的问题：
(1).单点故障
    当某一节点出现宕机，停止服务时，服务请求会分发到当前剩余存活的其他节点上进行处理。
(2).性能问题
    当某一服务节点出现过多的访问量时，此时访问超出了该服务的处理能力，可通过负载均衡分发到访问量少的节点。
````
**服务熔断与服务降级（Sentinel）**
````
(1).服务雪崩：
    假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者
    不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。
(2).服务熔断：
    熔断机制是应对雪崩效应的一种微服务链路保护机制。
    在微服务架构中，熔断机制也是起着类似的作用。当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，
    快速返回错误的响应信息。当检测到该节点微服务调用响应正常后，恢复调用链路。
(3).服务降级：
    当服务器压力剧增的情况下，根据实际业务情况及流量，对一些服务和页面有策略的不处理或换种简单的方式处理，从而释放服务器资源以保证核心交易正常
    运作或高效运作。
    
降级与熔断对比:
共性
目的: 目的一致，都是从系统的可用性、可靠性着想。放了防止系统的整体缓慢甚至奔溃而采用的技术手段。
最终表现: 表现类似,最终都是给用户一种当前服务不可用或者不可达的感觉
粒度: 大多都是在服务级别，当然也有一些在持久层层面的应用
自治: 基本都是靠系统达到某一临界条件时，实现自动的降级与熔断，人工降级并不是那么稳妥。

区别
触发原因: 服务熔断一般指某个服务的下游服务出现问题时采用的手段,而服务降级一般是从整体层面考虑的。
管理目标层次: 熔断是一种框架级的处理，每一个微服务都需要。而降级一般需要对业务有层级之分，降级一般都是从外围服务开始的。
实现方式: 代码级别实现有差异

Sentinel使用：
引入依赖：
   <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
      <version>2.1.0.RELEASE</version>
   </dependency>
   
支持Feign
    feign:
       sentinel:
          enabled: true

Sentinel DashBoard:（默认端口8080）
参考：https://www.fangzhipeng.com/springcloud/2019/06/02/sc-sentinel.html

关于@SentinelResource 注解，有以下的属性：
    value：资源名称，必需项（不能为空）
    entryType：entry 类型，可选项（默认为 EntryType.OUT）
    blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项
    fallback：fallback 方法名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑，注意传参与返回必须一致。
如：
    @GetMapping("/hi")
    @SentinelResource(value="hi"，fallback="doSomeThings")
    public String hi(String name){
        return "hi "+name;
    }
    
    public String doSomeThings(String name){
        return "hi方法调用出现故障，直接调用doSomeThings方法。";
    }

执行：java -jar -Dserver.port=8081 sentinel-dashboard-1.6.3.jar   


````
**服务网关（Gateway）**
````
API网关是一个服务器，是系统的唯一入口。从面向对象设计的角度看，它与外观模式类似。API网关封装了系统内部架构，为每个客户端提供一个定制的API。
它可能还具有其它职责，如身份验证、监控、负载均衡、缓存、请求分片与管理、静态响应处理。
API网关方式的核心要点是，所有的客户端和消费端都通过统一的网关接入微服务，在网关层处理所有的非业务功能。通常，网关也是提供REST/HTTP的访问API。
服务端通过API-GW注册和管理服务。

配置路由时，统一使用lb://XXXXXXX，表示进行路由转发时，会去服务注册发现中找到具体的服务，且默认使用负载均衡算法
    routes:
        - id: mall-goods
          uri: lb://mall-goods

要点：1、统一系统入口。2、路由分发。
````
**服务注册与发现（Nacos）**
````
用于服务的注册和发现，方便管理各个微服务，同时检测各服务的健康状态

使用方法：
(1).本地下载nacos,并启动。http://localhost:8848/nacos：默认个账号密码：nacos
(2).添加依赖
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        <version>2.1.0.RELEASE</version>
    </dependency>
(3).配置：
    spring:
      application:
        name: XXXX
      cloud:
        nacos:
          discovery:
            server-addr: localhost:8848
            配置多个进行集群
            server-addr: IP1:8848,IP2:8848,IP3:8848
````
**服务配置（Nacos）**
````
 利用nacos控制平台实现微服务的自动化配置，实现热部署。（即省去了修改配置文件时重新打包、上传、启动的相关步骤）
 说明：
    Data ID：即${prefix}-${spring.profiles.active}.${file-extension}
    其中：nacos:
          config:
            server-addr: 127.0.0.1:8848
            file-extension: yml
            prefix: mall-gateway
         spring:
           profiles:
             active: dev
    如：利用nacos进行网关服务的配置
    Data ID：mall-gateway-dev.yml
    配置类容：将application.yml的配置文件复制进来
    
 补充：
    使用nacos config时，配置内容使用bootstrap.yml。
    <1、bootstrap.yml与application.yml
        bootstrap.yml（bootstrap.properties）用来程序引导时执行，应用于更加早期配置信息读取，如可以使用来配置application.yml中使用到参数等
        application.yml（application.properties) 应用程序特有配置信息，可以用来配置后续各个模块中需使用的公共参数等。
        bootstrap.yml 先于 application.yml 加载
    <2、测试环境中使用dev模式启动，生产环境中使用prod模式启动
        如：java -jar -Dspring.profiles.active=dev mall-1.0-SNAPSHOT.jar
           java -jar -Dspring.profiles.active=prod mall-1.0-SNAPSHOT.jar
````
**服务调用（OpenFeign）**
````
各微服务之间的Http请求。
使用：
    <1、引入依赖：
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    
    <2、启动类添加@EnableFeignClients
    
    <3、创建FeignClient
        // name 指当前服务索要调用的服务的服务ID，即服务名称
        // fallback 服务熔断，指定默认的返回信息
        @FeignClient(name = "mall-goods", fallback = GoodsFeignClientFallback.class)
        public interface GoodsFeignClient {
            @RequestMapping("feign")
            String getGoodsInfo();
        }
        
        
        @Component
        public class GoodsFeignClientFallback implements GoodsFeignClient {
            @Override
            public String getGoodsInfo() {
                return "GoodsFeignClientFallback: feign失败，返回默认值";
            }
        }
        
        如上所示：若当前mall-goods服务宕机，则请求getGoodsInfo()方法，则返回GoodsFeignClientFallback中的默认值。
        注：必须配置Sentinel，设置
            feign:
               sentinel:
                  enabled: true
        
        
        

````
**3、开发规范:**

(1).【参考】分层领域模型规约：   
````
• DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。    
• DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象。   
• VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
注：阿里巴巴开发规范——第38页

约定：<1、接口上传的数据对象封装为XxxxDTO，且该对象直接传入业务层：Service
     <2、数据库表对象封装为XxxxDO
     <3、后端返回给前端的数据对象封装为XxxxVO，可直接在业务层：Service直接构建返回，
     <4、进行分页请求时，同意返回对象PageVO，内部实现分页的基本参数
````
(2).【参考】各层命名规约(待讨论)
````
说明：由于当前开发框架已简化了dao层的相关操作，由第三方插件来完成，故约定控制层接口定义和业务层方法中一些基本增删改查的基本操作使用以下命名方式。
     优势：<1、基本统一系统对外开放的相关接口;
          <2、方便后续可能会使用Spring中的AOP
          
A) Service/DAO 层方法命名规约  
1） 获取单个对象的方法用 get 做前缀。                  约定：http://locahost:8080/getGoodsById
2） 获取多个对象的方法用 list 做前缀。                 约定：http://locahost:8080/list（普通）、http://locahost:8080/listPage（分页）
3） 获取统计值的方法用 count 做前缀。                  约定：http://locahost:8080/countGoodsNumber
4） 插入的方法用 save/insert 做前缀。                 约定：http://locahost:8080/saveGoods
5） 删除的方法用 remove/delete 做前缀。               约定：http://locahost:8080/removeGoodsById
6） 修改的方法用 update 做前缀。                      约定：http://locahost:8080/updateGoodsById
B) 领域模型命名规约  
1） 数据对象：xxxDO，xxx 即为数据表名。  
2） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。  
3） 展示对象：xxxVO，xxx 一般为网页名称。  
4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。  
注：阿里巴巴开发规范——第3页
````
(3).数据库建表规约
````
注：参考阿里巴巴开发规范第31页
约定：表命名禁止使用m_goods_cg等缩写形式或m_goodscategory，统一完整单词mall_goods_category
````
(4).全局统一业务异常处理类：BusinessException
````
约定：所有业务层方法中，只能执行一次“return Object”，其余不符合条件的判断，均以BusinessException抛出统一由businessException异常类统一处理。
举例：
     反例：
     public Object test(String account,String password){
        if(StringUtils.isEmpty(account)){
           return "账号不能为空";
        }
        
        if(StringUtils.isEmpty(password)){
           return "密码不能为空";
        }
        
        return "成功";
     }
     
     正例：
     public Object test(String account,String password){
        if(StringUtils.isEmpty(account)){
            throw new BusinessException("账号不能为空");
        }
             
        if(StringUtils.isEmpty(password)){
            throw new BusinessException("密码不能为空");
        }
             
        return "成功";
     }
````

(5).统一状态码
````
约定：平台端、商家端统一返回code：0：成功  1：失败
     移动端设置维护枚举类code：0成功  其他：失败
````

(6).Controller类返回值建议（非必须）
````
建议：<1、返回值为Result。常见方法中只返回code、msg相关内容的
     <2、返回值为PageVO。常见方法中执行分页相关信息的
     <3、返回值为XxxxVO。常见于向前端返回某些对象的封装类
     
注：所有code/msg统一由ResultAdvice进行封装处理，默认返回code：0，msg：成功，若要指定msg信息，请添加方法注释
ResultHandler进行赋值。对于业务层无需返回数据的API（即void方法）的，Controller可直接返回结果数据。
如：
    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ResultHandler("获取商品对象成功")
    public GoodsVO getGoodsById(Integer goodsId){
        
    }
````
(7).实体类对象操作
````
构建实体类对象：
    建议使用Lombok依赖包中的相关注解，好处：利于代码的简洁性。
    (必须)基本操作注解@Data，get/set/toString等
    如：
       @Data
       public class GoodsDTO{
            private String name;
       }
       使用：
       GoodsDTO goodsDTO = new GoodsDTO();
       goodsDTO.setName("XXXX");
       String name = goodsDTO.getName();
     
    (非必须)也可使用今天方法获取对象实例注解:@NoArgsConstructor(staticName = "build")
    如：
       @Data
       @NoArgsConstructor(staticName = "build")
       public class GoodsDTO{
           private String name;
       }
       其中，“build”可自定义，也可以是“of”等
       获取对象实例
       GoodsDTO goodsDTO = GoodsDTO.build();
       或
       GoodsDTO goodsDTO = GoodsDTO.of();
       
    (非必须)也可使用Lombok中的链式操作注解:@Accessors(chain = true)
        @Data
        @Accessors(chain = true)
        @NoArgsConstructor(staticName = "build")
        public class GoodsDTO{
            private String name;
            private String price;
        }
        使用：
        GoodsDTO goodsDTO = GoodsDTO.build()
                                    .setName("电脑")
                                    .setPrice("5999")

DTO、DO、VO之间的互相转化：
    建议使用Spring下的BeanUtils工具类，优势利于代码的简洁，缺点容易造成字段的遗漏（开发过程中注意该字段的重要性）
    如：
        GoodsDTO goodsDTO = GoodsDTO.build()
                                    .setName("电脑")
                                    .setPrice("5999");
        GoodsDO goodsDO = GoodsDO.build()
                                 .setName("手机")
                                 .setPrice("100");
        BeanUtils.copyProperties(goodsDTO,goodsDO);
        输出：goodDO={name=“电脑”,price="5999"}
        
    说明：该copyProperties()方式的实现是匹配两个对象间相同的字段，将第一个参数即goodsDTO中的属性值赋予第二个参数即goodsDO中相同属性的字段
````
(8).DTO对象校验约定
````
一般属性建议使用@Valid注解进行空值的判断，某些特殊情况下可使用if()判断。
优势避免了代码中出现大篇幅的if()判断语句。
如：
    public class GoodsDTO{
        @NotBlank(message = "name不能为空")
        private String name;
    }

    @RequestMapping(value = "/saveGoods", method = RequestMethod.POST)
    public Result saveGoods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        return JsonResult.buildResultOk();
    }
    若上传name为null，则会将错误信息包装在BindingResult类中，可直接操作BindingResult类。
````
(9).类、方法、字段添加相关注释说明
````
````
(10).其他
````
<1、开发过程中若有必要，请进行相关的注释说明。
<2、尽可能的按照Alibaba开发规范进行开发。
````
4、相关控制台  
````
(1).启动nacos客户端（nacos控制台：http://locahost:8848/nacos,账号：nacos 密码：nacos）   
    可进行相关微服务的上线、下线以及微服务的相关配置控制   
(2).启动Sentinel-dashboard(Sentinel控制台：http://localhost:8081,账号：sentinel 密码：sentinel)
    java -jar -Dserver.port=8081 sentinel-dashboard-1.6.3.jar  
    指定服务端口8081，默认端口为8080   
    可设置服务的熔断阀值与服务降级设置
````

