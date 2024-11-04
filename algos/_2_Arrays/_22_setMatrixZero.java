package _2_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

 

Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 

Follow up:

A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution
 */
public class _22_setMatrixZero {
    public static void main(String[] args) {
        int mat[][]={{1,1,1},{1,0,1},{1,1,1}};
        brute(mat);

    }
    public static void brute(int mat[][]){//O(mn)---O(m+n)
        List<Integer>rows=new ArrayList<>();
        List<Integer>cols=new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int e:rows){//mak row zero
            Arrays.fill(matrix[e],0);
        }
        // System.out.println(rows);
        // System.out.println(cols);
        for(int i=0;i<matrix.length;i++){//make colszero
            for(int e:cols){
                matrix[i][e]=0;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
               System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(nRows+" "+nCols);
    }

    public static void optimal(int mat[][]){
        //https://youtu.be/5TfyWZc_y-E

        //PROCEDURE:
        /*
         * first mark if first row contains 0 or first col conatins zero or both
         * u have dealt with first row and col, u safel start from 1,1
         * traverse from 1,1 timmll m,n, if at any instance u find a zero lets say at (i,j), mark matrix[i][0](that rows' first elemnt)=matrix[0][j](hat cols's first row) as zer
         * now u dont have to trvaerse for entire matrix, since u have marked which row and col have zero(by makring thier first elemnt)
         * now just trvere throwgh 0th rows all cols and also 0th columns all rows and make 0
         * deal with first and first col
         */




        int rows = mat.length;
        int cols = mat[0].length;
        boolean fcol = false;
        boolean frow = false;

        // Check if there is a zero in the first column, set fcol to true.
        for (int i = 0; i < rows; i++) {
            if (mat[i][0] == 0) {
                fcol = true;
                break;
            }
        }

        // Check if there is a zero in the first row, set frow to true.
        for (int i = 0; i < cols; i++) {
            if (mat[0][i] == 0) {
                frow = true;
                break;
            }
        }

        // Check row elements (by ignoring the first row and first column). If zero is
        // found,
        // set the corresponding row's and column's first element to zero.
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (mat[i][j] == 0) {
                    mat[0][j] = 0;
                    mat[i][0] = 0;
                }
            }
        }

        // Check every row's first element starting from the second row.
        // Set the complete row to zero if zero is found.
        for (int i = 1; i < rows; i++) {
            if (mat[i][0] == 0) {
                Arrays.fill(mat[i], 0);
            }
        }

        // Check every column's first element starting from the second column.
        // Set the complete column to zero if zero is found.
        for (int j = 1; j < cols; j++) {
            if (mat[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    mat[i][j] = 0;
                }
            }
        }

        // If fcol is true, set the first column to zero.
        if (fcol) {
            for (int i = 0; i < rows; i++) {
                mat[i][0] = 0;
            }
        }

        // If frow is true, set the first row to zero.
        if (frow) {
            Arrays.fill(mat[0], 0);
        }
    }

    
}
