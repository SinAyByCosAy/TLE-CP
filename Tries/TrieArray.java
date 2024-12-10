//Trie : array based implementation
package DPBootcamp.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TrieArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char q;
        do{
            System.out.println("1: enter string, 2: search string, 3: delete string, 4: count prefix");
            int k = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter string");
            String s = sc.nextLine();
            switch(k){
                case 1: Trie.insert(s); break;
                case 2: System.out.println(Trie.search(s)); break;
                case 3: System.out.println(Trie.delete(s)); break;
                case 4: System.out.println(Trie.countPrefix(s)); break;
            }
            System.out.println("Press y to continue");
            q = sc.nextLine().charAt(0);
        }while(q == 'y');
    }
}
class Node{
    int[] childRef;
    boolean isSpecial;
    int stringsCount;
    Node(){
        childRef = new int[26];
        isSpecial = false;
        stringsCount = 0;
        for(int i = 0; i < 26; i++)
            childRef[i] = -1;
    }
    boolean checkSpecial(){
        return isSpecial;
    }
    void markSpecial(){
        isSpecial = true;
    }
    void unmarkSpecial(){
        isSpecial = false;
    }
    boolean contains(char ch){
        if(childRef[ch - 'a'] == -1) return false;
        return true;
    }
    void insertChild(char ch, int addr){
        childRef[ch - 'a'] = addr;
    }
    int getPrefixCount(){
        return stringsCount;
    }
    void increaseCount(){
        stringsCount++;
    }
    void decreaseCount(){
        stringsCount--;
    }
    int getChildAddr(char ch){
        return childRef[ch - 'a'];
    }
    boolean canDeleteNode(){
        boolean flag = isSpecial;
        for(int i = 0; i < 26; i++)
            if(childRef[i] != - 1) return false;
        return !flag;
    }
    void deleteChildNode(char ch){
        childRef[ch - 'a'] = -1;
    }
}
class Trie {
    //global list of nodes stored in sequential indexes
    static ArrayList<Node> nodesList = new ArrayList<>(Arrays.asList(new Node()));
    public static void insert(String s){
        int idx = 0; //root node is present at idx 0, start of trie
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!nodesList.get(idx).contains(ch)) { //child is not present
                Node child = new Node(); //new node
                nodesList.get(idx).insertChild(ch, nodesList.size()); //insert it's position in current node/parent
                nodesList.add(child); //add in global list
            }
            idx = nodesList.get(idx).getChildAddr(ch); //move to child node
            nodesList.get(idx).increaseCount(); //increase count
        }
        nodesList.get(idx).markSpecial(); //end of string indicator
    }
    public static boolean search(String s){
        int idx = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!nodesList.get(idx).contains(ch)) return false;
            idx = nodesList.get(idx).getChildAddr(ch);
        }
        return nodesList.get(idx).checkSpecial(); //is end of string marked?
    }
    public static int countPrefix(String s){
        int idx = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!nodesList.get(idx).contains(ch)) return -1;
            idx = nodesList.get(idx).getChildAddr(ch);
        }
        return nodesList.get(idx).getPrefixCount();
    }
    public static boolean delete(String s){
        if(!search(s)) return false;
        deleteChildRecursively(s, 0, 0);
        return true;
    }
    static public boolean deleteChildRecursively(String s, int addr, int stringIdx){
        if(stringIdx == s.length()){ //standing at last node(child)
            nodesList.get(addr).unmarkSpecial(); //unmark it
            nodesList.get(addr).decreaseCount(); //decrease it's count
            return nodesList.get(addr).canDeleteNode(); //check if it can be deleted
        }

        if(deleteRecursively(s, nodesList.get(addr).getChildAddr(s.charAt(stringIdx)), stringIdx + 1)){
            nodesList.get(addr).deleteChildNode(s.charAt(stringIdx)); //delete child node
        }
        nodesList.get(addr).decreaseCount(); //decrease count of current node
        return nodesList.get(addr).canDeleteNode(); //tell parent if current node can be deleted or not
    }
}
