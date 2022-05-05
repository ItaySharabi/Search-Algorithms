package BestVersion;

import java.util.Hashtable;

public class DFID extends Algorithm {

    private Hashtable<State, Node> H;


    public DFID(IProblem p, boolean verbose) {
        super(p, verbose);
        this.name = "DFID";
    }



    private String LimitedDFS(Node curr, int depth, Hashtable<State, Node> workingBranch) {

        if (isGoal(curr)) {
            return output(path(curr), curr.getWeight());
        } else if (depth == 0) {
            return "cutoff";
        }
        workingBranch.put(curr.getState(), curr);
        boolean isCutoff = false;
        Node next = null;
        String result;

        for (Operator operator : Operator.allowedOperators(curr)) {
            State g = operator.apply(curr);
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

        int l = Integer.MAX_VALUE; // limit

        Node root = new Node(getStart());

        String output;
        for (int i = 1; i < l; ++i) {
            H = new Hashtable<>();
            output = LimitedDFS(root, i, H);
            if (!output.equals("cutoff")) {
//                System.out.println("Path found!");
                return output;
            }
//            System.out.println(output);
        }
        return "no path";
    }
}
