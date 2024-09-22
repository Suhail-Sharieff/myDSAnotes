package _4_Trees;


/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

 

Example 1:


Input: root = [1,2,2,3,4,4,3]
Output: true


Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
 */
public class _14_isSymmetric {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(new Integer[] { 1, 2, 2, null, 3, null, 3 });
        System.out.println(recursive(root,root));

       


    }

    // For two trees to be mirror images, the following
    // three conditions must be true
    // 1.) Their root node's key must be same
    // 2.) left subtree of left tree and right subtree
    // of right tree have to be mirror images
    // 3.) right subtree of left tree and left subtree
    // of right tree have to be mirror images

    public static boolean recursive(TreeNode leftSide, TreeNode rightSide) {
        // base case: every individual leaf is symmetric
        if (leftSide == null && rightSide == null) {
            return true;
        } else if (leftSide == null || rightSide == null) {// any one of them is null
            return false;
        }
        if (leftSide.val == rightSide.val) {// same values on both side ie that part is symmetric, check further
            return (recursive(leftSide.left, rightSide.right) && recursive(leftSide.right, rightSide.left));
        }
        return false;
    }



    
}
