#define  ERROR   1
#define  OK      0
#define VERTEX_MAX 26   //ͼ����󶥵���  
#define MINVALUE 0 
#define MAXVALUE 32767 //���ֵ(����Ϊһ���������) 
#define MAX 20
typedef int Status;
//ͼ���ڽӾ���洢�ṹ����
typedef struct //�����ڽӾ���ͼ�ṹ 
{
	char Vertex[VERTEX_MAX]; //���涥����Ϣ(��Ż���ĸ)
	int Edges[VERTEX_MAX][VERTEX_MAX]; //����ߵ�Ȩ 
	int isTrav[VERTEX_MAX]; //������־ 
	int VertexNum; //�������� 
	int EdgeNum;//������ 
	int GraphType; //ͼ������  
}MGraph;
void CreateUDG(MGraph *G);
void CreateDG(MGraph *G);
void CreateUDN(MGraph *G);
void CreateDN(MGraph *G);
//������ȱ���
void BFSTraverse(MGraph *G);
//������ȱ�������
void DFSTraverse(MGraph *G);
Status Toposort(MGraph G);