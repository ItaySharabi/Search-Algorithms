package BestVersion;

import java.util.List;
import java.util.stream.Collectors;

public class ManhattanDistance extends HeuristicEval {

    private State goalState;
    private String[][] g;
    private List<Marble> goalMarbles;
    private final int dim;
    private final int R = 1;
    private final int B = 2;
    private final int G = 10;
    private final int Y = 1;

    public ManhattanDistance(State goal) {
        super(goal);
        goalState = goal;
        g = goal.getBoard();
        goalMarbles = goalState.getMarbles();
        dim = g.length;
    }

    @Override
    public int heuristicVal(Node n) {
        int h = 0;
        String[][] b = n.getState().getBoard();
        boolean[][] visited = new boolean[dim][dim];

        List<Marble> misplacedMarbles
                = n.getState().getMarbles()
                .stream()
                .filter(x ->
                        !g[x.getI()][x.getJ()].equals(x.getTag()))
                .collect(Collectors.toList());

        List<Marble> matches; // Match goal marbles of the same color, for each marble.
        for (Marble mC : misplacedMarbles) {
            matches = goalMarbles
                    .stream()
                    .filter(x -> x.getTag().equals(mC.getTag()) // Same color
                            && !b[x.getI()][x.getJ()].equals(g[x.getI()][x.getJ()]))
                    .collect(Collectors.toList());

            int dx, dy, gridDistance = 0;
            int i, j;
            for (Marble mG : matches) {
                i = mG.getI();
                j = mG.getJ();
                if (visited[i][j]) {/*System.out.println("Oops.. taken marble!");*/continue;}
                visited[i][j] = true;
                dx = Math.abs(mC.getI() - i);
                dy = Math.abs(mC.getJ() - j);
                gridDistance = (dx + dy) * possibleMovementDirections(b, mC.getI(), mC.getJ());
                break;
            }
            h += gridDistance;
        }
        return h;
    }


    private int possibleMovementDirections(String[][] b, int i, int j) {
        if (b[i][j].equals("_")) {return 0;}
        int ans = 0;
        if (i > 0 && b[i-1][j].equals("_")) {ans++;}
        if (i < dim-1 && b[i+1][j].equals("_")) {ans++;}
        if (j > 0 && b[i][j-1].equals("_")) {ans++;}
        if (j < dim-1 && b[i][j+1].equals("_")) {ans++;}
        return ans;
    }

//    @Override
//    public int heuristicVal(Node n) {
//        if (n.getState().equals(goalState)) return 0;
//        hitsMatrix = new boolean[dim][dim];
//        int h = 0;
//        int dx, dy;
//        boolean foundDistance = false;
//        int possible_movements;
//        System.out.println("======================================");
//        System.out.println("Heuristic evaluation of\n" + n);
//        String[][] b = n.getState().getBoard();
//        for (int i = 0; i < dim; ++i) {
//            for (int j = 0; j < dim; ++j) {
//                if (hitsMatrix[i][j]) {continue;}
//                if (b[i][j].equals(goalBoard[i][j])) {
//                    hitsMatrix[i][j] = true;
//                    continue;
//                } else if (b[i][j].equals("_")) { continue;}
//                foundDistance = false;
//                for (int p = 0; p < dim; ++p) {
//                    if (foundDistance) {
//                        System.out.println("found distance... break!");break;}
//                    for (int q = 0; q < dim; ++q) {
//                        if (foundDistance) {
//                            System.out.println("found distance... break!");break;}
//                        if (hitsMatrix[p][q]) {continue;}
//                        if (b[i][j].equals(goalBoard[p][q])) {
//                            hitsMatrix[i][j] = true;
//                            hitsMatrix[p][q] = true;
//                            dx = Math.abs(i - p);
//                            dy = Math.abs(j - q);
//                            // dx + dy is the grid distance to be traveled
//                            System.out.println("b[" + i + "][" + j + "] = " + b[i][j]);
//                            System.out.println("dx = " + dx + "\ndy = " + dy);
//
//                            h += (dx + dy) * possibleMovementDirections(b, i, j);
//                            foundDistance = true;
//                        }
//                    }
//                }
//            }
//        }
////        h += (2 * n.getState().getMovableMarbles().size());
//        System.out.println("Heuristic: h(" + n.getKey() + ") -> " + h);
//        System.out.println("======================================");
//        return h;
//    }




//    @Override
//    public int heuristicVal(Node n) {
//        if (n.getState().equals(goalState)) return 0;
//        int h = 0;
//        System.out.println("======================================");
//        System.out.println("Heuristic evaluation of\n" + n);
//        String[][] givenBoard = n.getState().getBoard();
//        int n_val, target_val, dx, dy, min_dist = 1000;
//        String targetKey;
//
//        for (int i = 0; i < dim; ++i) {
//            for (int j = 0; j < dim; ++j) {
//                hitsMatrix[i][j] = true;
//                if (givenBoard[i][j].equals(goalBoard[i][j])) {continue;}
//                // If given board at (i,j) is empty ("_"), then look for the
//                if (givenBoard[i][j].equals("_")) {targetKey = goalBoard[i][j];}
//                else {targetKey = givenBoard[i][j];}
//
//                for (int p = i; p < dim; ++p) {
//                    for (int q = j; q < dim; ++q) {
//                        if (p == i && q == j) {continue;}
//                        if (goalBoard[p][q].equals(targetKey)) {
//
//
//                            dx = Math.abs(j - q);
//                            dy = Math.abs(i - p);
//                            if (dx + dy < min_dist) {
//                                min_dist = dx + dy;
//                                System.out.println("Found a new minimum goal distance for marble at (" + i + ", " + j + ")");
//                            }
//                        }
//                    }
//                }
//                h += min_dist;
//            }
//        }
//        System.out.println("Node #" + n.getKey() + ": f() -> " + h);
//        System.out.println("======================================");
//        return h;
//    }

}
