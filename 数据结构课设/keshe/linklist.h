#pragma once
typedef struct LNode
{
	int data;
	char name[20];//�������
	char number[15];//��ŵ绰����
	struct LNode *next;
}LNode, *Linklist;
void Creatlist(Linklist &L, int &n);//��������
int Insertlist(Linklist &L, int i, int e);//�������
int Dellist(Linklist &L, int i, int e);//ɾ������
int Findlist(Linklist &L, int i);//���Ҳ���
void print(Linklist &L);//�������
void create(Linklist &L);//��������
void output(Linklist &L);//�������
void update(Linklist &L);//�޸������е�ĳһ��
void insert(Linklist &L);//������β������һ��
void del(Linklist &L);//��������ɾ��һ��
void find(Linklist &L, char name[20]);