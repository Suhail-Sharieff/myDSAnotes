package _2_Arrays;

import java.util.ArrayList;
import java.util.List;

/*
 Given an m x n matrix, return all elements of the matrix in spiral order.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */
public class _24_spiralMatrix {
    public static void main(String[] args) {
        int mat[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        List<Integer> li = new ArrayList<>();
        rec(mat, 0, 0, li, new boolean[mat.length][mat[0].length]);
        System.out.println(li);//Clockwise spiral
        optimal(mat);

    }

    public static List<Integer> rec(int[][] arr, int i, int j, List<Integer> list,boolean isVisited[][]){
        if(!isVisited[i][j]){
            list.add(arr[i][j]);
            isVisited[i][j]=true;  
        }
        //traversing in right direction →
        if(j < arr[i].length-1 && !isVisited[i][j+1]){
            rec(arr, i, j+1, list,isVisited);
        }
        //traversing in down direction ↓
        else if(i < arr.length-1 && !isVisited[i+1][j]){
            rec(arr, i+1, j, list,isVisited);
        }
        //traversing in left direction ←
        else if(j > 0 && !isVisited[i][j-1]){
            rec(arr, i, j-1, list,isVisited);
        }
        //traversing all the way up to finish one spiral ↑
        if(i > 0 && !isVisited[i-1][j]){
            while(!isVisited[i-1][j]){
                i--;
                list.add(arr[i][j]);
            isVisited[i][j]=true;             }
            rec(arr, i, j+1, list,isVisited);
        }
        return list;
    }
   

    public static void optimal(int mat[][]) {
        List<Integer> ans = new ArrayList<>();
        int nRows = mat.length;
        int nCols = mat[0].length;
        int left = 0, right = nCols - 1; // Adjust right to be nCols - 1
        int top = 0, bottom = nRows - 1; // Adjust bottom to be nRows - 1

        while (top <= bottom && left <= right) {
            // Traverse from left to right
            for (int i = left; i <= right; i++) {
                ans.add(mat[top][i]);
            }
            top++;

            // Traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                ans.add(mat[i][right]);
            }
            right--;

            if (top <= bottom) { // Check if there's a valid row to traverse
                // Traverse from right to left
                for (int i = right; i >= left; i--) {
                    ans.add(mat[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) { // Check if there's a valid column to traverse
                // Traverse from bottom to top
                for (int i = bottom; i >= top; i--) {
                    ans.add(mat[i][left]);
                }
                left++;
            }
        }
        System.out.println(ans);
    }
}
