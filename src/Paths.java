import java.awt.*;
import java.util.ArrayDeque;
import java.util.Stack;


public class Paths {

    private boolean[] marked;
    private int[] edgeTo;     // 记录下标顶点上一次访问的最后一个顶点 - 用于后面的路径寻找
    private final int s;      // 起点

    public Paths(Graph G, int s) {
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
    }

    // 广搜记录起点s开始的路径到数组edgeTo
    public void bfs(Graph G) {
        bfs(G, s);
    }
    private void bfs(Graph G, int v) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        marked[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int curVertex = queue.remove();
            Vertex vertex = G.getVertex(curVertex);
            for (int i = 0; i < vertex.size(); i++) {
                int nextVertex = vertex.get(i);

                if (!marked[nextVertex]) {
                    // !!! 存储上一个顶点id
                    edgeTo[nextVertex] = curVertex;
                    marked[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
    }

    // 是否存在从s到v的路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // s到v的路径，如果不存在则返回null
    // 返回一个栈path便于遍历
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s ; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

}
