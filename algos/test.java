import java.util.HashMap;
import java.util.PriorityQueue;

import _5_BST.TreeNode;

public class test {

    public static void main(String[] args) {
       TreeNode root=TreeNode.constructTree(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,13});
      inor(root);

    }

    static void inor(TreeNode root){
        if(root==null) return;
        inor(root.right);
        System.out.print(root.val+"->");
        inor(root.left);
    }
}