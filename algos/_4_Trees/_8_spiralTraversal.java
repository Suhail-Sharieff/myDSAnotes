package _4_Trees;

/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _8_spiralTraversal {
    public static List<List<Integer>> brute(TreeNode root) {// some modification of level order traversal...just
                                                            // maintain a boolean flag to maintain when to rreverse and
                                                            // when not to reverse
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        boolean leftToRight = true;//here true
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> each = new ArrayList<>();
            for (int i = 0; i < size; i++) {

                TreeNode curr = q.poll();

                each.add(i, curr.val);

                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }

            }
            // IMP PART
            if (leftToRight) {
                ans.add(each);
                leftToRight = false;
            } else {
                Collections.reverse(each);
                ans.add(each);
                leftToRight = true;
            }
        }

        return ans;
    }

    public static List<List<Integer>> optimal(TreeNode root) {// avoids Collections.reverse()
        if (root==null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        boolean leftToRight = false;//here false
        while (!q.isEmpty()) {
            int size = q.size();
            Deque<Integer>dq=new LinkedList<>();//deque can perfr insertion at both end without exception where List will throw exception
            

            for (int i = 0; i < size; i++) {
                
                TreeNode curr=q.poll();

                if (leftToRight) {
                    //MISTAKE: dont update boolean here itself
                    dq.addFirst(curr.val);
                }else{
                    dq.addLast(curr.val);
                }
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if (curr.right!=null) {
                    q.offer(curr.right);
                }
            }
            //update boolean here
            leftToRight=!leftToRight;
            ans.add(new ArrayList<>(dq));
        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        System.out.println(brute(root));

    }
}
