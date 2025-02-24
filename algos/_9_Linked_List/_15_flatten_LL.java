package _9_Linked_List;

/*
Given a linked list containing n head nodes where every node in the linked list contains two pointers:
(i) next points to the next node in the list.
(ii) bottom pointer to a sub-linked list where the current node is the head.
Each of the sub-linked lists nodes and the head nodes are sorted in ascending order based on their data.
Your task is to flatten the linked list such that all the nodes appear in a single level while maintaining the sorted order.

Note:
1. ↓ represents the bottom pointer and -> represents the next pointer.
2. The flattened list will be printed using the bottom pointer instead of the next pointer.

Examples:

Input:
/*
Linked List Structure:

5 -> 10 -> 19 -> 28
|     |     |     |
7     20    22    35
|           |     |
8           50    40
|                 |
30                45


Output: 5-> 7-> 8-> 10 -> 19-> 20-> 22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation: 
Bottom pointer of 5 is pointing to 7.
Bottom pointer of 7 is pointing to 8.
Bottom pointer of 8 is pointing to 10 and so on.
Input:
 /*
Linked List Structure:

5 -> 10 -> 19 -> 28
|          |
7          22
|          |
8          50
|     
30    


Output: 5-> 7-> 8-> 10-> 19-> 22-> 28-> 30-> 50
Explanation:
Bottom pointer of 5 is pointing to 7.
Bottom pointer of 7 is pointing to 8.
Bottom pointer of 8 is pointing to 10 and so on.
Constraints:
0 <= n <= 100
1 <= number of nodes in sub-linked list(mi) <= 50
1 <= node->data <= 104
 */
public class _15_flatten_LL {
    static class Node {
        int val;
        Node next;
        Node bottom;

        //only for follow up:
        Node child;
        Node prev;

        public Node(int v) {
            this.val = v;
        }
    }

    public static void main(String[] args) {

        // Node example_node=get_exaple_LL();
        // Node flattened_ans=flatten_iterative(example_node);
        // Node flattened_ans=flatten_recursive(example_node);
        // print(flattened_ans);


        //--------------------follow up
        //run in leetocde to get to know 

    }
    


    //SOLUTION: gien that the each bottom part of each node is sorted, so if u merge 2 verical LLinked lists, job's done. Just make sure u move downwards in merge function rather than next

    static Node get_exaple_LL(){

        /*
5 -> 10 -> 19 -> 28
|     |     |     |
7     20    22    35
|           |     |
8           50    40
|                 |
30                45
         */

        
            // Creating nodes
            Node n5 = new Node(5);
            Node n10 = new Node(10);
            Node n19 = new Node(19);
            Node n28 = new Node(28);
            Node n7 = new Node(7);
            Node n20 = new Node(20);
            Node n22 = new Node(22);
            Node n35 = new Node(35);
            Node n8 = new Node(8);
            Node n50 = new Node(50);
            Node n40 = new Node(40);
            Node n30 = new Node(30);
            Node n45 = new Node(45);
        
            // Setting next pointers (horizontal links)
            n5.next = n10;
            n10.next = n19;
            n19.next = n28;
        
            // Setting bottom pointers (vertical links)
            n5.bottom = n7;
            n7.bottom = n8;
            n8.bottom = n30;
        
            n10.bottom = n20;
        
            n19.bottom = n22;
            n22.bottom = n50;
        
            n28.bottom = n35;
            n35.bottom = n40;
            n40.bottom = n45;
        
            return n5; // Returning the head of the linked list
        
    }

    //------------------iterative--O(n^2)---O(1)
    static Node flatten_iterative(Node root) {
        // code here
        if (root == null || root.next == null)
            return root;
        Node ans = root, prev = root;
        Node ptr = root.next, prev_head = prev;
        while (ptr != null) {//O(N)
            // disconnect prev--ptr
            prev.next = null;
            prev_head = merge(prev_head, ptr);//O(N)
            ptr = ptr.next;
            // print(ans);
        }
        return ans;
    }



    //--------------------recurive
    static Node flatten_recursive(Node root){//O(N^2)--O(n)(recursive stack space)
        if(root.next==null) return root;
        //go deep
        Node next_flatten_node=flatten_recursive(root.next);
        root.next=merge(root, next_flatten_node);//while back tracking merge last merged LL with its parent and return it
        return root;   
    }



