package _6_DynamicProgramming._01_1D;

/*
Problem statement
You are given an array/list of ‘N’ integers. You are supposed to return the maximum sum of the subsequence with the constraint that no two elements are adjacent in the given array/list.

Note:
A subsequence of an array/list is obtained by deleting some number of elements (can be zero) from the array/list, leaving the remaining elements in their original order.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 500
1 <= N <= 1000
-10^5 <= ARR[i] <= 10^5

Where 'ARR[i]' denotes the 'i-th' element in the array/list.

Time Limit: 1 sec.
Sample Input 1:
2
3
1 2 4
4
2 1 4 9
Sample Output 1:
5
11
Explanation to Sample Output 1:
In test case 1, the sum of 'ARR[0]' & 'ARR[2]' is 5 which is greater than 'ARR[1]' which is 2 so the answer is 5.

In test case 2, the sum of 'ARR[0]' and 'ARR[2]' is 6, the sum of 'ARR[1]' and 'ARR[3]' is 10, and the sum of 'ARR[0]' and 'ARR[3]' is 11. So if we take the sum of 'ARR[0]' and 'ARR[3]', it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
Sample Input 2:
2
5
1 2 3 5 4
9
1 2 3 1 3 5 8 1 9
Sample Output 2:
8
24
Explanation to Sample Output 2:
In test case 1, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]' and 'ARR[4]', i.e. 8, it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.

In test case 2, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]', 'ARR[4]', 'ARR[6]' and 'ARR[8]', i.e. 24 so, it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
 */
public class _05_maxSumOfNonAdj {
    static int mod = 1_000_000_007;


    //central idea: we need to choose non adj eleemnts, we can choose ith elemnt and its i-2 th elemnt recursively for not adj, else we coose i-1th recursively under that, so for each node of recursion tree we choose i-1th(if exculded ith) or (i-2)th if included ith

    //down to top
	static int usingRecursion(int arr[],int idx){
        //call like: usingRecursion(nums,nums.length-1);
        if(idx<0) return 0;
        int include_curr=arr[idx]+usingRecursion(arr, idx-2);//idx-2 coz we need non adj elemnts
        int exclude_curr=0+usingRecursion(arr, idx-1);//dont include curr elemnt instead choose prev and preoceed 
        return Math.max(include_curr, exclude_curr);

    }
    //memoize recursion
    static int memoize(int arr[], int idx, int dp[]) {
        //call like: memoize(nums,nums.length-1,dp);
        if (idx<0)
            return 0;
        if (idx == 0) {
            dp[idx] = Math.max(arr[0], 0);
            return dp[idx];
        }
        if (dp[idx] != -1)
            return dp[idx];
        int ifPickedCurr = (arr[idx] + memoize(arr, idx - 2, dp)) % mod;
        int ifNotPickedCurr = (memoize(arr, idx - 1, dp)) % mod;
        dp[idx] = Math.max(ifPickedCurr, ifNotPickedCurr);

        return dp[idx];
    }

    public int tabulation(int[] arr) {
        if(arr.length==0) return 0;
        if(arr.length==1) return Math.max(arr[0], 0);
        int dp[]=new int[arr.length];
        dp[0]=arr[0];
        for (int idx = 1; idx < arr.length; idx++) {
            int ifPickedCurr=(idx>1)?(arr[idx]+dp[idx-2]):(arr[idx]);//NOT 0-----VVIMP
            int ifNotPickedCurr=dp[idx-1];
            dp[idx]=Math.max(ifPickedCurr, ifNotPickedCurr);
        }
        return dp[arr.length-1];
    }
}
