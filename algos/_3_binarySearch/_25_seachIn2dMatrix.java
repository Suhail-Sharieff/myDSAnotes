package _3_binarySearch;

/**
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
 

Example 1:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
Example 2:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109
 */
public class _25_seachIn2dMatrix {

    class Solution {
        public boolean brute(int[][] matrix, int target) {//O(nOFRows*log(noOfCols))
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] <= target) {
                    int low = 0, high = matrix[i].length - 1;
                    while (low <= high) {
                        int mid = (low + high) / 2;
                        if (matrix[i][mid] == target) {
                            return true;
                        } else if (matrix[i][mid] < target) {
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    }
                }else{
                    return false;
                }
            }
            return false;
        }

        public boolean better(int[][] matrix, int target) {//O(M+N)
            int col=matrix[0].length-1;
            int row=0;
            while(col>=0&&row<=matrix.length-1){
                if(matrix[row][col]==target){
                    return true;
                }else if(matrix[row][col]>target){
                    col--;
                }else{
                    row++;
                }
            }
            return false;
        }

            public boolean optimal(int[][] matrix, int target) {//O(log(m*n))
                int low = 0, high = (matrix.length*matrix[0].length) - 1;
                        
                        while (low <= high) {
                            int mid = (low + high) / 2;
                            int row=mid/matrix[0].length;//mid/noOfCols
                            int col=mid%matrix[0].length;
                            if (matrix[row][col] == target) {
                                return true;
                            } else if (matrix[row][col] < target) {
                                low = mid + 1;
                            } else {
                                high = mid - 1;
                            }
                        }
                   
                return false;
            }
    }
}