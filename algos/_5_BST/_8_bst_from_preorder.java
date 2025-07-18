package _5_BST;
/*
Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

 

Example 1:


Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
            8
           / \
          5   10
         / \    \
        1   7    12
Example 2:

Input: preorder = [1,3]
Output: [1,null,3]
 

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 1000
All the values of preorder are unique.
 */
public class _8_bst_from_preorder {
    //--------------brute force traverse throught preorder array and at each iteration perform insert operation of bst
    static void insert(TreeNode root,int newVal){
        if (root==null) {
            return;
        }
        TreeNode temp=root;
        while (temp!=null) {
            if (temp.val>newVal) {
                if (temp.left==null) {
                    temp.left=new TreeNode(newVal);
                    return;
                }else{
                    temp=temp.left;
                }
            }else{
                if (temp.right==null) {
                    temp.right=new TreeNode(newVal);
                    return;
                }else{
                    temp=temp.right;
                }
            }
        }
    }
    static TreeNode brute(int preOrder[]){//---------O(n^2)---O(1)
        TreeNode root=new TreeNode(preOrder[0]);
        for (int i = 1; i < preOrder.length; i++) {
            insert(root, preOrder[i]);
        }
        return root;
    }


    //-------------------better-uses same idea that u used to construct a normal tree but her its advantageous that we know that smaller elemts on left and larger on right
    /**

 */
class Better {//worst case n^2
    public TreeNode bstFromPreorder(int[] preorder) {
       return rec(preorder,0,preorder.length-1);
    }
    public TreeNode rec(int pre[],int l,int r){
        if(l>r) return null;
        TreeNode root=new TreeNode(pre[l]);
        int leftEnd=getIdx(pre,l+1,r,pre[l]);
        root.left=rec(pre,l+1,leftEnd);
        root.right=rec(pre,leftEnd+1,r);
        return root;
    }
    public int getIdx(int arr[],int l,int r,int key){
        int idx=l-1;///BIG MISTAKE: took as some other value like r or arr.length
        for(int i=l;i<=r;i++) if(arr[i]<key) idx=i; else break;
        return idx;
    }
}












    //optimal: https://www.youtube.com/watch?v=UmJT3j26t1I&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=122&ab_channel=takeUforward
    //prereq: _6_validateBST idea it uses, but here we can iterate just by using upper bound , rather than both lower and upper bound
    // if elemnt under upperbound, insert it on left, if not backtrack
    static TreeNode optimal(int preorder[],int preIdx[],int upperBound){
        if (preIdx[0]>=preorder.length || preorder[preIdx[0]]>upperBound) {//second condition is important
            return null;
        }
        TreeNode root=new TreeNode(preorder[preIdx[0]++]);
        root.left=optimal(preorder, preIdx, root.val);//when u move left of curr, curr.val becmes upperbound for its left child
        root.right=optimal(preorder, preIdx, upperBound);//but when we move right, upperBound remains same
        return root;
    } 

    public static void main(String[] args) {
        TreeNode.displayLevelByLevel(optimal(new int[]{8,5,1,7,10,12}, new int[]{0}, Integer.MAX_VALUE));
    }
}
