package _7_Graph._04_Dir_weigh;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
/*
Given a weighted and directed graph of v vertices and edges, Find the shortest distance of all the vertex's from the source vertex, src and return a list of integers where the ith integer denotes the distance of the ith node from the source node. If a vertices can't be reach from the s then mark the distance as 10^8.
Note: If there exist a path to a negative weighted cycle from the source node then return {-1}.

Examples:

Input: edges = [[0,1,9]], src = 0

Output: [0, 9]
Explanation: Shortest distance of all nodes from source is printed.
Input: edges = [[0,1,5], [1,0,3], [1,2,-1], [2,0,1]], src = 2

Output: [1, 6, 0]
Explanation: For nodes 2 to 0, we can follow the path: 2-0. This has a distance of 1. For nodes 2 to 1, we cam follow the path: 2-0-1, which has a distance of 1+5 = 6,
Constraints:
1 ≤ V ≤ 500
1 ≤ E ≤ V*(V-1)
-1000 ≤ data of nodes, weight ≤ 1000
0 ≤ S < V
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=43&ab_channel=takeUforward

Bell man ford is almost similar to djikstra, but it can even hanle the cases where the edge weight may be negative.
- do n-1 iterations and update dis for each in edges. (n-1) coz we realax or build shortest distance to 1 node at a time
- to check if cycle exists, do one more iteration ie nth iteration, if any value changes(prolly decrease) in dis array compared to previous version, there's a cycle, else no
 */

public class _03_bell_man_ford {
    static int[] bellmanFord(int n, int[][] edges, int src) {
        int nNodes=n;
        int dis[]=new int[nNodes];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src]=0;
        for(int i=0;i<nNodes-1;i++){
            for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],dis);
        }
        if(hasCycles(edges,dis)) return new int[]{-1};//relaxing edges for one more time ie nth time , if dis array changes compared to previous version, there existsa  cycle
        return dis;
    }
    static void relaxEdges(int u,int v,int wt,int dis[]){
        //observe that first condition is extra in this---VVIMP
        if(dis[u]!=Integer.MAX_VALUE && dis[u]+wt<dis[v]) dis[v]=dis[u]+wt;
    }
    static boolean hasCycles(int edges[][],int dis[]){
        int clone[]=dis.clone();
        for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],clone);
        for(int i=0;i<dis.length;i++) if(dis[i]!=clone[i]) return true;
        return false;
    }


    //expample problem:
    /*Time limit: 1.00 s
Memory limit: 512 MB

cses:HighScore 

You play a game consisting of n rooms and m tunnels. Your initial score is 0, and each tunnel increases your score by x where x may be both positive or negative. You may go through a tunnel several times.
Your task is to walk from room 1 to room n. What is the maximum score you can get?
Input
The first input line has two integers n and m: the number of rooms and tunnels. The rooms are numbered 1,2,\dots,n.
Then, there are m lines describing the tunnels. Each line has three integers a, b and x: the tunnel starts at room a, ends at room b, and it increases your score by x. All tunnels are one-way tunnels.
You can assume that it is possible to get from room 1 to room n.
Output
Print one integer: the maximum score you can get. However, if you can get an arbitrarily large score, print -1.
Constraints

1 \le n \le 2500
1 \le m \le 5000
1 \le a,b \le n
-10^9 \le x \le 10^9

Example
Input:
4 5
1 2 3
2 4 -1
1 3 -2
3 4 7
1 4 4

Output:
5 */

//https://www.youtube.com/watch?v=2Epc8xZObIc

