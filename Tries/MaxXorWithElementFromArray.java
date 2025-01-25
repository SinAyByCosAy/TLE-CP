//https://www.geeksforgeeks.org/problems/maximum-xor-with-an-element-from-array/1
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxXorWithElementFromArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for(int i = 0; i < q; i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        SolutionMXWEA obj = new SolutionMXWEA();
        System.out.println(Arrays.toString(obj.maximumXor(n, q, arr, queries)));
    }
}
class SolutionMXWEA{
    int[] maximumXor(int n, int q, int[] arr, int[][] queries){
        TrieMXWEA trie = new TrieMXWEA();
        Arrays.sort(arr); //NlogN
        Queries[] query = new Queries[q];
        for(int i = 0; i < q; i++) query[i] = new Queries(queries[i][0], queries[i][1], i);
        Arrays.sort(query, (a, b) -> Integer.compare(a.limit, b.limit)); //QlogQ

        int[] result = new int[q];
        int ptr = 0;
        for(int i = 0; i < q; i++){
            int x = query[i].val;
            int limit = query[i].limit;
            int idx = query[i].idx;
            while(ptr < n && arr[ptr] <= limit){ //inserting elements only <= limit in trie
                trie.insert(arr[ptr]);
                ptr++;
            }
            if(ptr != 0) result[idx] = trie.getMaxXor(x);
            else result[idx] = -1;
        }
        return result;
    }
}
class TrieMXWEA{
    ArrayList<NodeMXWEA> nodes;
    TrieMXWEA(){
        nodes = new ArrayList<>(Arrays.asList(new NodeMXWEA()));
    }
    void insert(int n){
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (n & (1 << i)) > 0 ? 1 : 0;
            if(!nodes.get(idx).contains(bit)){
                nodes.get(idx).insert(bit, nodes.size());
                nodes.add(new NodeMXWEA());
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
            if(bit != desiredBit) xor |= (1 << i);
            idx = nodes.get(idx).getChildAddr(desiredBit);
        }
        return xor;
    }
}
class NodeMXWEA{
    int[] childRef;
    NodeMXWEA(){
        childRef = new int[2];
        Arrays.fill(childRef, -1);
    }
    boolean contains(int bit){
        if(childRef[bit] == -1) return false;
        return true;
    }
    void insert(int bit, int addr){
        childRef[bit] = addr;
    }
    int getChildAddr(int bit){
        return childRef[bit];
    }
}
class Queries{
    int val, limit, idx;
    Queries(int val, int limit, int idx){
        this.val = val;
        this.limit = limit;
        this.idx = idx;
    }
}
//TC: O(Max(NlogN, QlogQ))
//SC: O(Q + N)