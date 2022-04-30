public class Marble extends Tile {
    private int cost;

    public Marble(String tag, int i, int j) {
        super(tag, i, j);
        if (tag.equals("R")) {
            cost = 1;
        }
        else if (tag.equals("B")) {
            cost = 2;
        }
        else if (tag.equals("G")) {
            cost = 10;
        }
        else if (tag.equals("Y")) {
            cost = 1;
        }
    }

    public Marble(Tile t) {
        this(t.getTag(), t.getI(), t.getJ());
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Marble[" +
                "`" + getTag() + "`,"
                + "(" + i + "," + j + ")"
                + "]";
    }
}
