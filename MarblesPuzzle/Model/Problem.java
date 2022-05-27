package MarblesPuzzle.Model;

import API.IProblem;

public class Problem implements IProblem<State> {
    private State start, goal;

    public Problem(State s, State g) {
        start = s;
        goal = g;
    }

    @Override
    public State getInitialState() {
        return start;
    }

    @Override
    public State getGoalState() {
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
