---
title: 十分钟学会CSS基础使用
date: 2020-08-01 21:56:20
tags: 前端
categories: 前端
---

<!-- toc -->

<!--more-->

## 概念
css : cascading  style  sheet  层叠 样式 表 ：修饰网页，布局和美化
好处：
  * 美化网页，样式更多更强大 
  * 提高效率  
## 使用
### 与HTML三种结合方式

```
1.内联样式 ：在元素上使用style属性
    <div style="color:red;">
    	hello world
    </div>

2.内部样式：在head标签中，使用style标签
    <style>
        div{
        	color: blue;
        }
    </style>
    
3.外部样式：定义css文件，在head标签中使用link标签引入css文件
	<link rel="stylesheet" href="../css/a.css"/>

三种结合方式的优先级：
	*内联样式最高 
	*内部和外部没有明显的优先级，取决于谁后加载
```
### 基本选择器
1. id选择器     #id值{样式属性列表}
```
    <style>
        #zsf{
            color: yellow;
        }
    </style>
	<ul>
    <li id="zsf">张三丰</li>
	</ul>
```
2. class选择器  .class值{样式属性列表}
```
    <style>
        .zs{
            color: blue;
        }
    </style>
	<ul>
    <li class="zs">张无极</li>
    <li class="zs">张飞</li>
	</ul>
```
元素选择器    元素名称{样式属性列表}
```
    <style>
        div{
            border: 1px solid red;
            width: 200px;
            height: 200px;
        }
   </style>
<div>1</div>
```

* 优先级：id > class >元素
### 扩展选择器

```
1. 交集   用.隔开
       两个选择器的范围之间的交集，两个选择器直接写在一起，不要理解为用.连接

2. 并集   用,隔开

3. 后代
   后代用空格隔开，可跨越多代

4. 子代
   用大于号隔开，跨越一代 如： div>li

5. 属性选择器
   1. span[a="a"]{}

   2.<span a="a"> <span>
   例：
    <style>
        /*元素选择器和属性选择器取  交集 */
        span[a='a']{
            color: red;
        }
        span[b='b']{
            color: blue;
        }
        [c="c"][e="e"]{
            color: rebeccapurple;
        }
        [d="d"]{
            color: yellowgreen;
        }

    </style>
    <p>
        <span a="a">当我的紫葡萄化为深秋的露水</span> <br>
        <span b="b">当我的鲜花依偎在别人的情怀</span> <br>
        <span c="c" e="e">我依然固执的拾起凝霜的枯藤</span> <br>
        <span d="d">在凄凉的大地上写下相信未来</span> <br>
    </p>
6. 伪类选择器(针对状态)

   对于超链接，初始状态(a:link)，鼠标悬停状态(a:hover)，鼠标点击状态(a:action)，访问过状态(a:visited)
```
### 常用的属性
1. 字体属性
```
字体属性的功能：设置页面字体的显示样式。
	font-family 设置使用的字体
	font-style 设置字体的样式，是否斜体
	font-variant 设置字体的大小写
	font-weight 设置字体的粗细
	font-size 设置字体的大小
```
2. 颜色和背景属性
```
颜色和背景属性的功能:设置页面元素的颜色和背景颜色.
color 设置元素前景色
background-color 设置元素背景色	
background-imge 设置元素背景图案	
background-repeat 设置背景图案的重复方式	
background-position 设置背景图案的初始位置	
background-size 设置背景大小
```
3. 文本属性
```
文本属性的功能:设置页面的显示效果。
text-align 设置文本的对齐方式
text-indent 设置文本的首行缩进		
line-height 设置文本的行高		
a:link 设置链接未访问的状态
a:visited 设置链接访问过的的状态
a:hover 设置链接的鼠标悬停状态		
```
4. 边框属性
```
边框属性是设置页面内边框元素的显示效果
border :边框 border:"1px solid red"  三个子属性：边框宽度 实线/虚线 颜色
```
5. 块属性
```
块属性是设置元素之间的距离
外边距
margin:
 margin-left  左外边距
 margin-right 右外边距
 margin-top   上外边距
 margin-bottom 下外边距

使用：
margin: 10px 10px 30px 20px; 依次表示：上 右 下 左
margin: 10px 10px;  依次表示上 左
margin: 10px auto;  auto会自动居中

注意：
上下冲突时，满足上面的
左右冲突时，满足左边的

padding
	容器内部的边距称为padding	即从边框开始往里的距离
	设置padding会增加宽，高，box-sizing: border-box;   盒子的尺寸按盒子的原始边框显示

padding-top 设置顶端填充距

padding-right  设置右侧填充距距 使用 同外边距一样。
```
6. 层属性
```
设置页面内元素的定位方式：
Relative 设置相当定位
Absolute 设置绝对定位
```
7. 内联元素(inline)和块级元素(block) 

内联元素(inline)  
对应于display:block	  
1、内联元素(inline)不会独占一行，相邻的内联元素会排在同一行。其宽度随内容的变化而变化。 
2、内联元素不可以设置宽高 
3、行内元素起边距作用的只有margin-left、margin-right、padding-left、padding-right，其它属性不会起边距效果。 
常见的有
```
a – 锚点 
abbr – 缩写 
b – 粗体
big – 大字体 
br – 换行 
cite – 引用 
code – 计算机代码(在引用源码的时候需要) 
em – 强调 
font – 字体设定(不推荐) 
i – 斜体 
img – 图片 
input – 输入框 
kbd – 定义键盘文本 
label – 表格标签 
q – 短引用 
span – 常用内联容器，定义文本内区块 
strong – 粗体强调 
textarea – 多行文本输入框 
```
块级元素   
对应于display:inline；  
1、块级元素会独占一行，默认情况下宽度自动填满其父元素宽度 
2、块级元素可以设置宽高 
3、块级元素可以设置margin，padding
常见的有
```
address – 地址 
blockquote – 块引用 
dir – 目录列表 
div – 常用块级容易，也是CSS layout的主要标签 
dl – 定义列表 
fieldset – form控制组 
form – 交互表单 
h1 – h6 标题 
hr – 水平分隔线 
menu – 菜单列表 
ol – 有序表单 
p – 段落 
pre – 格式化文本 
table – 表格 
ul – 无序列表 
li -定义列表项目
```
内联块状元素(display: inline-block)：
简单来说就是将对象呈现为inline对象，但是对象的内容作为block对象呈现（可以设置宽高和margin值）。之后的内联对象会被排列在同一内联。比如我们可以给一个link（a元素）inline-block属性值，使其既具有block的宽度高度特性又具有inline的同行特性。

8. 浮动
```
两个块级元素都设置了宽度和高度，两个想要占一行，就使用浮动，来进行设置
如果有嵌套，浮动相对的就是父元素，没有父元素，浮动相对的就是浏览器窗口
两边都为左浮动，如果宽度放不下，会被挤下去
默认情况下，子元素能够撑起父元素的高度
如果子元素设置了浮动，是没有办法撑起父元素的高度，父元素要么手动设置高度，要么清除浮动
表示垂直屏幕向上漂浮，会脱离文档流，也会让块级元素div失去 换行功能
float:left 向左浮动
float:right 向右浮动
```