import java.util.*;
import java.util.Queue;
import java.util.Stack;

public class GameSolver {
    Game game;
    int layer = 0;
    protected HashMap<String, Board> boardConfigsExplored = new HashMap<>();
    static int sp = -1;  //最终结果shortest path，用于存最短路径的长度。如果无路径，返回-1


    public void solve(Game game) {
//        已经存在过的棋盘状态放入哈希表里
        this.game = game;
//        System.out.println(game.initialBoard.num.length + " 456");
//        System.out.println(game.initialBoard.type.length + " 123");
//        if(game.initialBoard.count == 0 ) System.out.println("step1");
//        if(game.initialBoard.num == null) System.out.println("step2");
//        if(game.initialBoard.type == null) System.out.println("step3");
        bfs(game.initialBoard);
    }

    //判定该棋盘是否进过队
    /*static boolean[] inq = new boolean[Integer.MAX_VALUE-2];*/


    static class node{
        Board currentBoard;
        node parent = null;
        int step;//用于存放起点到当前棋盘的最少步数（即层数）

        node(Board currentBoard, int step){
            this.currentBoard = currentBoard;
            this.step = step;
        }
    }


//    public void bfs(Board currentBoard){
//        boardConfigsExplored.put(currentBoard.hashString,currentBoard);
//        Queue<node> q = new LinkedList<>();
//        ArrayList<int[][]> distance = new ArrayList<>(); // 最短路径中matrix的arraylist数组
//        Stack<node> path = new Stack<>(); //用于记录最短路径上的结点
//        q.add(new node(currentBoard,0));
//        /*inq[currentBoard.hashCode()] = true; //用棋盘的哈希值代表棋盘数据*/
//
//        while(!q.isEmpty()){
//            node temp = q.poll();
//            boolean flag=true;
//            //如果该棋盘为终点
//            for(int i = 0 ; i < currentBoard.matrix.length ; i ++){
//                for(int j = 0 ; j < currentBoard.matrix[i].length ; j ++){
//                    if(temp.currentBoard.matrix[i][j] != endBoard().matrix[i][j]){
//                        flag = false;
//                        break;
//                    }
//                }
//            }
//            /*System.out.println(temp.currentBoard.hashCode());
//            System.out.println(endBlocks().hashCode());
//            if (temp.currentBoard.hashCode() != endBlocks().hashCode()) flag = false;*/
//            if(flag){//找到最终结点
//                sp = temp.step;
//                path.push(temp);
//                while(temp.parent != null){
//                    path.push(temp.parent);
//                    temp = temp.parent;
//                }
//                break;
//            }
//            //不是终点
//            for(int i = 0 ; i < currentBoard.blocks.length; i++) {
//                if (temp.currentBoard.blocks[i].blockfield.blockType != BlockType.BLANK) {
//                    /*ArrayList<Board> next = NextBoard(temp.currentBoard, temp.currentBoard.blocks[i]);*/
//                    ArrayList<Board> next = Next(temp.currentBoard, temp.currentBoard.blocks[i], currentBoard.count, currentBoard.num, currentBoard.type);
//                    if (next.size() == 0) continue;
//                    for (int j = 0; j < next.size(); j++){
//                        if (next.get(j).isNewBoard){
//                            node newNode = new node(next.get(j),temp.step + 1);
//                            q.offer(newNode);
//                            newNode.parent = temp;
//                        }
//                    }
//                }
//            }
//            /*for(int i = 0 ; i < currentBoard.blocks.length; i++) {
//                if (temp.currentBoard.blocks[i].blockfield.blockType != BlockType.BLANK) {
//                    nextBoard = getNextBoard(temp.currentBoard, temp.currentBoard.blocks[i]); //如果block[i]不能移动，nextBoard = null
//                    if (nextBoard != null && nextBoard.isNewBoard) {   // ?? isNewBoard是否可用
//                        node newNode = new node(nextBoard, temp.step + 1);
//                        q.offer(newNode);
//                        newNode.parent = temp; //给每一个新生成的node记录其父节点
//                        */
//            /*inq[nextBoard.hashCode()] = true; //结点入队时标记已入队*//*
//                    }
//                }
//            }*/
//        }
//        if(sp == -1){
//            System.out.println("No");
//        }else{
//            System.out.println("Yes");
//            System.out.println(sp);
//            while(!path.isEmpty()){
//                node a = path.pop();
//                distance.add(a.currentBoard.matrix);
//                System.out.println(a.currentBoard.everyStep);
//            }
//        }
//        this.game.distance=distance;
//    }
public void bfs(Board currentBoard){
    boardConfigsExplored.put(currentBoard.hashString,currentBoard);
    Queue<node> q = new LinkedList<>();
    ArrayList<int[][]> distance = new ArrayList<>(); // 最短路径中matrix的arraylist数组
    Stack<node> path = new Stack<>(); //用于记录最短路径上的结点
    q.add(new node(currentBoard,0));
    /*inq[currentBoard.hashCode()] = true; //用棋盘的哈希值代表棋盘数据*/

    while(!q.isEmpty()){
        node temp = q.poll();
        boolean flag=true;
        //如果该棋盘为终点
        for(int i = 0 ; i < currentBoard.matrix.length ; i ++){
            for(int j = 0 ; j < currentBoard.matrix[i].length ; j ++){
                if(temp.currentBoard.matrix[i][j] != matrix[i][j]){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){//找到最终结点
            sp = temp.step;
            path.push(temp);
            while(temp.parent != null){
                path.push(temp.parent);
                temp = temp.parent;
            }
            break;
        }
        //不是终点

        for(int i = 0 ; i < currentBoard.blocks.length; i++) {
            if (temp.currentBoard.blocks[i].blockfield.blockType != BlockType.BLANK){
                ArrayList<Board> next = Next(temp.currentBoard, temp.currentBoard.blocks[i], temp.currentBoard.BlockNumber, currentBoard.num, currentBoard.type);
                if (next.size() == 0) continue;

                for (int j = 0; j < next.size(); j++){
                    if (next.get(j).isNewBoard && Arrays.toString(next.get(j).matrix[0]).equals(Arrays.toString(this.matrix[0])) && layer == 0){
                        layer++;
                        q.clear();
                        node newNode = new node(next.get(j),temp.step + 1);
                        q.offer(newNode);
                        newNode.parent = temp;
                        i = currentBoard.blocks.length;
                        break;
                    }
                    if (next.get(j).isNewBoard){
                        node newNode = new node(next.get(j),temp.step + 1);
                        q.offer(newNode);
                        newNode.parent = temp;
                    }
                }
            }
        }
    }
    if(sp == -1){
        System.out.println("No");
    }else{
        System.out.println("Yes");
        System.out.println(sp);
        while(!path.isEmpty()){
            node a = path.pop();
            distance.add(a.currentBoard.matrix);
            System.out.println(a.currentBoard.everyStep);
        }
    }
    this.game.distance=distance;
}



    /*private Board getNextBoard(Board currentBoard, Block oldBlock) {
        MoveType[] moveTypes = MoveType.values(); // {U,D,L,R}
        for (MoveType moveType : moveTypes) {
            Block newBlock = calcNewBlock(currentBoard, oldBlock, moveType); // 如果有效，返回新的block.否则返回null
            if (newBlock == null) continue;//判断移动是否有效
            Board newBoard = new Board(currentBoard, oldBlock, newBlock); // 更新block[]数组
            newBoard.MAXXPOS = currentBoard.MAXXPOS;
            newBoard.MAXYPOS = currentBoard.MAXYPOS;
            newBoard.matrix = getNextMatrix(currentBoard, oldBlock, moveType);
            newBoard.hashString = newBoard.hashString();
            newBoard.hashCode = newBoard.hashCode();
            if (boardConfigsExplored.containsKey(newBoard.hashString)) {
                /*newBoard = boardConfigsExplored.get(newBoard.hashString);
                newBoard.isNewBoard = false;
            } else {
                boardConfigsExplored.put(newBoard.hashString, newBoard);
            }
            newBoard.everyStep = newBlock.blockfield.number +" "+ moveType;
            return newBoard;
        }
        return null;
    }*/

   /* private ArrayList<Board> NextBoard(Board currentBoard, Block oldBlock) {
        ArrayList<Board> arrayList = new ArrayList<>();
        MoveType[] moveTypes = MoveType.values(); // {U,D,L,R}
        for (MoveType moveType : moveTypes) {
            Block newBlock = calcNewBlock(currentBoard, oldBlock, moveType); // 如果有效，返回新的block.否则返回null
            if (newBlock == null) continue;//判断移动是否有效
            Board newBoard = new Board(currentBoard, oldBlock, newBlock); // 更新block[]数组
            newBoard.MAXXPOS = currentBoard.MAXXPOS;
            newBoard.MAXYPOS = currentBoard.MAXYPOS;
            newBoard.matrix = getNextMatrix(currentBoard, oldBlock, moveType);
            newBoard.hashString = newBoard.hashString();
            newBoard.hashCode = newBoard.hashCode();
            if (boardConfigsExplored.containsKey(newBoard.hashString)) {
                newBoard = boardConfigsExplored.get(newBoard.hashString);
                newBoard.isNewBoard = false;
            } else {
                boardConfigsExplored.put(newBoard.hashString, newBoard);

                for (int i = 0; i < newBoard.matrix.length; i++){
                    for (int j = 0; j < newBoard.matrix[0].length; j++){
                        System.out.print(newBoard.matrix[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();

            }
            newBoard.everyStep = newBlock.blockfield.number +" "+ moveType;
            arrayList.add(newBoard);
        }
        return arrayList;
    }*/

    private ArrayList<Board> Next(Board currentBoard, Block oldBlock, int count, int[] num, String[] type) {
        ArrayList<Board> arrayList = new ArrayList<>();
        MoveType[] moveTypes = MoveType.values(); // {U,D,L,R}
        for (MoveType moveType : moveTypes) {
            if (!currentBoard.isValidMove(oldBlock,moveType)) continue;
            int[][] newMatrix = getNextMatrix(currentBoard,oldBlock,moveType);
            Board board = new Board(newMatrix);
            board.row = newMatrix.length;
            board.column = newMatrix[0].length;
            board.count = count;
            board.num = num;
            board.type = type;
            board.everyStep = oldBlock.blockfield.number + " " + moveType;

            int BlockNumber = board.row * board.column; //棋盘中block的数量

            for (int i = 0; i < type.length; i++){
                if (type[i].equals("1*2") || type[i].equals("2*1")){
                    BlockNumber--;
                }else BlockNumber -= 3;
            }
            board.blocks = new Block[BlockNumber];
            int k = 0;
            ArrayList<Integer> arrayList1 = new ArrayList<>();
            for (int i = 0; i < board.column; i++){
                for (int j = 0; j < board.row; j++){
                    if (k < BlockNumber){
                        if (DrawPanel.contains(num,board.matrix[j][i]) >= 0){
                            switch (type[DrawPanel.contains(num, board.matrix[j][i])]) {
                                case "1*2" -> {
                                    board.blocks[k] = new Block(new Blockfield(BlockType.HORIZONTAL, board.matrix[j][i]), i, j);
                                    arrayList1.add(board.matrix[j][i + 1]);
                                }
                                case "2*1" -> {
                                    board.blocks[k] = new Block(new Blockfield(BlockType.VERTICAL, board.matrix[j][i]), i, j);
                                    arrayList1.add(board.matrix[j + 1][i]);
                                }
                                case "2*2" -> {
                                    board.blocks[k] = new Block(new Blockfield(BlockType.SQUARE, board.matrix[j][i]), i, j);
                                    arrayList1.add(board.matrix[j][i + 1]);
                                    arrayList1.add(board.matrix[j + 1][i]);
                                    arrayList1.add(board.matrix[j + 1][i + 1]);
                                }
                            }
                            board.blocks[k].hashString = board.blocks[k].hashString();
                            board.blocks[k].hashCode = board.blocks[k].hashCode();
                            k++;
                        }else {
                            if (board.matrix[j][i] == 0){
                                board.blocks[k] = new Block(new Blockfield(BlockType.BLANK, 0), i, j);
                                board.blocks[k].hashString = board.blocks[k].hashString();
                                board.blocks[k].hashCode = board.blocks[k].hashCode();
                                k++;
                            }else if (!arrayList1.contains(board.matrix[j][i])){
                                board.blocks[k] = new Block(new Blockfield(BlockType.SINGLE, board.matrix[j][i]), i, j);
                                board.blocks[k].hashString = board.blocks[k].hashString();
                                board.blocks[k].hashCode = board.blocks[k].hashCode();
                                k++;
                            }
                        }
                    }
                }
            }
            board.hashString = board.hashString();
            board.hashCode = board.hashCode();
            if (boardConfigsExplored.containsKey(board.hashString)){
                board.isNewBoard = false;
            }else {
                boardConfigsExplored.put(board.hashString, board);
                for (int i = 0; i < board.matrix.length; i++){
                    for (int j = 0; j < board.matrix[0].length; j++){
                        System.out.print(board.matrix[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();
                arrayList.add(board);
            }
            /*Block newBlock = calcNewBlock(currentBoard, oldBlock, moveType); // 如果有效，返回新的block.否则返回null
            if (newBlock == null) continue;//判断移动是否有效
            Board newBoard = new Board(currentBoard, oldBlock, newBlock); // 更新block[]数组
            newBoard.MAXXPOS = currentBoard.MAXXPOS;
            newBoard.MAXYPOS = currentBoard.MAXYPOS;
            newBoard.matrix = getNextMatrix(currentBoard, oldBlock, moveType);
            newBoard.hashString = newBoard.hashString();
            newBoard.hashCode = newBoard.hashCode();
            if (boardConfigsExplored.containsKey(newBoard.hashString)) {
                *//*newBoard = boardConfigsExplored.get(newBoard.hashString);*//*
                newBoard.isNewBoard = false;
            } else {
                boardConfigsExplored.put(newBoard.hashString, newBoard);

                for (int i = 0; i < newBoard.matrix.length; i++){
                    for (int j = 0; j < newBoard.matrix[0].length; j++){
                        System.out.print(newBoard.matrix[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();

            }
            newBoard.everyStep = newBlock.blockfield.number +" "+ moveType;
            arrayList.add(newBoard);*/
        }
        return arrayList;
    }

/*
    private static final int[][] moveStepsUP = {{0, -1}};
    private static final int[][] moveStepsDOWN = {{0, 1}};
    private static final int[][] moveStepsLEFT = {{-1, 0}};
    private static final int[][] moveStepsRIGHT = {{1, 0}};

    //根据当前棋盘的情况，当前的block以及移动方向，返回能移动到的block
    protected static Block calcNewBlock(Board oldBoard, Block oldBlock, MoveType moveType) {
        int[][] moveSteps = null;
        switch (moveType) {
            case U:
                moveSteps = moveStepsUP;
                break;
            case D:
                moveSteps = moveStepsDOWN;
                break;
            case L:
                moveSteps = moveStepsLEFT;
                break;
            case R:
                moveSteps = moveStepsRIGHT;
                break;
            default:
                assert false;
        }
        if (!oldBoard.isValidMove(oldBlock,moveType)) {
            return null;
        }
        return new Block(oldBlock, moveSteps[0][0], moveSteps[0][1]);
    }
*/

    protected int[][] getNextMatrix(Board oldBoard, Block oldBlock, MoveType moveType) {
        int[][] matrix = new int[oldBoard.matrix.length][oldBoard.matrix[0].length];
        for (int i = 0; i < oldBoard.matrix.length; i++) {
            for (int j = 0; j < oldBoard.matrix[0].length; j++) {
                matrix[i][j] = oldBoard.matrix[i][j];
            }
        }
        for (int i = 0; i < oldBoard.matrix.length; i++) {
            for (int j = 0; j < oldBoard.matrix[0].length; j++) {
                if (oldBlock.blockfield.number == oldBoard.matrix[i][j]) {
                    if(moveType == MoveType.U) {
                        if (oldBlock.blockfield.blockType == BlockType.SINGLE){
                            matrix[i - 1][j] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
                            matrix[i - 1][j] = oldBoard.matrix[i][j];
                            matrix[i - 1][j + 1] = oldBoard.matrix[i][j + 1];
                            matrix[i][j] = 0;
                            matrix[i][j + 1] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.VERTICAL){
                            matrix[i - 1][j] = oldBoard.matrix[i][j];
                            matrix[i][j] = oldBoard.matrix[i + 1][j];
                            matrix[i + 1][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.SQUARE){
                            matrix[i - 1][j] = oldBoard.matrix[i][j];
                            matrix[i - 1][j + 1] = oldBoard.matrix[i][j + 1];
                            matrix[i][j] = oldBoard.matrix[i + 1][j];
                            matrix[i][j + 1] = oldBoard.matrix[i + 1][j + 1];
                            matrix[i + 1][j] = 0;
                            matrix[i + 1][j + 1] = 0;
                        }
                    } else if(moveType==MoveType.D) {
                        if (oldBlock.blockfield.blockType == BlockType.SINGLE){
                            matrix[i + 1][j] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
                            matrix[i + 1][j] = oldBoard.matrix[i][j];
                            matrix[i + 1][j + 1] = oldBoard.matrix[i][j + 1];
                            matrix[i][j] = 0;
                            matrix[i][j + 1] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.VERTICAL){
                            matrix[i + 2][j] = oldBoard.matrix[i + 1][j];
                            matrix[i + 1][j] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.SQUARE){
                            matrix[i + 2][j] = oldBoard.matrix[i + 1][j];
                            matrix[i + 2][j + 1] = oldBoard.matrix[i + 1][j + 1];
                            matrix[i + 1][j] = oldBoard.matrix[i][j];
                            matrix[i + 1][j + 1] = oldBoard.matrix[i][j + 1];
                            matrix[i][j] = 0;
                            matrix[i][j + 1] = 0;
                        }
                    } else if(moveType==MoveType.L) {
                        if (oldBlock.blockfield.blockType == BlockType.SINGLE){
                            matrix[i][j - 1] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
                            matrix[i][j - 1] = oldBoard.matrix[i][j];
                            matrix[i][j] = oldBoard.matrix[i][j + 1];
                            matrix[i][j + 1] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.VERTICAL){
                            matrix[i][j - 1] = oldBoard.matrix[i][j];
                            matrix[i + 1][j - 1] = oldBoard.matrix[i + 1][j];
                            matrix[i][j] = 0;
                            matrix[i + 1][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.SQUARE){
                            matrix[i][j - 1] = oldBoard.matrix[i][j];
                            matrix[i + 1][j - 1] = oldBoard.matrix[i + 1][j];
                            matrix[i][j] = oldBoard.matrix[i][j + 1];
                            matrix[i + 1][j] = oldBoard.matrix[i + 1][j + 1];
                            matrix[i][j + 1] = 0;
                            matrix[i + 1][j + 1] = 0;
                        }
                    }else {
                        if (oldBlock.blockfield.blockType == BlockType.SINGLE){
                            matrix[i][j + 1] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
                            matrix[i][j + 2] = oldBoard.matrix[i][j + 1];
                            matrix[i][j + 1] = oldBoard.matrix[i][j];
                            matrix[i][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.VERTICAL){
                            matrix[i][j + 1] = oldBoard.matrix[i][j];
                            matrix[i + 1][j + 1] = oldBoard.matrix[i + 1][j];
                            matrix[i][j] = 0;
                            matrix[i + 1][j] = 0;
                        }else if (oldBlock.blockfield.blockType == BlockType.SQUARE){
                            matrix[i][j + 2] = oldBoard.matrix[i][j + 1];
                            matrix[i + 1][j + 2] = oldBoard.matrix[i + 1][j + 1];
                            matrix[i][j + 1] = oldBoard.matrix[i][j];
                            matrix[i + 1][j + 1] = oldBoard.matrix[i + 1][j];
                            matrix[i][j] = 0;
                            matrix[i + 1][j] = 0;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    /*
    public Board endBlocks(){
        Board a = game.initialBoard; // 设 a 为初始棋盘
        int BlockNumber = a.row * a.column;
        for (int i = 0; i < a.type.length; i++){
            if (a.type[i].equals("1*2") || a.type[i].equals("2*1")){
                BlockNumber--;
            }else BlockNumber -= 3;
        }
        Block[] blocks = new Block[BlockNumber];
        int k = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < a.column; i++){
            for (int j = 0; j < a.row; j++){
                if (k < BlockNumber){
                    if (DrawPanel.contains(a.num,endBoard().matrix[j][i]) >= 0){
                        switch (a.type[DrawPanel.contains(a.num, endBoard().matrix[j][i])]) {
                            case "1*2" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.HORIZONTAL, endBoard().matrix[j][i]), i, j);
                                arrayList.add(endBoard().matrix[j][i + 1]);
                            }
                            case "2*1" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.VERTICAL, endBoard().matrix[j][i]), i, j);
                                arrayList.add(endBoard().matrix[j + 1][i]);
                            }
                            case "2*2" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.SQUARE, endBoard().matrix[j][i]), i, j);
                                arrayList.add(endBoard().matrix[j][i + 1]);
                                arrayList.add(endBoard().matrix[j + 1][i]);
                                arrayList.add(endBoard().matrix[j + 1][i + 1]);
                            }
                        }
                        blocks[k].hashString = blocks[k].hashString();
                        blocks[k].hashCode = blocks[k].hashCode();
                        k++;
                    }else {
                        if (endBoard().matrix[j][i] == 0){
                            blocks[k] = new Block(new Blockfield(BlockType.BLANK, 0), i, j);
                            blocks[k].hashString = blocks[k].hashString();
                            blocks[k].hashCode = blocks[k].hashCode();
                            k++;
                        }else if (!arrayList.contains(endBoard().matrix[j][i])){
                            blocks[k] = new Block(new Blockfield(BlockType.SINGLE, endBoard().matrix[j][i]), i, j);
                            blocks[k].hashString = blocks[k].hashString();
                            blocks[k].hashCode = blocks[k].hashCode();
                            k++;
                        }
                    }
                }
            }
        }
        return new Board(blocks, a.column, a.row);
    }*/

    protected Board endBoard(){
        int size=game.initialBoard.MAXXPOS*game.initialBoard.MAXYPOS;
        ArrayList<Integer> endlist=new ArrayList<>();
        int[] solution = new int[size];
        int m=0;
        int n=0;
        for (int i = 0; i < game.initialBoard.MAXYPOS; i++) {
            for (int j = 0; j < game.initialBoard.MAXXPOS; j++) {
                if(game.initialBoard.matrix[i][j]!=0) {
                    endlist.add(game.initialBoard.matrix[i][j]);
                }
                m++;
            }
        }
        Collections.sort(endlist);
        for (int i = 0; i < endlist.size(); i++) {
            solution[i]=endlist.get(i);
        }
        int[][] matrix=new int[game.initialBoard.MAXYPOS][game.initialBoard.MAXXPOS];
        while (n<size) {
            for (int i=0; i < game.initialBoard.MAXYPOS; i++) {
                for (int j=0; j < game.initialBoard.MAXXPOS; j++) {
                    matrix[i][j] = solution[n];
                    n++;
                }
            }
        }
        Board end=new Board(matrix);
        return end;
    }
}
