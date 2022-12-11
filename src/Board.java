import javax.swing.*;

public class Board extends JComponent {
    protected int MAXXPOS ;
    protected int MAXYPOS ;
    protected int[][]matrix;
    protected Block[] blocks;
    public String hashString;
    public int hashCode;
    public boolean hashCodeCalculated;
    boolean isNewBoard = true;
    protected String everyStep;
    int row;
    int column;
    int count;
    int[] num;
    String[] type;
    int BlockNumber;
    int max;


    public Board(int[][] matrix) {
        this.matrix=matrix;
        this.MAXXPOS=matrix[0].length;
        this.MAXYPOS=matrix.length;
    }

    public Board(Block[] blocks,int maxX,int maxY) {
        this.blocks = blocks;
        MAXXPOS=maxX;
        MAXYPOS=maxY;
    }

    //该构造器用于移动后创建新的棋盘
    public Board(Board oldBoard, Block oldBlock, Block newBlock) {
        blocks = new Block[oldBoard.blocks.length];
        if (oldBlock.blockfield.blockType == BlockType.SINGLE ){ // 1*1向左移动
            for (int i = 0; i < blocks.length; i++){
                if (oldBoard.blocks[i] != oldBlock && oldBoard.blocks[i] != newBlock) blocks[i] = oldBoard.blocks[i];
                if (oldBoard.blocks[i] == oldBlock) blocks[i] = new Block(newBlock.blockfield, oldBlock.xPos, oldBlock.yPos);
                if (oldBoard.blocks[i] == newBlock) blocks[i] = new Block(oldBlock.blockfield, newBlock.xPos, newBlock.yPos);
            }
        }

        /*for (int i = 0; i < blocks.length; i++) {
            if (oldBoard.blocks[i] != oldBlock) {
                if(oldBoard.blocks[i] == newBlock) blocks[i] = oldBlock;
                else{
                    blocks[i] = oldBoard.blocks[i];
                }
            } else {
                blocks[i] = newBlock;
            }
        }*/

        /*for (int i = 0; i < blocks.length; i++) {
            if (oldBoard.blocks[i] != oldBlock) {
                blocks[i] = oldBoard.blocks[i];
            } else {
                blocks[i] = newBlock;
            }
        }*/

        for (int i = 0; i < blocks.length; i++) {
            System.out.print((blocks[i].blockfield.number) + "X = "+ blocks[i].xPos + "  Y = " + blocks[i].yPos);
            System.out.println();
        }

        /*if (oldBlock.blockfield.blockType == BlockType.SINGLE && newBlock.xPos + 1 == oldBlock.xPos){ // 1*1向左移动
            for (int i = 0; i < blocks.length; i++){
                if (oldBoard.blocks[i] != oldBlock && oldBoard.blocks[i] != newBlock) blocks[i] = oldBoard.blocks[i];
                if (oldBoard.blocks[i] == oldBlock) blocks[i] = new Block(newBlock.blockfield, oldBlock.xPos, oldBlock.yPos);
                if (oldBoard.blocks[i] == newBlock) blocks[i] = new Block(oldBlock.blockfield, newBlock.xPos, newBlock.yPos);
            }
        }
        if (oldBlock.blockfield.blockType == BlockType.SINGLE && newBlock.xPos - 1 == oldBlock.xPos){ // 1*1向右移动
            for (int i = 0; i < blocks.length; i++){
                if (oldBoard.blocks[i] != oldBlock && oldBoard.blocks[i] != newBlock) blocks[i] = oldBoard.blocks[i];
                if (oldBoard.blocks[i] == oldBlock) blocks[i] = new Block(newBlock.blockfield, oldBlock.xPos, oldBlock.yPos);
                if (oldBoard.blocks[i] == newBlock) blocks[i] = new Block(oldBlock.blockfield, newBlock.xPos, newBlock.yPos);
            }
        }
        if (oldBlock.blockfield.blockType == BlockType.SINGLE && newBlock.yPos + 1 == oldBlock.yPos){ // 1*1向上移动
            for (int i = 0; i < blocks.length; i++){
                if (oldBoard.blocks[i] != oldBlock && oldBoard.blocks[i] != newBlock) blocks[i] = oldBoard.blocks[i];
                if (oldBoard.blocks[i] == oldBlock) blocks[i] = new Block(newBlock.blockfield, oldBlock.xPos, oldBlock.yPos);
                if (oldBoard.blocks[i] == newBlock) blocks[i] = new Block(oldBlock.blockfield, newBlock.xPos, newBlock.yPos);
            }
        }*/


        /*if (oldBlock.blockfield.blockType == BlockType.HORIZONTAL && newBlock.xPos + 1 == oldBlock.xPos){ // 1*2向左移动
            for (int i = 0; i < blocks.length; i++){
                if (oldBoard.blocks[i] != oldBlock && !(oldBoard.blocks[i].xPos + 1 == oldBlock.xPos && oldBoard.blocks[i].yPos == oldBlock.yPos)) blocks[i] = oldBoard.blocks[i];
                if (oldBoard.blocks[i] == oldBlock)  blocks[i] = new Block(oldBlock.blockfield, oldBlock.xPos - 1, oldBlock.yPos);
                if (oldBoard.blocks[i].xPos + 1 == oldBlock.xPos && oldBoard.blocks[i].yPos == oldBlock.yPos)  blocks[i] = new Block(oldBoard.blocks[i].blockfield, oldBlock.xPos + 1, oldBlock.yPos);
            }
        }*/

        /*blocks = new Block[oldBoard.blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            if (oldBoard.blocks[i] != oldBlock) {
                if(oldBoard.blocks[i] == newBlock) blocks[i] = oldBlock;
                else{
                    blocks[i] = oldBoard.blocks[i];
                }
            } else {
                blocks[i] = newBlock;
            }
        }*/
        }


