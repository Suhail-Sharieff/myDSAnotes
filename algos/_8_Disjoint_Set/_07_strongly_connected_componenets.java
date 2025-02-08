package _8_Disjoint_Set;


//KOSARAJU ALGORITHM



/*
Strongly Connected Components (SCCs) are a fundamental concept in graph theory and algorithms. In a directed graph, a Strongly Connected Component is a subset of vertices where every vertex in the subset is reachable from every other vertex in the same subset by traversing the directed edges. Finding the SCCs of a graph can provide important insights into the structure and connectivity of the graph, with applications in various fields such as social network analysis, web crawling, and network routing. This tutorial will explore the definition, properties, and efficient algorithms for identifying Strongly Connected Components in graph data structures

Table of Content

What is Strongly Connected Components (SCCs)?
Why Strongly Connected Components (SCCs) are Important?
Difference Between Connected and Strongly Connected Components (SCCs)
Why conventional DFS method cannot be used to find strongly connected components?
Connecting Two Strongly Connected Component by a Unidirectional Edge
Brute Force Approach for Finding Strongly Connected Components
Efficient Approach for Finding Strongly Connected Components (SCCs)
1. Kosaraju’s Algorithm:
2. Tarjan’s Algorithm:
Conclusion
What is Strongly Connected Components (SCCs)?
A strongly connected component of a directed graph is a maximal subgraph where every pair of vertices is mutually reachable. This means that for any two nodes A and B in this subgraph, there is a path from A to B and a path from B to A.

For example: The below graph has two strongly connected components {1,2,3,4} and {5,6,7} since there is path from each vertex to every other vertex in the same strongly connected component. 

scc_fianldrawio
Strongly Connected Component

Why Strongly Connected Components (SCCs) are Important?
Understanding SCCs is crucial for various applications such as:

Network Analysis: Identifying clusters of tightly interconnected nodes.
Optimizing Web Crawlers: Determining parts of the web graph that are closely linked.
Dependency Resolution: In software, understanding which modules are interdependent.
Difference Between Connected and Strongly Connected Components (SCCs)
Connectivity in a undirected graph refers to whether two vertices are reachable from each other or not. Two vertices are said to be connected if there is path between them. Meanwhile Strongly Connected is applicable only to directed graphs. A subgraph of a directed graph is considered to be an Strongly Connected Components (SCC) if and only if for every pair of vertices A and B, there exists a path from A to B and a path from B to A. Let’s see why the standard dfs method to find connnected components in a graph cannot be used to determine strongly connected components.

Consider the following graph:


scc_fianldrawio


Now, let’s start a dfs call from vertex 1 to visit other vertices.


dfs_finaldrawio


Try it on GfG Practice
redirect icon
Why conventional DFS method cannot be used to find the strongly connected components?
All the vertices can be reached from vertex 1. But vertices 1 and 5,6,7 can not be in the same strongly connected component because there is no directed path from vertex 5,6 or 7 to vertex 1. The graph has two strongly connected components {1,2,3,4} and {5,6,7}. So the conventional dfs method cannot be used to find the strongly connected components.

Connecting Two Strongly Connected Component by a Unidirectional Edge
Two different connected components becomes a single component if a edge is added between a vertex from one component to a vertex of other component. But this is not the case in strongly connected components. Two strongly connected components doesn’t become a single strongly connected component if there is only a unidirectional edge from one SCC to other SCC.

unidrawio-(2)

Brute Force Approach for Finding Strongly Connected Components
The simple method will be for each vertex i (which is not a part of any strongly component) find the vertices which will be the part of strongly connected component containing vertex i. Two vertex i and j will be in the same strongly connected component if they there is a directed path from vertex i to vertex j and vice-versa.

Let’s understand the approach with the help of following example:


exampledrawio


Starting with vertex 1. There is path from vertex 1 to vertex 2 and vice-versa. Similarly there is a path from vertex 1 to vertex 3 and vice versa. So, vertex 2 and 3 will be in the same Strongly Connected Component as vertex 1.  Although there is directed path form vertex 1 to vertex 4 and vertex 5. But there is no directed path from vertex 4,5 to vertex 1 so vertex 4 and 5 won’t be in the same Strongly Connected Component as vertex 1. Thus Vertex 1,2 and 3 forms a Strongly Connected Component.
For Vertex 2 and 3, there Strongly Connected Component has already been determined.
For Vertex 4, there is a path from vertex 4 to vertex 5 but there is no path from vertex 5 to vertex 4. So vertex 4 and 5 won’t be in the Same Strongly Connected Component. So both Vertex 4 and Vertex 5 will be part of Single Strongly Connected Component.
Hence there will be 3 Strongly Connected Component {1,2,3}, {4} and {5}.
 */
