package NewAPIVersion;

public interface IOperator {

    public int compareStates(IState a, IState b);
    public int heuristicEstimation(IState n);
    public boolean isGoal(IState n);
}
