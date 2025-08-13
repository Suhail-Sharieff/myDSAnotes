package _7_Graph._01_Undir_Unweigh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given an adjacency list of a graph adj. Check whether the graph is bipartite or not.

A bipartite graph can be colored with two colors such that no two adjacent vertices share the same color. This means we can divide the graph’s vertices into two distinct sets where:

All edges connect vertices from one set to vertices in the other set.
No edges exist between vertices within the same set.

NOTE: U may be given disjoint graphs as well ie 2 or more graphs that r independednt, return true iff each of them is bipartite

Examples:

Input: adj = [[1], [0,2], [1]]
Bipartite-Graph
Output: true
Explanation: The given graph can be colored in two colors so, it is a bipartite graph.
Input: adj = [[2, 3], [2], [0, 1, 3], [0, 2]]




Output: false 
Explanation: The given graph cannot be colored in two colors such that color of adjacent vertices differs. 
Constraints:
1 ≤ adj.size() ≤ 104
1 ≤ adj[i][j] ≤ 104
 */
public class _02_bipartite_graph {

    //solution: pls checkout recursion/_9_graph_coloring as well for good understanding, but here bipartitemeans we choose 2 colors(0 and 1), go through each node, curroupt all its neighbours and paint them wrt to baseNode ie if base node has 0 we paint neighbour as 1 and vice versa, at any point of time we find that the node is already visited and its color is as same as the base/parent node, ie obviously it has violtaed bipartite condition, so return false;

    public static void main(String[] args) {
        List<List<Integer>>adj=List.of(
            List.of(1),
            List.of(0,2),
            List.of(1)
        );
        isBipartite(adj);
    }

    static boolean isBipartite(List<List<Integer>> adj) {
        // actually we could have just returned bfs function directly, but given that we can have excpect to have even disjoint graphs, we run through each node in adj list if its not visited and make sure each of separtae graphs(if disjoints r present) is bipartite
        int nNodes=adj.size();
        int color[]=new int[nNodes];
        Arrays.fill(color,-1);
        
        boolean isVis[]=new boolean[nNodes];
        
        for(int node=0;node<nNodes;node++){
            if(!isVis[node]){
                boolean currIsBipartite=bfs(adj,isVis,color,node);
                if(!currIsBipartite) return false;
            }
        }

        //to check colors of each node:
        for(int i=0;i<nNodes;i++) System.out.println("Color of "+i+"th node is "+color[i]);

        return true;
    }
    
    static boolean bfs(List<List<Integer>> adj,boolean isVis[],int color[],int currNode){
        Queue<Integer>q=new LinkedList<>();
        q.offer(currNode);
        isVis[currNode]=true;
        color[currNode]=0;
        while(!q.isEmpty()){
            int top=q.poll();
            for(int neighBour:adj.get(top)){
                if(!isVis[neighBour]){
                    q.offer(neighBour);
                    isVis[neighBour]=true;
                    if(color[top]==0){
                        color[neighBour]=1;
                    }
                    else if(color[top]==1){
                        color[neighBour]=0;
                    }
                }else{
                    if(color[neighBour]==color[top]) return false;
                }
            }
        }
        return true;
    }

    /*Other way: 
     class Solution {
    int color[];
    boolean isV[];
    int[][]g;
    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        g=graph;
        color=new int[n];
        isV=new boolean[n];
        for(int u=0;u<n;u++) if(!isV[u]) if(!is_bipar(u)) return false;
        return true;
    }
    boolean is_bipar(int u){
        isV[u]=true;
        for(int v:g[u]) {
            if(!isV[v]){
                color[v]=color[u]^0^1;
                if(!is_bipar(v)) return false;

            }else{
                if(color[u]==color[v]) return false;
            }
        }
        return true;
    }
}
    */

}
