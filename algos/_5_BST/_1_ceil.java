package _5_BST;
/*
Given a BST and a number X, find Ceil of X.
Note: Ceil(X) is a number that is either equal to X or is immediately greater than X.

If Ceil could not be found, return -1.

Example 1:

Input:
      5
    /   \
   1     7
    \
     2 
      \
       3
X = 3
Output: 3
Explanation: We find 3 in BST, so ceil
of 3 is 3.
Example 2:

Input:
     10
    /  \
   5    11
  / \ 
 4   7
      \
       8
X = 6
Output: 7
Explanation: We find 7 in BST, so ceil
of 6 is 7.
Your task:
You don't need to read input or print anything. Just complete the function findCeil() to implement ceil in BST which returns the ceil of X in the given BST.

Constraints:
1 <= Number of nodes <= 105
1 <= Value of nodes<= 105


 */
public class _1_ceil {

    public static void main(String[] args) {
        //analyz code using:
        TreeNode root=TreeNode.constructTree(new Integer[]{2 ,1, 39, null, null, 17, 53 ,4 ,20 ,47 ,59 ,3,7, 18, 29, 41, 51,26});
        System.out.println(ceil(root, 28));
            
    }

    public static int ceil(TreeNode root,int key){
        if (root == null) return -1;
        // Code here
        int ans=-1;
        while(root!=null){
            if(root.val==key){
                return key;
            }
            if(root.val<key){//search on right to find greater value
                root=root.right;
            }else{//oh, the curr root's value i greater than key, may be it can be answer, so assume it as answer and move left to check for smaller values
                ans=root.val;
                root=root.left;
            }
        }
        
        return ans;
    }
}
