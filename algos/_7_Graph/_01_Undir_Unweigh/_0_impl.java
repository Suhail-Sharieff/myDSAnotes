package _7_Graph._01_Undir_Unweigh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _0_impl {
    public static void main(String[] args) {
        
    }

    //-------------------BFS
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {//uses Queue
        //example adj = [[2,3,1], [0], [0,4], [0], [2]], where ith list of adj has nodes connected to ith node,ex 0th node connected to 2,3,1 ike that
       ArrayList<Integer>ans=new ArrayList<>();
        boolean isVis[]=new boolean[adj.size()];
        Queue<Integer>q=new LinkedList<>();
        q.offer(0);
        isVis[0]=true;
        while(!q.isEmpty()){
            int topNodeNumber=q.poll();
            ArrayList<Integer>itsAdjList=adj.get(topNodeNumber);
            for(int e : itsAdjList){
                if(!isVis[e]){
                    q.offer(e);
                    isVis[e]=true;
                }
            }
            ans.add(topNodeNumber);
        }
        return ans;
    }

    //-----------DFS
    public static void dfsIterative(ArrayList<ArrayList<Integer>>adj){
        ArrayList<Integer>ans=new ArrayList<>();
        boolean isVis[]=new boolean[adj.size()];
        Stack<Integer>st=new Stack<>();
        st.push(0);
        isVis[0]=true;
        while (!st.isEmpty()) {
            int topNodeNumber=st.pop();
            ArrayList<Integer>itsAdjList=adj.get(topNodeNumber);
            for(int e : itsAdjList){
                if(!isVis[e]){
                    st.push(e);
                    isVis[e]=true;
                }
            }
            ans.add(topNodeNumber);

        }
    }

    public static void dfsRecursive(ArrayList<Integer>ans,ArrayList<ArrayList<Integer>>adj,int nodeNumber,boolean isVis[]){//https://www.youtube.com/watch?v=Qzf1a--rhp8&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=131&ab_channel=takeUforward
        ans.add(nodeNumber);
        isVis[nodeNumber]=true;
        for(int e : adj.get(nodeNumber)){
            if(!isVis[e]){
                dfsRecursive(ans,adj,e,isVis);
            }
        }
    }
    

}
