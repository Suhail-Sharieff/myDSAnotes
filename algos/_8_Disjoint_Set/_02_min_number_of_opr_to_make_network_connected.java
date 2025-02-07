package _8_Disjoint_Set;

import java.util.ArrayList;
import java.util.List;

/*
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

 

Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
 

Constraints:

1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.
 */
public class _02_min_number_of_opr_to_make_network_connected {
    public static void main(String[] args) {
        int connections[][]={
            {0,1},
            {0,2},
            {0,3},
            {1,2},
            {1,3},
        };
        System.out.println(using_DSU(connections, 6));
    }

    //=------------intutuon is to find number of isolated computers, since each isolated computer minimum requres 1 cable with other, so for n isolated computers, we need minimum 1 cable, so if there r n isolated computers, then we minimum need n cables to get all isolated computers get connected. Also if there r n total computers, WKT we need min n-1 edges to connect all computers, if we ind that nEdges is less than n-1, we can nvever get all computers connected so then return -1. And if its possble to get all computers connected , use the appraoch as u used in noOfISlands problem in connections, get number of totoal components, say its k, then obviosuly there must be (k-1) isolated computers , why (k-1), becozuse that 1 is nothing but cluster of connected computers. Now since we have (k-1) isolated computers, we will need (k-1) cables at minimu to connect all computers
    public int brute_force(int nComputers, int[][] connections) {
        int nEdges = connections.length;
        if (nEdges < nComputers - 1)
            return -1;

        boolean isVis[] = new boolean[nComputers];
        int cnt = 0;//no of islands

        List<List<Integer>>adj=get_adj(connections,nComputers);
        for (int i = 0; i < nComputers; i++) {
            if (!isVis[i]) {
                bfs(adj, isVis, i);
                cnt++;
            }
        }
        return cnt - 1;//coz the 1 is cluster of connected computers
    }

    public void bfs(List<List<Integer>>adj, boolean isVis[], int i) {
            isVis[i] = true;
            for (int neigh : adj.get(i)) {
               if(!isVis[neigh]) bfs(adj,isVis,neigh);
            }
    }

    public List<List<Integer>>get_adj(int connections[][],int nComputers){
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<nComputers;i++) adj.add(new ArrayList<>());
        for(int each[]:connections){
            adj.get(each[0]).add(each[1]);
            adj.get(each[1]).add(each[0]);
        }
        return adj;
    }



    ////////////////--------------------------Using DSU-----OPTIMAL
    static int using_DSU(int connections[][],int nComputers){
        int nEdges = connections.length;
        if (nEdges < nComputers - 1)
            return -1;
        DSU dsu=new DSU(nComputers);
        for(int edge[]:connections){
            dsu.join(edge[0], edge[1]);
        }
        int ans= dsu.get_number_of_connected_components()-1;
        return ans;
    }
}