    static void print(Node root){
        Node ptr=root;
        while(ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.bottom;
        }
        System.out.println();
    }

    static Node merge(Node l1, Node l2) {
        Node ans = new Node(-1);
        Node ptr = ans;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ptr.bottom = l1;
                l1 = l1.bottom;
            } else {
                ptr.bottom = l2;
                l2 = l2.bottom;
            }
            ptr = ptr.bottom;
        }
        // by this point any one of l1 or l2 must have become null
        if (l1 != null)
            ptr.bottom = l1;
        if (l2 != null)
            ptr.bottom = l2;
        return ans.bottom;
    }


    //_-----------------------------FOLLOW UP(HARD):flatten a multilevel doubly LL
    /*
You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.

 

Example 1:


Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 2:


Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 3:

Input: head = []
Output: []
Explanation: There could be empty list in the input.
 

Constraints:

The number of Nodes will not exceed 1000.
1 <= Node.val <= 105
 

How the multilevel linked list is represented in test cases:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together, we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,    2,    3, 4, 5, 6, null]
             |
[null, null, 7,    8, 9, 10, null]
                   |
[            null, 11, 12, null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     */


     //Intutuion: how we used to build right-threaded Binary tree, but there we dint make anything null, we just connected node if possible to its preorder successor, i got intutuion from that only. i treated the given DLL like a tree, starting from the first node as root, treat 'child' as left child and 'next' as rightchild of tree,  since we need to flatten this DLL, the idea is to some how flatten the tree into a single LL, the idea is as same as right-treaded-bt formation. Idea is to just trverse like how u did in a binary tree, whenver u find a node having both child and a next node ie a node(in tree) with 2 children. When we encounter such node, make a depth first search to last right end of that node, connect its next to right of node frommwhere u did dfs(ie haing 2 children), MAKE SURE U PROPERLY HANDLE TO MAKE CHILD,PREV,NEXT as null or whatsoever wherever possible, EDGE CASE: suppose nodes r nested children, ie node has no next or prev but instaed nly nested children, hadle such case properly, thats all
     

     /*
      For ex, the multilayered DLL is given as:
      1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL


             Imagine as trree to get: Traeting next as rightChild and child as left child

                       1
                        \
                         2
                        /  \
                       7    3
                        \    \
                         8    4
                        /  \   \
                       11   9   5
                        \    \   \
                         12   10  6

            Dry run of my  algo: 

            A normal dfs to just print nodes in wanted order as per question is
            dfs(root){
                if(root==null) return;
                print(root.val);
                dfs(root.child)
                dfs(root.next) 
            }

            Now just try to get desired LL by playing with this dfs call
            (try running the main method to get how its working)

            supoose u reach 8(has 2 children)
            say next_node=9
            so  a dfs from 8 to reach 12, break connection betwenn 8 and 9, make 9 as next of 12

            similarly, u can break connection between 2 and 3, connect 3 to next of 10, make connections accordingly, the tree has been convertedd to a single doubly LL




      */

      static Node flatten_DLL(Node root) {
        if(root==null) return root;
        Node ptr=root;
        rec(ptr);
        print(root);
        return root;
    }
    static Node rec(Node root){
        if(root.next==null && root.child==null){
            // print("---------------------------------------");
            // print("i reached "+root.val+" and my next is null");
            // print("---------------------------------------");
            return root;
        }else if(root.next==null && root.child!=null){
            root.next=root.child;
            root.next.prev=root;
            root.child=null;
            return rec(root.next);
        }
        if(root.child!=null && root.next!=null){
            // print("---------------------------------------");
            // print("root: "+root.next.val+" child: "+root.child.val);
            Node next_node=root.next;
            Node end=rec(root.child);
            // print(root.val+" has both left and right so trying to connect "+end.val+" with "+next_node.val);
            root.next=root.child;
            root.child=null;
            root.next.prev=root;
            next_node.prev=null;
            end.next=next_node;
            next_node.prev=end;
            // print("so now "+end.val+" has next as "+end.next.val);
            // print("---------------------------------------");
            return rec(end.next);
        }
        return rec(root.next);
    }
    public void print(String s){
        System.out.println(s);
    }
    public void printNext(Node head){//i used tthis function while debugging to make sure a node has only prev and next and no child after flattening
        Node ptr1=head;
        while(ptr1!=null){
            print(ptr1.val+" has child "+ptr1.child);
            ptr1=ptr1.next;
        }
        
    }
}
