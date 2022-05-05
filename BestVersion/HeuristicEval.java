package BestVersion;

import java.util.Comparator;

public abstract class HeuristicEval implements Comparator<Node> {

    public HeuristicEval(Node goal){}

    @Override
    public int compare(Node n1, Node n2) {

        if (f(n1) == f(n2)) {
            return n1.getDepth() - n2.getDepth();
        }
        else {
            return f(n1) - f(n2);
        }
    }

    public abstract int heuristicVal(Node n);

    public int f(Node n) {
        return n.getWeight() + heuristicVal(n);
    }
}
