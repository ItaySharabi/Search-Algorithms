package MarblesPuzzle.Model;

import API.IProblem;
import API.IState;

public class Problem implements IProblem {
    private final IState start, goal;

    public Problem(IState s, IState g) {
        start = s;
        goal = g;
    }

    @Override
    public IState getInitialState() {
        return start;
    }

    @Override
    public IState getGoalState() {
        return goal;
    }

    @Override
    public String toString() {
        return "\n`Arrange The Marbles`:\n" +
                "Let's try to find a solution for this game!\n" +
                "===========================================\n\n\n" +
                "Initial board state:\n" + start +
                "\nGoal board state:\n" + goal;
    }
}
