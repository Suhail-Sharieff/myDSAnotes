/*Time limit: 1.00 s
Memory limit: 512 MB



Byteland has n cities and m roads between them. Your task is to design a round trip that begins in a city, goes through two or more other cities, and finally returns to the starting city. Every intermediate city on the route has to be distinct.
Input
The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,\dots,n.
Then, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
Every road is between two different cities, and there is at most one road between any two cities.
Output
First print an integer k: the number of cities on the route. Then print k cities in the order they will be visited. You can print any valid solution.
If there are no solutions, print "IMPOSSIBLE".
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 6
1 3
1 2
5 3
1 5
2 4
4 5

Output:
4
3 5 1 3 */
package some_preq._06_cses._04_graph;
//this is basically yo print cycle:
import java.io.*;
import java.util.*;

public class _04_round_trip {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] parent;
    static int cycleStart = -1, cycleEnd = -1;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // Fast input reading
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Initialize adjacency list
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        visited = new boolean[n + 1];
        parent = new int[n + 1];

        // Perform DFS to find a cycle
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) {
                    break;
                }
            }
        }

        if (cycleStart == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            List<Integer> cycle = new ArrayList<>();
            cycle.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                cycle.add(v);
            }
            cycle.add(cycleStart);
            Collections.reverse(cycle);
            System.out.println(cycle.size());
            for (int v : cycle) {
                System.out.print(v + " ");
            }
        }
    }

    static boolean dfs(int v, int p) {
        visited[v] = true;
        for (int u : adj[v]) {
            if (u == p) continue; // Skip the edge to parent
            if (visited[u]) {
                cycleStart = u;
                cycleEnd = v;
                return true;
            }
            parent[u] = v;
            if (dfs(u, v)) return true;
        }
        return false;
    }
}
