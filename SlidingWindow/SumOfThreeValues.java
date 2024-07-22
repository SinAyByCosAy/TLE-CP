//https://cses.fi/problemset/task/1641
package DPBootcamp.SlidingWindow;

import java.util.Arrays;
import java.util.Scanner;

class Pair{
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class SumOfThreeValues {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        Pair[] arr = new Pair[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Pair(sc.nextInt(), i+1);
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a.x, b.x)); //java 8+ version of sorting using custom comparator
        for(int i = 0; i < n; i++){
            int x = target - arr[i].x;
            int p1 = i + 1, p2 = n - 1;
            while(p1 < p2){//two pointer approach to finding the values amounting to the required sum
                int sum = arr[p1].x + arr[p2].x;
                if(sum == x){
                    System.out.println(arr[i].y+" "+arr[p1].y+" "+arr[p2].y);
                    return;
                }else if(sum < x)
                    p1++;
                else
                    p2--;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
//Hashmap approach can be reverse engineered in CP to cause collisions and make O(1) ops -> O(N) ops
//therefore sorting and two pointer is better here
//TC: O(N^2)