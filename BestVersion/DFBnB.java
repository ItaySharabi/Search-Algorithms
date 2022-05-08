package BestVersion;

import java.util.*;

public class DFBnB extends Algorithm {
    private Stack<Node> L;           // openList
    private Hashtable<State, Node> H;// closedList
    private int t;                   //
    private Node start;
    private State goal;
    private HeuristicEval heuristics;
    private int SEARCH_OPTIMUM;

    public DFBnB(IProblem p, boolean verbose, HeuristicEval heuristics) {
        super(p, verbose);
        this.start = new Node(p.getStart());
        this.goal = p.getGoal();
        this.heuristics = heuristics;
        int dim = goal.size();
        SEARCH_OPTIMUM = 0;
        int f_start = heuristics.f(start);
        SEARCH_OPTIMUM = dim*dim*f_start*10;
        t = Integer.MAX_VALUE;
        System.out.println("SEARCH_OPTIMUM: " + SEARCH_OPTIMUM);
    }

    @Override
    public String execute() {
        Node resultNode = null;

        L = new Stack<>();
        H = new Hashtable<>();
        L.add(start);
        H.put(start.getState(), start);

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

                // Apply all allowed operators on node `n`
                List<Node> N = new ArrayList<>();
                for (Operator operator : Operator.allowedOperators(n)) {
                    N.add(new Node(n, operator.apply(n)));
                }
                // Sort the nodes in N according to their `f` heuristic values.
                N.sort(heuristics);
                List<Node> cpyN = new ArrayList<>(N);
//                System.out.println("All states were copied!\n" + cpyN);
                for (Node child : cpyN) {
                    if (heuristics.f(child) >= t) {
                        //delete everyone after him. include him
                        for (Node c : cpyN) {
                            if (heuristics.f(c) >= t) {
                                N.remove(c);
                            }
                        }
                    } else if (H.contains(child) && H.get(child.getState()).isOut()) {
                        N.remove(child);
                    } else if (H.contains(child) && (!H.get(child.getState()).isOut())) {
                        if (heuristics.f(H.get(child.getState())) <= heuristics.f(child)) {
                            N.remove(child);
                        } else {
                            L.remove(H.get(child.getState()));
                            H.remove(child.getState());
                        }
                    } else if (isGoal(child)) {
                        t = heuristics.f(child);
                        System.out.println();
                        resultNode = child;
                        if (t < SEARCH_OPTIMUM) {
                            System.out.println("Found a good enough target!");
                            System.out.println(resultNode);
                            return output(path(resultNode), resultNode.getWeight());
                        }
                        System.out.println("Found a solution, keep looking until: " + t + " < " + SEARCH_OPTIMUM);
                        N.removeIf(boardState -> N.indexOf(child) < N.indexOf(boardState));
                        N.remove(child);
                    }
                    Collections.reverse(N);
                    L.addAll(N);
                    for (Node c : N) {
                        H.put(c.getState(), c);
                    }
                }
            }
        }
        if (null == resultNode) {return output(path(null), Integer.MAX_VALUE);}
        return output(path(resultNode), resultNode.getWeight());
    }
}
