//https://leetcode.com/problems/majority-element/
package DPBootcamp.Combinatorics;

import java.util.Scanner;
//probability of getting the answer wrong in 10 tries is < 0.0001
public class MajorityElement {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        System.out.println(majorityElement(nums));
    }
    public static int majorityElement(int[] nums){
        int n = nums.length;
        int tries = 10; //can go till 8 as well for leetcode
        for(int i = 1; i <= tries; i++){
            int idx = rand(0, n - 1);
            int count = 0;
            for(int j = 0; j < n; j++){
                if(nums[j] == nums[idx])
                    count++;
            }
            if(count > (n / 2)) return nums[idx];
        }
        return -1;
    }
    static int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }
}
//TC: O(N)