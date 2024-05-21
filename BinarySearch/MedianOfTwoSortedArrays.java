package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int[] nums1 = new int[n1];
        int[] nums2 = new int[n2];
        for(int i=0;i<n1;i++){
            nums1[i] = sc.nextInt();
        }
        for(int i=0;i<n2;i++){
            nums2[i] = sc.nextInt();
        }
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;
        if(n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        int start = 0, end = n1;//here 0 means, 0 elements before cut. n1 means, n1 elements before cut.
        int l1 = 0, l2 = 0, r1 = 0, r2 = 0;
        while(start <= end){
            int cut1 = (start + end) / 2;
            int cut2 = (n1 + n2) / 2 - cut1;

            l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];//taking one element before cut
            l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1];
            r1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];//taking element after cut, 0 based indexing
            r2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];

            if(l1 > r2){
                end = cut1 - 1; // cut left
            }else if(l2 > r1){
                start = cut1 + 1; // cut right
            }else{
                break;
            }
        }
        return (n1+n2)%2==0 ? (Math.max(l1,l2)+Math.min(r1,r2))/2.0 : Math.min(r1, r2);
    }
}
