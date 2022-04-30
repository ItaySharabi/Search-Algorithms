import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private IOHandler io;
    private long start_time;

    @BeforeEach
    public void init() {
        io = new IOHandler("src/input.txt");
        start_time = System.currentTimeMillis();
    }

    @Test
    public void test_equals() {
        Board b = new Board(io.getInitialGameState());
        Node n = new Node(b, start_time);

        System.out.println(n);
    }

    @Test
    public void test_board() {
        long start_time = System.currentTimeMillis();
        Board b = new Board(io.getInitialGameState());
        Hashtable<Board, Node> table = new Hashtable<>();
        Board b1, b2, b3;

        b1 = Operator.moveTile(b, Direction.RIGHT, new Marble(b.getBoard()[0][1], 0, 1));
        b2 = Operator.moveTile(b, Direction.RIGHT, new Marble(b.getBoard()[1][1], 1, 1));
        b3 = Operator.moveTile(b, Direction.RIGHT, new Marble(b.getBoard()[2][1], 2, 1));
        table.put(b1, new Node(b1, start_time));
        table.put(b2, new Node(b2, start_time));
        table.put(b3, new Node(b3, start_time));
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        assertTrue(table.containsKey(b1));
        assertTrue(table.containsKey(b2));
        assertTrue(table.containsKey(b3));

        assertEquals(b1.getMovableMarbles().stream().distinct().count(), b1.getMovableMarbles().size());
        assertEquals(b2.getMovableMarbles().stream().distinct().count(), b2.getMovableMarbles().size());
        assertEquals(b3.getMovableMarbles().stream().distinct().count(), b3.getMovableMarbles().size());


    }

    @Test
    public void test_operator() {
        Board start = new Board(io.getInitialGameState());
        Board goal = new Board(io.getGoalGameState());
        Node s = new Node(start, start_time);
        Board res = Operator.moveTile(s.getBoardState(), Direction.RIGHT, start.getTile(0, 1));

        assertNull(start.getPrev());
        assertNull(goal.getPrev());
        assertNotNull(res.getPrev());
        assertEquals(start, res.getPrev());

        assertNotEquals(res.getTile(0, 1), start.getTile(0, 1));
        assertNotEquals(res.getTile(0, 1), start.getTile(0, 2));
        assertNotEquals(start, res);

        assertTrue(start.movableTile(start.getTile(0, 1), Direction.RIGHT));
        assertFalse(start.movableTile(start.getTile(0, 1), Direction.UP));
        assertFalse(start.movableTile(start.getTile(0, 1), Direction.DOWN));
        assertFalse(start.movableTile(start.getTile(0, 1), Direction.LEFT));

        assertTrue(res.movableTile(start.getTile(0, 2), Direction.DOWN));
        assertFalse(res.movableTile(start.getTile(0, 2), Direction.LEFT));
        assertFalse(res.movableTile(start.getTile(0, 2), Direction.UP));
        assertFalse(res.movableTile(start.getTile(0, 2), Direction.RIGHT));

        Board c = res.getPrev();
        System.out.println(start);
        System.out.println(c);
        assertEquals(start, c);
    }
}