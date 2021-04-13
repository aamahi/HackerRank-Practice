import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int max = 0;
        for(int i=0; i < n; i++){
            max = Math.max(max,in.nextInt());
        }
        if(k-max < 0)
            System.out.println(Math.abs(k-max));
        else
        System.out.println(0);
    }
}