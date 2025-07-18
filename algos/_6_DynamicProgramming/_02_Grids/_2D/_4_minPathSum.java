package _6_DynamicProgramming._02_Grids._2D;

import java.util.Arrays;
import java.util.PriorityQueue;

import _4_Trees.TreeNode;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200






IMP NOTE: 2 things may come in mind when this problem comes, one is using djikstra, other one is DP, but here we can apply dp becoz we can move right or down at a time, ie no chace of going back ie backtracking, but suppose given all 4 dirs, u need to use djiktra
 */

 //=============================DP
public class _4_minPathSum {
   public static void main(String[] args) {
     // return recursion(mat,0,0);

        // int dp[][]=new int[mat.length][mat[0].length];
        // return memoize(mat,0,0,dp);
        int mat[][]={
            {2,3,4},
            {0,1,1},
            {1,0,1},
        };
        // System.out.println(tabulate(mat));
        brute_recursion(mat, mat.length, mat[0].length, 0, 0, new StringBuilder(), 0, new int[]{Integer.MAX_VALUE});
    }
   
    static void brute_recursion(int mat[][],int m,int n,int i,int j,StringBuilder sb,int sum,int ans[]){
        if(i==m-1 && j==n-1){
            System.out.println(new String(mat[0][0]+"->"+sb+" => "+(sum+mat[0][0])));
            ans[0]=Math.min(ans[0], sum+mat[0][0]);
            return;
        }
        if (j+1<n) {
            brute_recursion(mat,m, n, i, j+1, new StringBuilder(sb).append(mat[i][j+1]+"->"),sum+mat[i][j+1],ans);
        }
        if (i+1<m) {
            brute_recursion(mat,m, n, i+1, j, new StringBuilder(sb).append(mat[i+1][j]+"->"),sum+mat[i+1][j],ans);
        }
      }

    public int recursion(int mat[][],int i,int j){
        int nRows=mat.length,nCols=mat[0].length;
        if(i==nRows-1 && j==nCols-1) {
            return mat[i][j];
        }


        int move_down=(i+1<nRows)?(recursion(mat, i+1,j)):Integer.MAX_VALUE;
        int move_right=(j+1<nCols)?(recursion(mat, i,j+1)):Integer.MAX_VALUE;
        

        return mat[i][j]+Math.min(move_down,move_right);

    }
    
    public int memoize(int mat[][],int i,int j,int dp[][]){
        int nRows=mat.length,nCols=mat[0].length;
        if(i==nRows-1 && j==nCols-1) {
            dp[i][j]=mat[i][j];
            return dp[i][j];
        }

        if(dp[i][j]!=0) return dp[i][j];
        
        int move_down=(i+1<nRows)?(memoize(mat, i+1,j,dp)):Integer.MAX_VALUE;
        int move_right=(j+1<nCols)?(memoize(mat, i,j+1,dp)):Integer.MAX_VALUE;
        
        
        dp[i][j]=mat[i][j]+Math.min(move_down,move_right);
        return dp[i][j];

    }
    
    public static int tabulate(int mat[][]){
        int nRows=mat.length,nCols=mat[0].length;
        int dp[][]=new int[nRows][nCols];
        dp[0][0]=mat[0][0];
        
        for(int i=0;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                if(i==0 && j==0) continue;
                int move_down=(i-1>=0)?dp[i-1][j]:Integer.MAX_VALUE;
                int move_right=(j-1>=0)?dp[i][j-1]:Integer.MAX_VALUE;
                dp[i][j]=mat[i][j]+Math.min(move_down,move_right);
            }
        }

