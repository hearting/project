#pragma once
typedef int SElemType;
typedef int Status;
#define STACK_INIT_SIZE 100
#define STACKINCREMENT  10
#define  OK      0
//ջ�ṹ��
typedef struct
{
	SElemType *base;
	SElemType *top;
	int stacksize;
}SqStack;
int InitStack(SqStack &S);//��ʼ��ջ
int Push(SqStack &S, SElemType e);//ѹջ
int GetTop(SqStack S, SElemType &e);//ȡջ��Ԫ��
int Pop(SqStack &S, SElemType &e);//��ջ
int ClearStack(SqStack &S);//����ջ
Status DestroyStack(SqStack &S); //����ջ
//float EvaluateExpression();