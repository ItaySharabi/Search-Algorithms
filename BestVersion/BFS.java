package BestVersion;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends Algorithm {

    private final Hashtable<Board, Node> frontier;
    private final Hashtable<Board, Node> exploredSet;

    public BFS() {
        this.name = "BFS";
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
    }

    @Override
    public String execute(Board start, List<Board> goals, boolean withOpen) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(start));
        Node curr;
//        Board g;
        int debug = 0;

        while (!queue.isEmpty()) {

            curr = queue.poll();

            // Do exploring...
            frontier.remove(curr.getState());
            exploredSet.put(curr.getState(), curr);
            System.out.println("Curr: " + curr);
            // Apply allowed operators on any movable marble from current board state:
            System.out.println("Allowed Operators(n): ");
            System.out.println(Operator.allowedOperators(curr));
            for (Operator operator : Operator.allowedOperators(curr)) {
                Node next = operator.apply(curr);
                System.out.println("New Node: " + next);
//                if (null == next) {continue;}

                if (!(frontier.containsKey(next.getState()) || exploredSet.containsKey(next.getState()))) {
                    if (goals.contains(next.getState())) {
//                            setPrev(g, curr);
                        return output(path(next), next.getWeight());
                    }
                    else {
//                            setPrev(g, curr);
                        frontier.put(next.getState(), next);
                        queue.add(next);
                    }
                }
            }
        }
        return "no path";
    }
}
