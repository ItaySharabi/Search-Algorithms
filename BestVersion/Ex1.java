package BestVersion;

public class Ex1 {

    public static int[] primes = {7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107};

    public static void main(String[] args) {

        long start_time = System.currentTimeMillis();
        IOHandler io = new IOHandler("BestVersion/input.txt");
//        IOHandler io = new IOHandler("BestVersion/input2.txt"); // Bad for IDA*
//        IOHandler io = new IOHandler("BestVersion/input3.txt"); // Bad for IDA*
//        IOHandler io = new IOHandler("BestVersion/input_large.txt");
//        IOHandler io = new IOHandler("BestVersion/input4.txt");
//        IOHandler io = new IOHandler("BestVersion/input6.txt");
//        IOHandler io = new IOHandler("BestVersion/input7.txt");

        // Extract algorithm execution info:
        String algoName = io.getAlgorithmName();
        State start = new State(io.getInitialGameState());
        State goal = new State(io.getGoalGameState());
        boolean showOpenList = io.getVerbose();

        IProblem p = new Problem(start, goal);
        Algorithm bfs = new BFS(p, showOpenList);
//        Algorithm dfid = new DFID(p, showOpenList);
//        Algorithm a_star = new AStar(p, showOpenList, new ManhattanDistance(goal));
//        Algorithm ida_star = new IDAStar(p, showOpenList, new ManhattanDistance(goal));
        String out = bfs.execute();
//        String out = dfid.execute();
//        String out = a_star.execute();
//        String out = ida_star.execute();
        System.out.println(out);


//        System.out.println("Tile's created: " + Marble.marbleCount);
//        System.out.println("State's created: " + State.getBoardCount());
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
