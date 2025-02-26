package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;

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
        int nums[]={1,15,7,9,2,5,10};
        int k=3;
        rec(nums, 0, nums.length-1, k);
    }
    static void rec(int nums[],int i,int j,int max_len){
        for(int k=i;k<nums.length;k+=max_len){
            
        }
    }

    static void prnt(int nums[],int i,int j){
    //    System.out.println(Arrays.toString(Arrays.subar));
    }

}
