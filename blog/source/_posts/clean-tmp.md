---
title: Linux删除无用缓存及垃圾文件
date: 2020-04-22 19:00:24
tags: Linux
categories: Linux
---
<!-- toc -->
<!-- more -->

## 一、删除缓存
sudo apt-get autoclean                清理旧版本的软件缓存

sudo apt-get clean                    清理所有软件缓存

sudo apt-get autoremove             删除系统不再使用的孤立软件

这三个命令主要清理升级缓存以及无用包的。

## 2、清理opera firefox的缓存文件：
ls ~/.opera/cache4

ls ~/.mozilla/firefox/*.default/Cache

## 3、清理Linux下孤立的包：
终端命令下我们可以用：

sudo apt-get install deborphan -y

## 4、卸载：tracker
一般我只要安装ubuntu就会第一删掉tracker他不仅会产生大量的cache文件而且还会影响开机速度。

附录：
包管理的临时文件目录:  
包在  
/var/cache/apt/archives  
没有下载完的在  
/var/cache/apt/archives/partial  

## 二、删除软件
sudo apt-get remove --purge 软件名  
purge：删除软件缓存和配置  

sudo apt-get autoremove  
autoremove：会删除软件的依赖文件，一般不推荐。

删除系统不再使用的孤立软件

sudo apt-get autoclean

## 三、删除多余内核

1，首先要使用这个命令查看当前Ubuntu系统使用的内核

uname -a

2.列出当前系统中安装内核的 image 和 extra 文件。

 dpkg --list | grep linux-image
 
3.同时删除 linux-headers-4.2.0-12 的 image 和 extra 文件。  
  sudo apt-get purge linux-image-4.2.0-12 

4.上一步中的命令，将同时触发系统对其他内核文件必要性的检查。上一步骤中，提示 linux-headers-4.2.0-30 等内核文件现在不需要了，可以使用“apt-get autoremove”来卸载它(它们)。  

        apt-get autoremove  
执行上述自动卸载操作。
        
列出当前系统中安装的内核 headers 文件。

 dpkg --list | grep linux-headers

image 、extra、 headers文件都是内核的组成文件。

继续删除 4.2.0-12 内核文件中的 headers 文件。

在终端中输入：

    sudo apt-get purge linux-headers-4.2.0-12

系统将删除 4.2.0-12 内核文件中的 headers 文件。
 
总之中间有“xxxxxx”那段的旧内核都能删，注意一般选内核号较小的删。
### 对于centos删除内核
使用uname -r查看内核版本
[root@xuexi ~]# uname -r
3.10.0-1062.18.1.el7.x86_64

接着使用rpm -q kernel查看系统内所有的内核
[root@xuexi ~]# rpm -q kernel
kernel-3.10.0-957.1.3.el7.x86_64
kernel-3.10.0-957.5.1.el7.x86_64
kernel-3.10.0-957.10.1.el7.x86_64
kernel-3.10.0-957.12.1.el7.x86_64
相互对照，将老旧内核使用yum remove命令删除
[root@xuexi ~]# yum remove kernel-3.10.0-957.1.3.el7.x86_64 kernel-3.10.0-957.5.1.el7.x86_64 kernel-3.10.0-957.10.1.el7.x86_64
