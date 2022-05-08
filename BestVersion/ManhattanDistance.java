package BestVersion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ManhattanDistance extends HeuristicEval {

    private State goalState;
    private final String[][] g;
    private final int dim;
    private HashMap<String, Integer> costs;

    public ManhattanDistance(State goal) {
        super(goal);
        goalState = goal;
        g = goal.getBoard();
        dim = g.length;
        costs = new HashMap<>();
        costs.put("_", 0);
        costs.put("R", 1);
        costs.put("B", 2);
        costs.put("G", 10);
        costs.put("Y", 1);
    }

    @Override
    public int heuristicVal(State s) {

        int h = 0;
        String[][] n_board = s.getBoard();
        boolean[][] placed = new boolean[dim][dim];
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                placed[i][j] = n_board[i][j].equals(g[i][j]) && !n_board[i][j].equals("_");
            }
        }
        int totalMvCost = 0, count = 0;
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                if (placed[i][j]) {continue;}
                if (!n_board[i][j].equals("_")) {
                    h += manhattanDist(n_board[i][j], i, j, n_board, placed);
                    count++;
                    totalMvCost += costs.get(n_board[i][j]);
                }
            }
        }
        return h * totalMvCost; // * totalMvCost/count;
    }

    private int manhattanDist(String KEY, int i, int j, String[][] board, boolean[][] placed) {
        if (placed[i][j]) {return 0;}
        int dist, avgCost = 0;
        int minDist = 10;
        int countHits = 0, mvOpts = 0;
//        System.out.println("=====KEY: " + KEY + "=====(" + i + ", " + j + ")==================");
        for (int k = 0; k < dim; ++k) {
            for (int l = 0; l < dim; ++l) {
                if (placed[k][l] || g[k][l].equals("_")) {continue;}

                if (g[k][l].equals(KEY)) {
                    if (k == i && j == l) {
                        continue;
                    }
                    dist = Math.abs(k-i) + Math.abs(l-j); // Mult by a factor of `D` maybe ?

                    // Save the larger distance possible for a marble to move.
                    if (dist < minDist) {
                        minDist = dist;
                    }
                    if (++countHits >= 2) {
//                        System.out.println(countHits + " Targets were found! Best: " + maxDist);
                        placed[i][j] = true;
                        if (i>0 && !board[i-1][j].equals("_")) {
                            mvOpts++;
                            avgCost += costs.get(board[i-1][j]);
                        }
                        if (i<dim-1 && !board[i+1][j].equals("_")) {
                            mvOpts++;
                            avgCost += costs.get(board[i+1][j]);
                        }
                        if (j>0 && !board[i][j-1].equals("_")) {
                            mvOpts++;
                            avgCost += costs.get(board[i][j-1]);
                        }
                        if (j<dim-1 && !board[i][j+1].equals("_")) {
                            mvOpts++;
                            avgCost += costs.get(board[i][j+1]);
                        }

                        return minDist * (mvOpts) * (avgCost/(5-mvOpts));
                    }
                }
            }
        }
        return minDist;
    }


//    @Override
//    public int heuristicVal(State s) {
//        int h = 0;
//        String[][] b = s.getBoard();
//        boolean[][] visited = new boolean[dim][dim];
//
//        List<Marble> misplacedMarbles
//                = s.getMarbles()
//                .stream()
//                .filter(x ->
//                        !g[x.getI()][x.getJ()].equals(x.getTag()))
//                .collect(Collectors.toList());
//
//        List<Marble> matches; // Match goal marbles of the same color, for each marble.
//        for (Marble mC : misplacedMarbles) {
//
//            matches = goalMarbles
//                    .stream()
//                    .filter(x -> x.getTag().equals(mC.getTag()) // Same color
//                            && !b[x.getI()][x.getJ()].equals(g[x.getI()][x.getJ()]))
//                    .collect(Collectors.toList());
//
//            int dx, dy, gridDistance;
//            int i, j;
//            for (Marble mG : matches) {
//
//                i = mG.getI();
//                j = mG.getJ();
//                if (!visited[i][j]) {
//                    visited[i][j] = true;
//                    dx = Math.abs(mC.getI() - i);
//                    dy = Math.abs(mC.getJ() - j);
//                    gridDistance = (dx + dy); //  * dim;/*possibleMovementDirections(b, mC.getI(), mC.getJ());*/
////                System.out.println("ManhattanDistance(" + mC + ") = " + gridDistance);
//                    h += gridDistance*dim; // + 1
//                    break;
//                }
//            }
//        }
////        System.out.println("marbles of (#" + n.getKey() + ") = " + misplacedMarbles);
////        System.out.println("h(#" + n.getKey() + ") = " + h + (2*misplacedMarbles.size()));
//
////        System.out.println("======================================================");
//        return h;
//    }
}
