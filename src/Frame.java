import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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



    public Frame(){
        setBounds(500, 100, 1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(false);
        setTitle("DS BB");
        addGoButton();
        setVisible(true);
        addLoadButton();
        setVisible(true);
    }


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
    }

    public void setInitialBoard(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
// 一行一行地读取
        this.row = scanner.nextInt();
        this.column = scanner.nextInt();
        int[][] matrix = new int[row][column];
        int max = 0;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] != 0) max++;
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
        this.board.max = max;
        this.board.matrix = matrix;
        this.board.row = this.row;
        this.board.column = this.column;
        this.board.count = this.count;
        this.board.num = this.num;
        this.board.type = this.type;
        this.board.hashString = this.board.hashString();
        this.board.hashCode = this.board.hashCode();
        this.board.BlockNumber = BlockNumber;
        this.game = new Game(this.board); //初始化游戏
        GameSolver gameSolver = new GameSolver();
        gameSolver.solve(this.game);
        /*for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                System.out.print(gameSolver.endBoard().matrix[i][j]  + "   ");
            }
            System.out.println();
        }*/
    }
}