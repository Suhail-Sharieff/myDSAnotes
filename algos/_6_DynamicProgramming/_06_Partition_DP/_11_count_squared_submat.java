package _6_DynamicProgramming._06_Partition_DP;
/*

https://www.youtube.com/watch?v=auS1fynpnjo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=58&ab_channel=takeUforward

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1

 */
public class _11_count_squared_submat {
    public int countSquares(int[][] mat) {
        int nRows=mat.length,nCols=mat[0].length;
        for(int i=1;i<nRows;i++){
            for(int j=1;j<nCols;j++){
                if(mat[i][j]==0) continue;
                mat[i][j]=1+Math.min(
                    Math.min(mat[i][j-1],mat[i-1][j]),
                    mat[i-1][j-1]
                );
            }
        }
        int ans=0;
        for(int r[]:mat)for(int e:r) ans+=e;
        return ans;
    }
}
