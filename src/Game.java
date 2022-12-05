import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public Board initialBoard;

//    String name;

    public Game( Board initialBoard) {
//      this.name = name;
        this.initialBoard = initialBoard;
        initialBoard.stepNumberToInitialNode = 0;
    }

    public Game() {
//      this.name = name;
//        initialBoard.stepNumberToInitialNode = 0;
    }

//    public static void main(String[] args) {
        GameSolver gameSolver = new GameSolver();
//        Game game = initGame横刀立马();
//      Game game = initGame四将连关();
//        gameSolver.solve(game);

//        System.out.println("<html><body><font face=Courier>");
//        PrintUtility.printFinalSolution(game, gameSolver);

//    }

    public static void main(String[] args) throws IOException {
        Game game=new Game();
        new Frame();

//        game.initialBoard.setInitialBoard();
        GameSolver gameSolver=new GameSolver();
    }






}
