package _6_DynamicProgramming._2D;
//IMP: previously u learnt how u can trvaerse via all paths when allowed to move in 4 dirs ie [ {i+1,j}, {i-1,j}, {i,j+1} or {i,j-1} ], here u will learn what if u want to trverse in dir other than this, like say down-right rather than just down ie [i+1][j+1], like that.....
import java.util.List;
/*
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10
 

Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */


 //solution:
 //how to recurse, in previous cases see that we can move right or down, ie [i+1][j] or [i][j+1], here observe that we move down or move down right, so use [i+1][j+1]

public class _5_triangleProb {
    public static void main(String[] args) {
        List<List<Integer>>triangle=List.of(
            List.of(2),
            List.of(3,4),
            List.of(6,5,7),
            List.of(4,1, 8, 3)
        );
        int len=triangle.size();
        int mat[][]=new int[len][len];
        for(int i=0;i<len;i++){
            int l=triangle.get(i).size();
            for(int j=0;j<l;j++){
                mat[i][j]=triangle.get(i).get(j);
            }
        }
        System.out.println(recursion(mat,0,0,new StringBuilder(),0));
    }



    //---------------------recursion
    static int recursion(int mat[][],int i,int j,StringBuilder sb,int sum){

        if(i==mat.length-1) {
            System.out.println(mat[0][0]+"->"+sb+"=> "+(sum+mat[0][0]));
            return mat[i][j];
        }
        int move_down=recursion(mat,i+1,j,new StringBuilder(sb).append(mat[i+1][j]+"->"),sum+mat[i+1][j]);

        int move_down_right=recursion(mat,i+1,j+1,new StringBuilder(sb).append(mat[i+1][j+1]+"->"),sum+mat[i+1][j+1]);

        return mat[i][j]+Math.min(move_down_right,move_down);

    }

    //---------------memoize
    static int memoize(int mat[][],int i,int j,int dp[][]){

        if(i==mat.length-1) {
            dp[i][j]=mat[i][j];
            return mat[i][j];
        }
        if(dp[i][j]!=-1) return dp[i][j];
        int move_down=memoize(mat,i+1,j,dp);
        int move_down_right=memoize(mat,i+1,j+1,dp);
        dp[i][j]=mat[i][j]+Math.min(move_down_right,move_down);
        return dp[i][j];

    }


    public static int tabulate(int mat[][]){
        int nRows=mat.length,nCols=mat[0].length;
        int dp[][]=new int[nRows][nCols]; 
        dp[0][0]=mat[0][0];
        for(int i=0;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                if(i==0 && j==0) continue;
                int move_down=dp[i-1][j];
                int move_down_right=dp[i-1][j-1];

                dp[i][j]=mat[i][j]+Math.min(move_down, move_down_right);
            }
        }
        return dp[nRows-1][nCols-1];
    }
}
