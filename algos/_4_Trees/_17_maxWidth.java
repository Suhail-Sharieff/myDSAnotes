package _4_Trees;

import java.util.*;

/**
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).
 

Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
 */
public class _17_maxWidth {

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(new Integer[] {1,3,2,5,null,null,9,6,null,7});
        System.out.println(optimal(root));
    }

    //watch to understand: https://www.youtube.com/watch?v=ZbybYvcVLks&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=100&ab_channel=takeUforward
    
    public static int optimal(TreeNode root){//the main thing here is we need to consider even the null values while taking width
        //so we assign for each node the index (just like heap and segment tree) starting from root having idx 1,(not 0 coz later we will use 2*idx), left child will have idx 2*idxOfParent and right has 2*idxOfParent+1  
        if (root==null) {
            return 0;
        }
        
        Queue<node_idx_pair>q=new LinkedList<>();
        q.offer(new node_idx_pair(root, 1));
        int ans=0;

        while (!q.isEmpty()) {
            int levelSize=q.size();
            int startIdx=q.peek().idx;
            
            int lastIdx=0;//just initializing to some value since we need it later, ie after all nodes of that level r traversed,this variable will contain the idx of last node of that level, thsi way we can intelligenly manage even the null values

            for (int i = 0; i < levelSize; i++) {
                node_idx_pair p=q.poll();
                TreeNode curr=p.node;
                lastIdx=p.idx;

                if (curr.left!=null) {
                    q.offer(new node_idx_pair(curr.left, 2*lastIdx));
                }
                if (curr.right!=null) {
                    q.offer(new node_idx_pair(curr.right, 2*lastIdx+1));
                }

            }

            int thatLevelWidth=lastIdx-startIdx+1;
            ans=Math.max(ans, thatLevelWidth);

        }

        return ans;
    }

    

    
}

class node_idx_pair{
    public TreeNode node;
    public int idx;
    public node_idx_pair(TreeNode node,int idx){
        this.node=node;
        this.idx=idx;
    }
}