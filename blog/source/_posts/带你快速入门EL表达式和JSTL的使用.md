---
title: 带你快速入门EL表达式和JSTL的使用
date: 2020-08-01 22:06:56
tags: Java 
categories: Java
---

<!-- toc -->

<!--more-->

## EL表达式
### 简介
Expression Language 表达式语言，为了使JSP写起来更加简单。表达式语言的灵感来自于 ECMAScript 和 XPath 表达式语言，它提供了在 JSP 中简化表达式的方法，让Jsp的代码更加简化。
### 语法：${表达式}
忽略el,不会解析表达式，原因：
  - page指令中定义 isElIgnored="true" 
 - \\${表达式}
 
### 功能：

* 简单的运算
* 取出域对象中的参数并在页面展示
* 
### 运算符
~~~powershell
1.算术  + - * /(div)  %(mod)
2.比较  >  <  >=  <=  ==  !=
3.逻辑  &&(and)  ||(or)  !(not)
4.empty 判断字符串，数组，集合的对象是否为null,也可以判断长度是否为0
	${empty list} :null--true 
    ${empty ""}  true
    集合：${not empty list}   list!=null&&list.size()!=0
    字符串：${not empty str}    str!=null&&str.length()!=0
    数组：${not empty arr}    arr!=null&&arr.length!=0
~~~
### 内置对象

```bash
EL有11个内置对象，这里主要讲域属性相关的4个
EL的11个内置对象，除了pageContext以外，其他10个内置对象的类型都是java.util.Map类型
```
   <table>
        <tr>
            <th>分类</th>
            <th>内置对象名称</th>
            <th>描述</th>
        </tr>
        <tr>
            <td rowspan="4">域对象</td>
            <td>pageScope</td>
            <td>page作用域</td>
        </tr>
        <tr>
            <td>requestScope</td>
            <td>request作用域</td>           
        </tr>
        <tr>
            <td>sessionScope</td>
            <td>session作用域</td>           
        </tr>
        <tr>
            <td>applicationScope</td>
            <td>application作用域</td>           
        </tr>
        <tr>
            <td rowspan="2">请求参数</td>
            <td>param</td>
            <td>用于获取请求参数的值，应用在参数值只有一个的情况。在应用param隐含对象时，返回结果为字符串。</td>           
        </tr>
        <tr>
            <td>paramValues</td>
            <td>用于当请求参数名对应多个值时获取参数的结果，在应用paramValues隐含对象时，返回结果为数组。</td>           
        </tr>
        <tr>
            <td rowspan="2">请求头</td>
            <td>header</td>
            <td>用于获取HTTP请求的一个具体的header的值。</td>           
        </tr>
        <tr>
            <td>headerValues</td>
            <td>用于获取HTTP请求的一个具体的header的值，但是在有些情况下，可能存在同一个header拥有多个不同的值的情况，这时就必须使用headerValues隐含对象。</td>           
        </tr>
        <tr>
            <td>JSP上下文对象</td>
            <td>pageContext</td>
            <td>页面上下文对象为pageContext，用于访问JSP内置对象（例如request、response、out、session、exception和page等，但不能用于获取application、config和pageContext对象）和servletContext。在获取到这些内置对象后，就可以获取其属性值。</td>           
        </tr>
        <tr>
        <td>全局初始化对象</td>
        <td>initParam</td>
        <td>用于获取Web应用初始化参数的值。</td>       
        </tr>
        <tr>
            <td>cookie</td>
            <td>cookie</td>
            <td>用于获取cookie对象，如果在cookie中已经设置一个名为username的值，那么可以使用 ${cookie.username} 来获取该cookie对象。但是如果要获取cookie中的值，需要使用cookie对象的value属性。</td>
        </tr>
    </table>

- 获取域对象中的参数
~~~powershell
*   1.pageScope
    2.requestScope
    3.sessionScope
    4.applicationScope
* 取域中的数据
    1. ${内置域对象.key}
    2. ${key} 在四个域对象中从小到大的范围依次查找，如果没有数据，只显示空字符串，不会显示null
~~~
- 获取域对象中的对象的值

~~~powershell
1.域中存储的是javabean对象
	${域名称.键值.对象属性名}
2.域中存储的是list集合
	${域名称.键值[索引]}
3.域中存储的是map集合
	${域名称.键值.key} 或 ${域名称.键值["key"]}
4.域中存储的是数组
	${域名称.键值[索引]}
~~~
实例代码：

