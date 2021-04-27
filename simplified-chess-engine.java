import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Feature {
        public int Qi;
        public int Qj;
        public int qi;
        public int qj;
        public List<int[]> wpieces = new ArrayList<>();
        public List<int[]> bpieces = new ArrayList<>();
    }
    
    static class Move {
        public int srcRow;
        public int srcCol;
        public char srcPiece;
        public int dstRow;
        public int dstCol;
        public char dstPiece;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int t = 0; t < g; t++) {
            char[][] chess = new char[4][4];
            int w = in.nextInt();
            int b = in.nextInt();
            int m = in.nextInt();
            for(int i = 0; i < w; i++) {
                char piece = in.next().charAt(0);
                int col = in.next().charAt(0) - 'A';
                int row = 4 - (in.next().charAt(0) - '0');
                chess[row][col] = piece; 
            }
            for(int i = 0; i < b; i++) {
                char piece = in.next().charAt(0);
                int col = in.next().charAt(0) - 'A';
                int row = 4 - (in.next().charAt(0) - '0');
                chess[row][col] = (char)(piece + ('a' - 'A')); 
            }
            if(m > 1 && m % 2 == 0) {
                m--;
            }
            System.out.println(evaluate(chess, m, 1) ? "YES" : "NO");
        }
    }
    private static boolean evaluate(char[][] chess, int m, int step) {
        Feature f = getFeature(chess);
        if(step % 2 == 1) {
            if(captureBlack(chess, f)) {
                return true;
            }
        }
        if(step == m) {
            return false;
        }
        if(step % 2 == 1) {
            for(Move move: getValidMoves(chess, f.wpieces)) {
                moveChess(chess, move);
                Feature f1 = getFeature(chess);
                if(captureWhite(chess, f1)) {
                    moveBack(chess, move);
                    continue;
                }
                if(evaluate(chess, m, step+1)) {
                    moveBack(chess, move);
                    return true;
                }
                moveBack(chess, move);
            }
        } else {
            for(Move move: getValidMoves(chess, f.bpieces)) {
                moveChess(chess, move);
                if(!evaluate(chess, m, step+1)) {
                    moveBack(chess, move);
                    return false;
                }
                moveBack(chess, move);
            }
            return true;
        }
        return false;
    }
    
    private static void moveChess(char[][] chess, Move m) {
        chess[m.dstRow][m.dstCol] = m.srcPiece;
        chess[m.srcRow][m.srcCol] = 0;
    }
    
    private static void moveBack(char[][] chess, Move m) {
        chess[m.dstRow][m.dstCol] = m.dstPiece;
        chess[m.srcRow][m.srcCol] = m.srcPiece;
    }
    
    private static List<Move> getValidMoves(char[][] chess, List<int[]> pieces) {
        List<Move> res = new ArrayList<>();
        if(pieces.isEmpty()) {
            return res;
        }
        boolean whiteMove = isWhite((char)(pieces.get(0)[0]));
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(isWhite(chess[i][j]) && whiteMove) {
                    continue;
                }
                if(isBlack(chess[i][j]) && !whiteMove) {
                    continue;
                }
                for(int[] p: pieces) {
                    if(isTarget(chess, p, i, j)) {
                        Move m = new Move();
                        m.srcRow = p[1];
                        m.srcCol = p[2];
                        m.srcPiece = (char)(p[0]);
                        m.dstRow = i;
                        m.dstCol = j;
                        m.dstPiece = chess[i][j];
                        res.add(m);
                    }
                }
            }
        }
        return res;
    } 
    
    private static Feature getFeature(char[][] chess) {
        Feature f = new Feature();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                char c = chess[i][j];
                int[] item = new int[3];
                item[0] = c;
                item[1] = i;
                item[2] = j;
                if(isWhite(c)) {
                    f.wpieces.add(item);
                }
                if(isBlack(c)) {
                    f.bpieces.add(item);
                }
                if(c == 'Q') {
                    f.Qi = i;
                    f.Qj = j;
                }
                if(c == 'q') {
                    f.qi = i;
                    f.qj = j;
                }
            }
        }
        return f;
    }
    private static boolean isWhite(char c) {
        return "QNBR".indexOf(c) >= 0;
    }
    
    private static boolean isBlack(char c) {
        return "qnbr".indexOf(c) >= 0;
    }
    private static boolean isEmpty(char c) {
        return c == 0;
    }
    
    private static boolean captureBlack(char[][] chess, Feature f) {
        for(int[] p: f.wpieces) {
            if(isTarget(chess, p, f.qi, f.qj)) {
                return true;
            }
        }
        return false;
    }
    private static boolean captureWhite(char[][] chess, Feature f) {
        for(int[] p: f.bpieces) {
            if(isTarget(chess, p, f.Qi, f.Qj)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isTarget(char[][] chess, int[] piece, int row, int col) {
        char p = (char)piece[0];
        int[] x1 = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] y1 = {1, -1, 0, 0, 1, -1, -1, 1};
        int[] x2 = {1, -1, 1, -1};
        int[] y2 = {1, -1, -1, 1};
        int[] x3 = {0, 0, 1, -1};
        int[] y3 = {1, -1, 0, 0};
        int[] x = x1;
        int[] y = y1;
        
        if(p == 'q' || p == 'Q') {
            x = x1;
            y = y1;
        } else if(p == 'n' || p == 'N') {
            if(Math.abs(piece[1]-row) == 2 && Math.abs(piece[2]-col) == 1) {
                return true;
            }
            if(Math.abs(piece[1]-row) == 1 && Math.abs(piece[2]-col) == 2) {
                return true;
            }
            return false;
        } else if(p == 'b' || p == 'B') {
            x = x2;
            y = y2;
        } else if(p == 'r' || p == 'R') {
            x = x3;
            y = y3;
        }
        for(int d = 0; d < x.length; d++) {
            int i = piece[1] + x[d];
            int j = piece[2] + y[d];
            for(; i >= 0 && i < 4 && j>=0 && j<4; i+=x[d], j+=y[d]) {
                if(i != row || j != col) {
                    if(!isEmpty(chess[i][j])){
                        break;
                    }
                }
                if(i == row && j == col) {
                    return true;
                }
            }
        }
        return false;
    }
}