package _7_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph.

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering.
Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0.

Examples:

Input: adj = [[], [0], [0], [0]] 

Output: 1
Explanation: The output 1 denotes that the order is valid. Few valid Topological orders for the given graph are:
[3, 2, 1, 0]
[1, 2, 3, 0]
[2, 3, 1, 0]
Input: adj = [[], [3], [3], [], [0,1], [0,2]]

Output: 1
Explanation: The output 1 denotes that the order is valid. Few valid Topological orders for the graph are:
[4, 5, 0, 1, 2, 3]
[5, 2, 4, 0, 1, 3]
Constraints:
2  ≤  V  ≤  103
1  ≤  E  ≤  (V * (V - 1)) / 2

Some of the key significances of topological sorting include: Task Scheduling: In project management and job scheduling systems, tasks often have dependencies on each other. Topological sorting helps determine the order in which tasks should be executed to satisfy these dependencies, ensuring that dependent tasks are completed before dependent ones begin. Dependency Resolution: In software development and package management systems, modules or packages often depend on other modules or packages. Topological sorting aids in resolving dependencies by determining the correct order of installation or compilation to ensure that dependencies are met. Symbol Resolution in Compilers: Compilers, particularly during the compilation phase, need to resolve symbols or identifiers referenced in the code. Topological sorting assists in determining the order of symbol resolution or code generation, ensuring that symbols are defined before they are used. Course Prerequisites in Education: In educational systems, courses often have prerequisites, where certain courses must be completed before others can be taken. Topological sorting helps in structuring course schedules by arranging courses in the order of prerequisites, ensuring that students take courses in the correct sequence. Pipeline Optimization in Hardware Design: In computer architecture and digital design, pipeline stages often have dependencies on each other. Topological sorting assists in optimizing pipeline stages by arranging them in the order of dependencies, ensuring that data flows through the pipeline efficiently. Network Routing and Dependency Analysis: In network routing algorithms and dependency analysis, topological sorting helps in understanding the flow of information or resources through a network by arranging nodes in the order of dependencies. Overall, topological sorting provides a systematic way to handle dependencies and precedence relationships in various domains, contributing to efficient resource allocation, scheduling, and decision-making processes. Its significance lies in its ability to model and solve dependency-related problems in a wide range of applications, making it a fundamental concept in computer science and engineering.Read more on Sarthaks.com - https://www.sarthaks.com/3595216/what-is-the-significance-of-topological-sorting



In simple words, if the directed graph has X->Y<-Z as nodes, then X,Z,Y or Z,X,Y is answer, ie the node being pointed to must be after the one that points to it
 */
public class _12_topo_sort {


    //*******************************************************USING DFS */

    // solution:if node isnt visited, perform dfs, ie goo deeper, while coming from
    // deep to up, push to stack, after all nodes r trvrersed, pop it one by one its
    // always in topo order

    static ArrayList<Integer> using_DFS(ArrayList<ArrayList<Integer>> adj) {

        boolean isVis[] = new boolean[adj.size()];
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < adj.size(); i++) {
            if (!isVis[i]) {
                dfs(i, adj, isVis, st);
            }
        }
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
        return (ans);
    }

    public static void dfs(int n, ArrayList<ArrayList<Integer>> adj, boolean isVis[], Stack<Integer> st) {
        isVis[n] = true;

        for (int e : adj.get(n)) {
            if (!isVis[e]) {
                dfs(e, adj, isVis, st);
            }
        }
        // after we go deep, and while returnning, push stuff to stack bottom to top of
        // tree
        st.push(n);
    }






    //*****************************************************************USING BFS - KAHN s ALGORITHM */
    //https://www.youtube.com/watch?v=73sneFXuTEg&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=22&ab_channel=takeUforward
    public static List<Integer> kahn_algo(ArrayList<ArrayList<Integer>>adj){
        int nNodes=adj.size();

        int in_degree[]=new int[nNodes];
        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++){
            for(int e:adj.get(nodeNumber)) in_degree[e]++;
        }

        Queue<Integer>q=new LinkedList<>();
        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++) if(in_degree[nodeNumber]==0) q.offer(nodeNumber);

        ArrayList<Integer>ans=new ArrayList<>();
        
        while (!q.isEmpty()) {
            int topNodeNumber=q.poll();
            ans.add(topNodeNumber);
            for(int neigh:adj.get(topNodeNumber)){
                in_degree[neigh]--;
                if(in_degree[neigh]==0) q.offer(neigh);
            }
        }

        return ans;
    }
}
