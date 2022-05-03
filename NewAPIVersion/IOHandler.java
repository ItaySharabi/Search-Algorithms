package NewAPIVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {

//    private final String outputFilePath;
//    private String algorithmName;
//    private boolean withOpenList;
//    private String[][] initialBoard;
//    private String[][] goalBoard;

    private IOHandler(String inputFilePath) {

    }

    public static IProblem readProblem(String inputFilePath) {
        Scanner s;
        try {
            s = new Scanner(new File(inputFilePath));

            // Algorithm name to execute
            String algorithmName = s.nextLine();
            System.out.println("Algorithm to execute: " + algorithmName);

            // Verbose output
            boolean withOpenList = s.nextLine().equalsIgnoreCase("with open");
            System.out.println("With open? " + withOpenList);

            // Node board size
            int dim = s.nextLine().equalsIgnoreCase("big") ? 5 : 3;
            System.out.println("Node size: " + dim + "x" + dim);

            // Build up a char[][] start and goal matrices:
            char[][] initialBoard = createBoard(dim, s);
            System.out.println(s.nextLine()); // Discard spare line: `Goal state`
            char[][] goalBoard = createBoard(dim, s);
            return new Problem(new State(initialBoard), new State(goalBoard));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static char[][] createBoard(int dim, Scanner s) {
        // Build up a char[][] matrix
        char[][] board = new char[dim][dim];
        String[] ln;
        int countR = 0, countB = 0, countG = 0, countY = 0;
        char R = 'R';
        char G = 'G';
        char B = 'B';
        char Y = 'Y';
        for (int i = 0; i < dim; ++i) {
            ln = s.nextLine().split(",");
            if (ln.length <= 0) {throw new InputMismatchException("Input length: " + ln.length);}
            for (int j = 0; j < dim; ++j) {

                if (ln[j].equals(R + "")) {
                    board[i][j] = R; countR++;}

                else if (ln[j].equals(G + "")) {
                    board[i][j] = G; countG++;}

                else if (ln[j].equals(B + "")) {
                    board[i][j] = B; countB++;}

                else if (dim == 5) {
                    if ((ln[j].equals(Y + ""))) {
                        board[i][j] = Y; countY++;}
                    else {board[i][j] = '_';}}

                else {
                    board[i][j] = '_';}
            }
        }
        if (dim == 3 && countR > 2 || countB > 2 || countG > 2 || countY > 0) {
            throw new InputMismatchException("IOHandler:: Wrong GameBoard input");
        } else if (dim == 5 && (countR > 4 || countB > 4 || countG > 4 || countY > 4)) {
            throw new InputMismatchException("IOHandler:: Wrong GameBoard input");
        }
        return board;
    }

    public void writeResults() {
        System.out.println("Implement Writing results to output.txt file!");
    }

    public boolean write(String content) throws IOException {
        String outputFilePath = "src/output.txt";
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