```bash
//    案例一
    Emps emps=new Emps();
    emps.setName("孙权");
    emps.setAge(36);
    Elephant elephant=new Elephant();
    elephant.setName("东东");
    elephant.setAge(18);
    emps.setElephant(elephant);
    pageContext.setAttribute("emps",emps);

//    案例二
    ArrayList<Emps> list=new ArrayList<>();
    list.add(emps);
    pageContext.setAttribute("list",list);

//    案例三
    HashMap<String,Emps> map=new HashMap<>();
    map.put("three",emps);
    pageContext.setAttribute("map",map);

%>
${pageScope.emps}<br>
${pageScope.emps.name}<br>
${pageScope.emps.age}<br>
${pageScope.emps.elephant}<br>
${pageScope.emps.elephant.name}<br>
${pageScope.emps.elephant.age}<br>

<h2>案例二</h2>
${pageScope.list[0]}
<br>
${pageScope.list.get(0)}

<h2>案例三</h2>
${pageScope.map.three}<br>
${pageScope.map.three.elephant}<br>
${pageScope.map.three.elephant.name}<br>
${pageScope.map['three'].elephant}<br>
${pageScope.map['three'].elephant.age}<br>
```

- 其他重要内置对象
~~~powershell
1.pageContext 获取jsp中其它8个内置对象
	*jsp页面动态获取虚拟路径(网络工程名) ${pageContext.request.contextPath}
	
2.param  ==> String value= request.getParameter("参数名");

3.paramValues ==> String [] values =  request.getParameterValues("参数名");
~~~
## JSTL
### 概念
java Server Pages Tag Library   jsp中的标签库，可以嵌入在jsp页面中使用标签的形式完成业务逻辑等功能。jstl出现的目的同el一样也是要代替jsp页面中的脚本代码。JSTL标准标准标签库有5个子库，但随着发展，目前常使用的是他的核心库。
![](https://img-blog.csdnimg.cn/20200725111509665.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
在使用前需要导入jar包,标准包能在tomcat安装目录找到。
![在这里插入图片描述](https://img-blog.csdnimg.cn/202007251123571.png)
![](https://img-blog.csdnimg.cn/20200725111909230.png)

主要：所有的jar包都要部署在服务器上。文件拷贝到 /WEB-INF/lib/ 下。
><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
- if标签

~~~powershell
1. <c:if test="true">
   </c:if>
   
2. <c:if test="${}">
   </c:if>  
~~~

- choose标签

~~~jsp
<c:set var="m" value="11" scope="page"></c:set>
<c:choose>
    <c:when test="${m>=1 && m<=3}">
        <h3>spring</h3>
    </c:when>
    <c:when test="${m>=4 && m<=6}">
        <h3>summer</h3>
    </c:when>
    <c:when test="${m>=7 && m<=9}">
        <h3>autumn</h3>
    </c:when>
    <c:otherwise>
        <h3>winter</h3>
    </c:otherwise>
</c:choose>
~~~
- foreach标签

~~~jsp
<c:forEach begin="0" end="10" step="1" var="i"> 
<%--会自动将变量i存进pageContext域对象中--%>
    ${pageScope.i}  
    - ${requestScope.i}  
    - - ${sessionScope.i}
    - - ${applicationScope.i} 
    - <br>
</c:forEach>
<%--2.增强for
String [] arr={"abc","haha","hehe"};
for(String s:arr){
   System.out.println(s);
}
--%>

<%
    String [] arr={"abc","haha","hehe"};
    request.setAttribute("arr",arr);
%>
<c:forEach var="s" items="${requestScope.arr}" varStatus="vs" >
<%--会自动将变量s和对象vs 都存进pageContext域对象中--%>
    ${s} 
    -- ${vs.count}
     -- ${vs.index}<br>
</c:forEach>
items属性：使用el从域对象中取出集合或数组
forEach会遍历集合或数组，将每个值赋给var中变量a
var属性：将变量s，存进pageConText域对象中，因此可以用${s}取出域对象的参数值
varStatus=“status”事实上定义了一个status名的对象作为varStatus的绑定值。
该绑定值也就是status封装了当前遍历的状态。
${status.index}      输出行号，从0开始。
${status.count}      输出行号，从1开始。
${status.current}   当前这次迭代的（集合中的）项
${status.first}  判断当前项是否为集合中的第一项，返回值为true或false
${status.last}   判断当前项是否为集合中的最后一项，返回值为true或false
~~~

