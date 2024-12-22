package _5_BST;




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
    @Override
    public String toString() {
        try {
            return (this.val+"");
        } catch (Exception e) {
            return "null";
        }
    }

    public static TreeNode constructBST(Integer arr[]){
        if (arr.length==0) {
            return null;
        }
        TreeNode root=new TreeNode(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            insertIntoBST(root, arr[i]);
        }

        return root;

    }

    private static void  insertIntoBST(TreeNode root,int target){
        TreeNode temp=root;
        while (true) {
            if (temp.val>=target) {
                //if left is null insert there
                if (temp.left==null) {
                    temp.left=new TreeNode(target);
                    break;
                }else{
                    temp=temp.left;
                }
            }else{
                if (temp.right==null) {
                    temp.right=new TreeNode(target);
                    break;
                }else{
                    temp=temp.right;
                }
            }
        }
    }

    public static TreeNode deleteFromBST(TreeNode root,int target){
        if (root==null) {
            return null;
        }
        if (root.val==target) {
            return shiftEverythingOnLeftAndReturnLeft(root);
        }
        TreeNode temp=root;
        while (root!=null) {
            if (root.val>target) {
                if (root.left!=null && root.left.val==target) {
                    root.left=shiftEverythingOnLeftAndReturnLeft(root.left);
                    break;
                }else{
                    root=root.left;
                }
            }else{
                if (root.right!=null && root.right.val==target) {
                    root.right=shiftEverythingOnLeftAndReturnLeft(root.right);
                    break;
                }else{
                    root=root.right;
                }
            }
        }
        return temp;
    }
    private static TreeNode getRightMostOnLeftOf(TreeNode root){
        if (root.right==null) {
            return root;
        }
        return getRightMostOnLeftOf(root.right);
    }
    private static TreeNode shiftEverythingOnLeftAndReturnLeft(TreeNode targetNode){
        if (targetNode.left==null) {
            return targetNode.right;
        }else if(targetNode.right==null){
            return targetNode.left;
        }
        TreeNode leftMostRightOfTarget=getRightMostOnLeftOf(targetNode.left);
        TreeNode rightChildOfTarget=targetNode.right;
        leftMostRightOfTarget.right=rightChildOfTarget;
        return targetNode.left;
    }
}
