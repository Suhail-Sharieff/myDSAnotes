/*
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”


Given a Binary Tree with all unique values and two nodes value, n1 and n2. The task is to find the lowest common ancestor of the given two nodes. We may assume that either both n1 and n2 are present in the tree or none of them are present.

LCA: It is the first common ancestor of both the nodes n1 and n2 from bottom of tree.

Example 1:

Input:
n1 = 2 , n2 = 3  
       1 
      / \ 
     2   3
Output: 1
Explanation:
LCA of 2 and 3 is 1.
Example 2:

Input:
n1 = 3 , n2 = 4
           5    
          /    
         2  
        / \  
       3   4
Output: 2
Explanation:
LCA of 3 and 4 is 2. 
Example 3:

Input:
n1 = 5 , n2 = 4
           5    
          /    
         2  
        / \  
       3   4
Output: 5
Explanation:
LCA of 5 and 4 is 5. 
Your Task:
You don't have to read, input, or print anything. Your task is to complete the function lca() that takes nodes, n1, and n2 as parameters and returns the LCA node as output. 

Expected Time Complexity:O(N).
Expected Auxiliary Space:O(Height of Tree).
 */


 package _4_Trees;


/**
  * _16_lowestCommonAncestor
  */
 public class _16_lowestCommonAncestor {
 
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode n1=new TreeNode(5);
        TreeNode n2=new TreeNode(4);

       System.out.println(optimal(root, n1, n2).val);
    }


    //themain idea is to move down until we find only one of nodes matching n1 or n2 first, we return it(n1 or n2 whichever is found), 
    // found some matching-->return that
    //matching+null-->return matching
    //null+null-->return null
    //matching+matching--->we got our anwer
    public static TreeNode optimal(TreeNode root,TreeNode p,TreeNode q){//O(n)--O(1)

       if(root==null) return null;
        if(root.val==p.val || root.val==q.val) return root;

        //base idea: if no p.val or q.val matches on left, mve right, and vice cersa

        TreeNode hasLeft=optimal(root.left,p,q);
        TreeNode hasRight=optimal(root.right,p,q);

        if(hasLeft==null) return hasRight;
        else if(hasRight==null) return hasLeft;

        return  root;



    }

 }


 class BruteForce {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       TreeNode[] ans={null};
       func(root,p,q,ans);
        return ans[0];
    }

    public void func(TreeNode root,TreeNode p,TreeNode q,TreeNode[] ans){
        if(root==null) return ;
        boolean parr[]={false};
        boolean qarr[]={false};

        trav(root,p.val,parr);
        trav(root,q.val,qarr);

        if(parr[0] && qarr[0]){
            //move deeper
            // System.out.println("YEP "+" "+root.val);
            ans[0]=root;
             func(root.left,p,q,ans);
             func(root.right,p,q,ans);
        }
    }

    public void trav(TreeNode root,int val,boolean arr[]){
       if(root==null) return;
       trav(root.left,val,arr);
        if(root.val==val){
        arr[0]=true;
        return;
       }
       trav(root.right,val,arr);

    }
}