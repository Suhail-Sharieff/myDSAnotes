package _7_Graph._03_Undir_weigh;
/*
Given a weighted, undirected, and connected graph with V vertices and E edges, your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph. The graph is represented by an adjacency list, where each element adj[i] is a vector containing vector of integers. Each vector represents an edge, with the first integer denoting the endpoint of the edge and the second integer denoting the weight of the edge.

Input:
3 3
0 1 5
1 2 3
0 2 1
      
     5   
   0-----1
    \    |             =>  correspoding MST  is:     0    1
   1 \   | 3                                          \   | 3        sumOfWts:1+3=4
        2                                            1 \  |   
                                                         2    
Output: 4
Explanation:

The Spanning Tree resulting in a weight
of 4 is shown above.
Input: 
2 1
0 1 5

  

Output: 5 

Explanation: Only one Spanning Tree is possible which has a weight of 5.
Constraints:
2 ≤ V ≤ 1000
V-1 ≤ E ≤ (V*(V-1))/2
1 ≤ w ≤ 1000
The graph is connected and doesn't contain self-loops & multiple edges.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class _04_minimum_spanning_tree {

    public static void main(String[] args) {
        int graph[][] = {
                { 0, 1, 5 },
                { 1, 2, 3 },
                { 0, 2, 1 }
        };
        int nNodes = 3;
        get_mst_graph(nNodes, graph);

    }

    // PRIM"s ALGORITHM: as same as BFS, diffrences are:
    // ---mark visited while polling out and not while pushing into Queue
    // ---dont mark 0 as visited bfr running while loop
    // --compulsory use PQ rather than just Q, coz the beaulty of algorithm is to
    // encounter nodes having min weights first mark them vis while polling, and
    // then encounter others
    // the algorithm can be used to get edges of MST and also min sum of all edges,
    // ie MST will have N nodees N-1 edges, basically MST adj list has config such
    // that all nodes r connected and also has less total sum than compared to
    // normal graph

    // intutuion and why marking while polling: just push all unvis neighboureers
    // into PQ, while polling PQ automatically gives the neigh with lowest wight,
    // hence its best among all options we have , hence must be a part of MST

    static List<int[]> get_mst_graph(int nNodes, int graph[][]) {

        List<int[]> mst_edges = new ArrayList<>();
        List<List<int[]>> adj = get_adj(nNodes, graph);
        boolean isVis[] = new boolean[nNodes];
        int src = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        pq.offer(new int[] { -1, src, 0 });// store format:[parent,node,wtWithParent]
        // isVis[0]=true;-------BIG MISTAKE--no need here

        int ans = 0;

        while (!pq.isEmpty()) {

            int front[] = pq.poll();
            int parentOfCurr = front[0];
            int curr = front[1];
            int wt = front[2];

            if (isVis[curr])
                continue;

            if (parentOfCurr != -1)
                mst_edges.add(new int[] { parentOfCurr, curr });// if condition coz initially we have parent as -1, we
                                                                // dont need it in our list
            isVis[curr] = true;
            ans += wt;

            for (int neigh[] : adj.get(curr)) {

                int neighbourNodeNumber = neigh[0];
                int wtOfCurrWithThatNeighBour = neigh[1];

                pq.offer(new int[] { curr, neighbourNodeNumber, wtOfCurrWithThatNeighBour });
                // isVis[neighbourNodeNumber]=true;--------BIG MISTAKE, mark visited only while
                // polling, not while pushing

            }
        }
        System.out.println("The Minimum spanning tree of given graph has the following adjacency list:");
        for (var e : mst_edges)
            System.out.println(Arrays.toString(e));
        System.out.println("The sum of all weights in MST of given graph  is " + ans);
        return mst_edges;
    }

    static List<List<int[]>> get_adj(int nNodes, int graph[][]) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < nNodes; i++)
            adj.add(new ArrayList<>());
        for (int[] each : graph) {
            adj.get(each[0]).add(new int[] { each[1], each[2] });
            adj.get(each[1]).add(new int[] { each[0], each[2] });
        }
        return adj;
    }

    // -------------------using Kruskal-----uses DSU:
    static class Solution {
        static int spanningTree(int n, int E, List<List<int[]>> adj) {
            List<int[]> edges = new ArrayList<>();

            // Collect all edges
            for (int u = 0; u < n; u++) {
                for (int[] pair : adj.get(u)) {
                    int v = pair[0], wt = pair[1];
                    if (u < v) { // To avoid adding both (u,v) and (v,u)
                        edges.add(new int[] { u, v, wt });
                    }
                }
            }

            // Sort edges by weight
            Collections.sort(edges, (a, b) -> a[2] - b[2]);

            dsu dsu = new dsu(n);
            int mstWeight = 0;
            int edgesUsed = 0;

            List<int[]> mstEdges = new ArrayList<>();

            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];

                if (dsu.up(u) != dsu.up(v)) {
                    dsu.join(u, v);
                    mstWeight += wt;
                    edgesUsed++;
                    mstEdges.add(new int[] { u, v, wt });
                    if (edgesUsed == n - 1)
                        break; // MST done
                }
            }

            return mstWeight;
        }

        static class dsu {
            int[] parent, size;

            public dsu(int n) {
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            public int up(int node) {//ultimate parent
                if (parent[node] == node)
                    return node;
                return parent[node] = up(parent[node]); // Path compression
            }

            public void join(int u, int v) {
                int pu = up(u), pv = up(v);
                if (pu == pv)
                    return;

                if (size[pu] < size[pv]) {
                    parent[pu] = pv;
                    size[pv] += size[pu];
                } else {
                    parent[pv] = pu;
                    size[pu] += size[pv];
                }
            }
        }
    }

}
