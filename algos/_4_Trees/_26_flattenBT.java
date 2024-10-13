package _4_Trees;
import java.util.*;
//IMP:https://www.youtube.com/watch?v=sWf7k1x9XR4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=110&ab_channel=takeUforward

public class _26_flattenBT {
    public TreeNode prev=null;
    public void brute(TreeNode root) {//move like right,left,root ie (make LL of right,then left part,then attach left part to root whose end is attached to right part)---O(n)---O(n)
        if(root==null){
            return;
        }
        brute(root.right);
        brute(root.left);
        root.right=prev;
        root.left=null;
        prev=root;
    }


    public void brute2(TreeNode root){//jus extension of brute
        if (root==null) {
            return;
        }
        Stack<TreeNode>st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode curr=st.peek();
            st.pop();
            if (curr.right!=null) {
                st.push(curr.right);
            }
            if (curr.left!=null) {
                st.push(curr.left);
            }
            if (!st.isEmpty()) {
                curr.right=st.peek();
            }
            curr.left=null;
        }
    }







    public static void optimal(TreeNode root) {
       if(root==null){
        return;
       }

       TreeNode curr=root;
       while (curr!=null) {
        if (curr.left!=null) {
            TreeNode prev=curr.left;
            while (prev.right!=null) {
                prev=prev.right;
            }
            prev.right=curr.right;
            curr.right=curr.left;
            curr.left=null;
        }
        curr=curr.right;
       }


    }
}
