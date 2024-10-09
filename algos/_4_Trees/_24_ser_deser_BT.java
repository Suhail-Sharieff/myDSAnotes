package _4_Trees;
//https://www.youtube.com/watch?v=-YbXySKJsX8&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=108&ab_channel=takeUforward
import java.util.*;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.The tree MAY CONTAIN -ve numbers as well as duplicate values as well

 

Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 */

public class _24_ser_deser_BT {
    public static void main(String[] args) {

        TreeNode root = TreeNode.constructTree(new Integer[] { 1, 2, 3, null, null, 4, 5 });

        String s=serialize(root);
        TreeNode tree=deserialize(s);

        System.out.println(s);
        TreeNode.displayLevelByLevel(tree);

    }

    public static  String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
            Queue<TreeNode>q=new LinkedList<>();
            q.offer(root);
            StringBuilder sb=new StringBuilder();
            while (!q.isEmpty()){
                TreeNode curr=q.poll();
                if(curr==null){
                    sb.append("null ");
                    continue;
                }
                sb.append(curr.val+" ");
                q.offer(curr.left);
                q.offer(curr.right);
            }
            return sb.toString();
        }
    
        // Decodes your encoded data to tree.
        public  static TreeNode deserialize(String data) {//IMP
            if(data.length()==0){
                return null;
            }
           Queue<TreeNode> q = new LinkedList<>();
            String split[] = data.split(" ");
            TreeNode root=new TreeNode(Integer.parseInt(split[0]));
            q.offer(root);
    
            for (int i = 1; i < split.length; i++) {
               TreeNode curr=q.poll();
               if (!split[i].equals("null")) {
                TreeNode leftNode=new TreeNode(Integer.parseInt(split[i]));
                curr.left=leftNode;
                q.offer(leftNode);
               }
               if (!split[++i].equals("null")) {//after left also we have null means its a leaf so we increment i
                TreeNode rightNode=new TreeNode(Integer.parseInt(split[i]));
                curr.right=rightNode;
                q.offer(rightNode);
               }
            }
            return root;
        }



//--------------------------THE CODE WHICH I WROTE FIRST IS GIVEN BELOW PASSED 43/54 TC-----------------------
//ides used: in a single string i appended inorder & preorder in format "inorder break preoder", and then performed operations. Used isVisited to handle duplicate node values which may occur in Tree




    // public static String serialize(TreeNode root) {
    // if(root==null){
    // return "";
    // }
    // StringBuilder sb=new StringBuilder();
    // inorder(root,sb);
    // sb.append(" break ");
    // preorder(root, sb);

    // return sb.toString();
    // }

    // // Decodes your encoded data to tree.
    // public static TreeNode deserialize(String data) {
    // if(data.length()==0){
    // return null;
    // }
    // String split[]=data.split(" ");

    // String inorStr[]=split[0].split(" ");
    // String preorStr[]=split[2].split(" ");

    // int inorder[]=new int[inorStr.length];
    // int preorder[]=new int[preorStr.length];

    // boolean isVisited[]=new boolean[inorder.length];

    // for (int i = 0; i < preorStr.length; i++) {
    // inorder[i]=Integer.parseInt(inorStr[i]);
    // preorder[i]=Integer.parseInt(preorStr[i]);
    // }

    // return buildTreeFromArr(inorder,preorder,0,inorder.length-1,new
    // int[]{0},isVisited);
    // }

    // public static void inorder(TreeNode root,StringBuilder sb){//left,root,right
    // if(root==null){
    // return ;
    // }
    // inorder(root.left,sb);
    // sb.append(root.val+" ");
    // inorder(root.right,sb);

    // }
    // public static void preorder(TreeNode root,StringBuilder sb){//root,left,right
    // if (root==null) {
    // return;
    // }
    // sb.append(root.val+" ");
    // preorder(root.left,sb);
    // preorder(root.right,sb);
    // }

    // public static int findPosOf_e_in_inorder(int inorder[],int e,boolean
    // isVisited[]){
    // for (int i = 0; i < inorder.length; i++) {
    // if (e==inorder[i]&&!isVisited[i]) {
    // return i;
    // }
    // }
    // return -1;
    // }
    // public static TreeNode buildTreeFromArr(int inOrder[],int preOrder[],int
    // inStart,int inEnd,int preIdx[],boolean isVisited[]){
    // if (preIdx[0]>=preOrder.length||inStart>inEnd) {
    // return null;
    // }
    // int elemnt=preOrder[preIdx[0]++];
    // TreeNode currRoot=new TreeNode(elemnt);
    // int posOfCurrRootInInorder=findPosOf_e_in_inorder(inOrder, elemnt,isVisited);
    // isVisited[posOfCurrRootInInorder]=true;

    // currRoot.left = buildTreeFromArr(inOrder, preOrder, inStart,
    // posOfCurrRootInInorder-1, preIdx,isVisited);
    // currRoot.right = buildTreeFromArr(inOrder, preOrder,
    // posOfCurrRootInInorder+1, inEnd, preIdx,isVisited);

    // return currRoot;
    // }
}
