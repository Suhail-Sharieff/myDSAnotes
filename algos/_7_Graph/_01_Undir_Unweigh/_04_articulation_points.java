package _7_Graph._01_Undir_Unweigh;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class _04_articulation_points {
    public static void main(String[] args) {
       List<List<Integer>>edges=Arrays.asList(
        Arrays.asList(0,1),
        Arrays.asList(1,4),
        Arrays.asList(4,3),
        Arrays.asList(4,2),
        Arrays.asList(3,2)
       );
       int n=5;

       System.out.println(func(n, edges));

    }
    
    public static List<Integer> func(int n,List<List<Integer>>edges){
        List<List<Integer>> adj = get_adj(n, edges);
        List<Integer> ans = new ArrayList<>();
        dfs(adj, ans, new int[n], new int[n], new boolean[n], 0, -1);

        //see that we have dealt with case if parent !=-1 , means dealt for everything except zero,
        int nNeighBours_of_0=adj.get(0).size();
        if(nNeighBours_of_0>1){
            ans.addFirst(0);
        }

        return ans;
    }

    static int order = 1;

    static void dfs(List<List<Integer>> adj,List<Integer> ans, int traversal_order[], int nearest_ancestor[],
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

               
                nearest_ancestor[currNodeNumber] = Math.min(
                        nearest_ancestor[currNodeNumber],
                        nearest_ancestor[nbr]
                    );

                if (parentOfCurr!=-1) {
                    if (nearest_ancestor[nbr] >=  traversal_order[currNodeNumber]
                     //VVVIMP CHANGE HERE condition and also operator from > to >=
                    ) {
                        ans.add(currNodeNumber);
                    }
                }
            } else {
                nearest_ancestor[currNodeNumber] = Math.min(
                        nearest_ancestor[currNodeNumber],
                        traversal_order[nbr]//CHANGE HERE
                    );
            }
        }
    }

    static List<List<Integer>> get_adj(int n, List<List<Integer>> edges) {
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
