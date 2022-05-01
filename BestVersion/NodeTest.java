//package BestVersion;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class NodeTest {
//
//    private IOHandler io;
//    private long start_time;
//
//    @BeforeEach
//    public void init() {
//        io = new IOHandler("src/input.txt");
//        start_time = System.currentTimeMillis();
//    }
//
//    @Test
//    public void test_equals() {
//        Board b = new Board(io.getInitialGameState());
//        Node n = new Node(b);
//
//        System.out.println(n);
//    }
//
//}