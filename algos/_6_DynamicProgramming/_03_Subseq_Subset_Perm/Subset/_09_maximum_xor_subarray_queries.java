package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;
/*3277. Maximum XOR Score Subarray Queries
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an array nums of n integers, and a 2D integer array queries of size q, where queries[i] = [li, ri].

For each query, you must find the maximum XOR score of any subarray of nums[li..ri].

The XOR score of an array a is found by repeatedly applying the following operations on a so that only one element remains, that is the score:

Simultaneously replace a[i] with a[i] XOR a[i + 1] for all indices i except the last one.
Remove the last element of a.
Return an array answer of size q where answer[i] is the answer to query i.

 

Example 1:

Input: nums = [2,8,4,32,16,1], queries = [[0,2],[1,4],[0,5]]

Output: [12,60,60]

Explanation:

In the first query, nums[0..2] has 6 subarrays [2], [8], [4], [2, 8], [8, 4], and [2, 8, 4] each with a respective XOR score of 2, 8, 4, 10, 12, and 6. The answer for the query is 12, the largest of all XOR scores.

In the second query, the subarray of nums[1..4] with the largest XOR score is nums[1..4] with a score of 60.

In the third query, the subarray of nums[0..5] with the largest XOR score is nums[1..4] with a score of 60.

Example 2:

Input: nums = [0,7,3,2,8,5,1], queries = [[0,3],[1,5],[2,4],[2,6],[5,6]]

Output: [7,14,11,14,5]

Explanation:

Index	nums[li..ri]	Maximum XOR Score Subarray	Maximum Subarray XOR Score
0	[0, 7, 3, 2]	[7]	7
1	[7, 3, 2, 8, 5]	[7, 3, 2, 8]	14
2	[3, 2, 8]	[3, 2, 8]	11
3	[3, 2, 8, 5, 1]	[2, 8, 5, 1]	14
4	[5, 1]	[5]	5
 

Constraints:

1 <= n == nums.length <= 2000
0 <= nums[i] <= 231 - 1
1 <= q == queries.length <= 105
queries[i].length == 2 
queries[i] = [li, ri]
0 <= li <= ri <= n - 1
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
5,087/11.9K
Acceptance Rate
42.7%
Topics
icon
Companies
Hint 1
Hint 2
Hint 3 */
public class _09_maximum_xor_subarray_queries {
    //brute force: for every [l,r] go through all subarrays of l,r, fetch max xor of all possible subarrays : n^4 * q


    //, watch://https://www.youtube.com/watch?v=alNyC5qWrx4

    //better: we can rediuce it to n^2 * q by precomputing xor of all elemnts from [l,r]
    public int[] better(int[] nums, int[][] queries) {
        int n=nums.length;
        int nq=queries.length;
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++) dp[i][i]=nums[i];
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j=i+len-1;
                dp[i][j]=dp[i][j-1]^dp[i+1][j];
            }
        }
        int ans[]=new int[nq];
        for(int x=0;x<nq;x++){
            int l=queries[x][0];
            int r=queries[x][1];
            for(int i=l;i<=r;i++) for(int j=l;j<=r;j++) ans[x]=Math.max(ans[x],dp[i][j]);
        }
        return ans;
    }



    //optimal: reduce to just q, once computed dp[i][j], where dp[i][j] has xor of all elemnts from [i,j], again modify inplace by taking max from its children in tree
    public int[] optimal(int[] nums, int[][] queries) {
        int n=nums.length;
        int nq=queries.length;
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++) dp[i][i]=nums[i];
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j=i+len-1;
                dp[i][j]=dp[i][j-1]^dp[i+1][j];
            }
        }
        //----extra
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j=i+len-1;
                dp[i][j]=Math.max(dp[i][j],Math.max(dp[i][j-1],dp[i+1][j]));
            }
        }//----
        int ans[]=new int[nq];
        for(int x=0;x<nq;x++){
            int l=queries[x][0];
            int r=queries[x][1];
             ans[x]=Math.max(ans[x],dp[l][r]);
        }
        return ans;
    }
}
