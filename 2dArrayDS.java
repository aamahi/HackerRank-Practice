import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for(int arr_i=0; arr_i < 6; arr_i++){
            for(int arr_j=0; arr_j < 6; arr_j++){
                arr[arr_i][arr_j] = in.nextInt();
            }
        }
        
        
        Sum(arr);
        
    }
    
    
    
    
    private  static void Sum(int arr[][]){
            //ROw
            int sum=-1000;
            for(int i =0 ; i<4;i++){
                for(int x =0 ; x<4; x++){
                    
                    int top = arr[i][x]+arr[i][x+1]+arr[i][x+2];
                    int middle = arr[i+1][x+1];
                    int bottom = arr[i+2][x]+arr[i+2][x+1]+arr[i+2][x+2];
                    if(top+middle+bottom>sum){sum=top+middle+bottom;}
                }
            }
            System.out.println(sum);
            
            
        }
}
 
