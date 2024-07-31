//https://codeforces.com/edu/course/2/lesson/9/3/practice/contest/307094/problem/B
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class TotalLength {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 0;
        long sum = 0;
        long length = 0;
        while(j < n){
            sum += arr[j];
            while(i <= j && sum > k){
                sum -= arr[i];
                i++;
            }
            long limit = (j - i + 1);
            length += limit * (limit + 1) / 2; //sum of all lengths in current good segment is summation of 1 to n where n = (j - i + 1)
            j++;
        }
        System.out.println(length);
    }
}