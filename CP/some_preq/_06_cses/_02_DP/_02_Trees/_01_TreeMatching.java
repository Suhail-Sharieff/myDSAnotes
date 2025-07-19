package some_preq._06_cses._02_DP._02_Trees;



/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
/*Time limit: 1.00 s
Memory limit: 512 MB

https://www.youtube.com/watch?v=RuNAYVTn9qM

You are given a tree consisting of n nodes.
A matching is a set of edges where each node is an endpoint of at most one edge. What is the maximum number of edges in a matching?
Input
The first input line contains an integer n: the number of nodes. The nodes are numbered 1,2,\ldots,n.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.
Output
Print one integer: the maximum number of pairs.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5
1 2
1 3
3 4
3 5

Output:
2

Explanation: One possible matching is (1,2) and (3,4). */


/*idea: Pick and not pick strategy but on trees

imagingine vertex u and its children vi
               u
         v1 v2...vi...vn   

case 1: u can decide that u will not coose any edge between u and vi, so ans will just be sum of fuction of values of each vi,again for each vi u choose max of choosing edge with vi and without
 
so dont_select_any=sum(max(f(vi,true),f(vi,false)))

case 2: u decide to form an edge with any one of vi, then ans will be vi's part+leftPartOf_vi+right_part_of_vi

so select=max(
    f(vi,true)[form edge with vi fixed]
    +sum(max(f(v0..i-1,true),f(v0...i-1),false))[again for each left try all combos]
    +sum(max(f(vi+1..n,true),f(vi+1..n,false)))[...same proc]
)
at last return max(dont_select_any,select)

for optimization just compute prefix and suffix values so that u can get sum(vi...vj) in O(1) time

*/

import java.io.*;
import java.util.*;

public class _01_TreeMatching {
    public static void main(String[] args) throws IOException {
        scanner = new FastScanner();
        writer = new PrintWriter(System.out);
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
        writer.flush();
    }

