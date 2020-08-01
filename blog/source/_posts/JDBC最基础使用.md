---
title: JDBC最基础使用
date: 2020-08-01 21:49:03
tags: Java
categories: Java
---

<!-- toc -->

<!--more-->

## JDBC的介绍
Java数据库连接，（Java Database Connectivity，简称JDBC）是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。
### 建议链接的五大步骤：
1. 加载（注册）数据库
2.  建立链接
3.  执行SQL语句
4.  处理结果集
5.  关闭数据库
### JDBC 架构
1. 传统连接
![](https://img-blog.csdnimg.cn/20200719152323372.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
缺点：
     用户每次请求都需要向数据库获得链接，而数据库创建连接通常需要消耗相对较大的资源，创建时间也较长。假设网站一天50万访问量，数据库服务器就需要创建50万次连接，极大的浪费数据库的资源，并且极易造成数据库服务器内存溢出、拓机。
2.  使用数据库连接池
连接池是将已经创建好的连接保存在池中，当有请求来时，直接使用已经创建好的连接对数据库进行访问。这样省略了创建连接和销毁连接的过程。这样性能上得到了提高。
![](https://img-blog.csdnimg.cn/20200719153501304.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
## 两种方式获取连接对象
1. 用原始的jdbc方式去获取连接对象
```
 @Test//用原始的jdbc方式去获取连接对象
    public void test() throws Exception {
        //注册驱动,高版本自动注册，这一步可以省略不写
        //低版本mysql是
        //Class.forName("com.mysql.jdbc.Driver");
       // mysql8用
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接对象
        //如果在本机上可使用：
        // Connection con= DriverManager.getConnection("jdbc:mysql:///yz","root","password");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yz","root","password");
        System.out.println(con);//输出连接对象的哈希地址值
    }
```
使用druid连接池
```
 @Test//使用druid连接池
    public void test1() throws Exception {
        //创建数据源（连接池）对象，并关联jdbc参数
        DruidDataSource ds= new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///yz");
        ds.setUsername("root");
        ds.setPassword("password");
        DruidPooledConnection con=ds.getConnection();
        System.out.println(con);
        }
```
## 利用jdbc执行增删改查测试
```
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class hello1 {
//    @SuppressWarnings("all")//工程中有重复的代码，会有灰色的波浪线提示，用该注解，消除提示
    private Connection con;
    @Before//执行每一个@Test方法前，都会去执行@Before方法
    public void init() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql:///yz","root","password");
        System.out.println(con);//输出连接对象的哈希地址值
    }

    @Test//测试在表中添加数据
    public void test1() throws Exception {
        //测试jdbc新增
        String sql = "insert into dept values (null,?,?)";
        //获取执行sql的预编译对象
        PreparedStatement psmt = con.prepareStatement(sql);
        //为占位符赋值
        psmt.setString(1, "销售部");
        psmt.setString(2, "光谷软件园D5栋");
        //执行sql
        int i = psmt.executeUpdate();
        System.out.println(i + "行数据受影响");
    }

    @Test//测试在表中修改数据
    public void test2() throws Exception {
        //测试jdbc修改
        String sql="update dept set dname=? where did=?";
        //获取执行sql的预编译对象
        PreparedStatement psmt=con.prepareStatement(sql);
        //为占位符赋值
        psmt.setString(1,"采购部");
        psmt.setInt(2,1);
        //执行sql
        int i=psmt.executeUpdate();
        System.out.println(i+"行数据受影响");
    }

    @Test//使用jdbc测试删除
    public void test3() throws Exception {
        //测试jdbc删除
        String sql="delete from dept where dname=?";
        //获取执行sql的预编译对象
        PreparedStatement psmt=con.prepareStatement(sql);
        //为占位符赋值
        psmt.setString(1,"销售部");
        //执行sql
        int i=psmt.executeUpdate();
        System.out.println(i+"行数据受影响");
    }

    @Test//使用jdbc测试查询--查询单行数据
    public void test4() throws Exception {
        //测试jdbc查询单行数据
        String sql1="select * from dept where did=?";
        //获取执行sql的预编译对象
        PreparedStatement psmt1=con.prepareStatement(sql1);
        //为占位符赋值
        psmt1.setInt(1,5);
        //执行sql返回结果集
        ResultSet rs=psmt1.executeQuery();
        //拆开，遍历结果集：rs只有一行，只需要判断是否有数据即可；rs有多行，使用迭代器遍历
        if (rs.next()){
            int did=rs.getInt(1);
            String dname=rs.getString(2);
            String location=rs.getString(3);
            System.out.println(did);
            System.out.println(dname);
            System.out.println(location);

    }
    }

    @Test//使用jdbc测试查询--查询多行数据
    public void test5() throws Exception {
        //测试jdbc查询单行数据
       String sql2="select * from dept";
       //获取执行sql的预编译对象
       PreparedStatement psmt2=con.prepareStatement(sql2);
       //执行sql返回结果集
       ResultSet rs1=psmt2.executeQuery();
       //拆开，遍历结果集：rs只有一行，只需要判断是否有数据即可；rs有多行，使用迭代器遍历
       while (rs1.next()){
           int did=rs1.getInt("did");
           String dname=rs1.getString("dname");
           String location=rs1.getString("location");
           System.out.println(did+"      "+dname+"       "+location);
       }
    }

}
```
## 查询所有数据，封装到List集合
ResultSet集合包含符合SQL语句中条件的所有行，并且它通过一套get方法（这些get方法可以访问当前行中的不同列）提供了对这些行中数据的访问。ResultSet.next方法用于移动到ResultSet中的下一行，使下一行成为当前行。但我们处理数据大部分情况都用的list。接下来我们讲查询的数据处理到List中。

查询emps表数据,并封装到List集合中
dept.java
```
public class dept {
    private String did;
    private String dname;
    private String location;
    @Override
    public String toString() {
        return "dept{" +
                "did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public dept(String did, String dname, String location) {
        this.did = did;
        this.dname = dname;
        this.location = location;
    }

    public dept() {
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

```
hello2.java
```
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//查询所有数据，封装到List集合
public class hello2 {
    private Connection con;
    @Before//执行每一个@Test方法前，都会去执行@Before方法
    public void init() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql:///yz","root","password");
        System.out.println(con);//输出连接对象的哈希地址值
    }
    @Test
    public void test() throws Exception{
        String sql = "select * from dept";
        //获取执行sql的预编译对象
        PreparedStatement psmt = con.prepareStatement(sql);
        //执行sql返回结果集
        ResultSet rs = psmt.executeQuery();
        //3.遍历结果集rs
        ArrayList<dept> list = new ArrayList<>();
        while (rs.next()) {//rs.next()准备取出某一行数据
            String did = rs.getString("did");
            String dname = rs.getString("dname");
            String location = rs.getString("location");
            //将emps表中的每一行数据封装成一个emps对象
            dept dept = new dept(did, dname, location);
            //emps对象存进list集合中
            list.add(dept);
        }
        System.out.println("封装后的集合数据："+list);
    }
}
```
注意：
可能会报错如下：
![](https://img-blog.csdnimg.cn/20200719161340944.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
解决方法：
1. MySQLl的jdbc驱动最新版（6.0+）时，遇到数据库和系统时区差异引起的问题。换用低版本的jar包。
2. 在URL后面加上时区参数，只要出现URL就需要添加后面的
>?serverTimezone=UTC  
>或
>?serverTimezone=GMT%2B8
3. 找到mysql的安装目录下MySQL Server 8.0目录下的my.ini文件，在mysqld下加入如下代码：
>default-time-zone = '+8:00'

特别提醒：
网上的另一种解决方法有个小问题，就是重启mysql后会还原为默认设置，需要重新设置，**最好不要用下面这种方法。**
![](https://img-blog.csdnimg.cn/20200719213755960.png)
## 使用DBUtils简化操作
DBUtils是一个对JDBC进行简单封装的开源工具类库   

用传统JDBC实现DBUtils如下：
 Jdbcutil.java
```
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbcutil {
    //将查询结果集rs中的数据封装到list集合中
    public static <T> List<T> queryList(String sql, Class<T> c) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            Connection con = DriverManager.getConnection("jdbc:mysql:///yz", "root", "password");
            //1.定义sql
            //2.创建执行sql的对象
            PreparedStatement pstm = con.prepareStatement(sql);
            //3.执行sql
            ResultSet rs = pstm.executeQuery();
            //4.遍历rs,创建一个list集合
            ArrayList<T> list = new ArrayList<>();
            //5.元数据
            ResultSetMetaData md = rs.getMetaData();
            //总列数
            int count = md.getColumnCount();
            while (rs.next()) {
                //T通用类型，不确实是哪一个实体类，dept,emps
                T t = c.newInstance();
                for (int i = 1; i <= count; i++) {
                    //每一个单元格的数据
                    Object value = rs.getObject(i);
                    //反射中对 属性的操作，将上面单元格的数据value,存到对象t中
                    //使用元数据获取列名,列名和实体类的属性名相同的
                    String columnName = md.getColumnName(i);
                    //获取实体类的每一个属性的管理对象
                    Field f = c.getDeclaredField(columnName);
                    //由于成员属性进行了封装，是private，暴力反射，开启私有属性操作权限
                    f.setAccessible(true);
                    f.set(t, value);//将value赋值给t对象的columnName属性
                }
                list.add(t);
            }
            System.out.println("封装后的集合数据：" + list);
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }
```

```bash
    @Test//测试部门表
    public void test1() throws SQLException {
        List<dept> dp= Jdbcutil.queryList("select * from dept",dept.class);
        System.out.println(dp);
    }
```
使用DBUtils简化操作
```
 @Test//测试dbutils
    public void test() throws SQLException {
        DruidDataSource ds= new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///yz");
        ds.setUsername("root");
        ds.setPassword("password");
        //1.创建qr对象，传入数据源参数
        QueryRunner qs=new QueryRunner(ds);
        //2.调用查询方法
        List <dept> dp=qs.query("select * from dept",new BeanListHandler<dept>(dept.class));

        System.out.println(dp);
    }
```
