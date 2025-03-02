package _10_Sliding_Window._02_dynamic_window;


public class _03_max_consecutve_ones {
    /*
     * 
     * LEVEL 1
     * 
     * Given a binary array nums, return the maximum number of consecutive 1's in
     * the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive
     * 1s. The maximum number of consecutive 1s is 3.
     * Example 2:
     * 
     * Input: nums = [1,0,1,1,0,1]
     * Output: 2
     * 
     */
    public int level1(int[] nums) {
        int cnt = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }

    /*
     * Given a binary array nums, return the maximum number of consecutive 1's in
     * the array if you can flip at most one 0.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,0,1,1,0]
     * Output: 4
     * Explanation:
     * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4
     * consecutive ones.
     * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3
     * consecutive ones.
     * The max number of consecutive ones is 4.
     * Example 2:
     * 
     * Input: nums = [1,0,1,1,0,1]
     * Output: 4
     * Explanation:
     * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4
     * consecutive ones.
     * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4
     * consecutive ones.
     * The max number of consecutive ones is 4.
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     * 
     * 
     * Follow up: What if the input numbers come in one by one as an infinite
     * stream? In other words, you can't store all numbers coming from the stream as
     * it's too large to hold in memory. Could you solve it efficiently?
     */

    static int level2_brute(int nums[]) {
        int max = 0;
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] == 0) {
                int leftPtr = idx - 1, right_ptr = idx + 1;
                while (leftPtr >= 0 && nums[leftPtr] == 1)
                    leftPtr--;
                while (right_ptr < nums.length && nums[right_ptr] == 1)
                    right_ptr++;
                max = Math.max(max, (idx - leftPtr) + (right_ptr - idx - 1));
            }
        }
        return max;
    }

    static int level2_optimal(int nums[]) {//main intituin: try maintanign max 1 zero in window, f found more than 1 move left to right untill we get max 1 zero
        /*
         * Optimal Approach: Sliding Window
         * We use a sliding window technique to efficiently find the longest subarray
         * that contains at most one 0.
         * 
         * How the Sliding Window Works
         * Expand the window (right pointer)
         * 
         * Move right forward.
         * Count the number of 0s inside the window.
         * Shrink the window (left pointer)
         * 
         * If there are more than one 0, move left forward until only one 0 remains.
         * Keep track of the maximum window size.
         * 
         * Step-by-Step Dry Run
         * Let's go step by step on nums = [1, 0, 1, 1, 0, 1].
         * 
         * left right nums[right] zero_count Window (valid?) Max Length
         * 0 0 1 0 ✅ [1] (valid) 1
         * 0 1 0 1 ✅ [1,0] (valid) 2
         * 0 2 1 1 ✅ [1,0,1] (valid) 3
         * 0 3 1 1 ✅ [1,0,1,1] (valid) 4
         * 0 4 0 2 ❌ [1,0,1,1,0] (invalid) 4
         * 1 4 (left moves) 1 (zero removed) ✅ [0,1,1,0] (valid) 4
         * 1 5 1 1 ✅ [0,1,1,0,1] (valid) 4
         * 💡 Final Answer: 4
         * 
         * 
         */

        int left_ptr = 0, right_ptr = 0; // Two pointers for sliding window
        int nZeroes = 0; // Track number of zeros in the window
        int max = 0; // Store the max consecutive ones (with at most one zero flipped)

        while (right_ptr < nums.length) { // Expand window
            if (nums[right_ptr] == 0)
                nZeroes++; // Count the zero

            while (nZeroes > 1) { // If more than one zero, shrink window
                if (nums[left_ptr] == 0)
                    nZeroes--; // Remove zero from count
                left_ptr++; // Move left pointer
            }

            max = Math.max(max, right_ptr - left_ptr + 1); // Update max length
            right_ptr++; // Expand right pointer
        }

        return max;

    }



    /*
    FOLLOW UP:
    Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
     */


     public int level3(int nums[],int k){

        // main intituin: try maintanign max k zeroes in window, if found more than k, move left to right untill we get max k zeroes


        int left_ptr = 0, right_ptr = 0; // Two pointers for sliding window
        int nZeroes = 0; // Track number of zeros in the window
        int max = 0; // Store the max consecutive ones (with at most one zero flipped)

        while (right_ptr < nums.length) { // Expand window
            if (nums[right_ptr] == 0)
                nZeroes++; // Count the zero

            while (nZeroes > k) { // If more than k zeroes, shrink window
                if (nums[left_ptr] == 0)
                    nZeroes--; // Remove zero from count
                left_ptr++; // Move left pointer
            }

            max = Math.max(max, right_ptr - left_ptr + 1); // Update max length
            right_ptr++; // Expand right pointer
        }

        return max;
     }

    public static void main(String[] args) {
        int nums[] = { 1, 0, 1, 1, 0 };
        System.out.println(level2_optimal(nums));
    }
}
