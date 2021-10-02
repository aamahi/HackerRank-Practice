import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] frequencies = new int[100];
        StringBuilder STDOUTT = new StringBuilder("");
        Map<Integer,Queue<StringBuilder>> order = new HashMap<>();

        for(int i = 0; i < n; i++)
        {
            String[] tmp = in.readLine().split(" ");
            int num = Integer.parseInt(tmp[0]);
            
            StringBuilder s = new StringBuilder(tmp[1]);
            if(i < n/2) s = new StringBuilder("-");//use - as s for first half
            
            //add the string to the queue associated with num
            if(!order.containsKey(num))
            {
                Queue<StringBuilder> strs = new LinkedList();
                order.put(num, strs);
            }
                order.get(num).add(s);
                
            frequencies[num] = frequencies[num] + 1;
        }
        
        //For all sorted numbers
        for(int i = 0; i < frequencies.length; i++)
        {
            for(int j = 0; j < frequencies[i]; j++)
            {
                STDOUTT.append(order.get(i).poll().toString() + " ");//Print every element from the queue
            }
        }
        System.out.print(STDOUTT);
    }
}
