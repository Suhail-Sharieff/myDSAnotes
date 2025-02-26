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
    public int maximumSumSubarray(int[] nums, int k) {// O(N)------------sliding window approach
        //https://www.youtube.com/watch?v=pBWCOCS636U&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=274&ab_channel=takeUforward
        // Logic:
        /*
         * Possibilities r:
         * ->sum(0..k)+0
         * ->sum(0...k-1)+sum(n..k)
         * ->sum(0....k+1)+sum(n...k-1)
         * ...like that suppose k=3, we can choose [3left+0right] or [2left+1right] or
         * [1left+2right] or [0left+3right] ans is max of all these
         */
        int left_sum = 0;
        for (int i = 0; i < k; i++) {
            left_sum += nums[i];
        }
        int right_sum=0;

        int ans=left_sum;

        int right_ptr=nums.length-1;
        int left_ptr=k-1;

        while (left_ptr!=-1) {
            left_sum-=nums[left_ptr--];
            right_sum+=nums[right_ptr--];
            ans=Math.max(ans, left_sum+right_sum);
        }

        return ans;
        
    }
}
