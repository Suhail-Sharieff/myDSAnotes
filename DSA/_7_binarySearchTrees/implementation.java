package _7_binarySearchTrees;

import java.util.Stack;


//So trees basically a data structure which is generally used because the searching,insertion as well as the deletion is extremely fast in case of this particular data structure.... so the topmost node present in case of the tree is called the root the root can have as many children as possible the dashes which connect the 2 nodes are called the wedges and the connection of of so many nodes by using the wedges from root to that particular node is known as a path ...so basically in case of a binary tree every node except the root is going to have maximum of two children and the one node which is not having any children is called a leaf...For example if you want to arrange the elements inside the tree we can add that element which is smaller than the key or the data which is present in that particular node as its left node or left child and the one which is having greater than or equal to that particular node's data as its right child and level of a particular node refers to how many generations of the node is from the root for example if we assume the root is level zero then its children will be level 1 its grandchildren will be then level 2 and so on..So basically every node will be having the data and two references one which is pointing towards its left child and the other which is pointing towards the right child
class treeNode {
    public int data;
    public treeNode leftChild;
    public treeNode rightChild;

    public void displayNode() {
        System.out.print("{" + data + "}");
    }
}

class tree {
    // create root
    private treeNode root;

    public tree() {
        this.root = null;// no nodes in tree yet
    }

    // ---------------------------------------------------------------------------------------
    // How to find a node we traversed from route to the rest of the tree if the
    // current node is having the data which is less than the item to search then we
    // will move towards a left child and if the current value of that node is
    // greater than the key value that we want to search then we will traverse to
    // its right child so we are using this concept because we are dealing with
    // binary search tree
    public treeNode find(int val) {
        treeNode curr = root;
        while (curr.data != val) {
            if (val < curr.data) {
                curr = curr.leftChild;
            } else {
                curr = curr.rightChild;
            }
            if (curr == null) {// dindt find it
                return null;
            }
        }
        return curr;
    }

