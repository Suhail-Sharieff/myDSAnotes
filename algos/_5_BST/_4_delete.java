package _5_BST;


/*
Input: root = [1, N, 2, N, 8, 5, 11, 4, 7, 9, 12]
            1
             \
              2
                \
                 8 
               /    \
              5      11
            /  \    /  \
           4    7  9   12
key = 11
Output: 1 2 4 5 7 8 9 12
Explanation: In the given input, tree after deleting 11 will be
             1
              \
               2
                 \
                  8
                 /  \
                5    12
               / \   /
              4   7 9 
 */

public class _4_delete {
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{5,3,6,2,4,null,7});
        int key=3;
        TreeNode.displayLevelByLevel(root);

        //IMP:
        if(root.val==key) {
            TreeNode.displayLevelByLevel( substitute(root));
            return;
        }

        deleteNode(root, key);
        System.out.println("------");
        TreeNode.displayLevelByLevel(root);
        
    }


    


    public static void deleteNode(TreeNode root,int target){
        if (root==null) {
            return;
        }
        TreeNode temp=root;

        while (temp!=null) {
            if(temp.val==target){
                TreeNode parentOfTarget=parentOf(target, root);
                if (parentOfTarget.left==temp) {
                    //we want to delete the node on left of parent
                    parentOfTarget.left=substitute(temp);
                }else{
                    //we want to delete on right of parent
                    parentOfTarget.right=substitute(temp);
                }
                break;
            }else if(temp.val>target){
                temp=temp.left;
            }else{
                temp=temp.right;
            }
        }

        
    }

    //function to find paretn node of the node we wanna delete:
    public  static TreeNode parentOf(int val,TreeNode root){
        if(root==null){
            return null;
        }   
        TreeNode temp=root;
        TreeNode par=null;
        while (temp!=null) {
            if (temp.val==val) {
                break;
            }
            par=temp;
            if (temp.val>val) {
                temp=temp.left;
            }else if(temp.val<val){
                temp=temp.right;
            }
        }
        return par;
    }

    public static TreeNode substitute(TreeNode targetNode){
        if (targetNode.left==null) {
            return targetNode.right;
        }else if(targetNode.right==null){
            return targetNode.left;
        }
        //the target node has 2 children
        //connect the right child of targetnode to right of rightmost node on (left of targetnode) 
        TreeNode rightChildOfTarget=targetNode.right;
        TreeNode rightMostOnLeftOfTargetNode=rightMost(targetNode.left);
        rightMostOnLeftOfTargetNode.right=rightChildOfTarget;
        return targetNode.left;//coz we have shifted complete right portion of target node to the left sie by attaching it to rightMostNode on left part of targetNode
    }

    public static TreeNode rightMost(TreeNode root){
        if (root.right==null) {
            return root;
        }
        return rightMost(root.right);
    }


    //optimal sultion--in the way that it avoids finding parent after we have reached target

    public  TreeNode optimalDelete(TreeNode root, int target){
        if (root==null) {
            return null;
        }
        if (root.val==target) {
            return substitute(root);
        }
        TreeNode temp=root;
        while (root!=null) {//DONT USE WHLE(TEMP!=NULL)--X
            if (root.val>target) {
                //target is on left
                if(root.left!=null && root.left.val==target){
                    root.left=substitute(root.left);
                    break;
                }else{
                    root=root.left;
                }
            }else{
                //target will be on right
                if (root.right!=null && root. right.val==target) {
                    root.right=substitute(root.right);
                    break;
                } else{
                    root=root.right;
                }
            }
        }
        return temp;
    }

}
