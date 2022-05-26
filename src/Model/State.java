package src.Model;

import src.API.Algorithm;
import src.API.IProblem;
import src.Utils.Direction;
import src.Utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static src.Utils.Direction.*;

/**
 * State class.
 *
 * A `State` is a representation of the Problem `p` in time `t`.
 * `State`s in this Project are represented by a matrix of strings.
 * It contains some more information like a `List` of `Marble`s
 * for the convenience of implementing this solution.
 *
 * it's weight (`Node`s gain weight only when being applied an `Operator`.
 * @see Node
 * @see Marble
 * @see Algorithm
 * @see IProblem
 */
public class State {

    private int dim;
    private String[][] board;
    private final List<Marble> movableMarbles;
    private Direction operatedMarbleDirection;
    private Marble operatedMarble;
    private static int boardCount = 0;
    private int hashCode;

    public State(String[][] board) {
        boardCount++;
        movableMarbles = new ArrayList<>();
//        marbles = new ArrayList<>();
        dim = board.length;
        setGameBoard(board);
        setIdentifier();
    }

    /**
     * This method will set a unique integer identifier for
     * this board, based on some pseudo-random multiplications
     * with prime numbers (So that less boards will have the same multiplication result!).
     * This helps with generating the exact amount of boards during the algorithm's execution time.
     */
    private void setIdentifier() {
        String h = Arrays.deepToString(board);
        hashCode = h.hashCode()
                /*And to ensure uniqueness:*/ * 61 * 31; // Prime numbers (7'th and 14'th).
    }

    public State(State b, Pair p) {
        boardCount++;
        Marble t = p.getMarble();
        Direction d = p.getDirection();
        int i = t.getI(), j = t.getJ();

        // Calculate the new index in which the moved marble is at
        switch (d) {
            case UP:
                i -= 1;
                break;
            case DOWN:
                i += 1;
                break;
            case LEFT:
                j -= 1;
                break;
            case RIGHT:
                j += 1;
                break;
        }
        String[][] newBoard = b.getBoard();
        newBoard[i][j] = t.getTag();
        newBoard[t.getI()][t.getJ()] = "_";
        movableMarbles = new ArrayList<>();
        setGameBoard(newBoard);
        operatedMarble = getMarble(i, j);
        operatedMarbleDirection = p.getDirection();
        setIdentifier();
    }

    public Marble getMarble(int i, int j) {
        for (Marble m : movableMarbles) {
            if (m.getJ() == j && m.getI() == i) {
                return m;
            }
        }
        return null;
    }

    public Marble getOperatedMarble() {
        return operatedMarble;
    }

    public Direction getOperatedMarbleDirection() {
        return operatedMarbleDirection;
    }

    public String[][] getBoard() {
        String[][] t = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(board[i], 0, t[i], 0, dim);
        }
        return t;
    }

    public List<Marble> getMovableMarbles() {
        return movableMarbles;
    }

    public boolean movableMarble(Marble t, Direction d) {
        return movableMarble(t.getI(), t.getJ(), d);
    }

    public boolean movableMarble(int i, int j, Direction d) {
        if (i < 0 || i >= dim || j < 0 || j >= dim) {
            return false;
        }
        if (board[i][j].equals("_")) {return false;}

        switch (d) {
            case UP:
                return i > 0 && board[i-1][j].equals("_");
            case LEFT:
                return j > 0 && board[i][j-1].equals("_");
            case RIGHT:
                return j < dim-1 && board[i][j+1].equals("_");
            case DOWN:
                return i < dim-1 && board[i+1][j].equals("_");
        }
        return false;
    }

    public static int getBoardCount() {
        return boardCount;
    }

    public boolean movableMarble(int i, int j) {
        for (Direction d : values()) {
            if (movableMarble(i, j, d)) {return true;}
        }
        return false;
    }

    private void setGameBoard(String[][] gameBoard) {
        dim = gameBoard.length;
        board = new String[dim][dim];
        for (int i = 0; i < dim; ++i) {
            System.arraycopy(gameBoard[i], 0, board[i], 0, dim);
        }
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (!board[i][j].equals("_")) {
                    if (movableMarble(i, j)) {
                        movableMarbles.add(new Marble(board[i][j], i, j));
                    }
                }
            }
        }
    }


    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return hashCode() == o.hashCode();
    }

    public int size() {return board.length;}

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                out.append(board[i][j]).append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
