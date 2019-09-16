#pragma once
const  int maxsize = 50;
typedef int ElemType;
typedef struct
{
	int i, j;//非零元的行下标，列下标
	ElemType e;
}triple;
typedef struct
{
	triple data[maxsize];
	int m, n, t;//矩阵行数，列数，非零元素个数
}matrix;
int creat_array();
int print_array();