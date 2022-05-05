package BestVersion;

import java.util.List;

public abstract class Algorithm {
    protected String name = "~Algorithm~";
    private final long start_time = System.currentTimeMillis();
    private Board start, goal;
    private boolean withOpen;

    public Algorithm(IProblem problem, boolean verbose) {
        start = problem.getStart();
        goal = problem.getGoal();
    }

    public Board getGoal() {
        return goal;
    }

    public Board getStart() {
        return start;
    }

    public boolean withOpen() {
        return withOpen;
    }

    protected boolean isGoal(Board b) {
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
        return path
                .substring(2)
                .substring(0, path.length()-2) +
                "\nNum: " +
                Board.getBoardCount() +
                "\nCost: " +
                cost +
                "\ntime: " + (System.currentTimeMillis() - getStartTime())/1000.0;
    }

    public long getStartTime() {
        return this.start_time;
    }

    protected String path(Node n) {
        if (null == n) {return "";}
        Marble m = n.getOperatedMarble();
        if (null == m) {return "";}
        if (null == n.getParent()) {return m.getTag() + ":(" + (m.getI()+1) + "," + (m.getJ()+1)+ ")";}
        return path(n.getParent()) + "--" + prev(n) + "" + m.getTag() + ":(" + (m.getI() + 1) + "," + (m.getJ()+1)+ ")";
    }

    private String prev(Node n) {
        if (null == n) {return "";}

        Direction d = n.getOperatedMarbleDirection();
        Marble t = n.getOperatedMarble();

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
