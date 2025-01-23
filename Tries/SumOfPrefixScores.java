//https://leetcode.com/problems/sum-of-prefix-scores-of-strings/description/
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SumOfPrefixScores {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] words = new String[n];
        for(int i = 0; i < n; i++) words[i] = sc.nextLine();

        SolutionPS obj = new SolutionPS();
        System.out.println(Arrays.toString(obj.sumPrefixScores(words)));
    }
}
class SolutionPS{
    public int[] sumPrefixScores(String[] words){
        int n = words.length;
        TriePS trie = new TriePS();
        for(String word : words)
            trie.insert(word);

        int[] result = new int[n];
        for(int i = 0; i < n; i++)
            result[i] = trie.getPrefixScore(words[i]);
        return result;
    }
}
class TriePS{
    ArrayList<NodePS> nodes;
    TriePS(){
        nodes = new ArrayList<>(Arrays.asList(new NodePS()));
    }
    void insert(String s){
        int n = s.length();
        int idx = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(!nodes.get(idx).contains(ch)){
                nodes.get(idx).insert(ch, nodes.size());
                nodes.add(new NodePS());
            }
            idx = nodes.get(idx).getChildAddr(ch);
            nodes.get(idx).increaseCount();
        }
        nodes.get(idx).markSpecial();
    }
    int getPrefixScore(String s){
        int n = s.length();
        int idx = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            idx = nodes.get(idx).getChildAddr(ch);
            sum += nodes.get(idx).getCount();
        }
        return sum;
    }
}
class NodePS{
    int[] childRef;
    boolean special;
    int count;
    NodePS(){
        childRef = new int[26];
        Arrays.fill(childRef, -1);
        special = false;
        count = 0;
    }
    boolean contains(char ch){
        if(childRef[ch - 'a'] == -1) return false;
        return true;
    }
    void insert(char ch, int addr){
        childRef[ch - 'a'] = addr;
    }
    int getChildAddr(char ch){
        return childRef[ch - 'a'];
    }
    void markSpecial(){
        special = true;
    }
    void increaseCount(){
        count ++;
    }
    int getCount(){
        return count;
    }
}
//TC: O(N.X)