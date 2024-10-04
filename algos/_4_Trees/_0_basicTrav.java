package _4_Trees;
import java.util.*;
public class _0_basicTrav {
    public void preOrderTraverse(TreeNode treeRoot) {//root--left--right
        if (treeRoot != null) {
            System.out.println(treeRoot.val);
            preOrderTraverse(treeRoot.left);
            preOrderTraverse(treeRoot.right);
        }
    }
    public void iterativePreOrder(TreeNode root){//root--left--right
        //https://www.youtube.com/watch?v=Bfqd8BsPVuw&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=81&ab_channel=takeUforward
        if (root==null) {
            return;
        }
        Stack<TreeNode>st=new Stack<>();//LIFO
        st.push(root);
        while (!st.isEmpty()) {
            root=st.pop();
            System.out.println(root.val);
            if (root.right!=null) {
                st.push(root.right);
            }
            if (root.left!=null) {
                st.push(root.left);
            }
        }
    }


    public void inOrderTraverse(TreeNode treeRoot) {//left--root--right
        if (treeRoot != null) {
            inOrderTraverse(treeRoot.left);
            System.out.println(treeRoot.val);
            inOrderTraverse(treeRoot.right);
        }
    }
    public void iterativeInOrder(TreeNode root){//left--root--right
        //https://www.youtube.com/watch?v=lxTGsVXjwvM&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=82&ab_channel=takeUforward
        if (root==null) {
            return;
        }
        //if node is not null,psh t to statck and move left..if it is null(reached left most)->if(st.isEmpty), we r done break...else move right by printing it
        Stack<TreeNode>st=new Stack<>();//LIFO
        TreeNode temp=root;
        while (!st.isEmpty()) {
            if (temp!=null) {
                st.push(temp);
                temp=temp.left;
            }else{
                if (st.isEmpty()) {
                    break;
                }
                temp=st.pop();
                System.out.println(temp.val);
                temp=temp.right;
            }
        }
        
    }




    public void postOrderTraverse(TreeNode treeRoot) {//left--right--root
        if (treeRoot != null) {
            postOrderTraverse(treeRoot.left);
            postOrderTraverse(treeRoot.right);
            System.out.println(treeRoot.val);
        }
    }
    public void iterativePostOrderUsing_2_Stacks(TreeNode root){
        if (root==null) {
            return;
        }
        Stack<TreeNode>st1=new Stack<>();
        Stack<TreeNode>st2=new Stack<>();

        st1.push(root);

        while (!st1.isEmpty()) {
            root=st1.pop();
            st2.add(root);
            if (root.left!=null) {
                st1.push(root.left);
            }
            if (root.right!=null) {
                st1.push(root.right);
            }
        }
        while (!st2.isEmpty()) {
            System.out.println(st2.pop().val);
        }
       
    }
    public void iterativePostOrderUsing_1_Stack(TreeNode root){
        TreeNode curr=root;
        Stack<TreeNode>st=new Stack<>();

        while (curr!=null||!st.isEmpty()) {
            if (curr!=null) {
                st.push(curr);
                curr=curr.left;
            }else{
                TreeNode temp=st.peek().right;
                if (temp==null) {
                    temp=st.pop();
                    System.out.println(temp.val);

                    while (!st.isEmpty()&&temp==st.peek().right) {
                        temp=st.peek();
                        st.pop();
                        System.out.println(temp.val);
                    }
                }else{
                    curr=temp;
                }
            }
        }

    }

    // IMP:
    public void displayTree(TreeNode root) {
        Stack<TreeNode> globalStack = new Stack<>();
        globalStack.push(root);
        // lets allow max of 6 lines of tree is 2^6 nodes max ie 32 nodes
        double nBlanks = Math.pow(2, 5);
        boolean isRowEmpty = false;
        System.out.println("..........................................................");
        while (isRowEmpty == false) {
            Stack<TreeNode> localStack = new Stack<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(' ');
            }
            while (globalStack.isEmpty() == false) {
                TreeNode temp = (TreeNode) globalStack.pop();
                if (temp != null) {
                    System.out.println(temp.val);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < nBlanks * 2 - 2; i++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("..........................................................");
    }
    // IMP:
    public  void levelOrderTraversaal(TreeNode root){
        //watch:https://www.youtube.com/watch?v=hXAqTO7VqUQ&ab_channel=DineshVaryani
        if (root==null) {
            System.out.println("Empty Tree");
            return;
        }
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode temp=q.poll();//retrive and remove first person in queue(FIFO)
            System.out.println(temp.val);
            if (temp.left!=null) {
                q.offer(temp.left);
            }
            if (temp.right!=null) {
                q.offer(temp.right);
            }
        }
        
    }

    public List<List<Integer>> levelByLevel(TreeNode root){
        if (root==null) {
            return new ArrayList<>();
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
        return ans;
    }
}
