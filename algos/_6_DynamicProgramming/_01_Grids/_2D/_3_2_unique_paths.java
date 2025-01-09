package _6_DynamicProgramming._01_Grids._2D;


/*
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 */
public class _3_2_unique_paths {
    

    public static void main(String[] args) {

        int mat[][]={
            {0,0,0},
            {0,1,0},
            {0,0,0},
        };

         //-----recursion_1
        // int ans[]={0};
        // recursion_1(mat,0,0,ans);
        // return ans[0];

        //--------recursion_2
        // return recursion_2(mat,0,0);

        //----------memoize:
        // int dp[][]=new int[mat.length][mat[0].length];
        // for(int e[]:dp) Arrays.fill(e,-1);
        // return memoize(mat,0,0,dp);

        //-------tabulate:
        System.out.println(tabulate(mat));
    }

    public void recursion_1(int mat[][],int i,int j,int ans[]){
        int nRows=mat.length,nCols=mat[0].length;
        if(mat[0][0]==1 || mat[nRows-1][nCols-1]==1) return ;//-----IMP: what if we had obstacle at starting pos itself or the last pos is obstacle, ieno change of reaching lasst cell
        if(i==nRows-1 && j==nCols-1 ){ans[0]++;return;}
        //down logic
        if(i+1<nRows && mat[i+1][j]!=1){
            recursion_1(mat,i+1,j,ans);
        }
        //right logic
        if(j+1<nCols && mat[i][j+1]!=1){
            recursion_1(mat,i,j+1,ans);
        }
    }


    public int recursion_2(int mat[][],int i,int j){
        int nRows=mat.length,nCols=mat[0].length;
        if(mat[0][0]==1 || mat[nRows-1][nCols-1]==1) return 0;
        if(i==nRows-1 && j==nCols-1 ) return 1;
        int move_down=(i+1<nRows&&mat[i+1][j]!=1)?recursion_2(mat,i+1,j):0;
        int move_right=(j+1<nCols&&mat[i][j+1]!=1)?recursion_2(mat,i,j+1):0;
        return (move_down+move_right);
    }

    public int memoize(int mat[][],int i,int j,int dp[][]){
        int nRows=mat.length,nCols=mat[0].length;
        if(mat[0][0]==1 || mat[nRows-1][nCols-1]==1) return 0;
        if(i==nRows-1 && j==nCols-1 ) return 1;
        if(dp[i][j]!=-1) return dp[i][j];
        int move_down=(i+1<nRows&&mat[i+1][j]!=1)?memoize(mat,i+1,j,dp):0;
        int move_right=(j+1<nCols&&mat[i][j+1]!=1)?memoize(mat,i,j+1,dp):0;
        dp[i][j]=move_down+move_right;
        return dp[i][j];
    }

    public static int tabulate(int mat[][]){
        int nRows=mat.length,nCols=mat[0].length;
        if(mat[0][0]==1 || mat[nRows-1][nCols-1]==1) return 0;
        int dp[][]=new int[nRows][nCols];
        dp[0][0]=1;
        for(int i=0;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                if(i==0 && j==0) continue;
                int move_down=(i-1>=0 && mat[i-1][j]!=1)?dp[i-1][j]:0;
                int move_right=(j-1>=0 && mat[i][j-1]!=1)?dp[i][j-1]:0;
                dp[i][j]=move_down+move_right;
            }
        }
        return dp[nRows-1][nCols-1];
    }
    
}
