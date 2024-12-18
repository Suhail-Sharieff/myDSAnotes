package _4_Trees;




import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    // Method to construct the binary tree from a level-order array
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

    // Display node value
    public void displayNode(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
        } else {
            System.out.print("null ");
        }
    }

    // Pre-order traversal to display the tree
    public void tr(TreeNode root) {//root,left,right
        if (root == null) {
            System.out.print(" Empty ");
            return;
        }
        displayNode(root);
        tr(root.left);
        tr(root.right);
    }

    // public static void main(String[] args) {
    //     Integer[] arr = {-10, 9, 20, null, null, 15, 7}; // Example input
    //     TreeNode root = TreeNode.constructTree(arr);

    //     // Display the constructed tree
    //     TreeNode treeNode = new TreeNode(0); // Create a dummy node for calling the traversal method
    //     treeNode.tr(root);
    // }

    public static void displayLevelByLevel(TreeNode root){
        if (root==null) {
            return ;
        }
        Queue<TreeNode>q=new LinkedList<>();
        List<List<Integer>>ans=new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int thatLevelSize=q.size();
            List<Integer>each=new ArrayList<>();
            for (int i = 0; i < thatLevelSize; i++) {
                TreeNode temp=q.poll();
                each.add(temp.val);
                if (temp.left!=null) {
                    q.offer(temp.left);
                }
                if (temp.right!=null) {
                    q.offer(temp.right);
                }
            }
            ans.add(each);
        }
        for (List<Integer> list : ans) {
            System.out.println(list);
        }
    }
    public static void displayLevelByLevelWithNulls(TreeNode root){
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

    public static void main(String[] args) {
        System.out.println("Bismillah");
    }
}
