package _4_Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class _13_left_right_views {
    //logic:
    //to get left view, just get first elemnt in list of level ordertraversal and vice versa for right view

    //ex:
    /*
root[] = [1, 2, 3, 4, 5, 6, 7, null, 8]
          1
       /     \
     2        3
   /   \    /   \
  4     5   6     7
   \
     8   


     */
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, null, 8});
        System.out.println(leftView(root));
        System.out.println(rightView(root));
    }

    static ArrayList<Integer> leftView(TreeNode root)
    {
      // Your code here
       if(root==null){
            return new ArrayList<>();
        }
        //last of each level is ans
        Queue<TreeNode>q=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            ArrayList<Integer>each=new ArrayList<>();
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                each.add(curr.val);
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
            ans.add(each.get(0));//add only lefrt most of that level
        }
        
        return ans;
    }
    static ArrayList<Integer> rightView(TreeNode root)
    {
      // Your code here
       if(root==null){
            return new ArrayList<>();
        }
        //last of each level is ans
        Queue<TreeNode>q=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            ArrayList<Integer>each=new ArrayList<>();
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                each.add(curr.val);
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
            ans.add(each.get(each.size()-1));//add only lefrt most of that level
        }
        
        return ans;
    }
}
