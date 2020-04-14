# 根据ip地址定位地理位置
## 安装库和数据准备
由于离线查询ip需要全球IP的分布数据，所以我直接选择了一个免费离线查询ip的数据包,
GeoLite2-City.mmdb在文件disk里

MMDB即Maxmind DB，是一个设计用于存储IPv4和IPv6的数据信息的数据库，mmdb文件是一个二进制格式的文件，它使用一个二分查找树加速IP信息的查询。
```
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
```
## 代码在localip.py里
## 将py文件转为.exe文件
安装pyinstaller
pip install pyinstaller

将.py转换为.exe
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