---
title: mysql远程连接数据库原来怎么简单
date: 2020-08-01 21:47:19
tags: Database
categories: Database
---
<!-- toc -->

<!--more-->

 ## 以管理员身份登录配置
###  一、登录mysql,输入密码
mysql -uroot -p
### 两种配置方法
一、改表法
    在localhost登入mysql后，更改 "mysql" 数据库里的 "user" 表里的 "host" 项，将"localhost"改称"%"

　　mysql>update user set host = '%' where user = 'root';

　　mysql>select host, user from user;

二、授权法
    添加用户：
	CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';
	授予权限
		GRANT ALL ON *.* TO 'myuser'@'localhost';
		或
		
 你想myuser使用mypassword（密码）从任何主机连接到mysql服务器的话。		    
>mysql>GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%'IDENTIFIED BY 'mypassword' WITH 	GRANT OPTION;

　　如果你想允许用户myuser从ip为192.168.1.5的主机连接到mysql服务器，并使用mypassword作为密码

>mysql>GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'192.168.1.5'IDENTIFIED BY  'mypassword' WITH GRANT OPTION;

>mysql>FLUSH PRIVILEGES
　　使修改生效，就可以了

***这里可以看看[数据控制语言（DCL）](https://blog.csdn.net/qq_42112448/article/details/107434384)，强烈推荐。***

## 在本地使用ip地址远程登录
1. 确定远程机器的防火墙关闭，或在防火墙允许3306端口号
> telnet 192.168.1.165 3306
如果不能联通，就需要在防火墙中运行3306端口入站。
2. 远程登录
> mysql -u root -p -h 192.168.1.104

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200719111605416.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
