---
title: 排序算法分析
date: 2020-04-15 12:47:40
tags: Algorithm
categories: Algorithm
---
<!-- toc -->

# 排序

算法的时间复杂度主要取决与比较和交换的次数。空间复杂度看是否需要额外的内存空间存储数组的副本。  
<!-- more -->

1. **冒泡排序法**  
比较相邻的元素。如果第一个比第二个大，就交换他们两个，依次类推。最好时间复杂度O(n)，平均时间复杂度O(n²)。空间复杂度O(1)。  
![](https://img-blog.csdnimg.cn/20200415114009703.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

2. **选择排序法**   
在数组中找出最小的元素，与第一个元素交换位置，依次类推。  
比较的次数为你n*(n-1)/2,交换的次数为n-1。时间复杂度O(n²)，空间复杂度O(1)。   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415114134152.jpg)

3. **插入排序法**  
将数插入到左侧已有序的数组中，将其余元素右移。插入排序所需时间取决于输入元素的初始位置。对于大规模乱序数组插入排序很慢。因为只交换相邻的元素。  
最好情况：交换0次，比较n-1次。  
平均情况：交换n*(n-1)/4次,比较n*(n-1)/4次。  
最坏情况：交换n*(n-1)/2次,比较n*(n-1)/2次。  
时间复杂度O(n²)，空间复杂度O(1)  
插入排序的交换操作和数组倒置的数量相同，需要比较次数大于数组倒置的数量，小于倒置的数量加数组的大小。插入排序不会访问索引右侧的元素，选择排序不会访问元素左侧的元素。  
![](https://img-blog.csdnimg.cn/20200415114044457.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
对于随机排序的无重复主键的数组，插入排序和选择排序的运行时间市平方级别的，二者之比为一个较小的常数。  

4. **希尔排序**  
基于插入排序的一种快速的排序算法，即分组插入排序。先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量  =1(  <  …<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。一般适用于中小规模的数据量。希尔算法在最坏的情况下和平均情况下执行效率相差不是很大。优化的是使d=1时，比较次数接近n。   
使用不同的增量对希尔排序的时间复杂度的改进将不一样  
(1) 希尔增量序列:初次取序列的一半为增量，以后每次减半，直到增量为1。最坏情况时间复杂度为O(n²)，空间复杂度O(1)。  
(2) Knuth增量序列:hi=3^i−1,递推公式：h1=1,hi=3∗h(i−1)+1。一般都用这个序列.最坏时间复杂度为 Θ(N^(3/2))  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415114214869.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

5. **归并排序**  
递归法（Top-down）  
申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列  
设定两个指针，最初位置分别为两个已经排序序列的起始位置  
比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置  
重复步骤3直到某一指针到达序列尾  
将另一序列剩下的所有元素直接复制到合并序列尾     
时间复杂为nlogn
空间复杂度为n
![](https://img-blog.csdnimg.cn/20200415114245703.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415114308895.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

6. **快排算法**（挖坑(基准数)填数，分治思想）  
先从数列中取出一个数作为基准数。将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。再对左右区间重复第二步，直到各区间只有一个数。    
时间复杂为nlogn
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415114339407.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

7.**堆排序算法**

用数组结构存储，逻辑结构为完全二叉树，先做成大根堆或小根堆，第一个数即为最大或最小，在将第一个数与最后一个数交换位置，在把第一个数通过上浮或下沉操作移动到顺序位置。   
一棵大小为N完全二叉树高度为lgn

对于一个含有N个元素的基于堆的优先队列，插入元素操作只需不超过lgN+1次比较，删除最大元素操作需要不超过2lgN次比较。  
构造堆时下沉操作需要小于2N次比较以及小于N次交换。  
要构造一个元素大小为28的堆，会处理1个大小为28的堆，两个大小为14的堆，4个大小为7的堆，8个大小为3的堆。8*1+4*2+2*3+1*5=27。27<N。又因在堆中最坏情况比较次数D(N)为两个子结点的一次比较，和堆顶点与两个子结点中大者进行比较，所以最坏情况下比较次数为2倍交换次数，D(N)=2C(N)<2N。
将N个元素排序，堆排序只需小于2NlgN+2N次比较，及N/2次交换。2N来自于堆的构造，2NlgN来自每次下沉操作的比较。  
![](https://img-blog.csdnimg.cn/20200415114419872.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
时间复杂为nlogn  
空间复杂度为1
## 分析测试
排序算法复杂度分析如图  ![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMuZG93bmsuY2MvaXRlbS81ZTk2N2ZiNGMyYTlhODNiZTU2NGNhZGQuanBn?x-oss-process=image/format,png)

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMuZG93bmsuY2MvaXRlbS81ZTk2N2ZiNGMyYTlhODNiZTU2NGNhY2MuanBn?x-oss-process=image/format,png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415114926777.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
每种排序方法需要时间如图： 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415115012459.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)
## 代码如下：
```
#include "pch.h"
#include <iostream>
#include<time.h>
using namespace std;
void bubble_sort(int *p, int n)
{
	int temp, i, j, k;
	for (i = 0; i < n - 1; i++)//选择出最大的数放后面
	{
		for (j = 0; j < n - i - 1; j++)
		{
			if (p[j] > p[j + 1])
			{
				temp = p[j];
				p[j] = p[j + 1];
				p[j + 1] = temp;
			}
		}
	}
}

void selection_sort(int *p, int n)
{
	int i, j, k, temp;
	for (i = 0; i < n - 1; i++)//选择出最小的数
	{
		k = i;//第k个为最小元素
		for (j = i + 1; j < n; j++)
		{
			if (p[k] > p[j])
				k = j;
		}
		if (i != k)
		{
			temp = p[k];
			p[k] = p[i];
			p[i] = temp;
		}
	}
}
void insertion_sort(int *p, int n)
{
	int i, j, temp;
	for (i = 1; i < n; i++)//i前面为有序
		for (j = i; j > 0; j--)
		{
			if (p[j - 1] > p[j])//与前一个元素比较交换，最后到达在序中位置，相当与插入到该位置
			{
				temp = p[j];
				p[j] = p[j - 1];
				p[j - 1] = temp;
			}
		}
}
//void shell_sort1(int *p,int n)//希尔增量
//{
//	int temp, i, j,h;
//	for (h = n / 2; h >=1; h = h / 2)//控制增量
//	{
//		for (i = h; i < n; i++)
//		{
//			for (j = i - h; j >= 0 && p[j] > p[j + h]; j -= h)
//			{
//				temp = p[j];
//				p[j] = p[j + h];
//				p[j + h] = temp;
//			}
//		}
//	}
//}
void shell_sort2(int *p, int n)// Knuth增量
{
	int temp, h = 1, i, j;
	while (h < n / 3)
		h = 3 * h + 1;//1，4，13，40……
	while (h >= 1)
	{
		for (i = h; i < n; i++)//将a[i]插入到a[i-j],a[i-2*j]……中
		{
			for (j = i; j >= h && p[j] < p[j - h]; j -= h)
			{
				temp = p[j];
				p[j] = p[j - h];
				p[j - h] = temp;
			}
		}
		h = h / 3;
	}
}
void mergearray(int a[], int first, int mid, int last, int temp[])
{
	int i = first, m = mid;
	int j = mid + 1, n = last;
	int k = 0;
	while (i <= m && j <= n)//前一半与后一半比较，输入小的到辅助数组temp[]中
	{
		if (a[i] <= a[j])
			temp[k++] = a[i++];
		else
			temp[k++] = a[j++];
	}

	while (i <= m)//将剩下的没有比较的继续插到后面
		temp[k++] = a[i++];

	while (j <= n)
		temp[k++] = a[j++];

	for (i = 0; i < k; i++)
		a[first + i] = temp[i];
}
void mergesort(int p[], int first, int last, int temp[])
{
	if (first < last)
	{
		int mid = (first + last) / 2;
		mergesort(p, first, mid, temp);    //左边有序
		mergesort(p, mid + 1, last, temp); //右边有序
		mergearray(p, first, mid, last, temp); //再将二个有序数列合并
	}
}
void quick_sort(int *p, int left, int right)
{
	int i, j, temp;//temp保存基准数
	if (left < right)
	{
		i = left; j = right;
		temp = p[left];
		while (i < j)
		{
			while (p[j] >= temp && i < j)//先找右边
				j--;
			if (i < j)
				p[i++] = p[j];
			while (p[i] <= temp && i < j)//再找左边
				i++;
			if (i < j)
				p[j--] = p[i];
			p[i] = temp;//将基准数归位

		}
		quick_sort(p, left, i - 1);
		quick_sort(p, i + 1, right);
	}
}
int main()
{
	srand(unsigned(time(NULL)));
	int n,x;
	cout << "请输入元素的个数:";
	cin >> n;
	cout << "选择方法\n1.冒泡排序\n2.选择排序\n3.插入排序\n4.希尔排序\n5.归并排序\n6.快速排序\n";
	cin >> x;
	int *p = new int[n];
	int *a = new int[n];
	for (int i = 0; i < n; i++)
	{
		p[i] = rand();
	}
	if (p == NULL)
		return false;
	switch (x)
	{
	case(1):bubble_sort(p,n); break;
	case(2):selection_sort(p, n); break;
	case(3):insertion_sort(p, n); break;
	case(4):shell_sort2(p, n); break;
	case(5):mergesort(p, 0, n - 1, a); break;
	case(6):quick_sort(p, 0, n - 1); break;
	default:
		break;
	}
	for (int i = 0; i < n; i++)
	{
		cout << p[i] << " ";
	}
	delete[] p;
	return true;
}
```
