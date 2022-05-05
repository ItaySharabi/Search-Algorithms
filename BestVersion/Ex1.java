package BestVersion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Ex1 {

    public static int[] primes = {7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107};

    public static void main(String[] args) {

        long start_time = System.currentTimeMillis();
        IOHandler io = new IOHandler("BestVersion/input.txt");

        // Extract algorithm execution info:
        String algoName = io.getAlgorithmName();
        Board start = new Board(io.getInitialGameState());
        Board goal = new Board(io.getGoalGameState());
        boolean showOpenList = io.getVerbose();

        IProblem p = new Problem(start, goal);
        Algorithm bfs = new BFS(p, showOpenList);
//        Algorithm dfid = new DFID();

//        Node n1 = new Node(start);
//        Hashtable<Board, Node> map = new Hashtable<>();
//        map.put(n1.getState(), n1);
//        Board n = null;
//        for (Operator operator : Operator.allowedOperators(n1)) {
//            n = operator.apply(n1);
//            map.put(n, new Node(n1, n));
//        }
//        Node p = new Node(n);
//        System.out.println(p);
//        for (Operator operator : Operator.allowedOperators(p)) {
//            n = operator.apply(p);
//            System.out.println("Generated: " + n);
//            if (map.containsKey(n)) {
//                System.out.println("YAY!");
//            }
//        }
//        System.out.println(map);
        String out = bfs.execute();
//        String out = dfid.execute(start, goals, showOpenList);
        System.out.println(out);
//        System.out.println("Tile's created: " + Marble.marbleCount);
//        System.out.println("Board's created: " + Board.getBoardCount());
//        System.out.println("Node's created: " + Node.getNumNodesCreated());
//        try {
//            io.write(
//                    AlgorithmsLibrary.execute(algoName, showOpenList, start, goal)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
