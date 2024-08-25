package _3_binarySearch;

import java.util.ArrayList;

/*
Problem statement
You are given a matrix ‘ARR’ with dimensions ‘N’ * ‘M’ and containing only 0s and 1s where EACH ROW IS SORTED.

Your task is to find the index of the row with a maximum number of 1s in it. Rows and columns are 0-indexed based.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 1000
1 <= N, M <= 500


Time Limit: 1 sec
Sample Input 1:
2
1 1
1
3 3
0 0 1
1 1 1
0 1 1
Sample Output 1:
0
1
Explanation for sample input 1:
For the first test case, 
Since the matrix has one row and one column. The only row, i.e., the 0th row, contains a 1. So the output is 0. 

For the second test case, 
Since the matrix has three rows and three columns. The number of ones in the 0th row is 1. The number of ones in the 1st row is 3, and the number of ones in the 2nd row is 2. So the output is 1.
Sample Input 2:
2
3 2
0 1
0 1
0 0
4 4
0 0 1 1
0 0 0 0
1 1 1 1 
0 0 1 1 
Sample Output 2:
0
2
Explanation for sample input 2:
For the first test case, 
Since the matrix has 3 rows and 2 columns. The number of ones in the 0th row is 1. The number of ones in the 1st row is 1, and the number of ones in the 2nd row is 0. So the output is 0.

For the second test case, 
Since the matrix has 4 rows and 4 columns. The number of ones in the 0th row is 2. The number of ones in the 1st row is 0. The number of ones in the 2nd row is 4 and the number of ones in the 3rd row is 2. So the output is 2.
 */
public class _24_rowWithMax1s {




    public static int[] brute(int mat[][]){
        int max=0;int ans[]=new int[2];
        for(int i=0;i<mat.length;i++){
            int k=0;
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==1){k++;}
            }
            if(k>max){
                max=k;
                ans[0]=i;
                ans[1]=max;
            }
        }

        return ans;
    }




    //since each row is sorted , we can think of binary search.....for each row we can find the upper bound of 1, ie idx of last 1 ,the one in which its max we would return it
    public static int optimal(ArrayList<ArrayList<Integer>> arr){
        
		int maxCount = -1;
        int rowIndex = -1;

        for (int i = 0; i < arr.size(); i++) {
            int count = arr.get(i).size()-findUpperBoundOf0(arr, i);//no of ones is sizeOFThatRow-upperBoundOf0
            if (count > maxCount) {
                maxCount = count;
                rowIndex = i;
            }
        }

        return rowIndex;
    }
    public static int findUpperBoundOf0(ArrayList<ArrayList<Integer>> nums,int row){//this func will tell till what idx zeroes r occuring, the remaining would be obviosully 1s since the array is sorted
        int lowIdx=0,highIdx=nums.get(row).size();
        while (lowIdx<=highIdx) {
            int midIdx=(lowIdx+highIdx)/2;
           if (nums.get(row).get(midIdx)==1) {
            //check for greater idx since it may be present beyond
            lowIdx=midIdx+1;
            
           }else{
            highIdx=midIdx-1;
           }
        }
        return lowIdx;
    }
}