//since they have askjed max score, we will apply normal bellman but make all edges multiply by -1,it will give most negative vvalue, ans is -1*thatValue, handle edge cases
//we will use modifies bellman which staes that upon doing 2*(n-1) times relaxation, if cycle exists, all nodes will have value NINF
    static 

    class HighScore{
        public static void main(String[] args) throws IOException {
            // int t = scanInt();
            // while (t-- > 0) {
                solve();
            // }
        }
    
        public static void solve() throws IOException {
            int nNodes=scanInt();
            int nConnections=scanInt();
            int graph[][]=scan_graph(nConnections, true);
    
            long dis[]=new long[nNodes];
            Arrays.fill(dis, INF);
    
            dis[0]=0;
    
            //relaxing edges for n-1 times
            for(int i=0;i<nNodes-1;i++){
                for(int edge[]:graph){
                    int u=edge[0]-1;
                    int v=edge[1]-1;
                    int d=-1*edge[2];//coz we need -ve of that edge 
                    if(dis[u]==INF) continue;
                    dis[v]=Math.min(dis[v], dis[u]+d);
                    dis[v]=Math.max(dis[v], NINF);//for ex if goes below NINF, means prolly a cycle, so take NINF for better
                }
            }
            //relaxing edges for one more n-1 times so that if a neg cycle exists, we will update to NINF
            for(int i=0;i<nNodes-1;i++){
                for(int edge[]:graph){
                    int u=edge[0]-1;
                    int v=edge[1]-1;
                    int d=-1*edge[2];
                    if(dis[u]==INF) continue;
                    dis[v]=Math.max(dis[v], NINF);
                    if(dis[u]+d<dis[v]){
                        dis[v]=NINF;
                    }
                }
            }
    
            if(dis[nNodes-1]==NINF) print(-1);
            else print(-1*dis[nNodes-1]);
    
            // print(Arrays.toString(dis));
        }
    
        static long INF =(long) 1e17;
        static long NINF =-INF;
    
    
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;
    
        static int scanInt() throws IOException {
            return Integer.parseInt(nextToken());
        }
    
        static long scanLong() throws IOException {
            return Long.parseLong(nextToken());
        }
    
        static String scanString() throws IOException {
            return nextToken();
        }
    
        static int[] scanIntArray(int size) throws IOException {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = scanInt();
            }
            return array;
        }
    
       
        static String nextToken() throws IOException {
            if (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }
    
    
      
    
        static void print(Object o) throws IOException {
            bw.write(o.toString());
            bw.newLine();
            bw.flush();
        }
    
         static int[][] scan_graph(int nConnections,boolean isWeighted) throws IOException{
            int graph[][]=new int[nConnections][isWeighted?3:2];
            for(int i=0;i<nConnections;i++) graph[i]=scanIntArray(isWeighted?3:2);
            return graph;
        }
    
       
    }
    
    
//example problem: to  print cycle:https://www.youtube.com/results?search_query=cycle+finding+cses

//i have taken 0 as A-prime
/*CSES Problem Set
      Cycle Finding

Task
Submit
Results
Statistics
Tests
Queue

    
    
  

  

    
CSES - Cycle Finding



Time limit: 1.00 s
Memory limit: 512 MB



You are given a directed graph, and your task is to find out if it contains a negative cycle, and also give an example of such a cycle.
Input
The first input line has two integers n and m: the number of nodes and edges. The nodes are numbered 1,2,\ldots,n.
After this, the input has m lines describing the edges. Each line has three integers a, b, and c: there is an edge from node a to node b whose length is c.
Output
If the graph contains a negative cycle, print first "YES", and then the nodes in the cycle in their correct order. If there are several negative cycles, you can print any of them. If there are no negative cycles, print "NO".
Constraints

1 \le n \le 2500
1 \le m \le 5000
1 \le a,b \le n
-10^9 \le c \le 10^9

Example
Input:
4 5
1 2 1
2 4 1
3 1 1
4 1 -3
4 3 -2

Output:
YES
1 2 4 1 */
static class CycleFinding {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int nNodes = scanInt();
        int nConnections = scanInt();
        int g[][] = new int[nConnections+nNodes+2][3];
        for(int i=0;i<nConnections;i++){
            g[i]=scanIntArray(3);
        }
        int src=0;
        int nodeNumber=1;
        for(int i=nConnections;i<=nConnections+nNodes;i++){
            g[i]=new int[]{src,nodeNumber++,0};
        }

        
        long dis[] = new long[nNodes+2];
        Arrays.fill(dis, INF);
        dis[src] = 0;

        int parent[]=new int[nNodes+2];
        int someNodeOfNegativeCycle__or_endOfNegCycle=-1;

        for (int i = 1; i <= nNodes; i++) {
            someNodeOfNegativeCycle__or_endOfNegCycle=-1;
            for (int e[] : g){
                int u=e[0];
                int v=e[1];
                int d=e[2];
                if(dis[u]==INF) continue;
                if(dis[u]+d<dis[v]){
                    dis[v]=dis[u]+d;
                    parent[v]=u;
                    someNodeOfNegativeCycle__or_endOfNegCycle=v;
                }
            }
        }

