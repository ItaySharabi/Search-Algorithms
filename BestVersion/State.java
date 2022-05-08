package BestVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {

    private int dim;
    private String[][] board;
    private final List<Marble> movableMarbles;
    private final List<Marble> marbles;
    private Direction operatedMarbleDirection;
    private Marble operatedMarble;
    private static int boardCount = 0;
//    private int hashCode;

    public State(String[][] board) {
        boardCount++;
        movableMarbles = new ArrayList<>();
        marbles = new ArrayList<>();
        dim = board.length;
        setGameBoard(board);
//        setIdentifier();
    }

    /**
     * This method will set a unique integer identifier for
     * this board, based on some pseudo-random multiplications
     * with prime numbers (So that less boards will have the same multiplication result!).
     * This helps with generating the exact amount of boards during the algorithm's execution time.
     */
//    private void setIdentifier() {
//        String[][] b = getBoard();
//        hashCode = 0;
//        for (int i = 0; i < dim; ++i) {
//            for (int j = 0; j < dim; ++j) {
//                if (b[i][j].equals("R")) {
//                    hashCode += Ex1.primes[dim-1];
//                } else if (b[i][j].equals("B")) {
//                    hashCode += ((int)Math.pow(-1, i) * Ex1.primes[j]);
//                } else if (b[i][j].equals("G")) {
//                    hashCode += Ex1.primes[2*i + j];
//                } else if (b[i][j].equals("Y")) {
//                    hashCode += ((int)Math.pow(-1, j) * Ex1.primes[i]);
//                } else {
//                    hashCode += Ex1.primes[j] ;
//                }
//                hashCode += (movableMarbles.size()*Ex1.primes[i + 2*j]);
//            }
//        }
//    }

    /* BEST HASHING SO FAR */
//    private void setIdentifier() {
//        String[][] b = getBoard();
//        int R = Ex1.primes[4], B = Ex1.primes[7], G = Ex1.primes[5]
//                , Y = Ex1.primes[3], EMPTY = Ex1.primes[0];
//        hashCode = 1;
//        int val = 1;
//        for (int i = 0; i < dim; ++i) {
//            for (int j = 0; j < dim; ++j) {
//                if (b[i][j].equals("R")) {
//                    val += (Math.pow(R, i + j));
//                } else if (b[i][j].equals("B")) {
//                    val += (Math.pow(B, i + j));
//                } else if (b[i][j].equals("G")) {
//                    val += (Math.pow(G, i + j));
//                } else if (b[i][j].equals("Y")) {
//                    val += (Math.pow(Y, i + j));
//                } else {
//                    val += (Math.pow(EMPTY, i + j));
//                }
//                val *= (i+1) * (j+1);
//            }
//            hashCode *= (val);
//        }
//    }

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
        marbles = new ArrayList<>();
        movableMarbles = new ArrayList<>();
        setGameBoard(newBoard);
        operatedMarble = getMarble(i, j);
        operatedMarbleDirection = p.getDirection();
//        setIdentifier();
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

    public List<Marble> getMarbles() {
        return marbles;
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
        for (Direction d : Direction.values()) {
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
                    marbles.add(new Marble(board[i][j], i, j));
                    if (movableMarble(i, j)) {
                        movableMarbles.add(marbles.get(marbles.size()-1));
                    }
                }
            }
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        String[][] other = state1.getBoard();
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (!board[i][j].equals(other[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
//        out.append("State (").append(hashCode()).append(")\n");
        for(String[] s : this.board) {
            out.append(Arrays.toString(s));
            out.append("\n");
        }
        return out.toString();
    }
}
