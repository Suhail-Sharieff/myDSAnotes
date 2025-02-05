package _4_Trees;
import java.util.*;


/*
Given below is a binary tree. The task is to print the top view of binary tree. Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. For the given below tree

       1
    /     \
   2       3
  /  \    /   \
4    5  6      7

Top view will be: 4 2 1 3 7
Note: Return nodes from leftmost node to rightmost node. Also if 2 nodes are outside the shadow of the tree and are at same position then consider the left ones only(i.e. leftmost). 
For ex - 1 2 3 N 4 5 N 6 N 7 N 8 N 9 N N N N N will give 8 2 1 3 as answer. Here 8 and 9 are on the same position but 9 will get shadowed.

Example 1:

Input:
      1
   /    \
  2      3
Output: 2 1 3
Example 2:

Input:
       10
    /      \
  20        30
 /   \    /    \
40   60  90    100
Output: 40 20 10 30 100



Your Task:
Since this is a function problem. You don't have to take input. Just complete the function topView() that takes root node as parameter and returns a list of nodes visible from the top view from left to right.
 */

//PREREQ: understand vertical order very well


public class _11_topView {
    static class co_ordinate {

        public TreeNode node;
        public int xPos;
        public int yPos;
    
        public co_ordinate(TreeNode node, int xPos, int yPos) {
            this.node = node;
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{10,20,30,40,6090,100});

        System.out.println(brute(root));
        System.out.println(optimal(root));
    }
    public static List<Integer> brute(TreeNode root) {
        //brute force is to just pivck up the first list from every list in treeMap of vertical order traversal since that first will always be on top visible on top view
        List<List<Integer>> li = verticalOrdTrav(root);//this has list of each vertical levl from left to right
        ArrayList<Integer> ans = new ArrayList<>();
        for (List<Integer> each : li) {
            ans.add(each.get(0));
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

            map.get(xPos).get(yPos).add(thatTreeNode.val);

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

    public static List<Integer>optimal(TreeNode root){//same as that of optimal but just we make it so that map adds only 1 value whichs what we nned rather than all--see line 121
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
                map.get(xPos).get(yPos).add(thatTreeNode.val);//this is shifted here,previouly we used to add val even if this block fails,now we do only if it passes this condition
            }

            

            if (thatTreeNode.left != null) {
                q.offer(new co_ordinate(thatTreeNode.left, xPos - 1, yPos + 1));
            }
            if (thatTreeNode.right != null) {
                q.offer(new co_ordinate(thatTreeNode.right, xPos + 1, yPos + 1));
            }

        }

        List<Integer> ans = new ArrayList<>();

        for (TreeMap<Integer, List<Integer>> eachMap : map.values()) {
            List<Integer> thatCol = new ArrayList<>();
            for (List<Integer> li : eachMap.values()) {
                thatCol.addAll(li);
            }
            ans.add(thatCol.get(0));//obviosuly only 1 elemnt will be present
        }

        return (ans);

    }
}



