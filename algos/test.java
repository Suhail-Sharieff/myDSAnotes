import _4_Trees.TreeNode;

public class test {

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{4,3,null,1,2});
       int ans[]={Integer.MIN_VALUE};
       func(root, ans);
       System.out.println(ans[0]);
    }

    public static void func(TreeNode root,int ans[]){
        if(root==null) return;
        if(validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE)){
            int sum=sum(root);
            ans[0]=Math.max(ans[0], sum);
        }
        func(root.left, ans);
        func(root.right, ans);
    }

    public static int sum(TreeNode root){
        if(root==null) return 0;
        return (root.val+sum(root.left)+sum(root.right));
    }

    public static  boolean validate(TreeNode root,long min,long max){
        if(root==null) return true;
        if(root.val<=min || root.val>=max) return false;
        return validate(root.left,min,root.val)&&validate(root.right,root.val,max);
    }
}