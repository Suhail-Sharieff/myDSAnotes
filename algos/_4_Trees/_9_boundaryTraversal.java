package _4_Trees;

import java.util.ArrayList;
//MORRIS TRAVERSAL 
import java.util.Collections;


/*
Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 

Left boundary TreeNodes: defined as the path from the root to the left-most TreeNode ie- the leaf TreeNode you could reach when you always travel preferring the left subtree over the right subtree. 
Leaf TreeNodes: All the leaf TreeNodes except for the ones that are part of left or right boundary.
Reverse right boundary TreeNodes: defined as the path from the right-most TreeNode to the root. The right-most TreeNode is the leaf TreeNode you could reach when you always travel preferring the right subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary TreeNodes.
Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary. 

Example 1:

Input:
        1 
      /   \
     2     3  
    / \   / \ 
   4   5 6   7
      / \
     8   9
   
Output: 1 2 4 8 9 6 7 3
Explanation:


 

Example 2:

Input:
            1
           /
          2
        /  \
       4    9
     /  \    \
    6    5    3
             /  \
            7     8

Output: 1 2 4 6 5 7 8
Explanation:















As you can see we have not taken the right subtree. 
Your Task:
This is a function problem. You don't have to take input. Just complete the function boundary() that takes the root TreeNode as input and returns an array containing the boundary values in anti-clockwise.

Expected Time Complexity: O(N). 
Expected Auxiliary Space: O(Height of the Tree).

Constraints:
1 ≤ Number of TreeNodes ≤ 105
1 ≤ val of a TreeNode ≤ 105





Test case for checking:


          1
       2     3      expected: 1,2,5,4,3 main obs: observe the literal moving left and right wount give good ans
           4
         5 















 */
/*
Algorithm:

Right Boundary – Go Right Right until no Right. Dont Include Leaf TreeNodes. (as it leads to duplication) ie check first if left!=null,add and move left, check if right is not null,add and mode right, dont handle nodes(both left&right are null as we handled in leaf)
Left Boundary – Go Left Left until no Left. Dont Include Leaf TreeNodes. (as it leads to duplication)
Leaf Boundary – Do inorder/preorder, if leaf TreeNode add to the List.
We pass the array as reference, so its the same memory location used by all functions, to coordinate the result at one place.
 */
public class _9_boundaryTraversal {

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(20);
        // root.left = new TreeNode(8);
        // root.left.left = new TreeNode(4);
        // root.left.right = new TreeNode(12);
        // root.left.right.left = new TreeNode(10);
        // root.left.right.right = new TreeNode(14);
        // root.right = new TreeNode(22);
        // root.right.right = new TreeNode(25);

        TreeNode root = TreeNode.constructTree(new Integer[] { 20, 8, 22, 4, 12, null, 25, null, null, 10, 14 });

        System.out.println(new _9_boundaryTraversal().AnticlockwiseBoundaryTraversal(root));

    }

    ArrayList<Integer> left;
    ArrayList<Integer> leaf;
    ArrayList<Integer> right;
    ArrayList<Integer>ans;
    ArrayList<Integer> AnticlockwiseBoundaryTraversal(TreeNode root) {
        left=new ArrayList<>();left_trav(root.left);
        leaf=new ArrayList<>();leaf_trav(root);
        right=new ArrayList<>();right_trav(root.right);
        ans=new ArrayList<>();ans.add(root.val);
        if(root.left==null && root.right==null) return ans;//what if case is: [2], to avoid it being added into ans as well as the leaf list 
        ans.addAll(left);
        ans.addAll(leaf);
        Collections.reverse(right);
        ans.addAll(right);
        return ans;
    }
    
    void left_trav(TreeNode root){
        if(root==null) return;
        if(root.left==null && root.right==null) return;
        left.add(root.val);
        if(root.left!=null) left_trav(root.left);
        else if(root.right!=null) left_trav(root.right);
    }
    void leaf_trav(TreeNode root){
        if(root==null) return;
        if(root.left==null && root.right==null) leaf.add(root.val);
        leaf_trav(root.left);
        leaf_trav(root.right);
    }
    void right_trav(TreeNode root){
        if(root==null) return;
        if(root.left==null && root.right==null) return;
        right.add(root.val);
        if(root.right!=null) right_trav(root.right);
        else if(root.left!=null) right_trav(root.left);
    }
}
