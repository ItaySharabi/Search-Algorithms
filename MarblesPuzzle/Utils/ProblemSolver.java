package MarblesPuzzle.Utils;

import API.Algorithm;
import API.HeuristicEval;
import API.IProblem;
import Algorithms.*;
import MarblesPuzzle.Heuristics.ManhattanDistance;
import MarblesPuzzle.Model.State;

/**
 * A simple demonstration class
 * */
public class ProblemSolver {

    private ProblemSolver() {}

    public static String solve(IProblem p, String algorithmName, boolean verbose) {
        Algorithm algo;
        HeuristicEval heuristicEval = new ManhattanDistance(p.getGoalState());
        if (algorithmName.equalsIgnoreCase("BFS")) {
            algo = new BFS(p, verbose);
        }
        else if(algorithmName.equalsIgnoreCase("DFID")) {
            algo = new DFID(p, verbose);

        }
        else if(algorithmName.equalsIgnoreCase("A*") || algorithmName.equalsIgnoreCase("AStar")) {
            algo = new AStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equalsIgnoreCase("IDA*") || algorithmName.equalsIgnoreCase("IDAStar")) {
            algo = new IDAStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equalsIgnoreCase("DFBnB")) {
            algo = new DFBnB(p, verbose, heuristicEval);

        }
        else {
            System.out.println("Invalid algorithm name!");
            return "";
        }

        return algo.execute();
    }
}