/*
Given an adjacency list, adj of Directed Graph, Find the number of strongly connected components in the graph.
 

Examples :

Input: adj[][] = [[2, 3], [0], [1], [4], []]

Output: 3
Explanation: We can clearly see that there are 3 Strongly Connected Components in the Graph.
 
Input: adj[][] = [[1], [2], [0]]

Output: 1
Explanation: All of the nodes are connected to each other. So, there's only one SCC.
Input: adj[][] = [[1], []]
Output: 2
Constraints:
2<=adj.size()<=106
0<=edges<=adj.size()-1
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//KOSARAJU ALGO:
/*
 * In short A component or a cluster in a graph is called a strongly connected componentsuch thatall nodes in it are reachablefrom any node in itwhich means say we have two nodes U and Vin one of the clusterthat particular cluster is called a strongly connected component if and only if each and every node in it are accessible via each other. This program illustrates how we can find the number of such strongly connected components or clusters in a graphand also print them.
 * 
 Should be intuition that is used in this Algorithmisthatthough we reverse the edgesin each of the strongly connected components they still remain the strongly connected component. So we utilize this particular observation in this particular algorithm.We first do a normal depth first searchh On the entire graphand basicallypush the elements into the stackafter going to the pest and then coming to topthat is basicallywe go so deep as much as possible and while returning we push to the stackwhich basically gives us the feel thatthe last element to be popped out of the stackhas no outgoing edges and only incoming edges. Now the stack basically containsthe dfs elements in the order of their accessing timewhich means the element which is easily accessibleis basically present on the top of the stack and the one which requires more deeper to go is basically present at the bottom of the stack.

 Now what we do is in the entire graph we will reverse whatever the edges which are presentsay if there is an edge from zero to one we convert it into 1 to 0 and so on for throughout the edgesthe advantage of doing this is thatthe strongly connected components will still remain the same but however now they will get Isolatedthis is becausethe edge that was connecting one strongly correctedcomponent to another strongly connected component has been reversed which meanswe cannot move now from one strongly connected component to another strongly connected component or in other words each strongly connected component has been isolated within itselfso hence we can use this property to print each and every isolated component. Now we start doing ADFS by maintaining an ease visited arrayfrom the top of the stack.Will pop out the topmost element from the stack and perform the dfs the dfs happens within all the nodes of only that particular strongly connected component because we have isolated that component by another strongly connected component by reversing the edgesso all the nodes of that particular top elementwill be touched by the dfs and the dfs will endby making visited all the elements of that particular strongly connected component next similarly we will do for the next elements and we will move on this is how we will print or we can even count the number of strongly connected components in a graph
 */

public class _07_strongly_connected_componenets {
    public static void main(String[] args) {
        int adj[][] = {
                { 2, 3 }, // connections of 0,{0->2} and {0->3}
                { 0 }, // conections of 1
                { 1 },
                { 4 },
                {}
        };
        int nNodes = adj.length;

        printSCC(nNodes, adj);
    }


    private static void  printSCC(int nNodes,int adj[][]){

        List<List<Integer>>ans=new ArrayList<>();

        //---------------dfs call 1
        Stack<Integer> st = new Stack<>();
        boolean isVis[] = new boolean[nNodes];
        for (int nodeNumber = 0; nodeNumber < nNodes; nodeNumber++)
            if (!isVis[nodeNumber])
                dfs(adj, isVis, nodeNumber, st);

        //-----------dfs call 2
        int reversedAdj[][] = get_reversed_edges(adj);
        isVis = new boolean[nNodes];
        while (!st.isEmpty()) {
            int topNode = st.pop();
            Stack<Integer> scc = new Stack<>();
            if (!isVis[topNode])
                dfs(reversedAdj, isVis, topNode, scc);
            else
                continue;
            ans.add(new ArrayList<>(scc));
        }
        System.out.println(ans);
    }

    private static void dfs(int adj[][], boolean isVis[], int nodeNumber, Stack<Integer> st) {
        isVis[nodeNumber] = true;
        for (int neigh : adj[nodeNumber])
            if (!isVis[neigh])
                dfs(adj, isVis, neigh, st);
        st.push(nodeNumber);
    }

    private static int[][] get_reversed_edges(int adj[][]) {
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < adj.length; i++)
            newAdj.add(new ArrayList<>());
        for (int nodeNumber = 0; nodeNumber < adj.length; nodeNumber++) {
            for (int neigh : adj[nodeNumber]) {
                newAdj.get(neigh).add(nodeNumber);
            }
        }
        int reversedAdj[][] = new int[adj.length][];
        for (int i = 0; i < newAdj.size(); i++) {
            reversedAdj[i] = newAdj.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return reversedAdj;
    }

}
