#include "pch.h"
#include "stdio.h"
#include<iostream>
#include "array.h"
using namespace std;
matrix mat;
int creat_array()
{
	printf("请输入非零元个数以及行数和列数\n");
	scanf("%d %d %d", &mat.t, &mat.m, &mat.n);
	printf("请输入非零元的所在行列以及非零元的值\n");
	for (int count = 1; count <= mat.t; count++)
	{
		//一共有data[t]个三元组
		scanf("%d %d %d", &mat.data[count].i, &mat.data[count].j, &mat.data[count].e);
	}
	return 0;
}
int print_array()
{
	int cou = 1;
	for (int i = 1; i <= mat.m; i++)
	{
		for (int j = 1; j <= mat.n; j++)
		{
			if ((i == mat.data[cou].i) && (j == mat.data[cou].j))
			{
				printf("%d ", mat.data[cou].e);
				cou++;
			}
			else
				printf("0 ");
		}
		printf("\n");
	}
	return 0;
}