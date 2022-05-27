package MarblesPuzzle.Algorithms;

import MarblesPuzzle.API.Algorithm;
import MarblesPuzzle.API.HeuristicEval;
import MarblesPuzzle.API.IProblem;
import MarblesPuzzle.Model.Node;
import MarblesPuzzle.Model.Operator;
import MarblesPuzzle.Model.State;

import java.util.*;

public class DFBnB extends Algorithm {
    this.name = "DFBnB";
    private final Stack<Node> STK;
    private final Hashtable<State, Node> frontier; // Open-list
    private int t;
    private final Node start;
    private final HeuristicEval heuristics;

    public DFBnB(IProblem p, boolean verbose, HeuristicEval heuristics) {
        super(p, verbose);
        this.start = new Node(p.getStart());
        this.heuristics = heuristics;
        STK = new Stack<>();
        frontier = new Hashtable<>();
        STK.add(start);
        frontier.put(start.getState(), start);
    }

    @Override
    public String execute() {

        String result = null;

        t = Integer.MAX_VALUE;
        timerOn();
        while (!STK.isEmpty()) {

            Node n = STK.pop();
            if (withOpen()) {
                System.out.println(n);
            }
            if (n.isOut()) {
                frontier.remove(n.getState());
            } else {
                n.markAsOut();
                STK.add(n);

                // Apply all allowed operators on node `n`
                List<Node> N = new ArrayList<>();
                for (Operator operator : Operator.allowedOperators(n)) {
                    N.add(new Node(n, operator.apply(n)));
                }
                // Sort the nodes in N according to their `f` heuristic values.
                N.sort(heuristics);
                List<Node> cpyN = new ArrayList<>(N);
                for (Node child : cpyN) {
                    if (heuristics.f(child) >= t) {
                        // Remove all nodes after `child` (including)
                        int thresh_ = N.indexOf(child);
                        N.removeIf(boardState -> thresh_ <= N.indexOf(boardState));

                    } else if (frontier.contains(child) && frontier.get(child.getState()).isOut()) {
                        N.remove(child);
                    } else if (frontier.contains(child) && (!frontier.get(child.getState()).isOut())) {
                        if (heuristics.f(frontier.get(child.getState())) <= heuristics.f(child)) {
                            N.remove(child);
                        } else {
                            STK.remove(frontier.get(child.getState()));
                            frontier.remove(child.getState());
                        }
                    } else if (isGoal(child)) {
                        // If we've reached here -
                        // then f(child) < t
                        t = heuristics.f(child);
                        result = output(path(child), child.getWeight());
                        int thresh_ = N.indexOf(child);
                        N.removeIf(boardState -> thresh_ <= N.indexOf(boardState));
                    }
                    Collections.reverse(N);
                    STK.addAll(N);
                    for (Node c : N) {
                        frontier.put(c.getState(), c);
                    }
                }
            }
        }
        if (null == result) {return output(path(null), Integer.MAX_VALUE);}
        return result;
    }
}
