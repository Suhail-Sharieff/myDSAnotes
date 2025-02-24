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

        public Node(int v) {
            this.val = v;
        }
    }

    public static void main(String[] args) {

        Node example_node=get_exaple_LL();
        // Node flattened_ans=flatten_iterative(example_node);
        Node flattened_ans=flatten_recursive(example_node);
        print(flattened_ans);
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

}
