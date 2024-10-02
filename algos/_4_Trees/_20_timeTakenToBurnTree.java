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

public class _20_timeTakenToBurnTree {//soln is just modification of _19_nodesAtDistanceK, we just need to remove break condition of (if(travelledDistance==k)) thats it
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{ 1,2,3,4,5,null,6,null,null,7,8,null,9,null,null,null,null,10});
        System.out.println(optimal(root, root.left.right.right));
        //Supoose target is NOT given as reference, but as value, use function getTarget_s_reference to get the node having that target value
    }
    public static int optimal(TreeNode root,TreeNode target){
        //mark parents for each node 
        Map<TreeNode,TreeNode>cpm=new HashMap<>();//childParentMap
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

        //now u know all nodes's parents
        int trD=0;//travelledDistance
        Queue<TreeNode>q=new LinkedList<>();
        Map<TreeNode,Boolean>visisted=new HashMap<>();

        q.offer(target);
        visisted.put(target, true);

        while (!q.isEmpty()) {
            
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
            //use the below code to understand how its touching nodes
            // for (TreeNode treeNode : q) {
            //     System.out.print(treeNode.val+" ");
            // }
            // System.out.println();
            trD++;
        }

        return(trD-1);//1 step it travels extra for null value, so -1

    }


    public static TreeNode getTarget_s_reference(TreeNode root, int target) {//function whcih returns node with value target
        if (root == null) {
            return null;
        }
        if (root.val == target) {
            return root;
        }
        TreeNode leftResult = getTarget_s_reference(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }
        return getTarget_s_reference(root.right, target);
    }
}
