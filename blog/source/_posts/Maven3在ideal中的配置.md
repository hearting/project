---
title: Maven3在ideal中的配置
date: 2020-08-01 22:15:18
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

## 下载Maven
[官网下载地址](http://maven.apache.org/download.cgi)
[华为云下载地址](https://mirrors.huaweicloud.com/apache/maven/maven-3/)在这里下载更快
注意！！！  idea2019版与maven3.6.2版本不兼容，我们授课使用3.5.2版本或3.5.4
* 下载往期版本
[往期版本下载地址](https://archive.apache.org/dist/maven/maven-3/)
![](https://img-blog.csdnimg.cn/20200731003247457.png)
## 2.安装和配置
解压
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731003541771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
配置环境变量
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731003750443.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
变量值为Maven安装目录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731003832602.png)
在系统变量Path中添加变量值：

;%MAVEN_HOME%\bin

检查是否安装成功
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004023240.png)
配置本地仓库和私服
在maven安装目录下的conf文件夹中找到settings.xml文件
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004154195.png)
```
1.本地仓库的路径
<localRepository>D:\rep_boot</localRepository>

2.阿里云镜像
<mirror>  
	  <id>alimaven</id>  
	  <name>aliyun maven</name>  
	  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
	  <mirrorOf>central</mirrorOf>          
</mirror>
或华为镜像，二者只能选一个
 <mirror>
      <id>huaweicloud</id>
      <mirrorOf>*</mirrorOf>
      <url>https://mirrors.huaweicloud.com/repository/maven/</url>
 </mirror>

3.jdk版本
 <profile>
      <id>jdk-14</id>
      <activation>
        <jdk>14</jdk>
      </activation>
	  <properties>
		<maven.compiler.source>14</maven.compiler.source>
			<maven.compiler.target>14</maven.compiler.target>
			<maven.compiler.compilerVersion>14</maven.compiler.compilerVersion>
	  </properties>
  </profile>
```
## ideal中配置maven
**1.进入设置**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004451883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004512771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004537693.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020073100463949.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731004708194.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
## 依赖管理
创建后的工程结构是不完善的，我们还需要在main下添加java和resources两个文件夹
![](https://img-blog.csdnimg.cn/20200731004930378.png)
**导入依赖**

**以最原始的jdbc操作为案例**

- 在pom.xml中导入mysql驱动jar包

  ~~~xml
  <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.38</version>
  </dependency>
  ~~~
  - jar包坐标来源，[中央仓库](https://mvnrepository.com/)
  - https://mvnrepository.com/
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731005142794.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
**坐标详解**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200731005300942.png)
**依赖范围**
~~~powershell
A 依赖 B，需要在 A 的 pom.xml 文件中添加 B 的坐标，添加坐标时需要指定依赖范围，依赖范围包
括：

compile：此范围为默认依赖范围。作用在： 编译、测试、运行

provided：只有在当 JDK 或者一个容器已提供该依赖之后才使用，在编译和测试时需要，在运行时不需要。
比如： tomcat 容器包含servlet api，但是在编译和测试时需要，运行时使用tomcat内置的servlet即可。

runtime：在运行和测试系统的时候需要，但在编译的时候不需要。
比如：jdbc的驱动包。

test：在编译和运行时都不需要，它们只有在测试编译和测试运行阶段可用，
比如：junit。

system：system依赖不推荐使用。
~~~
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020073100535468.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
