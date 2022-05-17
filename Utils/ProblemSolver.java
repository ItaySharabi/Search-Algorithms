package Utils;

import API.Algorithm;
import API.HeuristicEval;
import API.IProblem;
import Algorithms.*;
import Heuristics.ManhattanDistance;

/**
 * A simple demonstration class
 * */
public class ProblemSolver {

    private ProblemSolver() {}

    public static String solve(IProblem p, String algorithmName, boolean verbose) {
        Algorithm algo;
        HeuristicEval heuristicEval = new ManhattanDistance(p.getGoal());
        if (algorithmName.equals("Algorithms.BFS")) {
            algo = new BFS(p, verbose);
        }
        else if(algorithmName.equals("Algorithms.DFID")) {
            algo = new DFID(p, verbose);

        }
        else if(algorithmName.equals("A*") || algorithmName.equals("Algorithms.AStar")) {
            algo = new AStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equals("IDA*") || algorithmName.equals("Algorithms.IDAStar")) {
            algo = new IDAStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equalsIgnoreCase("Algorithms.DFBnB")) {
            algo = new DFBnB(p, verbose, heuristicEval);

        }
        else {
            System.out.println("Invalid algorithm name!");
            return "";
        }

        return algo.execute();
    }
}