        // print(someNodeOfNegativeCycle__or_endOfNegCycle);
        if(someNodeOfNegativeCycle__or_endOfNegCycle==-1){
            //no neg cycle exists
            print("NO");
            return;
        }


        for(int k=1;k<=nNodes;k++) someNodeOfNegativeCycle__or_endOfNegCycle=parent[someNodeOfNegativeCycle__or_endOfNegCycle];

        ArrayList<Integer>cycle=new ArrayList<>();

       for(int v=someNodeOfNegativeCycle__or_endOfNegCycle;;v=parent[v]){
        cycle.add(v);
        if(v==someNodeOfNegativeCycle__or_endOfNegCycle && cycle.size()>1) break;
       }

       print("YES");
       Collections.reverse(cycle);
        for(int e:cycle){
            if(e!=0){
                System.out.print(e+" ");
            }else{
                System.out.print((e+1)+" ");
            }
        }

       System.out.println();

        
    }

    static int MOD = 1_000_000_007;
    static int INF = (int) 1e17;
    static int NINF = -1*INF;
    static long fact[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static String scanString() throws IOException {
        return nextToken();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static int GCD(int a, int b) {
        return (b == 0) ? (a) : GCD(b, a % b);
    }

    static int LCM(int a, int b) {
        return ((a * b) / GCD(a, b));
    }

    static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static List<Integer> getPrimeList(int from, int tillWhere) {
        boolean isPrime[] = new boolean[tillWhere + 1];
        List<Integer> primesList = new ArrayList<>();
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= tillWhere; i++) {
            if (isPrime[i]) {
                if (i >= from) {
                    primesList.add(i);
                }
                for (int j = i * i; j <= tillWhere; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return primesList;
    }

    static List<Integer> getDivisorListOf(int num) {
        List<Integer> divisorList = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                divisorList.add(i);
                if (num / i != i) {
                    divisorList.add(num / i);
                }
            }
        }
        return divisorList;
    }

    static void printArray(int arr[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e + " ");
        }
        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> getPrimeFactorsListOf(int num) {
        if (num <= 1) {
            return new ArrayList<>();
        }
        List<Integer> primefactorsList = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                primefactorsList.add(i);
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num != 1) {
            primefactorsList.add(num);
        }
        return primefactorsList;
    }

    static long pow(long base, long exp) {
        long ans = 1l;
        boolean isNegativeExponent = exp < 0;
        exp = Math.abs(exp);
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base * 1l) % MOD;
            }
            base = (base * base * 1l) % MOD;
            exp >>= 1;
        }
        return isNegativeExponent ? (1l / ans) : ans;
    }

    static void compute_fact() {
        fact = new long[100001];
        fact[0] = fact[1] = 1;
        for (int i = 2; i <= 100000; i++) {
            fact[i] = (i * 1l * fact[i - 1]) % MOD;
        }
    }

    static long nCr(int n, int r) {
        long nr = fact[n];
        long dr = (fact[n - r] * 1l * fact[r]) % MOD;
        long inv = pow(dr, MOD - 2);// using fermat little theorm, inverse(x)=pow(x,m-2) given m is prime
        long ans = (nr * 1l * inv) % MOD;
        return ans;
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }

    static List<List<Integer>> get_adj(int graph[][], int nNodes, boolean isDirected) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < nNodes; i++)
            adj.add(new ArrayList<>());
        for (int con[] : graph) {
            adj.get(con[0] - 1).add(con[1] - 1);
            if (!isDirected)
                adj.get(con[1] - 1).add(con[0] - 1);
        }
        return adj;
    }

    static List<List<int[]>> get_adj_weighted(int graph[][], int nNodes, boolean isDirected) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < nNodes; i++)
            adj.add(new ArrayList<>());
        for (int con[] : graph) {
            adj.get(con[0] - 1).add(new int[] { con[1] - 1, con[2] });
            if (!isDirected)
                adj.get(con[1] - 1).add(new int[] { con[0] - 1, con[2] });
        }
        return adj;
    }

    static int[][] scan_graph(int nConnections, boolean isWeighted) throws IOException {
        int graph[][] = new int[nConnections][isWeighted ? 3 : 2];
        for (int i = 0; i < nConnections; i++)
            graph[i] = scanIntArray(isWeighted ? 3 : 2);
        return graph;
    }

    static int djikstra(int g[][], int nNodes, int src, int dest) {// use when all edges r positive
        List<List<int[]>> adj = get_adj_weighted(g, nNodes, true);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        int dis[] = new int[nNodes];
        Arrays.fill(dis, INF);
        dis[src] = 0;
        pq.offer(new int[] { src, 0 });
        while (!pq.isEmpty()) {
            int top[] = pq.poll();
            int curr = top[0];
            int d = top[1];
            if (d > dis[curr])
                continue;
            for (int edge[] : adj.get(curr)) {
                int to = edge[0], w = edge[1];
                if (dis[curr] + w < dis[to]) {
                    dis[to] = dis[curr] + w;
                    pq.offer(new int[] { to, dis[to] });
                }
            }
        }
        return dis[dest];
    }

    static int[] bellmanFord(int n, int[][] graph, int src) {// use when edges can be negative
        int nNodes = n;
        int dis[] = new int[nNodes];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        for (int i = 0; i < nNodes - 1; i++) {// we will update n-1 times by relaxing 1 edge at a time
            for (int each[] : graph)
                relaxEdges(each[0], each[1], each[2], dis);
        }
        if (hasCycles(graph, dis))
            return new int[] { -1 };// relaxing edges for one more time ie nth time , if dis array changes compared
                                    // to previous version, there existsa cycle
        return dis;
    }

    static void relaxEdges(int u, int v, int wt, int dis[]) {
        if (dis[u] != Integer.MAX_VALUE && dis[u] + wt < dis[v])
            dis[v] = dis[u] + wt;
    }

    static boolean hasCycles(int edges[][], int dis[]) {
        int clone[] = dis.clone();
        for (int each[] : edges)
            relaxEdges(each[0], each[1], each[2], clone);
        for (int i = 0; i < dis.length; i++)
            if (dis[i] != clone[i])
                return true;
        return false;
    }

    static long[][] floyd_warshall(int nNodes, int g[][], boolean isDirected) {// when i want miDis(u,v) for each query
                                                                               // in O(1) time
        long dis[][] = new long[nNodes][nNodes];
        for (int i = 0; i < nNodes; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }

        for (int[] e : g) {
            dis[e[0] - 1][e[1] - 1] = Math.min(dis[e[0] - 1][e[1] - 1], e[2]);
            if (!isDirected)
                dis[e[1] - 1][e[0] - 1] = Math.min(dis[e[1] - 1][e[0] - 1], e[2]);
        }

        for (int k = 0; k < nNodes; k++) {
            long[] disK = dis[k];
            for (int i = 0; i < nNodes; i++) {
                long dik = dis[i][k];
                if (dik == INF)
                    continue;
                long[] disI = dis[i];
                for (int j = 0; j < nNodes; j++) {
                    long alt = dik + disK[j];
                    if (alt < disI[j]) {
                        disI[j] = alt;
                    }
                }
            }
        }
        return dis;
    }

    static List<int[]> get_mst_graph(int nNodes, int graph[][], boolean isDirected) {// prims

        List<int[]> mst_edges = new ArrayList<>();
        List<List<int[]>> adj = get_adj_weighted(graph, nNodes, isDirected);
        boolean isVis[] = new boolean[nNodes];
        int src = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        pq.offer(new int[] { src, -1, 0 });// store format:[node,parent,wtWithParent]

        int ans = 0;

        while (!pq.isEmpty()) {

            int front[] = pq.poll();
            int curr = front[0];
            int parentOfCurr = front[1];
            int wt = front[2];

            if (isVis[curr]) {
                continue;
            }

            if (parentOfCurr != -1) {
                mst_edges.add(new int[] { parentOfCurr, curr, wt });
            }
            isVis[curr] = true;
            ans += wt;
            for (int neigh[] : adj.get(curr)) {
                int neighbourNodeNumber = neigh[0];
                int wtOfCurrWithThatNeighBour = neigh[1];
                pq.offer(new int[] { neighbourNodeNumber, curr, wtOfCurrWithThatNeighBour });
            }
        }
        if (mst_edges.size() != nNodes - 1) {
            return null; // Graph is disconnected
        }
        System.out.println("The Minimum spanning tree of given graph has the following adjacency list:");
        for (var e : mst_edges)
            System.out.println(Arrays.toString(e));
        System.out.println("The sum of all weights in MST of given graph  is " + ans);
        return mst_edges;
    }

}
}



