package BestVersion;

import java.io.IOException;

public class Ex1 {

    public static int[] primes = {7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107};

    public static void main(String[] args) {

        IOHandler io = new IOHandler("BestVersion/input.txt");

        // Extract execution info:
        String algoName = io.getAlgorithmName();
        boolean showOpenList = io.getVerbose();

        ProblemSolver solver = new ProblemSolver(io.getProblem());

        try {
            io.write(solver.solve(algoName, showOpenList));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I wish I knew what went wrong...");
        }
    }
}
