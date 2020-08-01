---
title: Mysql基本操作最简单入门
date: 2020-08-01 21:44:42
tags: Database
categories: Database
---
<!-- toc -->

<!--more-->

## 简介
MySQL 是一个关系型数据库管理系统，由瑞典 MySQL AB 公司开发，目前属于 Oracle 公司。MySQL 是一种关联数据库管理系统，关联数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。   
MySQL 使用标准的 SQL 数据语言形式。  
MySQL 支持大型数据库，支持 5000 万条记录的数据仓库，32 位系统表文件最大可支持 4GB，64 位系统支持最大的表文件为8TB。  
在语言中不区分大小写。
## 使用
1. 在cmd中，打开MySQL数据库服务,

>net start mysql
2. 登录mysql,输入密码
>mysql -uroot -p 
3. 查看数据库
> show databases;
4. 使用某一数据库
>use 数据库名;
5. 查看数据库中的表
>show tables;
6. 查询当前正在使用的数据库
> select database();
7. 查询表结构
>desc 表名;
8. 查看数据文件存放路径
> show variables like '%datadir%';
9. 查询数据库相关信息
> status;
10 查看当前登录的用户.
> SELECT CURRENT_USER;
11 查看数据库所有用户
>select user,host from mysql.user;
## SQL分类
### **1.数据定义语言（DDL）**
常用的有CREATE、ALTER和DROP，用于在数据库中创建新表或删除表，以及为表加入索引等。
#### 数据库操作
```
创建数据库：   
​		create database 数据库名称;
创建数据库db,判断是否存在，制定字符集为utf8
	 create database if not exists db character set utf8;
修改数据库的字符集
	alter database 数据库名称 character set 字符集名称;
删除数据库
	drop database `db`;
如果db存在就删除
	DROP database IF EXISTS `db`;
```
#### 表操作(CRUD)
##### 约束种类
非空约束(not null)  
用not null约束的字段不能为null值，必须给定具体的数据
1. 创建表时添加约束
		CREATE TABLE stu(
			id INT,
			NAME VARCHAR(20) NOT NULL -- name为非空
		);
2. 创建表完后，添加非空约束
		ALTER TABLE stu MODIFY NAME VARCHAR(20) NOT NULL;

3. 删除name的非空约束
		ALTER TABLE stu MODIFY NAME VARCHAR(20);
		
唯一性约束(unique)  
unique约束的字段，具有唯一性，不可重复，但可以为null
1. 创建表时，添加唯一约束
		CREATE TABLE stu(
			id INT,
			phone_number VARCHAR(20) UNIQUE -- 添加了唯一约束	
		);
2. 删除唯一约束(drop index)
		ALTER TABLE stu DROP INDEX phone_number;
		DROP INDEX index_name ON tbl_name
3. 在创建表后，添加唯一约束
		ALTER TABLE stu MODIFY phone_number VARCHAR(20) UNIQUE;
		alter table tableName add unique(column_name)
		
主键约束(primary key) PK  
表中的某个字段添加主键约束后，该字段为主键字段，主键字段中出现的每一个数据都称为主键值     
主键约束除了可以做到”not null unique”之外，还会默认添加”索引——index”   
主键值：是当前行数据的唯一标识，即使表中两行记录相关数据相同，但由于主键值不同，所以也认为是两行不同的记录。   

		在创建表时，添加主键约束
		create table stu(
			id int primary key,-- 给id添加主键约束
			name varchar(20)
		);

	  删除主键
		-- 错误 alter table stu modify id int ;
		ALTER TABLE stu DROP PRIMARY KEY;

	创建完表后，添加主键
		ALTER TABLE stu MODIFY id INT PRIMARY KEY;
		ALTER TABLE <数据表名> ADD PRIMARY KEY(<列名>);
	自动增长：
		1.  概念：如果某一列是数值类型的，使用 auto_increment 可以来完成值得自动增长
		2. 在创建表时，添加主键约束，并且完成主键自增长
		create table stu(
			id int primary key auto_increment,-- 给id添加主键约束
			name varchar(20)
		);
		3. 删除自动增长
		ALTER TABLE stu MODIFY id INT;
		4. 添加自动增长
		ALTER TABLE stu MODIFY id INT AUTO_INCREMENT;

外键约束(foreign key) FK  
若有两个表A、B，id是A的主键，而B中也有id字段，则id就是表B的外键，外键约束主要用来维护两个表之间数据的一致性。

