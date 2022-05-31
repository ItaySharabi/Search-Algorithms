package API;

import Algorithms.AStar;
import Algorithms.BFS;
import Algorithms.DFID;
import MarblesPuzzle.Model.Marble;
import MarblesPuzzle.Model.State;
import MarblesPuzzle.Utils.Direction;

/**
 * src.API.Algorithm abstract class.
 * This class defines basic functionalities of a `Search src.API.Algorithm`.
 * Abstract method `execute` is given to all algorithms, and methods
 * such as `path(n)` and `output()`.
 * @see BFS - `Breadth-First` Search: A "Blind" search algorithm.
 * @see DFID - `Depth-First` Iterative Deepening: An iterative "DFS" search algorithm.
 * @see AStar - Informed search algorithm: An informed search algorithm, or a best-first search
 */
public abstract class Algorithm {
    protected String name = "~Algorithm~";
    private long start_time;
    private final IState start, goal;
    private boolean withOpen;

    public Algorithm(IProblem problem, boolean verbose) {
        this.withOpen = verbose;
        start = problem.getInitialState();
        goal = problem.getGoalState();
        start_time = -1;
    }

    protected void timerOn() {
        if (start_time == -1) {
            start_time = System.currentTimeMillis();
        }
    }

    public IState getGoal() {
        return goal;
    }

    public IState getStart() {
        return start;
    }

    public boolean withOpen() {
        return withOpen;
    }

    protected boolean isGoal(IState b) {
        return b.equals(goal);
    }

    protected boolean isGoal(Node n) {
        return isGoal(n.getState());
    }

    public String getName() {
        return name;
    }

    public abstract String execute();

    public String output(String path, int cost) {
        if (null == path || path.isEmpty() || path.equals("no path")) {
            return "Path: Path could not be found!" +
                    "\nNum: " +
                    State.getBoardCount() +
                    "\nCost: inf" +
                    "\ntime: " + (System.currentTimeMillis() - getStartTime())/1000.0;
        }

        if (withOpen) {
            System.out.println("Goal State found:\n" + goal);
        }
        return path
                .substring(2)
                .substring(0, path.length()-2) +
                "\nNum: " +
                    State.getBoardCount() +
                "\nCost: " +
                cost +
                "\ntime: " + (System.currentTimeMillis() - getStartTime())/1000.0;
    }

    public long getStartTime() {
        return this.start_time;
    }

    protected String path(Node n) {
        if (null == n) {return "";}
        Marble m = ((State)n.getState()).getOperatedMarble();
        if (null == m) {return "";}
        if (null == n.getParent()) {return m.getTag() + ":(" + (m.getI()+1) + "," + (m.getJ()+1)+ ")";}
        return path(n.getParent()) + "--" + prev(n) + "" + m.getTag() + ":(" + (m.getI() + 1) + "," + (m.getJ()+1)+ ")";
    }

    private String prev(Node n) {
        if (null == n) {return "";}
        State s = (State) n.getState();
        Direction d = s.getOperatedMarbleDirection();
        Marble t = s.getOperatedMarble();

        if (null == t) {return "";}

        int i = t.getI() + 1, j = t.getJ() + 1;
        switch (d) {
            case UP:
                return "(" + (i+1) + "," + j + "):";
            case LEFT:
                return "(" + i + "," + (j+1) + "):";
            case RIGHT:
                return "(" + i + "," + (j-1) + "):";
            case DOWN:
                return "(" + (i-1) + "," + j + "):";
        }
        return "";
    }
}