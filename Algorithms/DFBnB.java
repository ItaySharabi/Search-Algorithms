package Algorithms;

import API.Algorithm;
import API.HeuristicEval;
import API.IProblem;
import API.Node;
import MarblesPuzzle.Model.Operator;
import MarblesPuzzle.Model.State;

import java.util.*;

public class DFBnB extends Algorithm {
    private final Stack<Node<State>> STK;                 // Stack
    private final Hashtable<State, Node<State>> frontier; // Open-list
    private int t;                                        // Threshold
    private final Node<State> start;
    private final HeuristicEval heuristics;

    public DFBnB(IProblem<State> p, boolean verbose, HeuristicEval heuristics) {
        super(p, verbose);
        this.name = "DFBnB";
        this.start = new Node<>(p.getInitialState());
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
        Node<State> n;
        timerOn();
        while (!STK.isEmpty()) {

            n = STK.pop();
            if (withOpen()) {
                System.out.println(n);
            }
            if (n.isOut()) {
                frontier.remove(n.getState());
            } else {
                n.markAsOut();
                STK.add(n);

                // Apply all allowed operators on node `n`
                List<Node<State>> N = new ArrayList<>();
                for (Operator operator : Operator.allowedOperators(n)) {
                    N.add(new Node<>(n, operator.apply(n)));
                }
                // Sort the nodes in N according to their `f` heuristic values.
                N.sort(heuristics);
                List<Node<State>> cpyN = new ArrayList<>(N);
                for (Node<State> child : cpyN) {
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
                    for (Node<State> c : N) {
                        frontier.put(c.getState(), c);
                    }
                }
            }
        }
        if (null == result) {return output(path(null), Integer.MAX_VALUE);}
        return result;
    }
}
