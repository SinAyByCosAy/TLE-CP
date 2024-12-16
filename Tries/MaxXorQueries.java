//given N numbers and Q queries
//query[i] : [xi, mi]
//we have to find the max xor of xi with numbers not greater than mi
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MaxXorQueries {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int q = sc.nextInt();
        ArrayList<InputInfo> queries = new ArrayList<>();
        for(int i = 0; i < q; i++)
            queries.add(new InputInfo(sc.nextInt(), sc.nextInt(), i));
        Collections.sort(queries, (a, b) -> Integer.compare(a.m, b.m));
        Arrays.sort(arr);

        int[] result = new int[q];
        int idx = 0;
        for(int i = 0; i < q; i++){
            int limit = queries.get(i).m;
            while(idx < n && arr[idx] <= limit){ //inserting only values <= limit in trie
                MaxXorQueriesTrie.insert(arr[idx]);
                idx++;
            }
            if(idx == 0) result[queries.get(i).idx] = -1; //if no value <= limit, -1
            else result[queries.get(i).idx] = MaxXorQueriesTrie.getMaxXor(queries.get(i).x);
        }
        for(int ele : result)
            System.out.println(ele);
    }
}
class MaxXorNode{
    int[] childRef;
    MaxXorNode(){
        childRef = new int[2];
        childRef[0] = childRef[1] = -1;
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
class MaxXorQueriesTrie{
    static ArrayList<MaxXorNode> nodes = new ArrayList<>(Arrays.asList(new MaxXorNode()));
    public static void insert(int x){
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            if(!nodes.get(idx).contains(bit)){
                MaxXorNode child = new MaxXorNode();
                nodes.get(idx).insertChild(bit, nodes.size());
                nodes.add(child);
            }
            idx = nodes.get(idx).getChildAddr(bit);
        }
    }
    public static int getMaxXor(int x){
        int res = 0;
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            int reqBit = bit ^ 1;
            if(!nodes.get(idx).contains(reqBit)) reqBit = bit;
            if(reqBit != bit) res |= (1 << i);
            idx = nodes.get(idx).getChildAddr(reqBit);
        }
        return res;
    }
}
class InputInfo{
    int x, m, idx;
    InputInfo(int x, int m, int idx){
        this.x = x;
        this.m = m;
        this.idx = idx;
    }
}