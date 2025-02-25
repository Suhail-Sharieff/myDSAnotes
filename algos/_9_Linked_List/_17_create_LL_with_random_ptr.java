package _9_Linked_List;

import java.util.HashMap;

public class _17_create_LL_with_random_ptr {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        // Example linked list: 7 -> 14 -> 21 -> 28
        Node head = new Node(7);
        head.next = new Node(14);
        head.next.next = new Node(21);
        head.next.next.next = new Node(28);

        // Assigning random pointers
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next;
        head.next.next.next.random = head.next;

        // print(brute_force(head));

        optimal(head);

    }

    public static void print(Node head) {
        Node ptr = head;
        while (ptr != null) {
            System.out.print("["+ptr.val);
            System.out.print(",");
            if (ptr.random != null) {
                System.out.println(ptr.random.val+"]");
            }else{
                System.out.println(null+"]");
            }
            ptr=ptr.next;
        }
    }

    //-----------------brute force:
    //map every orignal node with a new Node and then again iterate via original node, but this time make connections with copynode that u had mapped earlier O(2N)--O(N)
    static Node brute_force(Node original) {
        HashMap<Node, Node> hp = new HashMap<>();
        Node ptr = original;
        while (ptr != null) {
            hp.put(ptr, new Node(ptr.val));
            ptr = ptr.next;
        }
        ptr = original;
        while (ptr != null) {
            Node copy_node = hp.get(ptr);
            copy_node.next = hp.get(ptr.next);
            copy_node.random = hp.get(ptr.random);
            ptr = ptr.next;
        }
        return hp.get(original);
    }


    //---------optimal:
    /*
    he previous approach uses an extra space complexity of creating mappings between the original and copied nodes. Instead of creating duplicate nodes and storing them in a map, insert it in between the original node and the next node for quick access without the need for additional space.

Traverse the list again to set the random pointer of copied nodes to the corresponding copied node duplicating the original arrangement. As a final traversal, separate the copied and original nodes by detaching alternate nodes.

Algorithm

Step 1: Traverse the original node and create a copy of each node and insert it in between the original node and the next node.


Step 2: Traverse this modified list and for each original node that has a random pointer, set the copied node’s random pointer to the corresponding copies random node. If the original node’s random pointer is full, set the copied node’s random pointe to null as well.


Step 3: RecursionTraverse the modified list again and extract the coped nodes by breaking the links between the original nodes and the copied nodes. Revert the original list to its initial state by fixing the next pointers.


Step 4: Return the head of the deep copy obtained after extracting the copied nodes from the modified list.

Code
     */

     static void optimal(Node original){
        original=make_copies_of_each(original);//O(3N)----O(1)(neglecting the new LL we have created)
        original=connect_random_pointers_of_copies(original);
        original=form_copy_nodes_chain(original);
        print(original);
     }


     static Node make_copies_of_each(Node original){//step1: to next to each original's node make its new copy
        Node ptr=original;
        while (ptr!=null) {
            Node next_node=ptr.next;
            ptr.next=new Node(ptr.val);//attach a copy of ptr to its own next
            ptr.next.next=next_node;
            ptr=next_node;
        }
        return original;
     }

     static Node connect_random_pointers_of_copies(Node original){//step2: consruct randoms of copy nodes
        Node ptr=original;
        while (ptr!=null) {
            Node random_of_ptr=ptr.random;
            Node copy_node=ptr.next;

            if(random_of_ptr!=null) copy_node.random=random_of_ptr.next;//connect copy_node.random to copy of random of ptr----IMP
            
            ptr=ptr.next.next;//since ptr.next we have copy_node,move 2 steps
        }
        return original;
     }

     static Node form_copy_nodes_chain(Node original){
        Node ptr=original;
        Node ans=new Node(-1),ans_ptr=ans;

        while (ptr!=null) {
            Node copy_node=ptr.next;

            //disconnect copy node from parent chain
            ptr.next=copy_node.next;
            ptr=ptr.next;

            ans_ptr.next=copy_node;
            ans_ptr=ans_ptr.next;

        }
        return ans.next;
     }
}