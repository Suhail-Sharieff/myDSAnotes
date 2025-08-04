package some_preq._06_cses._02_DP._02_Trees;

//Aplication of Binary Uplifting
//https://www.youtube.com/watch?v=s9zZOVsF_eo&list=PLb3g_Z8nEv1j_BC-fmZWHFe6jmU_zv-8s&index=9



/*A company has n employees, who form a tree hierarchy where each employee has a boss, except for the general director.
Your task is to process q queries of the form: who is the lowest common boss of employees a and b in the hierarchy?
Input
The first input line has two integers n and q: the number of employees and queries. The employees are numbered 1,2,\dots,n, and employee 1 is the general director.
The next line has n-1 integers e_2,e_3,\dots,e_n: for each employee 2,3,\dots,n their boss.
Finally, there are q lines describing the queries. Each line has two integers a and b: who is the lowest common boss of employees a and b?
Output
Print the answer for each query.
Constraints

1 \le n,q \le 2 \cdot 10^5
1 \le e_i \le i-1
1 \le a,b \le n

Example
Input:
5 3
1 1 3 3
4 5
2 5
1 4

Output:
3
1
1 */

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/

import java.io.*;
import java.util.*;

public class _06_CompanyQueries2{
    public static void main(String[] args) throws IOException {
        scanner = new FastScanner();
        writer = new PrintWriter(System.out);
            solve();
        writer.flush();
    }

    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        int nv=scanInt();
        int nq=scanInt();

        adj=new ArrayList[nv];for(int i=0;i<nv;i++) adj[i]=new ArrayList<>();
        up=new int[nv][31];
        level=new int[nv];

        for(int row[]:up) Arrays.fill(row, -1);
        for(int i=1;i<nv;i++){int u=scanInt()-1;adj[u].add(i);adj[i].add(u);up[i][0]=u;}

        //--------brute force
        // while (nq-->0) {
        //     int u=scanInt()-1;
        //     int v=scanInt()-1;
        //     lca_node=-1;brute(0, -1, u, v);println(lca_node+1);
        // }        

        //---------optimal
        for(int i=1;i<=30;i++) for(int u=0;u<nv;u++) if(up[u][i-1]!=-1) up[u][i]=up[up[u][i-1]][i-1];
        build_level(0, -1, 0);
        while (nq-->0) {
            int u=scanInt()-1;
            int v=scanInt()-1;
            // println(LCA_in_LogSqare(u, v)+1);
            println(LCA_in_Log(u, v)+1);
        }
    }

    //----------------brute force:O(N) per query
    static int lca_node;
    static List<Integer>[]adj;
    static int brute(int root,int par,int u,int v){
        int cnt=0;
        if(root==u||root==v) cnt++;
        for(int child:adj[root]) if(child!=par) cnt+=brute(child, root, u, v);
        if(cnt==2 && lca_node==-1) lca_node=root;
        return cnt;
    }




    //----------------------------optimal
    static int up[][];
    static int level[];
    static void build_level(int u,int par,int curr_level){
        level[u]=curr_level;
        for(int v:adj[u]) if(v!=par) build_level(v, u, curr_level+1);
    }
    static int get_kth_ancestor(int u,int k){
        for(int i=0;i<=30;i++) if(u!=-1) if((k&(1<<i))!=0) u=up[u][i];
        return u;
    }
    static int LCA_in_LogSqare(int u,int v){//O(log^2 N)
        if(level[u]<level[v]){int temp=u;u=v;v=temp;}//i will assume u is preent at down than v
        //hypothtically bringing u an v at same level
        u=get_kth_ancestor(u, level[u]-level[v]);//now u is the node whichs present in the same level as of v, now we need to find lca which is preesne t say x levels higher, this x will obviously lie  in range [1 <= x <= level[u](or level[v] as level[u]==level[v])]
        
        int low=0,high=level[u];
        int lca=-1;
        while (low<=high) {//logn
            int mid=(low+high)>>1;
            int n1=get_kth_ancestor(u, mid);//logn
            int n2=get_kth_ancestor(v, mid);//logn
            if(n1==-1||n2==-1){
                high=mid-1;
            }else if(n1==n2){
                lca=n1;
                high=mid-1;
            }else{
                low=mid+1;
            }
            
        }
        return lca;
    }   

    static int LCA_in_Log(int u,int v){//O(logn)
        if(level[u]<level[v]){int temp=u;u=v;v=temp;}
        u=get_kth_ancestor(u, level[u]-level[v]);//bring u and v at samelevel
        if(u==v) return u;
        for(int i=30;i>=0;i--){//logn
            if(up[u][i]!=-1){
                if(up[u][i]!=up[v][i]){
                    u=up[u][i];
                    v=up[v][i];
                }
            }
        }
        return up[u][0];//we r now i a condition like u and v r immediate children of lca
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

