package _4_Trees;

import java.util.*;
/*
 * 
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

 

Example 1:


Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
Example 2:

Input: root = [1]
Output: ["1"]
 

Constraints:

The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
 */
public class _15_treePaths {
    public static void main(String[] args) {
        List<String>ans=new ArrayList<>();
        TreeNode root=TreeNode.constructTree(new Integer[]{1,2,3,null,5});
        optimal(root, ans, new StringBuilder());
        System.out.println(ans);

    }


    public static void optimal(TreeNode root,List<String>ans,StringBuilder sb){
        if (root==null) {
            return;
        }
        //logic is to move till leaf by adding root.vals..then backtrack back by checking if any other paths
        int len=sb.length();
        sb.append(root.val);
        if (root.left==null&&root.right==null) {
            ans.add(new String(sb));
            return;
        }

        sb.append(" -> ");
        optimal(root.left, ans, sb);
        optimal(root.right, ans, sb);
        sb.setLength(len);


        //MISTAKE CODE://didnt reset the string builder
        /*
         public void func(TreeNode root,StringBuilder sb,List<String>ans){
        if(root==null){
            return;
        }
        sb.append(root.val);
        if(root.left==null&&root.right==null){
            ans.add(new String(sb.toString()));
        }
            func(root.left,sb.append("->"),ans);
            func(root.right,sb.append("->"),ans);
        }
         */
    }
}
