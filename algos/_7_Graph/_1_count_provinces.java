package _7_Graph;

import java.util.ArrayList;
import java.util.List;
/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
 */
public class _1_count_provinces {


    //solution is to go for ith node, perform dfs for that node by incrementing cnt if its not visited, then it will corrupt(make as visited) all its neighbours
    public int optimal(int[][] mat) {//O(n)---O(n)
        
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<mat.length;i++){
            List<Integer>thatNode=new ArrayList<>();
            for(int j=0;j<mat.length;j++){
                if(mat[i][j]==1 &&i!=j){
                    thatNode.add(j);
                }
            }
            adj.add(thatNode);
        }
        System.out.println(adj);
        boolean isVis[]=new boolean[mat.length];
        int ans=0;
        for(int i=0;i<adj.size();i++){
            if(!isVis[i]){
                ans++;
                dfs(adj,i,isVis);
            }
        }
        return ans;

    }


    public static void dfs(List<List<Integer>>adj,int n,boolean isVis[]){
        isVis[n]=true;
        System.out.println(n);
        for(int e : adj.get(n)){
            if(!isVis[e]){
                dfs(adj,e,isVis);
            }
        }
    }
}
