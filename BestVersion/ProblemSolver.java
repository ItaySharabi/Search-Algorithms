package BestVersion;

public class ProblemSolver {

    private IProblem p;

    public ProblemSolver(IProblem p) {
        this.p = p;
    }

    public String solve(String algorithmName, boolean verbose) {
        Algorithm algo;
        HeuristicEval heuristicEval = new ManhattanDistance(p.getGoal());
        if (algorithmName.equals("BFS")) {
            algo = new BFS(p, verbose);
        }
        else if(algorithmName.equals("DFID")) {
            algo = new DFID(p, verbose);

        }
        else if(algorithmName.equals("A*") || algorithmName.equals("AStar")) {
            algo = new AStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equals("IDA*") || algorithmName.equals("IDAStar")) {
            algo = new IDAStar(p, verbose, heuristicEval);

        }
        else if(algorithmName.equalsIgnoreCase("DFBnB")) {
            algo = new DFBnB(p, verbose, heuristicEval);

        }
        else {
            System.out.println("Invalid algorithm name!");
            return "";
        }

        return algo.execute();
    }

    public IProblem getP() {
        return p;
    }
}
