import _5_BST.TreeNode;

public class test {

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructBST(new Integer[]{5,3,6,2,4,7});

        TreeNode.displayLevelByLevel(root);
        System.out.println("----------");
        TreeNode.deleteFromBST(root, 4);
        TreeNode.displayLevelByLevel(root);
    }




    public static TreeNode ceil(int val,TreeNode root){
        //ceil->smallest value that is greater than or equal to the val
        if (root==null) {
            return null;
        }
        TreeNode temp=root;
        TreeNode ans=null;
        while (temp!=null) {
            if (temp.val==val) {
                return temp;
            }
            if (temp.val>val) {
                ans=temp;
                temp=temp.left;
            }else{
                temp=temp.right;
            }
        }
        return ans;
    }


    public static void insertNode(TreeNode newNode,TreeNode root){
        if (root==null) {
            root=newNode;
            return;
        }
        TreeNode temp=root;
        TreeNode prev=root;
        while (temp!=null) {
            prev=temp;
            if (temp.val<newNode.val) {
                //the value we wanna insert is greater 
                temp=temp.right;
            }else{
                temp=temp.left;
            }
        }
        if (newNode.val<=prev.val) {
            prev.left=newNode;
        }else{
            prev.right=newNode;
        }
    }


    public static TreeNode find(int target,TreeNode root){
        if (root==null) {
            return null;
        }
        if (root.val==target) {
            return root;
        }
        TreeNode l=find(target, root.left);
        TreeNode r=find(target, root.right);
        if (l!=null ) {
            return l;
        }else if(r!=null){
            return r;
        }
        return null;
    }



    public static void deleteNode(int val,TreeNode root){
        if (root==null) {
            System.out.println("Empty Tree");
            return;
        }
        TreeNode target=find(val, root);


        if (target==null) {
            System.out.println("Not found to delete");
        }
        
        TreeNode substitue=ceil(val, root);

        if (target==root) {
            root=null;
            return;
        }


        if (target.left==null && target.right==null) {
            //is a leaf node
            System.out.println("Curr val of target : "+target);
            target=null;
            System.out.println("Curr val of target : "+target);
            return;
        }

        // target=substitue;


    }

    







}



