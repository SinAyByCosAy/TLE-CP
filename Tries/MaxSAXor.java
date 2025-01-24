//https://www.geeksforgeeks.org/problems/maximum-xor-subarray--141631/1
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//logic: find prefix xor array because
//(a3 ^ a4 ^ a5) = (a1 ^ a2 ^ a3 ^ a4 ^ a5) ^ (a1 ^ a2) = PrefixXOR[5] ^ PrefixXOR[2]
//now to find which two values to choose in prefix xor, we want max xor therefore,
//we want two values that give max xor in array(same as MAX XOR question)
public class MaxSAXor {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        SolutionMSX obj = new SolutionMSX();
        System.out.println(obj.maxSubarrayXOR(n, arr));
    }
}
class SolutionMSX{
    int maxSubarrayXOR(int n, int[] arr){
        int[] psXOR = new int[n + 1];
        for(int i = 1; i <= n; i++) psXOR[i] = psXOR[i - 1] ^ arr[i - 1];

        TrieMSX trie = new TrieMSX();
        for(int ele : psXOR) trie.insert(ele);

        int maxSAXOR = 0;
        for(int ele : psXOR) maxSAXOR = Math.max(maxSAXOR, trie.getMaxXor(ele));

        return maxSAXOR;
    }
}
class TrieMSX{
    ArrayList<NodeMSX> nodes;
    TrieMSX(){
        nodes = new ArrayList<>(Arrays.asList(new NodeMSX()));
    }
    void insert(int n){
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (n & (1 << i)) > 0 ? 1 : 0;
            if(!nodes.get(idx).contains(bit)){
                nodes.get(idx).insert(bit, nodes.size());
                nodes.add(new NodeMSX());
            }
            idx = nodes.get(idx).getChildAddr(bit);
        }
    }
    int getMaxXor(int x){
        int idx = 0;
        int xor = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            int desiredBit = bit ^ 1;
            if(!nodes.get(idx).contains(desiredBit)) desiredBit = bit;
            if(desiredBit != bit) xor |= (1 << i);
            idx = nodes.get(idx).getChildAddr(desiredBit);
        }
        return xor;
    }
}
class NodeMSX{
    int[] childRef;
    NodeMSX(){
        childRef = new int[2];
        Arrays.fill(childRef, -1);
    }
    void insert(int bit, int addr){
        childRef[bit] = addr;
    }
    boolean contains(int bit){
        if(childRef[bit] == -1) return false;
        return true;
    }
    int getChildAddr(int bit){
        return childRef[bit];
    }
}
//TC: O(N.32)