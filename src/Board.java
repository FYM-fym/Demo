import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Board  {
    static final int MINPOS = 0;
    static  int MAXXPOS ;
    static  int MAXYPOS ;
    Block[] blocks; // in sorted order??  棋盘用一个block的数组存当前所有棋子的状态
    HashSet<Board> existedBoards = new HashSet<Board>();
//    Board previousBoard;
//    Board nextBoard;
    private String hashString;
    private int hashCode;
    private boolean hashCodeCalculated;
    int idOfBoardExplored;

    //StepNumber stepNumberToInitialNode; //the step number counting from the initial node of Board.  first step being 0
    //StepNumber stepNumberToSolution = StepNumber.NOT_SOLVED; //step number until the end of best solution.  last step being 0;
    int stepNumberToInitialNode; //the step number counting from the initial node of Board.  first step being 0
    int stepNumberToSolution = Integer.MAX_VALUE;
    boolean isNewBoard = true;
    boolean isBorderLineNode = false;//?
//    GameSolverData gameSolverData = null;

    public Board parentNode;
    public Board link;

    public Board(){

    }


    public Board(Block[] blocks) {
        this.blocks = blocks;
//        Arrays.sort(blocks, Block.blockComparator);
    }

    public Board(Block[] blocks,int maxX,int maxY) {
        this.blocks = blocks;
//        Arrays.sort(blocks, Block.blockComparator);
        MAXXPOS=maxX;
        MAXYPOS=maxY;
    }

    public Board(Block[] sortedblocks, String hashString) {
        this.blocks = sortedblocks;
    }

    //该构造器用于移动后创建新的棋盘
    public Board(Board oldBoard, Block oldBlock, Block newBlock) {
        blocks = new Block[oldBoard.blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            if (oldBoard.blocks[i] != oldBlock) {
                blocks[i] = oldBoard.blocks[i];
            } else {
                blocks[i] = newBlock;
            }
        }
//        Arrays.sort(blocks, Block.blockComparator);
    }

    public BlockType getType(int x, int y){ //根据x, y的值判断block的类型
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
                    if ((blocks[i].xPos == x && blocks[i].yPos + 1 == y) ||(blocks[i].xPos + 1 == x && blocks[i].yPos == y) || (blocks[i].xPos + 1 == x && blocks[i].yPos == y + 1)){
                        return blocks[i].blockfield.blockType;
                    }
                }
            }
        }
        return null;
    }

    boolean isValidMove(Block oldBlock, MoveType moveType) {
        if(oldBlock.blockfield.blockType == BlockType.SINGLE){
            if(moveType == MoveType.UP){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.DOWN){
                if( getType(oldBlock.xPos, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +1)) return false;
            }
            if(moveType == MoveType.LEFT){
                if( getType(oldBlock.xPos - 1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
            }
            if(moveType == MoveType.RIGHT){
                if( getType(oldBlock.xPos +1, oldBlock.yPos) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos)) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.SQUARE){
            if(moveType == MoveType.UP){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos +1, oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.DOWN){
                if( getType(oldBlock.xPos, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +2)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos +1, oldBlock.yPos +2)) return false;
            }
            if(moveType == MoveType.LEFT){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos -1, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos -1, oldBlock.yPos +1)) return false;
            }
            if(moveType == MoveType.RIGHT){
                if( getType(oldBlock.xPos +2, oldBlock.yPos  ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+2 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos +2, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos +2, oldBlock.yPos +1)) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.HORIZONTAL){
            if(moveType == MoveType.UP){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos +1, oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.DOWN){
                if( getType(oldBlock.xPos, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +1 )) return false;
                if( getType(oldBlock.xPos +1, oldBlock.yPos +1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos +1, oldBlock.yPos +1 )) return false;
            }
            if(moveType == MoveType.LEFT){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
            }
            if(moveType == MoveType.RIGHT){
                if( getType(oldBlock.xPos +2, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+2 , oldBlock.yPos )) return false;
            }
        }
        if(oldBlock.blockfield.blockType == BlockType.VERTICAL){
            if(moveType == MoveType.UP){
                if( getType(oldBlock.xPos, oldBlock.yPos -1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos -1)) return false;
            }
            if(moveType == MoveType.DOWN){
                if( getType(oldBlock.xPos, oldBlock.yPos +2 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos , oldBlock.yPos +2 )) return false;
            }
            if(moveType == MoveType.LEFT){
                if( getType(oldBlock.xPos-1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos)) return false;
                if( getType(oldBlock.xPos-1, oldBlock.yPos+1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos-1 , oldBlock.yPos+1)) return false;
            }
            if(moveType == MoveType.RIGHT){
                if( getType(oldBlock.xPos +1, oldBlock.yPos ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos )) return false;
                if( getType(oldBlock.xPos+1, oldBlock.yPos+1 ) != BlockType.BLANK ) return false;
                if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos+1 , oldBlock.yPos+1)) return false;
            }
        }
        return true;
    }

