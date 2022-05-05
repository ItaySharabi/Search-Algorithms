package BestVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int dim;
    private String[][] board;
    private final List<Marble> movableMarbles;
    private Direction operatedMarbleDirection;
    private Marble operatedMarble;
    static int boardCount = 0;
    private int hashCode;
//    private int hashCode;

    public Board(String[][] board) {
        boardCount++;
        movableMarbles = new ArrayList<>();
        setGameBoard(board);
        setIdentifier();
    }

    private void setIdentifier() {
        int count = 0, p = 0;
        String[][] b = getBoard();
//        int[] A = new int[dim*dim];
        hashCode = 0;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (b[i][j].equals("R")) {
                    hashCode += Ex1.primes[dim-1];
                } else if (b[i][j].equals("B")) {
                    hashCode += Ex1.primes[i];
                } else if (b[i][j].equals("G")) {
                    hashCode += Ex1.primes[j];
                } else if (b[i][j].equals("Y")) {
                    hashCode += Ex1.primes[(i + j) % (dim*dim)];
                } else {
                    hashCode += Ex1.primes[0];
                }
                hashCode *= (int)(movableMarbles.size()/2);
//                hashCode += (A[count++] * (int)Math.pow(10, p++));
            }
        }
    }

    public Board(Board b, Pair p) {
//        this(b.getBoard());
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
        operatedMarble = new Marble(t.getTag(), i, j);
        operatedMarbleDirection = p.getDirection();
        setGameBoard(newBoard);
        setIdentifier();
    }

    public Marble getOperatedMarble() {
        return operatedMarble;
    }

    public Direction getOperatedMarbleDirection() {
        return operatedMarbleDirection;
    }

    public int size() {
        return dim;
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

    public boolean movableMarble(int i, int j) {
        for (Direction d : Direction.values()) {
            if (movableMarble(i, j, d)) {return true;}
        }
        return false;
    }

    private void setGameBoard(String[][] gameBoard) {
        dim = gameBoard.length;
        String[][] t = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(gameBoard[i], 0, t[i], 0, dim);
        }
        board = t;
        Marble m;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (!board[i][j].equals("_")) {
                        if (movableMarble(i, j)) {
                            m = new Marble(board[i][j], i, j);
                            movableMarbles.add(m);
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return hashCode() == board1.hashCode();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Board b1 = (Board) o;
//        if (dim != b1.size()) {return false;}
//        for (int i = 0; i < dim; ++i) {
//            for (int j = 0; j < dim; ++j) {
//                if (!board[i][j].equals(b1.board[i][j])) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Board (").append(")\n");
        for(String[] s : this.board) {
            out.append(Arrays.toString(s));
            out.append("\n");
        }
        return out.toString();
    }
}
