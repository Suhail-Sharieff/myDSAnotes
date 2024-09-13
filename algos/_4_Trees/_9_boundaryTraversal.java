package _4_Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 

Left boundary nodes: defined as the path from the root to the left-most node ie- the leaf node you could reach when you always travel preferring the left subtree over the right subtree. 
Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
Reverse right boundary nodes: defined as the path from the right-most node to the root. The right-most node is the leaf node you could reach when you always travel preferring the right subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary nodes.
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
This is a function problem. You don't have to take input. Just complete the function boundary() that takes the root node as input and returns an array containing the boundary values in anti-clockwise.

Expected Time Complexity: O(N). 
Expected Auxiliary Space: O(Height of the Tree).

Constraints:
1 ≤ Number of nodes ≤ 105
1 ≤ Data of a node ≤ 105






















 */
/*
Algorithm:

Right Boundary – Go Right Right until no Right. Dont Include Leaf nodes. (as it leads to duplication)
Left Boundary – Go Left Left until no Left. Dont Include Leaf nodes. (as it leads to duplication)
Leaf Boundary – Do inorder/preorder, if leaf node add to the List.
We pass the array as reference, so its the same memory location used by all functions, to coordinate the result at one place.
 */
public class _9_boundaryTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        root.right = new TreeNode(22);
        root.right.right = new TreeNode(25);

        System.out.println(AnticlockwiseBoundaryTraversal(root));
    }

    private static List<Integer> leftList = new ArrayList<>();
    private static List<Integer> leafList = new ArrayList<>();
    private static List<Integer> rightList = new ArrayList<>();

    private static void moveViaLeft(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.left != null) {
            leftList.add(root.val);
            moveViaLeft(root.left);
        } else {
            leftList.add(root.val);
            moveViaLeft(root.right);
        }
    }

    private static void moveViaLeaf(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafList.add(root.val);
            return;
        }
        moveViaLeaf(root.left);
        moveViaLeaf(root.right);
    }

    private static void moveViaRight(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right != null) {
            moveViaRight(root.right);
        } else {
            moveViaRight(root.left);
        }
        rightList.add(root.val);
    }

    public static List<Integer> AnticlockwiseBoundaryTraversal(TreeNode root) {
        leftList.clear();
        leafList.clear();
        rightList.clear();

        if (root == null) {
            return new ArrayList<>();
        }

        // Process the left boundary (excluding the root itself)
        if (root.left != null) {
            moveViaLeft(root.left);
        }

        // Process leaf nodes
        moveViaLeaf(root);

        // Process the right boundary (excluding the root itself)
        if (root.right != null) {
            moveViaRight(root.right);
        }

        // Construct the result list
        List<Integer> result = new ArrayList<>();
        result.add(root.val); // Add the root node
        result.addAll(leftList); // Add the left boundary nodes
        result.addAll(leafList); // Add leaf nodes
        Collections.reverse(rightList); // Reverse the right boundary list
        result.addAll(rightList); // Add the reversed right boundary nodes

        return result;
    }
}
