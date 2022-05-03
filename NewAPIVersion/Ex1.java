package NewAPIVersion;

public class Ex1 {


    public static void main(String[] args) {

        long start_time = System.currentTimeMillis();
//        IOHandler io = new IOHandler("BestVersion/input.txt");

        IProblem p = IOHandler.readProblem("BestVersion/input.txt");
        // Extract algorithm execution info:
//        String algoName = io.getAlgorithmName();
//        Board start = new Board(io.getInitialGameState());
//        Board goal = new Board(io.getGoalGameState());
//        boolean showOpenList = io.getVerbose();


        Algorithm bfs = new BFS(p);
        bfs.solveProblem(); // Make sure to consume an output filepath for the algorithm to write into
//        String out = dfid.execute(start, goals, true);
//        System.out.println(out);
//        try {
//            io.write(
//                    AlgorithmsLibrary.execute(algoName, showOpenList, start, goal)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
