package _4_Trees;

public class test {

    public static void main(String[] args) {
        int inorder[]={9,3,15,20,7},postorder[]={9,15,7,20,3};

        //inorder->left,root,right{used to build}
        //post order->left,right,root{used to get whats root}

        int postIdx[]={postorder.length-1};
        TreeNode root=build(inorder, postorder, 0, postIdx[0], postIdx);

        TreeNode.displayLevelByLevel(root);

    }

    public static TreeNode build(int inorder[],int postorder[],int inStart,int inEnd,int postIdx[]){
        //the last in postorder will be rrot
       if (postIdx[0]<0 || inStart>inEnd ) {
        return null;
       }
       int val=postorder[postIdx[0]--];
       TreeNode root=new TreeNode(val);
       int idx=idxOfRootInInorder(inorder, val);
       TreeNode rightPart=build(inorder, postorder, idx+1, inEnd, postIdx);
       TreeNode leftPart=build(inorder, postorder, inStart, idx-1, postIdx);
       root.right=rightPart;
       root.left=leftPart;
       return root;
    }
    
    public static int idxOfRootInInorder(int inorder[],int target){
        for(int i=0;i<inorder.length;i++){
            if (inorder[i]==target) {
                return i;
            }
        }
        return -1;
    }

}