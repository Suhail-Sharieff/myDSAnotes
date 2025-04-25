package _8_Disjoint_Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class _07_reductant_conections {

    // -----------brute: try apparently removing each edge, if nIslands remain same, its safe to remove it to make it tree
    static class Solution {

        private static List<List<Integer>> adj;

        public int[] findRedundantConnection(int[][] edges) {

            int n = edges.length;
            adj = getAdj(edges, n);
            int ini = nIslands(n);

            int ans[] = { -1, -1 };

            for (int i = 0; i < n; i++) {
                int temp[] = edges[i].clone();
                edges[i] = new int[] {};//removing edge
                adj = getAdj(edges, n);
                int k = nIslands(n);
                if (k == ini) {
                    ans = temp.clone();
                }
                edges[i] = temp.clone();//restoring edge
            }

            if (ans[0] == -1)
                return new int[] {};
            return ans;
        }

        public int nIslands(int n) {
            boolean isVis[] = new boolean[n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (!isVis[i]) {
                    dfs(i, isVis);
                    cnt++;
                }
            }
            return cnt;
        }

        public void dfs(int curr, boolean isVis[]) {
            isVis[curr] = true;
            for (int neigh : adj.get(curr)) {
                if (!isVis[neigh]) {
                    dfs(neigh, isVis);
                }
            }
        }

        public List<List<Integer>> getAdj(int[][] g, int n) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 1; i <= n; i++)
                adj.add(new ArrayList<>());
            for (var e : g) {
                if (e.length == 0)
                    continue;
                adj.get(e[0] - 1).add(e[1] - 1);
                adj.get(e[1] - 1).add(e[0] - 1);
            }
            return adj;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int node1 = -1, node2 = -1;
        DSU dsu = new DSU(n + 1);

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (dsu.find(u) != dsu.find(v)) {
                dsu.union(u, v);
            } else {
                node1 = u;
                node2 = v;
            }
        }
        return new int[] { node1, node2 };
    }

    static class DSU {
        int p[];
        int sz[];

        public DSU(int n) {
            p = new int[n];
            sz = new int[n];
            Arrays.fill(sz, 1);
            for (int i = 0; i < n; i++)
                p[i] = i;
        }

        public int find(int x) {
            if (p[x] != x)
                return p[x] = find(p[x]);
            return p[x];
        }

        public void union(int u, int v) {
            int rx = find(u);
            int ry = find(v);
            if (sz[rx] >= sz[ry]) {
                p[ry] = rx;
                sz[rx] += sz[ry];
            } else if (sz[ry] > sz[rx]) {
                p[rx] = ry;
                sz[ry] += sz[rx];
            }
        }
    }
}
