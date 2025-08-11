package DPBootcamp.Graphs;

import java.util.Scanner;

public class SegmentTree {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 8;
        int[] arr = {10, 20, 5, 4, 3, 2, 7, 11};
        int[] sgt = new int[2 * n];
        buildTree(1, 0, n - 1, arr, sgt);
    }
    //idx: current segment index
    private static void buildTree(int idx, int start, int end, int[] arr, int[] sgt){
        //leaf node, no need to divide the node further
        if(start == end){
            sgt[idx] = arr[start];
            return;
        }
        //internal node
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, arr, sgt); //build the left side
        buildTree(2 * idx + 1, mid + 1, end, arr, sgt); //build the right side
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1]; //get the sum from left and right
    }
    private static void update(int idx, int start, int end, int searchIdx, int val, int[] sgt){
        if(start == end){//at the leaf node, we'll be exactly at our target index
            sgt[idx] = val;
            return;
            //we can also pass in the original array and update it as well, if needed
        }
        int mid = (start + end) / 2;
        if(searchIdx <= mid) update(2 * idx, start, mid, searchIdx, val, sgt);
        else update(2 * idx + 1, mid + 1, end, searchIdx, val, sgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    private static int query(int idx, int start, int end, int qL, int qR, int[] sgt){
        if(qL > end || qR < start) return 0; //completely irrelevant
        else if(start >= qL && end <= qR) return sgt[idx]; //completely relevant, current range is completely inside query range
        else{//partially relevant
            int mid = (start + end) / 2;
            return query(2 * idx, start, mid, qL, qR, sgt) + query(2 * idx + 1, mid + 1, end, qL, qR, sgt);
        }
    }
}
//TC: O(N) for building, O(logN) for query