package _12_Greedy;

import java.util.Arrays;

/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105

 */
public class _04_jump_game {

    // -------------brute force
    public boolean dfs(int nums[], int idx) {
        if (idx >= nums.length - 1)
            return true;
        boolean ans = false;
        for (int i = 1; i <= nums[idx]; i++) {
            boolean can_reach_end = (dfs(nums, i + idx));
            if (can_reach_end)
                return true;// no need to check further, we got 1 way
            ans |= dfs(nums, i + idx);// else move forward by checking
        }
        return ans;
    }

    public boolean tabulation(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];

        // Base case: The last index is always reachable from itself
        dp[n - 1] = true;

        // Traverse from second last index to 0
        for (int i = n - 2; i >= 0; i--) {
            int maxJump = Math.min(i + nums[i], n - 1); // Ensure we don't go out of bounds

            for (int j = i + 1; j <= maxJump; j++) {
                if (dp[j]) { // If any position ahead is reachable, mark current index as reachable
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0]; 
    }


    //----------------optimal:
    /*
Intuition
Move goal position to the left if we meet a simple condition.

Solution Video


⭐️⭐️ Don't forget to subscribe to my channel! ⭐️⭐️
■ Subscribe URL
http://www.youtube.com/channel/UC9RMNwYTL3SXCP6ShLWVFww?sub_confirmation=1

Subscribers: 4,139
Thank you for your support!

Approach
We have several ways to reach goal(= the last index) or other index from a current position, so my strategy is to move goal to left position if we meet a simple condition.

The condition is

⭐️ Points

if current position + maximum jump >= goal
If we meet the condition we update goal with current index.

Let's see how it works.

Input: nums = [2,3,1,1,4]
We start at the second position from the last.

[2,3,1,1,4]
       i g

i = current position
g = goal
Let's use the formula above.

current position + maximum jump >= goal
= 3 + 1 >= 4
= true
We can reach the current goal(= index 4) from current position(= index 3), that means if we reach index 3, we are sure that we can definitely reach the goal(= the last index).

That's why we can move goal to index 3.

Next,

[2,3,1,1,4]
     i g
current position + maximum jump >= goal
= 2 + 1 >= 3
= true
If true, we are sure we can reach index 3 from index 2. We know that if we reach index 3, we can reach the last index, so update goal with index 2. In the next time, if we can reach index 2, that means we can reach the last index(= 4)

Next,

[2,3,1,1,4]
   i g
I'll speed up.

current position + maximum jump >= goal
= 1 + 3 >= 2
= true
[2,3,1,1,4]
 i g
current position + maximum jump >= goal
= 0 + 2 >= 1
= true
In the end,

[2,3,1,1,4]
 g
Now, goal is index 0. That means we can reach the goal because we start from index 0, so before we return true or false, we check this condition.

if goal == 0
In this case

return true
Easy!😄
Let's see solution codes and step by step algorithm!

⭐️ I recently created a video on how I've been using LeetCode to learn.



Complexity
Time complexity: O(n)

Space complexity: O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;        
    }
}
Step by Step Algorithm
Initialization:

goal = len(nums) - 1
goal = len(nums) - 1: Initialize the variable goal to the last index of the array nums. This represents the goal position we want to reach.
Main Loop:

for i in range(len(nums) - 2, -1, -1):
for i in range(len(nums) - 2, -1, -1): Iterate backward through the array nums, starting from the second to last element down to the first element.
Checking Jumpability:

if i + nums[i] >= goal:
if i + nums[i] >= goal: Check if the current index i plus the maximum jump length at that position (nums[i]) is greater than or equal to the goal. If it is, it means we can jump from index i to the goal or beyond.
Updating Goal:

goal = i
goal = i: If the condition in step 3 is met, update the goal to the current index i. This means we have found a new closer position from which we can reach the previous goal position.
Return Result:

return True if goal == 0 else False
return True if goal == 0 else False: If we successfully reach the first index (i.e., goal == 0), return True, indicating that it's possible to reach the last index from the first index. Otherwise, return False.
     */
    public boolean canJump(int[] nums) {
        int my_curr_goal_to_reach=nums.length-1;
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]+i>=my_curr_goal_to_reach) my_curr_goal_to_reach=i;
        }
        return my_curr_goal_to_reach==0;
    }


    //--------------------FOLLOW UP:
    public int dfs_rec(int nums[],int idx){
        if(idx>=nums.length-1) return 0;
        int min=(int)(1e5);
        for(int i=1;i<=nums[idx];i++){
            min=Math.min(min,1+dfs_rec(nums,idx+i));
        }
        return min;
    }
    //--------tabulation of above code:
       public int tab(int[] nums) {//O(n^2)
        int dp[] = new int[nums.length];
        Arrays.fill(dp, (int) (1e5));
        dp[nums.length - 1] = 0; 
        for (int idx = nums.length - 2; idx >= 0; idx--) {
            int min = (int) (1e5);
            for (int i = 1; i <= nums[idx]; i++) {
                if(idx+i<nums.length) min = Math.min(min, 1 + dp[idx + i]);
            }
            dp[idx]=min;
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }

    //=========optimal: learn new startategy
    //in short, we make ranges and count:https://www.youtube.com/watch?v=7SBVnw7GSTk&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=288&ab_channel=takeUforward
    public int optimal(int[] nums) {
        int l=0,r=0;
        int nJumps=0;
        while(r<nums.length-1){
            int farthest_point_reachable_from_any_element_of_curr_range=0;
            for(int i=l;i<=r;i++){
                farthest_point_reachable_from_any_element_of_curr_range=Math.max(
                    farthest_point_reachable_from_any_element_of_curr_range,
                    i+nums[i]
                );
            }
            l=r+1;
            r=farthest_point_reachable_from_any_element_of_curr_range;
            nJumps++;
        }
        return nJumps;
    }
    //---------FOLLOW UP:
    /*
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
     */
    public boolean rec(int nums[], int idx, boolean isVis[]) {
        if (idx < 0 || idx >= nums.length)
            return false;
        if (nums[idx] == 0)
            return true;

        boolean ans = false;
        if (!isVis[idx]) {
            isVis[idx] = true;
            ans |= rec(nums, idx + nums[idx], isVis);
            ans |= rec(nums, idx - nums[idx], isVis);
        }
        return ans;
    }
}
