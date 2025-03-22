/*

Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
 

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104

 */

import java.util.Arrays;
import java.util.Stack;

public class _06_sum_of_subarray_sum {
 
    public static void main(String[] args) {
        int nums[]={11,81,94,43,3};
        previous_smaller_or_equal(nums);
        next_smaller(nums);

    }
    static int m=1_000_000_007;


    /////////---brute
    public int brute(int[] nums) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            int min=nums[i];
            for(int j=i;j<nums.length;j++){
                //subnums: nums[i...j]
                min=Math.min(nums[j],min);
                sum=(sum+min)%m;
            }
        }
        return sum;
    }


    //-----------------optimal:
    /*
    For every elemnt arr[i], find store the indexies of its previous_smaller_or_equal_element(NOT just previous_smaller consider the case [1,1]) and also next_smaller elemnt index, left_len=i-psee[i], right_len=nse[i]-i, then 
    HEART OF PROBLEM is observation that number of subarrays from psse[i] to nse[i] whichs of lenth (left_len+right_len) is left_len*right_len, such that all of them has nums[i], ie in everyone nums[i] is min
     */

        //https://www.youtube.com/watch?v=v0e8p9JCgRc&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=306&ab_channel=takeUforward

     public static int optimal(int nums[]){
        int [] previous_smaller_or_equal_indx=previous_smaller_or_equal(nums);
        int [] next_smaller_idx=next_smaller(nums);

        long ans=0l;

        for(int i=0;i<nums.length;i++){
            int left_len=i-previous_smaller_or_equal_indx[i];
            int right_len=next_smaller_idx[i]-i;
            int nSubarrays_with_curr_as_min=(left_len*right_len);

            ans=(ans+nums[i]*1l*nSubarrays_with_curr_as_min)%m;
        }

        return (int)(ans);
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
        System.out.println(Arrays.toString(previous_smaller_or_equal));
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
        System.out.println(Arrays.toString(next_smaller));
        return next_smaller;
     }
 }