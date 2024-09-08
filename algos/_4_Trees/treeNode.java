package _4_Trees;






//So trees basically a data structure which is generally used because the searching,insertion as well as the deletion is extremely fast in case of this particular data structure.... so the topmost node present in case of the tree is called the root the root can have as many children as possible the dashes which connect the 2 nodes are called the wedges and the connection of of so many nodes by using the wedges from root to that particular node is known as a path ...so basically in case of a binary tree every node except the root is going to have maximum of two children and the one node which is not having any children is called a leaf...For example if you want to arrange the elements inside the tree we can add that element which is smaller than the key or the data which is present in that particular node as its left node or left child and the one which is having greater than or equal to that particular node's data as its right child and level of a particular node refers to how many generations of the node is from the root for example if we assume the root is level zero then its children will be level 1 its grandchildren will be then level 2 and so on..So basically every node will be having the data and two references one which is pointing towards its left child and the other which is pointing towards the right child
public class treeNode {
    public int data;
    public treeNode leftChild;
    public treeNode rightChild;

    public treeNode(int data){
        this.data=data;
    }

    public void displayNode() {
        System.out.print("{" + data + "}");
    }
}

