package _10_Sliding_Window._01_constant_window;
/*
Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.

Note: A subarray is a contiguous part of any given array.

Examples:

Input: arr[] = [100, 200, 300, 400] , k = 2
Output: 700
Explanation: arr3  + arr4 = 700, which is maximum.
Input: arr[] = [100, 200, 300, 400] , k = 4
Output: 1000
Explanation: arr1 + arr2 + arr3 + arr4 = 1000, which is maximum.
Input: arr[] = [100, 200, 300, 400] , k = 1
Output: 400
Explanation: arr4 = 400, which is maximum.
Constraints:
1 <= arr.size() <= 106
1 <= arr[i]<= 106
1 <= k<= arr.size()
 */
public class _01_max_sum_subarray_of_size_k {
    public int maximumSumSubarray(int[] nums, int k) {//O(N)------------sliding window approach
        // Code here
        if(nums.length<k) return 0;
        int sum=0;
        for(int z=0;z<k;z++){
            sum+=nums[z];
        }
        int max=sum;
        int i=0,j=k-1;
        while(j<nums.length){
            // System.out.println("From "+i+" to "+j+" sum:"+sum+" max:"+max);
            sum=sum-nums[i];
            i++;j++;
            if(j==nums.length) break;
            sum+=nums[j];
            max=Math.max(max,sum);
        }

        return max;
    }
}
