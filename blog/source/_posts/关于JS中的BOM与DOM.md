---
title: 关于JS中的BOM与DOM
date: 2020-08-01 21:59:51
tags: 前端
categories: 前端
---

<!-- toc -->

<!--more-->

## BOM简介
Browser Object Model 是浏览器对象模型，浏览器对象模型提供了独立与内容的、可以与浏览器窗口进行互动的对象结构，BOM由多个对象构成，其中代表浏览器窗口的window对象是BOM的顶层对象，其他对象都是该对象的子对象。是JS访问浏览器窗口的一个接口。
## BOM的构成部分
![](https://img-blog.csdnimg.cn/20200720215252144.png)
### 1.window对象
```bash
无需创建，直接使用
1.弹框方法：
 	1.alert();   --警告框： 弹框页面只有一个确定按钮
 	2.confirm(); -- 确认框：弹框页面有一个确定按钮，一个取消按钮
 	3.prompt(); -- 输入框： 弹框页面有一个提示信息和输入框，还有一个确定按钮，一个取消按钮
 
2.打开和关闭窗口的方法：
	1.open();  开启一个新的窗口 ，参数就是 新窗口的url，返回值就是一个新窗口对象
			可以设置第三个参数，设置窗口的宽和高 "width=300px,height=300px"
	2.close(); 关闭窗口，什么窗口对象调用该方法，就关闭什么窗口
	
3.定时器方法
   1.setTimeOut(参数1，参数2):一次性定时器，
   			参数1：js代码或函数名  参数2：毫秒数
   			返回值 就是当前这个定时器的编号id
   2.clearTimeOut(参数); 取消一次性定时器
   			参数：某个一次性定时器的返回编号id
   			
   3.setInterval(参数1，参数2): 循环定时器
  			参数1：js代码或函数名  参数2：毫秒数
  			返回值 就是当前这个定时器的编号id
   4.clearInterval(参数); 取消循环定时器	
   			参数：某个循环定时器的返回编号id
   			
注意： setInterval(fun1,3000);			
	  setInterval("fun1()",3000);////全局作用域下正常执行
      
4.获取子对象
	window.document;  -- dom
	window.location;  -- bom	地址栏对象
	window.history;   -- bom	浏览记录对象
	window.navigator; -- bom	浏览器对象
	window.screen;    -- bom	显示器屏幕对象
	
 // 不允许别人嵌套网页,防盗链
        onload=function(){if(window.self!=top) document.body.style.display="none"};
        onload=function(){if(window.self!=top) top.location=location};
```
### 2.location对象
表示网页的地址栏  
方法：    
	reload(); -- 重新载入当前页面， 刷新网页     
	assign(); -- 载入一个新页面 ，跳转到其它页面 -- 超链接也有该功能    
属性：   
	href -- location.href="url网址"; -- 超链接也有该功能    
	
* 案例--自动跳转首页
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>自动跳转首页</title>
<style>
 p{
	text-align : center;
 }
 
 span{
	color:red;
 }
</style>
</head>
<body>
    <p>
        <span id="time">5</span>秒之后,自动跳转首页...
    </p>

<script>
    //获取倒计时秒数
    var time = document.getElementById("time");
    var second = 5;
    function showTime(){
		second -- ;
        if(second <= 0){
            //时间到了，跳转首页
            location.href = "https://www.baidu.com";
        }
		//把当前秒数放在span标签内容中显示
        time.innerHTML = second;
        
    }
	//设置定时器 -- 每一秒执行一个showTime函数
    setInterval(showTime,1000);
</script>
</body>
</html>
```
### 3.history对象
记录并存储当前窗口的浏览的历史记录
方法：
	history.back();-- 后退一页
	history.forward();-- 前进一页	
	history.go(参数);
		参数：1 相当于forward()
		参数：-1 相当于back()
### screen 对象
screen.availHeight //屏幕实际高度
screen.availWidth //屏幕实际宽度
screen.height //屏幕高度
screen.width //屏幕宽度　
### navigator对象
//判断用户浏览器的类型
console.log(window.navigator.userAgent);
//判断浏览器所在的系统平台
console.log(window.navigator.platform);
## DOM简介
Document Object Model 文档对象模型， 把文档抽象成对象的形式，对象给我们提供了属性和方法用来处理可标记语言，DOM是一种基于树形的结构

 DOM定义了表示和修改文档所需的方法。

DOM对象即为宿主对象，由浏览器厂商定义，用来操作html和xml功能的一类对象的集合，也有人称DOM是对HTML和XML的标准编程接口。

DOM修改不了CSS样式表，只能通过间接的方式修改HTML中的行间样式。
## DOM结构图
![](https://img-blog.csdnimg.cn/20200720225937771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
文档： 一个网页可以称为文档
节点： 网页中所有内容都是节点（标签、 属性、 文本、 注释等）
元素： 网页中的标签
属性： 标签中的属性

DOM经常进行的操作
1. 获取元素
2.  对元素进行操作（设置其属性或调用其方法）
3. 动态创建元素
4. 事件（什么时候做相应的操作）
## 三、DOM常用对象的使用
### 1.Document文档对象
1. 直接访问文档中的节点对象：
```
document.getElementsById("id"); 通过 id 属性获取节点对象
document.getElementsByName("name"); 通过 name 属性获取节点对象
document/element.getElementsByTagName("tag"); 通过 tag 属性获取节点对象
document/element.getElementsByClassName("class1[ class2]"); 通过 class 属性获取节点对象
document/element.querySelector("selector"); 通过 css 选择器 获取首个节点对象
document/element.querySelectorAll("selector"); 通过 css 选择器 获取所有节点对象
```
其中第一个最常用。
2. 改变属性值 / 改变文本内容

img --> src
h1 -->innerHTML
### node节点对象
```
父节点(parent node)

parentNode/parentElement 获取所属父节点对象
ownerDocument 获取节点所属文档节点（根节点）对象

兄弟节点(sibling nodes)

nextSibling 获取前一个兄弟节点对象
nextElementSibling 获取前一个兄弟元素节点对象
previousSibling 获取后一个兄弟节点对象
previousElementSibling 获取后一个兄弟元素节点对象

子节点(child nodes)

childNodes 获取所有子节点对象
children 获取所有子元素节点对象
hasChildNodes(); 判断是否包含子节点对象
childElementCount 获取子元素节点对象数量
firstChild 获取第一个子节点对象
firstElementChild 获取第一个子元素节点对象
lastChild 获取最后一个子节点对象
lastElementChild 获取最后一个子元素节点对象
```
操作节点对象

```
创建节点

document.createElement("TAG"); 创建一个元素节点
document.createTextNode("#文本"); 创建一个文本节点
document.createComment("#注释"); 创建一个注释节点
document.createDocumentFragment(); 创建一个空白的文档片段节点

插入节点

parentNode.appendChild(newChild); 插入子节点（作为最后一个子节点）
parentNode.insertBefore(newChild,refChild); 插入子节点（在指定子节点之前）

删除节点

parentNode.removeChild(oldChild); 删除指定的子节点

替换节点

parentNode.replaceChild(newChild,oldChild); 替换一个子节点

克隆节点

node.cloneNode(deep); 克隆一个节点
```
### element对象
```
1.设置属性：
    1.setAttribute(参数1，参数2); --给某个元素设置某个属性，参数1：属性名  参数2：属性值
    2.removeAttribute(参数);     --移除元素的某个属性，参数：属性名

e.特性名称="value"; 获取/设置特性值
e.getAttributeNames()； 获取已设置的全部属性名称（非IE内核）
e.getAttribute("特性名称"); 获取特性值
e.getAttributeNode("特性名称"); 获取特性节点[特性名称=特性值]
e.setAttribute("特性名称","特性值"); 设置特性值（不存在时新建该属性）

2.操作节点对象的样式属性

e.style.color[ ="颜色值"]; 获取/设置标签内样式（内嵌样式）
window.getComputedStyle(e).color; 获取包括内嵌样式、<style>、<link>在内的最终样式（非IE内核 或 IE>8）
e.currentStyle.color; 获取包括内嵌样式、<style>、<link>在内的最终样式（IE内核）

提前在head标签中定义style标签，将固定的样式显示写好。
  在去获取某个元素对象，元素对象.calssName="样式名";
```
##  常用事件
```
1. 点击事件：
		1. onclick：单击事件
		2. ondblclick：双击事件
	2. 焦点事件
		1. onblur：失去焦点
		2. onfocus:元素获得焦点。

	3. 加载事件：
		1. onload：一张页面或一幅图像完成加载。

	4. 鼠标事件：
		1. onmousedown	鼠标按钮被按下。
		2. onmouseup	鼠标按键被松开。
		3. onmousemove	鼠标被移动。
		4. onmouseover	鼠标移到某元素之上。
		5. onmouseout	鼠标从某元素移开。
	5. 键盘事件：
		1. onkeydown	某个键盘按键被按下。	
		2. onkeyup		某个键盘按键被松开。
		3. onkeypress	某个键盘按键被按下并松开。
	6. 选择和改变
		1. onchange	域的内容被改变。
		2. onselect	文本被选中。
	7. 表单事件：
		1. onsubmit	确认按钮被点击。
		2. onreset	重置按钮被点击。
```
最后演示一个实例：动态表格添加和删除功能

```js
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>动态表格</title>
    <style>
        table{
            border: 1px solid;
            width: 600px;
            border-collapse: collapse;
            margin: auto;
        }
        th,td,tr{
            text-align: center;
            padding: 15px;
            border:1px solid;
        }
        div{
            text-align: center;
            margin: 70px;
        }
    </style>
</head>
<body>
<div>
    <input type="text" id="id" value="" placeholder="请输入编号">
    <input type="text" id="name" value="" placeholder="请输入姓名">
    <input type="text" id="gender" value="" placeholder="请输入性别">
    <input type="button" value="添加" id="add">
</div>
<div>
    <table id="table">
        <caption>学生信息表</caption>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>操作</th>
        </tr>
        <tr>
            <td>1</td>
            <td>令狐冲</td>
            <td>男</td>
            <td><a href="#" onclick="del(this)">删除</a></td>
        </tr>
        <tr>
            <td>2</td>
            <td>任我行</td>
            <td>男</td>
            <td><a href="javascript:void(0)" onclick="del(this)">删除</a></td>
        </tr>
        <tr>
            <td>3</td>
            <td>岳不群</td>
            <td>?</td>
            <td><a href="javascript:void(0)" onclick="del()">删除</a></td>
        </tr>
    </table>
</div>
<script>
    var table = document.getElementById("table");
    //1.给添加按钮绑定点击事件
    document.getElementById("add").onclick=function () {
        //2.获取编号值
        var id = document.getElementById("id").value;
        var name=document.getElementById('name').value;
        var gender=document.getElementById('gender').value;
        //3.创建一个文本节点
        var text = document.createTextNode(id);
        var text1 = document.createTextNode(name);
        var text2 = document.createTextNode(gender);
        //4.创建一个td元素对象
        var td = document.createElement("td");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("a");
        td3.text="删除";
         td3.href="#";
         td3.onclick=del1;
        // a.setAttribute("onclick","delTr(this)");
        //5.创建一个tr元素对象
        var tr = document.createElement("tr");
        //6.将text放进td中，td放进tr中，tr放进table中
        td.appendChild(text);
        td1.appendChild(text1);
        td2.appendChild(text2);
        tr.appendChild(td);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        table.appendChild(tr);
    }
    //删除 table.removeChild(tr);
    function del() {
        var tr=this.parentNode.parentNode;
        var table=this.parentNode.parentNode.parentNode;
        table.removeChild(tr);
    }
    function del1() {
        var tr=this.parentNode;
        var table=this.parentNode.parentNode;
        table.removeChild(tr);
    }
</script>
</body>
</html>
```
上面这种方法有点复杂了，还可以用：
```
<div>
    <input type="text" id="id" placeholder="请输入编号">
    <input type="text" id="name"  placeholder="请输入姓名">
    <input type="text" id="gender"  placeholder="请输入性别">
    <input type="button" value="添加" id="btn_add">
</div>
<table id="table">
    <caption>学生信息表</caption>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>操作</th>
    </tr>

    <tr>
        <td>1</td>
        <td>小明</td>
        <td>男</td>
        <td><a href="javascript:void(0);" onclick="delTr(this);">删除</a></td>
    </tr>
</table>

<script>
    //使用innerHTML添加
    document.getElementById("btn_add").onclick = function() {
        //2.获取文本框的内容
        var id = document.getElementById("id").value;
        var name = document.getElementById("name").value;
        var gender = document.getElementById("gender").value;

        var table=document.getElementById("table");
        var count=table.rows.length;
        var str=table.insertRow(count);
        str.innerHTML='<td>'+id+'</td><td>'+name+'</td><td>'+gender+'</td><td><a href="javascript:void(0)" onclick="delTr(this)">delete</a></td>'
    }
    //删除方法
    function delTr(obj){
        var table = obj.parentNode.parentNode.parentNode;
        var tr = obj.parentNode.parentNode;
        table.removeChild(tr);
    }
    </script>
```