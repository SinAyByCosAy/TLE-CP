//https://leetcode.com/problems/map-sum-pairs/description/
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MapSum {
    ArrayList<NodeSum> nodes;
    HashMap<String, Integer> map;
    public MapSum() {
        nodes = new ArrayList<>(Arrays.asList(new NodeSum()));
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int rem = 0; //to track the value to subtract in case of duplicates
        if(map.containsKey(key)) rem = map.get(key);
        map.put(key, val);
        int idx = 0;
        for(int i = 0; i < key.length(); i++){
            char ch = key.charAt(i);
            if(!nodes.get(idx).contains(ch)){
                nodes.get(idx).insert(ch, nodes.size());
                nodes.add(new NodeSum());
            }
            idx = nodes.get(idx).getChildAddr(ch);
            nodes.get(idx).sum(val, rem);
        }
        nodes.get(idx).markSpecial();
    }

    public int sum(String prefix) {
        int idx = 0;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!nodes.get(idx).contains(ch)) return 0;
            idx = nodes.get(idx).getChildAddr(ch);
        }
        return nodes.get(idx).getSum();
    }
}
class NodeSum{
    int[] childRef;
    boolean special;
    int sum;
    NodeSum(){
        childRef = new int[26];
        Arrays.fill(childRef, -1);
        special = false;
        sum = 0;
    }
    void insert(char ch, int addr){
        childRef[ch - 'a'] = addr;
    }
    boolean contains(char ch){
        if(childRef[ch - 'a'] == -1) return false;
        return true;
    }
    int getChildAddr(char ch){
        return childRef[ch - 'a'];
    }
    void markSpecial(){
        special = true;
    }
    void sum(int val, int rem){
        sum += val - rem;
    }
    int getSum(){
        return sum;
    }
}
//TC: O(N.X)
//SC: O(N.X.26)