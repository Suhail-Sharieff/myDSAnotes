package _10_Sliding_Window._02_dynamic_window;

public class _01_longest_subarray_with_sumK {
    // TAsk: find longest subarray length with sum<=K
    // https://www.youtube.com/watch?v=9kdHxplyl5I&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=272&ab_channel=takeUforward

    public static void main(String[] args) {
        int k = 14;
        int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(brute_force(nums, k));
        System.out.println(optimal(nums, k));
    }

    // --------lopp via all subarrays
    static int brute_force(int nums[], int k) {// O(n^2)
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for (int j = i; j < nums.length; j++) {
                currSum += nums[j];
                if (currSum > k)
                    break;
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    // -----------better solution: 2 pointer technique(optimal if we want to print)
    // initialize l=r=0, take sum, at each point shift right , whenevr sum>k, shrink
    // ie move l, else move right
    static int better(int nums[], int k) {
        int ans = 0;
        int sum = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            sum += nums[r];
            while (sum > k) {
                sum -= nums[l++];
            }
            if (sum <= k) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans;
    }

    // ------optimal(cant use if we need to print also)
    static int optimal(int nums[], int k) {// O(2N)
        int ans = 0;
        int sum = 0;
        int l = 0, r = 0;
        while (r < nums.length) {// O(N)
            sum += nums[r];
            if (sum > k) {// overal O(N)//-------while changed to if , since we need only length
                sum -= nums[l++];
            }
            if (sum <= k) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans;
    }

    // -------------------------------FOLLOW UP:
    /*
     * 930. Binary Subarrays With Sum
     * 
     * Given a binary array nums and an integer goal, return the number of non-empty
     * subarrays with a sum goal.
     * 
     * A subarray is a contiguous part of the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,0,1,0,1], goal = 2
     * Output: 4
     * Explanation: The 4 subarrays are bolded and underlined below:
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * Example 2:
     * 
     * Input: nums = [0,0,0,0,0], goal = 0
     * Output: 15
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 3 * 104
     * nums[i] is either 0 or 1.
     * 0 <= goal <= nums.length
     */
    public int brute(int[] nums, int goal) {// O(n^2)---O(1)
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == goal)
                    c++;
            }
        }
        return c;
    }
    //---------better: the normal sliding algo if we apply ie whenver we encoubnter that window sum exceeds given goal,shrink the window, BUT BUT BUT u may have zeroes on right, so if we include them also, the sum can reman equal to target, ie if we apply normal sliding window tehniche for maintaining sum==k, we may skip subarrays that could have been formed beyond right_ptr, so we use the idea (nSubarraysWithSum==k)=ans=(nSubarraysWithSum<=K)-(nSubarraysWithSum<k)
    public int better_(int[] nums, int goal) {
        return nSubarrays_with_target(nums,goal)-nSubarrays_with_target(nums,goal-1);
    }

    public int nSubarrays_with_target(int nums[],int target){
        int window_sum=0;//we try to maintain window_sum<=target
        int left_ptr=0,right_ptr=0,cnt=0;
        while(right_ptr<nums.length){
            window_sum+=nums[right_ptr];
            while(left_ptr<=right_ptr && window_sum>target){
                //shrinking window
                window_sum-=nums[left_ptr];
                left_ptr++;
            }
            cnt+=right_ptr-left_ptr+1;
            right_ptr++;
        }
        return cnt;
    }
    /*
Approach 3: Sliding Window in One Pass
Intuition
In the previous approach, we were finding the answer by calling the sliding window two times. However, we can optimize it to a single pass.

To do this, we track the number of zeros at the start of the current window. Each contiguous sequence of zeros at the start of the window can be considered separately when determining the total number of subarrays that sum up to the goal. That is, we need to increment the totalCount by 1 + prefix zeros. This is crucial because each subarray within the window, along with each combination of prefix zeros, contributes to the total count of subarrays that sum up to the goal.

Leading zeros in a window don't affect the sum, but they create opportunities for more subarrays to reach the target goal.

The remaining logic is the same as the previous sliding window approach. We iterate through the array nums using two pointers: start and end, representing the start and end indices of the current window.

If the sum of the current window exceeds the goal, we adjust the window by moving the start pointer forward until the sum is less than or equal to the goal. Along with adjusting the start pointer, we also need to update the prefix zeros count accordingly with the current window. If the start pointer is pointing to 0, we increment the prefix zero count; otherwise, if it's pointing to 1, we reset the prefix zero count to 0.

For example, consider a window represented by the array [0, 0, 1, 1]. In this window, there are 2 leading zeros. This means that the window can sum up to 2 in 2 + 1 = 3 ways.

Refer to the visual slideshow demonstrating the sliding window in one pass:

Current

Algorithm
Initialize variables start, prefixZeros, currentSum, and totalCount to 0.
Iterate through the array using the end variable as the end index of the sliding window.
Add the current element to the currentSum.
Enter a while loop to shrink the window from the left side if the sum exceeds the goal or if the element at the start of the window is 0.
Inside the while loop, check if the element at the start of the window is 1. If it is, reset the prefixZeros count to 0. Otherwise, increment the prefixZeros count.
Then subtract the element at the start of the window from the currentSum and increment the start pointer to move the window.
If the currentSum is equal to the goal, increment the totalCount by 1 plus the prefixZeros count.
Finally, return the totalCount.
Implementation

Complexity Analysis
Let n be the length of the nums array.

Time complexity: O(n)

The function iterates through the nums array once using a single for loop (end loop).

Inside the loop, the while loop might contract the window, but the total number of iterations within this loop is still bounded by the number of elements in the array (n).

Therefore, the overall time complexity is dominated by the single iteration through the array, resulting in O(n).

Space complexity: O(1)

The space complexity is O(1) because the algorithm uses a constant amount of space for variables such as start, currentSum, and totalCount. The space required does not depend on the size of the input array.
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int start = 0;
        int prefixZeros = 0;
        int currentSum = 0;
        int totalCount = 0;

        // Loop through the array using end pointer
        for (int end = 0; end < nums.length; end++) {
            // Add current element to the sum
            currentSum += nums[end];
            
            // Slide the window while condition is met
            while (start < end && (nums[start] == 0 || currentSum > goal)) {
                if (nums[start] == 1) {
                    prefixZeros = 0;
                } else {
                    prefixZeros++;
                }
                
                currentSum -= nums[start];
                start++;
            }
            
            // Count subarrays when window sum matches the goal
            if (currentSum == goal) {
                totalCount += 1 + prefixZeros;
            }
        }
        
        return totalCount;

    }



    //-------------FOLLOW UP:
    /*
1248. Count Number of Nice Subarrays

Hint
Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

 

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
     */
    public int numberOfSubarrays(int[] nums, int k) {
        return func(nums,k)-func(nums,k-1);
    }

    public int func(int[] nums, int k){
        int nOdd=0;
        int cnt=0;
        int left_ptr=0,right_ptr=0;
        while(right_ptr<nums.length){
            if((nums[right_ptr]&1)!=0){
                nOdd++;
            }
            while(left_ptr<=right_ptr && nOdd>k){
                if((nums[left_ptr]&1)!=0) nOdd--;
                left_ptr++;
            }
            cnt+=right_ptr-left_ptr+1;
            right_ptr++;
        }
        return cnt;
    }
}

