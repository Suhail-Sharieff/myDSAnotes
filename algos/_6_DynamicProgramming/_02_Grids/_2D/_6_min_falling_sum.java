package _6_DynamicProgramming._02_Grids._2D;

import java.util.Arrays;

public class _6_min_falling_sum {
    public static void main(String[] args) {
        int mat[][]={
            {2,1,3},
            {6,5,4},
            {7,8,9},
        };
        System.out.println(tabulate(mat));

        
    }



    //-------------------------recursion:

    /* IMP: call like, observe that if u just return recursion(...), it will find min path sum cnonsidering the starting point [0,0] consly, what about [0,1],[0,2]..., so we go from all starting points ie [0,n] and find min sums

    call like: 
    int ans=Integer.MAX_VALUE;
        for (int i = 0; i < mat.length; i++) {
            ans=Math.min(ans,recursion(mat, 0, i, new StringBuilder()));
        }
     */
    public static int recursion(int [][] mat,int i,int j,StringBuilder sb){
        if(i==mat.length-1) {
            System.out.println(mat[0][0]+"->"+sb);
            return mat[i][j];
        }
        int dirs[][]={{1,-1},{1,0},{1,1}};
        int nRows=mat.length,nCols=mat[0].length;
        int min=100000;
        for(int dir[]:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x<nRows && x>=0 && y<nCols && y>=0){
                min=Math.min(min,recursion(mat,x,y,new StringBuilder(sb).append(mat[x][y]+"->")));
            }
        }
        return (mat[i][j]+min);
    }


    //-------------memoization, call in same fahion as that of recursive method
    public int memoization(int[][] mat, int i, int j, int dp[][]) {
        if (i == mat.length - 1) {
            dp[i][j] = mat[i][j];
            return mat[i][j];
        }
        if (dp[i][j] != Integer.MIN_VALUE)
            return dp[i][j];

        int dirs[][] = { { 1, -1 }, { 1, 0 }, { 1, 1 } };//down-left, down, down-right

        int nRows = mat.length, nCols = mat[0].length;

        int min = Integer.MAX_VALUE;
        for (int dir[] : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < nRows && x >= 0 && y < nCols && y >= 0) {
                min = Math.min(min, memoization(mat, x, y, dp));
            }
        }
        dp[i][j] = mat[i][j] + min;
        return dp[i][j];
    }


    //----------------tabulation: OBSERVE CAREFULLY, i just manipulated dirs, ie in tabulation and recursive solution we needed to move down-left,down or down-right, here we need to extract the data from  upper-left,up or upper-left, so i first made the first row of dp as same as mat[0], then trversed from row 1 for each cell, extrcting the min value among [upper-right,up,upprt-left],
    //call just => return taulate(mat);
    public static int tabulate(int mat[][]){
        int nRows = mat.length, nCols = mat[0].length;

        int dp[][]=new int[nRows][nCols];
        int dirs[][] = { { -1, +1 }, { -1, 0 }, { -1, -1 } };

        //IMP, when u needed to strat from 0,0  initialized as dp[0][0]=mat[0][0], but here we can start from 0..n, points of first row, so initilaize entire stuff
        for(int i=0;i<dp[0].length;i++) dp[0][i]=mat[0][i];

        for(int i=1;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                int min=Integer.MAX_VALUE;
                for(int []dir:dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < nRows && x >= 0 && y < nCols && y >= 0) {
                        min = Math.min(min, dp[x][y]);
                    }
                }

                dp[i][j]=mat[i][j]+min;
            }
        }


        int ans=Integer.MAX_VALUE;
        for(int e:dp[nRows-1]) ans=Math.min(e,ans);

        for(int e[]:dp) System.out.println(Arrays.toString(e));

        return ans;

    }


    //other way:
    public int minFallingPathSum(int[][] mat) {
        int dp[][] = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++)
            dp[mat.length - 1][i] = mat[mat.length - 1][i];
        int dirs[][]={{1,-1},{1,0},{1,1}};
        for (int i = mat.length - 2; i >= 0; i--) {
            for (int j = 0; j < mat.length; j++) {
                int min=Integer.MAX_VALUE;
                for(int dir[]:dirs){
                    int x=i+dir[0];
                    int y=j+dir[1];
                    if(x>=0&&y>=0&&x<mat.length&&y<mat[0].length) min=Math.min(min,dp[x][y]);
                }
                dp[i][j]=mat[i][j]+min;
            }
        }
        int ans=Integer.MAX_VALUE;
        for(int e: dp[0]) ans=Math.min(ans,e);
        return ans;
    }



    //-------------------space optimization:
    public static int spaceOptimized(int mat[][]){
        int nRows = mat.length, nCols = mat[0].length;

        int dirs[][] = { { -1, +1 }, { -1, 0 }, { -1, -1 } };

        int prevRow[]=new int[nCols];

        for(int i=0;i<nCols;i++) prevRow[i]=mat[0][i];

        for(int i=1;i<nRows;i++){
            int[] currentRow = new int[nCols];
            for(int j=0;j<nCols;j++){
                int min=Integer.MAX_VALUE;
                for(int []dir:dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < nRows && x >= 0 && y < nCols && y >= 0) {
                        min = Math.min(min, prevRow[y]);
                    }
                }

                currentRow[j]=mat[i][j]+min;
            }
            prevRow=currentRow;
        }


        int ans=Integer.MAX_VALUE;
        for(int e:prevRow) ans=Math.min(e,ans);


        return ans;
    }

    //----------------follow up: u r now not allowed to move from one row to next row such that column of elemnt selected in 2 consecutive rows is not same: ie A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.-----IMP, removind down dir wont work--becoz it will then just move diagonally one elemnt left or right, but not via enire row of elemnts---VVIMP
    
    public static int followUp(int [][] mat){
        int nRows = mat.length, nCols = mat[0].length;

        int dp[][]=new int[nRows][nCols];
       

        for(int i=0;i<dp[0].length;i++) dp[0][i]=mat[0][i];

        for(int i=1;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                int min=Integer.MAX_VALUE;
                for(int k=0;k<nCols;k++){
                    //if chosen col is !=k
                    if(j!=k){
                        min=Math.min(min, dp[i-1][k]);
                    }
                }

                dp[i][j]=mat[i][j]+min;
            }
        }


        int ans=Integer.MAX_VALUE;
        for(int e:dp[nRows-1]) ans=Math.min(e,ans);

        for(int e[]:dp) System.out.println(Arrays.toString(e));

        return ans;
    }
}
