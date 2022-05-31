package API;

import MarblesPuzzle.Model.Operator;
import MarblesPuzzle.Model.State;

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
    private final IState state;
    private int depth;
    private int weight;
    private static int nodeCounter = 1;
    private final int key;
    private boolean tag;

    public Node(IState state) {
        key = nodeCounter++;
        this.state = state;
        this.parent = null;
    }

    public Node(Node n, IState st) {
        key = nodeCounter++;
        this.state = st;
        this.parent = n;
        this.weight = n.getWeight() + st.getCost();
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

    public int getWeight() {
        return weight;
    }

    public static int getNumNodesCreated() {
        return nodeCounter;
    }

    public IState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return state.equals(getState());
    }

    @Override
    public String toString() {
        return "Node #" + key + "\n" +
                state +
                "Weight: " + weight + "\n";
    }
}