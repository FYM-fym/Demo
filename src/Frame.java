import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

public class Frame extends JFrame {
    Board board;
    private String filePath;
    protected Game game;
    DrawPanel drawPanel;
    int row;
    int column;
    int count;
    int[] num;
    String[] type;
    /*public ArrayList<Board> list = new ArrayList<>();*/
    public ArrayList<int[][]> list = new ArrayList<>();


//    public Frame(int[][] matrix) {
//        this.matrix = matrix;
//    }

    /*public static void main(String[] args) {
        Board board1 = new Board(new Block[26], 6, 5);
        board1.blocks[0] = new Block(new Blockfield(BlockType.VERTICAL,8),0,0);
        board1.blocks[1] = new Block(new Blockfield(BlockType.SINGLE,13),0,2);
        board1.blocks[2] = new Block(new Blockfield(BlockType.SINGLE,1),0,3);
        board1.blocks[3] = new Block(new Blockfield(BlockType.SINGLE,5),0,4);
        board1.blocks[4] = new Block(new Blockfield(BlockType.SINGLE,25),1,0);
        board1.blocks[5] = new Block(new Blockfield(BlockType.SINGLE,18),1,1);
        board1.blocks[6] = new Block(new Blockfield(BlockType.SINGLE,20),1,3);
        board1.blocks[7] = new Block(new Blockfield(BlockType.SINGLE,2),1,4);
        board1.blocks[8] = new Block(new Blockfield(BlockType.SQUARE,16),2,1);
        board1.blocks[9] = new Block(new Blockfield(BlockType.SINGLE,3),2,0);
        board1.blocks[10] = new Block(new Blockfield(BlockType.SINGLE,4),3,0);
        board1.blocks[11] = new Block(new Blockfield(BlockType.SINGLE,27),2,4);
        board1.blocks[12] = new Block(new Blockfield(BlockType.SINGLE,15),3,4);
        board1.blocks[13] = new Block(new Blockfield(BlockType.SINGLE,26),4,0);
        board1.blocks[14] = new Block(new Blockfield(BlockType.SINGLE,11),4,1);
        board1.blocks[15] = new Block(new Blockfield(BlockType.SINGLE,9),4,2);
        board1.blocks[16] = new Block(new Blockfield(BlockType.SINGLE,10),4,3);
        board1.blocks[17] = new Block(new Blockfield(BlockType.SINGLE,21),4,4);
        board1.blocks[18] = new Block(new Blockfield(BlockType.SINGLE,19),5,0);
        board1.blocks[19] = new Block(new Blockfield(BlockType.SINGLE,12),5,1);
        board1.blocks[20] = new Block(new Blockfield(BlockType.SINGLE,7),5,2);
        board1.blocks[21] = new Block(new Blockfield(BlockType.SINGLE,24),5,3);
        board1.blocks[22] = new Block(new Blockfield(BlockType.SINGLE,6),5,4);
        board1.blocks[23] = new Block(new Blockfield(BlockType.BLANK,0),1,2);
        board1.blocks[24] = new Block(new Blockfield(BlockType.BLANK,0),2,3);
        board1.blocks[25] = new Block(new Blockfield(BlockType.BLANK,0),3,3);



        System.out.print(board1.isValidMove(board1.blocks[0],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[0],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[0],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[0],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[1],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[1],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[1],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[1],MoveType.RIGHT ) + "  ");
        System.out.print(board1.getType(board1.blocks[1].xPos + 1, board1.blocks[1].yPos ) != BlockType.BLANK);
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[2],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[2],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[2],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[2],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[3],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[3],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[3],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[3],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[4],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[4],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[4],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[4],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[5],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[5],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[5],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[5],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[6],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[6],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[6],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[6],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[7],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[7],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[7],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[7],MoveType.RIGHT ) + "  ");
        System.out.println();
        System.out.print(board1.isValidMove(board1.blocks[8],MoveType.UP ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[8],MoveType.DOWN ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[8],MoveType.LEFT ) + "  ");
        System.out.print(board1.isValidMove(board1.blocks[8],MoveType.RIGHT ) + "  ");
        System.out.println();
    }*/
    public Frame() throws IOException {
        setBounds(500, 100, 1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(false);
        setTitle("DS BB");

//        addBoard();
        addGoButton();
        setVisible(true);
        addLoadButton();
        setVisible(true);

        //固定最终棋盘的路径
        /*int[][] matrix1 = new int[5][6];
        int[][] matrix2 = new int[5][6];
        int[][] matrix3 = new int[5][6];
        int[][] matrix4 = new int[5][6];
        matrix1[0][0] = 8;
        matrix1[0][1] = 25;
        matrix1[0][2] = 3;
        matrix1[0][3] = 4;
        matrix1[0][4] = 26;
        matrix1[0][5] = 9;
        matrix1[1][0] = 14;
        matrix1[1][1] = 18;
        matrix1[1][2] = 16;
        matrix1[1][3] = 17;
        matrix1[1][4] = 11;
        matrix1[1][5] = 12;
        matrix1[2][0] = 13;
        matrix1[2][1] = 0;
        matrix1[2][2] = 22;
        matrix1[2][3] = 23;
        matrix1[2][4] = 9;
        matrix1[2][5] = 7;
        matrix1[3][0] = 1;
        matrix1[3][1] = 20;
        matrix1[3][2] = 0;
        matrix1[3][3] = 0;
        matrix1[3][4] = 10;
        matrix1[3][5] = 24;
        matrix1[4][0] = 5;
        matrix1[4][1] = 2;
        matrix1[4][2] = 27;
        matrix1[4][3] = 15;
        matrix1[4][4] = 21;
        matrix1[4][5] = 6;

        matrix2[0][0] = 0;
        matrix2[0][1] = 8;
        matrix2[0][2] = 3;
        matrix2[0][3] = 4;
        matrix2[0][4] = 26;
        matrix2[0][5] = 9;
        matrix2[1][0] = 0;
        matrix2[1][1] = 14;
        matrix2[1][2] = 16;
        matrix2[1][3] = 17;
        matrix2[1][4] = 11;
        matrix2[1][5] = 12;
        matrix2[2][0] = 13;
        matrix2[2][1] = 25;
        matrix2[2][2] = 22;
        matrix2[2][3] = 23;
        matrix2[2][4] = 9;
        matrix2[2][5] = 7;
        matrix2[3][0] = 1;
        matrix2[3][1] = 18;
        matrix2[3][2] = 0;
        matrix2[3][3] = 20;
        matrix2[3][4] = 10;
        matrix2[3][5] = 24;
        matrix2[4][0] = 5;
        matrix2[4][1] = 2;
        matrix2[4][2] = 27;
        matrix2[4][3] = 15;
        matrix2[4][4] = 21;
        matrix2[4][5] = 6;

        matrix3[0][0] = 13;
        matrix3[0][1] = 8;
        matrix3[0][2] = 3;
        matrix3[0][3] = 4;
        matrix3[0][4] = 26;
        matrix3[0][5] = 9;
        matrix3[1][0] = 1;
        matrix3[1][1] = 14;
        matrix3[1][2] = 16;
        matrix3[1][3] = 17;
        matrix3[1][4] = 11;
        matrix3[1][5] = 12;
        matrix3[2][0] = 0;
        matrix3[2][1] = 25;
        matrix3[2][2] = 22;
        matrix3[2][3] = 23;
        matrix3[2][4] = 9;
        matrix3[2][5] = 7;
        matrix3[3][0] = 0;
        matrix3[3][1] = 0;
        matrix3[3][2] = 18;
        matrix3[3][3] = 20;
        matrix3[3][4] = 10;
        matrix3[3][5] = 24;
        matrix3[4][0] = 5;
        matrix3[4][1] = 2;
        matrix3[4][2] = 27;
        matrix3[4][3] = 15;
        matrix3[4][4] = 21;
        matrix3[4][5] = 6;

        matrix4[0][0] = 1;
        matrix4[0][1] = 2;
        matrix4[0][2] = 3;
        matrix4[0][3] = 4;
        matrix4[0][4] = 5;
        matrix4[0][5] = 6;
        matrix4[1][0] = 7;
        matrix4[1][1] = 8;
        matrix4[1][2] = 9;
        matrix4[1][3] = 10;
        matrix4[1][4] = 11;
        matrix4[1][5] = 12;
        matrix4[2][0] = 13;
        matrix4[2][1] = 14;
        matrix4[2][2] = 15;
        matrix4[2][3] = 16;
        matrix4[2][4] = 17;
        matrix4[2][5] = 18;
        matrix4[3][0] = 19;
        matrix4[3][1] = 20;
        matrix4[3][2] = 21;
        matrix4[3][3] = 22;
        matrix4[3][4] = 23;
        matrix4[3][5] = 24;
        matrix4[4][0] = 25;
        matrix4[4][1] = 26;
        matrix4[4][2] = 27;
        matrix4[4][3] = 0;
        matrix4[4][4] = 0;
        matrix4[4][5] = 0;

        *//*list.add(this.board.matrix);*//*
        list.add(matrix1);
        list.add(matrix2);
        list.add(matrix3);
        list.add(matrix4);*/

    }

//    public Frame(Board board) {
//        this.board = board;
//        setBounds(500, 100, 1000, 600);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setLayout(null);
//        setVisible(false);
//        setTitle("DS BB");
////        addBoard();
//
//        addGoButton();
//        setVisible(true);
//        addLoadButton();
//        setVisible(true);
//    }


//    public void addBoard() throws IOException {
////        board.setLocation(200, 50);
////        board.setInitialBoard();
//        add(board);
//    }


    /*protected void repaint(Graphics2D g2) {
        this.setVisible(false);
//        setContentPane(new DrawPanel(matrix));
        repaint();
        setVisible(true);


        Font font = new Font("宋体", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.RED);
        g2.drawString(String.valueOf(22), 100 + 120 + 50, 80 + 80 + 50);

    }*/

    private void addGoButton() {
        JButton button = new JButton("Go");
        button.addActionListener((e) -> {
            System.out.println(game.distance.size() +"wrtyu");
            if (game.distance.size() > 0){
                remove(this.drawPanel);
                repaint();
                this.drawPanel = new DrawPanel(row,column,game.distance.get(0),count,num,type);
                drawPanel.setBounds(0,30,1000,600);
                getContentPane().add(drawPanel);
                drawPanel.setLayout(null);
                drawPanel.setVisible(true);
                repaint();
                for (int i = 0; i < row; i++){
                    for (int j = 0; j < column; j++){
                        System.out.print(game.distance.get(0)[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();
                game.distance.remove(0);
            }
        });
        button.setLocation(350,20);
        button.setSize(100, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        getContentPane().add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(550,20);
        button.setSize(100, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        getContentPane().add(button);
        button.addActionListener(e -> {
            System.out.println("Click load");
            filePath = JOptionPane.showInputDialog(this, "input the path here");
            try {
                if (filePath != null){
                    getContentPane().removeAll();
                    setInitialBoard(filePath);
                    this.setVisible(true);
                    repaint();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        /*add(button);*/

    }

    public void setInitialBoard(String fileName) throws IOException {

        Block[] board;
        String filename = fileName;
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
//        System.out.println("Read text file using Scanner");
// 一行一行地读取
        this.row = scanner.nextInt();
        this.column = scanner.nextInt();
        int[][] matrix = new int[row][column];
        board = new Block[row];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        this.count = scanner.nextInt();
        this.num = new int[count];
        this.type = new String[count];
        if(count != 0){
            for (int i = 0; i < count; i++) {
                num[i] = scanner.nextInt();
                type[i] = scanner.next();
            }
        }
        scanner.close();

        //初始化block数组
        int BlockNumber = row * column; //棋盘中block的数量

        for (int i = 0; i < type.length; i++){
            if (type[i].equals("1*2") || type[i].equals("2*1")){
                BlockNumber--;
            }else BlockNumber -= 3;
        }
        Block[] blocks = new Block[BlockNumber];
        int k = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < column; i++){
            for (int j = 0; j < row; j++){
                if (k < BlockNumber){
                    if (DrawPanel.contains(num,matrix[j][i]) >= 0){
                        switch (type[DrawPanel.contains(num, matrix[j][i])]) {
                            case "1*2" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.HORIZONTAL, matrix[j][i]), i, j);
                                arrayList.add(matrix[j][i + 1]);
                            }
                            case "2*1" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.VERTICAL, matrix[j][i]), i, j);
                                arrayList.add(matrix[j + 1][i]);
                            }
                            case "2*2" -> {
                                blocks[k] = new Block(new Blockfield(BlockType.SQUARE, matrix[j][i]), i, j);
                                arrayList.add(matrix[j][i + 1]);
                                arrayList.add(matrix[j + 1][i]);
                                arrayList.add(matrix[j + 1][i + 1]);
                            }
                        }
                        blocks[k].hashString = blocks[k].hashString();
                        blocks[k].hashCode = blocks[k].hashCode();
                        k++;
                    }else {
                        if (matrix[j][i] == 0){
                            blocks[k] = new Block(new Blockfield(BlockType.BLANK, 0), i, j);
                            blocks[k].hashString = blocks[k].hashString();
                            blocks[k].hashCode = blocks[k].hashCode();
                            k++;
                        }else if (!arrayList.contains(matrix[j][i])){
                            blocks[k] = new Block(new Blockfield(BlockType.SINGLE, matrix[j][i]), i, j);
                            blocks[k].hashString = blocks[k].hashString();
                            blocks[k].hashCode = blocks[k].hashCode();
                            k++;
                        }
                    }
                }
            }
        }
        this.drawPanel = new DrawPanel(row,column,matrix,count,num,type);
        drawPanel.setBounds(0,30,1000,600);
        getContentPane().add(drawPanel);
        drawPanel.setLayout(null);
        drawPanel.setVisible(true);
        addGoButton();
        addLoadButton();

        this.board = new Board(blocks, column, row); //初始化棋盘
        this.board.matrix = matrix;

        this.board.row = this.row;
        this.board.column = this.column;
        this.board.count = this.count;
        this.board.num = this.num;
        this.board.type = this.type;

        System.out.println(blocks.length);
        this.board.hashString = this.board.hashString();
        this.board.hashCode = this.board.hashCode();
        this.game = new Game(this.board); //初始化游戏


        GameSolver gameSolver = new GameSolver();
        gameSolver.solve(this.game);
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                System.out.print(gameSolver.endBoard().matrix[i][j]  + "   ");
            }
            System.out.println();
        }


    }
}