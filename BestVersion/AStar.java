package BestVersion;

import java.util.List;
import java.util.PriorityQueue;

public class AStar extends Algorithm {
    private HeuristicEval heuristics;
    private PriorityQueue<Node> PQ;

    public AStar(IProblem problem, boolean verbose, HeuristicEval h) {
        super(problem, verbose);
        heuristics = h;
        PQ = new PriorityQueue<>();
    }

    @Override
    public String execute() {
        return null;
    }
}
