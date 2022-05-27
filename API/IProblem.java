package API;

public interface IProblem<T> {
    public T getInitialState();
    public T getGoalState();
}
