import _4_Trees.TreeNode;

public class test {

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{4,2,7,1,3});
        int target=2;
        TreeNode ans[]={null};
        trav(root, target, ans);
        System.out.println();
        TreeNode.displayLevelByLevel(ans[0]);
        
    }

    public static void trav(TreeNode root,int searchVal,TreeNode ans[]){
        if (root==null) {
            return;
        }
        System.out.print("Reached "+root.val+" -> ");
        if (root.val==searchVal) {
            ans[0]=root;
        }
        if (root.val<searchVal) {
            //it will be present at right of that
            trav(root.right, searchVal,ans);
        }else{
            trav(root.left, searchVal, ans);
        }
    }
}