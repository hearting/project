---
title: HTML快速了解入门教程
date: 2020-08-01 21:51:14
tags: 前端
categories: 前端
---

<!-- toc -->

<!--more-->

## 概念
全称：Hyper Text Markup Language 超文本标记语言
     超文本:
     	* 包含超链接 
     	* 超越了普通文本 ，除了有文字，还有图片，视频等
     标记：每一个元素都是被<>包裹   
     
语法特征
```
1.文件后缀名 .html  .htm
2.标签的分类：
	*围堵标签(双标签)   <font>文本</font> 具备页面展示功能
	*自闭合标签(单标签)  <br/> 换行的功能，不具备页面展示功能
3. 可以在开始标签中定义属性，属性名和属性值就是一个键值对，属性值必须用引号引起来	
4.标签大小写都可以，建议小写
```
转义字符
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200720004055751.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

## html基本结构
```
1、<!DOCTYPE>
<!DOCTYPE>位于文档的最前面，用于向浏览器说明当前.html文件使用的是哪种HTML或者XHTML标准规范。浏览器会按照此处指定的规范对html文件进行解析。
HTML5可以向下兼容，所以，现在直接指定为<!DOCTYPE html>即可。

2. html标签：就是告诉浏览器这是一个网页文件

3、charset（字符编码集）
GB2312：简体中文字符集，含6763个常用汉字
BIG5：繁体中文，港澳台地区使用
GBK：含全部中文字符，是对GB2312的扩展，支持繁体字
UTF-8：支持中文和英文等，是最常用的字符集

  head标签:用于网站添加一些配置信息
            指定网站的标题/指定网站的小图片
            添加网站SEO相关的信息(指定网站的关键字/指定网站的描述信息)
             外挂一些外部的css/js文件
            添加一些浏览器适配相关的内容
  一般情况下，写在head标签额内部的内容都不会显示给用户看(除标题外)
  
 title标签:就是用来定义网页的名字的
          必须写在head标签的里面
          
 body标签:就是定义你需要让用户查看到的内容
           虽然说有时候将内容写到了别的地方网页中也能看到，但是千万不要这么干，一定要将需要显示的内容写在body标签中
```
### 标签类别
|标签类别	|标签汇总  |
|--|--|
|单标签|\<br>、\<hr>、\<img>、\<input>、\<param>、\<meta>、\<link>|
| 双标签|	\<html>、\<head>、\<title>、\<body>、\<table>、\<tr>、\<td>、\<span>、\<p>、\<form>、\<h1>、\<h2>、\<h3>、\<h4>、\<h5>、\<h6>、\<object>、\<style>、\<b>、\<u>、\<strong>、\<i>、\<div>、\<a>、\<script>、\<center>|  |

## HTML标签
### 文本标签
```
1. <!--注释-->     idea中的快捷键是:ctr+shift+/ 
2. h1~h6  标题标签 依次变小，加粗，黑体，换行
3. <br/> 换行标签
4. <p>文章段落</p>  换行，段落间空一行
5. <hr/>水平线
        - color:颜色 
        - width:宽度, 单位px
        - size :高度，单位px
        - align:center,left,right 对齐方式
6. <b> 加粗
7. <i> 斜体
8. <font> 文本
        - color:颜色
        - size:大小 ，范围 1-7 ，从小到大
        - face:字体
9. <center> 居中
10.<pre>内容</pre>	预格式化。就是你敲在源码里是什么格式。在页面上就显示什么格式。一般跟code配合使用。
11.<code>hello world<code>	表示此段是程序代码
12.<del>内容</del>	在字符上带一条删除线。 一般用于价格  的打折 把原价划掉  下面出来一个新价格
13.<ins>内容</ins>	表示插入的字符，表示方式，带下划线
```
### 图片标签

