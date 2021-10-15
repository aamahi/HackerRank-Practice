import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String B = in.next();
        
        int i = 0;
        int total = 0;
        while (i < B.length()-2) {
            if (B.substring(i,i+3).equals("010")) {
                total++;
                i+=3;
            } else {
                i++;
            }
        }
        System.out.println(total);
    }
}