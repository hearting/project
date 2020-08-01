---
title: 安装配置HBASE
date: 2020-05-15 13:55:42
tags: BigData
categories: BigData
---
<!-- toc -->
<!-- more -->
#安装配置HBase
[下载链接](http://archive.apache.org/dist/hbase/)

说明：HBase的版本一定要和之前已经安装的Hadoop的版本保持兼容，不能随便选择版本。
## Hadoop和HBase版本支持
[参考链接](http://hbase.apache.org/book.html)
![](https://pic.downk.cc/item/5ebd55dec2a9a83be597b77c.png)
![](https://pic.downk.cc/item/5ebd5614c2a9a83be597fd1c.png)

## 安装
本教程安装hbase-1.3.5。如果没有安装Hadoop请参考[安装Hadoop](https://blog.csdn.net/qq_42112448/article/details/105128852)

1 解压安装包hbase-1.1.2-bin.tar.gz至路径 /usr/local，命令如下：
>sudo tar -zxf ~/下载/hbase-1.3.5-bin.tar.gz -C /usr/local

2 将解压的文件名hbase-1.1.2改为hbase，命令如下：
>sudo mv /usr/local/hbase-1.3.5-bin.tar.gz /usr/local/hbase

3 配置环境变量
将hbase下的bin目录添加到path中，这样，启动hbase就无需到/usr/local/hbase目录下，大大的方便了hbase的使用。编辑~/.bashrc文件
>vi ~/.bashrc

如果没有引入过PATH请在~/.bashrc文件尾行添加如下内容：  
>export PATH=$PATH:/usr/local/hbase/bin

编辑完成执行下面命令：  
>source ~/.bashrc

5 查看HBase版本，确定hbase安装成功,命令如下：  
>hbase version
![](https://pic.downk.cc/item/5ebd5ba9c2a9a83be59dd3b7.png)
## HBase配置
HBase有三种运行模式，单机模式、伪分布式模式、分布式模式。这里我使用的是伪分布式。

1.配置/usr/local/hbase/conf/hbase-env.sh。命令如下：
>vi /usr/local/hbase/conf/hbase-env.sh

配置JAVA_HOME，HBASE_CLASSPATH，HBASE_MANAGES_ZK.HBASE_CLASSPATH设置为本机Hadoop安装目录下的conf目录（即/usr/local/hadoop/conf）

>export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64  
>export HBASE_CLASSPATH=/usr/local/hadoop/conf  
>export HBASE_MANAGES_ZK=true

2.配置/usr/local/hbase/conf/hbase-site.xml。用命令vi打开并编辑hbase-site.xml，命令如下：

vi /usr/local/hbase/conf/hbase-site.xml

修改hbase.rootdir，指定HBase数据在HDFS上的存储路径；将属性hbase.cluter.distributed设置为true。假设当前Hadoop集群运行在伪分布式模式下，在本机上运行，且NameNode运行在9000端口。hbase.rootdir指定HBase的存储目录；hbase.cluster.distributed设置集群处于分布式模式.


	<configuration>
        <property>
                <name>hbase.rootdir</name>
                <value>hdfs://localhost:9000/hbase</value>
        </property>
        <property>
                <name>hbase.cluster.distributed</name>
                <value>true</value>
        </property>
	</configuration>

3 接下来测试运行HBase
> start-dfs.sh

再启动HBase

> start-hbase.sh

输入命令jps

![](https://pic.downk.cc/item/5ebd5ed4c2a9a83be5a1b998.png)

进入shell界面：

hbase shell

退出Shell
>exit

停止HBase运行,命令如下：

> stop-hbase.sh

注意：如果在操作HBase的过程中发生错误，可以通过{HBASE_HOME}目录（/usr/local/hbase）下的logs子目录中的日志文件查看错误原因。  
这里启动关闭Hadoop和HBase的顺序一定是：  
启动Hadoop—>启动HBase—>关闭HBase—>关闭Hadoop