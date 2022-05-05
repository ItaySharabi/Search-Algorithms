package BestVersion;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {

    private final Hashtable<Board, Node> frontier;
    private final Hashtable<Board, Node> exploredSet;


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
                Board g = operator.apply(curr);
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
