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

    public static String solve(IProblem<State> p, String algorithmName, boolean verbose) {
        Algorithm algo;
        HeuristicEval heuristicEval = new ManhattanDistance(p.getGoalState());
        if (algorithmName.equals("BFS")) {
            algo = new BFS(p, verbose);
        }
        else if(algorithmName.equals("DFID")) {
            algo = new DFID(p, verbose);

        }
        else if(algorithmName.equals("A*") || algorithmName.equals("AStar")) {
            algo = new AStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equals("IDA*") || algorithmName.equals("IDAStar")) {
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
