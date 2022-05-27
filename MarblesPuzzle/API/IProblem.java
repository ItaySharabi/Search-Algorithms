package MarblesPuzzle.API;

import MarblesPuzzle.Model.State;

public interface IProblem<T> {
    public T getInitialState();
    public T getGoalState();
}
