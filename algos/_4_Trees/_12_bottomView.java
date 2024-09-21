package _4_Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
/*
Given a binary tree, return an array where elements represent the bottom view of the binary tree from left to right.

Note: If there are multiple bottom-most nodes for a horizontal distance from the root, then the latter one in the level traversal is considered. For example, in the below diagram, 3 and 4 are both the bottommost nodes at a horizontal distance of 0, here 4 will be considered.

                      20
                    /    \
                  8       22
                /   \     / \
              5      3  4    25
                     /    \      
                  10       14

For the above tree, the output should be 5 10 4 14 25.

Examples :

Input:
       1
     /   \
    3     2
Output: 3 1 2
Explanation: First case represents a tree with 3 nodes and 2 edges where root is 1, left child of 1 is 3 and right child of 1 is 2.

Thus bottom view of the binary tree will be 3 1 2.
Input:
         10
       /    \
      20    30
     /  \
    40   60
Output: 40 20 60 30
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(n)

Constraints:
1 <= Number of nodes <= 105
1 <= Data of a node <= 105
 */
public class _12_bottomView {

    public static void main(String[] args) {
        
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(20);
        root.right=new TreeNode(30);
        root.left.left=new TreeNode(40);
        root.left.right=new TreeNode(60);

        System.out.println(brute(root));
    }



    //the logic of brute is as same as that of top view but instaed of adding elemnt at index 0, we add last index elemnt
    public static List<Integer> brute(TreeNode root) {
        List<List<Integer>> li = verticalOrdTrav(root);
        ArrayList<Integer> ans = new ArrayList<>();
        for (List<Integer> each : li) {
            ans.add(each.get(each.size()-1));
        }
        
        return ans;
    }

    public static List<List<Integer>> verticalOrdTrav(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        Queue<co_ordinate> q = new LinkedList<>();
        q.offer(new co_ordinate(root, 0, 0));

        while (!q.isEmpty()) {
            co_ordinate curr = q.poll();

            TreeNode thatTreeNode = curr.node;
            int xPos = curr.xPos;
            int yPos = curr.yPos;

            if (!map.containsKey(xPos)) {
                map.put(xPos, new TreeMap<>());
            }
            if (!map.get(xPos).containsKey(yPos)) {
                map.get(xPos).put(yPos, new ArrayList<>());
            }

            map.get(xPos).get(yPos).add(thatTreeNode.val);//add all

            if (thatTreeNode.left != null) {
                q.offer(new co_ordinate(thatTreeNode.left, xPos - 1, yPos + 1));
            }
            if (thatTreeNode.right != null) {
                q.offer(new co_ordinate(thatTreeNode.right, xPos + 1, yPos + 1));
            }

        }

        List<List<Integer>> ans = new ArrayList<>();

        for (TreeMap<Integer, List<Integer>> eachMap : map.values()) {
            List<Integer> thatCol = new ArrayList<>();
            for (List<Integer> li : eachMap.values()) {
                thatCol.addAll(li);
            }
            ans.add(thatCol);
        }

        return (ans);

    }




    //just comment some things in brute to make it better
    public static List<Integer> better(TreeNode root){
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        Queue<co_ordinate> q = new LinkedList<>();
        q.offer(new co_ordinate(root, 0, 0));

        while (!q.isEmpty()) {
            co_ordinate curr = q.poll();

            TreeNode thatTreeNode = curr.node;
            int xPos = curr.xPos;
            int yPos = curr.yPos;

            // if (!map.containsKey(xPos)) {
                map.put(xPos, new TreeMap<>());
            // }
            // if (!map.get(xPos).containsKey(yPos)) {
                map.get(xPos).put(yPos, new ArrayList<>());
            // }

            //see the diference, if u remove those conditions, only 1 value will be added into map for each xPos each time due to overriding at same place , the final vval will be automatically the last one 

            map.get(xPos).get(yPos).add(thatTreeNode.val);//add all

            if (thatTreeNode.left != null) {
                q.offer(new co_ordinate(thatTreeNode.left, xPos - 1, yPos + 1));
            }
            if (thatTreeNode.right != null) {
                q.offer(new co_ordinate(thatTreeNode.right, xPos + 1, yPos + 1));
            }

        }

        List<List<Integer>> ans = new ArrayList<>();

        for (TreeMap<Integer, List<Integer>> eachMap : map.values()) {
            List<Integer> thatCol = new ArrayList<>();
            for (List<Integer> li : eachMap.values()) {
                thatCol.addAll(li);
            }
            ans.add(thatCol);
        }

        return (ans);

    }
}
class co_ordinate {

    public TreeNode node;
    public int xPos;
    public int yPos;

    public co_ordinate(TreeNode node, int xPos, int yPos) {
        this.node = node;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
