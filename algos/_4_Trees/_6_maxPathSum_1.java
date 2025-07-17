package _4_Trees;
/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
public class _6_maxPathSum_1 {
    public int maxSum1UsingGlobalVar(TreeNode root){//O(n)
        maxGlobal=Integer.MIN_VALUE;
        maxSum1(root);
        return maxGlobal;
    }
    public int maxGlobal;
    public int maxSum1(TreeNode root){//the logic is as same as that of diameter of BT, move all possible downward U shapes in the tree
        if (root==null) {//root doestn exists there
            return 0;
        }
        int leftSum=Math.max(0, maxSum1(root.left));//IMP: why Math.max and not just maxSum1(root.left), coz we dont want to take -ve values & sum, since they always tend to decrease the sum
        int rightSum=Math.max(0, maxSum1(root.right));
        maxGlobal=Math.max(maxGlobal, root.val+leftSum+rightSum);

        return root.val+Math.max(leftSum, rightSum);//max willtell wheather to take left/right path

    }




    public static int maxSum1WithoutGlobalVar(TreeNode root){
        int ref[]=new int[1];
        ref[0]=Integer.MIN_VALUE;
        maxSum2(root, ref);
        return ref[0];
    }
    public static int maxSum2(TreeNode root,int ref[]){
        if (root==null) {
            return 0;
        }
        int leftSum=Math.max(0, maxSum2(root.left, ref));
        int rightSum=Math.max(0, maxSum2(root.right, ref));
        ref[0]=Math.max(ref[0], root.val+leftSum+rightSum);

        return root.val+Math.max(leftSum, rightSum);
    }


    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{-10,9,20,null,null,15,7});
        int ref[]=new int[1];
        ref[0]=Integer.MIN_VALUE;
        System.out.println(maxSum1WithoutGlobalVar(root));
    }
}
