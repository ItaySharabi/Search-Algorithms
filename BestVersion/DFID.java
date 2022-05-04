//package BestVersion;
//
//import java.util.Hashtable;
//import java.util.List;
//
//public class DFID extends Algorithm {
//
////    private final Hashtable<Board, Node> frontier;
//    private final Hashtable<Board, Node> exploredSet;
//
//
//    public DFID() {
//        this.name = "DFID";
////        this.frontier = new Hashtable<>();
//        this.exploredSet = new Hashtable<>();
//    }
//
//
//
//    private String LimitedDFS(Node curr, List<Board> goals, int depth, Hashtable<Board, Node> workingBranch) {
//
//        if (goals.contains(curr.getBoardState())) {
//            if (goals.size() > 1) {
//                System.out.println("Goal found!");
//                System.out.println("More goals could be found!");
//            }
//            return output(path(curr), curr.getWeight());
//        } else if (depth == 0) {
//            return "cutoff";
//        }
//        workingBranch.put(curr.getBoardState(), curr);
//        boolean isCutoff = false;
//        Board g = null;
//        String result;
//
//        for (Direction d : Direction.values()) {
//            for (Marble m : curr.getMovableMarbles()) {
//                g = Operator.apply(curr, d, m);
//                if (null == g || workingBranch.containsKey(g)) {continue;}
//                setPrev(g, curr);
//                result = LimitedDFS(new Node(g, m, d, curr.getWeight() + m.getCost()),
//                        goals, depth-1, workingBranch);
//
//                if (result.equals("cutoff")) {
//                    isCutoff = true;
//                } else if (!result.equals("fail")) {
//                    return result;
//                }
//            }
//        }
//        if (null != g) {
//            workingBranch.remove(g);
//        }
//        if (isCutoff) {
//            return "cutoff";
//        } else return "fail";
//    }
//
//    @Override
//    public String execute(Board start, List<Board> goals, boolean withOpen) {
//
//        int l = 10; // limit
//
//        Node root = new Node(start);
//
//        String output;
//
//        for (int i = 1; i < l; ++i) {
//            output = LimitedDFS(root, goals, i, new Hashtable<>());
//            if (!output.equals("cutoff")) {
//                System.out.println("Path found!");
//                return output;
//            }
//            clearPrevTable();
//            System.out.println(output);
//        }
//        return "no path";
//    }
//}
