package _4_Trees;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{1,2,3,null,5});
        List<String>ans=new ArrayList<>();


        StringBuilder sb=new StringBuilder();

        inorder(root,sb);

        System.out.println(sb);

    }
    


    public static void inorder(TreeNode root,StringBuilder sb){
        if(root==null){
            System.out.println(sb);
            // sb=new StringBuilder();
            return;
        }
        if (root.left==null&&root.right==null) {
            sb.append(root.val+"->");
            // sb=new StringBuilder();
            return;
        }
        sb.append(root.val+"->");
        inorder(root.left,sb);
        inorder(root.right,sb);

    }
}