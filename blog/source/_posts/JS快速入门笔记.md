---
title: JS快速入门笔记
date: 2020-08-01 21:57:39
tags: 前端
categories: 前端
---

<!-- toc -->

<!--more-->

## js简介
1.概念
~~~
1.js是一个脚本语言--不用编译，直接被浏览器解析
2.js可以实现动态的效果，如轮播图，还可以进行数据校验
JAVASript= EcmaScript(基础语法)+JAVASript自身(BOM+DOM)
~~~

## js基础语法
1. js和html的结合方式
~~~
1.内部 js:  写一个script 标签，标签体内容就是js代码
<body>
 <script>            </script>
</body>
2.外部 js: 单独写一个js文件，在head标签中使用script标签引入外部js文件
<head>
    <script src="a.js"></script>
</head>

注意：
1. script标签和html标签的先后顺序，会导致加载的顺序不一样 ，从上到下加载
2. 页面中可以有多个script标签
3. 如果script标签是引入外部js文件，不能简写为单标签，标签体中也不能再写js代码

~~~
2. 注释
~~~
//单行注释
/* 多行注释 */
~~~
3. 数据类型
~~~
1. number:  整数和小数 NaN(非数字)
2. string:  字符串 ，单引号和双引号都可以 'agc' "abc"
3. boolean: true/false
4. null: 空对象
5. undefined：变量没有初始化
~~~
4. 变量
~~~
java是强类型语言：定义一个变量，数据类型一旦声明，就不可以更改，更不可以装其它类型的数据
js是弱类型语言：定义一个变量，数据类型可以随意变化，可以装任意类型的数据
定义变量 
  var 变量名=变量值;
  
typeof(变量名) -- 返回一个原始数据类型
console.log("a是一个数字"); 在浏览器控制台输出 F12按键
document.wirte("hello");在浏览器里输出显示出来
~~~

5.一元运算符

~~~
1. ++ --  +(正号) -(负号)
2. +可以将其它类型转为number类型
	string : 
    	var a="123";  +a 就是number类型
    	var a="abc";  +a 就是 NaN 非数字的类型
    boolean:
        var a=true; +a 输出结果：1
        var a=false; +a 输出结果：0	
~~~
3. 算术运算符&比较运算符
~~~
4. + - * / %   
2.> <  >=  <=  !=  
==判断内容是否相同
===(全等，比较内容和类型)
~~~
5. 逻辑运算符
~~~
&& || ！
! 可以将其它数据类型转换成boolean:
	1.number : 0或NaN --> false; 其它-->true
	2.string : "" --> false  ;其它-->true, " "-->true
	3.null&undefined -->false
	4.对象==null -->false ; 对象!=null -->true
	
基于上面的类型转换，可以简写：
1.死循环 while(1){}
2.非空验证：
	*对象var obj： if(obj){} 相当于 if(obj！=null){}
	*字符串 var str; if(str){} 相当于 if(str！= null && str.length>0){},过滤了null和“”
~~~
6. 三元运算符
~~~
条件判断?表达式1:表达式2; -- 通常可以直接输出，或者给其它变量赋值
~~~
7. 特殊语法(归功于浏览器解析功能强大)

~~~
8. 每一条语句后的分号，可以省略，前提是 一行只有一条语句
9. 定义变量可以省略var关键字:相当于定义了一个全局变量
10. 变量名虽然可以相同，但是后者会覆盖前者
~~~

11. 流程控制语句
~~~
12. if...else...
13. switch:
	* 在java中，switch语句可以接受的数据类型： byte int shor char,枚举(1.5) ,String(1.7)
	* switch(变量):
			case 值:
	* 在JS中,switch语句可以接受任意的原始数据类型
14. while
15. do...while
16. for  增强：for of    for in 

    var t=[1,2,3,'a','b'];
    for (var i=0;i<t.length;i++){
        console.log(t[i]);
    }
    for(var i of t){
        console.log(i);
    }
    for(var i in t){
        console.log(t[i]);
    }
 
~~~

## js对象

### 1.Function 对象

1. 函数的三种定义方式以及调用

~~~js
    // 1.最常用的方式
    function fun1() {
        var a=6
        alert(a);
    }
   fun1(); //调用

    //2.匿名函数--搭配js事件
    var fun2=function(){
        alert(1);
    };
    fun2();

    //3.动态函数
    var fun3=new Function("a","alert(a)");
    fun3(1);
