package MarblesPuzzle;

import API.IProblem;
import MarblesPuzzle.Model.Utils.IOHandler;
import java.io.IOException;

/**
 * AI Search Algorithms Library.
 *
 * This project is all about finding (The best?) solutions for
 * a certain problem, for instance: `Arrange the Marbles` Game,
 * which is a very simple game, but when it comes to figuring out
 * in what search space we're dealing with, this project demonstrates
 * 5 different search algorithms, for finding a solution to given
 * situations of the game.
 * Just write an input file (`input1.txt` is an example file. More
 * are to be found in `Inputs` folder) and plug it into the constructor
 * of IOHandler.
 * @see IOHandler
 * @see API.Algorithm
 * @author Itay Sharabi
 */
public class SearchEngine {

    public static void main(String[] args) {
        IOHandler io;
        IProblem p;
        try {
            io = new IOHandler("MarblesPuzzle/Inputs/input1.txt");

            // Extract execution info:
            p = io.getProblem();
            String algoName = io.getAlgorithmName();
            boolean showOpenList = io.getVerbose();

            prompt(p + "\n\n" +
                    " ~ Starting `" + algoName + "` search algorithm ~\n" +
                    "=======================================\n"
                    , showOpenList);

            // Solve the problem
            String solution =
                    ProblemSolver.solve(p, algoName, showOpenList);

            prompt("=======================================" +
                    "\n ~ `" + algoName + "` ~ has finished successfully!\n" +
                    "Open `output.txt` to see the solution for this problem!\n" +
                    "=======================================\n" +
                    solution
                    , showOpenList);

            // Write out solution information
            io.write(solution, "MarblesPuzzle/output.txt");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I wish I knew what went wrong...");
        }
    }

    private static void prompt(String message, boolean verbose) {
        System.out.println(verbose ? message : "");
    }
}
