public class Marble {
    private int cost;
    private int i, j;
    private String tag;
    static int marbleCount = 0;

    public Marble(String tag, int i, int j) {
        marbleCount++;
        this.i = i;
        this.j = j;
        this.tag = tag;
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
        else {
            cost = 0;
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getTag() {
        return tag;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marble m = (Marble) o;
        return i == m.i &&
                j == m.j &&
                tag.equals(m.tag);
    }

    @Override
    public String toString() {
        return "Marble[" +
                "`" + tag + "`,"
                + "(" + i + "," + j + ")"
                + "]";
    }
}
