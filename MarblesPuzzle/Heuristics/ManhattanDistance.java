package MarblesPuzzle.Heuristics;

import API.HeuristicEval;
import API.IState;
import MarblesPuzzle.Model.State;

import java.util.HashMap;

/**
 * The `Manhattan Distance` Heuristic Evaluation class.
 * `Manhattan Distance` is a "Heuristic Evaluation" method,
 * which evaluates a Node `n` to be "far" from target Node `g`,
 * by summing all "Gridded" distances of misplaced `MarbleButtonView`s.
 * @see HeuristicEval
 */
public class ManhattanDistance extends HeuristicEval {

    private final String[][] g;
    private final int dim;
    private final HashMap<String, Integer> costs;

    public ManhattanDistance(IState goal) {
        super(goal);
        g = ((State)goal).getBoard();
        dim = g.length;
        costs = new HashMap<>();
        costs.put("_", 0);
        costs.put("R", 1);
        costs.put("Y", 1);
        costs.put("B", 2);
        costs.put("G", 10);
    }

    @Override
    public int h(IState s) {
        int h = 0;
        State state = (State) s;
        String[][] n_board = state.getBoard();
        boolean[][] placed = new boolean[dim][dim];
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                placed[i][j] =
                        n_board[i][j].equals(g[i][j]);
            }
        }
        int countMisplaced = 0,
            dist;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (n_board[i][j].equals("_")) { continue; }
                if (!g[i][j].equals(n_board[i][j])) {
                    // If MarbleButtonView[i, j] is not placed.
                    dist = manhattanDist(n_board[i][j], i, j, placed)
                            * costs.get(n_board[i][j]);
                    countMisplaced ++;
                    if (!state.movableMarble(i, j)) {
                        // Punish nodes with +1 distance
                        // if they are misplaced and blocked!
                        dist++;
                    }
                    h += dist;
                }
            }
        }
        /*Return the heuristic value, `h`, of
        * the given State, `s`, with an addition
        * of the misplaced `MarbleButtonView`s count*/
        return h + countMisplaced;
    }

    private int manhattanDist(String KEY, int i, int j, boolean[][] placed) {
        int dist;
        for (int k = 0; k < dim; ++k) {
            for (int l = 0; l < dim; ++l) {
                if (g[k][l].equals(KEY)) {
                    if (placed[k][l]) {continue;}
                    placed[k][l] = true;
                    dist = Math.abs(k-i) + Math.abs(l-j); // The Manhattan Distance from M[i][j] to M[k][l]
                    return dist;
                }
            }
        }
        return 0;
    }
}
