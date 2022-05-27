package API;

import MarblesPuzzle.Model.Node;
import MarblesPuzzle.Model.State;

public interface IOperator {
    public State apply(Node n);
}
