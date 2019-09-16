#include "pch.h"
#include <iostream>
#include<stdio.h>
#include "graph.h"
#include <queue>
#include <stack>
#include "stack.h"
using namespace std;
#define MVNum 100
bool visited[MVNum];

//��������ͼ
void CreateUDG(MGraph *G) {
	int i, j, k;
	char start, end; //�ߵ���ʼ����
	cout << "����ͼ�Ķ��������ͱ�����:\n";
	cin >> G->VertexNum >> G->EdgeNum;//����ͼ�������ͱ��� 
	for (i = 0; i < G->VertexNum; i++)  //��վ���
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MINVALUE; //���þ����и�Ԫ�ص�ֵΪ��Сֵ
	}
	cout << "�����������Ϣ\n";
	for (i = 0; i < G->VertexNum; i++) //���붥�� 
	{
		printf("��%d������:", i + 1);
		cin >> G->Vertex[i]; //���浽����������Ԫ����
	}
	printf("���빹�ɸ��ߵ���������:\n");
	for (k = 0; k < G->EdgeNum; k++)  //����ߵ���Ϣ 
	{
		printf("��%d���ߣ�", k + 1);
		cin >> start >> end;
		for (i = 0; start != G->Vertex[i]; i++); //�����ж����в���ʼ�� 
		{
			for (j = 0; end != G->Vertex[j]; j++); //�����ж����в��ҽ��յ�
			{
				G->Edges[i][j] = 1; //��Ӧλ�ñ���Ȩֵ����ʾ��һ����
				G->Edges[j][i] = 1;//�ڶԽ�λ�ñ���Ȩֵ
			}
		}
	}

}
//��������ͼ
void CreateDG(MGraph *G) {
	int i, j, k;
	char start, end; //�ߵ���ʼ����
	cout << "����ͼ�Ķ��������ͱ�����:\n";
	cin >> G->VertexNum >> G->EdgeNum;//����ͼ�������ͱ��� 
	for (i = 0; i < G->VertexNum; i++)  //��վ���
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MINVALUE; //���þ����и�Ԫ�ص�ֵΪ��Сֵ
	}
	cout << "�����������Ϣ\n";
	for (i = 0; i < G->VertexNum; i++) //���붥�� 
	{
		printf("��%d������:", i + 1);
		cin >> G->Vertex[i]; //���浽����������Ԫ����
	}
	printf("���빹�ɸ��ߵ���������:\n");
	for (k = 0; k < G->EdgeNum; k++)  //����ߵ���Ϣ 
	{
		printf("��%d���ߣ�", k + 1);
		cin >> start >> end;
		for (i = 0; start != G->Vertex[i]; i++); //�����ж����в���ʼ�� 
		{
			for (j = 0; end != G->Vertex[j]; j++); //�����ж����в��ҽ��յ�
			{
				G->Edges[i][j] = 1; //��Ӧλ�ñ���Ȩֵ����ʾ��һ����
			}
		}
	}

}
//����������
void CreateUDN(MGraph *G) {
	int i, j, k, weight;
	char start, end; //�ߵ���ʼ����
	cout << "����ͼ�Ķ��������ͱ�����:\n";
	cin >> G->VertexNum >> G->EdgeNum;//����ͼ�������ͱ��� 
	for (i = 0; i < G->VertexNum; i++)  //��վ���
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MAXVALUE; //���þ����и�Ԫ�ص�ֵΪ���ֵ
	}
	cout << "�����������Ϣ\n";
	for (i = 0; i < G->VertexNum; i++) //���붥�� 
	{
		printf("��%d������:", i + 1);
		cin >> G->Vertex[i]; //���浽����������Ԫ����
	}
	printf("���빹�ɸ��ߵ��������㼰Ȩֵ:\n");
	for (k = 0; k < G->EdgeNum; k++)  //����ߵ���Ϣ 
	{
		printf("��%d���ߣ�", k + 1);
		cin >> start >> end >> weight;
		for (i = 0; start != G->Vertex[i]; i++); //�����ж����в���ʼ�� 
		{
			for (j = 0; end != G->Vertex[j]; j++); //�����ж����в��ҽ��յ�
			{
				G->Edges[i][j] = weight; //��Ӧλ�ñ���Ȩֵ����ʾ��һ����
				G->Edges[j][i] = weight;//�ڶԽ�λ�ñ���Ȩֵ
			}
		}
	}
}
//����������
void CreateDN(MGraph *G) {
	int i, j, k, weight;
	char start, end; //�ߵ���ʼ����
	cout << "����ͼ�Ķ��������ͱ�����:\n";
	cin >> G->VertexNum >> G->EdgeNum;//����ͼ�������ͱ��� 
	for (i = 0; i < G->VertexNum; i++)  //��վ���
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MAXVALUE; //���þ����и�Ԫ�ص�ֵΪ���ֵ
	}
	cout << "�����������Ϣ\n";
	for (i = 0; i < G->VertexNum; i++) //���붥�� 
	{
		printf("��%d������:", i + 1);
		cin >> G->Vertex[i]; //���浽����������Ԫ����
	}
	printf("���빹�ɸ��ߵ��������㼰Ȩֵ:\n");
	for (k = 0; k < G->EdgeNum; k++)  //����ߵ���Ϣ 
	{
		printf("��%d���ߣ�", k + 1);
		cin >> start >> end >> weight;
		for (i = 0; start != G->Vertex[i]; i++); //�����ж����в���ʼ�� 
		{
			for (j = 0; end != G->Vertex[j]; j++); //�����ж����в��ҽ��յ�
			{
				G->Edges[i][j] = weight; //��Ӧλ�ñ���Ȩֵ����ʾ��һ����
			}
		}
	}
}
//������ȱ�������
void BFSfunction(MGraph *G, int i)
{
	int  j;
	queue<int> Q;
	G->isTrav[i] = 1;//��ʾ��������Ѿ�����������
	cout << "->" << G->Vertex[i];//�����һ������
	Q.push(i);	//�����
	while (!Q.empty())
	{
		Q.pop();
		for (j = 0; j < G->VertexNum; j++)
		{
			if (G->Edges[i][j] != MINVALUE && !G->isTrav[j])
			{
				cout << "->" << G->Vertex[j];
				G->isTrav[j] = 1;
				Q.push(j);
			}
		}
	}
}
//������ȱ���
void BFSTraverse(MGraph *G)
{
	int i;
	for (i = 0; i < G->VertexNum; i++)	//��ʾ��������Ϊ������
		G->isTrav[i] = 0;
	for (i = 0; i < G->VertexNum; i++)
	{
		if (!G->isTrav[i])	//��δ������
			BFSfunction(G, i);//����һ��
	}
}
//������ȱ�������
void DFSfunction(MGraph *G, int i)
{
	int j = 0;
	G->isTrav[i] = 1;//��Ǹö����Ѿ���������
	cout << "->" << G->Vertex[i];//�����һ�������Ķ�����Ϣ
	for (j = 0; j < G->VertexNum; j++)//ѭ��������������
	{
		if (G->Edges[i][j] != MINVALUE && !G->isTrav[i])//�ý��������������������δ��������
		{
			DFSfunction(G, j);
		}
	}
}
//������ȱ���
void DFSTraverse(MGraph *G)
{
	int i;
	for (i = 0; i < G->VertexNum; i++)//��ʾ�����㻹û�б�����
	{
		G->isTrav[i] = 0;
	}
	for (i = 0; i < G->VertexNum; i++)
	{
		if (!G->isTrav[i])//��δ������
			DFSfunction(G, i);//����һ��
	}
	cout << '\n';
}
//FirstAdjVex����:����v�ĵ�һ���ڽӶ���.��v��G��û���ڽӶ���,�򷵻�"��"
int FirstAdjVex(MGraph G, int v) {
	for (int i = 0; i < G.VertexNum; i++)
	{
		if (G.Edges[v][i] == 1)
			return i;
	}
	return -1;
}
//NextAdjVex����:����v����һ���ڽӶ���.��w��v�����һ���ڽӵ�,�򷵻�"��"
int NextAdjVex(MGraph G, int v, int w) {
	for (int i = w + 1; i < G.VertexNum; i++)
	{
		if (G.Edges[v][i] == 1)
			return i;
	}
	return -1;
}
void DFS(MGraph G, int v) {
	visited[v] = true; //���ʵ�v������,���÷��ʱ�־������Ӧ����ֵΪtrue
	cout << G.Vertex[v] << " ";
	for (int w = FirstAdjVex(G, v); w >= 0; w = NextAdjVex(G, v, w)) //���μ��v�������ڽӵ�w
	{
		if (!visited[w]) //��v����δ���ʵ��ڽӶ���,�ݹ����DFS
			DFS(G, w);
	}
}
void DFSTraverse(MGraph G) {
	for (int i = 0; i < G.VertexNum; i++) //���ʱ�־�����ʼ��
		visited[i] = false;
	for (int i = 0; i < G.VertexNum; i++)
	{
		if (!visited[i]) //��Ϊ���ʵĶ������DFS
			DFS(G, i);
	}
}
//��������
int indegree[MAX];//�����������нڵ�����֮��
//����ͼ�и��ڵ�����
void FindInDegree(MGraph G) {//����ͼ�и��ڵ�����
	int i, j;
	for (i = 0; i < G.VertexNum; i++) {
		for (j = 0; j < G.VertexNum; j++)
			if (G.Edges[i][j])//��������֮����ڱ�ʱ������Լ�
				indegree[j] ++;
	}
}
int StackEmpty(SqStack S) {
	if (S.top - S.base == 0)
		return 1;
	return 0;
}
//����������
Status Toposort(MGraph G) {
	int i, j, count = 0;
	SqStack S;
	FindInDegree(G);
	InitStack(S);
	for (i = 0; i < G.VertexNum; ++i)
		if (!indegree[i])  Push(S, i);
	while (!StackEmpty(S)) {
		Pop(S, i);
		printf("%c\n", G.Vertex[i]);	count++;
		for (j = 0; j < G.VertexNum; j++) {
			if (G.Edges[i][j]) {
				if (!(--indegree[j]))     //ɾ�����Ӧ�ñ�
					Push(S, j);
			}
		}
	}
	if (count < G.VertexNum) return ERROR;
	else return OK;
}
