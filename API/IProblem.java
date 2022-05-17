package API;

import Model.State;

public interface IProblem {
    public State getStart();
    public State getGoal();
}
