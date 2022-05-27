package API;

import Algorithms.Node;
import MarblesPuzzle.Model.State;

public interface IOperator {
    public State apply(Node n);
}
