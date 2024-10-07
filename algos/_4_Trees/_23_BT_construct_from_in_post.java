package _4_Trees;
/*
106. Construct Binary Tree from Inorder and Postorder Traversal
Solved
Medium
Topics
Companies
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]
 

Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */
public class _23_BT_construct_from_in_post {
    //post order-->left,right,root...observe that root is present at last in postorder array,just lil modification of prev algo.....we decrement the pointer and also build right first and then left
    public static void main(String[] args) {
        int inOrder[]={9,3,15,20,7};//left.root.right
        int postOrder[]={3,9,20,15,7};//root,left,right
        

      

        TreeNode.displayLevelByLevel(better(inOrder, postOrder,0,inOrder.length-1,new int[]{inOrder.length-1}));
    }
    public  static TreeNode better(int inOrder[],int postOrder[],int inStart,int inEnd,int postIdx[]){
        if (postIdx[0]<0||inStart>inEnd) {//dont put inStart>=inEnd,ie '=' symbol
            return null;
        }
        int elemnt=postOrder[postIdx[0]--];
        TreeNode currRoot=new TreeNode(elemnt);
        int posOfCurrRootInInorder=findPosOf_e_in_inorder(inOrder, elemnt);

        //carefully observe that sinvce we r building tree by moving from right of post order to left we nned to build right and then left
        currRoot.right = better(inOrder, postOrder, posOfCurrRootInInorder+1, inEnd, postIdx);
        currRoot.left = better(inOrder, postOrder, inStart, posOfCurrRootInInorder-1, postIdx);

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


    //for optimal, just use map in place of findPos....function and map inorder elemnts to thier index and directly retreive it
}
