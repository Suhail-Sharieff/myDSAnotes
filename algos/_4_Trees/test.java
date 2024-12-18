package _4_Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



class Lol{
    TreeNode node;
    Boolean isVisited;
    public Lol(TreeNode node,Boolean isVisited){
        this.node=node;
        this.isVisited=isVisited;
    }
    @Override
    public String toString() {
       return (node.val+"");
    }
}


public class test{
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{1, 2, 3, null, null, 4, 5 });

        // String s=(serialize(root));
        // System.out.println("serialized String: "+s);
        // deseriaLize(s);
        // TreeNode.displayLevelByLevel(deseriaLize(s));
level(root);

    }

    public static void level(TreeNode root){
        if (root==null) {
            return;
        }
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        List<List<Integer>>ans=new ArrayList<>();
        while (!q.isEmpty()) {
            int s=q.size();
            List<Integer>lev=new ArrayList<>();
            for (int i = 0; i <s; i++) {
                TreeNode curr=q.poll();
                if (curr==null) {
                    lev.add(null);
                    continue;
                }
                lev.add(curr.val);
                // if (curr.left!=null) {
                    q.offer(curr.left);
                // }
                // if (curr.right!=null) {
                    q.offer(curr.right);
                // }
            }
            ans.add(lev);
        }
        System.out.println(ans);
    }

    public static void getPreOrder(TreeNode root,List<Lol>preOrder){
        if (root==null) {
            return;
        }
        //root,left,right
        preOrder.add(new Lol(root, false));
        getPreOrder(root.left, preOrder);
        getPreOrder(root.right, preOrder);
    }
    public static void getInorder(TreeNode root,List<Lol>inorder){
        if (root==null) {
            return;
        }
        getInorder(root.left, inorder);
        inorder.add(new Lol(root, false));
        getInorder(root.right, inorder);
    }



    public static String serialize(TreeNode root){
        if (root==null) {
            return new String();
        }
        List<Lol>inorder=new ArrayList<>();getInorder(root, inorder);
        List<Lol>preorder=new ArrayList<>();getPreOrder(root, preorder);
        String ins=inorder.toString().replace(",", "").replace("[", "").replace("]", "").trim();
        String pres=preorder.toString().replace(",", "").replace("[", "").replace("]", "").trim();

        return (ins+"/"+pres);
    }
    public static TreeNode deseriaLize(String s){
        String part[]=s.split("/");
        List<Lol>inorder=new ArrayList<>();
        List<Lol>preorder=new ArrayList<>();
        for(String e : part[0].split(" ")){
            if (!e.isEmpty()) {
                inorder.add(new Lol(new TreeNode(Integer.parseInt(e)), false));
            }
        }
        for(String e : part[1].split(" ")){
            if (!e.isEmpty()) {
                preorder.add(new Lol(new TreeNode(Integer.parseInt(e)), false));
            }
        }
        System.out.println(inorder);
        System.out.println(preorder);
        //now we have inorder and preorder list
        return build(inorder, preorder, 0, inorder.size()-1, new int[]{0});
       
    }   
    public static TreeNode build(List<Lol>inorder,List<Lol>preorder,int inStart,int inEnd,int preIdx[]){
            if (inStart>inEnd || preIdx[0]>=preorder.size()) {
                return null;
            }
            Lol lol=preorder.get(preIdx[0]++);
            TreeNode node=lol.node;
            int val=node.val;
            int idxOfRootInInorder=getIdx(val, inorder);


            TreeNode root=new TreeNode(val);
            root.left=build(inorder, preorder, inStart, idxOfRootInInorder-1, preIdx);
            root.right=build(inorder, preorder, idxOfRootInInorder+1,inEnd, preIdx);

            return root;
    }

    public static int getIdx(int target,List<Lol>inorder){
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i).node.val==target && !inorder.get(i).isVisited) {
                return i;
            }
        }
        return -1;
    }
}