外键值可以为null，外键字段去引用一张表的某个字段的时候，被引用的字段必须具有unique约束    
```
1. 在创建表时，可以添加外键
			create table 表名(
				....
					foreign key(classno) references t_class(cno)
				);
2. 删除外键
ALTER TABLE 表名 DROP FOREIGN KEY 外键名称;

3. 创建表之后，添加外键
ALTER TABLE 从表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段名称) REFERENCES 			  
```
创建一张表名为dept，主键为did自增，其他键为dname、locaction，制定字符集为utf8，引擎为InnoDB，从6开始自增。
```
CREATE TABLE `dept` (
  `did` int(11) NOT NULL AUTO_INCREMENT unique,
  `dname` varchar(20) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

重命名一张表
RENAME TABLE 原名 TO 新名;
ALTRE TABLE 原名 RENAME 新名;
ALTRE TABLE 原名 RENAME TO 新名；

删除一张表
drop table 表名;

删除一列
ALTER TABLE 表名 DROP COLUMN 列名字;
ALTER TABLE 表名 DROP 列名字;

修改表的字符集
alter table 表名 character set 字符集名称;

 添加一列
alter table 表名 add 列名 数据类型;

修改列名
alter table 表名 change 列名 新列别 新数据类型;

修改列类型
alter table 表名 modify 列名 新数据类型;

修改字段排列位置：
alter table 表名modify 字段名1 数据类型 first   |    after  字段名2；
ALTER TABLE grade MODIFY newname VARCHAR(20) FIRST;（将字段newname插入到表的第一个位置）
```
### **2. 数据操纵语言（DML：Data Manipulation Language）**
主要用来对数据库的数据进行一些操作，常用的就是INSERT、UPDATE、DELETE。

语法：
```
    INSERT INTO <表名>(列1,列2,...) VALUES (值1,值2,...);
	
    UPDATE <表名> SET <列名>=新值, 列名2 = 值2 WHERE <列名>=某值;

    DELETE FROM <表名> WHERE <列名>=某值;
    删除整表数据
TRUNCATE TABLE 表名; -- 推荐使用，效率更高 先删除表，然后再创建一张一样的表。
DELETE FROM emps WHERE 1=1;
DELETE FROM emps WHERE TRUE;
DELETE FROM emps WHERE 'a'='a';
```

### **3. 数据查询语言（DQL: Data Query Language）**

数据查询语句，用于从表中获取数据。通常最常用的为保留字SELECT,并且常与FROM子句、WHERE子句组成查询SQL查询语句。  
语法：
 select [distinct] 字段1 [as 别名], ..., 字段n [as 别名] from [库名.]表名
                    [
                    where 约束条件
                    group by 分组依据
                    having 过滤条件
                    order by 排序的字段
                    limit 限制显示的条数
                    ];   
                    
