#pragma once
const  int maxsize = 50;
typedef int ElemType;
typedef struct
{
	int i, j;//����Ԫ�����±꣬���±�
	ElemType e;
}triple;
typedef struct
{
	triple data[maxsize];
	int m, n, t;//��������������������Ԫ�ظ���
}matrix;
int creat_array();
int print_array();