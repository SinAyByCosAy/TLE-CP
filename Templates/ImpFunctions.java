package DPBootcamp.Templates;

import java.util.*;

public class ImpFunctions {

    //Random value generator from [a, b]
    public static int rand(int a, int b){
        return (int) ((Math.random() * (b - a + 1)) + a);
    }

    //gcd
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    //comparator
    public static void sortList(List<Integer> arr){
        Collections.sort(arr, (a, b) -> Integer.compare(a, b));
    }

    public static void sortArray(Integer[] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(a, b)); //requires an object type
    }

    //hashmap iterator
    public static void hashMapIterator(HashMap<Integer, Integer> hm){
        for (Map.Entry<Integer, Integer> ele : hm.entrySet()) {
            System.out.println(ele.getKey() + " " + ele.getValue());
        }
    }


    //A^N, Binary Exponentiation
    public static long binaryExpo(int a, int n){
        long res = 1;
        while(n >  0){
            if((n & 1) == 1){
                res *= 1l * a; //res %= mod
            }
            a *= a; //a %= mod
            n >>= 1;
        }
        return res;
    }


    //Modulo division when mod value is prime
    //(x / y) % mod = [x * y ^ (mod - 2)] % mod
    static int mod = (int)1e9 + 7;
    public static int modMul(int x, int y, int m){
        return (int)((x * 1l * y) % m);
    }

    //multiplicative modulo inverse under a prime: (x ^ (m-2)) % m
    public static int mmInvPrime(int x, int m){
        int y = m - 2;
        int ans = 1;
        while(y > 0){
            if((y & 1) == 1) ans = modMul(ans, x, m);
            x = modMul(x, x, m);
            y = y >> 1;
        }
        return ans;
    }
    public static int divide(int x, int y){
        return modMul(x, mmInvPrime(y, mod), mod);
    }


    //Binomial Coefficient under mod with pre-computation

    static int[] factorial = getFactorial((int)1e6, mod); //pre-computed factorials
    static int[] inverseFactorial = getInverseFactorial((int)1e6, factorial, mod); //pre-computed inverse factorials

    public static int modNCR(int n, int r, int mod){
        return modMul(modMul(factorial[n], inverseFactorial[r], mod), inverseFactorial[n - r], mod);
    }
    public static int[] getFactorial(int limit, int mod){
        int[] factorial = new int[limit + 1];
        factorial[0] = 1;
        for(int i = 1; i <= limit; i++){ //O(N)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        }
        return factorial;
    }
    public static int[] getInverseFactorial(int limit, int[] factorial, int mod){
        int[] inverseFactorial = new int[limit + 1];
        inverseFactorial[limit] = mmInvPrime(factorial[limit], mod); //O(log m)
        for(int i = limit - 1; i >= 0; i--){ //O(N)
            inverseFactorial[i] = modMul(inverseFactorial[i + 1], i + 1, mod);
        }
        return inverseFactorial;
    }

    //Binomial coefficient w/o mod, nCr
    //send nCr or nC(n-r) based on whichever is smaller
    public static int nCr(int n, int r){
        long ans = 1;
        for(int i = 0; i < r; i++){
            ans *= (n - i); //numerator : n * (n - 1) * (n - 2) .... (n - r + 1)
            ans /= (i + 1); //denominator : 1 * 2 * 3 .... r
        }
        return (int) ans;
    }


    //Euler totient value
    public static int phi(int n){//can be also done using SPF
        //TC: O(sqrt(N))
        int result = n;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                result -= result / i;
                while(n % i == 0)
                    n /= i;
            }
        }
        if(n > 1)
            result -= result / n;

        return result;
    }

    public static int[] phiOneToN(int n){
        //similar to sieve or SPF construction logic, TC: O(N * log(logN)) ~ O(N)
        int[] phiValues = new int[n + 1];
        for(int i = 1; i <= n; i++)
            phiValues[i] = i;

        for(int i = 2; i <= n; i++){
            if(phiValues[i] == i){//found a prime
                for(int j = i; j <= n; j += i)//contribution to all the multiples of prime
                    phiValues[j] -= phiValues[j] / i;
            }
        }
        return phiValues;
    }
}

//STRING HASHING


//  Hash a = new Hash(s1);
//  Hash b = new Hash(s2);
//  Hashes s1Hash = a.get(l1, r1); ---> requires (l,r) index for string hash, access via s1Hash.hash1, s1Hash.hash2
//  Hashes s2Hash = b.get(l2, r2);

