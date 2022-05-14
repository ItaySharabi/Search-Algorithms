import java.io.IOException;

/**
 * This is a
 * @author Itay Sharabi
 */
public class Ex1 {

    public static int[] primes = {7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107};

    public static void main(String[] args) {
        IOHandler io;
        IProblem p;
        try {
            io = new IOHandler("input.txt");

            // Extract execution info:
            p = io.getProblem();
            String algoName = io.getAlgorithmName();
            boolean showOpenList = io.getVerbose();

            // Solve the problem
            String solution =
                    ProblemSolver.solve(p, algoName, showOpenList);

            io.write(solution, "output.txt");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I wish I knew what went wrong...");
        }
    }
}
