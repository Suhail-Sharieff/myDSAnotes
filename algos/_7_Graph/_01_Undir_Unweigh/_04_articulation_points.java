package _7_Graph._01_Undir_Unweigh;

import java.util.ArrayList;
/*
Given an undirected connected graph with V vertices and adjacency list adj. You are required to find all the vertices removing which (and edges through it) disconnects the graph into 2 or more components(so called articulation points) and return it in sorted manner.
Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.

Example 1:

Input:

Output:{1,4}
Explanation: Removing the vertex 1 will
discconect the graph as-

Removing the vertex 4 will disconnect the
graph as-

 

Your Task:
You don't need to read or print anything. Your task is to complete the function articulationPoints() which takes V and adj as input parameters and returns a list containing all the vertices removing which turn the graph into two or more disconnected components in sorted order. If there are no such vertices then returns a list containing -1.
 

Expected Time Complexity: O(V + E)
Expected Auxiliary Space: O(V)
 

Constraints:
1 ≤ V ≤ 105
 */


 //solution: almost as same as the code for previous code with lill conditions and changes
 //watch for logic:
 //https://www.youtube.com/watch?v=j1QDfU21iZk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=56&ab_channel=takeUforward
import java.util.*;

class _04_articulation_points {
    static int timer;
    static boolean[] visited;
    static int[] tin, low;
    static ArrayList<ArrayList<Integer>> adj;
    static HashSet<Integer> articulationPoints;

    static ArrayList<Integer> articulationPoints(int nv, int[][] edges) {
        buildAdj(nv, edges);

        timer = 0;
        visited = new boolean[nv];
        tin = new int[nv];
        low = new int[nv];
        articulationPoints = new HashSet<>();

        for (int i = 0; i < nv; i++) {
            if (!visited[i])
                dfs(i, -1);
        }

        ArrayList<Integer> ans = new ArrayList<>(articulationPoints);
        Collections.sort(ans);
        if (ans.isEmpty()) ans.add(-1);
        return ans;
    }

    static void buildAdj(int nv, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i < nv; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
    }

    static void dfs(int u, int parent) {
        visited[u] = true;
        tin[u] = low[u] = timer++;
        int childCount = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;
            if (!visited[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                // Articulation condition
                //u----v
                if (parent != -1 && tin[u]<=low[v] )
                    articulationPoints.add(u);

                childCount++;
            } else {
                // back edge
                low[u] = Math.min(low[u], tin[v]);
            }
        }

        // Special case for root
        if (parent == -1 && childCount > 1)
            articulationPoints.add(u);
    }

    public static void main(String[] args) {
        int nv = 5;
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}
        };

        System.out.println(articulationPoints(nv, edges));
        // Output: [1, 3]
    }
}
