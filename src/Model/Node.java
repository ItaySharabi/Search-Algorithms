package src.Model;

import src.API.Algorithm;
import src.Utils.Direction;

/**
 * Node class.
 *
 * A `Node` is the Data-Structure, which holds
 * `State`s of a Problem `p`, during the search of an `Algorithm`.
 * It contains the the state,
 * it's depth in the search tree,
 * it's weight (`Node`s gain weight only when being applied an `Operator`.
 * @see State
 * @see Algorithm
 * @see Operator
 */
public class Node {

    private final Node parent;
    private final State boardState;
    private int depth;
    private int weight;
    private static int nodeCounter = 1;
    private final int key;
    private boolean tag;

    public Node(State b) {
        key = nodeCounter++;
        this.boardState = b;
        this.parent = null;
    }

    public Node(Node n, State b) {
        key = nodeCounter++;
        this.boardState = b;
        this.parent = n;
        this.weight = n.getWeight() + b.getOperatedMarble().getCost();
        this.depth = n.depth + 1;
    }

    public boolean getTag() {return tag;}
    public void setTag(boolean tag) {
        this.tag = tag;
    }
    public void markAsOut() {setTag(true);}
    public boolean isOut() {return tag;}

    public int getDepth() {
        return depth;
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

    public static int getNumNodesCreated() {
        return nodeCounter;
    }

    public State getState() {
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
        return "Node #" + key + "\n" +
                (boardState.size() == 3 ? "------\n" : "---------\n") +
                boardState +
                (boardState.size() == 3 ? "------\n" : "---------\n") +
                "Weight: " + weight + "\n";
    }
}
