import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the surfaceArea function below.
    static int surfaceArea(int H,int W,int[][] A) {
        int n = H;//A.length;
        int m = W;//A[0].length;
        //System.out.println(m);
        int k,pos=0;//,flag=0;
        int cost=0;
        int noO=0;
        int tba=0;
        int a=0,b=0,c=0,d=0,e=0;
        cost = n*m*2;
        int i,j,max=0;
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(A[i][j]==0)
                    noO++;
            }
        }
        cost=cost-(noO*2);
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                e = A[i][j];
                a = va(n,m,i,j,A);
                b = vb(n,m,i,j,A);
                c = vc(n,m,i,j,A);
                d = vd(n,m,i,j,A);
                 if(e>a)
                    {
                        tba = tba+(e-a);
                    }
                    if(e>b)
                    {
                        tba = tba+(e-b);
                    }
                    if(e>c)
                    {
                        tba = tba+(e-c);
                    }
                    if(e>d)
                    {
                        tba = tba+(e-d);
                    }
            }
            
        }
        cost = cost+tba;
        return cost;

    }
    
    static int va(int n,int m,int i, int j, int[][] A)
    {
        if(j==0)
            return 0;
        else 
            return(A[i][j-1]);
    }
     static int vb(int n,int m,int i, int j, int[][] A)
    {
         if(j==m-1)
            return 0;
        else 
            return(A[i][j+1]);
    }
     static int vc(int n,int m,int i, int j, int[][] A)
    {
         if(i==0)
            return 0;
        else 
            return(A[i-1][j]);
    }
     static int vd(int n,int m,int i, int j, int[][] A)
    {
         if(i==n-1)
            return 0;
        else 
            return(A[i+1][j]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        
         String[] nm = scanner.nextLine().split(" ");

        int H = Integer.parseInt(nm[0].trim());

        int W = Integer.parseInt(nm[1].trim());
        /*String[] HW = scanner.nextLine().split(" ");
        
        int H = Integer.parseInt(HW[0]);
        System.out.println(H);
        int W = Integer.parseInt(HW[1]);*/
        //System.out.println(H);
        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(H,W,A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}