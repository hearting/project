#define  ERROR   1
#define  OK      0
#define VERTEX_MAX 26   //图的最大顶点数  
#define MINVALUE 0 
#define MAXVALUE 32767 //最大值(可设为一个最大整数) 
#define MAX 20
typedef int Status;
//图的邻接矩阵存储结构定义
typedef struct //定义邻接矩阵图结构 
{
	char Vertex[VERTEX_MAX]; //保存顶点信息(序号或字母)
	int Edges[VERTEX_MAX][VERTEX_MAX]; //保存边的权 
	int isTrav[VERTEX_MAX]; //遍历标志 
	int VertexNum; //顶点数量 
	int EdgeNum;//边数量 
	int GraphType; //图的类型  
}MGraph;
void CreateUDG(MGraph *G);
void CreateDG(MGraph *G);
void CreateUDN(MGraph *G);
void CreateDN(MGraph *G);
//广度优先遍历
void BFSTraverse(MGraph *G);
//深度优先遍历函数
void DFSTraverse(MGraph *G);
Status Toposort(MGraph G);