//    //该block数组是否有效，要求每一个block都是有效移动位置
//    public boolean isValid() {
//        for (int i = 0; i < blocks.length; i++) {
//            if (!isValidblock(blocks[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isValidblock(Block a) {
//        return isValidMove(a, a);
//    }
//
//    boolean isValidMove(Block oldBlock, int deltaXPos, int deltaYPos) {
//        // The block is within the board boundary
//        if (!isblockInBoundary(oldBlock.blockfield, oldBlock.xPos + deltaXPos, oldBlock.yPos + deltaYPos)) return false;
//
//        // No overlapping block placements
//        for (Block block : blocks) {
//            if (block != oldBlock) {
//                if (areOverlappingblocks(block, oldBlock, deltaXPos, deltaYPos)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * @param oldBlock
//     * @param newBlock
//     * @return Check whether the move from oldBlockPlacment to newBlock is a valid move
//     * The newBlock should be within the board boundary and does not overlap with any
//     * other blocks.
//     */
//
//    private boolean isValidMove(Block oldBlock, Block newBlock) {
//
//        assert newBlock != null;
//        // The block is within the board boundary
//        assert isblockInBoundary(newBlock.blockfield, newBlock.xPos, newBlock.yPos);
//        //减少了移动的种类，是否需要？？？
//        // No overlapping block placements  没有跨过其它棋子移动
//        for (int i = 0; i < blocks.length; i++) {
//            if (blocks[i] != oldBlock) {
//                if (areOverlappingblocks(blocks[i], newBlock)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
    private static boolean isblockInBoundary(Blockfield block, int xPos, int yPos) {
        return (xPos >= MINPOS
                && xPos + block.width - 1 <= MAXXPOS
                && yPos >= MINPOS
                && yPos + block.height - 1 <= MAXYPOS);
    }
//
//    /**
//     * Check whether 2 blocks are overlapping each other.
//     * xPos overlapping = a overlaps b from left or right
//     * yPos overlapping = a overlaps b from up or down
//     */
//
//    private static boolean areOverlappingblocks(Block a, Block b) {
//        return (b.xPos >= a.xPos
//                && b.xPos <= a.xPos + a.blockfield.width - 1
//                || a.xPos >= b.xPos
//                && a.xPos <= b.xPos + b.blockfield.width - 1)
//                && (b.yPos >= a.yPos
//                && b.yPos <= a.yPos + a.blockfield.height - 1
//                || a.yPos >= b.yPos
//                && a.yPos <= b.yPos + b.blockfield.height - 1);
//    }
//
//
//    private static boolean areOverlappingblocks(Block a, Block b, int deltaXPos, int deltaYPos) {
//        return (b.xPos + deltaXPos >= a.xPos
//                && b.xPos + deltaXPos <= a.xPos + a.blockfield.width - 1
//                || a.xPos >= b.xPos + deltaXPos
//                && a.xPos <= b.xPos + deltaXPos + b.blockfield.width - 1)
//                && (b.yPos + deltaYPos >= a.yPos
//                && b.yPos + deltaYPos <= a.yPos + a.blockfield.height - 1
//                || a.yPos >= b.yPos + deltaYPos
//                && a.yPos <= b.yPos + deltaYPos + b.blockfield.height - 1);
//    }

    /**
     * @return hashString
     * <p>
     * hashString uniquely identify a Board by specifying the position of all the BlockPlacments.
     * Note the blockPlacments[] is sorted by Block type and position for this to work.
     * Also note the name of the Block is not considered.
     */


