package BestVersion;

import java.util.Hashtable;
import java.util.Stack;

public class IDAStar extends Algorithm{
    private Stack<Node> L;
    private Hashtable<State, Node> H;
    private HeuristicEval heuristics;
    private int minF;
    private int t;
    private IProblem p;
    private State start, goal;
    private final boolean OUT_OF_THE_STACK = false;


    public IDAStar(IProblem p, boolean verbose, HeuristicEval heuristic) {
        super(p, verbose);
        this.p = p;
        this.start = p.getStart();
        this.goal = p.getGoal();
        this.heuristics = heuristic;
        this.t = heuristic.heuristicVal(start);
        L = new Stack<>();
        H = new Hashtable<>();
    }

    @Override
    public String execute() {
        int _f;
        Node curr;
        while (t != Integer.MAX_VALUE) {
            minF = Integer.MAX_VALUE;
            curr = new Node(start);
            curr.setTag(OUT_OF_THE_STACK);
            L.add(curr);
            H.put(start, curr);
            while (!L.isEmpty()) {
                Node n = L.pop();
                if (withOpen()) {
                    System.out.println(n);
                }
                if (n.isOut()) {
                    H.remove(n.getState());
                } else {
                    n.markAsOut();
                    L.add(n);

                    for (Operator operator : Operator.allowedOperators(n)) {
                        State child = operator.apply(n);
                            _f = heuristics.heuristicVal(child);
                            if (_f > t) {
                                minF = Math.min(minF, _f);
                                continue;
                            }
                            if (H.contains(child) && H.get(child).isOut()) {
                                continue;
                            }

                            if (H.contains(child) && !H.get(child).isOut()) {
                                if (heuristics.
                                        f(H.get(child)) >
                                        heuristics.heuristicVal(child) + child.getOperatedMarble().getCost()) {
                                    L.remove(H.get(child));
                                    H.remove(child);
                                } else {
                                    continue;
                                }
                            }
                            Node next = new Node(n, child);
                            if (isGoal(child)) {
                                return output(path(next), next.getWeight());
                            }
                            L.add(next);
                            H.put(child, next);
                        }
                    }
                }
            t = minF;
        }
        return output(path(null), 0);
    }
}
