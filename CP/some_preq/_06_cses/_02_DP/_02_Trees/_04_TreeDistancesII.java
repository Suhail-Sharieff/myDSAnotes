package some_preq._06_cses._02_DP._02_Trees;
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/

import java.io.*;
import java.util.*;
/*You are given a tree consisting of n nodes.
Your task is to determine for each node the sum of the distances from the node to all other nodes.
Input
The first input line contains an integer n: the number of nodes. The nodes are numbered 1,2,\ldots,n.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.
Output
Print n integers: for each node 1,2,\ldots,n, the sum of the distances.
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
6 9 5 8 8 */

public class _04_TreeDistancesII{
    public static void main(String[] args) throws IOException {
        scanner = new FastScanner();
        writer = new PrintWriter(System.out);
        solve();
        writer.flush();
    }
    static List<Integer>[]adj;
    static int nv;
    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        nv=scanInt();   
        adj=new ArrayList[nv];
        for(int i=0;i<nv;i++) adj[i]=new ArrayList<>();
        for(int i=1;i<nv;i++){
            int u=scanInt()-1,v=scanInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }   
       dp=new int[nv];
       sz=new int[nv];
       build_rooted_answer(0, -1);
       ans=new long[nv];
       build_ans(0, -1);
       printArray(ans);
    }
    //------step1:rooting, build dp, where dp[u] represents the sum of distances from u to all other nodes WITH u as ROOT
    static int dp[];
    static int sz[];
    static void build_rooted_answer(int u,int par){
        sz[u]=1;
        for(int v:adj[u]){
            if(v==par) continue;
            build_rooted_answer(v, u);
            sz[u]+=sz[v];
            dp[u]+=dp[v]+sz[v];
        }
    }   
    //----step2: rerooting, u have for each node, the sum considering u as root, ie dp[u] has asnwer for sum of distances from u and to all nodes under its substree,using this we can build ans with other nodes also 
    static long ans[];
    static void build_ans(int u,int par){
        ans[u]=dp[u];
        for(int v:adj[u]){
            if(v==par) continue;
            int pu=dp[u],pv=dp[v];
            int su=sz[u],sv=sz[v];

            dp[u]=dp[u]-(dp[v]+sz[v]);
            sz[u]=sz[u]-(sz[v]);

            dp[v]+=dp[u]+sz[u];
            sz[v]+=sz[u];

            build_ans(v, u);

            dp[u]=pu;dp[v]=pv;
            sz[u]=su;sz[v]=sv;

        }
    }
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
            while (c == '\n' || c == '\r') c = read(); // skip newlines
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

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }


    static class SegTree{
        int arr[];
        int seg[];
        public SegTree(int arr[]) {
            int n=arr.length;
            this.arr=arr;
            seg=new int[(n<<2)+1];
            build(0, 0, n-1);
        }
        void build(int i,int l,int r){
            if(l==r) {seg[i]=arr[l];return;}
            int m=(l+r)>>1;
            build((i<<1)+1, l, m);
            build((i<<1)+2, m+1, r);
            seg[i]=seg[(i<<1)+1]+seg[(i<<1)+2];
        }
        void up(int i,int l,int r,int tidx,int tval){
            if(l==r){seg[i]=arr[tidx]=tval;return;}
            int m=(l+r)>>1;
            if(tidx<=m) up((i<<1)+1, l, m, tidx,tval);
            else up((i<<1)+2, m+1, r, tidx,tval);
            seg[i]=seg[(i<<1)+1]+seg[(i<<1)+2];
        }
        TreeNode getRoot(int i,int l,int r){
            if(l==r) return new TreeNode(seg[i]);
            int m=(l+r)>>1;
            TreeNode root=new TreeNode(seg[i]);
            root.left=getRoot((i<<1)+1, l, m);
            root.right=getRoot((i<<1)+2, m+1, r);
            root.val=root.left.val+root.right.val;
            return root;
        }

        int getSum(int i,int l,int r,int tl,int tr){
            if(tr<l||tl>r) return 0;
            if(tl<=l&&r<=tr) return seg[i];
            int m=(l+r)>>1;
            return getSum((i<<1)+1, l, m, tl, tr)+getSum((i<<1)+2, m+1, r, tl, tr);
        }

        public void printTree() throws IOException{
            prettyPrintTree(getRoot(0, 0, arr.length-1), "", true);
        }
        protected void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) throws IOException {
            if (node == null) return;
            if (node.right != null) prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            println(prefix + (isLeft ? "└── " : "┌── ") + node.val);
            if (node.left != null) prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
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

    static void printArray(long[] ans2) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (var e : ans2) {
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