去除重复：distinct   
as也可以省略    
IFNULL() 函数用于判断第一个表达式是否为 NULL，如果为 NULL 则返回第二个参数的值，如果不为 NULL 则返回第一个参数的值。   
IFNULL(expression, alt_value)    
```
 条件查询
 1. where子句后跟条件
2. 运算符
		 > 、< 、<= 、>= 、= 、<>
		 BETWEEN...AND  
		 IN( 集合) 
		 IS NULL  IS NOT NULL isnull()
		and  或 &&
	    or  或 || 
		not  <>或 !
		
模糊查询
LIKE：
		占位符：
		_:单个任意字符
		%：多个任意字符
		[ ] ：表示括号内所列字符中的一个（类似正则表达式）。指定一个字符、字符串或范围，要求所匹配对象为它们中的任一个。
		[^ ] ：表示不在括号所列之内的单个字符。其取值和 [] 相同，但它要求所匹配对象为指定字符以外的任一个字符。
		
排序查询
	order by 排序字段1 排序方式1 ，  排序字段2 排序方式2...
	对筛选结果进行升序或者降序排列(默认是升序)
　　升序:  asc ;         降序:  desc
　　
分页查询
	语句1：select * from student limit 9,4
	语句2：slect * from student limit 4 offset 9
	// 语句1和2均返回表student的第10、11、12、13行  
	//语句2中的4表示返回4行，9表示从表的第十行开始
	公式：开始的索引 = （当前的页码 - 1） * 每页显示的条数
聚合函数：将一列数据作为一个整体，进行纵向的计算。
	1. count：计算个数
		1. 一般选择非空的列：主键
		2. count(*)
	2. max：计算最大值
	3. min：计算最小值
	4. sum：计算和
	5. avg：计算平均值
	6. group_concat()：组内字段拼接，用来查看组内其他字段
	* 注意：聚合函数的计算，排除null值。
		解决方案：
			1. 选择不包含非空的列进行计算
			2. IFNULL函数
分组查询（group by）
SELECT column_name, function(column_name) FROM table_name WHERE column_name operator value GROUP BY column_name;
　　以记录的字段共性对记录进行分组 
　　分组后字段可进行聚合函数处理
　　
having　　筛选
　　对where和group by处理的结果进一步筛选 , 得到我们想要的数据
		where 和 having 的区别？
			1. where 在分组之前进行限定，如果不满足条件，则不参与分组。having在分组之后进行限定，如果不满足结果，则不会被查询出来。如果用了group by 那么后面的过滤条件就不能再用where了，要用having。
			2. where 后不可以跟聚合函数，having可以进行聚合函数的判断。

多表查询
- 内连接
从笛卡尔积中筛选出有效的数据，内连接只显示两个表匹配得上的数据。
SELECT * FROM emp a ,dept b WHERE a.id = b.id;
 select 字段列表 from 表名1 [inner] join 表名2 on 条件
 
- 左、右外连接
左外（以左边的表为主表，主表的数据会全部展示） 右外（以右边的表为主表，主表的数据会全部展示）
左外连接=左表全部记录+相关联结果
右外连接=右表全部记录+相关联结果
SELECT * FROM emps e LEFT JOIN  dept d ON  e.dept_id=d.did  -- 左外

SELECT * FROM emps e RIGHT JOIN  dept d ON  e.dept_id=d.did  -- 右外

SELECT * FROM  dept d RIGHT JOIN  emps e  ON  e.dept_id=d.did  -- 右外 和第一个等效

- 模拟全外连接
左表和右表都不做限制，所有的记录都显示，两表不足的地方用null 填充；
全外连接=左表全部记录+右表全部记录+相关联结果=左外连接+右外连接-相关联结果（即去重复）
union all或union纵向合并结果集，只需要两个结果集的列数和列类型相同即可，无需名称相同。
(SELECT id,`name` FROM emps) UNION (SELECT did,dname FROM dept);

全外连接-左外和右外使用union纵向合并结果集即可
(SELECT * FROM dept d LEFT JOIN emps e ON e.dept_id-d.did)
UNION
(SELECT * FROM dept d RIGHT JOIN emps e ON e.dept_id-d.did);

子查询：查询中嵌套查询，称嵌套查询为子查询。
SELECT * FROM dept t1 ,(SELECT * FROM emp WHERE emp.`join_date` > '2019-11-11')  t2
WHERE t1.id = t2.dept_id;
```

