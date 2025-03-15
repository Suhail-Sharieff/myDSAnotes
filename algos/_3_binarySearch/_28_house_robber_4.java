package _3_binarySearch;

import java.util.Arrays;

/*
 * 
2560. House Robber IV
Solved
Medium
Topics
Companies
Hint
There are several consecutive houses along a street, each of which has some money inside. There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.

The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.

You are given an integer array nums representing how much money is stashed in each house. More formally, the ith house from the left has nums[i] dollars.

You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.

Return the minimum capability of the robber out of all the possible ways to steal at least k houses.

 

Example 1:

Input: nums = [2,3,5,9], k = 2
Output: 5
Explanation: 
There are three ways to rob at least 2 houses:
- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
Therefore, we return min(5, 9, 9) = 5.
Example 2:

Input: nums = [2,7,9,3,1], k = 2
Output: 2
Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= (nums.length + 1)/2
 */
public class _28_house_robber_4 {
    /*
Overview
This is yet another problem based on the House Robber series! This article will assume some prior knowledge of the original version, so you may want to solve that before this one. So before diving in, let's quickly recall the core idea behind the original problem.

In the classic House Robber problem, the goal is to maximize the total amount stolen from a row of houses while following one key restriction: the robber cannot rob two consecutive houses. This forces the robber into a branched decision making process that at each house, they must choose whether to rob it or skip it. If they robs it, they must add its value to the best amount stolen from two houses before. If they skips it, they simply takes the best amount stolen from the previous house. This naturally leads to a recursive relationship:

maxAmount(houseNumber) = max(maxAmount(houseNumber - 1), maxAmount(houseNumber - 2) + amount(houseNumber))

Using dynamic programming, we can store these values and efficiently compute the maximum amount the robber can steal.

In this current problem, the robber still has to follow the restraint that they cannot steal from two consecutive houses. However, this time, instead of maximizing the total reward, they want to minimize the maximum amount stolen from any single house while ensuring that at least k houses are robbed.

Similar to the original problem, we can think of a recursive relation to solve this. Again, we have two choices:

Rob the current house (but then we must skip the next house).
Skip the current house and move forward.
However, unlike the original problem, we need an additional condition—ensuring that we rob at least k houses. The dynamic programming solution involves a state dp[houseIndex][numberOfHousesRobbed]. Since we iterate over n houses and track up to k robbed houses, the problem becomes more complex, and solving it with dynamic programming takes O(n⋅k) time.

Problems that require minimizing the maximum or maximizing the minimum often suggest a binary search approach. Instead of searching through indices or subsets directly, we can binary search on the capability (i.e., something like the maximum amount stolen from any single house). By determining whether a given capability is achievable, we can efficiently narrow down the possible solutions. If you're unfamiliar with this technique, you can refer to this guide to learn more about binary search.

Approach: Binary Search
Intuition
Instead of focusing on maximizing a total sum, we need to guarantee that the maximum amount stolen from any robbed house is as small as possible while still robbing at least k houses. A brute force approach would involve checking every possible way to rob k houses while obeying the adjacency constraint, but this would be too slow for large inputs.

A more efficient way to approach this problem is to recognize that we are trying to minimize the maximum stolen amount while ensuring that at least k houses are robbed. This naturally leads to using binary search on the maximum reward that the robber can steal from any single house.

We define the search space based on the possible values for this maximum reward. The smallest possible value for this maximum reward is min(nums) (the lowest value in the house list), and the largest possible value is max(nums) (the highest value in the house list). This gives us a range of [minReward, maxReward], where minReward = min(nums) or more specifically 1 and maxReward = max(nums).

We use binary search to determine the minimum possible capability that still allows robbing at least k houses. At each step, we take the middle value in our range (midReward = (minReward + maxReward) / 2) and check whether it's possible to rob at least k houses while ensuring that no single robbed house has a value greater than midReward.

To determine whether a particular midReward is feasible, we use a greedy approach. We iterate through the list of house values and greedily select houses that have at most midReward. Since we cannot rob consecutive houses, we skip the next house each time we choose one. We keep a count of how many houses have been robbed, and if we reach at least k houses, it means the current midReward is achievable.

If it is possible to rob at least k houses while keeping the "maximum stolen amount ≤ midReward", then we try lowering it by moving the binary search range to the left (maxReward = midReward).
If it is not possible, it means midReward is too low, so we increase it by moving the search range to the right (minReward = midReward + 1).
By continuously adjusting our search range, we eventually find the smallest possible maximum stolen amount that still allows robbing at least k houses.

Algorithm
Initialize Search Bounds:

Set left = 1 (minimum possible reward).
Set right = maximum value in nums.
Determine the total number of houses, numHouses = houseRewards.size().
Perform Binary Search on Maximum Allowed Reward:

While left < right:
Compute mid = (left + right) / 2, representing the maximum reward a robber can take from a house.
Initialize housesRobbed = 0 to count how many houses can be robbed under this constraint.
Simulate Robbery Under the Current Constraint (mid):

Iterate through the houseRewards array:
If houseRewards[i] <= mid:
Rob the house and increment housesRobbed.
Skip the next house (i++) since consecutive houses cannot be robbed.
Adjust Search Range:

If housesRobbed >= housesToRob, reduce the reward constraint (right = mid).
Otherwise, increase it (left = mid + 1).
Return the Minimum Maximum Reward:

Once left == right, return left, which represents the smallest possible maximum reward that still allows robbing at least housesToRob houses.
     */
    class Solution {

    public int minCapability(int[] nums, int k) {
        // Store the maximum nums value in maxReward.
        int minReward = 1;
        int maxReward = Arrays.stream(nums).max().getAsInt();
        int totalHouses = nums.length;

        // Use binary search to find the minimum reward possible.
        while (minReward < maxReward) {
            int midReward = (minReward + maxReward) / 2;
            int possibleThefts = 0;

            for (int index = 0; index < totalHouses; ++index) {
                if (nums[index] <= midReward) {
                    possibleThefts += 1;
                    index++; // Skip the next house to maintain the
                    // non-adjacent condition
                }
            }

            if (possibleThefts >= k) maxReward = midReward;
            else minReward = midReward + 1;
        }

        return minReward;
    }
}
}
