package _6_DynamicProgramming._01_1D;

import java.util.Arrays;


/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400

 */
public class _06_houseRobber {// same logic as that of _05_...
    public static void main(String[] args) {

        int nums[] = { 2, 7, 9, 3, 1 };

        // int dp[]=new int[nums.length];
        // Arrays.fill(dp,-1);
        // return rec(nums,nums.length-1,dp);

        System.out.println(tabulation(nums));

        System.out.println(spaceOptimized(nums));
    }

    static int recurse(int nums[], int idx, int dp[]) {
        if (idx < 0)
            return 0;
        dp[0] = nums[0];
        if (dp[idx] != -1)
            return dp[idx];
        int way1 = nums[idx] + recurse(nums, idx - 2, dp);
        int way2 = recurse(nums, idx - 1, dp);
        dp[idx] = Math.max(way1, way2);
        return dp[idx];
    }

    static int tabulation(int houses[]) {
        if (houses.length == 1)
            return houses[0];
        int dp[] = new int[houses.length];
        Arrays.fill(dp, -1);
        dp[0] = houses[0];
        for (int i = 1; i < houses.length; i++) {
            int way1 = (i > 1) ? houses[i] + dp[i - 2] : houses[i];
            int way2 = dp[i - 1];
            dp[i] = Math.max(way1, way2);
        }
        return dp[houses.length - 1];
    }

    static int spaceOptimized(int houses[]) {
        if (houses.length == 1)
            return houses[0];
        int prev2 = 0, prev1 = houses[0];
        // dp[i-2] means prev2
        // dp[i-1] means prev1
        for (int i = 1; i < houses.length; i++) {
            int way1 = houses[i] + prev2;
            int way2 = prev1;
            prev2 = prev1;
            prev1 = Math.max(way1, way2);
        }
        return prev1;
    }

    // ------------------follow up: if houses r located circular, ie the first and
    // last hoses r adjacent
    public int loot(int[] nums, int start, int end) {
        // call like this:------IMP
        /*
         * if(nums.length==1) return nums[0];
         * if(nums.length==2) return Math.max(nums[0],nums[1]);
         * int lootFirst=loot(nums,0,nums.length-2);//loot first dont loot last
         * int lootLast=loot(nums,1,nums.length-1);//loot last dont loot first
         * return Math.max(lootFirst,lootLast);
         */
        int prevRob = 0, maxRob = 0;

        for (int i = start; i <= end; i++) {
            int temp = Math.max(maxRob, prevRob + nums[i]);
            prevRob = maxRob;
            maxRob = temp;
        }

        return maxRob;
    }

    // -------------foloow up:
    /*
     * The thief has found himself a new place for his thievery again. There is only
     * one entrance to this area, called root.
     * 
     * Besides the root, each house has one and only one parent house. After a tour,
     * the smart thief realized that all houses in this place form a binary tree. It
     * will automatically contact the police if two directly-linked houses were
     * broken into on the same night.
     * 
     * Given the root of the binary tree, return the maximum amount of money the
     * thief can rob without alerting the police.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [3,2,3,null,3,null,1]
     * Output: 7
     * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     * Example 2:
     * 
     * 
     * Input: root = [3,4,5,1,3,null,1]
     * Output: 9
     * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [1, 104].
     * 0 <= Node.val <= 104
     */
    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        int rob_curr = root.val;
        if (root.left != null)
            rob_curr += rob(root.left.left) + rob(root.left.right);//mistale took as max inplace of '+'
        if (root.right != null)
            rob_curr += rob(root.right.left) + rob(root.right.right);//here also
        int dont_rob_curr = rob(root.left) + rob(root.right);
        return Math.max(rob_curr, dont_rob_curr);
    }
}
