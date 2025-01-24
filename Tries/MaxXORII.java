//https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxXORII {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        SolutionMX obj = new SolutionMX();
        System.out.println(obj.findMaximumXOR(arr));
    }
}
class SolutionMX{
    public int findMaximumXOR(int[] nums){
        TrieMX trie = new TrieMX();
        for(int ele : nums) trie.insert(ele);

        int maxXor = 0;
        for(int ele : nums) maxXor = Math.max(maxXor, trie.findMaxXor(ele));
        return maxXor;
    }
}
class TrieMX{
    ArrayList<NodeMX> nodes;
    TrieMX(){
        nodes = new ArrayList<>(Arrays.asList(new NodeMX()));
    }
    void insert(int n){
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (n & (1 << i)) > 0 ? 1 : 0;
            if(!nodes.get(idx).contains(bit)){
                nodes.get(idx).insert(bit, nodes.size());
                nodes.add(new NodeMX());
            }
            idx = nodes.get(idx).getChildAddr(bit);
        }
    }
    int findMaxXor(int x){
        int idx = 0;
        int xor = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            int desiredBit = bit ^ 1;
            if(!nodes.get(idx).contains(desiredBit)) desiredBit = bit;
            if(bit != desiredBit) xor |= (1 << i);
            idx = nodes.get(idx).getChildAddr(desiredBit);
        }
        return xor;
    }
}
class NodeMX{
    int[] childRef;
    NodeMX(){
        childRef = new int[2];
        Arrays.fill(childRef, - 1);
    }
    boolean contains(int x){
        if(childRef[x] == -1) return false;
        return true;
    }
    void insert(int x, int addr){
        childRef[x] = addr;
    }
    int getChildAddr(int x){
        return childRef[x];
    }
}
//TC: O(32.N)