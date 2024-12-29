package _5_BST;
//find inorder successor and predessor of the given key, if not found return -1
public class _9_inor_suc_pred {
    public static void main(String[] args) {
        //inorder succ is nothing but ceil value of key, ie value which's just greater or equal than key
        //inorder predessor is nothing but floor value ie value which's just less or equal to key

    }
    public static TreeNode inorSucc(TreeNode root,int key){//O(logn)--O(1)
        TreeNode temp=root;
        TreeNode ans=null;
        while (temp!=null) {
            if(temp.val>key){
                ans=temp;
                temp=temp.left;
            }else{
                temp=temp.right;
            }
        }
        return ans;
    }
    public static TreeNode inorPred(TreeNode root,int key){//O(logn)--O(1)
        TreeNode temp=root;
        TreeNode ans=null;
        while (temp!=null) {
            if(temp.val<key){
                ans=temp;
                temp=temp.right;
            }else{
                temp=temp.left;
            }
        }
        return ans;
    }
}
