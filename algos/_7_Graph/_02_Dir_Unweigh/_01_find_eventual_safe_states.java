package _7_Graph._02_Dir_Unweigh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//prereq: detect cycle in an undirected graph

/*
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

NOTE: there should be no cycles in graph [ALWAYS KEEP THIS IN MIND,though there can be some nodes that lead to terminal, but they can also lead to themselves, ie they have cycle, dont add such nodes]-------VVIMP

Example 1:

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 

Constraints:

n == graph.length
1 <= n <= 104
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 104].
 */
public class _01_find_eventual_safe_states {

    // solution: just add all nodes that doesnt have cycles, eventually remining
    // always will lead to terminal
    public static void main(String[] args) {
        int graph[][] = {
                { 1, 2 },
                { 2, 3 },
                { 5 },
                { 0 },
                { 5 },
                {},
                {},
        };
        int nNodes = graph.length;
        boolean hasCycle[] = new boolean[nNodes];
        boolean isVis[] = new boolean[nNodes];
        boolean dfs_done[] = new boolean[nNodes];

        List<Integer> ans = new ArrayList<>();

        for (int nodeNumber = 0; nodeNumber < nNodes; nodeNumber++) {
            boolean currHasCycle = hasCycle(graph, isVis, dfs_done, nodeNumber);
            hasCycle[nodeNumber] = currHasCycle;
            if (!currHasCycle)
                ans.add(nodeNumber);
        }
        System.out.println(ans);

    }

    static boolean hasCycle(int graph[][], boolean isVis[], boolean dfs_done[], int nodeNumber) {
        isVis[nodeNumber] = true;
        dfs_done[nodeNumber] = true;
        for (int e : graph[nodeNumber]) {
            if (!isVis[e]) {
                if (hasCycle(graph, isVis, dfs_done, e))
                    return true;
            } else {
                if (dfs_done[e])
                    return true;
            }
        }
        dfs_done[nodeNumber] = false;
        return false;
    }

    // -------------------------way2:
    class Solution {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            int[] state = new int[n]; // 0: unvisited, 1: visiting, 2: safe
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (dfs(i, graph, state)) {
                    result.add(i);
                }
            }

            return result;
        }

        private boolean dfs(int node, int[][] graph, int[] state) {
            if (state[node] != 0) {
                return state[node] == 2;
            }

            state[node] = 1; // mark as visiting

            for (int neighbor : graph[node]) {
                if (!dfs(neighbor, graph, state)) {
                    return false; // cycle detected
                }
            }

            state[node] = 2; // mark as safe
            return true;
        }
    }


    //-=---------------==========way3: reverseEdgesOrGraqph(then all terminals will have indegree=0)+then Do KahnAlgo

    public List<Integer>usingKahn(int g[][]){
        g=revreseEdges(g);
        Queue<Integer>q=new LinkedList<>();
        int indeg[]=new int[g.length];
        for(int i=0;i<g.length;i++){
           for(int n:g[i]) indeg[n]++;
        }
        for(int i=0;i<g.length;i++) if(indeg[i]==0) q.offer(i);
        List<Integer>ans=new ArrayList<>();
        while(!q.isEmpty()){
            int top=q.poll();
            ans.add(top);
            for(int neigh:g[top]){
                indeg[neigh]--;
                if(indeg[neigh]==0) q.offer(neigh);
            }
        }
        return ans;
    }
    public int[][] revreseEdges(int g[][]){
        List<List<Integer>>rev=new ArrayList<>();
        for(int i=0;i<g.length;i++) rev.add(new ArrayList<>());
        for(int i=0;i<g.length;i++){
            for(int neigh:g[i]) rev.get(neigh).add(i);
        }
        return rev.stream()
              .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
              .toArray(int[][]::new);
    }
}
