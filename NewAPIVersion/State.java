package NewAPIVersion;

public class State implements IState {

    private IState parent;
    private char[][] board;
    private int dim;
    private int depth;
    private double weight;

    public State(char[][] b) {
        dim = b.length;
        board = new char[dim][dim];
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                board[i][j] = b[i][j];
            }
        }
    }

    @Override
    public IState getParent() {
        return null;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
