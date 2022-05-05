//package BestVersion;
///**
// * This class contains the heuristic function.
// * I used the Manhattan Distance for the computation.
// */
//public class Heuristic {
//
//    private int heuristic;  // the heuristic value computed
//    private int len;        // the board length
//
//    public int compute(State currentState, State goalState) {
//
//        heuristic = 0;
//        len = currentState.getBoard().length;
//
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                // we don't compute MD if we have already correct marble at this location or the char '_'
//                if (currentState.getBoard()[i][j] == '_' ||
//                        currentState.getBoard()[i][j] == goalState.getBoard()[i][j]) {
//                    continue;
//                }
//                manhattanDistance(currentState, goalState, i, j);
//            }
//        }
//
//        return heuristic;
//    }
//
//    private void manhattanDistance(State curr, State goal, int i, int j) {
//
//        for (int n = 0; n < len; n++) {
//            for (int m = 0; m < len; m++) {
//                if (curr.getBoard()[n][m] == goal.getBoard()[i][j]) {
//                    heuristic += ((Math.abs(i - n) + Math.abs(j - m)) * len); // Manhattan Distance
//                    return;
//                }
//            }
//        }
//    }
//}