### **4.数据控制语言（DCL）**
通过GRANT和REVOKE，确定单个用户或用户组对数据库对象的访问权限。
```
管理用户，授权
	1. 管理用户
		1. 添加用户：
			* 语法：CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';
		2. 删除用户：
			* 语法：DROP USER '用户名'@'主机名';
		3. 修改用户密码：
			
			UPDATE USER SET PASSWORD = PASSWORD('新密码') WHERE USER = '用户名';
			UPDATE USER SET PASSWORD = PASSWORD('abc') WHERE USER = 'lisi';
			
			SET PASSWORD FOR '用户名'@'主机名' = PASSWORD('新密码');
			SET PASSWORD FOR 'root'@'localhost' = PASSWORD('123');
	通配符： % 表示可以在任意主机使用用户登录数据库
			
	mysql中忘记了root用户的密码,前提是有服务器的root权限
				1. cmd -- > net stop mysql 停止mysql服务
					* 需要管理员运行该cmd
				2. 使用无验证方式启动mysql服务： mysqld --skip-grant-tables
				3. 打开新的cmd窗口,直接输入mysql命令，敲回车。就可以登录成功
				4. use mysql;
				5. update user set password = password('你的新密码') where user = 'root';
				6. 关闭两个窗口
				7. 打开任务管理器，手动结束mysqld.exe 的进程
				8. 启动mysql服务
				9. 使用新密码登录。
权限管理：
常用权限有：select,update,alter,delete，create
all可以代表除了grant之外的所有权限
		1. 查询权限：
			-- 查询权限
			SHOW GRANTS FOR '用户名'@'主机名';
			SHOW GRANTS FOR 'lisi'@'%';

		2. 授予权限：
			1.授予权限
			grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';
			-- 给张三用户授予所有权限，在任意数据库任意表上
			GRANT ALL ON *.* TO 'zhangsan'@'localhost';
			
			2.授予列权限
		授予用户bob可以对test.a表的id和name列进行更新
		grant update(id,name) on test.a to ‘bob’@‘localhost’;

		3.授予数据库权限
		授予用户bob可以对test数据库中的所有表进行查询
		grant select on test.* to bob;
		授予jim在test数据库中创建、修改、删除表的权限以及创建视图的权限
		grant create、alter、drop、create view on test.* to jim;
		授予jim可以对当前数据库中的所有表进行查询
		grant select on * to jim; *----表示当前数据库
		授予jim可以创建、修改、删除数据库以及对所有数据库中的所有表进行create、alter和drop
		grant create,alter,drop on . to jim；
		授予jim可以创建新用户
		grant create user on . to jim;
		授予newroot1具有和root@localhost一样的权限
		grant all on . to ‘newroot1’@‘localhost’ with grant option;
		all权限没有给别人赋权的权限，因此要在最后加with grant option
		查看自己的权限：
		show grants;

		权限的传递   
		with grant option子句：通过在grant语句的最后使用该子句，就允许被授权的用户把得到的权限继续授给其它用户    
授予jim对teams表具有references权限，并允许他把权限授给其它用户
grant references on teams to jim with grant option;

3. 撤销权限：
			-- 撤销权限：
			revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';
			REVOKE UPDATE ON db.dept FROM 'user'@'%';	
4. 删除用户
 　　mysql>Delete FROM user Where User='test' and Host='localhost';
 　　mysql>flush privileges;
		删除账户及权限：
drop user 用户名@'%';
drop user 用户名@ localhost; 
````
### **5.事务处理语言（DPL）**
事务处理语句能确保被DML语句影响的表的所有行及时得以更新。TPL语句包括BEGIN TRANSACTION、COMMIT和ROLLBACK。

```
1. 事务的基本介绍
概念：
		*  事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。	
操作：
		1. 开启事务： start transaction;
		2. 回滚：rollback;
		3. 提交：commit;
事务的四大特征：
	1. 原子性：是不可分割的最小操作单位，要么同时成功，要么同时失败。
	2. 持久性：当事务提交或回滚后，数据库会持久化的保存数据。
	3. 隔离性：多个事务之间。相互独立。
	4. 一致性：事务操作前后，数据总量不变
3. 事务的隔离级别
	* 概念：多个事务之间隔离的，相互独立的。但是如果多个事务操作同一批数据，则会引发一些			问题，设置不同的隔离级别就可以解决这些问题。
	* 存在问题：
		1. 脏读：一个事务，读取到另一个事务中没有提交的数据
		2. 不可重复读(虚读)：在同一个事务中，两次读取到的数据不一样。
		3. 幻读：事务A 按照一定条件进行数据读取， 期间事务B 插入了相同搜索条件的新数据，事务A再次按照原先条件进行读取时，发现了事务B 新插入的数据 ,查询不到自己的修改。
如果事务A 按一定条件搜索， 期间事务B 删除了符合条件的某一条数据，导致事务A 再次读取时数据少了一条。这种情况归为 不可重复读事务

	* 隔离级别：
		1. read uncommitted：读未提交
			* 产生的问题：脏读、不可重复读、幻读
		2. read committed：读已提交 （Oracle）
			* 产生的问题：不可重复读、幻读
		3. repeatable read：可重复读 （MySQL默认）
			* 产生的问题：幻读
		4. serializable：串行化
			* 可以解决所有的问题

		* 注意：隔离级别从小到大安全性越来越高，但是效率越来越低
		* 数据库查询隔离级别：
			*select @@transaction_isolation;
		MySQL8.0 以前用的是：tx_isolation 现在用是： transaction_isolation
		* 数据库设置隔离级别：
			* set global transaction isolation level  级别字符串;
```
### **6.指针控制语言（CCL）**
它的语句，想DECLARE CURSOR、FETCH INTO和UPDATE WHERE CURRENT用于对一个或多个表单独行的操作。
