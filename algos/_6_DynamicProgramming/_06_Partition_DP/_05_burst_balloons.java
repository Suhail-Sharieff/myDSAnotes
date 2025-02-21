//hardest part is to recognize that this is a MCM problem

/*
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

 

Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10
 

Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100
*/


package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;

public class _05_burst_balloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = 1; arr[n+1] = 1;
        for(int i = 1; i <= n; i++) arr[i] = nums[i-1];
        
        int dp[][] = new int[n+1][n+1];
        for(int[] a: dp) Arrays.fill(a, -1);
        return rec(arr, 1, n, dp);
    }
    public int rec(int[] arr, int left, int right, int[][] dp){
        if(left > right) return 0;
        if(dp[left][right]!=-1) return dp[left][right];
        int max = 0;
        for(int i = left; i<=right ; i++){
            int costOf_left = rec(arr, left, i-1, dp);
            int costOf_last = arr[left-1]*arr[i]*arr[right+1];
            int costOf_right = rec(arr, i+1, right, dp);
            max = Math.max(max, costOf_left+costOf_last+costOf_right); 
        }
        return dp[left][right] = max;
    }
}
