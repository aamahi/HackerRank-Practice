import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        input.nextLine();
        
        tests:
        for(int t = 0; t < T; t++)
        {
            String s = input.nextLine();
            int operationsPerformed = 0;
            int i = 0;
            int j = s.length() - 1;
            while(i < j)
            {
                operationsPerformed += Math.abs(s.charAt(i) - s.charAt(j));
                i++;
                j--;
            }
            System.out.println(operationsPerformed);
        }
    }
}