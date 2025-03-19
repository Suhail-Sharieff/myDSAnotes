package _13_Stack_Queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class _03_next_greater {
    public int[] bruet(int[] nums1, int[] nums2) {// O(n^2)
        HashMap<Integer, Integer> hs = new HashMap<>();
        int idx = 0;
        for (int e : nums2)
            hs.put(e, idx++);
        int ans[] = new int[nums1.length];
        Arrays.fill(ans, -1);
        idx = 0;
        for (int e : nums1) {
            int i = hs.get(e);
            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[j] > e) {
                    ans[idx] = nums2[j];
                    break;
                }
            }
            idx++;
        }
        return ans;
    }

    public int[] optimal(int[] nums1, int[] nums2) {// O(N)
        HashMap<Integer, Integer> hs = new HashMap<>();
        int idx = nums2.length - 1;
        Stack<Integer> st = new Stack<>();
        while (idx >= 0) {
            while (!st.isEmpty() && st.peek() < nums2[idx])
                st.pop();
            if (st.isEmpty())
                hs.put(nums2[idx], -1);
            else
                hs.put(nums2[idx], st.peek());
            st.push(nums2[idx]);
            idx--;
        }
        System.out.println(hs);
        int ans[] = new int[nums1.length];
        idx = 0;
        for (int e : nums1) {
            ans[idx++] = hs.get(e);
        }
        return ans;
    }

    // ----------------------------
    /*
     * 503. Next Greater Element II
     * Solved
     * Medium
     * Topics
     * Companies
     * Given a circular integer array nums (i.e., the next element of
     * nums[nums.length - 1] is nums[0]), return the next greater number for every
     * element in nums.
     * 
     * The next greater number of a number x is the first greater number to its
     * traversing-order next in the array, which means you could search circularly
     * to find its next greater number. If it doesn't exist, return -1 for this
     * number.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number.
     * The second 1's next greater number needs to search circularly, which is also
     * 2.
     * Example 2:
     * 
     * Input: nums = [1,2,3,4,3]
     * Output: [2,3,4,-1,4]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     */
    public int[] brute(int[] nums) {
        int ans[]=new int[nums.length];
        for(int idx=0;idx<nums.length;idx++){
            int max=nums[idx];
            for(int j=idx+1;j<nums.length;j++){
                if(nums[j]>nums[idx]){
                    max=nums[j];
                    break;
                }
            }
            if(max==nums[idx]){
                for(int j=0;j<idx;j++){
                    if(nums[j]>nums[idx]){
                    max=nums[j];
                    break;
                }
                } 
            }
            if(max!=nums[idx]) ans[idx]=max;
            else ans[idx]=-1;
        }
        return ans;
    }
    public int[] optimal(int nums[]) {//https://www.youtube.com/watch?v=7PrncD7v9YQ&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=303&ab_channel=takeUforward
        Stack<Integer> st = new Stack<>();
        int len = nums.length;
        int i = 2 * len - 1;
        int ans[] = new int[len];
        while (i >= 0) {
            while (!st.isEmpty() && st.peek() <= nums[i % len])
                st.pop();
            if (i < len) {
                if (st.isEmpty())
                    ans[i] = -1;
                else
                    ans[i] = st.peek();
            }
            st.push(nums[i % len]);
            i--;
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }
}
