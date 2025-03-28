package _6_DynamicProgramming._02_Grids._2D;


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
    


    //----------------------FOLLOW UP:
    /*
980. Unique Paths III
Solved
Hard
Topics
Companies
You are given an m x n integer array grid where grid[i][j] could be:

1 representing the starting square. There is exactly one starting square.
2 representing the ending square. There is exactly one ending square.
0 representing empty squares we can walk over.
-1 representing obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:


Input: grid = [[0,1],[2,0]]
Output: 0
Explanation: There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
1 <= m * n <= 20
-1 <= grid[i][j] <= 2
There is exactly one starting cell and one ending cell.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
224.7K
Submissions
273.2K



MAin thing is to understand q, challenge is to visit all walkables nodes to reach dest---------IMP
     */
    static class Solution {
        public int uniquePathsIII(int[][] grid) {
            int get[]=get(grid);
            int i=get[0],j=get[1];
            // System.out.println(i+","+j+"  "+dx+","+dy);
            nNonObs(grid);//to instantiate nOfWalkables coz we need to touch all walkables
            return rec(i,j,new boolean[grid.length][grid[0].length],grid,0);
        }
    
        private int dirs[][]={{-1,0},{1,0},{0,-1},{0,1}};
        private int nNonObstacles;
    
    
        public void nNonObs(int grid[][]){
            nNonObstacles=1;//----not 0, coz we need to include starting point as well
            for(int r[]:grid) for(int e:r) if(e==0) nNonObstacles++;
        }
    
        public int rec(int i,int j,boolean isVis[][],int grid[][],int cnt){
            if(grid[i][j]==2 && cnt==nNonObstacles) return 1;
            int ans=0;
            isVis[i][j]=true;
            for(int dir[]:dirs){
                int x=dir[0]+i;
                int y=dir[1]+j;
                if(x>=0 && y>=0 && x<grid.length && y<grid[0].length){
                    if((grid[x][y]==0 || grid[x][y]==2) && !isVis[x][y]){// we have empty cell or dest cell
                        ans+=rec(x,y,isVis,grid,cnt+1);
                    }
                }
            }
            isVis[i][j]=false;
            return ans;
        }
    
        public int[] get(int grid[][]){
            int ans[]=new int[]{-1,-1,-1,-1};
            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid[0].length;j++){
                    if(grid[i][j]==1){
                         ans[0]=i;
                         ans[1]=j;
                    }
                    if(grid[i][j]==2){
                         ans[2]=i;
                         ans[3]=j;
                    }
                }
            }
            return ans;
        }
    }
}
