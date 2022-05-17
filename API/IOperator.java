package API;

import Model.Node;
import Model.State;

public interface IOperator {
    public State apply(Node n);
}
