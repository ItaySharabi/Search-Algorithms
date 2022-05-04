package BestVersion;

import java.util.ArrayList;
import java.util.List;

public class Operator {
    private Pair p;

    private Operator(Pair p) {this.p = p;}

    public Node apply(Node n) {
        if (null == n || null == p) {return null;}
        return new Node(n, p);
    }

    public static List<Operator> allowedOperators(Node n) {
        List<Operator> allowedOperators = new ArrayList<>();
        Marble badMarble = null;
        Direction badDir = null;
        if (null != n.getParent()) {
            badMarble = n.getOperatedMarble();
            badDir = opposite(n.getOperatedMarbleDirection());
        }

        for (Direction d : Direction.values()) {
            for (Marble m : n.getState().getMovableMarbles()) {
                if (null != badMarble) {
                    if (m.equals(badMarble) && badDir.equals(d)) {continue;}
                }
                if (n.getState().movableTile(m, d)) {
                    allowedOperators.add(new Operator(new Pair(d, m)));
                }
            }
        }
        return allowedOperators;
    }

    private static Direction opposite(Direction d) {
        switch (d) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
        }
        return Direction.UP;
    }

    @Override
    public String toString() {
        return "Operator{" +
                p +
                '}';
    }
}
