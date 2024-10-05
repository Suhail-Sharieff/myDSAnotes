package _4_Trees;

import java.util.HashMap;
import java.util.Map;
//https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=106&ab_channel=takeUforward

/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class _22_BT_construction {
    public static void main(String[] args) {//O(n)
        int preOrder[]={3,9,20,15,7};
        int inOrder[]={9,3,15,20,7};

        Map<Integer,Integer>inorder_node_idx_map=new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inorder_node_idx_map.put(inOrder[i], i);
        }

        TreeNode root=buildTree(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1, inorder_node_idx_map);

        TreeNode.displayLevelByLevel(root);

    }

    public static TreeNode buildTree(int preOrder[],int preStart,int preEnd,int inOrder[],int inStart,int intEnd,Map<Integer,Integer>inorder_node_idx_map){
        if (preStart>preEnd || inStart>intEnd) {
            return null;
        }
        TreeNode root=new TreeNode(preOrder[preStart]);

        int idxOfCurrRoot=inorder_node_idx_map.get(root.val);
        int noOfNumOnLeftOfCurrRoot=idxOfCurrRoot-inStart;

        root.left=buildTree(preOrder, preStart+1, preStart+noOfNumOnLeftOfCurrRoot, inOrder, inStart, idxOfCurrRoot-1, inorder_node_idx_map);

        root.right=buildTree(preOrder, preStart+noOfNumOnLeftOfCurrRoot+1, preEnd, inOrder, idxOfCurrRoot+1, intEnd, inorder_node_idx_map);

        return root;
    }
}
