//https://codeforces.com/edu/course/2/lesson/9/3/practice/contest/307094/problem/A
package DPBootcamp.TwoPointers;

import java.util.Scanner;
public class LoppedPlaylist {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        int[] arr = new int[n];
        long arraySum = 0;
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            arraySum += arr[i];
        }
        long ans = k / arraySum * n; //#rounds * n
        long targetSum = k % arraySum; //remaining sum
        int i = 0, j = 0;
        long sum = 0;
        long segmentLength = n;
        int pos = 1; //default index position can be any index(1 to n) as entry point can be any song
        while(j < 2 * n - 1){
            sum += arr[j % n];
            while(i <= j && sum - arr[i % n] >= targetSum){
                sum -= arr[i % n];
                i++;
            }
            if(sum >= targetSum){
                if(j - i + 1 < segmentLength){
                    segmentLength = j - i + 1;
                    pos = i % n + 1;
                }
            }
            j++;
        }
        System.out.println(pos + " " + (ans + segmentLength));
    }
}