//    存棋盘的哈希值
    public String hashString() {
        if (hashString == null) {
            StringBuilder hashStringBuilder = new StringBuilder();
            for (int i = 0; i < blocks.length; i++) {
                hashStringBuilder.append(blocks[i].hashString());
            }
            hashString = hashStringBuilder.toString();
        }
        return hashString;
    }

//    @Deprecated
//    private static String hashString(Block[] blocks) {
//        String hashString;
//        StringBuilder hashStringBuilder = new StringBuilder();
//        for (int i = 0; i < blocks.length; i++) {
//            hashStringBuilder.append(blocks[i].hashString());
//        }
//        hashString = hashStringBuilder.toString();
//        return hashString;
//    }

    @Override
    public int hashCode() {
        if (!hashCodeCalculated) {
            hashCode = hashString().hashCode();
//            hashCodeCalculated = true;
        }
        return hashCode;
    }

    @Override
    // Needed for HashSet connectedBoards
    public boolean equals(Object obj) {
        Board b = (Board) obj;
        if (hashString().equals(b.hashString())) {
            return true;
        }
        return false;
    }

    public void checkWhetherSolved() {
        // Already solved
        if (stepNumberToSolution != Integer.MAX_VALUE) {
            return;
        }

        // check for new solution   ?
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].blockfield.blockType == BlockType.SQUARE) {
                if (blocks[i].xPos == (MAXXPOS - 1) / 2
                        && blocks[i].yPos == MAXYPOS - 1) {
                    stepNumberToSolution = 0;
                    return;
                }
            }
        }
        return;
    }


    /**
     * @param nextNode
     * @return Calculated the move that takes the current Board to the nextNode
     */


    public Move calculcateMove(Board nextNode) {
        Move move;
        boolean foundMatch;
        Block oldBlock = null, newBlock = null;
        for (int i = 0; i < blocks.length; i++) {
            foundMatch = false;
            for (int j = 0; j < nextNode.blocks.length; j++) {
                if (blocks[i].blockfield.blockType == nextNode.blocks[j].blockfield.blockType
                        && blocks[i].xPos == nextNode.blocks[j].xPos
                        && blocks[i].yPos == nextNode.blocks[j].yPos) {
                    // found match
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                oldBlock = blocks[i];
            }
        }

        for (int j = 0; j < nextNode.blocks.length; j++) {
            foundMatch = false;
            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i].blockfield.blockType == nextNode.blocks[j].blockfield.blockType
                        && blocks[i].xPos == nextNode.blocks[j].xPos
                        && blocks[i].yPos == nextNode.blocks[j].yPos) {
                    // found match
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                newBlock = nextNode.blocks[j];
            }
        }

        if (oldBlock != null && newBlock != null) {
            int deltaXPos = newBlock.xPos - oldBlock.xPos;
            int deltaYPos = newBlock.yPos - oldBlock.yPos;
            move = new Move(this, oldBlock, deltaXPos, deltaYPos);
            return move;
        }
        assert false;
        return null;
    }


    /**
     * @author weian.zhu
     * <p>
     * Comparator of the stepToSolution
     */
    public class StepToSolutionComparator implements Comparator<Board> {
        @Override
        public int compare(Board arg0, Board arg1) {

            return arg0.stepNumberToSolution == arg1.stepNumberToSolution ? 0
                    : arg0.stepNumberToSolution < arg1.stepNumberToSolution ? -1
                    : 1;
        }

    }

    public class StepToInitialNodeComparator implements Comparator<Board> {
        @Override
        public int compare(Board arg0, Board arg1) {

            return arg0.stepNumberToInitialNode == arg1.stepNumberToInitialNode ? 0
                    : arg0.stepNumberToInitialNode < arg1.stepNumberToInitialNode ? -1
                    : 1;
        }
    }

}
