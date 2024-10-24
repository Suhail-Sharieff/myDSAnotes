package _5_BST;
/*
You are given a BST(Binary Search Tree) with n number of nodes and value x. your task is to find the greatest value node of the BST which is smaller than or equal to x.
Note: when x is smaller than the smallest node of BST then returns -1.

Example:

Input:
n = 7               2
                     \
                      81
                    /     \
                 42       87
                   \       \
                    66      90
                   /
                 45
x = 87
Output:
87
Explanation:
87 is present in tree so floor will be 87.
Example 2:

Input:
n = 4                     6
                           \
                            8
                          /   \
                        7       9
x = 11
Output:
9
Your Task:-
You don't need to read input or print anything. Complete the function floor() which takes the integer n and BST and integer x returns the floor value.

Constraint:
1 <= Noda data <= 109
1 <= n <= 105

Expected Time Complexity: O(height of tree)
Expected Space Complexity: O(height of tree)
 */
public class _2_floor {
    public static int floor(TreeNode root,int x){
        if(root==null){
            return -1;
        }
        int ans=-1;
        while(root!=null){
            if(root.val==x){
                return x;
            }
            if(root.val<x){
                ans=root.val;
                root=root.right;
            }else{
                root=root.left;
            }
        }
        return ans;
    }
}
