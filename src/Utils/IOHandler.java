package src.Utils;

import src.API.IProblem;
import src.Model.Problem;
import src.Model.State;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {

    private String algorithmName;
    private boolean withOpenList;
    private String[][] initialBoard;
    private String[][] goalBoard;
    private IProblem p;

    public IOHandler(String inputFilePath) {

        Scanner s;
        try {
            s = new Scanner(new File(inputFilePath));

            // src.API.Algorithm name to execute
            this.algorithmName = s.nextLine();

            // Verbose output
            this.withOpenList = s.nextLine().equalsIgnoreCase("with open");

            // src.Model.Node board size
            int dim = s.nextLine().equalsIgnoreCase("big") ? 5 : 3;

            // Build up a char[][] start and goal matrices:
            this.initialBoard = createBoard(dim, s);
            s.nextLine(); // Discard spare line: `Goal state`
            this.goalBoard = createBoard(dim, s);
            p = new Problem(
                    new State(initialBoard),
                    new State(goalBoard)
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[][] createBoard(int dim, Scanner s) {
        // Build up a char[][] matrix
        String [][]board = new String[dim][dim];
        String[] ln;
        int countR = 0, countB = 0, countG = 0, countY = 0;
        String R = "R";
        String G = "G";
        String B = "B";
        String Y = "Y";
        for (int i = 0; i < dim; ++i) {
            ln = s.nextLine().split(",");
            if (ln.length <= 0) {return null;}
            for (int j = 0; j < dim; ++j) {

                if (ln[j].equals(R)) {
                    board[i][j] = R; countR++;}

                else if (ln[j].equals(G)) {
                    board[i][j] = G; countG++;}

                else if (ln[j].equals(B)) {
                    board[i][j] = B; countB++;}

                else if (dim == 5) {
                    if ((ln[j].equals(Y))) {
                        board[i][j] = Y; countY++;}
                    else {board[i][j] = "_";}}

                else {
                    board[i][j] = "_";}
            }
        }
        if (dim == 3 && (countR > 2 || countB > 2 || countG > 2 || countY > 0)) {
            throw new InputMismatchException("src.Utils.IOHandler:: Wrong GameBoard input");
        } else if (dim == 5 && (countR > 4 || countB > 4 || countG > 4 || countY > 4)) {
            throw new InputMismatchException("src.Utils.IOHandler:: Wrong GameBoard input");
        }
        return board;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public boolean getVerbose() {
        return this.withOpenList;
    }

    public IProblem getProblem() {
        return p;
    }

    public boolean write(String content, String outputFilePath) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(outputFilePath));

            fw.write(content);
            fw.close();
            return true;
        } catch (IOException e) {
            if (null != fw) fw.close();
            e.printStackTrace();
        }
        return false;
    }
}