    // ---------------------------------------------------------------------------------------
    // Now to insert that particular element we use the same principle that is we
    // find if the value to be added is greater than the current node's value or
    // less than the current nodes value so if the value is less than the current
    // node's value we insert it towards the left of the node and if it is greater
    // we insert it towards the right of the node and we have to also take care of
    // the corner its case that is if the current that is there if we have reached
    // the end of the line then at what position we have to insert with that left or
    // right
    public void insert(int val) {
        treeNode newNode = new treeNode();
        newNode.data = val;
        if (root == null) {
            root = newNode;
        } else {
            treeNode curr = root;
            treeNode prev;// we need adress of prev to insert
            while (true) {
                prev = curr;
                if (val < curr.data) {
                    curr = curr.leftChild;
                    if (curr == null) {// reached enf of line,insert on left
                        prev.leftChild = newNode;
                        return;// IMP
                    }
                } else {
                    curr = curr.rightChild;
                    if (curr == null) {
                        prev.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // -------------------------------------------------------------------------------------
    // IMP:
    public boolean delete(int val) {
        // finding val's node
        treeNode curr = root;
        treeNode prev = root;
        boolean isLeftChild = true;
        while (curr.data != val) {
            prev = curr;
            if (val < curr.data) {// move left
                isLeftChild = true;
                curr = curr.leftChild;
            } else {
                isLeftChild = false;
                curr = curr.rightChild;
            }
            if (curr == null) {
                System.out.println("Value NOT found in tree");
                return false;
            }
        }
        // ~~~~IF CURR HAS NO CHILDREN ie IS A LEAF
        // Now we have found the node so first we check whether it has no children when
        // this is true we check the special case of the root that is if that's the node
        // to be deleted if we simply set it to null this in taste the tree otherwise we
        // set the parent's left child or the right child to null to disconnect the
        // parent from the node
        if (curr.leftChild == null && curr.rightChild == null) {// is a leaf
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                prev.leftChild = null;
            } else {
                prev.rightChild = null;
            }
        }
        // ~~~~IF CURR HAS ONLY 1 CHILD(may be right or left)
        else if (curr.rightChild == null) {// the node to be deleted(curr) has one child on its left
            if (curr == root) {// we want to delete root(is curr) and it has a left child
                root = curr.leftChild;
            } else if (isLeftChild) {// the node to be deleted(curr) is on left side of prev and it(curr) has one
                                     // child on left side(coz it satisfied curr.rightChild==null)
                prev.leftChild = curr.leftChild;
            } else {// the node to be deleted(curr) is on right side of prev and it(curr) has one
                    // child on left(coz it satisfied curr.rightChild==null)
                prev.rightChild = curr.leftChild;
            }
        } else if (curr.leftChild == null) {// the node to be deleted(curr) has one child on its right
            if (curr == root) {// we want to delete root(curr) and it has a right child
                root = curr.rightChild;
            } else if (isLeftChild) {// the node to be deleted(curr) is on left side of prev and it(curr) has one
                                     // child on right side(coz it satisfied curr.leftChild==null)
                prev.leftChild = curr.rightChild;
            } else {// the node to be deleted(curr) is on right side of prev and it(curr) has one
                    // child on right(coz it satisfied curr.rightChild==null)
                prev.rightChild = curr.rightChild;
            }
        } else {
            // ~~~~***IF CURR HAS 2 CHILDREN***{VVIMP}---doesnt work if has 1
            // REFER READme.png BEFORE PROCEEDING
            // Now suppose the current What we have to delete is going to have the two
            // children,then we have to delete that particular current and we have to
            // replace that particular current value with the smallest value of the set of
            // the nodes that are larger than that particular current node, so basically
            // that particular value is called the successor and our first task is to find
            // that particular successor from the right of curr coz it should be larger
            /*
             * KEY POINTS ABOUT SUCCESSOR(SEE RAEDme.png):
             * it has no left child
             * it is always present in the right sub tree of curr
             */
            // find succesor of curr first:
            treeNode successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            } else if (isLeftChild) {
                prev.leftChild = successor;
            } else {
                prev.rightChild = successor;
            }
            // connect successor to curr's left child
            successor.leftChild = curr.leftChild;
        }
        return true;
    }

    // finding successor node of curr(node to delete) IFF it has 2 children
    public treeNode getSuccessor(treeNode toDelete) {
        // analyse this code using dleting 26 in READme.png
        treeNode successorParent = toDelete;
        treeNode successor = toDelete;
        treeNode curr = toDelete.rightChild;// successor can never lie on left side
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.leftChild;
        }
        // CASE 1:if the right child of toDelete is a NOT leaf,
        if (successor != toDelete.rightChild) {// if the right child of toDelete is a NOT leaf,then that rightChild
                                               // itself is successor
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = toDelete.rightChild;
        }
        return successor;
    }

    public void preOrderTraverse(treeNode treeRoot) {
        if (treeRoot != null) {
            treeRoot.displayNode();
            preOrderTraverse(treeRoot.leftChild);
            preOrderTraverse(treeRoot.rightChild);
        }
    }

    public void inOrderTraverse(treeNode treeRoot) {
        if (treeRoot != null) {
            inOrderTraverse(treeRoot.leftChild);
            treeRoot.displayNode();
            inOrderTraverse(treeRoot.rightChild);
        }
    }

    public void postOrderTraverse(treeNode treeRoot) {
        if (treeRoot != null) {
            postOrderTraverse(treeRoot.leftChild);
            postOrderTraverse(treeRoot.rightChild);
            treeRoot.displayNode();
        }
    }

    // IMP:
    public void displayTree() {
        Stack<treeNode> globalStack = new Stack<>();
        globalStack.push(root);
        // lets allow max of 6 lines of tree is 2^6 nodes max ie 32 nodes
        double nBlanks = Math.pow(2, 5);
        boolean isRowEmpty = false;
        System.out.println("..........................................................");
        while (isRowEmpty == false) {
            Stack<treeNode> localStack = new Stack<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(' ');
            }
            while (globalStack.isEmpty() == false) {
                treeNode temp = (treeNode) globalStack.pop();
                if (temp != null) {
                    System.out.println(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null) {
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
}

public class implementation {
    public static void main(String[] args) {
        tree t = new tree();
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);
        t.displayTree();
        //Note that I have insulted the elements in the ascending order ok this will lead to the formation of an unbalanced tree which means that it is like having all the nodes pointing in the same direction the same case happens when I try to insert the element in the descending order but however this particular code works very well when I try to insert the elements in some random order so to sort this particular problem that is whether we insert the elements in the ascending or descending order or any random order we get a balanced tree we use red and black trees let's explore it further
        
    }
}
