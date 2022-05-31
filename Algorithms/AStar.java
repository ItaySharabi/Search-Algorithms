package Algorithms;

import API.*;
import MarblesPuzzle.Model.Operator;
import MarblesPuzzle.Model.State;

import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 *  A* algorithm is an Informed search algorithm,
 *  which evaluates `Node`s during search using 
 *  a HeuristicEval comparator to help "navigate" the algorithm towards the goal.
 */
public class AStar extends Algorithm {

    private final PriorityQueue<Node> PQ;
    private final Hashtable<IState, Node> frontier;
    private final Hashtable<IState, Node> exploredSet;

    public AStar(IProblem problem, boolean verbose, HeuristicEval h) {
        super(problem, verbose);
        this.name = "A*";
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
                g = operator.apply();
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
