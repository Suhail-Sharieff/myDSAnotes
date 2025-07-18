package _5_BST;

public class _7_lca_in_bst {
    //intution: if both p and q vals r less than root.val,move left and viceversa, once u find that this condition is not longer valid, ie its that point where thay r getting split, its lca
    
    //https://www.youtube.com/watch?v=cX_kPV_foZc&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=120&t=103s&ab_channel=takeUforward
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val<p.val && root.val<q.val) return lowestCommonAncestor(root.right,p,q);
        if(root.val>p.val && root.val>q.val) return lowestCommonAncestor(root.left,p,q);
        return root;
    }

    //itertive soln:
    public TreeNode lowestCommonAncestor_iterative(TreeNode root, TreeNode p, TreeNode q) {
        int min=Math.min(p.val,q.val),max=Math.max(p.val,q.val);
        while(root!=null){
            if(root.val<min){
                root=root.right;
            }else if(root.val>max){
                root=root.left;
            }else{
                return root;
            }
        }
        return null;
    }

}
