package _4_Trees;
/*

CHILDREN SUM PROPERTY: EVERY NODE'S VALUE IS EQUAL TO SUM OF VALUES OF THEIR LEFT AND RIGHT CHILDEREN (TREAT NULL AS 0) 



You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.

Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.

 

Example 1:


Input: root = [10,4,6]
Output: true
Explanation: The values of the root, its left child, and its right child are 10, 4, and 6, respectively.
10 is equal to 4 + 6, so we return true.
Example 2:


Input: root = [5,3,1]
Output: false
Explanation: The values of the root, its left child, and its right child are 5, 3, and 1, respectively.
5 is not equal to 3 + 1, so we return false.
 

Constraints:

The tree consists only of the root, its left child, and its right child.
-100 <= Node.val <= 100
 */
public class _18_checkChildSum {
    public boolean checkTree(TreeNode root) {
        if(root==null){
            return true;
        }
        if(root.left!=null&&root.right!=null){
            if(root.val==root.left.val+root.right.val){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }



//TYPE 2:
/*


Given a binary tree having n nodes. Check whether all of its nodes have the value equal to the sum of their child nodes. Return 1 if all the nodes in the tree satisfy the given properties, else it return 0.

For every node, data value must be equal to the sum of data values in left and right children. Consider data value as 0 for NULL child.  Also, leaves are considered to follow the property.

Example 1:

Input:
Binary tree
       35
      /   \
     20  15
    /  \  /  \
   15 5 10 5
Output: 
1
Explanation: 
Here, every node is sum of its left and right child.
Example 2:

Input:
Binary tree
       1
     /   \
    4    3
   /  
  5    
Output: 
0
Explanation: 
Here, 1 is the root node and 4, 3 are its child nodes. 4 + 3 = 7 which is not equal to the value of root node. Hence, this tree does not satisfy the given condition.
Your Task:
You don't need to read input or print anything. Your task is to complete the function isSumProperty() that takes the root Node of the binary tree as input and returns 1 if all the nodes in the tree satisfy the following properties, else it returns 0.

Expected Time Complexiy: O(n).
Expected Auxiliary Space: O(Height of the Tree).

Constraints:
1 <= n <= 105
1 <= Data on nodes <= 105
 */
public static int isSumProperty(TreeNode root){
    //base case: every null node holds good,also a leaf holds good
    if(root==null||(root.left==null&&root.right==null)){
        return 1;
    }
    int leftVal=(root.left!=null)?(root.left.val):(0);
    int rightVal=(root.right!=null)?(root.right.val):(0);

    //IMP:
    //only check further if leftVal+rightVal==root.val
    if (leftVal+rightVal==root.val) {
        if (isSumProperty(root.left)==1&&isSumProperty(root.right)==1) {
            return 1;            
        }
    }
    return 0;
 }
 


 ///MOST IMPORTANT PART:
 /*
Given an arbitrary binary tree, convert it to a binary tree that holds Children Sum Property. You can only increment data values in any node (You cannot change the structure of the tree and cannot decrement the value of any node). 
For example, the below tree doesn’t hold the children sum property, convert it to a tree that holds the property.

             50
           /     \     
         /         \
       7             2
     / \             /\
   /     \          /   \
  3        5      1      30


Let us run the algorithm for the given example. 

First convert the left subtree (increment 7 to 8). 
 

             50
           /     \     
         /         \
       8             2
     / \             /\
   /     \          /   \
  3        5      1      30
Then convert the right subtree (increment 2 to 31)

          50
        /    \     
      /        \
    8            31
   / \           / \
 /     \       /     \
3       5    1       30
Now convert the root, we have to increment left subtree for converting the root. 

          50
        /    \     
      /        \
    19           31
   / \           /  \
 /     \       /      \
14      5     1       30
Please note the last step – we have incremented 8 to 19, and to fix the subtree we have incremented 3 to 14.
  */

  /*
Algorithm: Traverse the given tree in post order to convert it, i.e., first change left and right children to hold the children sum property then change the parent node. 
Let difference between node’s data and children sum be diff. 

diff = node’s children sum - node’s data  

If diff is 0 then nothing needs to be done. 

If diff > 0 ( node’s data is smaller than node’s children sum) increment the node’s data by diff.

If diff < 0 (node’s data is greater than the node’s children sum) then increment one child’s data. We can choose to increment either left or right child if they both are not NULL. Let us always first increment the left child. Incrementing a child changes the subtree’s children sum property so we need to change left subtree also. So we recursively increment the left child. If left child is empty then we recursively call increment() for right child.
   */
}