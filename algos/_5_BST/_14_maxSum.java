package _5_BST;
/*
Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:



Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
Example 2:



Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
Example 3:

Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST.
 

Constraints:

The number of nodes in the tree is in the range [1, 4 * 104].
-4 * 104 <= Node.val <= 4 * 104
 */
public class _14_maxSum {

    public static void main(String[] args) {
    //     TreeNode root=TreeNode.constructTree(new Integer[]{1,4,3,2,4,2,5,null,null,null,null,null,null,4,6});
    //    int ans[]={Integer.MIN_VALUE};
    //    System.out.println(ans[0]);
    }


    //brute:O(n^2)
    public static void brute(TreeNode root,int ans[]){
        if(root==null) return;
        if(isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE)){
            int sum=sum(root);
            ans[0]=Math.max(ans[0], sum);
        }
        brute(root.left, ans);
        brute(root.right, ans);
    }

    public static int sum(TreeNode root){
        if(root==null) return 0;
        return (root.val+sum(root.left)+sum(root.right));
    }

    public static  boolean isValid(TreeNode root,long min,long max){
        if(root==null) return true;
        if(root.val<=min || root.val>=max) return false;
        return isValid(root.left,min,root.val)&&isValid(root.right,root.val,max);
    }


    //-------------optimal: uses same idea as that of maxSizeBST

    private int maxSum = 0;
    public int optimal(TreeNode root) {
        postOrderTraverse(root);
        return maxSum;
    }
    private int[] postOrderTraverse(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // {min, max, sum}, initialize min=MAX_VALUE, max=MIN_VALUE
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);
        // The BST is the tree:
        if (!(     left != null             // the left subtree must be BST
                && right != null            // the right subtree must be BST
                && root.val > left[1]       // the root's key must greater than maximum keys of the left subtree
                && root.val < right[0]))    // the root's key must lower than minimum keys of the right subtree
            return null;
        int sum = root.val + left[2] + right[2]; // now it's a BST make `root` as root
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[]{min, max, sum};
    }
}
