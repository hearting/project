#pragma once
typedef int SElemType;
typedef int Status;
#define STACK_INIT_SIZE 100
#define STACKINCREMENT  10
#define  OK      0
//栈结构体
typedef struct
{
	SElemType *base;
	SElemType *top;
	int stacksize;
}SqStack;
int InitStack(SqStack &S);//初始化栈
int Push(SqStack &S, SElemType e);//压栈
int GetTop(SqStack S, SElemType &e);//取栈顶元素
int Pop(SqStack &S, SElemType &e);//弹栈
int ClearStack(SqStack &S);//清理栈
Status DestroyStack(SqStack &S); //销毁栈
//float EvaluateExpression();