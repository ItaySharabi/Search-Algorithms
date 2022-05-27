package Algorithms;

import API.Algorithm;
import API.HeuristicEval;
import API.IProblem;
import MarblesPuzzle.Model.*;

import java.util.Hashtable;
import java.util.Stack;

public class IDAStar extends Algorithm {
    private final Stack<Node<State>> STK;                  // Stack
    private final Hashtable<State, Node<State>> frontier;  // Open-list
    private final HeuristicEval heuristics;                // Heuristic evaluation object
    private int minF;                                      // Minimum heuristic val threshold
    private int t;                                         // Threshold
    private final State start;


    public IDAStar(IProblem<State> p, boolean verbose, HeuristicEval heuristic) {
        super(p, verbose);
        this.name = "IDA*";
        this.start = p.getInitialState();
        this.heuristics = heuristic;
        this.t = heuristic.h(start);
        STK = new Stack<>();
        frontier = new Hashtable<>();
    }

    @Override
    public String execute() {
        int _f;
        Node<State> curr;
        final boolean OUT_OF_THE_STACK = false;
        t = heuristics.h(start);
        timerOn();
        while (t != Integer.MAX_VALUE) {
            minF = Integer.MAX_VALUE;
            curr = new Node<>(start);
            curr.setTag(OUT_OF_THE_STACK);
            STK.add(curr);
            frontier.put(start, curr);

            while (!STK.isEmpty()) {
                Node<State> n = STK.pop();
                if (withOpen()) {
                    System.out.println(n);
                }
                if (n.isOut()) {
                    frontier.remove(n.getState());
                } else {
                    n.markAsOut();
                    STK.add(n);

                    State g;
                    for (Operator operator : Operator.allowedOperators(n)) {
                        g = operator.apply(n);
                            _f =  // f(x) = g(x) + h(x.getState())
                                    heuristics
                                    .h(g)
                                    + g.getOperatedMarble().getCost()
                                    + n.getWeight();
                            if (_f > t) {
                                minF = Math.min(minF, _f);
                                continue;
                            }
                            if (frontier.contains(g) && frontier.get(g).isOut()) {
                                continue;
                            }
                            Node<State> next = new Node<>(n, g);

                            if (frontier.contains(g) && !frontier.get(g).isOut()) {
                                if (heuristics.
                                        f(frontier.get(g)) >
                                        heuristics.f(next)) {
                                    STK.remove(frontier.get(g));
                                    frontier.remove(g);
                                } else {
                                    continue;
                                }
                            }
                            if (isGoal(g)) {
                                return output(path(next), next.getWeight());
                            }
                            STK.add(next);
                            frontier.put(g, next);
                        }
                    }
                }
            t = minF;
        }
        return output(path(null), -1);
    }
}
