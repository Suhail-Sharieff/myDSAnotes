package some_preq._06_cses._04_graph;

/*
 * Your task is to find a minimum-price flight route from Syrjälä to Metsälä. You have one discount coupon, using which you can halve the price of any single flight during the route. However, you can only use the coupon once.
When you use the discount coupon for a flight whose price is x, its price becomes \lfloor x/2 \rfloor (it is rounded down to an integer).
Input
The first input line has two integers n and m: the number of cities and flight connections. The cities are numbered 1,2,\ldots,n. City 1 is Syrjälä, and city n is Metsälä.
After this there are m lines describing the flights. Each line has three integers a, b, and c: a flight begins at city a, ends at city b, and its price is c. Each flight is unidirectional.
You can assume that it is always possible to get from Syrjälä to Metsälä.
Output
Print one integer: the price of the cheapest route from Syrjälä to Metsälä.
Constraints

2 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n
1 \le c \le 10^9

Example
Input:
3 4
1 2 3
2 3 1
1 3 7
2 1 5

Output:
2
 */
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _07_Flight_Discount{
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
            solve();
        // }
    }

    //https://www.youtube.com/watch?v=GZtZXhir7Wg

    //idea is to paralleley handle with_using and without_using coupon and pushing both in PQ simmultaneously(if coupon not used else just with coupon) adn then choosing best
    public static void solve() throws IOException {
        int nCities=scanInt();
        int nConnections=scanInt();

        int graph[][]=scan_graph(nConnections, true);

        List<List<int[]>>adj=get_adj_weighted(graph, nCities, true);


        PriorityQueue<Journey>pq=new PriorityQueue<>((x,y)->Long.compare(x.cost, y.cost));

        int src=0;
        int dest=nCities-1;

        long cost_with_coupon[]=new long[nCities];Arrays.fill(cost_with_coupon, INF);
        long cost_without_coupon[]=new long[nCities];Arrays.fill(cost_without_coupon, INF);

        cost_with_coupon[src]=0;
        cost_without_coupon[src]=0;

        pq.offer(new Journey(0, 0,false));

        while (!pq.isEmpty()) {
            Journey top=pq.poll();

            int curr=top.toCity;
            long cost_to_reach_curr=top.cost;
            boolean coupon_is_used=top.coupon_used;

            if(//this is just an optimization if statement(even if ignored passes all TCs except 2)
                (coupon_is_used && cost_with_coupon[curr] < cost_to_reach_curr) ||
                (!coupon_is_used && cost_without_coupon[curr] < cost_to_reach_curr) 
            ) continue;

            for(int edge[]:adj.get(curr)){

                int neighNodeNumber=edge[0];
                int cost_to_reach_neigh=edge[1];


                if (coupon_is_used) {
                    if (cost_to_reach_curr+cost_to_reach_neigh<cost_with_coupon[neighNodeNumber]) {
                        cost_with_coupon[neighNodeNumber]=cost_to_reach_curr+cost_to_reach_neigh;
                        pq.offer(new Journey(neighNodeNumber, cost_with_coupon[neighNodeNumber], true));
                    }
                }else{
                    // we have coupon available becoz coupon is not used
                    //use it or dont use it
                    long cost_if_used_coupon=cost_to_reach_curr+(cost_to_reach_neigh/2);
                    long cost_if_not_used_coupon=cost_to_reach_curr+cost_to_reach_neigh;

                    if(cost_if_used_coupon<cost_with_coupon[neighNodeNumber]){
                        cost_with_coupon[neighNodeNumber]=cost_if_used_coupon;
                        pq.offer(new Journey(neighNodeNumber, cost_if_used_coupon, true));
                    }
                    if(cost_if_not_used_coupon<cost_without_coupon[neighNodeNumber]){
                        cost_without_coupon[neighNodeNumber]=cost_if_not_used_coupon;
                        pq.offer(new Journey(neighNodeNumber, cost_if_not_used_coupon, false));
                    }
                }

            }
        }

        print(cost_with_coupon[dest]);


    }

    static class Journey{
        int toCity;
        long cost;
        boolean coupon_used;
        public Journey(int toCity,long cost,boolean coupon_used){
            this.toCity=toCity;
            this.cost=cost;
            this.coupon_used=coupon_used;
        }
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

     static int[][] scan_graph(int nConnections,boolean isWeighted) throws IOException{
        int graph[][]=new int[nConnections][isWeighted?3:2];
        for(int i=0;i<nConnections;i++) graph[i]=scanIntArray(isWeighted?3:2);
        return graph;
    }

    static int djikstra(int g[][], int nNodes, int src, int dest) {//use when all edges r positive
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
