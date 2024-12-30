package _5_BST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

 

Example 1:


Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:


Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1
 

Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?

 */
public class _12_restore_BST {



    //brute force:first do normal inorder traversal of the tree, compare it with sorted list, obvously the 2 elements to swap will mismatch with sorted array, just traverse once again and swap if toSwap elements r found
    //o(nlogn)--O(n)
    public void brute(TreeNode root) {
        List<Integer>li=new ArrayList<>();
        inor(root,li);
        List<Integer>sorted=new ArrayList<>(li);
        Collections.sort(sorted);
        List<Integer>toSwap=new ArrayList<>();
        for(int i=0;i<li.size();i++){
            if(li.get(i)!=sorted.get(i)){
                toSwap.add(li.get(i));
            }
        }
        System.out.println(li);
        System.out.println(sorted);
        System.out.println(toSwap);
        if(!toSwap.isEmpty()) change(toSwap.get(0),toSwap.get(1),root);
    }

    public void change(int x,int y,TreeNode root){
        if(root==null) return;
        if(root.val==x || root.val==y){root.val^=x^y;}
        change(x,y,root.left);
        change(x,y,root.right);
    }

    public void inor(TreeNode root,List<Integer>li){
        if(root==null) return;
        inor(root.left,li);
        li.add(root.val);
        inor(root.right,li);
    }






    //---------------optimal:o(n)--O(n)
    /*
    imagine if it were array and u need to do same operation,ie swap 2 mismatched elements,this was how u must be doing, let us call first and second as the 2 elemnts we need to swap respectively
    int first=-1,second=-1,prev=Integer.MIN_VALUE
    for(int i=0;i<Sorted_arr.length;i++){
        if(arr[i]<prev){// this is the violation condition
            if(first==-1) f{irst=prev;}
            second=prev;
        }
        prev=arr[i]
    }
    swap(first,second);


    Now similary u do that operation checking in inorder traversal
     */
    public void optimalFunc(TreeNode root) {
        TreeNode firstValToSwap[]={null},secondValToSwap[]={null},prevVal[]={new TreeNode(Integer.MIN_VALUE)};
        optimal(root, firstValToSwap, secondValToSwap, prevVal);
        int xor=firstValToSwap[0].val^secondValToSwap[0].val;
        firstValToSwap[0].val^=xor;
        secondValToSwap[0].val^=xor;
    }
    public static void optimal(TreeNode root,TreeNode firstValToSwap[],TreeNode secondValToSwap[],TreeNode prevVal[]){
        if(root==null) return;
        optimal(root.left, firstValToSwap, secondValToSwap, prevVal);

        //operation amidst inorder
        if(root.val<prevVal[0].val){//we got violation where prev value is more than currRoot, ie swap that root with prev ie first is prev, second is curr

            if(firstValToSwap[0]==null){
                firstValToSwap[0]=prevVal[0];
            }
            secondValToSwap[0]=root;
            

        }
        prevVal[0]=root;
        optimal(root.right, firstValToSwap, secondValToSwap, prevVal);
    }
    
    
}
