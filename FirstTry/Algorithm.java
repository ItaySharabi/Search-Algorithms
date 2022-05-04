import java.util.Hashtable;
import java.util.List;

public abstract class Algorithm {
    protected String name = "~Algorithm~";
    private final long start_time = System.currentTimeMillis();
    private final Hashtable<Board, Board> prev_table;

    public Algorithm() {
        this.prev_table = new Hashtable<>();
    }

    public String getName() {
        return name;
    }

    public abstract String execute(Board start, List<Board> goals, boolean withOpen);

    public String output(String path, int cost) {
        return path
                .substring(2)
                .substring(0, path.length()-2) +
                "\nNum: " +
                Node.getNumNodesCreated() +
                "\nCost: " +
                cost +
                "\ntime: " + (System.currentTimeMillis() - getStartTime())/1000.0;
    }

    public long getStartTime() {
        return this.start_time;
    }

    protected String path(Board b) {
        if (null == prev_table) {return "BFS: prev_state is null!";}
        if (null == b) {return "";}
        Marble m = b.getOperatedMarble();
        if (null == m) {return "";}
        if (prev_table.get(b) == null) {return m.getTag() + ":(" + (m.getI() + 1) + "," + (m.getJ()  + 1 )+ ")";}
        return path(prev_table.get(b)) + "--" + prev(b) + "" + m.getTag() + ":(" + (m.getI() + 1) + "," + (m.getJ()  + 1 )+ ")";
    }

    private String prev(Board b) {
        if (null == b) {return "";}

        Direction d = b.getOperatedMarbleDirection();
        Marble t = b.getOperatedMarble();

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

    protected void setPrev(Board c, Board prev) {
        prev_table.put(c, prev);
    }
}
