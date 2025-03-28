package _6_DynamicProgramming._02_Grids._2D;

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
 */
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
}
