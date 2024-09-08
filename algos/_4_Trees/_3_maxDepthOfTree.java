package _4_Trees;

public class _3_maxDepthOfTree {
    public static int maxDepth(treeNode root){
        if (root==null) {
            return 0;
        }
        int leftDepth=maxDepth(root.leftChild);
        int rightDepth=maxDepth(root.rightChild);
        return Math.max(leftDepth, rightDepth);
    }
}
