package _4_Trees;
/*
Given a binary tree, determine if it is 
height-balanced(A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.)

 
****NOTE THE WORD SUBTREE***

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:


Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:

Input: root = []
Output: true
 */
public class _4_checkIfBalanced {
    //using maxD algo previosuly coded

    //myApproach:
    /*
     *every single node alone is balanced, so if root==null, return true;
     *if maxD on left and right (starting from root) is <=1, then recursively check for its subtreee 
     if no, return false;
    */
    public boolean brute(TreeNode root){//O(N)
        if (root==null) {//every single node is balanced alone
            return true;
        }
        if (Math.abs(maxDepth(root.left)-maxDepth(root.right))<=1) {//check if root is balnced first,then only move to iits subtees
            return (brute(root.left)&&brute(root.right));
        }
        //if root ony not balanced(same applies for recursive subrtrees)
        return false;
    }



    public int maxDepth(TreeNode root){
        if (root==null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }



}
