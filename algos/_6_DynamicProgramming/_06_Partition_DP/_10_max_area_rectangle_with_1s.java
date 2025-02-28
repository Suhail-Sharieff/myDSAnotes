package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;
import java.util.Stack;

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
public class _10_max_area_rectangle_with_1s {
    //https://leetcode.com/problems/max-area-of-island/description/ was about find largest island, no matter it is of whechever shape, but here specifically gievn that it should be rectangle, hence not a graph problem
    public static void main(String[] args) {
        int heights[]={1,3,4,2,4,5,1,4,3,6,7};
        get_prev_smallest_of_each(heights);
        get_next_largest_of_each(heights);

    }

    /*
    7 
     */

    //for every heights[i], move untill we encounter a lesser height than heights[i] on left and right, compute area, ans is max of all areas for each index, ie idea is that we assume for each heights[i] that it is minimum, and cimpute area with its largers on left and right, finally take max---O(N^2)
    public int brute_force(int[] heights) {
        if(heights.length==1) return heights[0];
        int ans=0;
        for(int i=0;i<heights.length;i++){
            int left_ptr=i,right_ptr=i;
            while(left_ptr>=0 && heights[left_ptr]>=heights[i]) left_ptr--;
            while(right_ptr<heights.length && heights[right_ptr]>=heights[i]) right_ptr++;
            ans=Math.max(ans,heights[i]*(right_ptr-left_ptr-1));
        }
        return ans;
    }


    static int[] get_prev_smallest_of_each(int arr[]){
        Stack<Integer>st=new Stack<>();
        int prev_smallest[]=new int[arr.length];
        Arrays.fill(prev_smallest, -1);
        for(int i=0;i<arr.length;i++){
            while(!st.isEmpty()){
                if(arr[i]>st.peek()){
                    prev_smallest[i]=st.peek();
                    break;
                }
                st.pop();
            }
            st.push(arr[i]);
        }
        // System.out.println(Arrays.toString(prev_smallest));
        return prev_smallest;
    }

    static int[] get_next_largest_of_each(int arr[]){
        Stack<Integer>st=new Stack<>();
        int next_greater[]=new int[arr.length];
        Arrays.fill(next_greater, -1);
        for(int i=arr.length-1;i>=0;i--){
            while(!st.isEmpty()){
                if(arr[i]<st.peek()){
                    next_greater[i]=st.peek();
                    break;
                }
                st.pop();
            }
            st.push(arr[i]);
        }
        System.out.println(Arrays.toString(next_greater));
        return next_greater;
    }


    //---------better: memoization of brute force, see in brute force that for each idx, u r moving tilll height less than currHeight on left and right, ie the previousSmaller(on left) and nextSmaller(on Right) for each index, what if we pre compute it and store somewhere?, we can calculatae for each index in O(1) time
}
