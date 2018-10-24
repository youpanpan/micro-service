# micro-service
微服务项目基础结构

该项目结构分为以下几部分：

1、父项目：POM项目（micro-service）

2、实体、公共类项目（micro-service-api）

3、服务提供者项目（micro-service-provider-项目名称）

4、服务消费者项目（micro-service-consumer-项目名称）


1、父项目：POM项目（micro-service）

这个项目主要是进行子项目包的版本管理及通用的依赖、构建引入，这个项目只有一个pom.xml文件

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.chengxuunion</groupId>
  <artifactId>micro-service</artifactId>
  <version>0.0.1</version>
  <packaging>pom</packaging>
  
  <!-- 依赖的父项目 -->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.0.4.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
  </parent>
  
  <!-- 属性配置 -->
  <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
      <java.version>1.8</java.version>
  </properties>
  
  <dependencyManagement>
    <dependencies>
    
        <!-- 依赖micro-service-api -->
		<dependency>
		   <groupId>com.chengxuunion</groupId>
		   <artifactId>micro-service-api</artifactId>
		   <version>0.0.1</version>
		</dependency>
        
        <!-- SpringCloud依赖包 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Finchley.SR1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        
        <!-- mybatis依赖 -->
		<dependency>
		    <groupId>org.mybatis.spring.boot</groupId>
		    <artifactId>mybatis-spring-boot-starter</artifactId>
		    <version>1.3.2</version>
		</dependency>
		
		<!-- mybatis分页插件: pagehelper -->
		<dependency>
		    <groupId>com.github.pagehelper</groupId>
		    <artifactId>pagehelper</artifactId>
		    <version>4.1.4</version>
		</dependency>
        
    </dependencies>
  </dependencyManagement>
  
  <build>
    <finalName>micro-service</finalName>
    <plugins>
	    <!-- java编译插件 --> 
	    <plugin> 
	         <groupId>org.apache.maven.plugins</groupId>
	        <artifactId>maven-compiler-plugin</artifactId>
	        <configuration>
	            <!-- 源码使用的开发版本 -->
	            <source>${java.version}</source>
	            <!-- 编译版本 -->
	            <target>${java.version}</target>
	            <!-- 编译器编译源码编码格式 -->
	            <encoding>${project.build.sourceEncoding}</encoding>
	        </configuration> 
	    </plugin> 
    </plugins>
  </build>
  
  <modules>
  	<module>micro-service-api</module>
  	<module>micro-service-provider-pagedesign</module>
  	<module>micro-service-consumer-pagedesign</module>
  </modules>
</project>
这个父项目，依赖了SpringBoot项目，并在dependencyManagement下定义了一些依赖的版本信息，这样在之类只要引入就行，不需要写版本号，同时定义了java编译的信息

2、实体、公共类项目（micro-service-api） 

这个项目主要定义一些公共的类及实体，这样在服务方和提供方只要引入这个项目的依赖就行，不用写两套实体代码及公用的代码



3、服务提供者项目（micro-service-provider-项目名称） 

该项目是服务提供方提供REST服务的项目，这里依赖了micro-service-api， SpringBoot，mysql，mybatis, 分页插件，并在application.yml中进行配置

#配置web服务器
server:
    port: 8090
    servlet:
        context-path: /pagedesign-provider

#mybatis配置
mybatis:
    config-location: classpath:mybatis-config.xml
    mapper-locations: classpath*:com/chengxuunion/mapper/**/*.xml
    type-aliases-package: com.chengxuunion


spring:
    #数据源配置
    datasource:
        driverClassName: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/pagedesign?useUnicode\=true&characterEncoding\=UTF-8&allowMultiQueries\=true
        username: root
        password: 123456
        
#日志配置
#开启DEBUG级别的日志
debug: true
logging:
    level:
        root: INFO
    file: /logs/pagedesign-provider.log
    pattern:
        console: "%d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger- %msg%n"
        file: "%d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger- %msg%n"
通过REST提供接口（PageController）

4、服务消费者项目（micro-service-consumer-项目名称） 

该项目使用SpringBoot作为REST客户端，调用服务提供方提供的服务，这里使用RestTemplate进行访问 ，该项目依赖：micro-service-api，SpringBoot

PageController.java(考虑到这是客户端，后面可能会有页面交互，所以这里使用@Controller注解)

package com.chengxuunion.page.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.chengxuunion.common.ResponseBean;
import com.chengxuunion.exception.BaseException;
import com.chengxuunion.vo.Page;

/**
 * 页面控制器（客户端交互）
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@Controller
public class PageController {
	
	private static final String QUERY_ALL_PAGE = "http://localhost:8090/pagedesign-provider/pages/user/{userId}";
	private static final String QUERY_PAGE = "http://localhost:8090/pagedesign-provider/pages/{pageId}";
	private static final String ADD_PAGE = "http://localhost:8090/pagedesign-provider/pages";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@ResponseBody
	@RequestMapping(value="/pages/user/{userId}", method=RequestMethod.GET)
	public ResponseBean queryAllPage(@PathVariable("userId") String userId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.getForEntity(QUERY_ALL_PAGE , ResponseBean.class, userId);
		ResponseBean responseBean = responseEntity.getBody();
		
		return responseBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.GET)
	public ResponseBean queryPage(@PathVariable("pageId") String pageId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.getForEntity(QUERY_PAGE, ResponseBean.class, pageId);
		ResponseBean responseBean = responseEntity.getBody();
		
		return responseBean;
	}
	
	@ResponseBody
	@RequestMapping(value="/pages", method=RequestMethod.POST)
	public ResponseBean addPage(Page page) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.postForEntity(ADD_PAGE, page, ResponseBean.class);
		
		return responseEntity.getBody();
	}
	
	@ResponseBody
	@RequestMapping(value="/pages/{pageId}", method=RequestMethod.DELETE)
	public ResponseBean deletePage(@PathVariable("pageId") String pageId) throws BaseException {
		ResponseEntity<ResponseBean> responseEntity = restTemplate.exchange(QUERY_PAGE, HttpMethod.DELETE, null, ResponseBean.class, pageId);
		
		return responseEntity.getBody();
	}
	

}
