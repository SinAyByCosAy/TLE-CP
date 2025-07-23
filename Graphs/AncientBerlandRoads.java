//https://www.codechef.com/practice/course/5-star-and-above-problems/DIFF2500/problems/ABROADS
package DPBootcamp.Graphs;

import java.util.*;

public class AncientBerlandRoads {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Stack<Integer>[] pop = new Stack[n + 1];
        //initial population
        for(int i = 1; i <= n; i++) {
            pop[i] = new Stack<>();
            pop[i].push(sc.nextInt());
        }

        Pair6[] edges = new Pair6[m + 1];//store the edges being formed
        for(int i = 1; i <= m; i++) edges[i] = new Pair6(sc.nextInt(), sc.nextInt());

        boolean[] deleted = new boolean[m + 1];//store the edges that have been deleted
        Query[] queries = new Query[q + 1];//store the queries in order
        for(int i = 1; i <= q; i++){//queries
            String ch = sc.next();
            //0: Delete, 1: update
            if(ch.equals("D")){
                int x = sc.nextInt();
                queries[i] = new Query(0, x);
                deleted[x] = true;
            }else{
                int x = sc.nextInt();
                int y = sc.nextInt();
                pop[x].push(y);
                queries[i] = new Query(1, x);//tracking the city, value is in pop[] in order
            }
        }
        //pre-computing the graph's state as if all queries are answered. Then we'll backtrack and build up from there.
        DSU7.init(n, pop);
        for(int i = 1; i <= m; i++){
            if(!deleted[i]){
                int x = edges[i].x;
                int y = edges[i].y;
                DSU7.unionSet(x, y);
            }
        }
        long[] res = new long[q + 1];
        for(int i = q; i >= 1; i--){
            res[i] = DSU7.getMaxPop();
            if(queries[i].type == 0){//means before this query, the edge existed. Restore it.
                int idx = queries[i].val;
                int x = edges[idx].x;
                int y = edges[idx].y;
                DSU7.unionSet(x, y);
            }else{
                int rem = pop[queries[i].val].pop(); //remove the current value of the node, get the previous one
                DSU7.updateWeight(queries[i].val, pop[queries[i].val].peek(), rem);
            }
        }
        for(int i = 1; i <= q; i++) System.out.println(res[i]);
    }
}
class DSU7{
    static int[] parent, rank;
    static long[] weight;
    static TreeMap<Long, Integer> fm = new TreeMap<>(Collections.reverseOrder()); //track the weights of the components
    public static void init(int n, Stack<Integer>[] pop){
        parent = new int[n + 1];
        rank = new int[n + 1];
        weight = new long[n + 1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
            weight[i] = pop[i].peek();
            fm.put(weight[i], fm.getOrDefault(weight[i], 0) + 1);
        }
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(a != b){
            if(rank[a] < rank[b]){int t = a; a = b; b = t;}
            //'a' is the root
            parent[b] = a;

            //since we are merging, weights of component a and b need to be removed from map
            //and weight of a + b needs to be added
            fm.put(weight[a], fm.get(weight[a]) - 1);
            if(fm.get(weight[a]) == 0) fm.remove(weight[a]);
            fm.put(weight[b], fm.get(weight[b]) - 1);
            if(fm.get(weight[b]) == 0) fm.remove(weight[b]);

            weight[a] += weight[b]; //merge and store the combined weight in the root
            fm.put(weight[a], fm.getOrDefault(weight[a], 0) + 1);
            if(rank[a] == rank[b]) rank[a]++;
        }
    }
    public static void updateWeight(int v, int x, int y){
        int a = findSet(v); //weight of node is stored in sum of root
        //remove old value
        fm.put(weight[a], fm.get(weight[a]) - 1);
        if(fm.get(weight[a]) == 0) fm.remove(weight[a]);
        //update value now
        weight[a] += (x - y);
        fm.put(weight[a], fm.getOrDefault(weight[a], 0) + 1);
    }
    public static long getMaxPop(){
        return fm.firstKey();
    }
}
class Query{
    int type, val;
    Query(int type, int val){
        this.type = type;
        this.val = val;
    }
}
class Pair6{
    int x, y;
    Pair6(int x, int y){
        this.x = x;
        this.y = y;
    }
}
//TC: O((N + M + Q) . log N)
//SC: O(N + M + Q)