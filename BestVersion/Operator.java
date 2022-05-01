package BestVersion;

public class Operator {

    private Operator() {}

    public static Board moveTile(Node n, Direction d, Tile t) {
        if (null == n || null == t || null == d) {return null;}
        if (n.getOperatedMarbleDirection() == opposite(d)) {return null;}
        if (!n.getBoardState().movableTile(t, d)) {return null;}

        String[][] newBoard = n.getBoardState().getBoard();
        int i = t.getI();
        int j = t.getJ();

        switch (d) {
            case UP:
                newBoard[i-1][j] = t.getTag();
                break;
            case DOWN:
                newBoard[i+1][j] = t.getTag();
                break;
            case LEFT:
                newBoard[i][j-1] = t.getTag();
                break;
            case RIGHT:
                newBoard[i][j+1] = t.getTag();
                break;
        }
        newBoard[i][j] = "_";
        return new Board(newBoard);
    }

    public static Direction opposite(Direction d) {
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
}
