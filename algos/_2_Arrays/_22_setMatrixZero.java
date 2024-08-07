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
        List<Integer>rowsHavingZeroes=new ArrayList<>();
        List<Integer>colsHavingZeroes=new ArrayList<>();
        int nRows=mat.length,nCols=mat[0].length;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]==0) {
                    rowsHavingZeroes.add(i);
                    colsHavingZeroes.add(j);
                }
            }
        }
        //making rows 0
        for (int i = 0; i < rowsHavingZeroes.size(); i++) {
            Arrays.fill(mat[rowsHavingZeroes.get(i)], 0);
        }
        //making cols 0
        for (int i = 0; i < colsHavingZeroes.size(); i++) {
            int temp=nRows;
            while (temp--!=0) {
                mat[temp][colsHavingZeroes.get(i)]= 0;
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
