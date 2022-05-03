package NewAPIVersion;

import BestVersion.Marble;
import BestVersion.Node;
import BestVersion.Operator;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {

    private final Hashtable<IState, IState> frontier;
    private final Hashtable<IState, IState> exploredSet;
    private IState start, goal;

    public BFS(IProblem p) {
        this.name = "BFS";
        frontier = new Hashtable<>();
        exploredSet = new Hashtable<>();
        start = p.getStart();
        goal = p.getGoal();
    }

    @Override
    public void solveProblem() {
        Queue<IState> queue = new LinkedList<>();

        queue.add(start);
        IState curr, next;

        while (!queue.isEmpty()) {

            curr = queue.poll();

            // Do exploring...
            frontier.remove(curr.get());
            exploredSet.put(curr.getBoardState(), curr);

            // Apply allowed operators on any movable marble from current board state:
            for (Direction d : Direction.values()) {
                if (Operator.opposite(d).equals(curr.getOperatedMarbleDirection())) {continue;}
                for (Marble m : curr.getMovableMarbles()) {
                    g = Operator.moveTile(curr, d, m);
                    if (null == g) {continue;}

                    if (!(frontier.containsKey(g) || exploredSet.containsKey(g))) {
                        if (goals.contains(g)) {
                            setPrev(g, curr);
                            Node goalNode = new Node(g, m , d,
                                    curr.getWeight() + m.getCost());
                            output(path(goalNode), goalNode.getWeight());
                            return;
                        }
                        setPrev(g, curr);
                        next = new Node(g, m, d, curr.getWeight() + m.getCost());
                        frontier.put(g, next);
                        queue.add(next);
                    }
                }
            }
        }
    }
}
