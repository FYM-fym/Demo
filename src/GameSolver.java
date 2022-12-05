import java.util.*;
import java.util.Queue;

import edu.princeton.cs.algs4.*;

public class GameSolver {
    //    private static Graph graph;
    Game game;
    protected HashMap<String, Board> boardConfigsExplored = new HashMap<>();
    //    protected int nextIdOfBoardExplored = 0;
    LinkedList<Board> tree=new LinkedList<>();
    LinkedList <Board> Queue=new LinkedList<>();

//    Board solutionNode;
//    Frame frame;

    public void solve(Game game) {
//        game.initialBoard.idOfBoardExplored = nextIdOfBoardExplored ++;
//        已经存在过的棋盘状态放入哈希表里
//        boardConfigsExplored.put(game.initialBoard.hashString(), game.initialBoard);
        this.game = game;
        buildGraph(game.initialBoard);
        bfs();
//        findOneShortestPath();
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

    private void buildGraph (Board currentBoard) {
        while (currentBoard != null) {
            tree.add(currentBoard);
            Board nextBoard;
            System.out.println(tree);
//                if(currentBoard.link!=null)
            for (int i = 0; i < currentBoard.link.size(); i++) {
                for (int j = 0; j < currentBoard.blocks.length; j++) {
                    nextBoard = getNextBoard(currentBoard, currentBoard.blocks[j]);
                    if (nextBoard != null && nextBoard.isNewBoard) {
                        tree.add(nextBoard);
                        currentBoard.link.add(nextBoard);
                        nextBoard.parentNode = currentBoard;
                        //??
//                    PrintUtility.printTestMove(currentBoard, i, currentBoard.blocks[i], moveType, nextBoard);
//                    if(!nextBoard.equals(solutionNode)&&nextBoard.isNewBoard){
//                        boardConfigWorkQueue
//
//                    }

//                        if (nextBoard.stepNumberToSolution < Integer.MAX_VALUE) {
//                            solutionNode = nextBoard; //The first solution we reach is the fastest solution.
//                            return;
//                        }

                        // Only explore the nodes that have not been explored before.
//                            if (nextBoard.isNewBoard) {
//                                Queue.add(nextBoard);
//                                nextBoard.isNewBoard = false;
//                            }
                    }
                }
                currentBoard = currentBoard.link.get(i);
            }
        }
    }

    private void bfs(){
        int head = 0;
        int rear = 0;
        Queue.add(rear++,tree.get(0));
        tree.get(0).isvisited=true;
        while (head != rear) {
            for (int j = 0; j < Queue.get(head).link.size(); j++) {
                if (!Queue.get(head).link.get(j).isvisited) {
                    Queue.add(rear++, Queue.get(head).link.get(j));
                    Queue.get(head).link.get(j).isvisited=true;
                }
            }
            head++;
        }
    }


//    protected void findOneShortestPath() {
//        Board iterator = solutionNode;
//        while (iterator != game.initialBoard) {
//            ArrayList<Board> connected = new ArrayList<Board>(iterator.existedBoards);
//            connected.sort(iterator.new StepToInitialNodeComparator());
//            Board previousStep = connected.get(0);
//            previousStep.stepNumberToSolution = iterator.stepNumberToSolution + 1;
//            iterator = previousStep;
//        }
//    }

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


    private Board getNextBoard(Board currentBoard, Block oldBlock) {
        MoveType[] moveTypes = validMoveTypes.get(oldBlock.blockfield.blockType);
        for (MoveType moveType : moveTypes) {
            Block newBlock = calcNewBlock(currentBoard, oldBlock, moveType);
            if (newBlock == null) continue;
            Board newBoard = new Board(currentBoard, oldBlock, newBlock);
            String boardConfigHashString = newBoard.hashString();
            if (boardConfigsExplored.containsKey(boardConfigHashString)) {
                newBoard = boardConfigsExplored.get(boardConfigHashString);
                newBoard.isNewBoard = false;
            } else {
//            newBoard.idOfBoardExplored = nextIdOfBoardExplored ++;
                boardConfigsExplored.put(boardConfigHashString, newBoard);
//            newBoard.stepNumberToInitialNode = currentBoard.stepNumberToInitialNode + 1;
                // Check if a solution
//            newBoard.checkWhetherSolved();
            }
//        newBoard.existedBoards.add(currentBoard);
            currentBoard.existedBoards.add(newBoard);
            return newBoard;
        }
        return null;
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
    protected static Block calcNewBlock(Board oldBoard, Block oldBlock, MoveType moveType) {
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

    private Board endBoard(){
        int size=game.initialBoard.MAXXPOS*game.initialBoard.MAXYPOS;
        int[] solution = new int[size];
        int m=0;int n=0;
        while (m<size) {
            for (int i = 0; i < game.initialBoard.MAXXPOS; i++) {
                for (int j = 0; j < game.initialBoard.MAXYPOS; j++) {
                    if(game.initialBoard.matrix[i][j]!=0)
                        solution[m]=game.initialBoard.matrix[i][j];
                }
            }
            m++;
        }
        Arrays.sort(solution);
        int[][] matrix=new int[game.initialBoard.MAXXPOS][game.initialBoard.MAXYPOS];
        while (n<size) {
            for (int i = 0; i < game.initialBoard.MAXXPOS; i++) {
                for (int j = 0; j < game.initialBoard.MAXYPOS; j++) {
                    matrix[i][j]=solution[n];
                }
            }
            n++;
        }
        Board end=new Board(matrix);
        return end;
    }

}
