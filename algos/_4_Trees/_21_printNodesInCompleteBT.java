package _4_Trees;
/*
Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

 

Example 1:


Input: root = [1,2,3,4,5,6]
Output: 6
Example 2:

Input: root = []
Output: 0
Example 3:

Input: root = [1]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [0, 5 * 104].
0 <= Node.val <= 5 * 104
The tree is guaranteed to be complete.
 */
public class _21_printNodesInCompleteBT {
//https://www.youtube.com/watch?v=2XTXL7a6ItI&ab_channel=codestorywithMIK

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{1,2,3,4,5,6});
        System.out.println(brute(root));
        System.out.println(optimal(root));
    }

    public static int brute(TreeNode root) {//O(n)--O(h)
        if(root==null){
            return 0;
        }
        
        int left=brute(root.left);
        int right=brute(root.right);

        return left+right+1;
    }

    //OPTIMAL:We knwo that for every node IN A COMPLETE BINARY tree, the left height and right height are equal(lets call it as h), thn nOfNodes from that node is (2^h)-1 or (2<<(h-1))-1  {both r meaning as pow(2,h), but 2<<(h-1) is faster (h-1 coz 2<<h gives 2^(h+1))}.
    //So instaed of foolish like moving via every node and incre   asing the number cnt by 1, we just see if left and right heights r equal, if it is the case,means we already know that no nodes from that node will be (2^h)-1, so we return this value there itself withourt traversing further nodes, if its not the case, then only we will traverse via the incomplete part to count via each node
    public static int optimal(TreeNode root){//O((logn)^2)--worst case where we calculate the left&rightHeight which takes logN time for every Node
        if (root==null) {
            return 0;
        }
        
        int leftHeight=leftHeight(root);
        int rightHeight=rightHeight(root);

        if (leftHeight==rightHeight) {//is perfect BT from tht node
            return ((2<<(leftHeight-1))-1);
        }

        return (optimal(root.left)+optimal(root.right)+1);
    }

    public static int leftHeight(TreeNode root){
        if (root==null) {
            return 0;
        }
        return (leftHeight(root.left)+1);
    }
    public static int rightHeight(TreeNode root){
        if (root==null) {
            return 0;
        }
        return (rightHeight(root.right)+1);
    }
}
