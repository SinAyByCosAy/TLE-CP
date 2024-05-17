//https://leetcode.com/problems/find-the-duplicate-number/description/
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class FindDuplicateNumber {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n+1];
        for(int i=0;i<=n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(findDuplicatesArrayModify(arr));
    }
    public static int findDuplicatesArrayModify(int[] nums){
        int n = nums.length;
        for(int i=0;i<=n;i++){
            if(nums[i]-1 == i) //0 based indexing
                continue;
            else if(nums[nums[i]-1] == nums[i]){
                return nums[i];
            }
            else{
                int t = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[t - 1] = t;
                i--;//staying at the current location for further swaps
            }
        }
        return -1;
    }
}
