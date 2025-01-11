package _6_DynamicProgramming._02_Grids._2D;

import java.util.Arrays;

//https://www.youtube.com/watch?v=sdE0A2Oxofw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=9&ab_channel=takeUforward
/*
You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is the bottom-right cell of the same matrix. Your task is to find the total number of unique paths from point ‘A’ to point ‘B’.In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the total number of unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].

To traverse in the matrix, you can either move Right or Down at each step. For example in a given point MATRIX[i] [j], you can move to either MATRIX[i + 1][j] or MATRIX[i][j + 1].

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 ≤ T ≤ 100
1 ≤ M ≤ 15
1 ≤ N ≤ 15

Where ‘M’ is the number of rows and ‘N’ is the number of columns in the matrix. 

Time limit: 1 sec
Sample Input 1:
2
2 2
1 1
Sample Output 1:
2
1
Explanation of Sample Output 1:
In test case 1, we are given a 2 x 2 matrix, to move from matrix[0][0] to matrix[1][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1)
Path 2 = (0, 0) -> (1, 0) -> (1, 1)

Hence a total of 2 paths are available, so the output is 2.

In test case 2, we are given a 1 x 1 matrix, hence we just have a single cell which is both the starting and ending point. Hence the output is 1.
Sample Input 2:
2
3 2
1 6
Sample Output 2:
3
1
Explanation of Sample Output 2:
In test case 1, we are given a 3 x 2 matrix, to move from matrix[0][0] to matrix[2][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1) -> (2, 1)
Path 2 = (0, 0) -> (1, 0) -> (2, 0) -> (2, 1)
Path 3 =  (0, 0) -> (1, 0) -> (1, 1) -> (2, 1)

Hence a total of 3 paths are available, so the output is 3.

In test case 2, we are given a 1 x 6 matrix, hence we just have a single row to traverse and thus total path will be 1.
 */
public class _3_1_unique_paths {

    public static void main(String[] args) {
        // int mat[][]={
        //     {0,0},
        //     {0,0},
        //     {0,0},
        // };
        // boolean isVis[][]=new boolean[mat.length][mat[0].length];
        // int ans[]={0};
        // recursion_1(mat, 0, 0, new StringBuilder(),isVis,ans);
        tabulate(3, 3);

    }

    //-----------------recursive solution is as same as that of rat in maze problem in multipleCalls/_10_ratInMaze, but here we just need to move down or tight as mentioned in the question,if u want to know paths its foloowing to reach end point for fun u can use this
    //call like:
    /*
     int mat[][]={
            {0,0},
            {0,0},
            {0,0},
        };
        boolean isVis[][]=new boolean[mat.length][mat[0].length];
        int ans[]={0};
        recursion(mat, 0, 0, new StringBuilder(),isVis,ans);
     */
    public static void recursion_1(int mat[][], int i, int j, StringBuilder sb, boolean isVis[][], int ans[]) {
        int nRows = mat.length, nCols = mat[0].length;
        if (i == mat.length - 1 && j == mat[0].length - 1) {
            System.out.println("(0,0)->"+sb);
            ans[0]++;
        }
        isVis[i][j] = true;
        int dirs[][] = { { 1, 0 }, { 0, 1 } };
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < nRows && y < nCols && !isVis[x][y]) {
                recursion_1(mat, x, y, sb.append("("+x+","+y+")->"), isVis, ans);
            }
        }
        isVis[i][j] = false;
    }

    //recursion way2:----better and shorter than 1st way since we dont have to make matrix first and ans as well: O(2^n)--(m*n)
    public static int recursion_2(int i,int j,int m,int n){
        if(i==m-1 && j==n-1) return 1;//found 1 path
        if(i>=m || j>=n) return 0;
        int move_right=recursion_2(i, j+1, m, n);
        int move_down=recursion_2(i+1,j,m,n);
        return move_right+move_down;
    }

    //------------------memoization:
    public static int memoize(int i,int j,int m,int n,int dp[][]){//0 to top appraoch--o(m*n)--(m*n)
        if(i==m-1 && j==n-1) return 1;//found 1 path
        if(i>=m || j>=n) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int move_right=memoize(i, j+1, m, n,dp);
        int move_down=memoize(i+1,j,m,n,dp);
        dp[i][j]=move_right+move_down;
        return dp[i][j];
    }

    //----------------tabulation, also observe that dp[i][j] represnts number of ways u can reach from mat[0][0] to mat[i][j]
    public static int tabulate(int m,int n){//very important
        int dp[][]=new int[m][n];
        dp[0][0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;
                //LOGIC:UTILIZE PREVIOUS RESOURCES AND ASSIGN IT TO CURR
                int move_down=(i-1>=0)?dp[i-1][j]:0;//utilize its upper row's resource 
                int move_right=(j-1>=0)?dp[i][j-1]:0;//utilize its left col's resource 
                dp[i][j]=move_down+move_right;
            }
            for(int e[]:dp)System.out.println(Arrays.toString(e));
            System.out.println("----");
        }
        return dp[m-1][n-1];
    }

}