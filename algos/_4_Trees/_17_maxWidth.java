package _4_Trees;

import java.util.*;

/**
 * _17_maxWidth
 */
public class _17_maxWidth {

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(new Integer[] {1,3,2,5,null,null,9,6,null,7});
        System.out.println(optimal(root));
    }

    public static int optimal(TreeNode root){
        if (root==null) {
            return 0;
        }
        
        Queue<node_idx_pair>q=new LinkedList<>();
        q.offer(new node_idx_pair(root, 1));
        int ans=0;

        while (!q.isEmpty()) {
            int levelSize=q.size();
            int startIdx=q.peek().idx;
            
            int thatIdx=0;//just initializing to some value since we need it later

            for (int i = 0; i < levelSize; i++) {
                node_idx_pair p=q.poll();
                TreeNode curr=p.node;
                thatIdx=p.idx;

                if (curr.left!=null) {
                    q.offer(new node_idx_pair(curr.left, 2*thatIdx));
                }
                if (curr.right!=null) {
                    q.offer(new node_idx_pair(curr.right, 2*thatIdx+1));
                }

            }

            int thatLevelWidth=thatIdx-startIdx+1;
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