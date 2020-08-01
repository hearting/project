---
title: JavaEE关于Jquery必须会的基础使用
date: 2020-08-01 22:14:09
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

## Jquery基础
jQuery是一个快速，小型且功能丰富的JavaScript库。借助易于使用的API（可在多种浏览器中使用），它使HTML文档的遍历和操纵，事件处理，动画和Ajax等事情变得更加简单,极大的简化了js的dom和bom操作。
接下来我们看看JQuery的基础使用
### 引入jq插件三种方式
1.使用相对路径
>\<script src="../js/jquery-3.3.1.min.js"></script>
2. /ajax为服务器根目录如下
>\<script src="/ajax/js/jquery-3.3.1.min.js"></script>
3. 使用${pageContext.request.contextPath}表示服务器路径，推荐使用这种，当服务器路径变化时，不需要改变。
> \<script src="${pageContext.request.contextPath}/js/jquery-3.5.0.min.js"></script>
### 1.jquery选择器
1. id

2. class

3. 标签
 ```
 <body>
<p id="p1" class="p1">好好学习，天天向上</p>
<script>
    //js方式
    var p1=document.getElementById("p1")
    console.log(p1.innerHTML);
    //jq方式--id选择器
    var ptext=$("#p1").html()
    console.log(ptext)
    //jq方式--class选择器
    console.log($('.p1').html())
    // jq方式--标签选择器
    console.log($('p').html())
</script>
</body>
 ```
### 2.实现dom操作
html(): 获取/设置元素的标签体内容 相当于 js:innerHTML
text(): 获取/设置元素的标签体纯文本内容  相当于 js:innerText
val() : 获取/设置元素的value属性值  相当于 js:value
实例代码
```
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-3.5.0.min.js"></script>
</head>
<body>
<p id="p1"><span>我们要</span>好好学习天天向上</p>
<input type="text" value="qwer" name="name" id="name">
</body>
<script>
    // html(): 获取/设置元素的标签体内容
    //js方式
    var p1=document.getElementById("p1");
    alert(p1.innerHTML)
//  jq方式
    var p=$('#p1').html();
    console.log(p);
    //text(): 获取/设置元素的标签体纯文本内容
    // js方式
    alert(p1.innerText)
//jq方式
    var p=$('#p1').text();
    console.log(p);

    // val() : 获取/设置元素的value属性值
    // js方式
    var input=document.getElementById("name")
    console.log(input.value)
    //jq方式
    console.log($('input').val())
</script>
</html>
```
### 3.基本事件
1.click 点击事件
2.blur 失去焦点事件
3.change 当值改变触发事件
实例代码

```
<html>
<head>
    <title>事件演示</title>
    <script src="/ajax/js/jquery-3.5.0.min.js"></script>
</head>
<body>
<input type="button" value="确定" id="butn">
<input type="text" value="确定" id="in">
<select name="se" id="se">
    <option value="0">成熟</option>
    <option value="1">青涩</option>
    <option value="2">单纯</option>
    <option value="3">轻浮</option>
    <option value="4">激情</option>
</select>
</body>
<script>
    // 点击事件
    //js实现
    document.getElementById("butn").onclick=function () {
        console.log('我被点了一下')
    }
    // jq实现
    $('#butn').click(function () {
        console.log('我被点了一下')
    })
    //2.失去焦点事件
    $('#in').blur(function () {
console.log(this.value);//js实现
        console.log($(this).val())
    })
//change事件
    $('#se').change(function () {
    console.log($(this).val())
        console.log(this.value)
        console.log($('#se>option:selected').text()) //jq
    })
</script>
</html>
```
## Jquery实现ajax
### 1.ajax请求
$.get()  
$.post()  

**异步请求get的4个参数：
    1.url 请求路径，访问服务器servlet的路径
    2.请求参数，发送到服务器的参数
    3.回调函数，接收并处理服务器响应的数据的函数
    4.希望服务器响应的数据格式，通常有 text(字符串)  ，json （键值对形式的字符串)**

以下需求示例代码：  
姓名输入框失去焦点的瞬间，就自动携带name参数，去后台查找，是否名称冲突，而不是点击了注册后才提交整个表单，此处只携带一个参数去后台，表单并没有提交

实现步骤：
    1.姓名输入框绑定一个失去焦点事件
    2.失去焦点事件实现函数中，发送异步请求，携带参数name和参数值去后台servlet
    3.在servlet中： 接收参数name ,判断是否重名 ，给出不同的反馈，响应给浏览器客户端不同的值
    4.客户端接收到servlet的反馈信息后，在姓名输入框的后面显示出来

```
<head>
    <title>ajax</title>
    <%--动态上下文路径--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" ></script>
</head>
<body>

    <form action="">
        姓名：<input type="text" name="name" value="" id="name"><span id="sp"></span><br>
        密码：<input type="password" name="psw"><br>
        <input type="submit" value="注册">
    </form>
</body>
<script>
$('#name').blur(function () {
    $.get('/ajax/ajax',{'name':$(this).val()},function (msg) {
        console.log(msg)
        $('#sp').html(msg);
    },"text");
})

</script>
```
servlet代码
```
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
  
    //异步请求处理中，不做页面跳转，只是用response对象响应数据即可
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("异步请求进来啦");
        //1.获取异步请求发送的参数
        String name = request.getParameter("name");
        System.out.println(name);
        //2.判断姓名是否冲突--简化业务：和 null比较
        response.setContentType("text/html;charset=utf-8");
        if(name.equals("null")){
            //3.响应不同的数据 -- 处理响应中文乱码问题
            response.getWriter().write("用户名冲突！");
        }else {
            response.getWriter().write("用户名正常！");
        }
    }
}
```
### 2.遍历
$.each()
示例代码：
```
<head>
    <title>遍历</title>
    <%--动态上下文路径--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" ></script>
</head>
<body>

</body>
<script>
    //1.创建一个数组
    var arr=[1,2,3,'abc'];
    //2.遍历
    $.each(arr,function (i,e) {
        console.log(i+"--"+e)
    });
//i - 选择器的 index 位置
//e - 当前的元素（也可使用 "this" 选择器）
</script>
```
