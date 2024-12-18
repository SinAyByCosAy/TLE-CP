package DPBootcamp.Contests.CF993Div4;

import java.util.HashSet;
import java.util.Scanner;

public class ProbF {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        long sum1 = 0;
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            sum1 += a[i];
        }
        long sum2 = 0;
        int[] b = new int[m];
        for(int i = 0; i < m; i++){
            b[i] = sc.nextInt();
            sum2 += b[i];
        }

        //X = (rowSum - a[r]) . (colSum - b[c])
        //so we store the various values of rowSum - a[r] and colSum - b[c]
        //then we find factors of X. If both factors are present in the stored values, we can have X as sum
        HashSet<Long> rowSums = new HashSet<>();
        HashSet<Long> colSums = new HashSet<>();
        for(int i = 0; i < n; i++)
            rowSums.add(sum1 - a[i]);
        for(int i = 0; i < m; i++)
            colSums.add(sum2 - b[i]);

        for(int i = 1; i <= q; i++){
            long x = sc.nextLong();
            int limit = (int)Math.sqrt(Math.abs(x));
            boolean flag = false;
            for(long j = 1; j <= limit; j++){
                if(x % j == 0){
                    if(checkFactors(j, x/j, rowSums, colSums)){
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean checkFactors(long a, long b, HashSet<Long> rowSums, HashSet<Long> colSums){
        if(rowSums.contains(a) && colSums.contains(b)) return true;
        else if(rowSums.contains(b) && colSums.contains(a)) return true;
        else if(rowSums.contains(-a) && colSums.contains(-b)) return true;
        else if(rowSums.contains(-b) && colSums.contains(-a)) return true;
        return false;
    }
}
//TC: O(N + M + Q.sqrt(X))