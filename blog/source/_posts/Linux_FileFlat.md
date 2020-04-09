---
title: Linux目录结构
date: 2020-04-08 17:23:31
tags: Linux
categories: Linux
---

<!-- toc -->
## 1.Linux目录结构是什么？

<!--more-->

整个Linux系统最重要的地方就是在于目录树架构，所谓的目录树架构就是以根目录为主， 然后向下呈现分支状的目录结构的一种档案架构。我们可以先通过下面这幅图来直观的认识一下Linux的目录结构。
![](https://img-blog.csdnimg.cn/20200325230517460.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
 对于这样一些目录，根据FHS（Filesystem Hierarchy Standard）规范了每个目录下面应该放置什么样的数据，主要是依据文件系统使用的频繁与否与是否允许使用者随意更动来划分。
 可分享的(shareable) 不可分享的(unshareable)
不变的(static) /usr (软件放置处) /etc (配置文件)
/opt (第三方协力软件) /boot (开机与核心档) 
可变动的(variable) /var/mail (使用者邮件信箱) /var/run (程序相关)
/var/spool/news (新闻组) /var/lock (程序相关) 
2.    主要目录的简介

a)   根目录（/）

根目录是整个系统最重要的一个目录，因为所有的目录都是由根目录衍生出来的。只有root用户才具有在该目录下写权限。

b)   /bin目录 – 用户二进制文件

包含二进制的可执行文件，你需要的常见的Linux命令都位于此目录下。
![](https://img-blog.csdnimg.cn/20200325230745949.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

c)   /sbin目录 – 系统二进制文件
![](https://img-blog.csdnimg.cn/20200325230805491.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
这个目录下的命令通常由系统管理员使用， 对系统进行维护。



d)   /etc– 配置文件
![](https://img-blog.csdnimg.cn/20200325230823141.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
包含所有程序所需要的配置文件，也包含用于启动/停止单个程序的起动和关闭shell脚本。



e)   /dev-设备文件
![](https://img-blog.csdnimg.cn/20200325230854277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
包含设备文件，包括终端设备、USB或连接到系统的任何设备，如网卡等。


f)   /proc-进程信息文件

这是一个虚拟的文件系统，包含有关正在运行的进程信息。

g)   /var-变量文件

包含目录下内容可变的文件，例如，系统日志文件（/var/log）;包和数据库文件（/var/lib）;电子邮件（/var/mail）;打印队列（/var/spool）;锁文件（/var/lock）;多次重新启动需要的临时文件（/var/tmp）。

h)   /tem-临时文件

包含系统和用户创建的临时文件，当系统重启时，文件消失。

i)   /usr-用户程序

包含二进制文件、库文件、文档和二级程序的源代码。

/usr/bin中包含用户程序的二进制文件。如果你在/bin中找不到用户二进制文件，到/usr/bin目录看看。例如：at、awk、cc、less、scp。

/usr/sbin中包含系统管理员的二进制文件。如果你在/sbin中找不到系统二进制文件，到/usr/sbin目录看看。例如：atd、cron、sshd、useradd、userdel。

/usr/lib中包含了/usr/bin和/usr/sbin用到的库。

/usr/local中包含了从源安装的用户程序。例如，当你从源安装Apache，它会在/usr/local/apache2中

j)  /home -HOME目录

包含所有用户的个人档案，Linux是多用户的系统，所以用该目录保存各用户的信息。

k)  /boot -引导加载程序

包含引导加载程序相关的文件。

l)  /lib -系统库

包含支持位于/lib和/sbin下的二进制文件的库文件。

m)  /opt -可选的附加应用程序

n)  /mnt -挂载目录

## 2.Linux系统中的每个文件和目录都有访问许可权限，
用它来确定谁可以通过何种方式对文件和目录进行访问和操作。
文件或目录的访问权 限分为只读，只写和可执行三种。

这里显示的权限是依次排列的，分别为：[用户][同组][其他]
用户权限，就是你自己的权限。英文：user，简写：u（覆盖标号123）
用户组权限，就是和你同组的人的权限。英文：group，简写：g（覆盖标号456）
其他权限，就是不和你同组的人的权限。英文：others，简写：o（覆盖标号789）
所有人的权限，英文：all，简写：a

r, 即Read，读，权限值为4
w，即Write，写，权限值为2
x，即eXecute，执行，权限值为1
-，在标号0位置，表示普通的文件
-，其他位置，表示对应权限未开启，不具备权限
d，即directory，表示目录文件

无任何权限：数字0表示
开所有权限：数字7表示，即7=4+2+1

chmod 命令是用于改变文件或目录的访问权限。

+ 表示增加权限，如u+x, u+r, u+w, g+w, g+r, o+r， a+r等
- 表示取消权限，如u-x, u-r, u-w, g-w, g-r, o-r， a-r等
= 表示赋予给定权限，并取消其他所有权限（如果有的话，如原来u是rwx，设置u=r，u就剩r）

