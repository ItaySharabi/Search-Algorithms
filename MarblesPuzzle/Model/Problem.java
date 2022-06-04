package MarblesPuzzle.Model;

import API.Algorithm;
import API.HeuristicEval;
import API.IProblem;
import API.IState;
import Algorithms.*;
import MarblesPuzzle.Heuristics.ManhattanDistance;

public class Problem implements IProblem {
    private final IState start, goal;

    public Problem(IState s, IState g) {
        start = s;
        goal = g;
    }

    @Override
    public IState getInitialState() {
        return start;
    }

    @Override
    public IState getGoalState() {
        return goal;
    }

    @Override
    public String solve(String algoName, boolean verbose) {
        Algorithm algo;
        HeuristicEval heuristicEval = new ManhattanDistance(getGoalState());
        if (algoName.equalsIgnoreCase("BFS")) {
            algo = new BFS(this, verbose);
        }
        else if(algoName.equalsIgnoreCase("DFID")) {
            algo = new DFID(this, verbose);

        }
        else if(algoName.equalsIgnoreCase("A*") || algoName.equalsIgnoreCase("AStar")) {
            algo = new AStar(this, verbose, heuristicEval);

        }
        else if(algoName.equalsIgnoreCase("IDA*") || algoName.equalsIgnoreCase("IDAStar")) {
            algo = new IDAStar(this, verbose, heuristicEval);

        }
        else if(algoName.equalsIgnoreCase("DFBnB")) {
            algo = new DFBnB(this, verbose, heuristicEval);

        }
        else {
            System.out.println("Invalid algorithm name!");
            return "";
        }

        return algo.execute();
    }

    @Override
    public String toString() {
        return "\n`Arrange The Marbles`:\n" +
                "Let's try to find a solution for this game!\n" +
                "===========================================\n\n\n" +
                "Initial board state:\n" + start +
                "\nGoal board state:\n" + goal;
    }
}