//package BestVersion;
//
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//        IOHandler io = new IOHandler("BestVersion/input.txt");
//
//        // Extract algorithm execution info:
//        String algoName = io.getAlgorithmName();
//        IProblem p = io.getProblem();
//        boolean showOpenList = io.getVerbose();
//
//
//        Node n = new Node(start);
//        HeuristicEval h = new ManhattanDistance(goal);
//
//        List<State> childsOfN = new ArrayList<>();
//        PriorityQueue<Node> PQ = new PriorityQueue<>(h);
//        Hashtable<State, Node> H = new Hashtable<>();
//        H.put(start, n);
//        // Create possible states of n
//        for (Operator op : Operator.allowedOperators(n)) {
//            childsOfN.add(op.apply(n));
//            System.out.println(childsOfN.get(childsOfN.size()-1));
//        }
//        System.out.println("======================================================");
//        System.out.println("======================================================");
//        System.out.println("======================================================");
//
//        Node child;
//        for (State s : childsOfN) {
//            System.out.println("==========================================");
//            System.out.println("Heuristic evaluation of");
//            System.out.println("child: \n" + s + "::");
//            System.out.println("h(s) = " + h.heuristicVal(s));
//            child = new Node(n, s);
//            System.out.println("f(child) = h(s) + child.getWeight = " + (h.f(child)));
//            H.put(s, child);
//        }
////
////        int countStates = 0;
////        int innerCount;
////        for (State s1 : H.keySet()) {
////            if (H.containsKey(s1)) {
////                countStates++;
////            }
////            innerCount = 0;
////            for (State s2 : H.keySet()) {
////                if (s1.equals(s2) || s1.hashCode() == s2.hashCode()) {
////                    innerCount++;
////                }
////            }
////            if (innerCount > 1) {
////                System.out.println("DUPLICATES WERE FOUND FOR STATE " + s1);
////            }
////        }
////        System.out.println("States in the set: " + countStates + " (Out of " + H.size() + ")");
//    }
//}
