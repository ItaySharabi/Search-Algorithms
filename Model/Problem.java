package Model;

import API.IProblem;

public class Problem implements IProblem {
    private State start, goal;

    public Problem(State s, State g) {
        start = s;
        goal = g;
    }

    @Override
    public State getStart() {
        return start;
    }

    @Override
    public State getGoal() {
        return goal;
    }
}
