import java.util.Hashtable;
import java.util.List;

public class DFID extends Algorithm {

    private Hashtable<Board, Node> frontier;
    private Hashtable<Board, Node> exploredSet;

    public DFID() {
        this.name = "DFID";
        this.frontier = new Hashtable<>();
        this.exploredSet = new Hashtable<>();
    }

    @Override
    public String execute(Board start, List<Board> goals, boolean withOpen) {



        return null;
    }
}
