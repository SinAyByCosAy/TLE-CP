//given N numbers, find max xor of two numbers
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxXOR {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            BTrie.insert(arr[i]);
        }
        System.out.println(getMaxXor(arr, n));
    }
    public static int getMaxXor(int[] arr, int n){
        int maxXor = 0;
        for(int i = 0; i < n; i++){//TC: O(N.32)
            int xor = 0;
            int a = arr[i];
            int idx = 0;//index on Trie's list
            for(int j = 31; j >= 0; j--){
                int bit = (a & (1 << j)) > 0 ? 1 : 0;
                int desiredBit = bit ^ 1;
                if(!BTrie.nodesList.get(idx).contains(desiredBit)){//the alternate bit is not present
                    desiredBit = bit; //so we need to take the same bit itself for xor
                }
                if(desiredBit == (bit ^ 1)) xor += (1 << j); //we need to set the bit in xor if we found an alternate bit
                idx = BTrie.nodesList.get(idx).getChildAddr(desiredBit);
            }
            maxXor = Math.max(maxXor, xor);
        }
        return maxXor;
    }
}
class BNode{
    int[] childRef;
    boolean isSpecial;
    BNode(){
        childRef = new int[2];
        childRef[0] = childRef[1] = -1;
        isSpecial = false;//all numbers reach the end of 32 sized path, so this is not needed
    }
    void markSpecial(){
        isSpecial = true;
    }
    boolean contains(int bit){
        if(childRef[bit] == -1) return false;
        return true;
    }
    void insertChild(int bit, int addr){
        childRef[bit] = addr;
    }
    int getChildAddr(int bit){
        return childRef[bit];
    }
}
class BTrie{
    static ArrayList<BNode> nodesList = new ArrayList<>(Arrays.asList(new BNode()));
    public static void insert(int x){
        int idx = 0;
        for (int j = 31; j >= 0; j--) {//iterating from MSB side
            int bit = (x & (1 << j)) > 0 ? 1 : 0; //checking if current bit is set or not
            if (!nodesList.get(idx).contains(bit)) {//if current bit is not inserted
                BNode child = new BNode();
                nodesList.get(idx).insertChild(bit, nodesList.size());
                nodesList.add(child);
            }
            idx = nodesList.get(idx).getChildAddr(bit);//move to the next bit
        }
        nodesList.get(idx).markSpecial();
    }
}