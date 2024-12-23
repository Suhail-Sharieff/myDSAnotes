package _5_BST;

public class _5_kth_sm_lar {
    public int kthSmallest(TreeNode root, int k) {
        int cnt[]={0},ans[]={-1};
        optimalSmallest(root,cnt,k,ans);
        return ans[0];
    }
    public int kthLargest(TreeNode root, int k) {
        int cnt[]={0},ans[]={-1};
        optimalLargest(root,cnt,k,ans);
        return ans[0];
    }

    //brute would be to do inorder traversal and store elemnts in some List and return list.get(k) since inorder of bst is always a sorted array

    public void optimalSmallest(TreeNode root,int cnt[],int k,int ans[]){
        if(root==null) return;
        optimalSmallest(root.left,cnt,k,ans);
        cnt[0]++;
        if(cnt[0]==k){ ans[0]=root.val;return;}
        optimalSmallest(root.right,cnt,k,ans);
    }
    public void optimalLargest(TreeNode root,int cnt[],int k,int ans[]){
        if(root==null) return;
        optimalLargest(root.right,cnt,k,ans);
        cnt[0]++;
        if(cnt[0]==k){ ans[0]=root.val;return;}
        optimalLargest(root.left,cnt,k,ans);
    }
}
