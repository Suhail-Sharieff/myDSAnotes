package _4_Trees;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
public class _28_maxPathSum {

    //THE thing that makes this problem special is that we must reach from root to compulsorily to leaf and then check if sum is equal to target sum or not


     public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        func(root, 0, ans, new ArrayList<>(), targetSum);
        return new ArrayList<>(ans);
    }

    public static void func(TreeNode root, int currSum, List<List<Integer>> ans, List<Integer> empty, int targetSum) {
        if (root == null) {
            if (currSum == targetSum)
                ans.add(empty);
            return;
        }
        empty.add(root.val);
        currSum += root.val;
        if (root.left == null && root.right == null)
            if (currSum == targetSum)
                ans.add(empty);
        if (root.left != null)
            func(root.left, currSum, ans, new ArrayList<>(empty), targetSum);
        if (root.right != null)
            func(root.right, currSum, ans, new ArrayList<>(empty), targetSum);
    }
}
