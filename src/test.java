import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Graph G = new Graph(new Scanner(System.in));
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        Paths paths = new Paths(G, 0);


        System.out.println();
        System.out.println("从顶点 0 广搜路径如下：");
        breadthFirstSearch.bfs(G, 0);


        System.out.println();
        System.out.println("使用广搜输出顶点 0 到顶点 5 的路径：");
        paths.bfs(G);
        Iterable<Integer> path_ = paths.pathTo(5);
        for (Integer v :
                path_) {
            System.out.print(v + " ");
        }

    }
}
