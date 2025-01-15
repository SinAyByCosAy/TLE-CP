package DPBootcamp.StringHashing;

import java.util.ArrayList;
import java.util.Scanner;

public class StringHashingTemplate {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int l1 = sc.nextInt();
        int r1 = sc.nextInt();
        int l2 = sc.nextInt();
        int r2 = sc.nextInt();

        //need these objects to get the hash values
        Hash a = new Hash(s1);
        Hash b = new Hash(s2);
        Hashes s1Hash = a.get(l1, r1);
        Hashes s2Hash = b.get(l2, r2);


        if(s1Hash.hash1 == s2Hash.hash1 && s1Hash.hash2 == s2Hash.hash2)
            System.out.println("Same");
        else
            System.out.println("Not Same");
    }
}

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
            hash1 = (modMul(hash1, base1) + (ch - 'a' + 1)) % mod;
            hash2 = (modMul(hash2, base2) + (ch - 'a' + 1)) % mod;
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