package _4_Trees;

import java.util.HashMap;
import java.util.Map;
//https://www.youtube.com/watch?v=ffE1xj51EBQ&ab_channel=CodeHelp-byBabbar

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

    public static void main(String[] args) {
        int inOrder[]={9,3,15,20,7};//left.root.right
        int preOrder[]={3,9,20,15,7};//root,left,right
        int preIdx[]={0};//coz directly if u pass preIdx as 0, it wouldnt give crt ans, so pass refrence


        TreeNode.displayLevelByLevel(better(inOrder,preOrder,0,inOrder.length-1,preIdx));

        TreeNode.displayLevelByLevel(optimal(inOrder, preOrder));

    }
   

    public  static TreeNode better(int inOrder[],int preOrder[],int inStart,int inEnd,int preIdx[]){
        if (preIdx[0]>=preOrder.length||inStart>inEnd) {//dont put inStart>=inEnd,ie '=' symbol
            return null;
        }
        int elemnt=preOrder[preIdx[0]++];
        TreeNode currRoot=new TreeNode(elemnt);
        int posOfCurrRootInInorder=findPosOf_e_in_inorder(inOrder, elemnt);

        currRoot.left = better(inOrder, preOrder, inStart, posOfCurrRootInInorder-1, preIdx);
        currRoot.right = better(inOrder, preOrder, posOfCurrRootInInorder+1, inEnd, preIdx);

        return currRoot;
    }
    public static  int findPosOf_e_in_inorder(int inorder[],int e){
        for (int i = 0; i < inorder.length; i++) {
            if (e==inorder[i]) {
               return i;
            }
        }
        return -1;
    }






    //we would optimize in the way that instad of finding pos of root elemnt in the inorder after findding the root frompreorder,we would just use Map so that we would map evry inOrerelemnt with its idx
    public static TreeNode optimal(int inOrder[],int preOrder[]) {//O(n)
       

        Map<Integer,Integer>inorder_node_idx_map=new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inorder_node_idx_map.put(inOrder[i], i);
        }

        TreeNode root=buildTree(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1, inorder_node_idx_map);

        return(root);

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
