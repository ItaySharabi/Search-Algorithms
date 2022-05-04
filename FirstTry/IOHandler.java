import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {

    private final String outputFilePath;
    private String algorithmName;
    private boolean withOpenList;
    private String[][] initialBoard;
    private String[][] goalBoard;

    public IOHandler(String inputFilePath) {
        this.outputFilePath = "src/output.txt";

        Scanner s;
        try {
            s = new Scanner(new File(inputFilePath));

            // Algorithm name to execute
            this.algorithmName = s.nextLine();
            System.out.println("Algorithm to execute: " + algorithmName);

            // Verbose output
            this.withOpenList = s.nextLine().equalsIgnoreCase("with open");
            System.out.println("With open? " + withOpenList);

            // Node board size
            int dim = s.nextLine().equalsIgnoreCase("big") ? 5 : 3;
            System.out.println("Node size: " + dim + "x" + dim);

            // Build up a char[][] start and goal matrices:
            this.initialBoard = createBoard(dim, s);
            System.out.println(s.nextLine()); // Discard spare line: `Goal state`
            this.goalBoard = createBoard(dim, s);

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
        if (dim == 3 && countR > 2 || countB > 2 || countG > 2 || countY > 0) {
            throw new InputMismatchException("IOHandler:: Wrong GameBoard input");
        } else if (dim == 5 && countR > 4 || countB > 4 || countG > 4 || countY > 4) {
            throw new InputMismatchException("IOHandler:: Wrong GameBoard input");
        }
        return board;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public boolean getVerbose() {
        return this.withOpenList;
    }

    public String[][] getInitialGameState() {
        return this.initialBoard;
    }

    public String[][] getGoalGameState() {
        return this.goalBoard;
    }

    public void writeResults() {
        System.out.println("Implement Writing results to output.txt file!");
    }

    public boolean write(String content) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(this.outputFilePath));

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
