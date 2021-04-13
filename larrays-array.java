import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
        static int[] aux;
    
    
    
    public static long insertSort(int[] ar)
    {
        aux = new int[ar.length];
        long count = mergeSort(ar, 0, ar.length-1);
        return count;
        
    }
    
    public static long mergeSort(int[] ar, int from, int to) {
        long count = 0;
        if (from + 8 >= to) {
            for (int i = from+1; i <= to; i++) {
                int x = ar[i];
                int j = i-1;
                while (j >= from && ar[j] > x) {
                    ar[j+1] = ar[j];
                    count++;
                    j--;
                }
                ar[j+1] = x;
            }
            return count;
        }
        int middle = (from + to) / 2;
        count += mergeSort(ar, from, middle);
        count += mergeSort(ar, middle+1, to);
        count += merge(ar, from, middle+1,  to);
        return count;
    }
    
    public static long merge(int[] ar, int from, int second, int to) {
        long count = 0;
        for (int i = from; i <= to; i++) {
            aux[i] = ar[i]; 
        }
        int i = from;
        int j = second;
        int k = from;
        while (k <= to) {
            if (i >= second) ar[k] = aux[j++];
            else if (j > to) ar[k] = aux[i++];
            else if (aux[i] <= aux[j]) ar[k] = aux[i++];
            else {
                ar[k] = aux[j++];
                count += (second-i);
            }
            k++;
        }
        return count;
    }
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int i=0;i<t;i++){
            int n = in.nextInt();
            int[] ar = new int[n];
            for(int j=0;j<n;j++){
                ar[j]=in.nextInt();
                //System.err.println(ar[j]);
            }
            long c = insertSort(ar);
            String answer = "YES";
            if (c%2 == 1) answer = "NO";
            System.out.println(answer);
        }
}
}