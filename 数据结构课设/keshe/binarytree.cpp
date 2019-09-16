#include "pch.h"
#include <iostream>
#include "binarytree.h"
using namespace std;
status visit(TElemType e)
{
	cout << e;
	return 1;
}
status CreateBitree(Bitree &T)//按先序次序创建二叉树结点中的值，空格字符表示空树
{
	char ch;
	cin >> ch;
	if (ch == '#')
		T = NULL;
	else
	{
		if (!(T = (BiTNode*)malloc(sizeof(BiTNode))))
			exit(OVERFLOW);
		else
			T->data = ch;
		CreateBitree(T->lchild);
		CreateBitree(T->rchild);
	}
	return 1;
}
status PreOrderTraverse(Bitree T, status(*visit)(TElemType e))//先序遍历递归调用，根左右
{
	if (T)
	{
		visit(T->data);
		PreOrderTraverse(T->lchild, visit);
		PreOrderTraverse(T->rchild, visit);
	}
	return 0;
}
status InOrderTraverse(Bitree T, status(*visit)(TElemType e))//中序遍历，左中右
{
	if (T)
	{
		InOrderTraverse(T->lchild, visit);
		visit(T->data);
		InOrderTraverse(T->rchild, visit);
	}
	return 0;
}
status PostOrderTraverse(Bitree T, status(*visit)(TElemType e))//后序遍历，左右中
{
	if (T)
	{
		PostOrderTraverse(T->lchild, visit);
		PostOrderTraverse(T->rchild, visit);
		visit(T->data);
	}
	return 0;
}
int GetHeight(Bitree T)//二叉树高度
{
	int hl, hr, max;
	if (T != NULL)
	{
		if ((T->lchild == NULL) && (T->rchild == NULL))
			return 1;
		else
		{
			hl = GetHeight(T->lchild);
			hr = GetHeight(T->rchild);
			max = hl > hr ? hl : hr;
			return (max + 1);
		}
	}
	else
		return 0;
}
void Countleaf(Bitree T, int &num)
{
	if (T != NULL)
	{
		if ((T->lchild == NULL) && (T->rchild == NULL))
		{
			num++;
		}
		Countleaf(T->lchild, num);
		Countleaf(T->rchild, num);
	}
}
int getleaf(Bitree T)//计算叶子节点个数
{
	int num = 0;
	Countleaf(T, num);
	return num;
}
void findparents(Bitree T)//查找双亲
{
	char ch1;
	/*PreOrderTraverse(T,visit);*/
	cout << "请输入需要查找双亲的节点\n";
	cin >> ch1;
	if(parent(T, ch1))
	{
		cout << "你输入的不是该树的节点\n";
	}
}
int parent(Bitree T,char e)
{
	if (T->data == e)
	{
		cout << "该节点为根节点\n";
			return 0;
	}
	if (T)
	{
		if (T->lchild && (T->lchild->data == e) || T->rchild && (T->rchild->data == e))
		{
			cout << e << "的双亲为" << T->data;
			return 0;
		}
		else
		{
			if (T->lchild)
			{
				if (!parent(T->lchild, e))
					return 0;
			}
		
			if (T->rchild)
			{
				if (!parent(T->rchild, e))
					return 0;
			}
		}return 1;
	}
	return 1;
}
void brother(Bitree T)
{
	char ch2;
	cout << "请输入需要查找兄弟节点的节点\n";
	cin >> ch2;
	if (sibling(T,ch2))
	{
		cout << "您输入的字符不在此二叉树内\n";
	}
}
int sibling(Bitree T, char e)
{
	if (T->data==e)
	{
		cout << "该节点为根节点，没有左兄弟和右兄弟\n";
			return 0;
	}
	if (T)
	{
		if (T->rchild&&(T->rchild->data==e))
		{
			if (T->lchild)
				cout << e << "的左兄弟是" << T->lchild->data<<"，没有右兄弟\n";
			else
				cout << e << "没有左兄弟和右兄弟\n";
			return 0;
		}
		else if (T->lchild&&(T->lchild->data==e))
		{
			if (T->rchild)
				cout << e << "没有左兄弟，右兄弟是" << T->rchild->data << endl;
			else
				cout << e << "没有左兄弟和右兄弟\n";
			return 0;

		}
		else
		{
			if (T->lchild)
			  if (!sibling(T->lchild,e))
				return 0;
			if (T->rchild)
				if (!sibling(T->lchild, e))
					return 0;
		}
	}
	return 1;
}