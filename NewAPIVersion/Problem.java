package NewAPIVersion;

public class Problem implements IProblem {

    private IState start, goal;
    public Problem(IState s, IState g) {
        start = s;
        goal = g;
    }
    @Override
    public IState getStart() {
        return start;
    }

    @Override
    public IState getGoal() {
        return goal;
    }
}
