#pragma once
typedef struct LNode
{
	int data;
	char name[20];//存放名字
	char number[15];//存放电话号码
	struct LNode *next;
}LNode, *Linklist;
void Creatlist(Linklist &L, int &n);//创建链表
int Insertlist(Linklist &L, int i, int e);//插入操作
int Dellist(Linklist &L, int i, int e);//删除操作
int Findlist(Linklist &L, int i);//查找操作
void print(Linklist &L);//输出链表
void create(Linklist &L);//创建链表
void output(Linklist &L);//输出链表
void update(Linklist &L);//修改链表中的某一项
void insert(Linklist &L);//在链表尾部插入一项
void del(Linklist &L);//在链表中删除一项
void find(Linklist &L, char name[20]);