    //根据x, y的值判断block的类型
    public BlockType getType(int x, int y){
        for (int i = 0; i < blocks.length; i++){
            if (Math.abs(blocks[i].xPos - x) <= 1 && Math.abs(blocks[i].yPos - y) <= 1 ){
                if (blocks[i].xPos == x && blocks[i].yPos == y){
                    return blocks[i].blockfield.blockType;
                }
                else if (blocks[i].blockfield.blockType == BlockType.VERTICAL){
                    if (blocks[i].xPos == x && blocks[i].yPos + 1 == y) return blocks[i].blockfield.blockType;
                }else if (blocks[i].blockfield.blockType == BlockType.HORIZONTAL){
                    if (blocks[i].xPos + 1 == x && blocks[i].yPos == y) return blocks[i].blockfield.blockType;
                }else if (blocks[i].blockfield.blockType == BlockType.SQUARE){
                    if ((blocks[i].xPos == x && blocks[i].yPos + 1 == y) ||(blocks[i].xPos + 1 == x && blocks[i].yPos == y) || (blocks[i].xPos + 1 == x && blocks[i].yPos + 1 == y )){
                        return blocks[i].blockfield.blockType;
                    }
                }
            }
        }
        return null;
    }

    boolean isValidMove(Block oldBlock, MoveType moveType) {
        if(oldBlock.blockfield.blockType == BlockType.SINGLE){
            if(moveType == MoveType.U){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.D){
                if( getType(oldBlock.xPos, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +1)) return false;
            }
            if(moveType == MoveType.L){
                if( getType(oldBlock.xPos - 1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
            }
            if(moveType == MoveType.R){
                if( getType(oldBlock.xPos +1, oldBlock.yPos) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos)) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.SQUARE){
            if(moveType == MoveType.U){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.D){
                if( getType(oldBlock.xPos, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +2)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.L){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos -1, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.R){
                if( getType(oldBlock.xPos +2, oldBlock.yPos  ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos +2, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
            if(moveType == MoveType.U){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.D){
                if( getType(oldBlock.xPos, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +1 )) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.L){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
            }
            if(moveType == MoveType.R){
                if( getType(oldBlock.xPos +2, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos )) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.VERTICAL){
            if(moveType == MoveType.U){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.D){
                if( getType(oldBlock.xPos, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +1 )) return false;
            }
            if(moveType == MoveType.L){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos-1, oldBlock.yPos+1 ) != BlockType.BLANK ) return false;
            }
            if(moveType == MoveType.R){
                if( getType(oldBlock.xPos +1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos )) return false;
                if( getType(oldBlock.xPos+1, oldBlock.yPos+1 ) != BlockType.BLANK ) return false;
            }
        }
        return true;
    }

    private boolean isblockInBoundary(Blockfield block, int xPos, int yPos) {
        return (xPos >= 0
                && xPos + block.width - 1 < this.MAXXPOS
                && yPos >= 0
                && yPos + block.height - 1 < this.MAXYPOS);
    }

//    存棋盘的哈希值
    public String hashString() {
        if (hashString == null) {
            StringBuilder hashStringBuilder = new StringBuilder();
            for (int i = 0; i < this.blocks.length; i++) {
                hashStringBuilder.append(this.blocks[i].hashString());
            }
            hashString = hashStringBuilder.toString();
        }
        return hashString;
    }


    @Override
    public int hashCode() {
        if (!hashCodeCalculated) {
            hashCode = hashString().hashCode() & Integer.MAX_VALUE;
            hashCodeCalculated = true;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        Board b = (Board) obj;
        if (hashString().equals(b.hashString())) {
            return true;
        }
        return false;
    }
}


