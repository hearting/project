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

//构造无向图
void CreateUDG(MGraph *G) {
	int i, j, k;
	char start, end; //边的起始顶点
	cout << "输入图的顶点数量和边数量:\n";
	cin >> G->VertexNum >> G->EdgeNum;//输入图顶点数和边数 
	for (i = 0; i < G->VertexNum; i++)  //清空矩阵
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MINVALUE; //设置矩阵中各元素的值为最小值
	}
	cout << "输入各顶点信息\n";
	for (i = 0; i < G->VertexNum; i++) //输入顶点 
	{
		printf("第%d个顶点:", i + 1);
		cin >> G->Vertex[i]; //保存到各顶点数组元素中
	}
	printf("输入构成各边的两个顶点:\n");
	for (k = 0; k < G->EdgeNum; k++)  //输入边的信息 
	{
		printf("第%d条边：", k + 1);
		cin >> start >> end;
		for (i = 0; start != G->Vertex[i]; i++); //在已有顶点中查找始点 
		{
			for (j = 0; end != G->Vertex[j]; j++); //在已有顶点中查找结终点
			{
				G->Edges[i][j] = 1; //对应位置保存权值，表示有一条边
				G->Edges[j][i] = 1;//在对角位置保存权值
			}
		}
	}

}
//构造有向图
void CreateDG(MGraph *G) {
	int i, j, k;
	char start, end; //边的起始顶点
	cout << "输入图的顶点数量和边数量:\n";
	cin >> G->VertexNum >> G->EdgeNum;//输入图顶点数和边数 
	for (i = 0; i < G->VertexNum; i++)  //清空矩阵
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MINVALUE; //设置矩阵中各元素的值为最小值
	}
	cout << "输入各顶点信息\n";
	for (i = 0; i < G->VertexNum; i++) //输入顶点 
	{
		printf("第%d个顶点:", i + 1);
		cin >> G->Vertex[i]; //保存到各顶点数组元素中
	}
	printf("输入构成各边的两个顶点:\n");
	for (k = 0; k < G->EdgeNum; k++)  //输入边的信息 
	{
		printf("第%d条边：", k + 1);
		cin >> start >> end;
		for (i = 0; start != G->Vertex[i]; i++); //在已有顶点中查找始点 
		{
			for (j = 0; end != G->Vertex[j]; j++); //在已有顶点中查找结终点
			{
				G->Edges[i][j] = 1; //对应位置保存权值，表示有一条边
			}
		}
	}

}
//构造无向网
void CreateUDN(MGraph *G) {
	int i, j, k, weight;
	char start, end; //边的起始顶点
	cout << "输入图的顶点数量和边数量:\n";
	cin >> G->VertexNum >> G->EdgeNum;//输入图顶点数和边数 
	for (i = 0; i < G->VertexNum; i++)  //清空矩阵
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MAXVALUE; //设置矩阵中各元素的值为最大值
	}
	cout << "输入各顶点信息\n";
	for (i = 0; i < G->VertexNum; i++) //输入顶点 
	{
		printf("第%d个顶点:", i + 1);
		cin >> G->Vertex[i]; //保存到各顶点数组元素中
	}
	printf("输入构成各边的两个顶点及权值:\n");
	for (k = 0; k < G->EdgeNum; k++)  //输入边的信息 
	{
		printf("第%d条边：", k + 1);
		cin >> start >> end >> weight;
		for (i = 0; start != G->Vertex[i]; i++); //在已有顶点中查找始点 
		{
			for (j = 0; end != G->Vertex[j]; j++); //在已有顶点中查找结终点
			{
				G->Edges[i][j] = weight; //对应位置保存权值，表示有一条边
				G->Edges[j][i] = weight;//在对角位置保存权值
			}
		}
	}
}
//构造有向网
void CreateDN(MGraph *G) {
	int i, j, k, weight;
	char start, end; //边的起始顶点
	cout << "输入图的顶点数量和边数量:\n";
	cin >> G->VertexNum >> G->EdgeNum;//输入图顶点数和边数 
	for (i = 0; i < G->VertexNum; i++)  //清空矩阵
	{
		for (j = 0; j < G->VertexNum; j++)
			G->Edges[i][j] = MAXVALUE; //设置矩阵中各元素的值为最大值
	}
	cout << "输入各顶点信息\n";
	for (i = 0; i < G->VertexNum; i++) //输入顶点 
	{
		printf("第%d个顶点:", i + 1);
		cin >> G->Vertex[i]; //保存到各顶点数组元素中
	}
	printf("输入构成各边的两个顶点及权值:\n");
	for (k = 0; k < G->EdgeNum; k++)  //输入边的信息 
	{
		printf("第%d条边：", k + 1);
		cin >> start >> end >> weight;
		for (i = 0; start != G->Vertex[i]; i++); //在已有顶点中查找始点 
		{
			for (j = 0; end != G->Vertex[j]; j++); //在已有顶点中查找结终点
			{
				G->Edges[i][j] = weight; //对应位置保存权值，表示有一条边
			}
		}
	}
}
//广度优先遍历函数
void BFSfunction(MGraph *G, int i)
{
	int  j;
	queue<int> Q;
	G->isTrav[i] = 1;//表示这个顶点已经被遍历过了
	cout << "->" << G->Vertex[i];//输出第一个顶点
	Q.push(i);	//入队列
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
//广度优先遍历
void BFSTraverse(MGraph *G)
{
	int i;
	for (i = 0; i < G->VertexNum; i++)	//表示其它顶点为被遍历
		G->isTrav[i] = 0;
	for (i = 0; i < G->VertexNum; i++)
	{
		if (!G->isTrav[i])	//若未被遍历
			BFSfunction(G, i);//遍历一下
	}
}
//深度优先遍历函数
void DFSfunction(MGraph *G, int i)
{
	int j = 0;
	G->isTrav[i] = 1;//标记该顶点已经被遍历过
	cout << "->" << G->Vertex[i];//输出第一个遍历的顶点信息
	for (j = 0; j < G->VertexNum; j++)//循环遍历其它顶点
	{
		if (G->Edges[i][j] != MINVALUE && !G->isTrav[i])//该结点有相连的其它顶点且未被遍历且
		{
			DFSfunction(G, j);
		}
	}
}
//深度优先遍历
void DFSTraverse(MGraph *G)
{
	int i;
	for (i = 0; i < G->VertexNum; i++)//表示各顶点还没有被遍历
	{
		G->isTrav[i] = 0;
	}
	for (i = 0; i < G->VertexNum; i++)
	{
		if (!G->isTrav[i])//若未被遍历
			DFSfunction(G, i);//遍历一下
	}
	cout << '\n';
}
//FirstAdjVex函数:返回v的第一个邻接顶点.若v在G种没有邻接顶点,则返回"空"
int FirstAdjVex(MGraph G, int v) {
	for (int i = 0; i < G.VertexNum; i++)
	{
		if (G.Edges[v][i] == 1)
			return i;
	}
	return -1;
}
//NextAdjVex函数:返回v的下一个邻接顶点.若w是v的最后一个邻接点,则返回"空"
int NextAdjVex(MGraph G, int v, int w) {
	for (int i = w + 1; i < G.VertexNum; i++)
	{
		if (G.Edges[v][i] == 1)
			return i;
	}
	return -1;
}
void DFS(MGraph G, int v) {
	visited[v] = true; //访问第v个顶点,并置访问标志数组相应分量值为true
	cout << G.Vertex[v] << " ";
	for (int w = FirstAdjVex(G, v); w >= 0; w = NextAdjVex(G, v, w)) //依次检查v的所有邻接点w
	{
		if (!visited[w]) //对v的尚未访问的邻接顶点,递归调用DFS
			DFS(G, w);
	}
}
void DFSTraverse(MGraph G) {
	for (int i = 0; i < G.VertexNum; i++) //访问标志数组初始化
		visited[i] = false;
	for (int i = 0; i < G.VertexNum; i++)
	{
		if (!visited[i]) //对为访问的顶点调用DFS
			DFS(G, i);
	}
}
//拓扑排序
int indegree[MAX];//用来计算所有节点的入度之和
//计算图中各节点的入度
void FindInDegree(MGraph G) {//计算图中各节点的入度
	int i, j;
	for (i = 0; i < G.VertexNum; i++) {
		for (j = 0; j < G.VertexNum; j++)
			if (G.Edges[i][j])//当两顶点之间存在边时，入度自加
				indegree[j] ++;
	}
}
int StackEmpty(SqStack S) {
	if (S.top - S.base == 0)
		return 1;
	return 0;
}
//拓扑排序函数
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
				if (!(--indegree[j]))     //删除相对应得边
					Push(S, j);
			}
		}
	}
	if (count < G.VertexNum) return ERROR;
	else return OK;
}
