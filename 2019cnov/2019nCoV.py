import time, json, requests
from datetime import datetime
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import pandas as pd
def catch_daily():
    #抓取每日确诊和死亡数据

    url = 'https://view.inews.qq.com/g2/getOnsInfo?name=wuwei_ww_cn_day_counts&callback=&_=%d'%int(time.time()*1000)
    data = json.loads(requests.get(url=url).json()['data'])
    data.sort(key=lambda x: x['date'])
    print(data)
    date=[]
    date_list = list()  # 日期
    confirm_list = list()  # 确诊
    suspect_list = list()  # 疑似
    dead_list = list()  # 死亡
    heal_list = list()  # 治愈
    for i in data:
        month,day=i['date'].split('/')
        date.append(i['date'])
        date_list.append(datetime.strptime('2020-%s-%s' %(month, day), '%Y-%m-%d'))
        confirm_list.append(int(i['confirm']))
        suspect_list.append(int(i['suspect']))
        dead_list.append(int(i['dead']))
        heal_list.append(int(i['heal']))
        # 将数据导出到excel
        dateframe = pd.DataFrame({'日期': date, '确诊人数': confirm_list, '疑似人数': suspect_list, '死亡人数': dead_list, '治愈人数': heal_list})
        dateframe.to_excel('2019-nCoV中国疫情统计表.xls')
    return date_list, confirm_list, suspect_list, dead_list, heal_list


def plot_daily():
#绘制每日确诊和死亡数据
    date_list, confirm_list, suspect_list, dead_list, heal_list = catch_daily()  # 获取数据
    plt.rcParams['font.sans-serif'] = ['SimHei']  #正常显示中文标签
    plt.figure('2019-nCoV疫情统计图表', facecolor='#f4f4f4', figsize=(10, 8))
    plt.title('2019-nCoV疫情曲线', fontsize=20)

    plt.plot(date_list, confirm_list, label='确诊')
    plt.plot(date_list, suspect_list, label='疑似')
    plt.plot(date_list, dead_list, label='死亡')
    plt.plot(date_list, heal_list, label='治愈')

    plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%m-%d'))  # 格式化时间轴标注
    plt.gcf().autofmt_xdate()  # 优化标注（自动倾斜）
    plt.grid(linestyle=':')  # 显示网格
    plt.legend(loc='best')  # 显示图例
    plt.savefig('2019-nCoV疫情曲线.png')  # 保存为文件
    plt.show()

plot_daily()