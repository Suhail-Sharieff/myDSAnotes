package _4_Trees;
import java.util.*;

/*
 * 
Given a binary tree and a node data called target. Find the minimum time required to burn the complete binary tree if the target is set on fire. It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.
Note: The tree contains unique values.


Examples : 

Input:      
          1
        /   \
      2      3
    /  \      \
   4    5      6
       / \      \
      7   8      9
                   \
                   10
Target Node = 8
Output: 7
Explanation: If leaf with the value 8 is set on fire. 
After 1 sec: 5 is set on fire.
After 2 sec: 2, 7 are set to fire.
After 3 sec: 4, 1 are set to fire.
After 4 sec: 3 is set to fire.
After 5 sec: 6 is set to fire.
After 6 sec: 9 is set to fire.
After 7 sec: 10 is set to fire.
It takes 7s to burn the complete tree.
Input:      
          1
        /   \
      2      3
    /  \      \
   4    5      7
  /    / 
 8    10
Target Node = 10
Output: 5

Expected Time Complexity: O(number of nodes)
Expected Auxiliary Space: O(height of tree)


Constraints:
1 ≤ number of nodes ≤ 105

1 ≤ values of nodes ≤ 105
 */

public class _20_timeTakenToBurnTree {
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{ 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        func(root, root.left, 2);
    }
    public static void func(TreeNode root,TreeNode target,int k){
        Map<TreeNode,TreeNode>cpm=new HashMap<>();
        Queue<TreeNode>temp=new LinkedList<>();
        temp.offer(root);
        while (!temp.isEmpty()) {
            int size=temp.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr=temp.poll();
                if (curr.left!=null) {
                    cpm.put(curr.left, curr);
                    temp.offer(curr.left);
                }
                if (curr.right!=null) {
                    cpm.put(curr.right, curr);
                    temp.offer(curr.right);
                }
            }
        }

        int trD=0;
        Queue<TreeNode>q=new LinkedList<>();
        Map<TreeNode,Boolean>visisted=new HashMap<>();

        q.offer(target);
        visisted.put(target, true);

        while (!q.isEmpty()) {
            if (trD==k) {
                break;
            }
            int size=q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr=q.poll();

                if (curr.left!=null && !visisted.containsKey(curr.left)) {
                    q.offer(curr.left);
                    visisted.put(curr.left, true);
                }
                if (curr.right!=null && !visisted.containsKey(curr.right)) {
                    q.offer(curr.right);
                    visisted.put(curr.right, true);
                }
                TreeNode parent=cpm.get(curr);
                if (parent!=null && !visisted.containsKey(parent)) {
                    q.offer(parent);
                    visisted.put(parent, true);
                }
            }
            trD++;
        }

        List<Integer>ans=new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }
        System.out.println(ans);

    }
}
