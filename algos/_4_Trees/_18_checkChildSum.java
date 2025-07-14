package _4_Trees;
import java.util.*;
/*
https://www.youtube.com/watch?v=fnmisPM6cVo&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=101&ab_channel=takeUforward




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


   //THOUGH THE QUESTION CLEARLY SAYS THAT WE R NOT SUPPOSED TO DECREASE ANY NODE' VALUE, BUT JUST ACN INVREASE, I HAVE INCLUDED ALL 3 CASSES
   //


   //CASE 1: u just have to obtain a binary tree whcih follws child sum property, u can both reduce or increase any node's value to achive the same
   //algorithm: start from root,make and go its children(both left and right) equal to its value till down...,then backtrack upside my making parent as sum of its children
   public static void can_incr_decr_both(TreeNode root){
    if (root==null) {
      return;
    }
    if (root.left!=null) {
      if (root.left.val!=root.val) {
        root.left.val=root.val;
      }
    }
    if (root.right!=null) {
      if (root.right.val!=root.val) {
        root.right.val=root.val;
      }
    }
    
    can_incr_decr_both(root.left);
    can_incr_decr_both(root.right);

    if (root.left!=null&&root.right!=null) {
      root.val=root.left.val+root.right.val;
    }else if(root.left!=null){
      root.val=root.left.val;
    }else if(root.right!=null){
      root.val=root.right.val;
    }
   }


   //CASE 2: u just have to obtain a binary tree whcih follws child sum property,but  u can ONLY increase any node's value to achive the same,for ex:
   /*
    
             50
           /     \     
         /         \
       7             2
     / \             /\
   /     \          /   \
  3        5      1      120

  notice that on reaching 120 which is more than 50, u cant make 120 to 50 as per condition given
    */
   //algorithm: 
   //brute: so to satsify the above condition, we can assign max value in the tree to root's value and then do normal can_incr_decr_both algortithm, that time u can see that no such problems will occur (in above example first make root.val ie 50 as 120 ie max value in tree ) and then do normal flow
   /* static int brute(BinaryTreeNode<Integer> root){
        if(root==null) return 0;
        if(root.left==null && root.right==null) return root.data=1000000;(assign max val)
        int l=brute(root.left);
        int r=brute(root.right);
        root.data=l+r;
        return root.data;
    }
 */
   //optimal:see the below algorithm
   public static void can_incr_only(TreeNode root){
    if(root==null) return;
        int sum=0;
        if(root.left!=null) sum+=root.left.val;
        if(root.right!=null) sum+=root.right.val;
        if(sum>=root.val){
            root.val=sum;
        }
        else{
            //left right milkar bhi nahi beat karre
            if(root.left!=null) root.left.val=root.val;//MISTAKE: assigned as sum inplace of root.val
            if(root.right!=null) root.right.val=root.val;
        }
        can_incr_only(root.left);
        can_incr_only(root.right);
        //-the below is executed while backtracking
        sum=0;
        if(root.left==null && root.right==null) return;
        if(root.left!=null) sum+=root.left.val;
        if(root.right!=null) sum+=root.right.val;
        root.val=sum;

   }





   public static void main(String[] args) {
    TreeNode root=TreeNode.constructTree(new Integer[]{5,23,34,45,56,78,79});
    displayTree_level(root);
    // can_incr_decr_both(root);
    // displayTree_level(root);
    
    //brute force for only increse:
    /*
     int max= findMaxEleInTree(root);
     root.val=max;
     can_incr_decr_both(root);
     *
     */

     //optimal for only increase:
      can_incr_only(root);
      displayTree_level(root);
   }





   public static void displayTree_level(TreeNode root){
    if (root==null) {
      System.out.println("Empty");
      return;
    }
    Queue<TreeNode>q=new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      int size=q.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr=q.poll();
        System.out.print(curr.val+" ");
        if (curr.left!=null) {
          q.offer(curr.left);
        }
        if (curr.right!=null) {
          q.offer(curr.right);
        }
      }
      System.out.println();
    }
   }
   public static int findMaxEleInTree(TreeNode root){
    if (root==null) {
      return 0;
    }
    int maxLeft=findMaxEleInTree(root.left);
    int maxRight=findMaxEleInTree(root.right);
    return Math.max(Math.max(maxLeft, maxRight), root.val);
   }
}