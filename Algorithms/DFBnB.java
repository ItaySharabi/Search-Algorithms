package Algorithms;

import API.*;
import MarblesPuzzle.Model.Operator;

import java.util.*;

public class DFBnB extends Algorithm {
    private final Stack<Node> STK;                  // Stack
    private final Hashtable<IState, Node> frontier; // Open-list
    private final HeuristicEval heuristics;

    public DFBnB(IProblem p, boolean verbose, HeuristicEval heuristics) {
        super(p, verbose);
        this.name = "DFBnB";
        Node start = new Node(p.getInitialState());
        this.heuristics = heuristics;
        STK = new Stack<>();
        frontier = new Hashtable<>();
        STK.add(start);
        frontier.put(start.getState(), start);
    }

    @Override
    public String execute() {

        String result = null;

        // Threshold
        int t = Integer.MAX_VALUE;
        Node n;
        long startTime = System.currentTimeMillis();

        while (!STK.isEmpty()) {

            n = STK.pop();
            print(n);

            if (n.isOut()) {
                frontier.remove(n.getState());
            } else {
                n.markAsOut();
                STK.add(n);

                // Apply all allowed operators on node `n`
                List<Node> N = new ArrayList<>();
                for (IOperator operator : Operator.allowedOperators(n)) {
                    N.add(new Node(n, operator.apply()));
                }
                // Sort the nodes in N according to their `f` heuristic values.
                N.sort(heuristics);
                List<Node> cpyN = new ArrayList<>(N);
                for (Node child : cpyN) {
                    if (heuristics.f(child) >= t) {
                        // Remove all nodes after `child` (including)
                        int thresh_ = N.indexOf(child);
                        N.removeIf(boardState -> thresh_ <= N.indexOf(boardState));

                    } else if (frontier.contains(child.getState()) && frontier.get(child.getState()).isOut()) {
                        N.remove(child);
                    } else if (frontier.contains(child.getState()) && (!frontier.get(child.getState()).isOut())) {
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
                        result = output(path(child), child.getWeight(), startTime);
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
        if (null == result) {return output(path(null), -1, startTime);}
        return result;
    }
}