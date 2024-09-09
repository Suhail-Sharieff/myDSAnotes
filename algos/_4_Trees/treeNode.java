package _4_Trees;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val){
        this.val=val;
    }

    public void displayNode() {
        System.out.print("{" + val + "}");
    }
}
