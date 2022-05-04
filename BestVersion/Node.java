package BestVersion;

import java.util.List;

public class Node {

    private Node parent;
    private Board boardState;
    private Direction operatedMarbleDirection;
    private Marble operatedMarble;
//    private Index operatedMarbleIndex;
    private int depth;
    private int weight;
//    private final long timeCreated;
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

    public Node(Node n, Pair p) {
        key = nodeCounter++;
        Marble t = p.getMarble();
        Direction d = p.getDirection();
        int i = t.getI(), j = t.getJ();

        // Calculate the new index in which the moved marble is at
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
        String[][] newBoard = n.boardState.getBoard();
        newBoard[i][j] = t.getTag();
        newBoard[t.getI()][t.getJ()] = "_";
        this.boardState = new Board(newBoard);
        this.parent = n;
        this.operatedMarbleDirection = d;
        this.operatedMarble = new Marble(t.getTag(), i, j);
        this.weight = n.getWeight() + t.getCost();
        this.depth = n.depth + 1;
    }

    public Node getParent() {
        return parent;
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
