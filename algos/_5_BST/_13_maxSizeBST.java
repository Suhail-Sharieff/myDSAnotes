package _5_BST;
//watch to catch logic:https://www.youtube.com/watch?v=X0oXMdtUDwo&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=127&ab_channel=takeUforward
/*
You're given a binary tree. Your task is to find the size of the largest subtree within this binary tree that also satisfies the properties of a Binary Search Tree (BST). The size of a subtree is defined as the number of nodes it contains.

Note: A subtree of the binary tree is considered a BST if for every node in that subtree, the left child is less than the node, and the right child is greater than the node, without any duplicate values in the subtree.

Examples :

Input: root = [5, 2, 4, 1, 3]
Root-to-leaf-path-sum-equal-to-a-given-number-copy
Output: 3
Explanation:The following sub-tree is a BST of size 3
Balance-a-Binary-Search-Tree-3-copy
Input: root = [6, 7, 3, N, 2, 2, 4]

Output: 3
Explanation: The following sub-tree is a BST of size 3:

Constraints:
1 ≤ number of nodes ≤ 105
1 ≤ node->data ≤ 105
 */
public class _13_maxSizeBST {

    public static void main(String[] args) {
        
    }

   
    static void bruteFunc(TreeNode root,int max[]){//O(n^2)---O(n)
        //call like: int max[]={1};
        // bruteFunc(root, max);
        // return(max[0]);
        if(root==null) return ;
        bruteFunc(root.left,max);
        if(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE)){
            max[0]=Math.max(max[0],countTreeNodes(root.left)+countTreeNodes(root.right)+1);
        }
        bruteFunc(root.right,max);
    }
    static boolean isBST(TreeNode root,int min,int max){
        if(root==null) return true;
        if(root.val>=max || root.val<=min) return false;
        boolean leftSideIsSmaller=isBST(root.left,min,root.val);
        boolean rightSideIsLarger=isBST(root.right,root.val,max);
        return (leftSideIsSmaller&&rightSideIsLarger);
    }

    static int countTreeNodes(TreeNode root){
        if(root==null) return 0;
        return (countTreeNodes(root.left)+countTreeNodes(root.right)+1);
    }



    //---------optimal:o(n)--O(1)
    //we will do DFS (move from bottom to top by using post order(left,right,root)),, where in at each node, instaed of just ptr,right,left,& val, we will store TreeNode, mi_till_that_Node and max_till_that_Node as well

    public static Triplet optimalFunc(TreeNode root, int ans[]) {
        if (root == null) {
            return new Triplet(null, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        // Post-order traversal
        Triplet left = optimalFunc(root.left, ans);
        Triplet right = optimalFunc(root.right, ans);

        if (left.max_Till_me < root.val && root.val < right.min_Till_Me) {
            int newMin = Math.min(root.val, left.min_Till_Me);
            int newMax = Math.max(root.val, right.max_Till_me);
            int newMaxNumber = left.nNodesInMySubtree + right.nNodesInMySubtree + 1;
            ans[0] = Math.max(ans[0], newMaxNumber); // Update the global maximum
            return new Triplet(root, newMin, newMax, newMaxNumber);
        }

        // If the current subtree is not a BST, propagate the maximum node count seen so far
        return new Triplet(null, Integer.MIN_VALUE, Integer.MAX_VALUE, 
                           Math.max(left.nNodesInMySubtree, right.nNodesInMySubtree));
    }
}

class Triplet {
    TreeNode root;
    int min_Till_Me;
    int max_Till_me;
    int nNodesInMySubtree;

    public Triplet(TreeNode root, int min, int max, int nNodes) {
        this.root = root;
        this.min_Till_Me = min;
        this.max_Till_me = max;
        this.nNodesInMySubtree = nNodes;
    }
}