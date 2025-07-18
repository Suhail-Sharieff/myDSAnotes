import java.util.Stack;

import _4_Trees.TreeNode;

public class Solution {

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{
            7, 3, 8, 2, 4, null, 9
        });
        TreeNode.prettyPrintTree(root, "", false);
        int target=12;
        BST_Iterator ds=new BST_Iterator(root);

        while(ds.get_left()!=ds.get_right()){
            int l=ds.get_left();
            int r=ds.get_right();
            if(l+r==target){
                System.out.println(l+" "+r);
                break;
            }else{
                if(l+r<target){
                    ds.move_right();
                }else{
                    ds.move_left();
                }
            }
        }

    }

    

   static class BST_Iterator{
        
        Stack<TreeNode>forward;
        Stack<TreeNode>backward;
        
        public BST_Iterator(TreeNode root){
            forward=new Stack<>();
            backward=new Stack<>();
            push_all_left_of(root);
            push_all_right_of(root);
        }
        void push_all_left_of(TreeNode root){
            TreeNode curr=root;
            while(curr!=null) {forward.push(curr);curr=curr.left;}
        }
        void push_all_right_of(TreeNode root){
            TreeNode curr=root;
            while(curr!=null) {backward.push(curr);curr=curr.right;}
        }
        
        void move_right(){
            push_all_left_of(forward.pop().right);
        }
        void move_left(){
            push_all_right_of(backward.pop().left);
        }
        int get_right(){
            return backward.peek().val;
        }
        int get_left(){
           return forward.peek().val;
        }
        boolean hasF(){return !forward.isEmpty();}
        boolean hasB(){return !backward.isEmpty();}
    }
}