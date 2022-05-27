package MarblesPuzzle.API;

import MarblesPuzzle.Model.State;

public interface IProblem {
    public State getStart();
    public State getGoal();
}