        return dp[nRows-1][nCols-1];
    }



    //------------------------FOLLOW UP:
    /*
Maximum path sum from any node
Difficulty: MediumAccuracy: 42.92%Submissions: 99K+Points: 4Average Time: 45m
Given a binary tree, the task is to find the maximum path sum. The path may start and end at any node in the tree.

Examples:

Input: root[] = [10, 2, 10, 20, 1, N, -25, N, N, N, N, 3, 4]
Output: 42
Explanation: 

Max path sum is represented using green colour nodes in the above binary tree.
Input: root[] = [-17, 11, 4, 20, -2, 10]
Output: 31
Explanation: 

Max path sum is represented using green colour nodes in the above binary tree.
Constraints:
1 ≤ number of nodes ≤ 103
-104 ≤ node->data ≤ 104
     */
    static class Solution {
        // Function to return maximum path sum from any node in a tree.
        private int ans;
        int findMaxSum(TreeNode node) {
            ans=Integer.MIN_VALUE;
            func(node);
            return ans;
        }
        
        int func(TreeNode root){
            // your code goes here
            if(root==null) return 0;
            int left=Math.max(0,func(root.left));
            int right=Math.max(0,func(root.right));
            int curr_max=root.val+left+right;//imp
            ans=Math.max(ans,curr_max);
            return root.val+Math.max(left,right);
        }
    }
    


    //===========================DJIKTRA
    /*Minimum Cost Path
Difficulty: HardAccuracy: 26.99%Submissions: 123K+Points: 8
Given a square grid of size N, each cell of which contains an integer cost that represents a cost to traverse through that cell, we need to find a path from the top left cell to the bottom right cell by which the total cost incurred is minimum.
From the cell (i,j) we can go (i,j-1), (i, j+1), (i-1, j), (i+1, j).  

Examples :

Input: grid = {{9,4,9,9},{6,7,6,4},{8,3,3,7},{7,4,9,10}}
Output: 43
Explanation: The grid is-
9 4 9 9
6 7 6 4
8 3 3 7
7 4 9 10
The minimum cost is-
9 + 4 + 7 + 3 + 3 + 7 + 10 = 43.
Input: grid = {{4,4},{3,7}}
Output: 14
Explanation: The grid is-
4 4
3 7
The minimum cost is- 4 + 3 + 7 = 14.
Expected Time Complexity: O(n2*log(n))
Expected Auxiliary Space: O(n2) 
 Constraints:
1 ≤ n ≤ 500
1 ≤ cost of cells ≤ 500 */



//here cant use DP coz we can move in all dirs, so use djikstra
    static class Solution_d{
    // Function to return the minimum cost to react at bottom
    // right cell from top left cell.
    public int minimumCostPath(int[][] mat) {
        int m=mat.length,n=mat[0].length;
        int dis[][]=new int[m][n];for(int e[]:dis) Arrays.fill(e,inf);
        PriorityQueue<int[]>pq=new PriorityQueue<>((x,y)->x[2]-y[2]);
        pq.offer(new int[]{0,0,mat[0][0]});
        dis[0][0]=mat[0][0];
        while(!pq.isEmpty()){
            int top[]=pq.poll();
            int i=top[0],j=top[1],d=top[1];
            if(dis[i][j]<d) continue;
            for(int dir[]:dirs){
                int x=dir[0]+i;
                int y=dir[1]+j;
                if(x>=0 && y>=0 && x<mat.length && y<mat[0].length){
                    if(dis[x][y]>dis[i][j]+mat[x][y]){
                        dis[x][y]=dis[i][j]+mat[x][y];
                        pq.offer(new int[]{x,y,dis[x][y]});
                    }
                }
            }
        }
        return dis[m-1][n-1];
    }
    
    public int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
    public int inf=Integer.MAX_VALUE;
    
    public int rec(int mat[][],int i,int j,boolean isVis[][]){
        if(i==1 && j==1){
            return mat[i-1][j-1];
        }
        isVis[i][j]=true;
        int min=inf;
        for(int dir[]:dirs){
            int x=dir[0]+i;
            int y=dir[1]+j;
            if(x>=1 && y>=1 && x<=mat.length && y<=mat[0].length){
                if(!isVis[x][y]){
                    int v=rec(mat,x,y,isVis);
                    if(v!=inf) min=Math.min(min,mat[i-1][j-1]+v);
                }
            }
        }
        isVis[i][j]=false;
        return min;
    }
    
}
}
