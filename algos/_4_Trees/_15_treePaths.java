package _4_Trees;

import java.util.*;
/*
 * 
Given a Binary Tree of nodes, you need to find all the possible paths from the root node to all the leaf nodes of the binary tree.

Example 1:

Input:
       1
    /     \
   2       3
Output: 
["1->2", 
"1->3"]
Explanation: 
All possible paths:
[1->2,
1->3]
Example 2:

Input:
         10
       /    \
      20    30
     /  \
    40   60
Output: 
["10->20->40",
"10->20->60",
"10->30 "]
Your Task:
Your task is to complete the function Paths() which takes the root node as an argument and returns all the possible paths. (All the paths are printed in new lines by the driver's code.)

Expected Time Complexity: O(n)
Expected Auxiliary Space: O(height of the tree)

Constraints:
1<=n<=104
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
        sb.append(root.val);//for list of integer,empty.add(root.val)
        if (root.left==null&&root.right==null) {
            ans.add(new String(sb));
            // return;    / imP NOTE: DONT ADD return HERE IN ANY CASE oR ELSE IT WOULD BACK TRACK
        }

        sb.append(" -> ");
        optimal(root.left, ans, sb);
        optimal(root.right, ans, sb);
        sb.setLength(len);//for list of integer, empty.remove(empty.size()-1)


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
