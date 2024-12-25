
import java.util.*;

import _5_BST.TreeNode;

public class test {

    public static void main(String[] args) {
       TreeNode root=TreeNode.constructTree(new Integer[]{5,1,4,null,null,3,6});
        // isValidBST(root,Integer.MIN_VALUE,-1);
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
                TreeNode fornt=q.poll();
                System.out.println(fornt);
                if (fornt.right!=null) {
                    q.offer(fornt.right);
                }
                if (fornt.left!=null) {
                    q.offer(fornt.left);
                }
        }


    }

    public static void isValidBST(TreeNode root,int prev,int curr) {
     if(root==null) return;
       if (prev!=root.val) {
        prev=root.val;
        System.out.print(" prev: "+prev);
       }
       isValidBST(root.left,prev,curr);
       if (prev!=curr) {
        curr=root.val;
        System.out.println(" curr: "+curr);
       }
       isValidBST(root.right,prev,curr);

    }
}