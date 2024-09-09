package _4_Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//pre-->root,left,right
//inorder-->left,root,right
//post-->left,right,root
public class _1_oneShotTraversal {//using this method we would traverse throught all nodes and at one go, we would traverse via all types of traversals
    //https://www.youtube.com/watch?v=1i8E4mJ9x3o&ab_channel=Yogesh%26Shailesh%28CodeLibrary%29


    //we would have 3 states 1,2,3, initially stack has root with state 1
    //if the state of topPair in stack is 1, put it to preOrder,increase stateBy1,push left(if not null)
    //if the state of topPair in stack is 2, put it to inOrder,increase stateBy1,push rightIif not null
    //if the state of topPair in stack is 2, put it to postOrder,pop

    public static void main(String[] args) {
        //constructing sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        oneShotTrav(root);
    }

    public static void oneShotTrav(TreeNode root){
        Stack<pair>st=new Stack<>();
        List<Integer>preOrder=new ArrayList<>();
        List<Integer>inOrder=new ArrayList<>();
        List<Integer>postOrder=new ArrayList<>();


        st.push(new pair(root, 1));

        while (!st.isEmpty()) {

            pair topPair=st.peek();
            TreeNode node=topPair.node;
            int state=topPair.state;

            if (state==1) {
                preOrder.add(node.val);
                topPair.state++;
                if (node.left!=null) {
                    st.push(new pair(node.left, 1));
                }
            }else if(state==2){
                inOrder.add(node.val);
                topPair.state++;
                if (node.right!=null) {
                    st.push(new pair(node.right, 1));
                }
            }else if(state==3){
                postOrder.add(node.val);
                st.pop();
            }
        }

        System.out.println("Pre-order: " + preOrder);
        System.out.println("In-order: " + inOrder);
        System.out.println("Post-order: " + postOrder);
        
    }
    

}
class pair{
    public TreeNode node;
    public int state;
    public pair(TreeNode node,int state){
        this.node=node;
        this.state=state;
    }
}
