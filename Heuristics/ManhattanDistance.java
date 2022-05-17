package Heuristics;

import API.HeuristicEval;
import Model.State;

import java.util.HashMap;

/**
 * The `Manhattan Distance` Heuristic Evaluation class.
 * `Manhattan Distance` is a "Heuristic Evaluation" method,
 * which evaluates a Model.Node `n` to be "far" from target Model.Node `g`,
 * by summing all "Gridded" distances of misplaced `Model.Marble`s.
 * @see HeuristicEval
 */
public class ManhattanDistance extends HeuristicEval {

    private final String[][] g;
    private final int dim;
    private final HashMap<String, Integer> costs;

    public ManhattanDistance(State goal) {
        super(goal);
        g = goal.getBoard();
        dim = g.length;
        costs = new HashMap<>();
        costs.put("_", 0);
        costs.put("R", 1);
        costs.put("Y", 1);
        costs.put("B", 2);
        costs.put("G", 10);
    }

    @Override
    public int h(State s) {
        int h = 0;
        String[][] n_board = s.getBoard();
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
                    // If Model.Marble[i, j] is not placed.
                    dist = manhattanDist(n_board[i][j], i, j, placed)
                            * costs.get(n_board[i][j]);
                    countMisplaced ++;
                    if (!s.movableMarble(i, j)) {
                        // Punish nodes with +1 distance
                        // if they are misplaced and blocked!
                        dist++;
                    }
                    h += dist;
                }
            }
        }
        /*Return the heuristic value, `h`, of
        * the given Model.State, `s`, with an addition
        * of the misplaced `Model.Marble`s count*/
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