package _6_DynamicProgramming._06_Partition_DP;

//TEaches Multiple partitions how to do
/*
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
 */
public class _08_partition_array_for_max_sum {
    public static void main(String[] args) {
        int nums[] = { 1, 15, 7, 9, 2, 5, 10 };
        int k = 3;
        System.out.println(rec(nums, 0, k));
    }
    /*
     * 
     * PRINTING PARTITIONS:
     * 
     * static void rec(int nums[],int i,int max_len){
     * if(i==nums.length) return;
     * StringBuilder sb=new StringBuilder();
     * for(int j=i;j<Math.min(nums.length, j+max_len);j++){
     * sb.append(prnt(nums, i, j)).append(" AND ").append(prnt(nums, j+1,
     * Math.min(nums.length, j+max_len)));
     * rec(nums, j+1, max_len);
     * }
     * System.out.println(sb);
     * }
     * 
     * static String prnt(int nums[],int i,int j){
     * return(Arrays.toString(Arrays.copyOfRange(nums, i,j+1)));
     * 
     * }
     * 
     */

    static int rec(int nums[], int i, int k) {
        if (i == nums.length)
            return 0;
        int len = 0, maxE = Integer.MIN_VALUE, maxSum = 0;
        for (int j = i; j < Math.min(i + k, nums.length); j++) {
            len++;
            maxE = Math.max(maxE, nums[j]);

            int currSum = len * maxE + rec(nums, j + 1, k);// since we will chage all elemnts in subarray of size n to
                                                           // maxE, we can directly calc its sum as len*maxE
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    static int mem(int nums[], int i, int k, int dp[]) {
        if (i == nums.length)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int len = 0, maxE = Integer.MIN_VALUE, maxSum = 0;
        for (int j = i; j < Math.min(i + k, nums.length); j++) {
            len++;
            maxE = Math.max(maxE, nums[j]);

            int currSum = len * maxE + mem(nums, j + 1, k, dp);// since we will chage all elemnts in subarray of size n
                                                               // to maxE, we can directly calc its sum as len*maxE
            maxSum = Math.max(maxSum, currSum);
        }
        return dp[i] = maxSum;
    }

    static int tab(int nums[], int k) {
        int dp[] = new int[nums.length+1];//+1 coz we will use dp[j+1] down
        for (int i = nums.length-1; i >= 0; i--) {
            int len = 0, maxE = Integer.MIN_VALUE, maxSum = 0;
        for (int j = i; j < Math.min(i + k, nums.length); j++) {
            len++;
            maxE = Math.max(maxE, nums[j]);

            int currSum = len * maxE + dp[j + 1];// since we will chage all elemnts in subarray of size n
                                                               // to maxE, we can directly calc its sum as len*maxE
            maxSum = Math.max(maxSum, currSum);
        }
            dp[i]=maxSum;
        }
        return dp[0];
    }

}
