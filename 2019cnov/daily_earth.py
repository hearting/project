import time, json, requests
import pandas as pd
import numpy as np
import openpyxl
url = 'https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5&callback=&_=%d'%int(time.time()*1000)
data = json.loads(requests.get(url=url).json()['data'])
d = data['areaTree']
#获取国家名称
name=list()           #国家
for item in d:
    name.append(item['name'])
print(name)
#统计各国数据
for item in range(len(name)):
    url = 'https://api.inews.qq.com/newsqa/v1/query/pubished/daily/list?country=%s&'%name[item+1]
    data1 = json.dumps(requests.get(url).json()['data'])
    data2=json.loads(data1)
    print(data2)
    date_list = list()  # 日期
    confirm_list = list()  # 确诊
    suspect_list = list()  # 疑似
    dead_list = list()  # 死亡
    heal_list = list()  # 治愈
    now_confirm=[]#现存确诊
    for i in data2:
        date_list.append(i['date'])
        confirm_list.append(int(i['confirm']))
        suspect_list.append(int(i['suspect']))
        dead_list.append(int(i['dead']))
        heal_list.append(int(i['heal']))
    now_confirm=np.array(confirm_list)-np.array(dead_list)-np.array(heal_list)
    print(now_confirm)
    # 将数据导出到excel
    dateframe = pd.DataFrame({'日期': date_list, '确诊人数': confirm_list, '疑似人数': suspect_list, '死亡人数': dead_list, '治愈人数': heal_list,'现存确诊':now_confirm})
    dateframe.to_excel(name[item+1]+'.xlsx')
