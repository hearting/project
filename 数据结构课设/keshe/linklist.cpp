// Linklist.cpp : ���ļ����� "main" ����������ִ�н��ڴ˴���ʼ��������
//
#include "pch.h"
#include "stdio.h"
#include<iostream>
#include"linklist.h"
Linklist L, p, s, q;
int n, i, e;
void Creatlist(Linklist &L, int &n)//�½�����
{
	L = (Linklist)malloc(sizeof(LNode));
	L->next = NULL;
	printf("���������ָ���:");
	scanf_s("%d", &n);
	for (int i = n; i > 0; --i)
	{
		p = (Linklist)malloc(sizeof(LNode));
		scanf_s("%d", &p->data);
		p->next = L->next;
		L->next = p;
	}
}
int Insertlist(Linklist &L, int i, int e) //����
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
int Dellist(Linklist &L, int i, int e)//ɾ��
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
int Findlist(Linklist &L, int i)//����
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
void print(Linklist &L)//���
{
	p = L->next;
	for (i = 1; p != NULL; i++)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("\n");
}
//��������
void create(Linklist &L) {
	char flag = 'y';
	Linklist p, s;
	p = L;
	puts("����һ��ͨѶ¼��");
	while (flag == 'Y' || flag == 'y') {
		//��ʼ�����ɿ�
		s = (Linklist)malloc(sizeof(LNode));
		printf("������");
		scanf("%s", s->name);
		printf("���룺");
		scanf("%s", s->number);
		//β�巨������俪ʼ
		s->next = NULL;//ע����
		p->next = s;
		p = s;
		//β�巨����������
		//�Ի��з�
		getchar();
		printf("�������룿(y/n)");
		scanf("%c", &flag);
	}
}
//�������
void output(Linklist &L) {
	p = L->next;
	puts("���\t\t\t����\t\t\t����");//��ͷ
	//puts("======\t\t\t======\t\t\t======");//�����ķָ���
	int count = 1;
	while (p) {
		printf("%d\t\t\t%s\t\t\t%s\n", count, p->name, p->number);
		p = p->next;
		count++;
	}
}
//�޸������е�ĳһ��
void update(Linklist &L) {
	p = L;
	int n;
	puts("��Ҫ�޸���һ�");
	scanf("%d", &n);
	for (int i = 1; i < n + 1; i++) {
		p = p->next;//��ָ����λ����Ҫ�޸ĵ�һ��
	}
	if (p == NULL) {
		puts("û�����");
	}
	else {
		printf("������");
		scanf("%s", p->name);
		printf("���룺");
		scanf("%s", p->number);
	}
}
//������β������һ��
void insert(Linklist &L) {
	p = L;
	while (p->next) {//β�巨��ֱ�Ӱ�ָ����λ��β��
		p = p->next;
	}
	//��ʼ�����ɿ�
	s = (Linklist)malloc(sizeof(LNode));
	printf("������");
	scanf("%s", s->name);
	printf("���룺");
	scanf("%s", s->number);
	//β�巨������俪ʼ
	s->next = NULL;//ע����
	p->next = s;
	//ֻ����һ�����p=s
	//β�巨����������
}
//��������ɾ��һ��
void del(Linklist &L) {//��дdelete����Ϊdelete��C���ԵĹؼ���
	p = L;
	int n;
	puts("��Ҫɾ����һ�");
	scanf("%d", &n);
	//�����ָ�붨λ��ͬ���޸ģ�������Ҫ��ָ�붨λ��Ҫɾ����ǰһ�����ɾ��
	for (int i = 1; i < n; i++) {
		p = p->next;
	}
	if (p == NULL) {
		puts("û�����");
	}
	else {//ɾ���ĺ������
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
		printf("���\t����\t����\t\n");
		printf("%d\t",i);
		printf("%s\t",p1->name);
		printf("%s\t", p1->number);
		printf("\n");
	}
	else
		printf("ĿǰͨѶ¼û���������\n");	
}