package _4_Trees;
/*
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

 
 */
public class _5_diameterOfBT {
    public int optimal(TreeNode root){//O(n)
        //  //https://www.youtube.com/watch?v=Rezetez59Nk&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=88&ab_channel=takeUforward
        //watch from 10:00
        
        //since we need the longest distance between any two nodes(they could be at same/different levels involving/notInvolving root), we would trverse via each node and from that node we find its left and right maxDepth, we finally return max ofd all depths...just slight modification of maxDepth algo
        maxDepth(root);//call it first and then return ans
        return ans;

    }
    public int ans;
    public int maxDepth(TreeNode root){
        if (root==null) {
            return 0;
        }
        int leftD=maxDepth(root.left);
        int rightD=maxDepth(root.right);
        int currDia=leftD+rightD;
        ans=Math.max(ans, currDia);
        return Math.max(leftD, rightD)+1;
    }




}
