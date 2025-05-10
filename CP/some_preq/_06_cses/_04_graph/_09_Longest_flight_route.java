package some_preq._06_cses._04_graph;

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _09_Longest_flight_route {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        // solve();
        // }
    }

    public static void brute() throws IOException {// just modify djikstra
        int nCities = scanInt();
        int nConnections = scanInt();
        int graph[][] = scan_graph(nConnections, false);
        List<List<Integer>> adj = get_adj(graph, nCities, true);

        int parent[] = new int[nCities];

        int dis[] = new int[nCities];
        Arrays.fill(dis, -INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        parent[0] = -1;
        pq.offer(new int[] { 0, 0 });// curr,dis,par
        dis[0] = 0;

        while (!pq.isEmpty()) {
            int top[] = pq.poll();
            int u = top[0];
            int cost = top[1];
            if (dis[u] > cost)
                continue;
            for (int v : adj.get(u)) {
                if (1 + cost > dis[v]) {
                    dis[v] = 1 + cost;
                    parent[v] = u;
                    pq.offer(new int[] { v, dis[v] });
                }
            }
        }

        // print(Arrays.toString(parent));
        // print(Arrays.toString(dis));

        // int farthest_node_dis = -1 * INF, idx_of_farthest_node = -1;
        // for (int i = 0; i < nCities; i++) {
        // if (dis[i] >= farthest_node_dis) {
        // farthest_node_dis = dis[i];
        // idx_of_farthest_node = i;
        // }
        // }
        if (dis[nCities - 1] == -INF) {
            print("IMPOSSIBLE");
            return;
        }
        int start = nCities - 1;
        int k = 1;
        int end = -1;
        List<Integer> ans = new ArrayList<>();
        ans.add(start + 1);
        while (start != -1) {
            start = parent[start];
            if (start == end)
                break;
            ans.add(start + 1);
            k++;
        }
        // print(k);
        // print(ans);

        // if (k == 1) {
        // print("IMPOSSIBLE");
        // return;
        // }
        print(k);
        Collections.reverse(ans);
        StringBuilder sb = new StringBuilder();
        ans.forEach(e -> sb.append(e).append(" "));
        print(sb);

    }

    public static void optimal() throws IOException {// since clearly mentioned the graph is DAG, apply kahn+djikstra,
        /*
         * ✅ Why this is better:
         * Topological sort guarantees correct processing order in DAGs.
         * 
         * No unnecessary revisits like in Dijkstra with PQ.
         * 
         * Simple and readable.
         * 
         */
        int nCities = scanInt();
        int nConnections = scanInt();
        int graph[][] = scan_graph(nConnections, false);
        List<List<Integer>> adj = get_adj(graph, nCities, true);

        int[] indegree = new int[nCities];
        for (int[] edge : graph) {
            indegree[edge[1]]++;
        }

        // Topological sort (Kahn's algorithm)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < nCities; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0)
                    q.offer(v);
            }
        }

        // Initialize distances
        int[] dist = new int[nCities];
        Arrays.fill(dist, -INF);
        int[] parent = new int[nCities];
        Arrays.fill(parent, -1);

        dist[0] = 0;

        // Relax nodes in topo order
        for (int u : topo) {
            if (dist[u] == -INF)
                continue;
            for (int v : adj.get(u)) {
                if (dist[u] + 1 > dist[v]) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                }
            }
        }

        // Reconstruct path
        if (dist[nCities - 1] == -INF) {
            print("IMPOSSIBLE");
            return;
        }

        List<Integer> path = new ArrayList<>();
        int curr = nCities - 1;
        while (curr != -1) {
            path.add(curr + 1); // Convert to 1-based index
            curr = parent[curr];
        }
        Collections.reverse(path);
        print(path.size());
        StringBuilder sb = new StringBuilder();
        for (int x : path)
            sb.append(x).append(" ");
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

}
