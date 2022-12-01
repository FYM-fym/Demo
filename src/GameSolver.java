import java.util.*;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;

public class GameSolver {
    Graph graph;
    Game game;
    protected HashMap<String, Board> boardConfigsExplored = new HashMap<>();
    protected int nextIdOfBoardExplored = 0;
    Queue<Board> boardConfigWorkQueue=new Queue<>();
    Board solutionNode;

    public void solve(Game game) {
        game.initialBoard.idOfBoardExplored = nextIdOfBoardExplored ++;
//        已经存在过的棋盘状态放入哈希表里
        boardConfigsExplored.put(game.initialBoard.hashString(), game.initialBoard);
        this.game = game;
        buildGraph(game.initialBoard);
        findOneShortestPath();
    }
    /*
    public void insertBoard(ChessStep newitem)
{
  TreeNode newNode;

  newNode = new TreeNode();
  newNode.chessStep = newitem;
  newNode.link = null;
  newNode.parentNode = null;

  if(root == null)
  {
    root = newNode;
    current = newNode;
    end = current;
  }
  else
  {
    newNode.parentNode = current;
    end.link = newNode;
    end = newNode;
    end.link = newNode;
    end = newNode;
  }
  count++;
}
     */


        private void buildGraph (Board currentBoard){
            int sameLayer = 0;
            while (currentBoard != null) {
                for (int i = 0; i < currentBoard.blocks.length; i++) {
                    //当前可移动的类型
                    MoveType[] moveTypes = validMoveTypes.get(currentBoard.blocks[i].blockfield.blockType);
                    for (MoveType moveType : moveTypes) {
                        Block newBlock = calcNewBlock(currentBoard, currentBoard.blocks[i], moveType);
                        if (newBlock == null) continue; // Invalid move
                        Board nextBoard = getNextBoard(currentBoard, currentBoard.blocks[i], newBlock);




                        //??
//                    PrintUtility.printTestMove(currentBoard, i, currentBoard.blocks[i], moveType, nextBoard);
//                    if(!nextBoard.equals(solutionNode)&&nextBoard.isNewBoard){
//                        boardConfigWorkQueue
//
//                    }

                        if (nextBoard.stepNumberToSolution < Integer.MAX_VALUE) {
                            solutionNode = nextBoard; //The first solution we reach is the fastest solution.
                            return;
                        }

                        // Only explore the nodes that have not been explored before.
                        if (nextBoard.isNewBoard) {
                            //PrintUtility.printTestMove(currentBoard, i, moveInTest, nextBoardInTest);
                            boardConfigWorkQueue.enqueue(nextBoard);
                        }
                    }
                }
                if (!boardConfigWorkQueue.isEmpty())
                    currentBoard = boardConfigWorkQueue.dequeue();
            }
        }
//?
    protected void findOneShortestPath() {
        Board iterator = solutionNode;
        while (iterator != game.initialBoard) {
            ArrayList<Board> connected = new ArrayList<Board>(iterator.existedBoards);
            connected.sort(iterator.new StepToInitialNodeComparator());
            Board previousStep = connected.get(0);
            previousStep.stepNumberToSolution = iterator.stepNumberToSolution + 1;
            iterator = previousStep;
        }
    }

//    private static final  MoveType[] validMoveTypesSQUARE =  MoveType.values();
//    private static final  MoveType[] validMoveTypesHORIZONTAL =  MoveType.values();
//    private static final  MoveType[] validMoveTypesVERTICAL =  MoveType.values();
//    private static final  MoveType[] validMoveTypesSINGLE =  MoveType.values();
    static EnumMap<BlockType, MoveType[]> validMoveTypes = new EnumMap<BlockType, MoveType[]>(BlockType.class);


    static {
        validMoveTypes.put(BlockType.SQUARE, MoveType.values());
        validMoveTypes.put(BlockType.HORIZONTAL, MoveType.values());
        validMoveTypes.put(BlockType.VERTICAL, MoveType.values());
        validMoveTypes.put(BlockType.SINGLE, MoveType.values());
    }


