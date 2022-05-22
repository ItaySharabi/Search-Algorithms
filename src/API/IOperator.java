package src.API;

import src.Model.Node;
import src.Model.State;

public interface IOperator {
    public State apply(Node n);
}
