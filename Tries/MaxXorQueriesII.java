//given N numbers and Q queries
//query[i] : [xi, mi]
//we have to find the max xor of xi with numbers not greater than mi

//Approach 2: we use left and right child array attributes to figure out the path to choose
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MaxXorQueriesII {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for(int i = 0; i < q; i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        for(int ele : arr)
            MXQTrie.insert(ele);

        for(int[] pair : queries)
            System.out.println(getMaxXor(pair[0], pair[1]));
    }
    public static int getMaxXor(int x, int limit){
        int idx = 0;
        int res = 0;
        if(!((MXQTrie.nodes.get(idx).leftElements.size() > 0 && MXQTrie.nodes.get(idx).leftElements.get(0) <= limit) ||
                (MXQTrie.nodes.get(idx).rightElements.size() > 0 && MXQTrie.nodes.get(idx).rightElements.get(0) <= limit)))
            return -1;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            int reqBit = bit ^ 1;
            if(!MXQTrie.nodes.get(idx).contains(reqBit) || (reqBit == 0 && MXQTrie.nodes.get(idx).leftElements.get(0) > limit) ||
                    (reqBit == 1 && MXQTrie.nodes.get(idx).rightElements.get(0) > limit)) {
                reqBit = bit;
            }
            if(reqBit != bit) res |= (1 << i);
            idx = MXQTrie.nodes.get(idx).getChildAddr(reqBit);
        }
        return res;
    }
}
class MXQNode{
    int[] childRef;
    ArrayList<Integer> leftElements;
    ArrayList<Integer> rightElements;
    MXQNode(){
        childRef = new int[2];
        childRef[0] = childRef[1] = -1;
        leftElements = new ArrayList<>();
        rightElements = new ArrayList<>();
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
    void addLeftElement(int x){
        leftElements.add(x);
    }
    void addRightElement(int x){
        rightElements.add(x);
    }
}
class MXQTrie{
    static ArrayList<MXQNode> nodes = new ArrayList<>(Arrays.asList(new MXQNode()));
    public static void insert(int x){
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            if(!nodes.get(idx).contains(bit)){
                nodes.get(idx).insertChild(bit, nodes.size());
                nodes.add(new MXQNode());
            }
            if(bit == 0) nodes.get(idx).addLeftElement(x);
            else nodes.get(idx).addRightElement(x);
            idx = nodes.get(idx).getChildAddr(bit);
        }
    }
}