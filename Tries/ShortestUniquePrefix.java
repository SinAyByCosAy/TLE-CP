//https://www.geeksforgeeks.org/problems/shortest-unique-prefix-for-every-word/1
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ShortestUniquePrefix {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        sc.nextLine();
        for(int i = 0; i < n; i++) arr[i] = sc.nextLine();
        SolutionPre obj = new SolutionPre();
        System.out.println(Arrays.toString(obj.findPrefixes(arr, n)));
    }
}
class SolutionPre{
    public String[] findPrefixes(String[] arr, int n){
        TriePre triePre = new TriePre();
        for(String s : arr) triePre.insert(s); //O(N . X)

        String[] result = new String[n];
        for(int i = 0; i < n; i++) //O(N . X)
            result[i] = triePre.getPrefixes(arr[i]);
        return result;
    }
}
class TriePre{
    ArrayList<NodePre> nodes;
    TriePre(){
        nodes = new ArrayList<>(Arrays.asList(new NodePre()));
    }
    void insert(String s){
        int idx = 0;
        for(int i =0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!nodes.get(idx).contains(ch)){
                nodes.get(idx).insert(ch, nodes.size());
                nodes.add(new NodePre());
            }
            idx = nodes.get(idx).getChildAddr(ch);
            nodes.get(idx).increaseCount();//keep track of prefix count
        }
        nodes.get(idx).markSpecial();
    }
    String getPrefixes(String s){
        int idx = 0;
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            res.append(ch);
            idx = nodes.get(idx).getChildAddr(ch);
            if(nodes.get(idx).getCount() == 1) return res.toString(); //found unique point
        }
        return "";
    }
}
class NodePre{
    int[] childRef;
    boolean special;
    int prefixCount;
    NodePre(){
        childRef = new int[26];
        Arrays.fill(childRef, -1);
        special = false;
        prefixCount = 0;
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
    void increaseCount(){
        prefixCount++;
    }
    int getCount(){
        return prefixCount;
    }
    void markSpecial(){
        special = true;
    }
}
//TC: O(N . X)