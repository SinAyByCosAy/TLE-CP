//https://codeforces.com/problemset/problem/1487/D
package DPBootcamp.BinarySearch;

import java.util.ArrayList;
import java.util.Scanner;

public class PythagoreanTriples {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = (int) 1e5;
        ArrayList<Long> values = new ArrayList<>();
        for(int i = 3; i < m; i += 2){
            long a = i;
            long c = ((a * a) + 1) / 2;
            long b = c - 1;
            if(c * c == (a * a) + (b * b) && c == (a * a) - b)
                values.add(c);
        }
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            System.out.println(upperBound(values, n));
        }
    }
    public static int upperBound(ArrayList<Long> values, int target){
        int start = 0, end = values.size() - 1;
        int ans = values.size();
        while(start <= end){
            int mid = (start + end) / 2;
            if(values.get(mid) > target){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
}
