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
//        System.out.println(findDuplicateArrayModify(arr));
        System.out.println(findDuplicateCycleNode(arr));
    }

    //Approach: Consider elements as addresses like in LL. Duplicate element will create a cycle. Find loop node, Floyd's cycle detection algo.
    public static int findDuplicateCycleNode(int[] nums){
        //idx: [0, 1, 2, 3, 4, 5, 6, 7, 8]
        //arr: [4, 6, 3, 7, 2, 5, 8, 1, 2]
        //      0->4->2->3->7->1->6->8--
        //            ^                 |
        //            |-----------------
        int slow = nums[0]; //slow = 4
        int fast = nums[nums[0]]; //fast = 2
        while(slow != fast){//finding intersection node
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(slow != fast){//reaching the loop node
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    //Approach: Sending elements to their corresponding index, one index will end up having duplicate
    public static int findDuplicateArrayModify(int[] nums){
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
