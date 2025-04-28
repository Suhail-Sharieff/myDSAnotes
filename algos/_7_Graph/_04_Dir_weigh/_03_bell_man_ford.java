package _7_Graph._04_Dir_weigh;
/*
Given a weighted and directed graph of v vertices and edges, Find the shortest distance of all the vertex's from the source vertex, src and return a list of integers where the ith integer denotes the distance of the ith node from the source node. If a vertices can't be reach from the s then mark the distance as 10^8.
Note: If there exist a path to a negative weighted cycle from the source node then return {-1}.

Examples:

Input: edges = [[0,1,9]], src = 0

Output: [0, 9]
Explanation: Shortest distance of all nodes from source is printed.
Input: edges = [[0,1,5], [1,0,3], [1,2,-1], [2,0,1]], src = 2

Output: [1, 6, 0]
Explanation: For nodes 2 to 0, we can follow the path: 2-0. This has a distance of 1. For nodes 2 to 1, we cam follow the path: 2-0-1, which has a distance of 1+5 = 6,
Constraints:
1 ≤ V ≤ 500
1 ≤ E ≤ V*(V-1)
-1000 ≤ data of nodes, weight ≤ 1000
0 ≤ S < V
 */
import java.util.Arrays;


/*
https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=43&ab_channel=takeUforward

Bell man ford is almost similar to djikstra, but it can even hanle the cases where the edge weight may be negative.
- do n-1 iterations and update dis for each in edges. (n-1) coz we realax or build shortest distance to 1 node at a time
- to check if cycle exists, do one more iteration ie nth iteration, if any value changes(prolly decrease) in dis array compared to previous version, there's a cycle, else no
 */

public class _03_bell_man_ford {
    static int[] bellmanFord(int n, int[][] edges, int src) {
        int nNodes=n;
        int dis[]=new int[nNodes];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src]=0;
        for(int i=0;i<nNodes-1;i++){
            for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],dis);
        }
        if(hasCycles(edges,dis)) return new int[]{-1};
        return dis;
    }
    static void relaxEdges(int u,int v,int wt,int dis[]){
        //observe that first condition is extra in this---VVIMP
        if(dis[u]!=Integer.MAX_VALUE && dis[u]+wt<dis[v]) dis[v]=dis[u]+wt;
    }
    static boolean hasCycles(int edges[][],int dis[]){
        int clone[]=dis.clone();
        for(int each[]:edges)
                relaxEdges(each[0],each[1],each[2],clone);
        for(int i=0;i<dis.length;i++) if(dis[i]!=clone[i]) return true;
        return false;
    }
}
