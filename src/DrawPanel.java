import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DrawPanel extends JPanel {
    int row;
    int column;
    int[][] matrix;
    int count; //特殊type的数量
    int[] num; //特殊type左上角的数字
    String[] type; //特殊type：1*2，2*1，2*2

    public DrawPanel(int row, int column, int[][] matrix, int count, int[] num, String[] type) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
        this.count = count;
        this.num = num;
        this.type = type;
    }

    protected static int contains(int[] num,int value){
        for (int i = 0; i < num.length; i++) {
            if(num[i]==value)
                return i;
        }
        return -1;
    }
    public void paint(Graphics g) {
        String[] redraw = new String[count]; //需重画的block
        Graphics2D g2 = (Graphics2D) g;
        g.drawRect(100, 80, 120 * column, 80 * row);
        Font font = new Font("宋体", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (contains(num, matrix[i][j]) >= 0) {  // 如果是特殊的block
                    for (int k = 0; k < count; k++) {
                        if (contains(num, matrix[i][j]) == k) {
                            if (type[k].equals("1*2")) {
                                redraw[k] = String.valueOf(i) + j + "12";
                            } else if (type[k].equals("2*1")) {
                                redraw[k] = String.valueOf(i) + j + "21";
                            } else if (type[k].equals("2*2")) {
                                redraw[k] = String.valueOf(i) + j + "22";
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                g.drawRect(100 + 120 * j, 80 + 80 * i, 120, 80); //画1*1的方块
                if (!String.valueOf(matrix[i][j]).equals("0"))
                    g2.drawString(String.valueOf(matrix[i][j]), 100 + 120 * j + 50, 80 + 80 * i + 50);//写数字
            }
        }
        if (redraw.length != 0){
            for (int i = 0; i < count; i++) {
                g.setColor(Color.RED);
                int x = redraw[i].charAt(1) - 48;
                int y = redraw[i].charAt(0) - 48;
                g.drawRect(100 + 120 * (x), 80 + 80 * (y), 120 * (redraw[i].charAt(3) - 48), 80 * (redraw[i].charAt(2) - 48));
            }
        }
    }
}

