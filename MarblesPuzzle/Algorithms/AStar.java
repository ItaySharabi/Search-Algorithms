package MarblesPuzzle.Algorithms;

import MarblesPuzzle.API.Algorithm;
import MarblesPuzzle.API.HeuristicEval;
import MarblesPuzzle.API.IProblem;
import MarblesPuzzle.Model.Node;
import MarblesPuzzle.Model.Operator;
import MarblesPuzzle.Model.State;

import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 *  A* algorithm only finds the shortest path from a specified source
 *  to a specified goal, and not the shortest-path tree
 *  from a specified source to all possible goals
 */
public class AStar extends Algorithm {
    this.name = "A*";
    private final PriorityQueue<Node> PQ;
    private final Hashtable<State, Node> frontier;
    private final Hashtable<State, Node> exploredSet;

    public AStar(IProblem problem, boolean verbose, HeuristicEval h) {
        super(problem, verbose);
        PQ = new PriorityQueue<>(h);
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
    }

    @Override
    public String execute() {
        timerOn();
        Node n = new Node(getStart());
        State g;
        PQ.add(n);
        frontier.put(getStart(), n);

        while (!PQ.isEmpty()) {

            n = PQ.poll();
            if(withOpen()) {
                System.out.println(n);
            }
            frontier.remove(n.getState());

            exploredSet.put(n.getState(), n);
            for (Operator operator : Operator.allowedOperators(n)){
                g = operator.apply(n);
                Node next = new Node(n, g);
                if (isGoal(g)) {
                    return output(path(next), next.getWeight());
                }
                if (!exploredSet.containsKey(g) && !frontier.contains(g)) {
                    PQ.add(next);
                    frontier.put(g, next);
                } else if (frontier.contains(g) && frontier.get(g) != null) {
                    if (frontier.get(g).getWeight() > next.getWeight()) {
                        PQ.remove(frontier.put(g, next));
                        PQ.add(next);
                    }
                }
            }
        }
        return output(path(null), -1);
    }
}
