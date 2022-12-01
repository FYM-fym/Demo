public class Edge {
    // 存在边的顶点下标
    private int vertexIndex;
    // 相连的下一个边
    private Edge next;

    public Edge() {}

    public Edge(int vertexIndex, Edge next) {
        this.vertexIndex = vertexIndex;
        this.next = next;
    }


    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }
}
