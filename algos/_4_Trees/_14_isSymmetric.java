package _4_Trees;
//iterative idea: https://www.youtube.com/watch?v=nKggNAiEpBE&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=97&ab_channel=takeUforward
import java.util.*;
/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

 

Example 1:


Input: root = [1,2,2,3,4,4,3]
Output: true


Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
 */
public class _14_isSymmetric {
    public static void main(String[] args) {
        /*
                      1
                    /   \
                    2     2
                    / \   / \
                    3   4 4   3

         */
        TreeNode root = TreeNode.constructTree(new Integer[] { 1, 2, 2, 3, 4, 4, 3 });
        System.out.println(recursive(root,root));
        System.out.println(iterative_stack(root));
        System.out.println(iterative_queue(root));

       


    }

    // For two trees to be mirror images, the following
    // three conditions must be true
    // 1.) Their root node's key must be same
    // 2.) left subtree of left tree and right subtree
    // of right tree have to be mirror images
    // 3.) right subtree of left tree and left subtree
    // of right tree have to be mirror images

    public static boolean recursive(TreeNode leftSide, TreeNode rightSide) {//O(n)----O(n)
        // base case: every individual leaf is symmetric
        if (leftSide == null && rightSide == null) {
            return true;
        } else if (leftSide == null || rightSide == null) {// not a leaf and any one of child is null
            return false;
        }
        if (leftSide.val == rightSide.val) {// same values on both side ie that part is symmetric, check further
            return (recursive(leftSide.left, rightSide.right) && recursive(leftSide.right, rightSide.left));
        }
        return false;
    }

    //using stack: uper ke elemnts ko yaad rakh kar( ie last me unko  check karenge), first neeche ke elemnts ko check karo(last in first out)
    public static boolean iterative_stack(TreeNode root){
        /*
        Algorithm for checking whether a binary tree is a mirror of itself using an iterative approach and a stack:

Create a stack and push the root node onto it twice.
While the stack is not empty, repeat the following steps:
   a. Pop two nodes from the stack, say node1 and node2.
   b. If both node1 and node2 are null, continue to the next iteration.
   c. If one of the nodes is null and the other is not, return false as it is not a mirror.
   d. If both nodes are not null, compare their values. If they are not equal, return false.
   e. Push the left child of node1 and the right child of node2 onto the stack.
   f. Push the right child of node1 and the left child of node2 onto the stack.
If the loop completes successfully without returning false, return true as it is a mirror.
         */
        if (root==null) {//every numm is symm
            return true;            
        }

        Stack<TreeNode>st=new Stack<>();

        st.push(root);
        st.push(root);

        while (!st.isEmpty()) {
            TreeNode n1=st.pop();//n1 stands for left node
            TreeNode n2=st.pop();//n2 for right node

            if (n1==null&&n2==null) {//leaf, i we came till most bottom, ab upar jao
                continue;
            }
            if (n1==null||n2==null) {
                return false;
            }

            if (n1.val!=n2.val) {
                return false;
            }

            st.push(n1.left);st.push(n2.right);
            st.push(n1.right);st.push(n2.left);

            
        }

        return true;
    }


    public static boolean iterative_queue(TreeNode root){//just reverse th order in which u r pushing elemnts as in statck
        if (root==null) {
            return true;
        }

        Queue<TreeNode>q=new LinkedList<>();

        q.offer(root);
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode n1=q.poll();//n1 stands for left node
            TreeNode n2=q.poll();//n2 stands for right node

            if (n1==null&&n2==null) {
                continue;
            }

            if (n1==null||n2==null) {
                return false;
            }

            if (n1.val!=n2.val) {
                return false;
            }

            q.offer(n1.right);q.offer(n2.left);
            q.offer(n1.left);q.offer(n2.right);

            //----OR------
            /*
            q.offer(n1.left);q.offer(n2.right);
            q.offer(n1.right);q.offer(n2.left);
             */

        }

        return true;
    }


    
}
