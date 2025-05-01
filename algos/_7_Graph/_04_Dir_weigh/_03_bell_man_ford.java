package _7_Graph._04_Dir_weigh;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
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
import java.util.Deque;
import java.util.List;
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


//since they have askjed max score, we will apply normal bellman but make negative edges,it will give most negative vvalue, ans is -1*thatValue, handle edge cases
    static 

     class HighScore {
        public static void main(String[] args) throws IOException {
            // int t = scanInt();
            // while (t-- > 0) {
            solve();
            // }
        }
    
        public static void solve() throws IOException {
            int nRooms = scanInt(), nConns = scanInt();
            int[][] edges = new int[nConns][3];
            // adjacency for reverse graph
            List<List<Integer>> rev = new ArrayList<>();
            for (int i = 0; i < nRooms; i++) rev.add(new ArrayList<>());
            for (int i = 0; i < nConns; i++) {
                edges[i][0] = scanInt() - 1;
                edges[i][1] = scanInt() - 1;
                edges[i][2] = scanInt();
                // build reverse adjacency
                rev.get(edges[i][1]).add(edges[i][0]);
            }
        
            long[] dis = new long[nRooms];
            Arrays.fill(dis, INF);
            dis[0] = 0;
            // Bellman-Ford on negated weights
            for (int i = 0; i < nRooms - 1; i++) {
                for (int[] e : edges) {
                    int u = e[0], v = e[1], w = -e[2];
                    if (dis[u] != INF) {
                        dis[v] = Math.min(dis[v], dis[u] + w);
                        dis[v] = Math.max(-INF, dis[v]);
                    }
                }
            }
            long answer = -dis[nRooms - 1];
        
            // extra pass: mark cycle nodes
            boolean[] inCycle = new boolean[nRooms];
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = -e[2];
                if (dis[u] != INF && dis[u] + w < dis[v]) {
                    inCycle[v] = true;
                }
            }
        
            // DFS/BFS from n-1 on rev graph to find nodes that can reach n
            boolean[] canReachN = new boolean[nRooms];
            Deque<Integer> dq = new ArrayDeque<>();
            dq.add(nRooms - 1);
            canReachN[nRooms - 1] = true;
            while (!dq.isEmpty()) {
                int x = dq.poll();
                for (int p : rev.get(x)) {
                    if (!canReachN[p]) {
                        canReachN[p] = true;
                        dq.add(p);
                    }
                }
            }
        
            // if any inCycle node can reach n, infinite answer
            for (int i = 0; i < nRooms; i++) {
                if (inCycle[i] && canReachN[i]) {
                    print(-1);
                    return;
                }
            }
            print(answer);
        }
        
    
        static void relaxEdges(int u, int v, int w, long dis[]) {
            if (dis[u] != INF) {
                dis[v] = Math.min(w + dis[u], dis[v]);
                // dis[v] = Math.max(-INF, dis[v]);
            }
        }
    
        static boolean cycle_present(long dis[], int g[][], int n) throws IOException {
            long clone[] = dis.clone();
            for (int i = 0; i < n-1; i++) {
                for (int edge[] : g)
                relaxEdges(edge[0] - 1, edge[1] - 1, -1 * edge[2], clone);
            }
            for (int i = 0; i < n; i++) {
                if (clone[i] != dis[i]) {
                    print(Arrays.toString(clone));
                    return true;
                }
            }
    
            return false;
    
        }
    
        static long INF = Long.MAX_VALUE;
    
        static int MOD = 1_000_000_007;
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
    
        static List<List<int[]>> get_adj(int graph[][], int nNodes) {
            List<List<int[]>> adj = new ArrayList<>();
            for (int i = 0; i < nNodes; i++)
                adj.add(new ArrayList<>());
            for (int con[] : graph) {
                adj.get(con[0] - 1).add(new int[] { con[1] - 1, -1 * con[2] });
            }
            return adj;
        }
    
    }
    
}


//example problem:

