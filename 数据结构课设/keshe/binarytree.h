#pragma once
typedef char TElemType;
typedef int status;
typedef struct BiTNode
{
	TElemType data;
	struct BiTNode *lchild, *rchild;
} BiTNode, *Bitree;
status visit(TElemType e);//����ڵ�
status CreateBitree(Bitree &T);//����������  
status PreOrderTraverse(Bitree T, status(*visit)(TElemType e));//�������������
status InOrderTraverse(Bitree T, status(*visit)(TElemType e));//�������������
status PostOrderTraverse(Bitree T, status(*visit)(TElemType e));//�������������
int GetHeight(Bitree T); //�����������
void Countleaf(Bitree T, int &num);// ����Ҷ�ӽ�����   
int getleaf(Bitree T);
void findparents(Bitree T);//����˫��
int parent(Bitree T, char e);
int sibling(Bitree T, char e);//�����ֵ�
void brother(Bitree T);