    Board getNextBoard(Board currentBoard, Block oldBlock, Block newBlock) {
        Board newBoard = new Board(currentBoard, oldBlock, newBlock);
        String boardConfigHashString = newBoard.hashString();
        if (boardConfigsExplored.containsKey(boardConfigHashString) ) {
            // use the existing instance in game.boardConfigsExplored
            newBoard = boardConfigsExplored.get(boardConfigHashString);
            newBoard.isNewBoard = false;
        }
        else {
            newBoard.idOfBoardExplored = nextIdOfBoardExplored ++;
            boardConfigsExplored.put(boardConfigHashString, newBoard);
            newBoard.stepNumberToInitialNode = currentBoard.stepNumberToInitialNode + 1;
            // Check if a solution
            newBoard.checkWhetherSolved();
        }
//        newBoard.existedBoards.add(currentBoard);
        currentBoard.existedBoards.add(newBoard);
        return newBoard;
    }

    private static final int[][] moveStepsUP = {{0, -1}};
    private static final int[][] moveStepsDOWN = {{0, 1}};
    private static final int[][] moveStepsLEFT = {{-1, 0}};
    private static final int[][] moveStepsRIGHT = {{1, 0}};
//    static final private int[][] moveStepsUP2 = {{0, -1}, {0, -2}};
//    static final private int[][] moveStepsDOWN2 = {{0, 1}, {0, 2}};
//    static final private int[][] moveStepsLEFT2 = {{-1, 0}, {-2, 0}};
//    static final private int[][] moveStepsRIGHT2 = {{1, 0}, {2, 0}};
//    static final private int[][] moveStepsNOTHING = {{0, 0}};
//    static final private int[][] moveStepsUPLEFT = {{0, -1}, {-1, -1}};
//    static final private int[][] moveStepsUPRIGHT = {{0, -1}, {1, -1}};
//    static final private int[][] moveStepsDOWNLEFT = {{0, 1}, {-1, 1}};
//    static final private int[][] moveStepsDOWNRIGHT = {{0, 1}, {1, 1}};
//    static final private int[][] moveStepsLEFTUP = {{-1, 0}, {-1, -1}};
//    static final private int[][] moveStepsLEFTDOWN = {{-1, 0}, {-1, 1}};
//    static final private int[][] moveStepsRIGHTUP = {{1, 0}, {1, -1}};
//    static final private int[][] moveStepsRIGHTDOWN = {{1, 0}, {1, 1}};

//根据当前棋盘的情况，当前的block以及移动方向，返回能移动到的block
    static Block calcNewBlock(Board oldBoard, Block oldBlock, MoveType moveType) {
        int[][] moveSteps = null;
        switch (moveType) {
            case UP:
                moveSteps = moveStepsUP;
                break;
            case DOWN:
                moveSteps = moveStepsDOWN;
                break;
            case LEFT:
                moveSteps = moveStepsLEFT;
                break;
            case RIGHT:
                moveSteps = moveStepsRIGHT;
                break;
//            case UP2:
//                moveSteps = moveStepsUP2;
//                break;
//            case DOWN2:
//                moveSteps = moveStepsDOWN2;
//                break;
//            case LEFT2:
//                moveSteps = moveStepsLEFT2;
//                break;
//            case RIGHT2:
//                moveSteps = moveStepsRIGHT2;
//                break;
//            case NOTHING:
//                moveSteps = moveStepsNOTHING;
//                break;
//            case UPLEFT:
//                moveSteps = moveStepsUPLEFT;
//                break;
//            case UPRIGHT:
//                moveSteps = moveStepsUPRIGHT;
//                break;
//            case DOWNLEFT:
//                moveSteps = moveStepsDOWNLEFT;
//                break;
//            case DOWNRIGHT:
//                moveSteps = moveStepsDOWNRIGHT;
//                break;
//            case LEFTUP:
//                moveSteps = moveStepsLEFTUP;
//                break;
//            case LEFTDOWN:
//                moveSteps = moveStepsLEFTDOWN;
//                break;
//            case RIGHTUP:
//                moveSteps = moveStepsRIGHTUP;
//                break;
//            case RIGHTDOWN:
//                moveSteps = moveStepsRIGHTDOWN;
//                break;
            default:
                assert false;
        }
        for (int i = 0; i < validMoveTypes.get(oldBlock.blockfield.blockType).length; i++) {
            if (!oldBoard.isValidMove(oldBlock,validMoveTypes.get(oldBlock.blockfield.blockType)[i])) {
                return null;
            }
        }
        return new Block(oldBlock, moveSteps[moveSteps.length-1][0], moveSteps[moveSteps.length-1][1]);
    }
}
