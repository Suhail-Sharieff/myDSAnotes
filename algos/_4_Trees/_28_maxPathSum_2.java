package _4_Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
public class _28_maxPathSum_2 {

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
            func(root.left, currSum, ans, new ArrayList<>(empty), targetSum);//MISTAKE: just passed as empty rather than new ArrayList<>(empty)
        if (root.right != null)
            func(root.right, currSum, ans, new ArrayList<>(empty), targetSum);
    }


    //----------------------follow up:
/*437. Path Sum III
Solved
Medium
Topics
premium lock icon
Companies
Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

 

Example 1:


Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
 

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000 */

    ///------------brute force
    static int brute(TreeNode root,int target){
        if(root==null) return 0;
        return rec(root, new StringBuilder(),target)+ brute(root.left, target)+brute(root.right, target);
    }
    static int rec(TreeNode root,StringBuilder sb,int target){
        if(root==null) return 0;
        int ans=0;
        sb.append(root.val+"->");
        int len=sb.length();
        if(target==0){System.out.println(sb);ans++;}
        ans+=rec(root.left, new StringBuilder(sb),target-root.val)+rec(root.right, new StringBuilder(sb),target-root.val);
        sb.setLength(len);
        return ans;
    }

    //----------optimal:

    /*So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) , and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways. */
    private HashMap<Long,Integer>hs;
    public int optimal(TreeNode root, int targetSum) {
        hs=new HashMap<>();
        hs.put(0l,1);
        return rec(root,0l,(long)targetSum);
    }
    private int rec(TreeNode root,long currSum,long target){
        if(root==null) return 0;
        currSum+=root.val;
        int ans = hs.getOrDefault(currSum - target, 0);
        hs.put(currSum,hs.getOrDefault(currSum,0)+1);
        ans+=rec(root.left,currSum,target)+rec(root.right,currSum,target);
        hs.put(currSum,hs.get(currSum)-1);
        return ans;
    }
}
