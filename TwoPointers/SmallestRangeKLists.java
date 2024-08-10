//https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
package DPBootcamp.TwoPointers;

import java.util.*;

public class SmallestRangeKLists {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n");
        int n = sc.nextInt();
        List<List<Integer>> nums = new ArrayList<>();
        for(int i = 0; i < n; i++){
            System.out.println("Enter m");
            int m = sc.nextInt();
            System.out.println("Enter list no: "+(i+1));
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < m; j++)
                row.add(sc.nextInt());
            nums.add(row);
        }
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

    public static int[] smallestRange(List<List<Integer>> nums){
        List<Pair> arr = new ArrayList<>();
        //creating a 1-D list
        for(int i = 0; i < nums.size(); i++){
            List<Integer> temp = nums.get(i);
            for(int j = 0; j < temp.size(); j++){
                arr.add(new Pair(temp.get(j), i));
            }
        }
        //sorting
        Collections.sort(arr, (a, b) -> Integer.compare(a.val, b.val));
        int i = 0, j = 0;
        int ans = Integer.MAX_VALUE;
        int[] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();//frequency map to track # elements in respective lists

        while(j < arr.size()){
            //element gets added to the segment
            hm.put(arr.get(j).listID, hm.getOrDefault(arr.get(j).listID, 0) + 1);

            while(i <= j && hm.get(arr.get(i).listID) > 1){//we remove extra elements to make the segment as small as possible. We need 1 from each list
                int del = arr.get(i).listID;
                hm.put(del, hm.get(del) - 1);
                i++;
            }

            if(hm.size() == nums.size()){ //when we have elements from all lists
                int l = arr.get(i).val;
                int r = arr.get(j).val;
                if(r - l < ans){
                    ans = r - l;
                    result[0] = l;
                    result[1] = r;
                }
            }
            j++;
        }
        return result;
    }
}
class Pair{
    //store value and list ID
    int val, listID;
    Pair(int val, int listID){
        this.val = val;
        this.listID = listID;
    }
}
