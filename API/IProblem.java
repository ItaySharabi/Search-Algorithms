package API;

public interface IProblem {
    public IState getInitialState();
    public IState getGoalState();
    public String solve(String algoName, boolean verbose);
}
