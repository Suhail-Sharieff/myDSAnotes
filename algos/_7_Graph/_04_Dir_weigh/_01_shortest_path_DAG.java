package _7_Graph._04_Dir_weigh;
/*

//MUST WATCH:https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=29&ab_channel=takeUforward

Shortest path in Directed Acyclic Graph
Difficulty: MediumAccuracy: 48.48%Submissions: 149K+Points: 4
Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Examples :

Input: V = 4, E = 2, edges = [[0,1,2], [0,2,1]]
Output: [0, 2, 1, -1]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->2 with edge weight 1. There is no way we can reach 3, so it's -1 for 3.
Input: V = 6, E = 7, edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
Output: [0, 2, 3, 6, 1, 5]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3. Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6. Shortest path from 0 to 4 is 0->4 with edge weight 1.Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
Constraint:
1 <= V <= 100
1 <= E <= min((N*(N-1))/2,4000)
0 <= edgesi,0, edgesi,1 < n
0 <= edgei,2 <=105
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class _01_shortest_path_DAG {

  // Solution exp:

  // prereq: toposort using BFS as described in _12_toposort
  /*
  intution: WKT toposort's first elemnt is always the one having no incoming edges, ie if we start from any node we cant reach it, so lets start from that node and from there we r dam sure that we can trverse others since gievn that graph is DAG, before we move to the next neighbour, we will make ans[neigh] whcih represents the distance of neigh node from the base (topo's first node), as weightOfEdgeBtwParentAndMe+dis[par] ie dis[neigh]=wt+dis[par] where dis[par] represents distance of parent from base node, this tradition we ill carry further, if we encounter the node, whose value ws already there, just take min value
  
   * First get adj list where
   * nodeNumber:[[neighBourNodeNumber,wt_with_that_neighBour],.....]
   * Get toposort statck(as described in _12_toposort(using DFS))
   * start filling the answer array by poping from top of stack,update the
   * dist[neighbour] to min(dis[neighbour],wt+dis[top])
   */

  public static void main(String[] args) {
    int graph[][] = { // NOTE THE GRAPH HAS EDGES LIST WRITH CORRESPONDING WTS, ITS NOT LIKE NORMAL
                      // ADJ LIST
        { 0, 1, 2 },
        { 0, 4, 1 },
        { 4, 5, 4 },
        { 4, 2, 2 },
        { 1, 2, 3 },
        { 2, 3, 6 },
        { 5, 3, 1 }
    };
    int nNodes = 7;//given
    // loks like:
    /*
     * /*
     * 
     * Graph Representation:
     * 
      3        6
    (1)───▶(2)────▶(3)
     ▲     ▲        ▲ 
     │ 2   │ 2      │ 1
     │     │        |
    (0)───▶(4)────▶(5)
        1        4
     */
    int src_nodeNumber = 0;// IN INTERVIEW THEY MAY CHNGE THIS_____IMP

    System.out.println(Arrays.toString(answer(graph, nNodes, src_nodeNumber)));

  }

  static int[] answer(int graph[][], int nNodes, int src_nodeNumber) {

    List<List<int[]>> adj = get_adj_list(graph, nNodes);
    Stack<Integer> topo_order_of_nodes = get_topo_sort_stack(adj, nNodes);

    int distance_from_base_node[] = new int[nNodes];
    Arrays.fill(distance_from_base_node, Integer.MAX_VALUE);

    distance_from_base_node[src_nodeNumber] = 0;// ----IMP

    while (!topo_order_of_nodes.isEmpty()) {
      int topNodeNumber = topo_order_of_nodes.pop();
      if (distance_from_base_node[topNodeNumber] != Integer.MAX_VALUE) { // Skip unreachable nodes---IMMP
        for (int[] pair : adj.get(topNodeNumber)) {
          int neighbourNodeNumber = pair[0];
          int distance_with_neighbour = pair[1];
          int new_distance = distance_with_neighbour + distance_from_base_node[topNodeNumber];

          distance_from_base_node[neighbourNodeNumber] = Math.min(
              distance_from_base_node[neighbourNodeNumber],
              new_distance);
        }
      }
    }
    return Arrays.stream(distance_from_base_node).map((e) -> (e != Integer.MAX_VALUE ? e : -1)).toArray();
  }

  static List<List<int[]>> get_adj_list(int graph[][], int nNodes) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < nNodes; i++)
      adj.add(new ArrayList<>());// lets initially add empty list for each node
    for (int i = 0; i < graph.length; i++) {
      adj.get(graph[i][0]).add(new int[] { graph[i][1], graph[i][2] });
    }
    return adj;
  }

  static Stack<Integer> get_topo_sort_stack(List<List<int[]>> adj, int nNodes) {
    Stack<Integer> topo_order_of_nodes = new Stack<>();
    boolean isVis[] = new boolean[nNodes];
    for (int nodeNumber = 0; nodeNumber < nNodes; nodeNumber++) {
      if (!isVis[nodeNumber])
        dfs(adj, nodeNumber, isVis, topo_order_of_nodes);
    }
    return topo_order_of_nodes;
  }

  static void dfs(List<List<int[]>> adj, int nodeNumber, boolean isVis[], Stack<Integer> st) {
    isVis[nodeNumber] = true;
    for (int pair[] : adj.get(nodeNumber)) {
      int itsNeighbour = pair[0];
      if (!isVis[itsNeighbour])
        dfs(adj, itsNeighbour, isVis, st);
    }
    st.push(nodeNumber);
  }

}
