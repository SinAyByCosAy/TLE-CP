//https://leetcode.com/problems/majority-element-ii/
package DPBootcamp.Combinatorics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MajorityElementII {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        System.out.println(majorityElement(nums));
    }
    public static List<Integer> majorityElement(int[] nums){
        int n = nums.length;
        List<Integer> result = new ArrayList<Integer>();
        int tries = 20; //can be as low as 14 for leetcode
        for(int i = 1; i <= tries; i++){
            int idx = rand(0, n - 1);
            int count = 0;
            if(result.size() == 1 && result.get(0) == nums[idx]) continue; //don't need to calculate again for already added element
            for(int j = 0; j < n; j++){
                if(nums[j] == nums[idx])
                    count++;
            }
            if(count > (n / 3)){
                result.add(nums[idx]);
                if(result.size() == 2) return result;
            }
        }
        return result;
    }
    static int rand(int a, int b){
        return (int) ((Math.random() * (b - a + 1)) + a);
    }
}
//TC: O(N)