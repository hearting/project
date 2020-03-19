import time, json, requests
import pandas as pd
import numpy as np
import xlwt, xlrd
import matplotlib.pyplot as plt
url = 'https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5&callback=&_=%d'%int(time.time()*1000)
data = json.loads(requests.get(url=url).json()['data'])
print(data.keys())
d = data['areaTree'][0]['children']
print(d)
print(data['areaTree'][0].keys())
# 统计省份信息
sheng=([item['name'] for item in d])
print(sheng)
# 解析所有省确诊数据
total=([item['total'] for item in d])
#print(total)
#累计确诊人数
confirm=[]
for i in range(34):
    confirm.append(total[i]['confirm'])
print(confirm)
#累计死亡人数
dead=[0]*34
for i in range(34):
    dead[i]=total[i]['dead']
print(dead)
#累计治愈人数
heal=[0]*34
for i in range(34):
     heal[i]=total[i]['heal']
print(heal)
#现存确诊
now_confirm=[]
now_confirm=np.array(confirm)-np.array(dead)-np.array(heal)
#将数据导出到excel
dateframe=pd.DataFrame({'省份':sheng,'确诊人数':confirm,'死亡人数':dead,'治愈人数':heal,'现存确诊':now_confirm})
now=time.strftime("%m-%d-%Y")
print(now)
wirte=pd.ExcelWriter(now+'.xlsx')
dateframe.to_excel(excel_writer=wirte,index=False,sheet_name='China')
wirte.save()
wirte.close()
# f = xlwt.Workbook() #创建工作簿
# china = f.add_sheet(u'china',cell_overwrite_ok=True) #创建sheet
# china.write(0,1,'确诊人数')
# china.write(0,2,'死亡人数')
# china.write(0,3,'治愈人数')
# for i in range(1,35):
#     china.write(i,0,sheng[i-1])
#     china.write(i,1,confirm[i-1])
#     china.write(i,2,dead[i-1])
#     china.write(i,3,heal[i-1])
# f.save(now+'.xls')#保存文件

# 使用 Matplotlib 绘制全国确诊病例柱状图
plt.rcParams['font.sans-serif'] = ['SimHei']  #正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False    #正常显示负号

#获取数据
all_data = dict(zip(sheng,confirm))
print(all_data)
names = all_data.keys()
nums = all_data.values()

# 绘图
plt.figure(figsize=[11,7])
plt.bar(names, nums, width=0.8, color='purple')

# 设置标题
plt.xlabel("地区", fontproperties='SimHei', size=15)
plt.ylabel("人数", fontproperties='SimHei', rotation=90, size=12)
plt.title("全国疫情确诊图", fontproperties='SimHei', size=16)
plt.xticks(list(names), fontproperties='SimHei', rotation=-60, size=10)

# 显示数字
for a, b in zip(list(names), list(nums)):
    plt.text(a, b, b, ha='center', va='bottom', size=6)
#　图形展示
plt.savefig(now+'全国疫情确诊图.png')  # 保存为文件
plt.show()
