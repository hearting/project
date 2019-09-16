// keshe.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include "linklist.h"
#include "stack.h"
#include "array.h"
#include "binarytree.h"
#include "graph.h"
using namespace std;
void ShowMainMenu() {
	printf("\n");
	printf("*******************算法与数据结构******************\n");
	printf("* 1  单链表的基本操作及应用                       *\n");
	printf("* 2  栈的基本操作及应用                           *\n");
	printf("* 3  数组的基本操作及应用                         *\n");
	printf("* 4  树的基本操作及应用                           *\n");
	printf("* 5  图的基本操作及应用                           *\n");
	printf("* 6  退出                                         *\n");
	printf("***************************************************\n");
}

void LinkList() {
	Linklist L, p, s, q;
	int n, i, e, a;
	do {
		printf("\n");
		printf("**************单链表的基本操作及应用***************\n");
		printf("* 1  创建                                         *\n");
		printf("* 2  插入                                         *\n");
		printf("* 3  删除                                         *\n");
		printf("* 4  查找                                         *\n");
		printf("* 5  显示                                         *\n");
		printf("* 6  通讯录（应用）                               *\n");
		printf("* 7  退出                                         *\n");
		printf("***************************************************\n");
		printf("请选择：");
		scanf("%d", &a);
		switch (a) {
		case 1:
			printf("--------创建单链表---------\n");
			Creatlist(L, n);
			break;
		case 2:
			printf("--------插入元素-------\n"); 
			printf("请输入插入位置和数字:\n");
			scanf_s("%d%d", &i, &e);
			Insertlist(L, i, e);
			break;
		case 3:
			printf("--------删除元素-------\n");
			printf("请输入删除第几个数\n");
			scanf_s("%d", &i);
			Dellist(L, i, e);
			break;
		case 4:
			printf("--------查找元素-------\n");
			printf("请输入查找的数\n");
			scanf_s("%d", &i);
			Findlist(L, i);
			break;
		case 5:
			printf("--------显示链表-------"); 
			print(L); 
			break;
		case 6:
		{
		printf("--------通讯录---------");
		Linklist L;
		int i = 1;
		L = (Linklist)malloc(sizeof(LNode));//熟记malloc的用法，词句为开创一个长为Linklist的空间，而后被L所指向
		L->next = NULL;//此处头结点的尾指针必须封口，否则不同的编译器会编译出不同的成分。
		while (i != 0) 
		{
			puts("\n功能：\n1.输出\t2.修改\t3.删除\t4.插入\t5.查找\t0.退出");
			scanf("%d", &i);
			switch (i) {
			case 1:
				output(L);
				break;
			case 2:
				update(L);
				break;
			case 3:
				del(L);
				break;
			case 4:
				insert(L);
				break;
			case 5:
			char name1[20];
			printf("请输入查找联系人的姓名：\n");
			scanf("%s", name1);
			find(L,name1); break;
			}
		  }
		}
			break;
		case 7: break;
		default:
			printf("ERROR!"); break;
		}
	} while (a != 7);
}

void Stack() {
	int a = 1, n;
	SElemType e;
	SqStack s;
	do {
		printf("\n");
		printf("****************栈的基本操作及应用*****************\n");
		printf("* 1  进栈                                         *\n");
		printf("* 2  出栈                                         *\n");
		printf("* 3  取栈顶元素                                   *\n");
		printf("* 4  表达式求解（应用）                           *\n");
		printf("* 5  退出                                         *\n");
		printf("***************************************************\n");
		printf("请选择：");
		scanf("%d", &n);
		switch (n) {
		case 1:
			InitStack(s);
			printf("--------进栈-------\n");
			cout << "请输入入栈元素个数\n";
			cin >> n;
			cout << "请输入入栈元素\n";
			for (int i = 0; i < n; i++)
			{			
				cin >> e;
				Push(s, e);
			}
			break;
		case 2:
			printf("--------出栈-------\n"); 
			Pop(s,e);
			break;
		case 3:
			printf("--------取栈顶元素-------\n"); 
			GetTop(s,e);
			break;
		case 4:
			printf("--------表达式求值-------\n");
			printf("请输入表达式：\t");
			/*printf("表达式的值为：  %f", EvaluateExpression());*/
			break;
		case 5:break;
		default:
			printf("ERROR!"); break;
		}
	} while (n != 5);
}


