package _4_Trees;

public class _3_maxDepthOfTree {
    public static int maxDepth(TreeNode root){
        if (root==null) {
            return 0;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        return Math.max(leftDepth, rightDepth);
    }
}
