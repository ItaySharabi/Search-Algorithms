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
        dim = board.length;
    }

    @Override
    public int heuristicVal(Node n) {

        int h = 0;
        for(int i=0;i<dim;++i){
            for(int j=0;j<dim;++j){
                int value = boardState.getBoard()[i][j];
                if(value==0)
                    continue;
                int cRow = value/columns;
                int cColumn = value%columns;
                if(cColumn==0){
                    cColumn = columns-1;
                    cRow--;
                }else{
                    cColumn--;
                }
                h+=(Math.abs(i-cRow)+Math.abs(j-cColumn))*boardState.getPriceOfValue(value);
            }
        }
        return h;
    }
}
