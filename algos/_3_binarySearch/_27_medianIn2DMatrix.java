package _3_binarySearch;
// problem link: https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

import java.util.PriorityQueue;

/*
Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.

Example 1:

Input:
R = 3, C = 3
M = [[1, 3, 5], 
     [2, 6, 9], 
     [3, 6, 9]]
Output: 5
Explanation: Sorting matrix elements gives 
us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median. 
 

Example 2:

Input:
R = 3, C = 1
M = [[1], [2], [3]]
Output: 2
Explanation: Sorting matrix elements gives 
us {1,2,3}. Hence, 2 is median.

Your Task:  
You don't need to read input or print anything. Your task is to complete the function median() which takes the integers R and C along with the 2D matrix as input parameters and returns the median of the matrix.

Expected Time Complexity: O(32 * R * log(C))
Expected Auxiliary Space: O(1)


Constraints:
1 <= R, C <= 400
1 <= matrix[i][j] <= 2000


 */
public class _27_medianIn2DMatrix {
    //https://www.youtube.com/watch?v=Q9wXgdxJq48&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=72


    //brute force very simple TC:n*m*log(m*n),SC:m*n

    //better:using min heap: The key idea is that for a number x to be the median in an n x m matrix, there must be exactly (n * m) / 2 elements less than or equal to x. We perform binary search over the range [minElement, maxElement], where minElement and maxElement are the smallest and largest elements in the matrix.
    // At each step, we compute the number of elements less than or equal to the current mid.
    // => If this count is less than or equal to (n * m) / 2, we search in the upper half of the range to increase the candidate value.
    // => Otherwise, we search in the lower half to reduce it

    public int better(int mat[][]){
        int nr=mat.length;
        int nc=mat[0].length;

        PriorityQueue<int[]>pq=new PriorityQueue<>((x,y)->x[0]-y[0]);

        //first push lowest elemnt of each row
        for(int i=0;i<nr;i++) pq.offer(new int[]{mat[i][0],i,0});

        //since m*n is given as always add median elemnt is the one that appears (m*n)>>1 th time in PQ

        int cnt=0;
        int mid=(nr*nc)>>1;
        int ans=-1;
        while (cnt<=mid) {
            int top[]=pq.poll();
            ans=top[0];cnt++;
            int currRow=top[1];
            int currCol=top[2];

            if(currCol+1<nc) pq.offer(new int[]{mat[currRow][currCol+1],currRow,currCol+1});


        }

        return ans;
    }



//optimal solution: based on fact that the median in a set of N(N is odd) numbers is always the first number that is greater (N/2) numbers. 
// So we can do a BS form 1 to 2000(max possible mat[i][j]), then we just find the first elemnt such that its greater than (nRows*nCols)/2 elemnts of matrix lets call it x, to find it in better than m*n TC, again we can opt BS, for each row we find index last number less than or equl to x using BS then the number of elemnts in that row less than or equal to x is thatIdx+1, we accumulate it and return, finally return ans


    public int median(int[][] mat) {//log(maxEleInMat)*nRows*log(nCols)
        // code here
        int low=1,high=2000;
        int median=-1;
        int req=(mat.length*mat[0].length)>>1;//median should be greater than half number of elemnts
        while(low<=high){
            int mid=(low+high)>>1;
            int nOfElemntsLessThanMe=countOfNumbersLessThanOrEqualToX(mat,mid);
            // System.out.println(mid+" "+nelem);
            if(nOfElemntsLessThanMe>req){//first elemnt having hast g than req
                median=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return median;
    }

    int countOfNumbersLessThanOrEqualToX(int mat[][],int x){//nRows*log(nCOls)

        int ans=0;
        for(int row[]:mat){
            int low=0,high=row.length-1;
            int idx=-1;
            while(low<=high){
                int mid=(low+high)>>1;
                if(row[mid]<=x){//last element le x
                    idx=mid;
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            ans+=idx+1;
        }

        return ans;
    }


}
