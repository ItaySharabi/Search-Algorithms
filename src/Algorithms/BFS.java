package src.Algorithms;

import src.API.Algorithm;
import src.API.IProblem;
import src.Model.Node;
import src.Model.Operator;
import src.Model.State;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {

    private final Hashtable<State, Node> frontier;
    private final Hashtable<State, Node> exploredSet;
    private final Queue<Node> Q;


    public BFS(IProblem p, boolean verbose) {
        super(p, verbose);
        this.name = "BFS";
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
        Q = new LinkedList<>();
    }

    @Override
    public String execute() {
        timerOn();
        Q.add(new Node(getStart()));
        Node curr;
        while (!Q.isEmpty()) {
            curr = Q.poll();
            // Do exploring...
            if (withOpen()) {
                System.out.println(curr);
            }
            frontier.remove(curr.getState());
            exploredSet.put(curr.getState(), curr);
            // Apply allowed operators on any movable marble from current board state:
            for (Operator operator : Operator.allowedOperators(curr)) {
                State g = operator.apply(curr);

                if (!(frontier.containsKey(g) || exploredSet.containsKey(g))) {
                    Node next = new Node(curr, g);
                    if (isGoal(g)) {
                        return output(path(next), next.getWeight());
                    } else {
                        frontier.put(g, next);
                        Q.add(next);
                    }
                }
            }
        }
        return "no path";
    }
}
