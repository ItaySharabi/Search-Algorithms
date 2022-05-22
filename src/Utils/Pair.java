package src.Utils;

import src.Model.Marble;

public class Pair {

    private Direction d;
    private Marble m;

    public Pair(Direction d, Marble m) {
        this.d = d;
        this.m = m;
    }

    public Direction getDirection() {
        return d;
    }

    public Marble getMarble() {
        return m;
    }

    @Override
    public String toString() {
        return "[" +
                m +
                ", " + d +
                ']';
    }
}
