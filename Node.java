import java.util.List;

public class Node {

    private final Board boardState;
    private int weight;
    private final long timeCreated;
    private static int nodeCounter = 0;

    public Node(Board b, long start_time) {
        nodeCounter++;
        this.boardState = b;
        this.timeCreated = System.currentTimeMillis() - start_time;
        this.weight = 0;
    }

    public Node(Board b, int weight, long start_time) {
        this(b, start_time);
        this.weight = weight;
    }

    public Marble getOperatedMarble() {
        return boardState.getOperatedMarble();
    }

    public Direction getOperatedMarbleDirection() {
        return boardState.getOperatedMarbleDirection();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {

    }

    public List<Marble> getMovableTiles() {
        return boardState.getMovableMarbles();
    }

    public static int getNumNodesCreated() {
        return nodeCounter;
    }

    public Board getBoardState() {
        return boardState;
    }

    public double getTimeCreated() {
        return timeCreated/1000.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return boardState.equals(node.boardState);
    }

    @Override
    public String toString() {
        return "Node{" +
                "boardState=\n" + boardState +
                ", timeCreated=" + timeCreated/1000.0;
    }
}
