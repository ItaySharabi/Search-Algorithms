package BestVersion;

import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class AStar extends Algorithm {
    private HeuristicEval heuristics;
    private PriorityQueue<Node> PQ;
    private Hashtable<State, Node> frontier;
    private Hashtable<State, Node> exploredSet;

    public AStar(IProblem problem, boolean verbose, HeuristicEval h) {
        super(problem, verbose);
        PQ = new PriorityQueue<>(h);
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
    }

    @Override
    public String execute() {
        Node n = new Node(getStart());
        State g;
        PQ.add(n);
        frontier.put(getStart(), n);

        while (!PQ.isEmpty()) {

            n = PQ.poll();
            if(withOpen()) {System.out.println("Node #" + n.getKey()); }
            frontier.remove(n.getState());
//            System.out.println("=====================================================");
            System.out.println("Current: ");
            System.out.println(n);
//            System.out.println("Parent: " + n.getParent());

            // Explore current node `n`:
            exploredSet.put(n.getState(), n);
            for (Operator operator : Operator.allowedOperators(n)){
                g = operator.apply(n);
                System.out.println("New State generated:\n" + g);
                Node next = new Node(n, g);
                if (isGoal(g)) {
                    System.out.println("Goal found: ");
                    System.out.println(g);
                    return output(path(next), next.getWeight());
//                endTime = System.currentTimeMillis();
//                totalTime = (endTime - startTime) * 1.0 / 1000;
//                foundSolution = true;
//                finish(n,totalTime);
                }

                if (!exploredSet.containsKey(g) && !frontier.contains(g)) {
//                    System.out.println("Not in frontier nor in exploredSet");
                    PQ.add(next);
                    frontier.put(g, next);
                } else if (frontier.contains(g) && frontier.get(g) != null) {
//                    System.out.println("========================================");
//                    System.out.println("State found in the frontier!\n" + g);
//                    System.out.println("========================================");
                    if (frontier.get(g).getWeight() > next.getWeight()) {
                        PQ.remove(frontier.put(g, next));
                        PQ.add(next);
                    }
                }
            }
//            System.out.println("=====================================================");
        }
        return null;
    }
}
