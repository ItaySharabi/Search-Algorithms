package BestVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int dim;
    private String[][] board;
    private final List<Marble> movableMarbles;

    public Board(String[][] board) {
        movableMarbles = new ArrayList<>();
        setGameBoard(board);
    }

//    public Board getPrev() {
//        if (null == operatedMarble || null == operatedMarbleDirection) {return null;}
//
//        Board k = new Board(getBoard());
//        Board prevBoard = Operator.moveTile(k, Operator.opposite(operatedMarbleDirection), operatedMarble);
//        if (null == prevBoard) {return null;}
//        return new Board(prevBoard.getBoard());
//    }

//    public Marble getOperatedMarble() {
//        return operatedMarble;
//    }
//
//    public Direction getOperatedMarbleDirection() {
//        return operatedMarbleDirection;
//    }

//    public Board(String[][] board) {
//        if (! (null == operated || null == direction)) {
//            movableMarbles = new ArrayList<>();
//            setGameBoard(board);
//        }
//    }

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

    public boolean movableTile(Tile t, Direction d) {
        return movableTile(t.getI(), t.getJ(), d);
    }

    public boolean movableTile(int i, int j, Direction d) {
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

    public boolean movableTile(int i, int j) {
        for (Direction d : Direction.values()) {
            if (movableTile(i, j, d)) {return true;}
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
                        if (movableTile(i, j)) {
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
        Board b1 = (Board) o;
        if (dim != b1.size()) {return false;}
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (!board[i][j].equals(b1.board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(String[] s : this.board) {
            out.append(Arrays.toString(s));
            out.append("\n");
        }
        return out.toString();
    }
}
