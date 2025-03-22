
/*
 You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
Example 2:

Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
Example 3:

Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.
 

Constraints:

1 <= nums.length <= 1000
-109 <= nums[i] <= 109
 */

import java.util.Stack;

public class _07_sum_of_subarray_ranges {
    public long brute(int[] nums) {
        long ans=0l;
        for(int i=0;i<nums.length;i++){
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            for(int j=i;j<nums.length;j++){
                //subarr[i..n]
                min=Math.min(nums[j],min);
                max=Math.max(nums[j],max);
                ans+=(max-min);
            }
        }
        return ans;
    }



    //--------------optimal:O(5n)

    //since we need to find sum(max-min) for each subarray, this can also be written as [sum(max)-sum(min)] for each subarray, prev_file u know how to calacute sum(min), here additionally we find sum(max), ans is thier differnec
     public long subArrayRanges(int[] nums) {
        int [] previous_smaller_or_equal_idx=previous_smaller_or_equal(nums);
        int [] next_smaller_idx=next_smaller(nums);


        int [] previous_larger_or_equal_idx=previous_larger_or_equal(nums);
        int [] next_larger_idx=next_larger(nums);

        long sum_of_mins=0l,sum_of_maxs=0l;

        for(int i=0;i<nums.length;i++){
            sum_of_mins+=(i-previous_smaller_or_equal_idx[i])*1l*(next_smaller_idx[i]-i)*nums[i];
            sum_of_maxs+=(i-previous_larger_or_equal_idx[i])*1l*(next_larger_idx[i]-i)*nums[i];
        }
        return  sum_of_maxs-sum_of_mins;
     }

     public static int[] previous_smaller_or_equal(int nums[]){
        Stack<int[]>st=new Stack<>();
        int previous_smaller_or_equal[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            while (!st.isEmpty() && st.peek()[0]>nums[i]) st.pop();
            if(st.isEmpty()) previous_smaller_or_equal[i]=-1;
            else previous_smaller_or_equal[i]=st.peek()[1];
            st.push(new int[]{nums[i],i});
        }
        return previous_smaller_or_equal;
     }


     public static int[] next_smaller(int nums[]){
        Stack<int[]>st=new Stack<>();
        int next_smaller[]=new int[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            while(!st.isEmpty() && st.peek()[0]>=nums[i]) st.pop();
            if(st.isEmpty()) next_smaller[i]=nums.length;
            else next_smaller[i]=st.peek()[1];
            st.push(new int[]{nums[i],i});
        }
        return next_smaller;
     }

    public static int[] previous_larger_or_equal(int nums[]){
        Stack<int[]>st=new Stack<>();
        int prev_larger[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            while(!st.isEmpty() && st.peek()[0]<nums[i]) st.pop();
            if(st.isEmpty()) prev_larger[i]=-1;
            else prev_larger[i]=st.peek()[1];
            st.push(new int[]{nums[i],i});
        }
        return prev_larger;
    }
    public static int[] next_larger(int nums[]){
        Stack<int[]>st=new Stack<>();
        int next_larger[]=new int[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            while(!st.isEmpty() && st.peek()[0]<=nums[i]) st.pop();
            if(st.isEmpty()) next_larger[i]=nums.length;
            else next_larger[i]=st.peek()[1];
            st.push(new int[]{nums[i],i});
        }
        return next_larger;
     }


}
