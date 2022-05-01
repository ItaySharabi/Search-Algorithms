package BestVersion;

import java.util.List;

public class Node {

    private final Board boardState;
    private Direction operatedMarbleDirection;
    private Marble operatedMarble;
    private int weight;
//    private final long timeCreated;
    private static int nodeCounter = 0;

    public Node(Board b/*, long start_time*/) {
        nodeCounter++;
        this.boardState = b;
//        this.timeCreated = System.currentTimeMillis() - start_time;
    }

    public Node(Board b, Marble t, Direction d, int weight/*, long start_time*/) {
        this(b);
        int i = t.getI(), j = t.getJ();

        switch (d) {
            case UP:
                i -= 1;
                break;
            case DOWN:
                i += 1;
                break;
            case LEFT:
                j -= 1;
                break;
            case RIGHT:
                j += 1;
                break;
        }
        this.operatedMarble = new Marble(t.getTag(), i, j);
        this.operatedMarbleDirection = d;
        this.weight = weight;
    }

    public Marble getOperatedMarble() {
        return operatedMarble;
    }

    public Direction getOperatedMarbleDirection() {
        return operatedMarbleDirection;
    }

    public int getWeight() {
        return weight;
    }

    public List<Marble> getMovableMarbles() {
        return boardState.getMovableMarbles();
    }

    public static int getNumNodesCreated() {
        return nodeCounter;
    }

    public Board getBoardState() {
        return boardState;
    }

//    public double getTimeCreated() {
//        return timeCreated/1000.0;
//    }

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
                "boardState=\n" + boardState;
//                ", timeCreated=" + timeCreated/1000.0;
    }
}
