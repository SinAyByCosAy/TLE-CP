//https://www.geeksforgeeks.org/problems/phone-directory4628/1
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PhoneDirectory {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] contacts = new String[n];
        for(int i = 0; i < n; i++) contacts[i] = sc.nextLine();
        String s = sc.nextLine();
        Solution obj = new Solution();
        System.out.println(obj.displayContacts(n, contacts, s));
    }
}
class Solution{
    public ArrayList<ArrayList<String>> displayContacts(int n, String[] contacts, String s){
        Arrays.sort(contacts); //O(NlogN . X), X = length of the longest string(for ch by ch comparison)

        TriePD trie = new TriePD();
        trie.insert(contacts[0], 0);
        for(int i = 1; i < n; i++) {
            if(!contacts[i].equals(contacts[i - 1]))
                trie.insert(contacts[i], i);
        }

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        results = trie.autoComplete(s, contacts);
        return results;
    }
}
class TriePD{
    ArrayList<NodePD> nodes;
    TriePD(){
        nodes = new ArrayList<>(Arrays.asList(new NodePD()));
    }
    void insert(String s, int inputIndex){
        int idx = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!nodes.get(idx).hasChild(ch)){
                nodes.get(idx).insert(ch, nodes.size());
                nodes.add(new NodePD());
            }
            idx = nodes.get(idx).getChildAddr(ch);
            nodes.get(idx).setIdx(inputIndex);
        }
        nodes.get(idx).markSpecial();
    }
    ArrayList<ArrayList<String>> autoComplete(String query, String[] contacts){//O(Q.N)
        int i, idx = 0;
        boolean flag = true;
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for(i = 0; i < query.length(); i++){
            char ch = query.charAt(i);
            if(!nodes.get(idx).hasChild(ch)) {
                flag = false;
                break;
            }
            idx = nodes.get(idx).getChildAddr(ch);
            ArrayList<String> prefixRes = new ArrayList<>();
            for(int ele : nodes.get(idx).idx) prefixRes.add(contacts[ele]);
            results.add(prefixRes);
        }
        if(!flag){
            while(i++ < query.length()) results.add(new ArrayList<>(Arrays.asList("0")));
        }
        return results;
    }
}
class NodePD{
    int[] childRef;
    boolean special;
    ArrayList<Integer> idx;
    NodePD(){
        childRef = new int[26];
        Arrays.fill(childRef, -1);
        special = false;
        idx = new ArrayList<>();
    }
    void insert(char ch, int addr){
        childRef[ch - 'a'] = addr;
    }
    boolean hasChild(char ch){
        if(childRef[ch - 'a'] == -1) return false;
        return true;
    }
    void setIdx(int inputIndex){
        idx.add(inputIndex);
    }
    void markSpecial(){
        special = true;
    }
    boolean checkSpecial(){
        return special;
    }
    int getChildAddr(char ch){
        return childRef[ch - 'a'];
    }
}
//TC: (NlogN . X + Q . N)