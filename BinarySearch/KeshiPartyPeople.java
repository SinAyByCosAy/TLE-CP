//https://codeforces.com/problemset/problem/1610/C
package DPBootcamp.BinarySearch;

import java.util.Scanner;
public class KeshiPartyPeople {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for(int i=0;i<n;i++){
                a[i] = sc.nextInt();
                b[i] = sc.nextInt();
            }

            int start = 1, end = n;
            int ans = 1;
            while(start <= end){ //binary search on answer(X: number of people that can be allowed)
                int mid = (start + end) / 2;
                if(check(a, b, mid)){ //if current X is possible, look for a bigger X
                    ans = mid;
                    start = mid + 1;
                }else{ //look for a smaller X
                    end = mid - 1;
                }
            }
            System.out.println(ans);
        }
    }
    public static boolean check(int[] a, int[] b, int x){
        //a person can be invited if he allows:
        //the remaining richer(it just means #elements ahead) people
        //the already accepted poorer(it just means #elements behind) people
        int richCount = x - 1;
        int poorCount = 0;
        int n = a.length;
        for(int i=0; i<n; i++){
            if(a[i] >= richCount && b[i] >= poorCount){
                richCount--;
                poorCount++;
            }
            if(poorCount == x)
                break;
        }
        if(poorCount < x)
            return false;
        return true;
    }
}
