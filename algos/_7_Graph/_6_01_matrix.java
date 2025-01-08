package _7_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
Medium
Topics
Companies
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

 

Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 
 */

public class _6_01_matrix {

    public static void main(String[] args) {
        int mat[][] = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 1, 1, 1 }
        };

        int ans[][] =optimal(mat);

       
        
           

        for (int e[] : ans)
            System.out.println(Arrays.toString(e));

    }
    //solution:https://www.youtube.com/watch?v=edXdVwkYHF8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=13&ab_channel=takeUforward
    //as same as rottn oranges problem, bnut here additionly we need isVis array coz we are not modifying the matrix. We first get all positions of 0s and put in queue, each elemnt of queue represnts [i,j,disTanceOfItFrom0]. 

    //we  need to copute ditance of [i,j] from its nearest 0

    public static int[][] optimal(int mat[][]) {
        
        List<int[]>all_0s_pos=getAll_0s_pos(mat);
        boolean isVis[][]=new boolean[mat.length][mat[0].length];

        Queue<int[]>q=new LinkedList<>();
        for(int pos[]:all_0s_pos){
            q.offer(new int []{pos[0],pos[1],0});
            isVis[pos[0]][pos[1]]=true;
        }

        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};

        int ans[][]=new int[mat.length][mat[0].length];


        while (!q.isEmpty()) {
            int[] top=q.poll();

            int top_x=top[0];
            int top_y=top[1];
            int dist_from_0=top[2];


            ans[top_x][top_y]=dist_from_0;

            for(int [] dir : dirs){
                int x=top_x+dir[0];
                int y=top_y+dir[1];

                if(x>=0 && x<mat.length && y>=0 && y<mat[0].length){
                    if(mat[x][y]==1 && !isVis[x][y]){
                        q.offer(new int[]{x,y,dist_from_0+1});
                        isVis[x][y]=true;
                    }
                }

            }


        }

        return ans;

    }

    public static List<int[]>getAll_0s_pos(int mat[][]){
        List<int[]>li=new ArrayList<>();
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                if(mat[i][j]==0) li.add(new int[]{i,j});
        return li;
    }



    //--------------------using DP

/*
Solution 2: Dynamic Programming
https://leetcode.com/problems/01-matrix/solutions/1369741/c-java-python-bfs-dp-solutions-with-picture-clean-concise-o-1-space/

For convinience, let's call the cell with value 0 as zero-cell, the cell has with value 1 as one-cell, the distance of the nearest 0 of a cell as distance.
Firstly, we can see that the distance of all zero-cells are 0, so we skip zero-cells, we process one-cells only.
In DP, we can only use prevous values if they're already computed.
In this problem, a cell has at most 4 neighbors that are left, top, right, bottom. If we use dynamic programming to compute the distance of the current cell based on 4 neighbors simultaneously, it's impossible because we are not sure if distance of neighboring cells is already computed or not.
That's why, we need to compute the distance one by one:
Firstly, for a cell, we restrict it to only 2 directions which are left and top. Then we iterate cells from top to bottom, and from left to right, we calculate the distance of a cell based on its left and top neighbors.
Secondly, for a cell, we restrict it only have 2 directions which are right and bottom. Then we iterate cells from bottom to top, and from right to left, we update the distance of a cell based on its right and bottom neighbors.
 */


 public static int[][] using_dp(int mat[][]){
    
    int dp[][]=new int[mat.length][mat[0].length];
        int inf=mat.length+mat[0].length;
    //first: iterate from top to bottoom while extract values from up and left
    for(int i=0;i<mat.length;i++){
        for(int j=0;j<mat[0].length;j++){
            if(mat[i][j]==0) continue;
            int from_up=(i-1>=0)?dp[i-1][j]:inf;
            int from_left=(j-1>=0)?dp[i][j-1]:inf;

            dp[i][j]=Math.min(from_up,from_left)+1;
        }
    }

     //secondly: iterate from bottom to top while extract values from down and right

     int nRows=mat.length,nCols=mat[0].length;

     for(int i=nRows-1;i>=0;i--){
        for(int j=nCols-1;j>=0;j--){
            int from_down=(i+1<nRows)?dp[i+1][j]:inf;
            int from_right=(j+1<nCols)?dp[i][j+1]:inf;

            dp[i][j]=Math.min(dp[i][j],Math.min(from_down, from_right) +1 );
        }
     }

     return dp;
    }

 }

