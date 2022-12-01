import java.util.*;

public class Graph {
    private int V;  // 顶点数目
    private int E;  // 边的数目
    private Vertex<Integer>[] adj; //邻接表
    private Graph(int V) {
        this.V = V;
        this.E = 0;
        // 实例化邻接表
        adj = new Vertex[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Vertex<>();
            adj[i].setData(i);
        }
    }

    private void addEdge(int v, int w) {
        // 将w添加到v的链表中
        adj[v].add(new Edge(w, null));
        adj[w].add(new Edge(v, null));
        E++;
    }

    // 获得某个顶点的链表
    public Vertex getVertex(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public Graph(Scanner input) {
        // 读取V并初始化图
        /*this(input.nextInt());
        int E = input.nextInt();
        for (int i = 0; i < E; i++) {
            int v = input.nextInt();
            int w = input.nextInt();
            addEdge(v, w);
        }*/
        this(6);
        int E = 8;
        int a[][] = {
                {0, 5},
                {2, 4},
                {2, 3},
                {1, 2},
                {0, 1},
                {0, 4},
                {3, 5},
                {0, 2}
        };
        for (int i = 0; i < a.length; i++) {
            addEdge(a[i][0], a[i][1]);
        }
    }
}

