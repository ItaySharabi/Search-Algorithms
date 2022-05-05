package BestVersion;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {

    private final Hashtable<State, Node> frontier;
    private final Hashtable<State, Node> exploredSet;
//    private int nodesExpanded = 0;


    public BFS(IProblem p, boolean verbose) {
        super(p, verbose);
        this.name = "BFS";
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
    }

    @Override
    public String execute() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(getStart()));
        Node curr;

        while (!queue.isEmpty()) {
            curr = queue.poll();
            // Do exploring...
            if (withOpen()) {
                System.out.println(queue);
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
                    }
                    else {
                        frontier.put(g, next);
                        queue.add(next);
                    }
                }
            }
        }
        return "no path";
    }
}
