package _5_BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _4_delete {
    public static void main(String[] args) {
        TreeNode root=TreeNode.constructTree(new Integer[]{5,3,6,2,4,null,7});
        int key=3;
        TreeNode.displayLevelByLevel(func(root, key));
        
        
    }
    public static TreeNode constructTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while (!queue.isEmpty() && index < arr.length) {
            TreeNode current = queue.poll();

            // Assign left child
            if (index < arr.length && arr[index] != null) {
                current.left = new TreeNode(arr[index]);
                queue.add(current.left);
            }
            index++;

            // Assign right child
            if (index < arr.length && arr[index] != null) {
                current.right = new TreeNode(arr[index]);
                queue.add(current.right);
            }
            index++;
        }

        return root;
    }

    public static TreeNode func(TreeNode root,int key){
        TreeNode lca=lca(root, key);
       
            List<Integer>li=new ArrayList<>();
            Queue<TreeNode>q=new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode curr=q.poll();
                li.add(curr.val);
                if (curr.left!=null) {
                    q.offer(curr.left);
                }
                if (curr.right!=null) {
                    q.offer(curr.right);
                }
            }
            Integer arr[]=new Integer[li.size()];
            li.toArray(arr);
            if (lca!=null) {
                // for (Integer integer : arr) {
                //     if (integer==key) {
                //         integer=lca.val;
                //     }
                //     if (integer==lca.val) {
                //         integer=null;
                //     }
                // }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i]==key) {
                        arr[i]=lca.val;
                    }
                    if (arr[i]==lca.val) {
                        arr[i]=null;
                    }
                }
            }
            
            return TreeNode.constructTree(arr);

    }


    public static TreeNode lca(TreeNode root,int val){
        TreeNode curr=root;
        TreeNode ans=null;
        while (curr!=null) {
            if (curr.val>val) {
                ans=curr;
                curr=curr.left;
            }else{
                curr=curr.right;
            }
        }
        return ans;
    }
}
