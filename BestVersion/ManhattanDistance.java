package BestVersion;

public class ManhattanDistance extends HeuristicEval {

    private Node goalNode;
    private String[][] board;
    private int dim;
    private final int R = 1;
    private final int B = 2;
    private final int G = 10;
    private final int Y = 1;

    public ManhattanDistance(Node goal) {
        super(goal);
        this.goalNode = goal;
        this.board = goal.getState().getBoard();
    }

    @Override
    public double heuristicVal(Node n) {
        return 0;
    }
}
