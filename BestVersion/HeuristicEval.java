package BestVersion;

import java.util.Comparator;

public abstract class HeuristicEval implements Comparator<Node> {
    private final State goalState;

    public HeuristicEval(State goal){goalState = goal;}

    @Override
    public int compare(Node n1, Node n2) {
        int f1 = f(n1), f2 = f(n2);
        if (f1 == f2) {
//            return n1.getIterationCreated() - n2.getIterationCreated();
            return n1.getDepth() - n2.getDepth();
        }
        else {
            return f1 - f2;
        }
    }

    public abstract int heuristicVal(State s);

    public int f(Node n) {
        return n.getWeight() + heuristicVal(n.getState()); // give a factor of x5 to the Heuristic value!
    }

    public State getGoalState() {
        return goalState;
    }
}
