---
title: zip、unzip应用和dpkg用法
date: 2020-04-22 19:02:42
tags: Linux
categories: Linux
---
<!-- toc -->
<!-- more -->
## 1、安装zip、unzip应用。
### centos
yum install zip unzip
### ubuntu
apt install zip unzip
## 2、压缩和解压文件
```
	以下命令均在/home目录下操作  
	cd /home #进入/home目录  
　　　　
　　a、把/home目录下面的mydata目录压缩为mydata.zip    
　　		zip -r mydata.zip mydata #压缩mydata目录    
　　　　
　　b、把/home目录下面的mydata.zip解压到mydatabak目录里面  
　　
　　　　unzip mydata.zip -d mydatabak  
　　　　
　　c、把/home目录下面的abc文件夹和123.txt压缩成为abc123.zip  
　　
　　　　zip -r abc123.zip abc 123.txt  
　　　　
　　d、把/home目录下面的wwwroot.zip直接解压到/home目录里面  
　　
　　　　unzip wwwroot.zip  
　　e、把/home目录下面的abc12.zip、abc23.zip、abc34.zip同时解压到/home目录里面  
　　　　unzip abc\*.zip  
　　f、查看把/home目录下面的wwwroot.zip里面的内容  
　　　　unzip -v wwwroot.zip  
　　g、验证/home目录下面的wwwroot.zip是否完整  
　　　　unzip -t wwwroot.zip  
　　h、把/home目录下面wwwroot.zip里面的所有文件解压到第一级目录  
　　　　unzip -j wwwroot.zip
```
## 3、用dpkg命令行运行deb安装包的方法

sudo dpkg -I iptux.deb 查看iptux.deb软件包的详细信息，包括软件名称、版本以及大小等（其中-I等价于–info）

sudo dpkg -c iptux.deb 查看iptux.deb软件包中包含的文件结构（其中-c等价于–contents）

sudo dpkg -i iptux.deb 安装iptux.deb软件包（其中-i等价于–install）

sudo dpkg -l iptux 查看iptux软件包的信息（软件名称可通过dpkg -I命令查看，其中-l等价于–list）

sudo dpkg -L iptux 查看iptux软件包安装的所有文件（软件名称可通过dpkg -I命令查看，其中-L等价于–listfiles）

sudo dpkg -s iptux 查看iptux软件包的详细信息（软件名称可通过dpkg -I命令查看，其中-s等价于–status）

sudo dpkg -r iptux 卸载iptux软件包（软件名称可通过dpkg -I命令查看，其中-r等价于–remove）

注： dpkg命令无法自动解决依赖关系。如果安装的deb包存在依赖包，则应避免使用此命令，或者按照依赖关系顺序安装依赖包。