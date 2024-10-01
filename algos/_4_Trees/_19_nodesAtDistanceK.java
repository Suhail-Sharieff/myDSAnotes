package _4_Trees;

import java.util.*;


/*
Medium
Topics
Companies
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

ex:
Input:      
          20
        /    \
      8       22 
    /   \
   4    12 
       /   \
      10    14
Target Node = 8
K = 2
Output:[10,14,22]
Explanation: The three nodes at distance 2
from node 8 are 10, 14, 22.
 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []
 

Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
 */
public class _19_nodesAtDistanceK {// slight modification of level order traversal


    //idea: the idea is using queue DS as in level order traversal, we can go to k distance eeasily from every node to ITS CHILDREN, but we couldnt go back ie towards its parent and check for k distanced node, so to ensure that we also move towards parent, we need to remember every node's parent, so we remember each node's parent ie we even know adress of parent, know along with left and right as in level order, we can move even upside using parent's address which we know

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 });

        System.out.println(better(root, root.left, 2));
        System.out.println(optimal(root, root.left, 2));

    }

    public static List<Integer> better(TreeNode root, TreeNode target, int k) {//---TLE
        if (root == null) {
            return new ArrayList<>();
        }

        Map<TreeNode, TreeNode> child_parent_map = knowParentsOfEachNode_slow(root, new HashMap<>());// now u have all
                                                                                                // child-parent pairs of
                                                                                                // each node
        Map<TreeNode, Boolean> isVisisted = new HashMap<>();// by default all r not visited

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(target);// start from target(since they had given reference of target directly,we can
                        // directly start from targetNode without searching it first)
        isVisisted.put(target, true);

        int travelledDistance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            if (travelledDistance == k) {
                break;
            }
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                // left side move
                if (curr.left != null && isVisisted.get(curr.left) == null) {// DONT WRITE ON RHS
                                                                             // !isVisisted.get(curr.left)...it throws
                                                                             // an errror since we r dealing with
                                                                             // references
                    q.offer(curr.left);
                    isVisisted.put(curr.left, true);
                }
                // rigth side move
                if (curr.right != null && isVisisted.get(curr.right) == null) {
                    q.offer(curr.right);
                    isVisisted.put(curr.right, true);
                }
                // also move towards parent to check
                TreeNode parent = child_parent_map.get(curr);
                if (parent != null && isVisisted.get(parent) == null) {
                    q.offer(parent);
                    isVisisted.put(parent, true);
                }
            }
            travelledDistance++;
        }

        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }

        return ans;

    }

    public static List<Integer> optimal(TreeNode root,TreeNode target,int k){// optimal in the sense instead of using map for isVisisted, we use Set(using its .contains() method) and also knowPrents is also faster using recursion
        if (root==null) {
            return new ArrayList<>();
        }
        Map<TreeNode,TreeNode>child_parent_map=new HashMap<>();
        knowParentsOfEachNode_fast(root,null,child_parent_map);
        Set<TreeNode>isVisited=new HashSet<>();

        Queue<TreeNode>q=new LinkedList<>();
        q.offer(target);
        isVisited.add(target);
        int travelledDistance=0;

        while (!q.isEmpty()) {
            if (travelledDistance==k) {
                break;
            }
            int size=q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr=q.poll();
                if (curr.left!=null&&!isVisited.contains(curr.left)) {
                    q.offer(curr.left);
                    isVisited.add(curr.left);
                }
                if (curr.right!=null&&!isVisited.contains(curr.right)) {
                    q.offer(curr.right);
                    isVisited.add(curr.right);
                }
                TreeNode parent=child_parent_map.get(curr);
                if (parent!=null&&!isVisited.contains(parent)) {
                    q.offer(parent);
                    isVisited.add(parent);
                }
            }
            travelledDistance++;
        }
        List<Integer>ans=new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }
        return ans;

    }

    public static Map<TreeNode, TreeNode> knowParentsOfEachNode_slow(TreeNode root,
            Map<TreeNode, TreeNode> child_parent_map) {
        if (root == null) {
            return new HashMap<>();
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode currNode = q.poll();
            for (int i = 0; i < size; i++) {
                if (currNode.left != null) {
                    child_parent_map.put(currNode.left, currNode);
                    q.offer(currNode.left);
                }
                if (currNode.right != null) {
                    child_parent_map.put(currNode.right, currNode);
                    q.offer(currNode.right);
                }
            }
        }
        return child_parent_map;
    }
    public static void knowParentsOfEachNode_fast(TreeNode root,TreeNode parent,Map<TreeNode,TreeNode>child_parent_map){
        if (root==null) {
            return;
        }
        child_parent_map.put(root, parent);
        knowParentsOfEachNode_fast(root.left, root, child_parent_map);
        knowParentsOfEachNode_fast(root.right, root, child_parent_map);
    }
}
