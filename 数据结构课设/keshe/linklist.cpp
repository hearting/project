// Linklist.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//
#include "pch.h"
#include "stdio.h"
#include<iostream>
#include"linklist.h"
Linklist L, p, s, q;
int n, i, e;
void Creatlist(Linklist &L, int &n)//新建链表
{
	L = (Linklist)malloc(sizeof(LNode));
	L->next = NULL;
	printf("请输入数字个数:");
	scanf_s("%d", &n);
	for (int i = n; i > 0; --i)
	{
		p = (Linklist)malloc(sizeof(LNode));
		scanf_s("%d", &p->data);
		p->next = L->next;
		L->next = p;
	}
}
int Insertlist(Linklist &L, int i, int e) //插入
{
	p = L; int j = 0;
	while (p&&j < i - 1)
	{
		p = p->next; ++j;
	}
	if (!p&&j > i - 1)
		return 0;
	else
		s = (Linklist)malloc(sizeof(LNode));
	s->data = e; s->next = p->next;
	p->next = s;
	return 1;
}
int Dellist(Linklist &L, int i, int e)//删除
{
	p = L; int j = 0;
	while (j < i - 1)
	{
		p = p->next; ++j;
	}
	if (!(p->next) && j > i - 1)
		return 0;
	q = p->next; p->next = q->next;
	e = q->data; free(q);
	return e;
}
int Findlist(Linklist &L, int i)//查找
{
	p = L;
	if (p && i > 0)
	{
		for (int j = 0; j < i; j++)
		{
			p = p->next;
		}
		printf("%d\n", p->data);
		return 1;
	}
	else
		return 0;
}
void print(Linklist &L)//输出
{
	p = L->next;
	for (i = 1; p != NULL; i++)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("\n");
}
//创建链表
void create(Linklist &L) {
	char flag = 'y';
	Linklist p, s;
	p = L;
	puts("创建一个通讯录：");
	while (flag == 'Y' || flag == 'y') {
		//初始化生成块
		s = (Linklist)malloc(sizeof(LNode));
		printf("姓名：");
		scanf("%s", s->name);
		printf("号码：");
		scanf("%s", s->number);
		//尾插法核心语句开始
		s->next = NULL;//注意封口
		p->next = s;
		p = s;
		//尾插法核心语句结束
		//吃换行符
		getchar();
		printf("继续输入？(y/n)");
		scanf("%c", &flag);
	}
}
//输出链表
void output(Linklist &L) {
	p = L->next;
	puts("编号\t\t\t姓名\t\t\t号码");//表头
	//puts("======\t\t\t======\t\t\t======");//华丽的分割线
	int count = 1;
	while (p) {
		printf("%d\t\t\t%s\t\t\t%s\n", count, p->name, p->number);
		p = p->next;
		count++;
	}
}
//修改链表中的某一项
void update(Linklist &L) {
	p = L;
	int n;
	puts("需要修改哪一项？");
	scanf("%d", &n);
	for (int i = 1; i < n + 1; i++) {
		p = p->next;//把指针移位到需要修改的一项
	}
	if (p == NULL) {
		puts("没有这项！");
	}
	else {
		printf("姓名：");
		scanf("%s", p->name);
		printf("号码：");
		scanf("%s", p->number);
	}
}
//在链表尾部插入一项
void insert(Linklist &L) {
	p = L;
	while (p->next) {//尾插法，直接把指针移位到尾部
		p = p->next;
	}
	//初始化生成块
	s = (Linklist)malloc(sizeof(LNode));
	printf("姓名：");
	scanf("%s", s->name);
	printf("号码：");
	scanf("%s", s->number);
	//尾插法核心语句开始
	s->next = NULL;//注意封口
	p->next = s;
	//只插入一项，无须p=s
	//尾插法核心语句结束
}
//在链表中删除一项
void del(Linklist &L) {//不写delete是因为delete是C语言的关键字
	p = L;
	int n;
	puts("需要删除哪一项？");
	scanf("%d", &n);
	//这里的指针定位不同于修改，我们需要把指针定位到要删除的前一项，进行删除
	for (int i = 1; i < n; i++) {
		p = p->next;
	}
	if (p == NULL) {
		puts("没有这项！");
	}
	else {//删除的核心语句
		q = p->next;
		p->next = q->next;
		free(q);
	}
}
void find(Linklist &L, char name[20])
{
	Linklist p1;
	int i = 0;
	p1 = L;
	while(p1->next != NULL && strcmp(p1->name,name)!=0)
	{
		p1 = p1->next;	
		i++;
	}
	if(strcmp(p1->name,name)==0)
	{
		printf("序号\t姓名\t号码\t\n");
		printf("%d\t",i);
		printf("%s\t",p1->name);
		printf("%s\t", p1->number);
		printf("\n");
	}
	else
		printf("目前通讯录没这个宝宝！\n");	
}