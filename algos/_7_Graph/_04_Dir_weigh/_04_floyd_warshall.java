package _7_Graph._04_Dir_weigh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
Thia algo is different fromt that of dikstra or bell man(which is used to find the shortest path from single src to all nodes). This alog cn determine all shortest distances from all nodes ie multiple srcs and also detect cycles as well
 */
/*
The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. The graph is represented as an adjacency matrix. mat[i][j] denotes the weight of the edge from i to j. If mat[i][j] = -1, it means there is no edge from i to j.
Note: Modify the distances for every pair in place.

Examples :

Input: mat = [[0, 25], [-1, 0]]

Output: [[0, 25], [-1, 0]]

Explanation: The shortest distance between every pair is already given(if it exists).
Input: mat = [[0, 1, 43],[1, 0, 6], [-1, -1, 0]]

Output: [[0, 1, 7], [1, 0, 6], [-1, -1, 0]]

Explanation: We can reach 2 from 0 as 0->1->2 and the cost will be 1+6=7 which is less than 43.
Constraints:
1 <= mat.size() <= 100
-1 <= mat[ i ][ j ] <= 1000

Company Tags
Samsung
Topic Tags
Related Articles
 */
public class _04_floyd_warshall {
    public static void main(String[] args) {
        int graph[][] = {
                { 0, 1, 45 }, // meaning[{0 to 0 =0, 0 to 1 =1 , 0 to 2 = 45}]
                { 1, 0, 6 }, // meaning [{1 to 0 =1 , 1 to 1 =0, 1 to 2=6 }]
                { -1, -1, 0 }// meaning [{2 to 0 =INF, 2 to1 =INF, 2 to 2 =0}]
        };
        // Code here
        get_distances_mat(graph);

    }

    // Floyd warshall: just like brute force, for each assumed via node, check and
    // take min of [from][to], using
    // mat[from][to]+min(mat[from][to],mat[from][via],mat[via][to]:)
    // https://www.youtube.com/watch?v=YbY8cVwWAvw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=42&ab_channel=takeUforward
    public static int[][] get_distances_mat(int graph[][]) {// O(n^3)
        int nNodes = graph.length;

        for (int i = 0; i < nNodes; i++)
            for (int j = 0; j < nNodes; j++)
                if (graph[i][j] == -1)
                    graph[i][j] = (int) (1e9);// NOTE: dont use Integer.MAX_VALUE, coz later n loop u will be taking min
                                              // by adding some other number to it whivh mayy result in integer overflow

        for (int via = 0; via < nNodes; via++) {
            for (int i = 0; i < nNodes; i++) {
                for (int j = 0; j < nNodes; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][via] + graph[via][j]);
                }
            }
        }
        for (int i = 0; i < nNodes; i++)
            for (int j = 0; j < nNodes; j++)
                if (graph[i][j] == (int) (1e9))
                    graph[i][j] = -1;

        Arrays.stream(graph).forEach(row -> System.out
                .println(Arrays.toString(Arrays.stream(row).map(e -> (e == Integer.MAX_VALUE) ? -1 : e).toArray())));
        return graph;
    }

    public boolean hasCycles(int graph[][]) {
        int distances[][] = get_distances_mat(graph);
        for (int i = 0; i < distances.length; i++)
            if (distances[i][i] < 0)
                return true;
        return false;
    }

    // ------------------------------IMP: how u can still optimize floyd:
    static class OptimizedFloyd {
        /*There are n cities and m roads between them. Your task is to process q queries where you have to determine the length of the shortest route between two given cities.
Input
The first input line has three integers n, m and q: the number of cities, roads, and queries.
Then, there are m lines describing the roads. Each line has three integers a, b and c: there is a road between cities a and b whose length is c. All roads are two-way roads.
Finally, there are q lines describing the queries. Each line has two integers a and b: determine the length of the shortest route between cities a and b.
Output
Print the length of the shortest route for each query. If there is no route, print -1 instead.
Constraints

1 \le n \le 500
1 \le m \le n^2
1 \le q \le 10^5
1 \le a,b \le n
1 \le c \le 10^9

Example
Input:
4 3 5
1 2 5
1 3 9
2 3 3
1 2
2 1
1 3
1 4
3 2

Output:
5
5
8
-1
3 */
        public static void main(String[] args) throws IOException {
            // int t = scanInt();
            // while (t-- > 0) {
            solve();
            // }
        }

        public static void solve() throws IOException {
            int nCities = scanInt();
            int nConns = scanInt();
            int nQ = scanInt();

            long INF = (long) 1e15;

            int g[][] = new int[nConns][3];
            for (int i = 0; i < nConns; i++)
                g[i] = scanIntArray(3);

            long dis[][] = new long[nCities][nCities];
            for (int i = 0; i < nCities; i++) {
                Arrays.fill(dis[i], INF);
                dis[i][i] = 0;
            }

            for (int[] e : g) {
                dis[e[0] - 1][e[1] - 1] = Math.min(dis[e[0] - 1][e[1] - 1], e[2]);//why min, just e[2] wasnt enough aah?---consider a smart test case:
                /*
2 2 1
1 2 1
1 2 2
1 2
 see that first they said between 1st and 2nd node the wt is 1, then again they said 2 for the same edge, consider min of both
                 */
                dis[e[1] - 1][e[0] - 1] = Math.min(dis[e[1] - 1][e[0] - 1], e[2]);

            }

            // for(var r:dis) System.out.println(Arrays.toString(r));

            for (int k = 0; k < nCities; k++) {//loop optimizations
                long[] disK = dis[k]; // row k
                for (int i = 0; i < nCities; i++) {
                    long dik = dis[i][k]; // d(i,k)
                    if (dik == INF)
                        continue; // no need to try any j if i→k unreachable
                    long[] disI = dis[i]; // row i
                    // now only one array‐bounds check per iteration, no Math.min call
                    for (int j = 0; j < nCities; j++) {
                        long alt = dik + disK[j];
                        if (alt < disI[j]) {
                            disI[j] = alt;
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            while (nQ-- != 0) {
                int i = scanInt();
                int j = scanInt();
                long ans = dis[i - 1][j - 1];
                if (ans == INF)
                    sb.append(-1);
                else
                    sb.append(ans);
                sb.append(" ");
            }

            print(sb);

        }

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
            for (int i = 0; i <= nNodes; i++)
                adj.add(new ArrayList<>());
            for (int con[] : graph) {
                adj.get(con[0] - 1).add(new int[] { con[1] - 1, con[2] });
                adj.get(con[1] - 1).add(new int[] { con[0] - 1, con[2] });
            }
            return adj;
        }

    }

}
