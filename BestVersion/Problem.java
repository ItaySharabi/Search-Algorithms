package BestVersion;

public class Problem implements IProblem{
    private Board start, goal;

    public Problem(Board s, Board g) {
        start = s;
        goal = g;
    }

    @Override
    public Board getStart() {
        return start;
    }

    @Override
    public Board getGoal() {
        return goal;
    }
}
