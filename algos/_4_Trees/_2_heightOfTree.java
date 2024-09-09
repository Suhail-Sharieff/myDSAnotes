package _4_Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * heightOfTree
 */
public class _2_heightOfTree {

    public static void main(String[] args) {
        
    }

    public static int brute(TreeNode root){
        //traverse level by level and just 
        if (root==null) {
            return 0;
        }
        Queue<TreeNode>q=new LinkedList<>();
        List<List<Integer>>ans=new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int thatLevelSize=q.size();
            List<Integer>each=new ArrayList<>();
            for (int i = 0; i < thatLevelSize; i++) {
                TreeNode temp=q.poll();
                each.add(temp.val);
                if (temp.left!=null) {
                    q.offer(temp.left);
                }
                if (temp.right!=null) {
                    q.offer(temp.right);
                }
            }
            ans.add(each);
        }
        return ans.size();
    }
}