package DPBootcamp.StringHashing;

import java.util.Scanner;

public class StringHashing {
    static int BASE1 = 7919;
    static int BASE2 = 4793;
    static int mod = (int)1e9 + 7;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int l1 = sc.nextInt();
        int r1 = sc.nextInt();
        int l2 = sc.nextInt();
        int r2 = sc.nextInt();
        int n = s.length();
        Hashes[] hashes = new Hashes[n + 1];
        hashes[0] = new Hashes(0, 0);
        Bases[] bases = new Bases[n + 1];
        bases[0] = new Bases(1, 1);
        computeHashes(s, hashes, bases);
        Hashes string1Hash = getHash(l1 + 1, r1 + 1, hashes, bases);
        Hashes string2Hash = getHash(l2 + 1, r2 + 1, hashes, bases);
        if(string1Hash.hash1 == string2Hash.hash1 && string1Hash.hash2 == string2Hash.hash2)
            System.out.println("Match");
        else
            System.out.println("Don't match");
    }
    public static void computeHashes(String s, Hashes[] hashes, Bases[] bases){
        for(int i = 0; i < s.length(); i++){
            hashes[i + 1] = new Hashes(
                    (modMul(hashes[i].hash1, BASE1) + (s.charAt(i) - 'a' + 1) % mod),
                    (modMul(hashes[i].hash2, BASE2) + (s.charAt(i) - 'a' + 1) % mod)
            );
            System.out.println("prefix: "+s.substring(0, i + 1));
            System.out.println("hash 1: "+hashes[i + 1].hash1+", hash 2: "+hashes[i + 1].hash2);
            bases[i + 1] = new Bases(
                    modMul(bases[i].base1, BASE1),
                    modMul(bases[i].base2, BASE2)
            );
            System.out.println("Curr base 1: "+bases[i].base1+", Curr base 2: "+bases[i].base2);
        }
    }
    public static Hashes getHash(int l, int r, Hashes[] hashes, Bases[] bases){
        System.out.println("query for: "+l+" "+r);
        int chars = (r - l + 1);
        int queryHash1 = (hashes[r].hash1 - modMul(bases[chars].base1, hashes[l - 1].hash1) + mod) % mod;
        int queryHash2 = (hashes[r].hash2 - modMul(bases[chars].base2, hashes[l - 1].hash2) + mod) % mod;
        System.out.println("Calculated hash 1: "+queryHash1+", hash 2: "+queryHash2);
        return new Hashes(queryHash1, queryHash2);
    }
    public static int modMul(int x, int y){
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
class Bases{
    int base1, base2;
    Bases(int base1, int base2){
        this.base1 = base1;
        this.base2 = base2;
    }
}