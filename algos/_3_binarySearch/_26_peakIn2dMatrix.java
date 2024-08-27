package _3_binarySearch;

public class _26_peakIn2dMatrix {
    //brute force would be just to return the position of max Elemnt in the 2d matrix---O(m*n)




    public static int[] optimal(int mat[][]){//nRows*log(nCols)
        /*
Explanation:
Binary Search on Columns: The algorithm performs binary search on columns to narrow down the potential peak column.

Find Maximum in Column: For each mid column, find the row with the maximum value in that column.The max elemnt in that col will obviosult be greater than its UP and DOWN neighbours, we so now dont have to wroory to check its UP and DOWN , we just nned to check in that row's 1D array

Peak Checking: Check if this maximum value is a peak in that row. If it is not, adjust the search direction based on its neighbors.

Return Result: If a peak is found, return its position. Otherwise, adjust the search range based on comparisons.
 */
        int nRows=mat.length;
        int nCols=mat[0].length;

        // Binary search over columns

        int low=0,high=nCols-1;

        while (low<=high) {
            int midCol=(low+high)/2;
            //find row having max elemnt in that midCol
            int maxRow=0;
            for (int i = 1; i < nRows; i++) {
                if (mat[i][midCol]>mat[maxRow][midCol]) {
                    maxRow=i;
                }
            }

            boolean isPeak=true;

            //write the conditions for which it CANT be a peak 
            if (midCol>0 && mat[maxRow][midCol]<mat[maxRow][midCol-1]) {
                isPeak=false;
                high=midCol-1;
            }
            if (midCol<nCols-1 && mat[maxRow][midCol]<mat[maxRow][midCol+1]) {
                isPeak=false;
                low=midCol+1;
            }

            if (isPeak) {
                return new int[]{maxRow,midCol};
            }
        }


        return new int[]{-1,-1};
    }
}
