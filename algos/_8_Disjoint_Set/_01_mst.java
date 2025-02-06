package _8_Disjoint_Set;
/*

KRUSKAL ALGO

Given a weighted, undirected, and connected graph with V vertices and E edges, your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph. The graph is represented by an adjacency list, where each element adj[i] is a vector containing vector of integers. Each vector represents an edge, with the first integer denoting the endpoint of the edge and the second integer denoting the weight of the edge.

Input:
3 3
0 1 5
1 2 3
0 2 1

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
import java.util.Collections;
import java.util.List;

//prereq: _7_Grpah/_03_Undir_weigh/_04_minimum_spanning_tree----teaches u how to build MST using normal priority Queue
//here we will build MST using Disjoint set data structure

//idea: we will start from the point having smallest edge weight, say we have array where each elemnt has formal {wt,u,v}, where u an v r nodes. We will first creat such array for all nodes, sort it, and then start for each elent in that sorted array, we will check if u and v has same parent or not, if they tend to have same parent(belong to same component), then we will not do anything coz they r already connected,move formaward, but if they tend to have disillar parent, we will connnect u with v, thts all, and his process will repeat and at last u will get a beautiful MST, and since in question we r asked to find the sum of edges oftthis MST, we will add wt if they tend to have different parent whil e traversing in above procesdure. Observe the beauty that oth algos(using PQ an DSU) r tending to start from those edges that r having min weight first bfr others
public class _01_mst {
    public static void main(String[] args) {
        int graph[][]={
            {0,1,5},
            {1,2,3},
            {0,2,1}
        };
        int nNodes=3;
        get_mst(nNodes, graph);
    }

    static int[][]get_mst(int nNodes,int graph[][]){
        List<int[]>adj=get_sorted_adj(nNodes, graph);
        DSU dsu=new DSU(nNodes);
        int sum=0;
        for(int each[]:adj){
            int from=each[0];
            int to=each[1];
            int wt=each[2];
            if(dsu.belong_to_same_component(from, to)) continue;
            dsu.join(from, to);
            sum+=wt;
        }
        System.out.println("MST sum "+sum);

        System.out.println("MST path compressed graph :");

        return dsu.get_path_compressed_adj();
    }

    static List<int[]>get_sorted_adj(int nNodes,int graph[][]){
        List<int[]>adj=new ArrayList<>();
        for(int each[]:graph){
            adj.add(new int[]{each[0],each[1],each[2]});//{from,to,wt}
            adj.add(new int[]{each[1],each[0],each[2]});//------coz its an undirected graph,comment for DG
        }
        Collections.sort(adj,(x,y)->x[2]-y[2]);//sort as per wts
        return adj;
    }
}