void Array() {
	int n;
	do {
		printf("\n");
		printf("*************稀疏矩阵的压缩存储及应用**************\n");
		printf("* 1  创建                                         *\n");
		printf("* 2  显示                                         *\n");
		printf("* 3  矩阵乘法（应用）                             *\n");
		printf("* 4  退出                                         *\n");
		printf("***************************************************\n");
		printf("请选择：");
		scanf("%d", &n);
		matrix *M;
		switch (n) {
		case 1:
			printf("---------创建-------");
			creat_array();
			break;
		case 2:
			printf("---------显示-------\n");
			print_array();
			break;
		case 3:
			printf("---------矩阵乘法-------"); break;
		case 4:break;
		default:
			printf("ERROR!"); break;
		}
	} while (n != 4);
}

void BiTree() {
	int n;
	do {
		printf("\n");
		printf("**************二叉树的基本操作及应用***************\n");
		printf("* 1  创建二叉树                                   *\n");
		printf("* 2  遍历二叉树（先/中/后）                       *\n");
		printf("* 3  计算树的深度                                 *\n");
		printf("* 4  计算叶子结点个数                             *\n");
		printf("* 5  查找双亲                                     *\n");
		printf("* 6  查找兄弟                                     *\n");
		printf("* 7  Huffman编码（应用）                          *\n");
		printf("* 8  退出\n");
		printf("***************************************************\n");
		printf("请选择：");
		scanf("%d", &n);
		switch (n) {
		case 1:
			printf("---------创建二叉树--------\n");
			cout << "请按先序序列输入,空的话用#代替:\n";
			Bitree T;
			CreateBitree(T);
			break;
		case 2:
			printf("---------*遍历二叉树-------\n");
			cout << "先序遍历\n";
			PreOrderTraverse(T,visit);
			cout << endl;
			cout << "中序遍历\n";
			InOrderTraverse(T, visit);
			cout << endl;
			cout << "后序遍历\n";
			PostOrderTraverse(T, visit);
			cout << endl;
			break;
		case 3:
			printf("---------计算树的深度------\n");
			cout << "树深度为：" << getleaf(T) << endl;
			break;
		case 4:
			printf("---------计算叶子结点个数-------\n");
			cout << "二叉树中叶子节点的个数为：" << getleaf(T)<<endl;
			break;
		case 5:
			printf("---------查找双亲-------\n");
			findparents(T);
			break;
		case 6:
			printf("---------查找兄弟-------\n");
			brother(T);
			break;
		case 7:
			printf("---------Huffman编码-------\n"); break;
		case 8:break;
		default:
			printf("ERROR!"); break;
		}
	} while (n != 8);
}

void Graph() {
	int n;
	MGraph G;
	do {
		printf("\n");
		printf("****************图的基本操作及应用*****************\n");
		printf("* 1  创建无向图                                    *\n");
		printf("* 2  创建无向网                                    *\n");
		printf("* 3  创建有向图                                    *\n");
		printf("* 4  创建有向网                                    *\n");
		printf("* 5  遍历                                          *\n");
		printf("* 6  拓扑排序                                      *\n");
		printf("* 7  最小生成树（应用）                            *\n");
		printf("* 8  最短路径（应用）                              *\n");
		printf("* 9  关键路径（应用）                              *\n");
		printf("* 10 退出                                          *\n");
		printf("***************************************************\n");
		printf("请选择：");
		scanf("%d", &n);
		switch (n) {
		case 1:
			printf("---------创建无向图-------\n");
			CreateUDG(&G); break;
			break;
		case 2:
			printf("---------创建无向网-------\n"); 
			CreateUDN(&G); break;
			break;
		case 3:
			printf("---------创建有向图-------\n"); 
			CreateDG(&G); break;
			break;
		case 4:
			printf("---------创建有向网-------\n"); 
			CreateDN(&G); break;
			break;
		case 5:
			printf("---------遍历-------\n"); 
			cout << "深度优先遍历结点："; DFSTraverse(&G);
			cout << "广度优先遍历结点："; BFSTraverse(&G); 
			break;

		case 6:
			printf("---------拓扑排序-------\n");
			printf("拓扑序列如下:\n");
			Toposort(G); 
			break;
		case 7:
			printf("---------最小生成树-------\n");
			break;
		case 8:
			printf("---------最短路径-------\n");
			break;
		case 9:
			printf("---------关键路径-------\n");
			break;
		case 10:break;
		default:
			printf("ERROR!"); break;
		}
	} while (n != 10);
}

int main() 
{
	int n,ch;
	do {
		ShowMainMenu();
		printf("请选择：");
		
		scanf("%d", &n);
		setbuf(stdin,NULL);
		switch (n) {
		case 1:LinkList(); break;
		case 2:Stack(); break;
		case 3:Array(); break;
		case 4:BiTree(); break;
		case 5:Graph(); break;
		case 6:break;
		default:printf("ERROR!"); break;
		}
	} while (n != 6);
}

