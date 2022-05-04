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

        queue.add(new Node(start, getStartTime()));
        Node curr, next;
        Board g;

        while (!queue.isEmpty()) {

            curr = queue.poll();

            // Do exploring...
            frontier.remove(curr.getBoardState());
            exploredSet.put(curr.getBoardState(), curr);

            // Apply allowed operators on any movable marble from current board state:
            for (Direction d : Direction.values()) {
                if (Operator.opposite(d).equals(curr.getOperatedMarbleDirection())) {continue;}
                for (Marble m : curr.getMovableTiles()) {
                    g = Operator.moveTile(curr, d, m);
                    if (null == g) {continue;}

                    if (!(frontier.containsKey(g) || exploredSet.containsKey(g))) {
                        if (goals.contains(g)) {
                            setPrev(g, curr.getBoardState());
                            return output(path(g),
                                    curr.getWeight() + m.getCost());
                        }
                        setPrev(g, curr.getBoardState());
                        next = new Node(g, curr.getWeight() + m.getCost(), getStartTime());
                        frontier.put(g, next);
                        queue.add(next);
                    }
                }
            }
        }
        return "no path";
    }
}
