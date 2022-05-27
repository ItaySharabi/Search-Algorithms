package MarblesPuzzle.Algorithms;

import MarblesPuzzle.API.Algorithm;
import MarblesPuzzle.API.HeuristicEval;
import MarblesPuzzle.API.IProblem;
import MarblesPuzzle.Model.*;

import java.util.Hashtable;
import java.util.Stack;

public class IDAStar extends Algorithm {
    private final Stack<Node> STK;
    private final Hashtable<State, Node> frontier;
    private final HeuristicEval heuristics;
    private int minF;
    private int t;
    private final State start;


    public IDAStar(IProblem p, boolean verbose, HeuristicEval heuristic) {
        super(p, verbose);
        this.start = p.getStart();
        this.heuristics = heuristic;
        this.t = heuristic.h(start);
        STK = new Stack<>();
        frontier = new Hashtable<>();
    }

    @Override
    public String execute() {
        int _f;
        Node curr;
        final boolean OUT_OF_THE_STACK = false;
        t = heuristics.h(start);
        timerOn();
        while (t != Integer.MAX_VALUE) {
            minF = Integer.MAX_VALUE;
            curr = new Node(start);
            curr.setTag(OUT_OF_THE_STACK);
            STK.add(curr);
            frontier.put(start, curr);
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

                    State g;
                    for (Operator operator : Operator.allowedOperators(n)) {
                        g = operator.apply(n);
                            _f =    heuristics
                                    .h(g) // f(x) = h(x.getState()) + g(x)
                                    + g.getOperatedMarble().getCost()
                                    + n.getWeight();
                            if (_f > t) {
                                minF = Math.min(minF, _f);
                                continue;
                            }
                            if (frontier.contains(g) && frontier.get(g).isOut()) {
                                continue;
                            }
                            Node next = new Node(n, g);

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
        return output(path(null), 0);
    }
}
