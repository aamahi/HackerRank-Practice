import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Graph{
    private int n;
    private List<Integer> adj[];
    private boolean visited[][];
    private int dist[][];
    private int k;
    private int INF = 10000000;
    private int bitMask[];
    private int cost[][];
    Graph(int n, int k, int bitMask[]){
        this.n = n;
        this.k = k;
        this.bitMask = bitMask;
        adj = new List[n];
        for(int i=0;i<n;i++){
            adj[i] = new ArrayList<Integer>();
        }
        visited = new boolean[n][(1<<k)];
        dist = new int[n][(1<<k)];
        cost = new int[n][n];
        for(int i=0;i<dist.length;i++){
            for(int j=0;j<dist[i].length;j++){
                dist[i][j] = INF;
            }
        }
    }
    public void addEdge(int u,int v,int c){
        adj[u].add(v);
        adj[v].add(u);
        cost[u][v] = c;
        cost[v][u] = c;
    }
     public void bfs(int source,int mask){
         dist[source][mask] = 0;
      //   visited[source][mask] = true;
         LinkedList<Integer[]> set = new LinkedList<>();
         set.add(new Integer[]{source,mask}); // current node, bitmask
         while(set.size()>0){
             Integer[] current = set.poll();
             int currentSource = current[0];
             int currentMask = current[1];
             Iterator<Integer> itr = adj[currentSource].iterator();
             while(itr.hasNext()){
                int next = itr.next();
                int newMask = currentMask| bitMask[next];
                int newDist = dist[currentSource][currentMask] + cost[currentSource][next];
                    if(newDist < dist[next][newMask]){
                    dist[next][newMask] = newDist;
                    set.add(new Integer[]{next,newMask});
                    }
             //    visited[next][newMask] = true;
                    
                }                      
             }
    //      for(int i=0;i<dist.length;i++){
    //     for(int j=0;j<dist[i].length;j++){
    //         System.out.print(dist[i][j]+" ");
    //     } 
    //     System.out.println();
    // }
       int output = Integer.MAX_VALUE; 
       for(int i=0;i<(1<<k);i++){
           for(int j=1;j<=i;j++){
               if((i|j) == (1<<k)-1){
                   output = Math.min(output,Math.max(dist[n-1][i],dist[n-1][j]));
               }
           }
       }
             System.out.println(output);

     }
}
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt(),m = sc.nextInt(), k = sc.nextInt();
       int bitMask[] = new int[n];
       for(int i=0;i<n;i++){
           int numberOfFish = sc.nextInt();
           for(int j=0;j<numberOfFish;j++){
               int fishType = sc.nextInt();
               bitMask[i] = bitMask[i] | 1<<(fishType-1);
           }
       }
    Graph g = new Graph(n,k,bitMask);
     for(int i=0;i<m;i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         int cost = sc.nextInt();
         g.addEdge(u-1,v-1,cost);
     }
    g.bfs(0,bitMask[0]);
}
    
}