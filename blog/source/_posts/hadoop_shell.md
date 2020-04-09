---
title: hadoop shell命令使用
date: 2020-04-08 16:41:48
tags: BigData
categories: BigData
---

常用的shell命令，在之后使用的时候可以作为参考。
hadoop fs

选项名称|	使用格式|	含义
-|-|-
-ls	|-ls <路径>|	查看指定路径的当前目录结构
-lsr|	-lsr <路径>|	递归查看指定路径的目录结构
-du	|-du <路径>	|统计目录下个文件大小
-dus|	-dus <路径>	|汇总统计目录下文件(夹)大小
-count|	-count [-q] <路径>	|统计文件(夹)数量
-mv	|-mv <源路径> <目的路径>	|移动
-cp	|-cp <源路径> <目的路径>	|复制
-rm	|-rm [-skipTrash] <路径>	|删除文件/空白文件夹
-rmr|	-rmr [-skipTrash] <路径>|	递归删除
-put|	-put <多个 linux 上的文件> <hdfs 路径>|	上传文件
-copyFromLocal|	-copyFromLocal <多个 linux 上的文件><hdfs 路径>|	从本地复制
-moveFromLocal|	-moveFromLocal <多个 linux 上的文件><hdfs 路径>|	从本地移动
-getmerge	|-getmerge <源路径> <linux 路径>	|合并到本地
-cat|	-cat <hdfs 路径>|	查看文件内容
-text|	-text <hdfs 路径>|	查看文件内容
-copyToLocal|	-copyToLocal [-ignoreCrc] [-crc] [hdfs 源路径] [linux 目的路径]	|从HDFS复制到本地
-moveToLocal|	-moveToLocal [-crc] <hdfs 源路径> <linux目的路径>	从HDFS移动到本地
-mkdir|	-mkdir <hdfs 路径>|	创建空白文件夹
-setrep|	-setrep [-R] [-w] <副本数> <路径>|	修改副本数量
-touchz	|-touchz <文件路径>	|创建空白文件