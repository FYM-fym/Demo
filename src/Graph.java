import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    //顶点元素
    private ArrayList<Board> boardList; //每局棋盘的状况
    //邻接矩阵
    private int[][] edges;
    private int edgesNum;
    private boolean[] isSelected;
    //传入顶点个数
    public Graph(int x){
        this.boardList = new ArrayList<>();
        this.edges = new int[x][x];
        this.isSelected = new boolean[x];
    }

    //插入顶点方法
    public void insertVertex(Board newBoard){
        this.boardList.add(newBoard);

    }

    //添加图边
    public void insertEdges(int x, int y){
        //无向图
        edges[x][y] = 1;
        edges[y][x] = 1;
        edgesNum++;
    }

    //返回顶点个数
    public int getVertexSize(){
        return this.boardList.size();
    }

    //返回边的数量
    public int getEdgesSize(){
        return this.edgesNum;
    }


    //输出邻接矩阵图案
    public void showList(){
        for(int[] arr:edges){
            System.out.println(Arrays.toString(arr));
        }
    }

    //给定一个索引顶点位置，查找当前索引的第一个邻接点
    public int getFirstVertex(int index){
        for(int i = 0 ; i < boardList.size(); i++){
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据给定的坐标，获取下一个邻接顶点
    public int getNextVertex(int x, int y){
        for(int i = y + 1; i < boardList.size(); i++){
            if (edges[x][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //访问的顶点
    public Board getValueIndex(int i){
        return this.boardList.get(i);
    }


    public void bfs(boolean[] isSelected, int i){
        //取出头结点
        int u;
        //第一个邻接点
        int w ;
        //创建一个队列
        LinkedList queue = new LinkedList();
        System.out.print(getValueIndex(i));
        isSelected[i] = true;
        queue.addLast(i);
        while(!queue.isEmpty()){
            u = (Integer)queue.removeFirst();
            w = getFirstVertex(u);
            while(w != -1){
                if(!isSelected[w]){
                    System.out.print(getValueIndex(w));
                    isSelected[w] = true;
                    queue.addLast(w);
                }
                w = getNextVertex(i,w);
            }
        }
    }

    public void bfs(){
        for(int i = 0 ; i < getEdgesSize(); i++){
            if(!isSelected[i]){
                bfs(isSelected,i);
            }
        }
    }
}
