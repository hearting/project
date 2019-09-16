#include "pch.h"
#include <iostream>
#include "binarytree.h"
using namespace std;
status visit(TElemType e)
{
	cout << e;
	return 1;
}
status CreateBitree(Bitree &T)//��������򴴽�����������е�ֵ���ո��ַ���ʾ����
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
status PreOrderTraverse(Bitree T, status(*visit)(TElemType e))//��������ݹ���ã�������
{
	if (T)
	{
		visit(T->data);
		PreOrderTraverse(T->lchild, visit);
		PreOrderTraverse(T->rchild, visit);
	}
	return 0;
}
status InOrderTraverse(Bitree T, status(*visit)(TElemType e))//���������������
{
	if (T)
	{
		InOrderTraverse(T->lchild, visit);
		visit(T->data);
		InOrderTraverse(T->rchild, visit);
	}
	return 0;
}
status PostOrderTraverse(Bitree T, status(*visit)(TElemType e))//���������������
{
	if (T)
	{
		PostOrderTraverse(T->lchild, visit);
		PostOrderTraverse(T->rchild, visit);
		visit(T->data);
	}
	return 0;
}
int GetHeight(Bitree T)//�������߶�
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
int getleaf(Bitree T)//����Ҷ�ӽڵ����
{
	int num = 0;
	Countleaf(T, num);
	return num;
}
void findparents(Bitree T)//����˫��
{
	char ch1;
	/*PreOrderTraverse(T,visit);*/
	cout << "��������Ҫ����˫�׵Ľڵ�\n";
	cin >> ch1;
	if(parent(T, ch1))
	{
		cout << "������Ĳ��Ǹ����Ľڵ�\n";
	}
}
int parent(Bitree T,char e)
{
	if (T->data == e)
	{
		cout << "�ýڵ�Ϊ���ڵ�\n";
			return 0;
	}
	if (T)
	{
		if (T->lchild && (T->lchild->data == e) || T->rchild && (T->rchild->data == e))
		{
			cout << e << "��˫��Ϊ" << T->data;
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
	cout << "��������Ҫ�����ֵܽڵ�Ľڵ�\n";
	cin >> ch2;
	if (sibling(T,ch2))
	{
		cout << "��������ַ����ڴ˶�������\n";
	}
}
int sibling(Bitree T, char e)
{
	if (T->data==e)
	{
		cout << "�ýڵ�Ϊ���ڵ㣬û�����ֵܺ����ֵ�\n";
			return 0;
	}
	if (T)
	{
		if (T->rchild&&(T->rchild->data==e))
		{
			if (T->lchild)
				cout << e << "�����ֵ���" << T->lchild->data<<"��û�����ֵ�\n";
			else
				cout << e << "û�����ֵܺ����ֵ�\n";
			return 0;
		}
		else if (T->lchild&&(T->lchild->data==e))
		{
			if (T->rchild)
				cout << e << "û�����ֵܣ����ֵ���" << T->rchild->data << endl;
			else
				cout << e << "û�����ֵܺ����ֵ�\n";
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