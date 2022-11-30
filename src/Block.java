import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Block {


        public Blockfield blockfield;//length,width
        public int xPos;
        public int yPos;
        private String hashString;  //当前块的哈希值
        private int hashCode;//??
        private boolean hashCodeCalculated = false;//?
        protected static Comparator<Block> blockComparator = new Comparator<Block>() {

//???
        @Override
        public int compare(Block a, Block b) {
            String aSortCriteria = a.hashString();
            String bSortCriteria = b.hashString();
            return aSortCriteria.compareTo(bSortCriteria);
        }
    };

    public Block (Blockfield blockfield, int xPos, int yPos) {
        this.blockfield = blockfield;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public Block(Block block, int deltaXPos, int deltaYPos) {
        this.blockfield = block.blockfield;
        this.xPos = block.xPos + deltaXPos;
        this.yPos = block.yPos + deltaYPos;
    }


//用哈希值存当前块的信息
    public String hashString() {
        if (hashString == null) {
            hashString = new StringBuilder(blockfield.blockType.toString()).append(xPos).append(yPos).toString();
        }
        return hashString;
    }

    @Override
    public int hashCode() {
        if (!hashCodeCalculated) {
            hashCode = hashString().hashCode();
            hashCodeCalculated = true;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (hashString().equals(((Block)obj).hashString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String filename = "input/sample";
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
        System.out.println("Read text file using Scanner");
// 一行一行地读取
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
        System.out.println(123456789);
        new Frame(row, column, matrix, count, num, type).setVisible(true);
        System.out.println(123456789);
        System.out.println(Arrays.toString(num));
        System.out.println(Arrays.toString(type));
        /*while(scanner.hasNextLine()){
            //process each line
            String line = scanner.nextLine();
            System.out.println(line);
        }*/
        scanner.close();
        System.out.println("我是最后啦");
    }
}
