package _7_Graph._01_Undir_Unweigh;
//prereq: _02_Dir_Unweigh/_06_strongly_connected_componenets

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

TARJAN ALGORITHM


There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.

 */
public class _03_number_of_critical_connections {

    public static void main(String[] args) {
        int edges[][] = {
                { 0, 1 },
                { 1, 2 },
                { 2, 0 },
                { 1, 3 }
        };
        int nNodes = 4;
        int adj[][] = get_adj(nNodes, edges);
        brute_force(nNodes, adj);
        // optimal_tarjan(adj, nNodes);
    }

    public static int[][] get_adj(int nNodes, int edges[][]) {
        List<List<Integer>> temp = new ArrayList<>();
        int adj[][] = new int[nNodes][];
        while (nNodes-- != 0)
            temp.add(new ArrayList<>());
        for (int each[] : edges) {
            temp.get(each[0]).add(each[1]);
            temp.get(each[1]).add(each[0]);
        }
        for (int i = 0; i < temp.size(); i++) {
            adj[i] = temp.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return adj;
    }

    // --------------------------brute force: try removing each edge, check if
    // number of connected componnets/islands r incresing or not, if increasing then
    // that must be a critical edge
    public static List<List<Integer>> brute_force(int nNodes, int[][] adj) {// O(e*(v+e))----TLE
        int initialNumber_of_componenets = get_nConnected_components(nNodes, adj);
        int nCriticalEdges = 0;
        List<List<Integer>> li = new ArrayList<>();
        for (int nodeNumber = 0; nodeNumber < nNodes; nodeNumber++) {
            for (int edge_number = 0; edge_number < adj[nodeNumber].length; edge_number++) {
                int temp = adj[nodeNumber][edge_number];
                adj[nodeNumber][edge_number] = -1;
                int new_number_of_components = get_nConnected_components(nNodes, adj);
                if (new_number_of_components > initialNumber_of_componenets) {
                    nCriticalEdges++;
                    li.add(Arrays.asList(nodeNumber, temp));
                }
                adj[nodeNumber][edge_number] = temp;
            }
        }
        System.out.println("Crtical edges are:");
        System.out.println(li);
        System.out.println("Number of critical edges: " + nCriticalEdges);
        return li;
    }

    static int get_nConnected_components(int nNodes, int adj[][]) {
        boolean isVis[] = new boolean[nNodes];
        int nIslands = 0;
        for (int nodeNumber = 0; nodeNumber < nNodes; nodeNumber++) {
            if (!isVis[nodeNumber]) {
                nIslands++;
                dfs(adj, nodeNumber, isVis);
            }
        }
        return nIslands;
    }

    static void dfs(int adj[][], int nodeNumber, boolean isVis[]) {
        if (!isVis[nodeNumber]) {
            isVis[nodeNumber] = true;
            for (int neigh : adj[nodeNumber]) {
                if (neigh != -1)
                    dfs(adj, neigh, isVis);
            }
        }
    }

    // -----------------------u can even think of an Kosaraju, coz it works on
    // principle that u isolate SCCs, if there r n SCCs then the edges
    // connecdiscoveryg SCCs r obviosuly critical edges, so u can return (nSccs-1)
    // since there must be (n-1) edges connecdiscoveryg n SCCs, BUT BUT BUT mind
    // that we r given an undirected grpah and Kosaraju works only for DG, f we
    // apply Kosaaju hee, we will get 0 SCCs coz in an undirected graph we can move
    // from any node to any other node since we dont have any direction issues, so
    // this prblem is solved using Tarjan's algo that can work for undir

    // ---------------optimal using Tarjan ALGO:
    // https://www.youtube.com/watch?v=RYaakWv5m6o&t=1706s&ab_channel=TechRevisions
    // https://www.youtube.com/watch?v=CiDPT1xMKI0&t=748s&ab_channel=CodeHelp-byBabbar

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        List<List<Integer>> adj = get_adj(n, edges);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(adj, ans, new int[n], new int[n], new boolean[n], 0, -1);
        return ans;

    }

    private int order = 1;

    public void dfs(List<List<Integer>> adj, List<List<Integer>> ans, int traversal_order[], int nearest_ancestor[],
            boolean isVis[], int currNodeNumber, int parentOfCurr) {
        isVis[currNodeNumber] = true;
        traversal_order[currNodeNumber] = order;
        nearest_ancestor[currNodeNumber] = order;
        order++;
        for (int nbr : adj.get(currNodeNumber)) {

            if (nbr == parentOfCurr)
                continue;

            if (!isVis[nbr]) {

                dfs(adj, ans, traversal_order, nearest_ancestor, isVis, nbr, currNodeNumber);

                // the below code is exceuted while backtracking, now the node from which it
                // came is nbr ie ulta
                nearest_ancestor[currNodeNumber] = Math.min(
                        nearest_ancestor[currNodeNumber],
                        nearest_ancestor[nbr]);
                // after updating
                if (nearest_ancestor[currNodeNumber] != nearest_ancestor[nbr]) {
                    if (nearest_ancestor[nbr] > traversal_order[currNodeNumber]) {// currNodeNumber was trvaersed bfr its neighbour
                        ans.add(Arrays.asList(currNodeNumber, nbr));
                    }
                }
            } else {
                nearest_ancestor[currNodeNumber] = Math.min(
                        nearest_ancestor[currNodeNumber],
                        nearest_ancestor[nbr]);
            }
        }
    }

    public List<List<Integer>> get_adj(int n, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        while (n-- != 0)
            adj.add(new ArrayList<>());
        for (var e : edges) {
            adj.get(e.get(0)).add(e.get(1));
            adj.get(e.get(1)).add(e.get(0));
        }
        return adj;
    }
}