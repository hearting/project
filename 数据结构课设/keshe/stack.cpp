#include "pch.h"
#include<iostream>
#include "stack.h"
#include<string.h>
using namespace std;
//����һ����ջ
int InitStack(SqStack &S)
{
	S.base = (SElemType *)malloc(STACK_INIT_SIZE * sizeof(SElemType));
	if (!S.base) exit(1);
	S.top = S.base;
	S.stacksize = STACK_INIT_SIZE;
	return 0;
}
/*��ջ*/
int Push(SqStack &S, SElemType e)
{
	if (S.top - S.base >= S.stacksize)
	{//ջ���ˣ�׷�Ӵ洢�ռ�
		S.base = (SElemType *)realloc(S.base, (S.stacksize + STACKINCREMENT) * sizeof(SElemType));
		if (!S.base)exit(1);
		S.top = S.base + S.stacksize;
		S.stacksize += STACKINCREMENT;
	}
	*S.top++ = e;
	return 0;
}
//ȡջ��
int GetTop(SqStack S, SElemType &e)
{
	if (S.top == S.base)
	{
		printf("ջΪ�գ�\n");
		exit(1);
	}
	e = *(S.top - 1);
	cout << e;
	return 0;
}
/*��ջ*/
int Pop(SqStack &S, SElemType &e)
{
	if (S.top == S.base)
	{
		printf("����\n\a");
	}
	else e = *--S.top;
	return 0;
}
//����ջ
int ClearStack(SqStack &S)
{
	S.top = S.base;
	return 0;
}
//����ջ
Status DestroyStack(SqStack &S) {
	free(S.base);
	S.stacksize = 0;
	S.top = S.base = NULL;
	return OK;
}
//int In(char c, char *OP) 
//{
//	int flag = 0;
//	int i = 0;
//	while (OP[i] != '\0') {
//		if (OP[i] == c) flag = 1;
//		i++;
//	}
//	return flag;
//}
//char Precede(char m, char n, char *OP) 
//{
//	unsigned char Prior[7][7] =
//	{ '>','>','<','<','<','>','>',
//	 '>','>','<','<','<','>','>',
//	 '>','>','>','>','<','>','>',
//	 '>','>','>','>','<','>','>',
//	 '<','<','<','<','<','=',' ',
//	 '>','>','>','>',' ','>','>',
//	 '<','<','<','<','<',' ','=', };
//	int i = 0; int j = 0;
//	while (m != OP[i]) i++;
//	while (n != OP[j]) j++;
//	return Prior[i][j];
//}
//float Opreate(float a, float b, char theta)
//{
//	switch (theta) {
//	case'+':return a+b;
//	case'-':return a-b;
//	case'/':return a/b;
//	case'*':return a*b;
//	default:return 0;
//	}
//}
//float EvaluateExpression()
//{
//	SqStack optr, opnd;
//	SElemType x,a,b, theta, c;
//	char OP[8] = { '+','-','*','/','(',')','#','\0' };
//	initstack(optr);//�����ջ���Ĵ������
//	push(optr, '#');//#��ʾ�������������#=#ʱ�������
//	initstack(opnd);//������ջ���Ĵ���������������
//	c = getchar();
//	while (c != '#' || gettop(optr) != '#')
//	{
//		if (!In(c,OP))
//		{
//			push(opnd, c);
//			c = getchar();
//		}
//		else
//			switch (Precede(gettop(optr),c,OP))
//			{
//			case'<'://ջ��Ԫ������Ȩ��
//				push(optr, c);
//				c = getchar();
//				break;
//			case'='://�����Ų�������һ���ַ�
//				pop(optr, x);
//				c = getchar();
//				break;
//			case'>':
//				pop(optr, theta);pop(opnd, b);pop(opnd, a);
//				push(opnd, Opreate(a, b,theta));
//				break;
//			default:
//				break;
//			}
//	}
//	return gettop(opnd);
//}