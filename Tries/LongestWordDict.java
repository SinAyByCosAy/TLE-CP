//https://leetcode.com/problems/longest-word-in-dictionary/description/
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// for a string to be a candidate, the input should contain all it's prefixes.
// Hence, all nodes in Trie for a word should be marked special. If not, word it not a candidate
public class LongestWordDict {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] words = new String[n];
        for(int i = 0; i < n; i++) words[i] = sc.nextLine();
        System.out.println(longestWord(words));
    }
    public static String longestWord(String[] words){
        TrieLW trieLW = new TrieLW();
        for(String ele : words)
            trieLW.insert(ele);

        String res = "";
        for(String ele : words){
            boolean check = trieLW.wordMadeOfPrefix(ele);
            if(check && ele.length() > res.length()) res = ele;
            if(check && ele.length() == res.length() && ele.compareTo(res) < 0) res = ele;
        }
        return res;
    }
}
class TrieLW{
    ArrayList<NodeLW> nodes;
    TrieLW(){
        nodes = new ArrayList<>(Arrays.asList(new NodeLW()));
    }
    void insert(String s){
        int n = s.length();
        int idx = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(!nodes.get(idx).contains(ch)) {
                nodes.get(idx).insert(ch, nodes.size());
                nodes.add(new NodeLW());
            }
            idx = nodes.get(idx).getChildAddr(ch);
        }
        nodes.get(idx).markSpecial();
    }
    boolean wordMadeOfPrefix(String s){
        int idx = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            idx = nodes.get(idx).getChildAddr(ch);
            if(!nodes.get(idx).isSpecial()) return false;
        }
        return true;
    }
}
class NodeLW{
    int[] childRef;
    boolean special;
    NodeLW(){
        childRef = new int[26];
        Arrays.fill(childRef, - 1);
        special = false;
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
    boolean isSpecial(){
        return special;
    }
}
//TC: O(N . X)