js中没有函数的重载，后面的重名函数会覆盖前面的，和参数构造无关。
~~~
2. 形参和实参个数关系 ，length
~~~javascript
 //形参的定义：直接写形参名
    function fun1(a,b) {
        console.log(a+b);
    }
 //实参个数可以不和形参个数相同
    fun1();//NaN
    fun1(3);//NaN
    fun1(3,4);//7
    fun1(3,4,"abc");//7

 //length属性：形参的个数
 console.log(fun1.length);//2
~~~
为了解决重载函数引入了实参数组arguments
3. 实参数组arguments
~~~javascript
function fun1() {
        //arguments实参数组：接收所有的实参，放在该数组中存起来
        var sum=0;
        for (var i = 0; i <arguments.length ; i++) {
            sum+=arguments[i];
        }
        console.log(sum);
}
   //调用函数
    fun1(3,4,+"12",5);//24
    fun1(3,4,"12",5);//7125
    fun1(3,4,true,5);//13
    fun1(3,4,+true,5);//13
~~~
### 2.Array对象
1. 三种定义方式
~~~
2. var arr=new Array(1,2,"abc"); -- (数组的元素)
3. var arr=new Array(5); -- (数组的长度)
4. var arr=[1,3,6,9,"a"];
参数是一个数表示初始长度，参数大于一个，表示初始值
~~~
2. 数组的特点
~~~
1.长度随意
2.类型随意
3.参数是一个数表示初始长度，参数大于一个，表示初始值
~~~
3. 数组对象的方法
~~~
8. join -- 连接  ,如： arr.join("--");  参数表示元素之间的连接符号，默认逗号隔开
9. push -- 添加元素 ，如： arr.push("abc"); 向数组的末尾添加元素
10. pop  -- 删除元素，如： arr.pop(); 删除数组末尾的元素
~~~
### 3.Date 对象
~~~
1.创建当前时间
 	var date=new Date();
2.方法：
	*toLocalString(); 本地日期格式 
	toLocaleTimeString()
	toLocaleDateString()
	*getTime();返回的是1970年1月1日零点到当前时间的毫秒数
~~~

#### 4.Math对象

~~~
1.不需要创建对象，直接调用静态工具方法
2.方法：
	*random(); [0,1)之间的随机小数
	*ceil();天花板 向上取整 ，如 Math.ceil(2.1);// 3
	*floor();地板 向下取整 ，如 Math.floor(2.9);// 2
	*round();四舍五入 ，如 Math.round(2.4);--> 2 ;  Math.round(2.5);--> 3
3.属性：PI: 3.1415926...
~~~
- 例子：求 [3,8]之间的随机整数
~~~javascript
/*
   1. [0,1) * 6 ==> [0,6)
   2. [0,6)+3 ==> [3,9)==[3,8]
   3. floor 向下取整
*/
    var a=Math.random();
    var v=Math.floor(a*6+3);
    console.log(v);
~~~
#### 4.RegExp正则对象
~~~
1.单个字符：
	[a]  [h-x]  [a-z]  [a-zA-Z0-9_]== \w    [1-6]     [0-9] == \d 数字 
	
2.量词符号
	？: 0或1次
	* :任意次数 [0,正无穷]
	+ :[1,正无穷]
	{x}:表示出现x次
	{m,n} : m <= 次数 <= n
	{m,}  : m <= 次数
	{,n}  : 次数 <= n
3.开头^和结尾$
~~~
- 使用
~~~
1.创建对象：
	*var reg=new RegExp("正则表达式");
	*var reg=/正则表达式/;

2.方法：test()
reg.test(s);
~~~
* 例子 验证表单输入必须是6-8位中文
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正则表达式</title>
</head>
<body>
<form action="./服务器.html" id="form" method="post">
<label for="name">姓名：</label>
<input name="name" type="text" id="name">
<br>
<input type="submit"value="提交">
</form>
<script>
    document.getElementById("form").onsubmit=function(ev){
        var name=document.getElementById("name").value;
        console.log(name);
        // if (name.length>=6&&name.length<=8){
        //     return true
        var reg=/^[\u4e00-\u9fa5]{6,8}$/
        return reg.test(name);
        // }
    }
</script>
</body>
</html>
```
### js调试时使用下面的代码更加方便找错

```bash
onerror=function(m,f,r,c){alert("错误信息："+m+"\n出错文件："+f+"\n出错行："+r+"\n出错列："+c)}
```
