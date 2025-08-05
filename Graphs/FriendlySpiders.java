//https://codeforces.com/contest/1775/problem/D
package DPBootcamp.Graphs;

import java.util.*;

public class FriendlySpiders {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int limit = (int) 3e5;
        boolean[] sieve = new boolean[limit + 1];
        int[] spf = new int[limit + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for(int i = 1; i <= limit; i++)
            spf[i] = i;

        //Sieve and SPF pre-computation, TC: O(N * log(log(N)))
        for(int i = 2; i <= (int) 1e4; i++){
            if(sieve[i]){
                for (int j = i * i; j <= limit; j += i) {
                    sieve[j] = false;
                    spf[j] = Math.min(spf[j], i);
                }
            }
        }
        int n = sc.nextInt();
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 1; i <= n; i++){//O(N.logX)
            int x = sc.nextInt();
            if(x == 1) adj.put(i, new ArrayList<>());
            else buildGraphWithPrime(x, i, spf, adj, n); //find primes and make connection
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        HashMap<Integer, Integer> parent = new HashMap<>(); //storing parent
        Queue<Integer> pq = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>(); //visited set
        pq.add(a);
        parent.put(a, 0);
        visited.add(a);
        while(!pq.isEmpty()){
            int top = pq.poll();
            if(top == b) break;
            for(int neighbor : adj.get(top)){
                if(!visited.contains(neighbor)){
                    pq.add(neighbor);
                    parent.put(neighbor, top);
                    visited.add(neighbor);
                }
            }
        }
        if(!parent.containsKey(b)) System.out.println(-1); //weren't able to reach b from a.
        else{
            List<Integer> res = new ArrayList<>();
            int ele = b;
            while(ele != 0){
                if(ele <= n) res.add(ele); //we are skipping prime nos here, they'll have index > n
                ele = parent.get(ele);
            }
            Collections.reverse(res);
            System.out.println(res.size());
            for(int x : res) System.out.print(x + " ");
            System.out.println();
        }
    }
    public static void buildGraphWithPrime(int a, int idx, int[] spf, HashMap<Integer, List<Integer>> adj, int n){
        //getting SPF for every X -> O(1)
        //Total divisions possible by all factors -> <= log A
        //TC: O(log A)
        while(a != 1){
            int factor = spf[a];
            while(a % factor == 0){
                a /= factor;
            }
            if(!adj.containsKey(idx)) adj.put(idx, new ArrayList<>());
            if(!adj.containsKey(n + factor)) adj.put(n + factor, new ArrayList<>());
            adj.get(idx).add(n + factor);
            adj.get(n + factor).add(idx);
        }
    }
}
//TC: O(N.logN)
//N nodes, every node can have log X prime factors
//therefore, edges = N.logX, worst case : N.logN