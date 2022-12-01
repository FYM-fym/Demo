public class Vertex<Board>{
        private Board data;
        private Edge first;
        private int size;

        public Vertex(){}

        public void add(Edge item){
            if(first == null){
                first = item;
            }else{
                item.setNext(first);
                first = item;
            }
            size++;
        }

        // 获得下标为index的元素
        public int get(int index) {
            if (index >= size)
                return 0;
            Edge cur = first;
            while (index != 0) {
                cur = cur.getNext();
                --index;
            }
            return cur.getVertexIndex();
        }

        // 获取元素个数
        public int size() {
            return size;
        }

        // 判断是否为空
        public boolean isEmpty() {
            return (first == null);
        }

        public Board getData() {
            return data;
        }

        public void setData(Board data) {
            this.data = data;
        }

        public Edge getFirst() {
            return first;
        }

        public void setFirst(Edge first) {
            this.first = first;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    ", first=" + first +
                    ", size=" + size +
                    '}';
        }
}

