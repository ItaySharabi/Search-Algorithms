package BestVersion;

public class Tile {

    private final String tag;
    int i, j;

    public Tile(String tag, int i, int j) {
        this.tag = tag;
        this.i = i;
        this.j = j;
    }

    public String getTag() {
        return tag;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return i == tile.i &&
                j == tile.j &&
                tag.equals(tile.tag);
    }

    @Override
    public String toString() {
        return "[" +
                "`" + getTag() + "`,"
                + "(" + i + "," + j + ")"
                + "]";
    }
}
