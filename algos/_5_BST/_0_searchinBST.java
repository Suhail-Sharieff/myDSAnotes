package _5_BST;



/**
ou are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

 

Example 1:


Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]
Example 2:


Input: root = [4,2,7,1,3], val = 5
Output: []
 

Constraints:

The number of nodes in the tree is in the range [1, 5000].
1 <= Node.val <= 107
root is a binary search tree.
1 <= val <= 107
 */
public class _0_searchinBST {


    public static void brute(TreeNode root,int searchVal,TreeNode ans[]){//O(n)---O(n)
        if (root==null) {
            return;
        }
        System.out.print("Reached "+root.val+" -> ");
        if (root.val==searchVal) {
            ans[0]=root;
        }
        if (root.val<searchVal) {
            //it will be present at right of that
            brute(root.right, searchVal,ans);
        }else{
            brute(root.left, searchVal, ans);
        }
        System.gc();///this helps in clearing unwanted memory---memoery now beats 99.87 :)
    }

    public static TreeNode optimal(TreeNode root,int target){//O(n)---O(1)
        if (root==null) {
            return null;
        }
        while (root!=null && root.val!=target) {
            if (target<root.val) {//then obviously it must lie on left
                root=root.left;
            }else{
                root=root.right;
            }
        }
        return root;
    }

}