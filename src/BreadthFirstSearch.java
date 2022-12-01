import java.util.ArrayDeque;

public class BreadthFirstSearch {

    private boolean[] marked;

    public BreadthFirstSearch() {
    }

    // 从顶点v开始广搜
    public void bfs(Graph G, int v) {
        marked = new boolean[G.getV()];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        marked[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int curVertex = queue.remove();
            System.out.print(curVertex + " ");

            Vertex vertex = G.getVertex(curVertex);
            for (int i = 0; i < vertex.size(); i++) {
                int nextVertex = vertex.get(i);
                if (!marked[nextVertex]) {
                    marked[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
    }

}
