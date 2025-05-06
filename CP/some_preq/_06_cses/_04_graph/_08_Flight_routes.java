package some_preq._06_cses._04_graph;
/*Your task is to find the k shortest flight routes from Syrjälä to Metsälä. A route can visit the same city several times.
Note that there can be several routes with the same price and each of them should be considered (see the example).
Input
The first input line has three integers n, m, and k: the number of cities, the number of flights, and the parameter k. The cities are numbered 1,2,\ldots,n. City 1 is Syrjälä, and city n is Metsälä.
After this, the input has m lines describing the flights. Each line has three integers a, b, and c: a flight begins at city a, ends at city b, and its price is c. All flights are one-way flights.
You may assume that there are at least k distinct routes from Syrjälä to Metsälä.
Output
Print k integers: the prices of the k cheapest routes sorted according to their prices.
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n
1 \le c \le 10^9
1 \le k \le 10

Example
Input:
4 6 3
1 2 1
1 3 3
2 3 2
2 4 6
3 2 8
3 4 1

Output:
4 4 7

Explanation: The cheapest routes are 1 \rightarrow 3 \rightarrow 4 (price 4), 1 \rightarrow 2 \rightarrow 3 \rightarrow 4 (price 4) and 1 \rightarrow 2 \rightarrow 4 (price 7). */

//

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _08_Flight_routes {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        more_optimal();
        // }
    }

    public static void solve() throws IOException {
        int nNodes = scanInt();
        int nConns = scanInt();
        int k = scanInt();
        int g[][] = scan_graph(nConns, true);
        List<List<int[]>> adj = get_adj_weighted(g, nNodes, true);

        @SuppressWarnings("unchecked")
        List<Long> dis[] = new ArrayList[nNodes];

        for (int i = 0; i < nNodes; i++) {
            dis[i] = (new ArrayList<>(Collections.nCopies(k, INF))); 
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));

        int src = 0, dest = nNodes - 1;
        pq.offer(new long[] { src, 0 });
        dis[src].set(0, 0L);

        while (!pq.isEmpty()) {

            long top[] = pq.poll();
            int u = (int) top[0];
            long d = top[1];

            if (dis[u].get(k - 1) < d)
                continue;

            for (int edge[] : adj.get(u)) {
                int v = edge[0];
                int w = edge[1];
                long newDis = d + w;
                if (dis[v].get(k - 1) > newDis) {
                    dis[v].set(k - 1, newDis);
                    pq.offer(new long[] { v, newDis });
                    Collections.sort(dis[v]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        dis[dest].forEach(e -> sb.append(e).append(" "));
        print(sb);
    }

    public static void optimal() throws IOException {
        int nNodes = scanInt();
        int nConns = scanInt();
        int k = scanInt();
        int g[][] = scan_graph(nConns, true);
        List<List<int[]>> adj = get_adj_weighted(g, nNodes, true);

        @SuppressWarnings("unchecked")
        List<Long> dis[] = new ArrayList[nNodes];

        for (int i = 0; i < nNodes; i++) {
            dis[i] = (new ArrayList<>(Collections.nCopies(k, INF))); // Pre-fills list with `INF`
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));

        int src = 0, dest = nNodes - 1;
        pq.offer(new long[] { src, 0 });
        dis[src].set(0, 0L);

        while (!pq.isEmpty()) {

            long top[] = pq.poll();
            int u = (int) top[0];
            long d = top[1];

            if (dis[u].stream().max(Comparator.naturalOrder()).get() < d)
                continue;

            for (int edge[] : adj.get(u)) {
                int v = edge[0];
                int w = edge[1];
                long newDis = d + w;

                long worst_case = -1 * INF;
                int index_of_worst_case = -1;
                for (int i = 0; i < k; i++) {
                    if (dis[v].get(i) > worst_case) {
                        worst_case = dis[v].get(i);
                        index_of_worst_case = i;
                    }
                }

                if (newDis < worst_case) {
                    dis[v].set(index_of_worst_case, newDis);
                    pq.offer(new long[] { v, newDis });
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        dis[dest].stream().sorted().forEach(e -> sb.append(e).append(" "));
        print(sb);
    }

    public static void more_optimal() throws IOException {
        int n = scanInt(), m = scanInt(), k = scanInt();
        List<List<int[]>> adj = get_adj_weighted(scan_graph(m, true), n, true);
    
        final long INF = (long)1e18;
        // dis[u][i] = the i‑th shortest distance to u (unsorted)
        long[][] dis = new long[n][k];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], INF);
        }
    
        // min‑heap of (node, dist)
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    
        dis[0][0] = 0;
        pq.offer(new long[]{0, 0});
    
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int)cur[0];
            long d = cur[1];
            // find current worst (max) among k best for u
            long worstU = dis[u][0];
            for (int i = 1; i < k; i++) if (dis[u][i] > worstU) worstU = dis[u][i];
            if (d > worstU) continue;
    
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                long nd = d + w;
                // find index of worst in dis[v]
                int worstIdx = 0;
                long worstV = dis[v][0];
                for (int i = 1; i < k; i++) {
                    if (dis[v][i] > worstV) {
                        worstV = dis[v][i];
                        worstIdx = i;
                    }
                }
                if (nd < worstV) {
                    dis[v][worstIdx] = nd;
                    pq.offer(new long[]{v, nd});
                }
            }
        }
    
        // now sort the k best for dest and print
        long[] ans = dis[n-1];
        Arrays.sort(ans);
        StringBuilder sb = new StringBuilder();
        for (long x : ans) sb.append(x).append(' ');
        print(sb);
    }
    

    static int MOD = 1_000_000_007;
    static long INF = Long.MAX_VALUE;
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
        // Arrays.fill(dis, INF);
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

}
