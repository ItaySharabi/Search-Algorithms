package BestVersion;

import java.util.List;

public class Node {

    private Node parent;
    private Board boardState;
    private int depth;
    private int weight;
    private static int nodeCounter = 1;
    private int key;

    public Node(Board b) {
        key = nodeCounter++;
        this.boardState = b;
        this.parent = null;
    }

    public int getKey() {
        return key;
    }

    public Node(Node n, Board b) {
        key = nodeCounter++;
        this.boardState = b;
        this.parent = n;
        this.weight = n.getWeight() + b.getOperatedMarble().getCost();
        this.depth = n.depth + 1;
    }

    public Node getParent() {
        return parent;
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

//    public List<Marble> getMovableMarbles() {
//        return boardState.getMovableMarbles();
//    }

    public static int getNumNodesCreated() {
        return nodeCounter;
    }

    public Board getState() {
        return boardState;
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
        return "Node #" + key + "{" +
                "boardState=\n" + boardState;
//                ", timeCreated=" + timeCreated/1000.0;
    }
}
