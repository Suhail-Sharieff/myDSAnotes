package _7_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//---------------------------------------------------------------------UNDIRECTED GRAPH
/*
Given an undirected graph with V vertices labelled from 0 to V-1 and E edges, check whether the graph contains any cycle or not. The Graph is represented as an adjacency list, where adj[i] contains all the vertices that are directly connected to vertex i.

NOTE: The adjacency list represents undirected edges, meaning that if there is an edge between vertex i and vertex j, both j will be adj[i] and i will be in adj[j].

Examples:

Input: adj = [[1], [0,2,4], [1,3], [2,4], [1,3]] 
Output: 1
Explanation: 

1->2->3->4->1 is a cycle.
Input: adj = [[], [2], [1,3], [2]]
Output: 0
Explanation: 

No cycle in the graph.
Constraints:
1 ≤ adj.size() ≤ 105
 */
public class _5_detect_cycle {
    
    public static void main(String[] args) {
        List<List<Integer>>adj=List.of(List.of(1),List.of(0,2,4),List.of(1,3),List.of(2,4),List.of(1,3));
        usingBFS_func(adj);
    }




    //------------------------------------------------------------------USING BFS
    //solution: if we find some vertex that [is not visited && already present in the queue], there is a cycle
    //IMP watch:
    //https://www.youtube.com/watch?v=BPlrALf1LDU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=12&ab_channel=takeUforward

    public static  boolean usingBFS_func(List<List<Integer>>adj){//but why do we need this function additionally: what if our graph has disconnected componts, and any one of componts may heave cycles-------IMP
        int nNodes=adj.size();
        boolean isVis[]=new boolean[adj.size()];
        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++){
            if(!isVis[nodeNumber]){
                boolean hasCycles=bfs(adj,nodeNumber,isVis);
                if(hasCycles==true) return true;
            }
        }
        return false;
    }

    public static boolean bfs(List<List<Integer>>adj,int startNode,boolean isVis[]){
        Queue<int[]>q=new LinkedList<>();
        //each q elemnt will store [parent,curr]
        q.offer(new int[]{-1,startNode});
        isVis[startNode]=true;
        while (!q.isEmpty()) {
            int top[]=q.poll();
            
            int parentOfCurr=top[0];
            int curr=top[1];

            for(int neighBour : adj.get(curr)){
                if(!isVis[neighBour]){
                    q.offer(new int[]{curr,neighBour});
                    isVis[neighBour]=true;
                }else{//neighbour is visited
                    //if its curr neighbour is visited but its not parent of it
                    if(neighBour!=parentOfCurr){
                        return true;
                    }
                }
            }

        }
        return false;
    }
    
    //------------------------------------------------------------------USING DFS


    //same appraoch as that of bfs

    public static boolean dfs(List<List<Integer>>adj,int startNode,boolean isVis[]){
        isVis[startNode]=true;
        for(int neighBour:adj.get(startNode)){
            if(!isVis[neighBour]){
                boolean check=dfs(adj, neighBour, isVis);
                if(check) return true;
            }else{//neighbur is visisted already
                if(neighBour!=startNode) return true;
            }
        }
        return false;
    }


    //-----------------------------my solution, used freq[nodeNumber] for each node, incremented fre[nodeNumber] each time its encountered though its visited already. Then if any of freq[nodeNumber]>=2 meaning it is neighbourer of 2 nodes at a time even after being visited, there;s a cycle
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int nNodes=adj.size();
        boolean isVis[]=new boolean[adj.size()];
        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++){
            if(!isVis[nodeNumber]){
                boolean hasCycles=hasCycles(adj,nodeNumber,isVis);
                if(hasCycles==true) return true;
            }
        }
        return false;
    }
     public boolean hasCycles(ArrayList<ArrayList<Integer>> adj,int startNode,boolean isVis[]) {
        // Code here
        int v=adj.size();
        // boolean isVis[]=new boolean[v];
        int freq[]=new int[v+1];
        dfs(freq,isVis,v,adj,startNode);
        // System.out.println(Arrays.toString(freq));
        for(int e: freq)  if(e>=2) return true;
        return false;
        
    }

    
    
    public void dfs(int freq[],boolean isVis[],int v,ArrayList<ArrayList<Integer>> adj,int n){
        if(n>=v) return;
        isVis[n]=true;
        // System.out.println(n);
        for(int e : adj.get(n)){
            if(!isVis[e]) dfs(freq,isVis,v,adj,e);
            else freq[n]++;
        }
    }


    //********************************************************************************************************* */

    //-------------------------------------------------------------------------------DIRECTED GRAPH:
    //Note that same code as that of undirectted wouldnt work coz in case of undir graph we didnt hhave dirs and there could possibly be many ways, but here we have directions, we can reach some nodes though it looks like a cycle in only 1 way as we move in 1 dir

    //example:
    /*
 * Directed Graph :
 * 
 *        (0) ---> (1)
 *         |        ^
 *         |        |               if we apply same algo here i will say there is cycle, but in actuall there is no node from which if we start we can return at same point s
 *         |        |                since this  is directed
 *         v        |                       
 *        (3) ---> (2)
 

 * Undirected Graph :
 * 
 *        (0) ---- (1)
 *         |        |                                           cycle exists here
 *         |        |
 *         |        |
 *        (3)------(2)
 * 
 * Nodes: 0, 1, 2, 3
 * Edges: (0 -> 1), (0 -> 3), (3 -> 2), (2 -> 1)
 */

    //watch:https://www.youtube.com/watch?v=Tl5qbEmEQyY&ab_channel=CodeHelp-byBabbar

    //solution: to the same algo, we will store additionaly another array called dfs_done array . whenever we encounter a node that is not visited, we mark it visited , mark it as dfs_done , perform dfs on it, then after its dfs is done mark it as dfs_not done again..during this process if we encounter any neighbour for a node that is both visited and also dfs_done there exists a cycle in it, so we return true

    public static boolean hasCycles_directed(ArrayList<ArrayList<Integer>>adj){
        int nNodes=adj.size();
        boolean []isVis=new boolean[nNodes];
        boolean []dfs_done=new boolean[nNodes];

        for(int nodeNumber=0;nodeNumber<nNodes;nodeNumber++){
            if (!isVis[nodeNumber]) {
                boolean hasCycles=dfs_directed(adj, isVis, dfs_done, nodeNumber);
                if(hasCycles) return true;
            }
        }
        return false;

    }

    static  boolean  dfs_directed(ArrayList<ArrayList<Integer>>adj, boolean isVis[],boolean dfs_done[],int nodeNumber){
        isVis[nodeNumber]=true;
        dfs_done[nodeNumber]=true;
        for(int neighBour : adj.get(nodeNumber)){
            if (!isVis[neighBour]) {
                boolean hasCycle= dfs_directed(adj, isVis, dfs_done, neighBour);
                if(hasCycle) return true;
            }else{
                //the node is visisted, check if its dfs is don or not
                if(dfs_done[neighBour]) return true;
            }
        }
        dfs_done[nodeNumber]=false;
        return false;
    }
    //the below uses kahn's algo as described in _12_top_sort, since it works only for DAG(Directed acyclic graph), if we try to apply for a cyclic graph, we will arrive at a point where the q becomes empty without goin through all nodes, ie fails to add all nodes topologically
    public static boolean kahn_algo_to_detect_cycle(ArrayList<ArrayList<Integer>>adj){
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

        return ans.size()!=nNodes;
    }
}
