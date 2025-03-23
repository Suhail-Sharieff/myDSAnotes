/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
 */

import java.util.Stack;

public class _09_largest_area_in_histogram {

    public int optimal(int[] nums) {
        int ps[]=prev_smaller(nums);
        int ns[]=next_smaller(nums);
        int ans=0;
        for(int i=0;i<nums.length;i++){
            int area=(ns[i]-ps[i]-1)*nums[i];
            ans=Math.max(ans,area);
        }
        return ans;
    }


    public int[] prev_smaller(int nums[]){
        Stack<Integer>st=new Stack<>();
        int ans[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            while(!st.isEmpty() && nums[i]<=nums[st.peek()]) st.pop();
            if(st.isEmpty()) ans[i]=-1;
            else ans[i]=st.peek();
            st.push(i);
        }
        // System.out.println(Arrays.toString(ans));
        return ans;
    }

    public int[] next_smaller(int nums[]){
        Stack<Integer>st=new Stack<>();
        int ans[]=new int [nums.length];
        for(int i=nums.length-1;i>=0;i--){
            while(!st.isEmpty() && nums[i]<=nums[st.peek()]) st.pop();
            if(st.isEmpty()) ans[i]=nums.length;
            else ans[i]=st.peek();
            st.push(i);
        }
        // System.out.println(Arrays.toString(ans));
        return ans;
    }
}