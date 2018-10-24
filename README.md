# micro-service
微服务项目基础结构

该项目结构分为以下几部分：

1、父项目：POM项目（micro-service）

2、实体、公共类项目（micro-service-api）

3、服务提供者项目（micro-service-provider-项目名称）

4、服务消费者项目（micro-service-consumer-项目名称）


1、父项目：POM项目（micro-service）

这个项目主要是进行子项目包的版本管理及通用的依赖、构建引入，这个项目只有一个pom.xml文件
这个父项目，依赖了SpringBoot项目，并在dependencyManagement下定义了一些依赖的版本信息，这样在之类只要引入就行，不需要写版本号，同时定义了java编译的信息

2、实体、公共类项目（micro-service-api） 

这个项目主要定义一些公共的类及实体，这样在服务方和提供方只要引入这个项目的依赖就行，不用写两套实体代码及公用的代码



3、服务提供者项目（micro-service-provider-项目名称） 

该项目是服务提供方提供REST服务的项目，这里依赖了micro-service-api， SpringBoot，mysql，mybatis, 分页插件，并在application.yml中进行配置
通过REST提供接口（PageController）

4、服务消费者项目（micro-service-consumer-项目名称） 

该项目使用SpringBoot作为REST客户端，调用服务提供方提供的服务，这里使用RestTemplate进行访问 ，该项目依赖：micro-service-api，SpringBoot