//First calculates Hash of string in O(N) and then we can fetch hash of any substring in O(1)
class Hash{//0-based indexing
    int base1 = 7919;
    int base2 = 4793;
    int mod = (int) 1e9 + 7;

    ArrayList<Hashes> hashes = new ArrayList<>();//maintaining hashes of prefixes ending at every index
    ArrayList<Pows> pows = new ArrayList<>(); //maintaining powers to fetch them while calculating substring hashes

    Hash(String s){
        int hash1 = 0, hash2 = 0; //starting points
        int pow1 = 1, pow2 = 1; //power 0
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            hash1 = (modMul(hash1, base1) + (ch - 'a')) % mod;
            hash2 = (modMul(hash2, base2) + (ch - 'a')) % mod;
            hashes.add(new Hashes(hash1, hash2));

            pows.add(new Pows(pow1, pow2));
            pow1 = modMul(pow1, base1);
            pow2 = modMul(pow2, base2);
        }
    }
    Hashes get(int l, int r){
        //l, r in 0-based indexing
        long val1, val2;
        if(l == 0) {
            val1 = hashes.get(r).hash1;
            val2 = hashes.get(r).hash2;
        }else{
            val1 = (hashes.get(r).hash1 - modMul(pows.get(r - l + 1).pow1, hashes.get(l - 1).hash1) + mod) % mod;
            val2 = (hashes.get(r).hash2 - modMul(pows.get(r - l + 1).pow2, hashes.get(l - 1).hash2) + mod) % mod;
        }
        return new Hashes((int) val1, (int) val2);
    }
    int modMul(int x, int y){
        return (int)((x * 1l * y) % mod);
    }
}
class Hashes{
    int hash1, hash2;
    Hashes(int hash1, int hash2){
        this.hash1 = hash1;
        this.hash2 = hash2;
    }
}
class Pows{
    int pow1, pow2;
    Pows(int pow1, int pow2){
        this.pow1 = pow1;
        this.pow2 = pow2;
    }
}




//CHARACTER TRIE
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
                nodesList.get(idx).insertChild(ch, nodesList.size()); //insert it's position in current node/parent
                nodesList.add(new Node()); //add in global list
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

        if(deleteChildRecursively(s, nodesList.get(addr).getChildAddr(s.charAt(stringIdx)), stringIdx + 1)){
            nodesList.get(addr).deleteChildNode(s.charAt(stringIdx)); //delete child node
        }
        nodesList.get(addr).decreaseCount(); //decrease count of current node
        return nodesList.get(addr).canDeleteNode(); //tell parent if current node can be deleted or not
    }
}



//BINARY TRIE
class BNode{
    int[] childRef;
    BNode(){
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
class BTrie{
    static ArrayList<BNode> nodesList = new ArrayList<>(Arrays.asList(new BNode()));
    public static void insert(int x){
        int idx = 0;
        for (int j = 31; j >= 0; j--) {//iterating from MSB side
            int bit = (x & (1 << j)) > 0 ? 1 : 0; //checking if current bit is set or not
            if (!nodesList.get(idx).contains(bit)) {//if current bit is not inserted
                nodesList.get(idx).insertChild(bit, nodesList.size());
                nodesList.add(new BNode());
            }
            idx = nodesList.get(idx).getChildAddr(bit);//move to the next bit
        }
    }
    public static int getMaxXor(int x){
        int res = 0;
        int idx = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (x & (1 << i)) > 0 ? 1 : 0;
            int reqBit = bit ^ 1;
            if(!nodesList.get(idx).contains(reqBit)) reqBit = bit;
            if(reqBit != bit) res |= (1 << i);
            idx = nodesList.get(idx).getChildAddr(reqBit);
        }
        return res;
    }
}


//Disjoint Set Union
class DSU{
    private static int[] parent, rank;
    public static void init(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        Arrays.fill(rank, 0);
        for(int i = 1; i <= n; i++) parent[i] = i;//self parent
    }
    public static int findSet(int x){//we are looking for root
        if(parent[x] == x) return x; //self parent means node is root
        return parent[x] = findSet(parent[x]); //otherwise we get the root from parent and memoize
    }
    public static void unionSet(int x, int y){
        int a = findSet(x); //find root
        int b = findSet(y);
        if(a != b){//edge doesn't exist, part of different components
            if(rank[a] < rank[b]){ int t = a; a = b; b = t; }//we make sure to merge b -> a
            parent[b] = a; //merged
            if(rank[a] == rank[b]) rank[a]++; //if rank is same, we need to increase
        }
    }
}
//TC per query: O(IAF(N)), IAF - Inverse Ackerman function