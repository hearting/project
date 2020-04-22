---
title: python根据ip地址定位地理位置并转换为.exe文件
date: 2020-04-22 18:51:06
tags: python
categories: python
---
<!-- toc -->
<!-- more -->

## 安装库和数据准备
由于离线查询ip需要全球IP的分布数据，所以我直接选择了一个免费离线查询ip的数据包,  

[GeoLite2-City.mmdb](https://download.csdn.net/download/qq_42112448/12289578)

MMDB即Maxmind DB，是一个设计用于存储IPv4和IPv6的数据信息的数据库，mmdb文件是一个二进制格式的文件，它使用一个二分查找树加速IP信息的查询。

格式说明
    
    {
    continent => {                    //大洲
        code        => string,        //大洲代码，如AS，可能为空
        names       => {              //大洲名称
            en      => string,        //大洲英文名称，如Asia，可能为空
            zh_CN   => string,        //大洲中文名称，如亚洲，可能为空
        },
    },
    country   => {                    //国家
        iso_code    => string,        //国家iso代码，如CN，可能为空
        names       => {              //国家名称
            en      => string,        //国家英文名称，如China，可能为空
            zh_CN   => string,        //国家中文名称，如中国，可能为空
        },
    },
    subdivisions => {                 //省份
        iso_code    => string,        //省iso代码省ISO代码，如44，可能为空
        names       => {              //省份名称
            en      => string,        //省份英文名称，如Guangdong，可能为空
            zh_CN   => string,        //省份中文名称，如广东，可能为空
        },
    },
    city      => {                    //城市
        id          => int,           //城市id，如440000，可能为空
        names       => {              //城市名称
            en      => string,        //城市英文名称，如Guangzhou，可能为空
            zh_CN   => string,        //城市中文名称，如广州，可能为空
        },
    },
    location  => {                    //地理位置
        latitude    => double,        //纬度，可能为空
        longitude   => double,        //经度，可能为空
        time_zone   => string,        //时区，可能为空
    },
    isp       => {                    //运营商
        id          => int,           //运营商id，可能为空
        names       => {              //运营商名称
            zh_CN   => string,        //运营商中文名称，如电信，可能为空
    },
	}


为了读取这个包的数据需要安装一个模块：pip install geoip2  
为了布局窗口安装一个模块：pip install tkinter    

## 代码如下  
```
import tkinter
import pygeoip,geoip2.database


class FindLocation(object):
    def __init__(self):
        self.gi = geoip2.database.Reader("./GeoLite2-City.mmdb")
        # 创建主窗口,用于容纳其它组件
        self.root = tkinter.Tk()
        # 给主窗口设置标题内容
        self.root.title("全球定位ip位置(离线版)")
        # 创建一个输入框,并设置尺寸
        self.ip_input = tkinter.Entry(self.root, width=30)

        # 创建一个回显列表
        self.display_info = tkinter.Listbox(self.root, width=50)

        # 创建一个查询结果的按钮
        self.result_button = tkinter.Button(self.root, command=self.find_position, text="查询")

    # 完成布局
    def gui_arrang(self):
        self.ip_input.pack()
        self.display_info.pack()
        self.result_button.pack()

    # 根据ip查找地理位置
    def find_position(self):
        # 获取输入信息
        self.ip_addr = self.ip_input.get()
        aim = self.gi.city(self.ip_addr)
        # 为了避免非法值,导致程序崩溃,有兴趣可以用正则写一下具体的规则,我为了便于新手理解,减少代码量,就直接粗放的过滤了
        try:

            # 获取目标城市
            city = aim.city.name
            # 获取目标国家
            country = aim.country.name
            # 获取目标经度
            longitude = aim.location.longitude
            # 获取目标纬度
            latitude = aim.location.latitude
        except:
            pass

        # 创建临时列表
        the_ip_info = ["所在纬度:" + str(latitude), "所在经度:" + str(longitude), "所在城市:" + str(city), "所在国家或地区:" + str(country), "需要查询的ip:" + str(self.ip_addr)]
        # 清空回显列表可见部分,类似clear命令
        for item in range(10):
            self.display_info.insert(0, "")

        # 为回显列表赋值
        for item in the_ip_info:
            self.display_info.insert(0, item)
        # 这里的返回值,没啥用,就是为了好看
        return the_ip_info


def main():
    # 初始化对象
    FL = FindLocation()
    # 进行布局
    FL.gui_arrang()
    # 主程序执行
    tkinter.mainloop()
    pass
    
if __name__ == "__main__":
    main()
```
效果如下  
![](https://img-blog.csdnimg.cn/20200402234903859.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
## 将py文件转为.exe文件
1. 安装pyinstaller  
	>pip install pyinstaller
2. 将.py转换为.exe
(1) 进入文件夹，运行cmd
(2)pyinstaller -F -w localip.py
-w表示运行.exe是弹出命令行窗口
更改exe文件的图标
自定义打包出来的exe文件图标，需要使用-i参数，同时需要准备一个ico格式的图片
pyinstaller -F --icon=1234.ico localip.py
1234.ico为当前路径下的图标文件，在其他位置需要加上路径
（3）运行测试
生成的.exe文件在dist文件夹内
将数据库文件GeoLite2-City.mmdb拷贝到dist文件夹中
运行可执行文件(.exe)