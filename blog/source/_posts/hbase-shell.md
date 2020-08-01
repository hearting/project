---
title: HBase Shell常用命令入门
date: 2020-05-15 13:55:54
tags: BigData
categories: BigData
---
<!-- toc -->
<!-- more -->
#HBase Shell常用命令入门
shell命令|描述
:-:|:-:
version|查看版本
alter|修改列族（column family)模式
count|统计表中行列数
create|创建表
describe|显示表的相关信息
delete|删除指定对象的值（行、列对应的值，可以指定时间）
deleteall|删除指定行的所有元素值
disable|使表无效
enable|使表有效
drop|删除表
exist|测试表是否存在
get|获取行或单元格（cell)的值
put|向指定单元格添加值
incr|增加指定行或列的值
list|列出Hbase存在的所有表
scan|扫描表来获取值
status|查看hbase集群的状态信息
truncate|重新创建指定表
shutdown|关闭hbase集群服务（与exit不同，必须重启才能恢复）
exit|退出（还可以重新进入）

##创建表
创建了一个“student”表，属性有：name,sex,age,,course。因为HBase的表中会有一个系统默认的属性作为行键，无需自行创建，默认为put命令操作中表名后第一个数据。定义表的时候只需要指定column family的名字，列名在put的时候动态指定
>create 'student','name','sex','age','course'

建立一个有3个column family的表,指定保存的版本数（假设指定为3),默认为1.

>create 't1', {NAME => 'f1', VERSIONS => 3}, {NAME => 'f2', VERSIONS => 3}, {NAME => 'f3', VERSIONS => 3}

查看“student”表的基本信息
>describe 'student'

## 基本操作
### 添加数据
一次只能为一个表的一行数据的一个列，也就是一个单元格添加一个数据

为student表添加了学号为95001，名字为zhang的一行数据，其行键为8279。
>put 'student','95001','name','zhang'

行键为8279下的course列族的math列添加了一个数据。
>put 'student','8279','course:math','80'


手工把memstore写到Hfile中,每次flash都会建一个新的hfile.11

flush 't1'
###删除数据

删除了student表中8279行下的sex列的所有数据。
>delete 'student','8279','sex'

删除了student表中的95001行的全部数据。
>deleteall 'student','8279'

delete 't1','rowkey001','f1:col1'

注：将删除改行f1:col1列所有版本的数据
### 查看数据
返回的是‘student’表‘95001’行的数据。
>get 'student','95001'  
返回的是‘student’表的全部数据。  
>scan 'student'
查询时，指定查询的历史版本数。默认会查询出最新的数据。

>get 'teacher','8279',{COLUMN=>'username',VERSIONS=>5}
### 删除表
删除表有两步，第一步先让该表不可用，第二步删除表。

	disable 'student'  
	drop 'student'
### 修改表结构
修改表结构必须先disable
>alter 't1', {NAME => 'f1'}, {NAME => 'f2', METHOD => 'delete'}

### 权限管理
1）分配权限
语法 : grant \<user> \<permissions> \<table> \<column family> \<column qualifier> 

参数后面用逗号分隔

权限用五个字母表示： "RWXCA".

 READ('R'), WRITE('W'), EXEC('X'), CREATE('C'), ADMIN('A')

2）查看权限

语法：user_permission <table>

3）收回权限

revoke \<user> \<table> \<column family> \<column qualifier>

