#pragma once
typedef char TElemType;
typedef int status;
typedef struct BiTNode
{
	TElemType data;
	struct BiTNode *lchild, *rchild;
} BiTNode, *Bitree;
status visit(TElemType e);//输出节点
status CreateBitree(Bitree &T);//创建二叉树  
status PreOrderTraverse(Bitree T, status(*visit)(TElemType e));//先序遍历二叉树
status InOrderTraverse(Bitree T, status(*visit)(TElemType e));//中序遍历二叉树
status PostOrderTraverse(Bitree T, status(*visit)(TElemType e));//后序遍历二叉树
int GetHeight(Bitree T); //计算树的深度
void Countleaf(Bitree T, int &num);// 计算叶子结点个数   
int getleaf(Bitree T);
void findparents(Bitree T);//查找双亲
int parent(Bitree T, char e);
int sibling(Bitree T, char e);//查找兄弟
void brother(Bitree T);