package _6_DynamicProgramming._02_Grids._3D;
//https://www.youtube.com/watch?v=QGfn7JeXK54&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=14&ab_channel=takeUforward


/*
You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
 

Example 1:


Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
Example 2:


Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
 

Constraints:

rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100
 */


 //#################### 4 D version, later will optimize to 3D version
 /*
class Solution {
    public int nRows;
    public int nCols;
    public int mat[][];
    public int dirs[][]={{1,-1},{1,0},{1,1}};
    public int cherryPickup(int[][] grid) {
        mat=grid;
        nRows=mat.length;
        nCols=mat[0].length;
        // return rec(0,0,0,nCols-1);
        int dp[][][][]=new int[nRows][nCols][nRows][nCols];
        for(int i1=nRows-1;i1>=0;i1--){
            for(int j1=nCols-1;j1>=0;j1--){
                for(int i2=nRows-1;i2>=0;i2--){
                    for(int j2=0;j2<nCols;j2++){
                        if(i1==nRows-1){
                            if(j1==j2) dp[i1][j1][i2][j2]=mat[i1][j1];
                            else dp[i1][j1][i2][j2]=mat[i1][j1]+mat[i2][j2];
                        }else{
                            int ans=0;
                            for(int d1[]:dirs){
                                for(int d2[]:dirs){
                                    if(!valid(i1+d1[0],j1+d1[1])||!valid(i2+d2[0],j2+d2[1])) continue;
                                    ans=Math.max(ans,dp[i1+d1[0]][j1+d1[1]][i2+d2[0]][j2+d2[1]]);
                                }
                            }
                            if(i1==i2 && j1==j2) dp[i1][j1][i2][j2]=mat[i1][j1]+ans;
                            else dp[i1][j1][i2][j2]=ans+mat[i1][j1]+mat[i2][j2];
                        }
                    }
                }
            }
        }
        return dp[0][0][0][nCols-1];
    }
    public int rec(int i1,int j1,int i2,int j2){
        if(!valid(i1,j1)||!valid(i2,j2)) return 0;
        if(i1==nRows-1){
            if(j1==j2) return mat[i1][j1];
            return mat[i1][j1]+mat[i2][j2];
        }
        int ans=0;
        for(int d1[]:dirs){
            for(int d2[]:dirs){
                ans=Math.max(ans,rec(i1+d1[0],j1+d1[1],i2+d2[0],j2+d2[1]));
            }
        }
        if(i1==i2 && j1==j2) return mat[i1][j1]+ans;
        return ans+mat[i1][j1]+mat[i2][j2];
    }
    public boolean valid(int i,int j){
        return (i<nRows&&j<nCols&&i>=0&&j>=0);
    }
}
  */

public class _01_choco_count_01_ {
    public static void main(String[] args) {
        int mat[][]={
            {4,5},
            {3,7},
            {4,2},
            // {5,6,3,5},
        };


        System.out.println(recursion(mat, 0, 0, mat[0].length-1));
    }
   
    //----------------recursion: 9*(3^nRows)---uses BFS
    public static int recursion(int mat[][],int i,int j1,int j2){
        
        if(!isValid(i,j1,mat,mat[0].length)||!isValid(i,j2,mat,mat[0].length)) return 0;

        if(i==mat.length-1) return (j1==j2)?(mat[i][j1]):(mat[i][j1]+mat[i][j2]);
        int ans=0;
        for(int x=-1;x<=1;x++)
            for(int y=-1;y<=1;y++)
                ans=Math.max(ans,recursion(mat,i+1,j1+x,j2+y));
        ans+=(j1==j2)?(mat[i][j1]):(mat[i][j1]+mat[i][j2]);//after robots r moved, check
        return ans;
    }
    static boolean isValid(int i,int j,int mat[][],int nCols){
        return (j>=0&&j<nCols);
    }


    //-------------memoization:
    public static int memoize(int mat[][],int i,int y1,int y2,int dp[][][]){
        int nRows=mat.length,nCols=mat[0].length;
        if(i==nRows-1) {
            //handle case in whcih they may be preent in same colmn at last row:
            if(y1==y2) return mat[i][y1];
            return mat[i][y1]+mat[i][y2];
        }

        if(dp[i][y1][y2]!=-1) return dp[i][y1][y2];

        int max=Integer.MIN_VALUE;

        for(int j1=-1;j1<=1;j1++){
            for(int j2=-1;j2<=1;j2++){
                int next_y1=y1+j1;
                int next_y2=y2+j2;


                if(next_y1>=0 && next_y2>=0 && next_y1<nCols && next_y2<nCols){
                   int chosenCherries = (y1 == y2) ? mat[i][y1] : mat[i][y1] + mat[i][y2];
                max = Math.max(max, chosenCherries + memoize(mat, i + 1, next_y1, next_y2,dp));

                }
            }
        }
        dp[i][y1][y2]=max;
        return dp[i][y1][y2];
    }


    //=------------tabulation
    //https://leetcode.com/problems/cherry-pickup-ii/solutions/4708405/interview-approach-dfs-brute-force-top-down-bottom-up-bottom-up-space-optimisation-dp/
    public int nRows;
    public int nCols;
    public int mat[][];
    public int dirs[][] = { { 1, -1 }, { 1, 0 }, { 1, 1 } };

    public int cherryPickup_tabulation(int[][] grid) {
        mat = grid;
        nRows = mat.length;
        nCols = mat[0].length;
        int dp[][][] = new int[nRows][nCols][nCols];
        for (int i1 = nRows - 1; i1 >= 0; i1--) {
            for (int j1 = nCols - 1; j1 >= 0; j1--) {
                for (int j2 = 0; j2 < nCols; j2++) {
                    if (i1 == nRows - 1) {
                        if (j1 == j2)
                            dp[i1][j1][j2] = mat[i1][j1];
                        else
                            dp[i1][j1][j2] = mat[i1][j1] + mat[i1][j2];
                    } else {
                        int ans = 0;
                        for (int d1[] : dirs) {
                            for (int d2[] : dirs) {
                                if (!valid(i1 + d1[0], j1 + d1[1], j2 + d2[1]))
                                    continue;
                                ans = Math.max(ans, dp[i1 + d1[0]][j1 + d1[1]][j2 + d2[1]]);
                            }
                        }
                        if (j1 == j2)
                            dp[i1][j1][j2] = mat[i1][j1] + ans;
                        else
                            dp[i1][j1][j2] = ans + mat[i1][j1] + mat[i1][j2];
                    }
                }
            }
        }
        return dp[0][0][nCols - 1];
    }

    public boolean valid(int i, int j1, int j2) {
        return (i < nRows && j1 < nCols && j2 < nCols && i >= 0 && j1 >= 0 && j2 >= 0);
    }
}
