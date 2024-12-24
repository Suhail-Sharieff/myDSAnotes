import java.util.HashMap;
import java.util.PriorityQueue;

import _5_BST.TreeNode;

public class test {

    public static void main(String[] args) {
       TreeNode root=TreeNode.constructTree(new Integer[]{2,1,3});
        // isValidBST(root,new TreeNode[]{null});
        TreeNode prev=null;
        while (root!=null) {
            prev=root;
           
        }

    }

    public static void isValidBST(TreeNode root,TreeNode prev[]) {
        if(root==null) return;
       prev[0]=root;
       isValidBST(root.left,prev);
       System.out.println("prev: "+prev[0]+" curr: "+root);
       isValidBST(root.right,prev);
    //    if(prev[0].val>=root.val) return false;
    //    return l&&r;
    }
}