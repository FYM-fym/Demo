import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

public class Frame extends JFrame {
    Board board;
    private String filePath;


//    public Frame(int[][] matrix) {
//        this.matrix = matrix;
//    }

    public Frame() throws IOException {
//        System.out.println("构造函数");
        setBounds(500, 100, 1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
//        System.out.println("fym");
        setVisible(false);
//        System.out.println("fym");
        setTitle("DS BB");
//        addBoard();


//        setVisible(true);
        addGoButton();
        setVisible(true);
        addLoadButton();
        setVisible(true);

//        setInitialBoard("C:\\Users\\Lenovo\\IdeaProjects\\Second\\input\\sample");
//        setVisible(true);
    }

    public Frame(Board board) {
        this.board = board;
    }


//    public void addBoard() throws IOException {
////        board.setLocation(200, 50);
////        board.setInitialBoard();
//        add(board);
//    }


    protected void repaint(Board board) {
        this.setVisible(false);
//        setContentPane(new DrawPanel(matrix));
        repaint();
        setVisible(true);
    }

    private void addGoButton() {
        JButton button = new JButton("Go");
        button.addActionListener((e) -> {
//            repaint(board.matrix);
        });
        button.setLocation(350,20);
        button.setSize(100, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.addActionListener(e -> {
            System.out.println("Click load");
            filePath = JOptionPane.showInputDialog(this, "input the path here");
            try {
                setInitialBoard(filePath);
                this.setVisible(true);
                repaint();
                addGoButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            addLoadButton();
        });

        button.setLocation(550,20);
        button.setSize(100, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

    }

    public void setInitialBoard(String fileName) throws IOException {
        String[] type;
        Block[] board;
        String filename = fileName;
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
//        System.out.println("Read text file using Scanner");
// 一行一行地读取
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] matrix = new int[row][column];
        board = new Block[row];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                matrix[i][j] = scanner.nextInt();
//                Block block=new Block(new Blockfield(BlockType.SINGLE,matrix[i][j]),i,j);
//                board[j]=block;
//                Game game=new Game(new Board(board,matrix));
//                gameSolver.solve(game);
//                System.out.print(matrix[i][j] + " ");
            }
//            System.out.println();
        }
        int count = scanner.nextInt();
        int[] num = new int[count];
        type = new String[count];
        for (int i = 0; i < count; i++) {
            num[i] = scanner.nextInt();
            type[i] = scanner.next();
        }
//        new Frame(row, column, matrix, count, num, type).setVisible(true);

//        System.out.println(123456789);

//        System.out.println(123456789);
//        System.out.println(Arrays.toString(num));
//        System.out.println(Arrays.toString(type));
        /*while(scanner.hasNextLine()){
            //process each line
            String line = scanner.nextLine();
            System.out.println(line);
        }*/
        scanner.close();
//        System.out.println("我是最后啦");
        DrawPanel drawPanel =new DrawPanel(row,column,matrix,count,num,type);
        this.setContentPane(drawPanel);
    }

//    public void readFileData(String fileName) throws FileNotFoundException {
//        String[] type;
//        //todo: read date from file
//        File input = new File(fileName);
//        if (!input.exists()) {
//            System.out.println("File isn't exist");
//            System.exit(0);
//        }
//        Scanner in = new Scanner(input);
//        int row = in.nextInt();
//        int column = in.nextInt();
//        int[][] matrix = new int[row][column];
//        for (int i = 0; i < row; i++){
//            for (int j = 0; j < column; j++){
//                matrix[i][j] = in.nextInt();
////                Block block=new Block(new Blockfield(BlockType.SINGLE,matrix[i][j]),i,j);
////                board[j]=block;
////                Game game=new Game(new Board(board,matrix));
////                gameSolver.solve(game);
//                System.out.print(matrix[i][j] + " ");
//            }
////            System.out.println();
//        }
//        int count = in.nextInt();
//        int[] num = new int[count];
//        type = new String[count];
//        for (int i = 0; i < count; i++) {
//            num[i] = in.nextInt();
//            type[i] = in.next();
//        }
////        System.out.println("read");
//        Draw draw=new Draw(row,column,matrix,count,num,type);
//        add(draw);
//        this.setContentPane(draw);
//        draw.setVisible(true);
//        DrawPanel drawPanel=new DrawPanel(row,column,matrix,count,num,type);
//        this.setContentPane(drawPanel);
//    }
}





