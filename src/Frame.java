import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class Frame extends JFrame {
    public Frame(int row, int column, int[][] matrix, int count, int[] num, String[] type){
        setBounds(500,100,1000,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("fym");
        setContentPane(new DrawPanel(row, column, matrix,count, num, type));
        System.out.println("fym");
        setTitle("DS BB");
    }
}
class DrawPanel extends JPanel{
        int row;
        int column;
        int[][] matrix;
        int count; //特殊type的数量
        int[] num; //特殊type左上角的数字
        String[] type; //特殊type：1*2，2*1，2*2

        public DrawPanel(int row, int column, int[][] matrix, int count, int[] num, String[] type){
            this.row = row;
            this.column = column;
            this.matrix = matrix;
            this.count = count;
            this.num = num;
            this.type = type;
            System.out.println("构造函数");
        }

        public void paint(Graphics g){
            int[][] skip = new int[row][column]; //需跳过的block
            String[] redraw = new String[count]; //需重画的block
            Graphics2D g2 = (Graphics2D) g;
            g.drawRect(100,80,120*column,80*row);
            Font font = new Font("宋体", Font.BOLD, 20);
            g2.setFont(font);
            g2.setColor(Color.BLACK);
            for (int i = 0; i < row; i++){
                for (int j = 0; j < column; j++){
                    if (Arrays.binarySearch(num,matrix[i][j]) >= 0){  // 如果是特殊的block
                        for (int k = 0; k < count; k++){
                            if (Arrays.binarySearch(num,matrix[i][j]) == k){
                                if (type[k].equals("1*2")){
                                    redraw[k] = String.valueOf(i) + j + "12";
                                }else if (type[k].equals("2*1")){
                                    redraw[k] = String.valueOf(i) + j + "21";
                                }else if (type[k].equals("2*2")){
                                    redraw[k] = String.valueOf(i) + j + "22";
                                }
                            }
                        }
                        skip[i][j] = 1;
                    }
                }
            }

            System.out.println(matrix[2][3]);


            for (int i = 0; i < row; i++){
                for (int j = 0; j < column; j++){
                    System.out.print(skip[i][j]);
                    if (skip[i][j] == 0) {
                        g.drawRect(100 + 120 * j, 80 + 80 * i, 120, 80); //画1*1的方块
                    }
                    /*g.drawRect(100 + 120 * j, 80 + 80 * 1, 120, 80); //画1*1的方块*/



                    g2.drawString(String.valueOf(matrix[i][j]),100 + 120 * j + 50,80 + 80 * i + 50); //写数字
                }
                System.out.println();
            }
           /* for (int i = 0; i < count; i++){
                g.setColor(Color.RED);
                int x = redraw[i].charAt(0) - 49;
                int y = redraw[i].charAt(1) - 49;
                g.drawRect(100 + 120 * x, 80 + 80 * y, 120 * (redraw[i].charAt(3) - 49), 80 * (redraw[i].charAt(2) - 49));
            }*/
            /*System.exit(0);*/
        }
    }
