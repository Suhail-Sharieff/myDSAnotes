package _5_BST;
//very beautiful algo: demonstrates how u can treat BST like an sorted array and also make 2 pointers on it
//https://www.youtube.com/watch?v=ssL3sHwPeb4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=125&ab_channel=takeUforward
import java.util.HashSet;
/*
Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

 

Example 1:


Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
Example 2:


Input: root = [5,3,6,2,4,null,7], k = 28
Output: false
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-104 <= Node.val <= 104
root is guaranteed to be a valid binary search tree.
-105 <= k <= 105
 */
import java.util.Stack;

//prereq:_10_iterate_inorder_bst.java
public class _11_2Sum {


    //brute:O(n)--O(n)
    public void brute(TreeNode root,HashSet<Integer>se,int k,boolean ans[]){
        if(root==null) return ;
        brute(root.left,se,k,ans);
        if(se.contains(k-root.val)){
            ans[0]=true;
            return;
        }
        se.add(root.val);
        brute(root.right,se,k,ans);
    }

    //optimal:O(n)--O(h)--see that we have optimized space complexity from O(n) to O(h)
    //normally how we do 2 sum? (for sorted array), we keep left pointer on leftmost of arr, and rightPtr on rightMost of arr, we check if left+right==targetSum, we return YES, if left+right>target, we move rightPtr 1 step left and if left+right<target, we move leftPtr 1 step right, this procedure continnues untill left<right. Now we make use of next() method in _10_iterate.. program and trat it as leftPtr, coz next() method in that always resulted in the next greater elemnt as poer inorder, in addition we use same idea to implemnt before() method that will give largest to smallest elemnt in order, ie we can treat it as rightPtr

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{5,3,6,2,4,null,7});
        int targetSum=9;
        System.out.println(optimal(root, targetSum));
    }

    public static boolean optimal(TreeNode root,int targetSum){
        Stack<TreeNode>leftStack=new Stack<>();
        Stack<TreeNode>rightStack=new Stack<>();
        pushLeftStuffOf(root, leftStack);
        pushRightStuffOf(root, rightStack);
        int leftPtr=nextGreater(leftStack);
        int rightPtr=nextSmaller(rightStack);
        boolean ans[]={false};
        while (leftPtr<rightPtr) {
            int currSum=leftPtr+rightPtr;
            if(currSum==targetSum){ans[0]=true;break;}
            if(currSum<targetSum) leftPtr=nextGreater(leftStack);
            else rightPtr=nextSmaller(rightStack);
        }
        return ans[0];
    }


    public static void pushLeftStuffOf(TreeNode root,Stack<TreeNode>st){
        TreeNode temp=root;
        while (temp!=null) {
            st.push(temp);
            temp=temp.left;
        }
    }
    public static int nextGreater(Stack<TreeNode>st){
        TreeNode top=st.pop();
        pushLeftStuffOf(top.right, st);
        return top.val;
    }

    //IMP:
    public static void pushRightStuffOf(TreeNode root,Stack<TreeNode>st){
        TreeNode temp=root;
        while (temp!=null) {
            st.push(temp);
            temp=temp.right;
        }
    }
    public static int nextSmaller(Stack<TreeNode>st){
        TreeNode top=st.pop();
        pushRightStuffOf(top.left, st);
        return top.val;
    }


    //===========Object orient code
     public static void optimal2(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{
            7, 3, 8, 2, 4, null, 9
        });
        int target=12;
        BST_Iterator ds=new BST_Iterator(root);

        while(ds.get_left()!=ds.get_right()){//untill just 1 elemnt remains
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
        boolean hasForward(){return !forward.isEmpty();}
        boolean hasBackward(){return !backward.isEmpty();}
    }

}
