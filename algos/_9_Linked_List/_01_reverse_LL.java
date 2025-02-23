package _9_Linked_List;

public class _01_reverse_LL {
    public static void main(String[] args) {
        ListNode root = ListNode.get_LL(new int[] { 1, 2, 3, 4, 5 });
        // rev(root);
        // ListNode.print_LL(root=dfs(root,null));
        func(root, 2);
    }

    static ListNode rev(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode.print_LL(prev);
        return prev;
    }

    // ----------------------recursive
    static ListNode dfs(ListNode root, ListNode prev) {
        if (root == null)
            return prev;
        ListNode next_node = root.next;
        root.next = prev;
        return dfs(next_node, root);
    }

    static class DLL_Node {
        int val;
        DLL_Node next;
        DLL_Node prev;

        public DLL_Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }

        static void print_DLL(DLL_Node head) {
            DLL_Node temp = head;
            while (temp != null) {
                System.out.print(temp.val + "<->");
                temp = temp.next;
            }
            System.out.println();
        }

        static void reverse_DLL(DLL_Node head) {
            DLL_Node curr = head;
            DLL_Node prev = null;
            while (curr != null) {
                DLL_Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            print_DLL(prev);
        }

        static DLL_Node get_DLL(int arr[]) {
            DLL_Node root = new DLL_Node(arr[0]);
            DLL_Node tail = root;
            for (int i = 1; i < arr.length; i++) {
                tail.next = new DLL_Node(arr[i]);
                tail = tail.next;
            }
            return root;
        }

        public static void main(String[] args) {
            int arr[] = { 1, 2, 34, 5 };
            // DLL_Node.print_DLL(DLL_Node.get_DLL(arr));
            DLL_Node.reverse_DLL(DLL_Node.get_DLL(arr));
        }
    }

    // ---------------------FOLLOW UP: reverse nodes in k-group---------------HARD
    /*
     * Given the head of a linked list, reverse the nodes of the list k at a time,
     * and return the modified list.
     * 
     * k is a positive integer and is less than or equal to the length of the linked
     * list. If the number of nodes is not a multiple of k then left-out nodes, in
     * the end, should remain as it is.
     * 
     * You may not alter the values in the list's nodes, only nodes themselves may
     * be changed.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     * Example 2:
     * 
     * 
     * Input: head = [1,2,3,4,5], k = 3
     * Output: [3,2,1,4,5]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is n.
     * 1 <= k <= n <= 5000
     * 0 <= Node.val <= 1000
     * 
     * 
     * Follow-up: Can you solve the problem in O(1) extra memory space?
     */

    static void func(ListNode head, int k) {
        //https://www.youtube.com/watch?v=lIar1skcQYI&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=257&ab_channel=takeUforward

        if (head == null || k <= 1) {
            ListNode.print_LL(head);
            return;
        }

        ListNode curr = head;
        ListNode prev_tail = null;
        ListNode newHead = null;  // new head after reversing the first group

        while (curr != null) {

            ListNode kthNode_from_here = get_kth_node_from(curr, k);

            if (kthNode_from_here == null) {
                if (prev_tail != null)
                    prev_tail.next = curr; // Connect remaining nodes as they are
               break;
            }
            ListNode head_of_next_group = kthNode_from_here.next;
            kthNode_from_here.next = null;

            // Reverse the current group
        ListNode reversedGroupHead = reverse(curr);
            // Set new head if it's the first reversed group
        if (newHead == null) {
            newHead = reversedGroupHead;
        }
            if(prev_tail!=null) {
                prev_tail.next = reversedGroupHead;
            }
            prev_tail = curr;
            curr = head_of_next_group;
        }
        ListNode.print_LL(newHead);
    }

    static ListNode get_kth_node_from(ListNode head, int k) {
        ListNode ptr = head;
        int i = 1;
        while (ptr != null) {
            if (i == k)
                return ptr;
            ptr = ptr.next;
            i++;
        }
        return null;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


}
