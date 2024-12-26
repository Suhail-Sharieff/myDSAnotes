package _5_BST;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left 
subtree
 of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


NOTE :
      5
     / \
    4   6
       / \
      3   7
      
    This is NOT a BST coz in right subtree of 5 we have 3 

 */


 /*
 WRONG CODE: coz the below code checks only if root.val>left.val && root.val<right.val, but we need that entire subtree on left is smaller tha root and entitre right tree is greater than root
 public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        int leftVal=(root.left==null)?Integer.MIN_VALUE:root.left.val;
        int rightVal=(root.right==null)?Integer.MAX_VALUE:root.right.val;
        if(root.val>leftVal && root.val<rightVal){
            return (isValidBST(root.left)&&isValidBST(root.right));
        }
        return false;
    }
  */

public class _6_validateBST {
    //-------------brute 1: do inorder tarversal and store elemnts in list, if list is sorted , its valid bst
    public boolean brute1(TreeNode root) {
        if(root==null) return true;
        List<Integer>ans=new ArrayList<>();
        in(root,ans);
        return isSorted(ans);
    }
    public boolean isSorted(List<Integer>li){
        for(int i=1;i<li.size();i++){
            if(li.get(i)<=li.get(i-1)){//see that "=" is IMP
                return false;
            }
        }
        return true;
    }
    public void in(TreeNode root,List<Integer>ans){
        if(root==null) return ;
        in(root.left,ans);
        ans.add(root.val);
        in(root.right,ans);
    }


    //-------------brute 2: traverse through every node and check for rach node if its value is greater than all nodes on left and smaller than all nodes on right
    public boolean brute2(TreeNode root) {
        if(root==null) return true;
        boolean leftIsSmall=(root.left==null)?true:lSm(root.left,root.val);
        boolean rightIsGreat=(root.right==null)?true:rG(root.right,root.val);
        if(leftIsSmall && rightIsGreat){
            return (brute2(root.left)&&brute2(root.right));
        }
        return false;
    }

    public boolean lSm(TreeNode lChild,int val){//tells if left is smaller
        if(lChild==null) return true;
        if(lChild.val>=val) return false;//"="
        return (lSm(lChild.left,val)&&lSm(lChild.right,val));
    }
    public boolean rG(TreeNode rChild,int val){//tells if right is smaller
        if(rChild==null) return true;
        if(rChild.val<=val) return false;
        return (rG(rChild.left,val)&&rG(rChild.right,val));
    }


    //optimal:O(n)--O(n): initialize max as Long.MAX, min as Long.MIN,
    // now when we move left of root, every value should lie in range [min,root.val) abd when moved right it shuld lie in (root.val,max], if it doent return false
    public static boolean optimal(TreeNode root,long min,long max){
        if(root==null) return true;
        if(root.val<=min || root.val>=max) return false;
        boolean isLeftBal=optimal(root.left,min,root.val);
        boolean isRightBal=optimal(root.right,root.val,max);
        return (isLeftBal && isRightBal);
    }

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructBST(new Integer[]{5,1,4,null,null,3,6});
        System.out.println(optimal(root, Long.MIN_VALUE, Long.MAX_VALUE));
    }
}
