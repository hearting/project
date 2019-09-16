#include "pch.h"
#include<iostream>
#include "stack.h"
#include<string.h>
using namespace std;
//构建一个空栈
int InitStack(SqStack &S)
{
	S.base = (SElemType *)malloc(STACK_INIT_SIZE * sizeof(SElemType));
	if (!S.base) exit(1);
	S.top = S.base;
	S.stacksize = STACK_INIT_SIZE;
	return 0;
}
/*入栈*/
int Push(SqStack &S, SElemType e)
{
	if (S.top - S.base >= S.stacksize)
	{//栈满了，追加存储空间
		S.base = (SElemType *)realloc(S.base, (S.stacksize + STACKINCREMENT) * sizeof(SElemType));
		if (!S.base)exit(1);
		S.top = S.base + S.stacksize;
		S.stacksize += STACKINCREMENT;
	}
	*S.top++ = e;
	return 0;
}
//取栈顶
int GetTop(SqStack S, SElemType &e)
{
	if (S.top == S.base)
	{
		printf("栈为空！\n");
		exit(1);
	}
	e = *(S.top - 1);
	cout << e;
	return 0;
}
/*出栈*/
int Pop(SqStack &S, SElemType &e)
{
	if (S.top == S.base)
	{
		printf("错误\n\a");
	}
	else e = *--S.top;
	return 0;
}
//清理栈
int ClearStack(SqStack &S)
{
	S.top = S.base;
	return 0;
}
//销毁栈
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
//	initstack(optr);//运算符栈，寄存运算符
//	push(optr, '#');//#表示结束运算符，当#=#时运算结束
//	initstack(opnd);//运算数栈，寄存运算数和运算结果
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
//			case'<'://栈顶元素优先权低
//				push(optr, c);
//				c = getchar();
//				break;
//			case'='://脱括号并接收下一个字符
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