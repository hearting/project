---
title: Mybatis中用@Select传参数
date: 2020-08-01 22:17:24
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

一般情况

@select("select * from user where name = #{name}")

List<user>  selectUserByName (String name)

高级用法

@Select("select * from userf where name like CONCAT('%',#{s},'%')")

List<Fkb> selectLikename(String s);

concat拼接字符串

CONCAT('%',#{0},'%')")  不能直接用 ‘%#{s}%’