    static List<Integer>[] adj;
    static int dp[][];

    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        int nNodes=scanInt();
        adj=new ArrayList[nNodes];
        dp=new int[nNodes][2];
        for(int e[]:dp) Arrays.fill(e, -1);
        for(int i=0;i<nNodes;i++) adj[i]=new ArrayList<>();
        for(int i=1;i<nNodes;i++){
            int u=scanInt(),v=scanInt();
            adj[u-1].add(v-1);
            adj[v-1].add(u-1);
        }
        int ans=max(optimal(0, -1, true), optimal(0, -1, false));
        println(ans);
    }

    static int rec(int u,int par,boolean edgeChosen){
        if(dp[u][edgeChosen?1:0]!=-1) return dp[u][edgeChosen?1:0];
        int dont_select_any=0;
        for(int v:adj[u]){
            if(v==par) continue;
            dont_select_any+=max(rec(v, u, true), rec(v, u, false));
        }
        int select=0;
        if(!edgeChosen){
            for(int i=0;i<adj[u].size();i++){
                int v=adj[u].get(i);
                if(v==par) continue;
                int mine=rec(v,u,true);
                int my_left=0;
                for(int j=0;j<i;j++){
                    v=adj[u].get(j);
                    if(v==par) continue;
                    my_left+=max(rec(v, u, true),rec(v, u, false));
                }
                int my_right=0;
                for(int j=i+1;j<adj[u].size();j++){
                    v=adj[u].get(j);
                    if(v==par) continue;
                    my_right+=max(rec(v, u, true),rec(v, u, false));
                }
                select=Math.max(select, 1+my_left+mine+my_right);
            }
        }
        return dp[u][edgeChosen?1:0]=max(select, dont_select_any);
    }

    static int optimal(int u,int par,boolean edgeChosen){
        if(dp[u][edgeChosen?1:0]!=-1) return dp[u][edgeChosen?1:0];

        List<Integer>myChildren=new ArrayList<>();
        for(int v:adj[u]) if(v!=par) myChildren.add(v);

        int nChildren=myChildren.size();
        int myChildValues[]=new int[nChildren];

        for(int i=0;i<nChildren;i++) myChildValues[i]=max(optimal(myChildren.get(i), u,true),optimal(myChildren.get(i), u,false));

        int dont_select_any=Arrays.stream(myChildValues).sum();
        int select=0;

        if(!edgeChosen){
            int pref[]=new int[nChildren];
            if(nChildren>0) pref[0]=myChildValues[0];
            for(int i=1;i<pref.length;i++) pref[i]=pref[i-1]+myChildValues[i];

            int suff[]=new int[nChildren];
            if(nChildren>0) suff[suff.length-1]=myChildValues[suff.length-1];
            for(int i=suff.length-2;i>=0;i--) suff[i]=suff[i+1]+myChildValues[i];



            for(int i=0;i<nChildren;i++){
                int mine=optimal(myChildren.get(i), u, true);
                int myLeft=(i>0)?pref[i-1]:0;
                int myRight=(i<nChildren-1)?suff[i+1]:0;
                select=Math.max(select, 1+myLeft+mine+myRight);
            }
            
        }

        return dp[u][edgeChosen?1:0]=max(select,dont_select_any);
    }


    static int max(int ...x){return Arrays.stream(x).max().getAsInt();}
    static int MOD = 1_000_000_007;
    static int INF = (int) 1e9;
    static long fact[];
    static FastScanner scanner;
    static PrintWriter writer;

    static int scanInt() throws IOException {
        return scanner.nextInt();
    }

    static long scanLong() throws IOException {
        return scanner.nextLong();
    }

    static String scanString() throws IOException {
        return scanner.nextString();
    }

    static String scanLine() throws IOException {
        return scanner.nextLine();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static long[] scanLongArray(int size) throws IOException {
        long array[] = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanLong();
        }
        return array;
    }

    static class FastScanner {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        private FastScanner() throws IOException {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ')
                c = read();
            return (char) c;
        }

        public String nextString() throws IOException {
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret.append((char) c);
            } while ((c = read()) > ' ');
            return ret.toString();
        }

        public String nextLine() throws IOException {
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c == '\n' || c == '\r')
                c = read(); // skip newlines
            do {
                ret.append((char) c);
                c = read();
            } while (c != -1 && c != '\n' && c != '\r');
            return ret.toString();
        }

        public void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        public byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    static int GCD(int a, int b) {
        return (b == 0) ? (a) : GCD(b, a % b);
    }

    static int LCM(int a, int b) {
        return ((a * b) / GCD(a, b));
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
        writer.println(sb.toString());
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
        writer.print(o.toString());
    }

    static void println(Object o) throws IOException {
        writer.println(o.toString());
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

    static int[] bellmanFord(int n, int[][] edges, int src) {// use when edges can be negative
        int nNodes = n;
        int dis[] = new int[nNodes];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        for (int i = 0; i < nNodes - 1; i++) {// we will update n-1 times by relaxing 1 edge at a time
            for (int each[] : edges)
                relaxEdges(each[0], each[1], each[2], dis);
        }
        if (hasCycles(edges, dis))
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

    static class ModularFunction {
        long x;

        public ModularFunction(long x) {
            this.x = x;
        }

        // (a*b)%k = ((a%k)*(b*k))%k
        public ModularFunction multiply(long b) {
            x = (((b % MOD) * 1l * (x % MOD)) % MOD);
            return this;
        }

        // (a/b)%k = ((a%k)*inv(b))%k
        public ModularFunction divideBy(long b) {
            x = (((x % MOD) * 1l * (pow(b, MOD - 2))) % MOD);
            return this;
        }

        // (a+b)%k
        public ModularFunction add(long b) {
            x = (((x % MOD) + (b % MOD)) % MOD);
            return this;
        }

        // (a-b)%k = ((a%k)-(b%k)+k)%k
        public ModularFunction subtract(long b) {
            x = (((x % MOD) - (b % MOD) + MOD) % MOD);
            return this;
        }

        // (a^b)=((a%k)^b)%k
        public ModularFunction power(long b) {
            x = ((pow(x % MOD, b)) % MOD);
            return this;
        }

        @Override
        public String toString() {
            return Long.toString(x);
        }
    }
}
