package some_preq._06_cses._04_graph;
/*
 * You are playing a game consisting of n planets. Each planet has a teleporter to another planet (or the planet itself).
Your task is to process q queries of the form: when you begin on planet x and travel through k teleporters, which planet will you reach?
Input
The first input line has two integers n and q: the number of planets and queries. The planets are numbered 1,2,\dots,n.
The second line has n integers t_1,t_2,\dots,t_n: for each planet, the destination of the teleporter. It is possible that t_i=i.
Finally, there are q lines describing the queries. Each line has two integers x and k: you start on planet x and travel through k teleporters.
Output
Print the answer to each query.
Constraints

1 \le n, q \le 2 \cdot 10^5
1 \le t_i \le n
1 \le x \le n
0 \le k \le 10^9

Example
Input:
4 3
2 1 1 4
1 2
3 4
4 1

Output:
1
2
4
 */

 //prereq:algos\_4_Trees\_29_kth_ancestor_in_tree.java, exactly same idea

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _12_planet_queries{
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
            solve();
        // }
    }
    static int maxV=(int)((2e+5)+1);
    static int maxLog=31;
    static int _2_pow_jth_par[][]=new int[maxV][maxLog];
 
    public static void solve() throws IOException {
        // i will assume that every planet ha only 1 dest
 
        int nPlanets=scanInt();
        int nQ=scanInt();
 
        //now i have parent of each vertex, leeme calc 2pow jth par of ach vert
        for(int v=1;v<=nPlanets;v++) _2_pow_jth_par[v][0]=scanInt();
 
        for(int j=1;j<maxLog;j++){
            for(int v=1;v<=nPlanets;v++){
                _2_pow_jth_par[v][j]=_2_pow_jth_par[ _2_pow_jth_par[v][j-1] ][j-1];
            }
        }
 
        StringBuilder sb=new StringBuilder();
       while (nQ--!=0) {
            int v=scanInt();
            int k=scanInt();
            int ans=v;
            for(int j=0;j<maxLog;j++) if((k&(1<<j))!=0)ans=_2_pow_jth_par[ans][j];
            sb.append(ans).append("\n");
       }
 
       print(sb);
 
 
 
    }



    static int MOD = 1_000_000_007;
    static int INF = (int) 1e9;
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

     static int[][] scan_graph(int nConnections,boolean isWeighted) throws IOException{
        int graph[][]=new int[nConnections][isWeighted?3:2];
        for(int i=0;i<nConnections;i++) graph[i]=scanIntArray(isWeighted?3:2);
        return graph;
    }

    static int djikstra(int g[][], int nNodes, int src, int dest) {//use when all edges r positive
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
    static int[] bellmanFord(int n, int[][] edges, int src) {//use when edges can be negative
        int nNodes=n;
        int dis[]=new int[nNodes];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src]=0;
        for(int i=0;i<nNodes-1;i++){//we will update n-1 times by relaxing 1 edge at a time
            for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],dis);
        }
        if(hasCycles(edges,dis)) return new int[]{-1};//relaxing edges for one more time ie nth time , if dis array changes compared to previous version, there existsa  cycle
        return dis;
    }
    static void relaxEdges(int u,int v,int wt,int dis[]){
        if(dis[u]!=Integer.MAX_VALUE && dis[u]+wt<dis[v]) dis[v]=dis[u]+wt;
    }
    static boolean hasCycles(int edges[][],int dis[]){
        int clone[]=dis.clone();
        for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],clone);
        for(int i=0;i<dis.length;i++) if(dis[i]!=clone[i]) return true;
        return false;
    }

    static long[][] floyd_warshall(int nNodes,int g[][],boolean isDirected){//when i want miDis(u,v) for each query in O(1) time
        long dis[][] = new long[nNodes][nNodes];
        for (int i = 0; i < nNodes; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }

        for (int[] e : g) {
            dis[e[0] - 1][e[1] - 1] = Math.min(dis[e[0] - 1][e[1] - 1], e[2]);
            if(!isDirected) dis[e[1] - 1][e[0] - 1] = Math.min(dis[e[1] - 1][e[0] - 1], e[2]);
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

    static List<int[]> get_mst_graph(int nNodes, int graph[][],boolean isDirected) {//prims

        List<int[]> mst_edges = new ArrayList<>();
        List<List<int[]>> adj = get_adj_weighted( graph,nNodes,isDirected);
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
