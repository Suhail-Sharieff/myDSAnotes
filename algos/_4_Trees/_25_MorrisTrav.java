package _4_Trees;
//first watch:https://www.youtube.com/watch?v=Wq3ibaP4dJY&ab_channel=codestorywithMIK
//then watch:https://www.youtube.com/watch?v=80Zug6D1_r4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=110&ab_channel=takeUforward
public class _25_MorrisTrav {
    

    //idea for inorder: connext the rightmost child of curr's left child to curr so we void recursion which uses O(n) space

    public static void main(String[] args) {
        //analyze using:---VIMP
        /* 
               1
             /   \
            2      3
          /   \
         4     5
                \
                 6
        */
        TreeNode root=TreeNode.constructTree(new Integer[]{1,2,3,4,5,null,null,null,null,null,6});
        Morris_inorder(root);//left,root,right
        System.out.println();
        Morris_preorder(root);//root,left,right
    }

    public static void Morris_inorder(TreeNode root){//O(n)---O(1)
        if (root==null) {
            return ;
        }
        TreeNode curr=root;

        while (curr!=null) {
            if (curr.left==null) {
                System.out.print(curr.val+" ");
                curr=curr.right;
            }else{
                TreeNode curr_s_leftChild=curr.left;
                while (curr_s_leftChild.right!=null && curr_s_leftChild.right!=curr) {//the 2nd condition is important to hanlde when the root already has thread with curr,if it is so we need to destroy it otherwise the algo loops within that itself
                    curr_s_leftChild=curr_s_leftChild.right;
                }

                //now there can be two case, the rightMost child may have the thread to curr from right already or may have null on right(then we nned to connext to curr),the 1st case is handled by 2nd condition in while loop above
                if (curr_s_leftChild.right==null) {
                    curr_s_leftChild.right=curr;
                    curr=curr.left;
                }else{
                    curr_s_leftChild.right=null;
                    System.out.print(curr.val+" ");
                    curr=curr.right;
                }
            }
        }


    }
    public static void Morris_preorder(TreeNode root){//O(n)---O(1)
        if (root==null) {
            return ;
        }
        TreeNode curr=root;

        while (curr!=null) {
            if (curr.left==null) {
                System.out.print(curr.val+" ");
                curr=curr.right;
            }else{
                TreeNode curr_s_leftChild=curr.left;
                while (curr_s_leftChild.right!=null && curr_s_leftChild.right!=curr) {
                    curr_s_leftChild=curr_s_leftChild.right;
                }

                if (curr_s_leftChild.right==null) {
                    curr_s_leftChild.right=curr;
                    System.out.print(curr.val+" ");
                    curr=curr.left;
                }else{
                    curr_s_leftChild.right=null;
                    curr=curr.right;
                }
                
            }
        }


    }
}
