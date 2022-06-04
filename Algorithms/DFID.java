package Algorithms;

import API.*;
import MarblesPuzzle.Model.Operator;

import java.util.Hashtable;

public class DFID extends Algorithm {
    private long startTime;
    public DFID(IProblem p, boolean verbose) {
        super(p, verbose);
        this.name = "DFID";
    }

    private String LimitedDFS(Node curr, int depth, Hashtable<IState, Node> workingBranch) {
        print(curr);

        if (isGoal(curr)) {
            return output(path(curr), curr.getWeight(), startTime);
        } else if (depth == 0) {
            return "cutoff";
        }
        workingBranch.put(curr.getState(), curr);
        boolean isCutoff = false;
        Node next = null;
        String result;
        IState g;
        for (IOperator operator : Operator.allowedOperators(curr)) {
            g = operator.apply();
            if (null == g || workingBranch.containsKey(g)) {continue;}
            next = new Node(curr, g);
            result = LimitedDFS(next, depth-1, workingBranch);

            if (result.equals("cutoff")) {
                isCutoff = true;
            } else if (!result.equals("fail")) {
                return result;
            }
        }

        if (null != next) {
            workingBranch.remove(next.getState());
        }
        if (isCutoff) {
            return "cutoff";
        } else return "fail";
    }

    @Override
    public String execute() {

        Node root = new Node(getStart());
        // Initialize Hashtable H to keep track of
        // current working branch
        Hashtable<IState, Node> H;
        String output;
        startTime = System.currentTimeMillis();

        int l = Integer.MAX_VALUE; // limit
        for (int i = 1; i < l; ++i) {
            H = new Hashtable<>();
            output = LimitedDFS(root, i, H);
            if (!output.equals("cutoff")) {
                return output;
            }
        }
        return "no path";
    }
}