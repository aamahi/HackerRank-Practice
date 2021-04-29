import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
public static void main(String[] args) {
/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
Scanner ask=new Scanner(System.in);
int[] colors=new int[101];
int ans=0;
int n=ask.nextInt();
for(int i=0;i<n;i++){
int color=ask.nextInt();
colors[color]++;
if(colors[color]%2 == 0){
ans++;
colors[color] = 0;
}
}
System.out.println(ans);
}
}