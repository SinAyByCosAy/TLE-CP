package DPBootcamp.Tries;

import java.util.HashMap;
import java.util.Scanner;

public class TrieHashMap {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char q;
        do {
            System.out.println("Enter 1: to input string, 2: to search string, 3: delete a string, 4: count #prefix matches");
            int k = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter String");
            String s = sc.nextLine();
            if (k == 1) TrieHM.insertString(s);
            else if(k == 2) System.out.println(TrieHM.searchString(s));
            else if(k == 3) System.out.println(TrieHM.deleteString(s));
            else if(k == 4) System.out.println(TrieHM.countPrefixMatch(s));
            System.out.println("press y to continue");
            q = sc.nextLine().charAt(0);
        }while(q == 'y');
    }
}
class NodeHM{
    HashMap<Character, NodeHM> childRef;
    boolean isSpecial;
    int stringsCount;
    NodeHM(){
        childRef = new HashMap<>();
        isSpecial = false;
        stringsCount = 0;
    }
    void markSpecial(){
        isSpecial = true;
    }
    void unmarkSpecial(){
        isSpecial = false;
    }
    void increaseCount(){
        stringsCount++;
    }
    void decreaseCount(){
        stringsCount--;
    }
    int getStringsCount(){
        return stringsCount;
    }
    void insert(char ch, NodeHM node){
        childRef.put(ch, node);
    }
    boolean contains(char ch){
        if(childRef.containsKey(ch)) return true;
        return false;
    }
    NodeHM moveToChild(char ch){
        return childRef.get(ch);
    }
    boolean canDeleteNode(){
        if(!isSpecial && childRef.isEmpty()) return true;
        return false;
    }
    void deleteChildNode(char ch){
        childRef.remove(ch);
    }
}
class TrieHM{
    static NodeHM root = new NodeHM(); //start of trie, dummy node
    public static void insertString(String s){
        NodeHM head = root;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i); //next character
            if(!head.contains(ch)){//if next character doesn't exist in current branch, we create it's node
                NodeHM child = new NodeHM(); //new node
                head.insert(ch, child); //link it to current head
            }
            head = head.moveToChild(ch); //we move to the node of the next character
            head.increaseCount(); //increase it's count to maintain count of number of strings with same prefix
        }
        head.markSpecial(); //last node gets marked as special indicating end of string
    }
    public static boolean searchString(String s){
        NodeHM head = root;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!head.contains(ch)) return false; //string doesn't exist
            head = head.moveToChild(ch);
        }
        return head.isSpecial;
    }
    public static boolean deleteString(String s){
        if(!searchString(s)) return false; //if string is not present, can't delete it
        //to delete iterate recursively to the end of the string, mark the last node as not special and check if node can be deleted
        NodeHM head = root;
        deleteNodeRecursively(s, head, 0);
        return true;
    }
    private static boolean deleteNodeRecursively(String s, NodeHM head, int index){
        if(index == s.length()){//reached the last child/char
            head.unmarkSpecial(); //need to unmark it
            head.decreaseCount(); //need to decrease it's count
            return head.canDeleteNode(); //and return if it can be deleted or not
        }
        if(deleteNodeRecursively(s, head.moveToChild(s.charAt(index)), index + 1)){
            head.deleteChildNode(s.charAt(index)); //child can be deleted
        }
        head.decreaseCount(); //even if I can't remove the node, count of it needs to be reduced
        return head.canDeleteNode(); //tell parent if current node can be deleted or not
    }
    public static int countPrefixMatch(String s){
        NodeHM head = root;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!head.contains(ch)) return -1; //if prefix doesn't exist
            head = head.moveToChild(ch);
        }
        return head.getStringsCount(); //get the count when we reach the end of the prefix
    }
}