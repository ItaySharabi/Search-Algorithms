package API;

import MarblesPuzzle.Heuristics.ManhattanDistance;
import MarblesPuzzle.Model.State;

import java.util.Comparator;

/**
 * Heuristic Evaluation abstraction class.
 * This is a Comparator class, which compares
 * `src.Model.Node`s by their f(n) value.
 * The f(n) value of a `src.Model.Node` n - is the heuristic evaluation
 * of the node, h(n), and some cost function, g(n).
 * There are many different heuristic evaluation methods available.
 * @see ManhattanDistance
 * */
public abstract class HeuristicEval implements Comparator<Node> {
    private final IState goalState; // Goal state to compare with

    public HeuristicEval(IState goal){goalState = goal;}

    @Override
    public int compare(Node n1, Node n2) {
        int f1 = f(n1), f2 = f(n2);
        if (f1 == f2) {
            return n1.getDepth() - n2.getDepth();
        }
        else {
            return f1 - f2;
        }
    }

    public abstract int h(IState s);

    public int f(Node n) {
        return n.getWeight() + h(n.getState()); // give a factor of x5 to the Heuristic value!
    }

    public IState getGoalState() {
        return goalState;
    }
}
