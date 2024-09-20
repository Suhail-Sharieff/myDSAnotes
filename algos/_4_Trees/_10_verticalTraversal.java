package _4_Trees;

import java.util.*;

import javax.print.attribute.IntegerSyntax;

/**
 * _10_verticalTraversal
 */
/*
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

 

  Input:            1
                  /    \ 
                2      3
               / \   /   \
             4   5  6     7
                         /  \ 
                       8    9 

Output: 
[[4],[2],[1,5,6],[3,8],[7],[9]]

Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.
Example 2:


Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.
Example 3:


Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
 */
/**
 * _10_verticalTraversal
 */
public class _10_verticalTraversal {
  public static void main(String[] args) {
    TreeMap<Integer,Integer>p=new TreeMap<>();

    // p.put(1,23);

    TreeNode root=TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});
    optimal(root);
    // p.put(2, 45);
    // System.out.println(p);

  }


  public static void optimal(TreeNode root){
    // <xPos,map<ypos,List>>
    TreeMap<Integer,TreeMap<Integer,List<Integer>>>map=new TreeMap<>();//treemap auto stores the k,v pairs in sorted fashion
    Queue<co_ordinate>q=new LinkedList<>();
    q.offer(new co_ordinate(root, 0, 0));

    while (!q.isEmpty()) {
      co_ordinate curr=q.poll();

      TreeNode thatNode=curr.Node;
      int xPos=curr.xPos;
      int yPos=curr.yPos;

      if (!map.containsKey(xPos)) {
        map.put(xPos, new TreeMap<>());
      }
      if (!map.get(xPos).containsKey(yPos)) {
        map.get(xPos).put(yPos, new ArrayList<>());
      }

      map.get(xPos).get(yPos).add(thatNode.val);

      if (thatNode.left!=null) {
        q.offer(new co_ordinate(thatNode.left, xPos-1, yPos+1));
      }
      if (thatNode.right!=null) {
        q.offer(new co_ordinate(thatNode.right, xPos+1, yPos+1));
      }

    }//now the list within the TreeMap has answer elemnts




    List<List<Integer>>ans=new ArrayList<>();

    for(TreeMap<Integer,List<Integer>> eachMap:map.values()){
      List<Integer>thatCol=new ArrayList<>();
      for(List<Integer>li:eachMap.values()){
        thatCol.addAll(li);
      }
      ans.add(thatCol);
    }

    System.out.println(ans);


  }



}

class co_ordinate {

  public TreeNode Node;
  public int xPos;
  public int yPos;

  public co_ordinate(TreeNode Node,int xPos, int yPos) {
    this.Node = Node;
    this.xPos = xPos;
    this.yPos = yPos;
  }
}