```
1.src  图片的路径：相对路径的寻找：从当前文件出发
    上级目录：../
    同级和下级目录：直接写文件夹或文件名
2.alt 替代文字信息,图片不能显示时出现
3.width:宽度
4.height：高度
5.align：对齐方式 left/right 左对齐/右对齐
-->
<img src="../image/c.jpg" alt="这里是刘亦菲" width="500px" height="400px" align="right"/>
```
### 列表标签
```
<h3>有序列表</h3>
<!--order list
 type: 1 A  a  i I
 start: 开始的下标
 -->
<ol type="I" start="I">
    <li>吃早餐</li>
    <li>上课</li>
    <li>下课</li>
    <li>放学</li>
    <li>吃饭</li>
    <li>睡觉</li>
</ol>

<h3>无序列表</h3>
<!--
unorder list
type:disc,square,circle
-->
<ul type="circle" >
    <li>吃早餐</li>
    <li>上课</li>
    <li>下课</li>
    <li>放学</li>
    <li>吃饭</li>
    <li>睡觉</li>
</ul>

<h3>自定义列表</h3>
<!--define list-->
<dl>
    <dt>娱乐</dt>
    <dd>游戏</dd>
    <dd>K歌</dd>
    <dd>电影</dd>
    <dd>逛街</dd>
</dl>
```
### 超链接标签
```
<!--
1.href:点击跳转的资源路径
  * 跳转到互联网资源
  * 跳转到本地资源
如果当时没有确定链接目标时，可以为 href 赋值 为 “#” ,即 href="#",表示一个空连接
2.target:
  * _self 当前窗口打开
  * _blank  新开一个窗口打开
  *_parent
这个目标使得文档载入父窗口或者包含来超链接引用的框架的框架集。如果这个引用是在窗口或者在顶级框架中，那么它与目标 _self 等效。
  *_top
这个目标使得文档载入包含这个超链接的窗口，用 _top 目标将会清除所有被包含的框架并将文档载入整个浏览器窗口。

<base> 标签可以限定同一页面内所有 超链接 的打开方式。<base target="_blank">
-->
<a href="http://www.baidu.com" target="_self">点我--访问互联网资源</a> <br>
<a href="../image/b.jpg" target="_blank">点我看刘亦菲--访问本地工程中资源</a>
```
通过创建锚点，可以快速定位到目标内容区域
创建锚点分为两步
为目标内容（即锚点）创建id 并赋值
将超链接文本与锚点的id 关联，

		<a href="#id名称"> 超链接文本 </a>
		<h3 id="name">创建锚点分为两步</h3>
### 表格标签
```
<!--
<caption>我的标题</caption>  标题
border="1"  边框,单位是像素px
cellspacing="0"   单元格外边距，通常设置为0，用于消除td之间的间距
cellpadding="10"  单元格内边距：单元格的边框和内部文本的距离，通常设置为10，可以撑大表格，显示清晰美观
align="center"    
	1.作用在table标签上：整个表格在对窗口居中显示
	2.作用在tr标签上：表示当前行的文本内容在单元格中居中显示
表头使用th :具有 加粗 和 文本内容居中显示的功能
-->

<!--跨行
1.从上到下
2.第一个单元格保留，剩下的删除
3.给第一个单元格一个属性 rowspan="3" 包含自己在内的总行数
-->

<!--跨列
1.从左到右
2.第一个单元格保留，剩下的删除
3.给第一个单元格一个属性 colspan="4" 包含自己在内的总列数
-->

<table border="1" cellspacing="0" cellpadding="10" align="center" bgcolor="#7fff00">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
    </tr>
    <tr align="center">
        <td>1</td>
        <td>小龙女</td>
        <td>36</td>
    </tr>
    <tr>
        <td>2</td>
        <td>杨过</td>
        <td>18</td>
    </tr>
</table>
```
### 表单
#### form 表单标签
~~~powershell
1.范围：数据提交到服务器的范围
2.属性：
	action：url 数据提交网址
	method：数据提交方式 get/post		
~~~
- 如何提交数据
~~~powershell
1.表单项标签  input
2.input元素必须要有name属性才能提交数据
表单项标签中的name和value属性值构成一个参数键值对，在提交的时候，会发送到服务器
~~~
- get和post的区别
~~~powershell
1.get请求方式会把提交的数据显示在浏览器的地址栏，post不会显示
2.get方式提交的数据有大小和长度的限制，post没有
3.get不安全，post安全
~~~
#### 表单项标签
- input标签的type属性值
~~~powershell
1.text 文本输入框
2.password 密码输入框
3.radio 单选
3.checkbox 复选框
4.file 文件引入按钮
5.submit提交按钮
6.button 普通按钮，本身不具备任何功能
7.image 图片提交
8.hidden 隐藏域 提交默认的数据
扩展：
9.color 取色器
10.date 日期
11.dateTime_local 日期和时间
12.email 邮箱，有@校验功能
13.number 数字

1.以上所有的表单项都应该有name和value属性，
	radio和checkbox必须要有name和value
	text和password可以省略value
	
2.text和password还有一个placeholder输入提示属性，不会对输入的值造成干扰

3.radio和checkbox 要实现单选和复选，name属性值必须一致 ，checked 关键字表示默认选中
~~~
- label标签
~~~powershell
1.for属性的属性值和input的id一致，可以实现点击lable,光标在input中闪烁
~~~
- textarea
~~~powershell
文本域：多行多列的输入框
rows:行数
cols:列数，每一行可以写的字符数
~~~
- select 下拉框
~~~powershell
1.option 子标签 
2.name属性在select中，value属性在option中
~~~
### div和span
```
1.div和span
	- div 是一个容器，用于包裹其它的标签
      是块积元素：占满一行，自带换行

	- span 文本标签，用于包裹少量文字信息   
	   是内联元素：不会换行
	   span是行内元素：没有宽和高属性，高度是固定的，实际的宽度取决于元素中的内容，不会自动换行
div和span都需要借助css才能实现样式调节，他们本身不具备任何样式。

2.header和footer
    <header>   前身：  <div id="header">
    <footer>   前身：  <div id="footer">
是h5新增的语义化标签，用于页面框架布局，增强代码可读性

```