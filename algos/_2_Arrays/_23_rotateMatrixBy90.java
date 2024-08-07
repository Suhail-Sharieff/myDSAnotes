package _2_Arrays;

/*
 You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */
public class _23_rotateMatrixBy90 {
    public static void main(String[] args) {
        int mat[][] = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

        optimal(mat);
    }

    public static void brute(int mat[][]) {// brute coz it uses O(n*n) space
        int rotMat[][] = new int[mat.length][mat.length];
        int len = mat.length;// coz n*n
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int k = mat[len - j - 1][i];
                rotMat[i][j] = k;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(rotMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void optimal(int mat[][]) {// O(1) space--IN SPACE
        // first transpose(row become col& vivceversa)
        // reverse each row

        // how to transpose in space?
        // observe that diagonal elemnts of orig and transpose r always same, but :
        // [0][1] changes to [1][0]
        // [0][2] changes to [2][0]
        // [0][3] changes to [3][0]
        // [1][3] changes to [3][1]
        // [2][3] changes to [3][2]..end

        int len = mat.length;

        // VVVVIMP: TRANSPOSE:
        // LHS:
        // to transpose , u dont have to proceed i from 0 to len, observe that i varies
        // from 0 to (len-2) and the j is always variing from (i+1) to (len-1)
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = mat[i][j];
                int y = mat[j][i];
                mat[i][j] = y;
                mat[j][i] = x;

            }
        }

        // REVERSING EACH ROW
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int k = mat[i][j];
                mat[i][j] = mat[i][len - j - 1];
                mat[i][len - j - 1] = k;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
