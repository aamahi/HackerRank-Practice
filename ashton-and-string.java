import java.util.*;

public class Solution {

    public static Integer[] GenerateSuffixArray(final String s)
    {
        int        len = s.length();
        Integer [] ret = new Integer[len];

        for (int i=0; i < len; i++) {
            ret[i] = i;
        }
        Arrays.sort(ret, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return s.substring(i1).compareTo(s.substring(i2));
            }
        });
        return ret;
    }

    public static int[] GenerateLCP(String s, Integer [] suffix)
    {
        int    len     = s.length();
        int [] ret     = new int[len];
        String prevStr = s.substring(suffix[0]);

        ret[0] = 0;
        for (int i=1; i < len; i++) {
            String str  = s.substring(suffix[i]);
            int    j    = 0;
            int    jMax = Math.min(prevStr.length(), str.length());

            // Find the longest common prefix of prevStr and str.
            for (j=0; j < jMax; j++) {
                if (prevStr.charAt(j) != str.charAt(j))
                    break;
            }
            ret[i] = j;
            prevStr = str;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            String s = scanner.next();
            long K = scanner.nextLong();

            int     n         = s.length();
            Integer [] suffix = GenerateSuffixArray(s);
            int     [] lcp    = GenerateLCP(s, suffix);

            for (int i=0;i<n;i++) {
                long len    = n - suffix[i];
                long count  = len * (len + 1) / 2;
                long prefix = lcp[i];

                count -= prefix * (prefix + 1) / 2;

                // If K is not found within the substrings generated by this
                // suffix, then go on to the next suffix.
                if (K > count) {
                    K -= count;
                    continue;
                }

                // K must be found within this suffix.  Now iterate through its
                // substring to find the exact substring and character.
                for (long j=prefix+1;j<=len;j++) {
                    if (K <= j) {
                        System.out.println(s.charAt((int) (suffix[i] + K - 1)));
                        break;
                    }
                    K -= j;
                }
                break;
            }
        }
    }
}
