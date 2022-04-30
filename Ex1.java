import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex1 {


    public static void main(String[] args) {

        long start_time = System.currentTimeMillis();
        IOHandler io = new IOHandler("src/input.txt");

        // Extract algorithm execution info:
        String algoName = io.getAlgorithmName();
        Board start = new Board(io.getInitialGameState());
        Board goal = new Board(io.getGoalGameState());
        boolean showOpenList = io.getVerbose();


        Algorithm bfs = new BFS();
        List<Board> goals = new ArrayList<>();
        goals.add(goal);
        String out = bfs.execute(start, goals, true);
        System.out.println(out);
//        try {
//            io.write(
//                    AlgorithmsLibrary.execute(algoName, showOpenList, start, goal)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
