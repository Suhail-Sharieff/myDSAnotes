package algos._4_Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//pre-->root,left,right
//inorder-->left,root,right
//post-->left,right,root
public class _1_oneShotTraversal {//using this method we would traverse throught all nodes and at one go, we would traverse via all types of traversals
    //https://www.youtube.com/watch?v=1i8E4mJ9x3o&ab_channel=Yogesh%26Shailesh%28CodeLibrary%29


    //we would have 3 states 1,2,3, initially stack has root with state 1
    //if the state of topPair in stack is 1, put it to preOrderpush,increase stateBy1,push left(if not null)
    //if the state of topPair in stack is 2, put it to inOrder,push,increase stateBy1,move right
    //if the state of topPair in stack is 2, put it to postOrder,pop,move left

    public static void main(String[] args) {
        //constructing sample tree
        treeNode root = new treeNode(1);
        root.leftChild = new treeNode(2);
        root.rightChild = new treeNode(3);
        root.leftChild.leftChild = new treeNode(4);
        root.leftChild.rightChild = new treeNode(5);
        root.rightChild.leftChild = new treeNode(6);
        root.rightChild.rightChild = new treeNode(7);

        oneShotTrav(root);
    }

    public static void oneShotTrav(treeNode root){
        Stack<pair>st=new Stack<>();
        List<Integer>preOrder=new ArrayList<>();
        List<Integer>inOrder=new ArrayList<>();
        List<Integer>postOrder=new ArrayList<>();


        st.push(new pair(root, 1));

        while (!st.isEmpty()) {

            pair topPair=st.peek();
            treeNode node=topPair.node;
            int state=topPair.state;

            if (state==1) {
                preOrder.add(node.data);
                topPair.state++;
                if (node.leftChild!=null) {
                    st.push(new pair(node.leftChild, 1));
                }
            }else if(state==2){
                inOrder.add(node.data);
                topPair.state++;
                if (node.rightChild!=null) {
                    st.push(new pair(node.rightChild, 1));
                }
            }else if(state==3){
                postOrder.add(node.data);
                st.pop();
            }
        }

        System.out.println("Pre-order: " + preOrder);
        System.out.println("In-order: " + inOrder);
        System.out.println("Post-order: " + postOrder);
        
    }
    

}
class pair{
    public treeNode node;
    public int state;
    public pair(treeNode node,int state){
        this.node=node;
        this.state=state;
    }
}
