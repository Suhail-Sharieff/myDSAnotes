

import _5_BST.TreeNode;

public class test {

    public static void main(String[] args) {
       int preorder[]={8,5,1,7,10,12};

       TreeNode root=func(preorder, 0);


       TreeNode.displayLevelByLevelWithNulls(root);


    }

    static TreeNode func(int preorder[],int s){
        if(s>=preorder.length) return null;

        TreeNode root=new TreeNode(preorder[s]);
        s++;
        if(s<preorder.length && preorder[s]<root.val){
            root.left=func(preorder,s);
        }
        s++;
        if(s<preorder.length && preorder[s]>root.val){
            root.right=func(preorder,s);
        }


        return  root;
    }
}