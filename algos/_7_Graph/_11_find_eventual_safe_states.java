package _7_Graph;

import java.util.ArrayList;
import java.util.List;


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
public class _11_find_eventual_safe_states {

    //solution: just add all nodes that doesnt have cycles, eventually remining always will lead to terminal
    public static void main(String[] args) {
        int graph[][]={
            {1,2},
            {2,3},
            {5},
            {0},
            {5},
            {},
            {},
        };
        int nNodes=graph.length;
        boolean hasCycle[]=new boolean[nNodes];
        boolean isVis[]=new boolean [nNodes];
        boolean dfs_done[]=new boolean [nNodes];

        List<Integer>ans=new ArrayList<>();

        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++){
                boolean currHasCycle=hasCycle(graph,isVis,dfs_done,nodeNumber);
                hasCycle[nodeNumber]=currHasCycle;
                if(!currHasCycle) ans.add(nodeNumber);
        }
       System.out.println(ans);
       
    }


    static boolean hasCycle(int graph[][],boolean isVis[],boolean dfs_done[],int nodeNumber){
        isVis[nodeNumber]=true;
        dfs_done[nodeNumber]=true;
        for(int e:graph[nodeNumber]){
            if(!isVis[e]){
                if(hasCycle(graph,isVis,dfs_done,e)) return true;
            }else{
                if(dfs_done[e]) return true;
            }
        }
        dfs_done[nodeNumber]=false;
        return false;
    }
}
