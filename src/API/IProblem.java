package src.API;

import src.Model.State;

public interface IProblem {
    public State getStart();
    public State getGoal();
}
