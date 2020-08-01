---
title: JavaWeb基础之JSP
date: 2020-08-01 22:04:57
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

## JSP简介
JSP—Java Server Page。JSP相当于模板引擎，简化开发，封装servlet响应html标签的代码，从用户角度看其实就是就是一个网页；从程序员角度看待，他其实就是一个Java类。它继承了servlet，所以可以说jsp就是一个特别的servlet。
## 三大指令
### 指令写法
<%@ 指令名字 %>
### 程序脚本
```bash
<%-- jsp注释 --%>  该注释中的java脚本不会被执行
<!-- html注释-->   该注释中的java脚本会被执行，但是执行结果会被注释
<% %>叫做脚本片段，其中写的内容会翻译在Servlet的Service方法中，显然我们可以在Service方法中定义局部变量或者调用其他方法，但是不能在Service中再定义其他的方法，也就是我们可以在<%%>中定义局部变量或者调用方法，但不能定义方法。在jsp页面可以有多个脚本片段，但是多
个脚本片段之间要保证结构完整。
<%!%>称作声明，其中写的内容将来会直接翻译在Servlet类中，因为我们可以在类中定义方法和属性以及全局变量，所以我们可以在<%!%>中声明方法、属性、全局变量。
<%=%>称作jsp表达式，用于将已经声明的变量或者表达式输出到网页上面。
```
### page指令
用于声明jsp :  jsp=html+page指令
1. contentType="text/html;charset=UTF-8" 指定jsp文件的类型和字符集 
2. import="java.util.*" 为jsp的脚本中的Java代码去导包
3. errorPage="500.jsp" 如果当前页面发生异常，就跳转到500.jsp中
4. isErrorPage="true" 承认自己是不是错误处理页面，如果为true就可以使用exception内置对象

		<%@ page contentType="text/html;charset=UTF-8" language="java" %>

### include指令
静态包含，一个jsp页面可以包含另一个jsp页面，会被tomcat服务器编译成一个servlet,可以实现脚本互通。

	<%@include file="b.jsp"%>

### taglib指令
<%@ taglib prefix="" uri =""%>   
uri：标签库路径   
prefix：标签库的别名  
## JSP动作标签
```
<jsp:include page=""></jsp:include>

<jsp:param value=“”value=""/>

<jsp:forward page=""></jsp:forward>

jsp:include
<jsp:include page="other.jsp"></jsp:include>包含指定的页面，这里是动态包含。也就是不把包含的页面的所有元素标签全部拿出来输出。而是把它的运行结果拿过来。
jsp:forward
<jsp:forward page=""></jsp:forward>请求转发：request.getRequestDispatcher("other.jsp").forward(request,response);
jsp:param意思是：在包含某个页面的时候，或者在跳转某个页面的时候，加入这个参数。
<jsp:forward page="other.jsp"><jsp:param value="beijing" name ="address"></jsp:forward>
```
## 九个内置对象
1.域对象(从小到大)
	pageContext  (只能在当前页面使用)    
	request   （仅限于转发之间的页面不支持int类型，都用string类型）
	session   （用于一次会话，浏览器和服务器都不关闭称为一次会话）
	application  （范围最大，只要服务器不管，浏览器随意）
	
2.其它
	**response**   
	客户端的请求信息被封装在request对象中，通过它才能了解到客户的需求，然后做出响应。
	**out**   
	out对象是JspWriter类的实例,是向客户端输出内容常用的对象
<%=%>也表示输出
**page**   
page对象就是指向当前JSP页面本身，有点象类中的this指针
**exception**     
exception对象是一个例外对象，当一个页面在运行过程中发生了例外，就产生这个对象。
**config**   
config对象是在一个Servlet初始化时，JSP引擎向它传递信息用的，此信息包括Servlet初始化时所要用到的参数（通过属性名和属性值构成）以及服务器的有关信息（通过传递一个ServletContext对象）

```bash
使用域对象来存储数据
<%
pageContext.setAttribute("name", "page");
request.setAttribute("name", "request");
session.setAttribute("name", "session");
application.setAttribute("name", "application");
%>

取出四个域对象中的值<br>
<%=pageContext.getAttribute("name")%>
<%=request.getAttribute("name")%>
<%=session.getAttribute("name")%>
<%=application.getAttribute("name")%>
```
servlet三大域对象：request,session,applicatioon(servletContext)
 jsp中四大域对象：pageContext,request,session,applicatioon
 ## JavaWebMVC设计示意图
 mvc  -- 一种软件设计典范（取调存转） ![](https://img-blog.csdnimg.cn/20200723174051611.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
