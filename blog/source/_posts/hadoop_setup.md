---
title: 安装配置Hadoop开发环境
date: 2020-04-08 17:13:41
tags: BigData
categories: Linux
---

<!-- toc -->
# 安装配置Hadoop开发环境

## 下载Hadoop
我们去官网下载：http://hadoop.apache.org/

<!--more-->

## 配置Hadoop环境
来搭建一个单节点的集群，配置一个伪分布式，为什么不做分布式呢？

其实分布式的配置和伪分布式差不多，只是分布式机器增加了而已，其他没什么两样。

##设置SSH免密登录
在之后操作集群的时候我们需要经常登录主机和从机，所以设置SSH免密登录时有必要的。

输入如下代码：

 ssh-keygen -t rsa -P ''  
生成无密码密钥对，询问保存路径直接输入回车，生成密钥对：id_rsa和id_rsa.pub，默认存储在~/.ssh目录下。 

接下来：把id_rsa.pub追加到授权的key里面去。

cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
然后修改权限：

chmod 600 ~/.ssh/authorized_keys
接着需要启用RSA认证，启动公钥私钥配对认证方式：  

vim /etc/ssh/sshd_config  如果提示权限不足在命令前加上sudo；

修改ssh配置：

RSAAuthentication yes # 启用 RSA 认证  
PubkeyAuthentication yes # 启用公钥私钥配对认证方式  
AuthorizedKeysFile %h/.ssh/authorized_keys # 公钥文件路径

重启SSH：
service ssh restart

好了准备工作已经做完了，我们要开始修改Hadoop的配置文件了，总共需要修改6个文件。分别是：

hadoop-env.sh；
yarn-env.sh ；
core-site.xml；
hdfs-site.xml；
mapred-site.xml；
yarn-site.xml。
我们一个一个接着来配置吧！

hadoop-env.sh 配置
两个env.sh文件主要是配置JDK的位置

提示：如果忘记了JDK的位置了，输入 echo $JAVA_HOME就可以看到哦。

首先我们切换到hadoop目录下

cd /app/hadoop3.1/etc/hadoop/
编辑  hadoop-env.sh在文件中插入如下代码： 
```
 # The java implementation to use.  
 #export JAVA_HOME=${JAVA_HOME}  
export JAVA_HOME=/app/jdk1.8.0_171
```
yarn-env.sh 配置
编辑yarn-env.sh 插入如下代码：
```
export JAVA_HOME=/app/jdk1.8.0_171
```
core-site.xml配置
这个是核心配置文件我们需要在该文件中加入HDFS的URI和NameNode的临时文件夹位置，这个临时文件夹在下文中会创建。

在文件末尾的configuration标签中添加代码如下：
```
<configuration>  
 <property>  
    <name>fs.default.name</name>  
    <value>hdfs://localhost:9000</value>  
    <description>HDFS的URI，文件系统://namenode标识:端口号</description>  
</property>  
<property>  
    <name>hadoop.tmp.dir</name>  
    <value>/usr/hadoop/tmp</value>  
    <description>namenode上本地的hadoop临时文件夹</description>  
</property>  
</configuration>  
```

hdfs-site.xml文件配置  
replication指的是副本数量，我们现在是单节点，所以是1。
```
<configuration>  
<property>  
    <name>dfs.name.dir</name>  
    <value>/usr/hadoop/hdfs/name</value>  
    <description>namenode上存储hdfs名字空间元数据 </description>   
</property>  
<property>  
    <name>dfs.data.dir</name>  
    <value>/usr/hadoop/hdfs/data</value>  
    <description>datanode上数据块的物理存储位置</description>  
</property>  
<property>  
    <name>dfs.replication</name>  
    <value>1</value>  
</property>  
</configuration> 
```
 
mapred-site.xml文件配置
```
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```
yarn-site.xml配置
```
<configuration>  
<property>  
        <name>yarn.nodemanager.aux-services</name>  
        <value>mapreduce_shuffle</value>  
</property>  
<property>  
        <name>yarn.resourcemanager.webapp.address</name>  
        <value>192.168.2.10:8099</value>  
        <description>这个地址是mr管理界面的</description>  
</property>  
</configuration>  
```

创建文件夹

我们在配置文件中配置了一些文件夹路径，现在我们来创建他们，在/usr/hadoop/目录下使用hadoop用户操作，建立tmp、hdfs/name、hdfs/data目录，执行如下命令：

mkdir -p /usr/hadoop/tmp 
mkdir /usr/hadoop/hdfs 
mkdir /usr/hadoop/hdfs/data 
mkdir /usr/hadoop/hdfs/name
将Hadoop添加到环境变量中
vim /etc/profile
在文件末尾插入如下代码：
```
 #set Hadoop Enviroment
export HADOOP-_HOME-/app/hadoop3.1
export PATH=SPATH：SHADOOP_HOME/bin：SHADOOP_HOME/sbin
```
最后使修改生效：source /etc/profile

##验证
现在配置工作已经基本搞定，接下来只需要完成：1.格式化HDFS文件、2.启动hadoop、3.验证Hadoop 即可。

格式化
在使用Hadoop之前我们需要格式化一些hadoop的基本信息。

使用如下命令：

hadoop namenode -format

##启动Hadoop
接下来我们启动Hadoop：

start-dfs.sh

之后如果你是图形化界面，可以在你虚拟机的图形化界面中打开火狐浏览器输入：http://localhost:9870/ 访问hadoop的管理页面。
