---
title: JavaEE中servlet的应用
date: 2020-08-01 22:02:47
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

## servlet简介
Tomcat其实是Web服务器和Servlet容器的结合体。Tomcat才与浏览器客户端打交道。Servlet可以简单的理解位Java里的一个接口。
1.servlet是服务器中核心处理单元    
2.服务器中有多个不同的servlet,每一个servlet负责不同的业务，处理不同的请求  
3.servlet的对象创建和调用其方法，都是tomcat来完成的。  
4.一个类必须实现servlet这个接口，才能算是servlet的家族成员。   

## servlet的使用
### 在web.xml中配置：
	servlet标签：告诉服务器servlet在哪儿，还给servlet取个名
	servlet-mapping标签：拦截所有请求，告诉浏览器如何访问到该servlet,提供url访问路径，同时和servlet标签关联
```bash
<!--    拦截请求-->
    <servlet-mapping>
      <!--servlet的名字一定是在Web服务器中进行注册声明的-->
    <servlet-name>Servlet</servlet-name>
    <!--servlet的响应路径-->
    <url-pattern>/UserLoginServlet</url-pattern>
    <!--/是Web服务器的根目录-->
    </servlet-mapping>
<!--    分配请求给servlet处理-->
    <servlet>
      <!-- 指明servlet实例的名字，具有唯一性-->
    <servlet-name>Servlet</servlet-name>
 <!-- 指明servlet的src目录下的后台实现类的包路径-->
        <url-pattern>/hello</url-pattern>     
    </servlet>
```
**注意**
1. 只有web文件夹下的内容才会被部署在服务器上
2. web文件夹下的WEB-INF文件夹里面的资源是受保护的，不能直接被访问
![](https://img-blog.csdnimg.cn/20200722220515159.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
### 配置版使用servlet的三种方法
#### 1.实现Servlet接口
共5个方法，其中最重要的3个是生命周期方法:init()  service() destroy()
1. init(); 初始化方法，在servlet第一次被请求时调用，表示servlet对象创建出来了
	      只会被调用一次，说明servlet是单例，一个servlet类只能创建一个对象
2. service();为人民服务方法，浏览器访问该servlet时会被服务器调用，表示提供一个服务
3. destroy();销毁方法，在服务器正常关闭时调用，表示当前servlet对象销毁了。
```bash
public class Helloservlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet 对象诞生");
    }

    @Override
    public ServletConfig getServletConfig() {
        //servlet配置信息
        return null;
    }
//servlet中真正处理请求的是service方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("为人民服务！");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁!");
    }
}
```
 servlet的访问：
```
1、首先，从浏览器中发送请求，是从当前工程中的路径与servlet-mapping标签中的url-pattern的标 签值进行匹配。
2、根据这个映射值，找到servlet-mapping标签中的servlet-name的值与servlet标签中的servlet-name进行匹
3、匹配到以后，找到servlet标签中的servlet-class标签中对应servlet类的src文件夹下的全路径。
4、从而调用并执行相应的servlet类。
  ```
#### 2.继承GenericServlet抽象类
```bash
public class Hello2servlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    System.out.println("第二版本servlet，为人民服务！");
    }
}
```
实现了Servlet接口，保留service方法仍是抽象，其它方法做了空实现
#### 3.继承HttpServlet封装了http协议的抽象类

```bash
public class Hello3servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我是专门请求get请求的方法");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我是专门请求post请求的方法");
    }
}
```
重写了service方法，自定义了doGet/doPost方法，用来分别处理get请求和post请求。
并在自己的service方法中对请求方式进行判断，然后分别调用doGet和doPost方法。
**使用上面三种方法需要配置web.xml文件，我在上面已经提到了。**
### 使用注解版

```bash
@WebServlet(name = "AServlet",urlPatterns="/as")
//还可以用@WebServlet("/as")
public class AServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
```
#### 两种方法的比较
注解访问servlet：

优点：代码少，可读性强，易于理解。

缺点：如果大量使用servlet注解，servlet类文件数量过多，不便于查找和修改。

web配置文件访问servlet：

优点：集中管理各servlet类路径的映射路径，便于修改和管理。

缺点：代码多，可读性不强，不易于理解。
## request和response对象
### request和response分工
1.都是doGet/doPost方法的形参，都是由服务器创建的   
2.request负责封装请求行信息(请求方式，请求路径，请求参数)   
3.response负责封装响应信息(响应头，响应体)    
### 1.request对象
- 获取请求行
~~~java
//1.获取请求方式 GET/POST
String m = request.getMethod();
//2.获取虚拟路径，工程访问路径  /req
String contextPath = request.getContextPath();
//3.获取servlet路径  /as
String servletPath = request.getServletPath();
//4.获取get方式的请求参数，
String qs = request.getQueryString();
//5.获取uri  :contextPath+servletPath  /req/as
String uri = request.getRequestURI();
//6.获取url:http://localhost:80/req/as?name=aa
StringBuffer url = request.getRequestURL();
//7.获取传输协议名 http
String protocol = request.getProtocol();
//8.获取客户端的ip: localhost
String ip = request.getRemoteAddr();
~~~
- 获取请求头
~~~java
//1.根据头名称获取头值
String userAgent = request.getHeader("user-agent");
System.out.println(userAgent);
String referer = request.getHeader("referer");
System.out.println(referer);
//2.获取所有头名称
Enumeration<String> hnames = request.getHeaderNames();
while (hnames.hasMoreElements()){
    //循环变量迭代器，取出每一个头名称
    String name = hnames.nextElement();
    //根据头名称获取头值
    String value = request.getHeader(name);
    System.out.println(name+"--"+value);
}
~~~
- 获取请求体数据
~~~java
post方式有请求体，get方式没有请求体
//1.从request对象中获取字符流--字符流中包含请求体数据
BufferedReader br = request.getReader();
//2.从字符输入流中读取一行数据---请求体数据
String data = br.readLine();
System.out.println(data);
~~~
* 获取请求参数--重点
~~~java
//1.获取单个请求参数 --根据参数名 获取参数值 ，如果是表单 ，参数名就是input的name值
String user = request.getParameter("user");
String psw = request.getParameter("psw");

//2.获取复选框的参数值，一个参数名对应多个参数值，参数值在一个数组中
String[] likes = request.getParameterValues("like");//java  games

//3.获取form中所有的请求参数名
Enumeration<String> pnames = request.getParameterNames();//user psw like
while (pnames.hasMoreElements()){
    //获取Enumeration集合中的每一个参数名
    String name = pnames.nextElement();
    //获取单个请求参数--根据参数名 获取参数值
    String [] value = request.getParameterValues(name);
    System.out.println(name+"--"+ Arrays.toString(value));
}

//        获取所有的参数名
        Enumeration<String> names=request.getParameterNames();
        while (names.hasMoreElements()){
            String s=names.nextElement();
            System.out.println(s);
        }
        
        //获取同参数名的所有参数
        String[] n = request.getParameterValues("name");
        System.out.println(n);
        System.out.println(Arrays.toString(n));
        
        //一次性获取所有的参数名和参数值
        Map<String ,String[]> map=request.getParameterMap();
        //Map的第一种遍历方法
        Set<Map.Entry<String ,String[]>> entries= map.entrySet();
        for (Map.Entry < String, String[] > entry:entries){
            String key=entry.getKey();
            String[] value =entry.getValue();
            System.out.println(key+":"+Arrays.toString(value));
        }

        //第二种遍历若干的Key
        Set<String> keys=map.keySet();
        for (String key : keys) {
            System.out.println(key+"："+Arrays.toString(map.get(key)));
        }
        //第三种遍历若干的Value
        Collection<String[]> values=map.values();
        for (String[] value : values) {
            System.out.println(Arrays.toString(value));
        }
~~~

- 处理post请求参数中文乱码
~~~java
 request.setCharacterEncoding("utf-8");
~~~
#### 转发
使用的方法：request.getRequestDispatcher("/cs").forward(request,response);  
- 转发的特点
~~~powershell
1.服务器行为，共享request对象。
2.只在同一个项目的资源之间进行转发（如不能转发百度的网站）
3.不论转发多少次，只能算是一次请求
4.转发路径，只写servlet上下文路径，不用加上虚拟路径
~~~
示例代码
```bash
@WebServlet("/bs")
public class BServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name=request.getParameter("name");
    request.setAttribute("wife","小乔");
    //参数传递给CServlet,转发是cservlet和bservlet共用一个对象
        request.getRequestDispatcher("/cs").forward(request,response);
        System.out.println(name);

    }
}
/*
1.转发到CServlet的DoGet,传入request对象作为forward的实参，说明两个servlet使用的是同一个request对象
2.转发是服务器行为，是在同一个项目内部进行转发，因此转发路径只写servlet路径，不用写工程访问路径
 */
@WebServlet("/cs")
public class CServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        System.out.println(name);
        String wife=(String) request.getAttribute("wife");
        System.out.println(wife);
    }
}
```
#### 域对象（域内传递参数）
~~~powershell
1.request是javaWeb中最小的域对象
2.request域范围：一次请求的范围，意味着共用一个request对象
3.应用于转发，在转发的过程中传递参数
4.获取最大的域对象ServletContext的方法

~~~
~~~java
//使用域方法--设置参数 age=18
request.setAttribute("age",18);
//使用域方法--获取参数 age=18
Object age = request.getAttribute("age");
~~~
![](https://img-blog.csdnimg.cn/20200722225306400.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

### Response对象
- 重定向

~~~powershell
1. 设置状态码 302 ，设置响应头 location=url
   response.setStatus(302);
   response.setHeader("location","新url");
2.简写：
   response.sendRedirect("新url");
~~~
- 重定向和转发对比
~~~powershell
1.重定向地址栏会发生改变，转发地址栏不会发生改变
2.重定向是两次请求，因此共用两个request对象，不能共享数据，转发是一次请求，可以共享数据
3.重定向可以访问任意站点的资源，转发是服务器内部行为，跳转仅限于当前站点
~~~
- 响应字符流形式的数据
response.getWriter().write("字符串");
```bash
     //在创建对象之前设置utf-8解决编码问题
   response.setContentType("text/html;charset=UTF-8");
      PrintWriter writer = response.getWriter();
       writer.write("我是字符流，第一次给浏览器发送消息");//不设置setContentType会出现乱码
```
 * 响应字节流----响应一个图片
获取字节输出流，并向流中写字节数据  
response.getOutputStream().write(字节或字节数组);     
方法一使用绝对路径
```bash
 //图片变成字节，先获取图片
        FileInputStream is = new FileInputStream("E:\\OPT\\Workspace\\day8-response\\web\\img\\a.jpg");
        System.out.println(is);
        //获取字节输出对象
        ServletOutputStream os = response.getOutputStream();
        int i=0;
        //两个流对接，边读边写
        while ((i=is.read())>=-1) {
            os.write(i);
        }
        os.close();
        is.close();
    }
}
```
 方法二使用相对路径
 这个方法需要将图片部署到服务器上。
 1. 先将图片放到这给目录下
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200722224743602.png)
2. 打开项目结构，添加到img文件夹到库文件
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200722225110995.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
代码如下：
```bash
 String realPath = request.getServletContext().getRealPath("/img/a.jpg");
        System.out.println(realPath);
        FileInputStream is = new FileInputStream(realPath);
        System.out.println(is);
        ServletOutputStream os = response.getOutputStream();
        int i;
        while ((i=is.read())>=-1){
            os.write(i);
        }
        os.close();
        is.close();
```
