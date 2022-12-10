import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public Board initialBoard;
    ArrayList<int[][]> distance = new ArrayList<>();
//    String name;


    public Game( Board initialBoard) {
//      this.name = name;
        this.initialBoard = initialBoard;
        /*initialBoard.stepNumberToInitialNode = 0;*/
    }


    public static void main(String[] args) throws IOException {
        Frame frame = new Frame();
        /*String filename = "input/sample";
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
        System.out.println("Read text file using Scanner");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                matrix[i][j] = scanner.nextInt();
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        int count = scanner.nextInt();
        int[] num = new int[count];
        String[] type = new String[count];
        for (int i = 0; i < count; i++){
            num[i] = scanner.nextInt();
            type[i] = scanner.next();
        }
        scanner.close();*/

        /*int BlockNumber = row * column; //棋盘中block的数量
        for (int i = 0; i < type.length; i++){
            if (type[i].equals("1*2") || type[i].equals("2*1")){
                BlockNumber--;
            }else BlockNumber -= 2;
        }

        //初始化block数组
        Block[] blocks = new Block[BlockNumber];
        int k = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < column; i++){
            for (int j = 0; j < row; j++){
                if (k < BlockNumber){
                    if (Arrays.binarySearch(num,matrix[j][i]) >= 0){
                        switch (type[Arrays.binarySearch(num, matrix[j][i])]) {
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
                        k++;
                    }else {
                        if (matrix[j][i] == 0){
                            blocks[k] = new Block(new Blockfield(BlockType.BLANK, 0), i, j);
                            k++;
                        }else if (!arrayList.contains(matrix[j][i])){
                            blocks[k] = new Block(new Blockfield(BlockType.SINGLE, matrix[j][i]), i, j);
                            k++;
                        }
                    }
                }
            }
        }
        Board initialBoard = new Board(blocks, column, row); //初始化棋盘
        new Frame(initialBoard).setVisible(true);
        initialBoard.matrix = matrix;*/
        /*Board initialBoard = new Board(blocks, column, row); //初始化棋盘*/
        /*new Frame(initialBoard).setVisible(true);
        initialBoard.matrix = matrix;

        Game game = new Game(initialBoard);*/ //初始化游戏
    }
}


