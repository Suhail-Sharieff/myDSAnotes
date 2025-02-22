package _9_Linked_List;

/*
Given a linked list where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to the head side, 2s at the end of the linked list, and 1s in the middle of 0s and 2s.

Examples:

Input: LinkedList: 1->2->2->1->2->0->2->2
Output: 0->1->1->2->2->2->2->2
Explanation: All the 0s are segregated to the left end of the linked list, 2s to the right end of the list, and 1s in between.
 
Input: LinkedList: 2->2->0->1
Output: 0->1->2->2
Explanation: After arranging all the 0s,1s and 2s in the given format, the output will be 0 1 2 2.

Expected Time Complexity: O(n).
Expected Auxiliary Space: O(n).

Constraints:
1 <= no. of nodes <= 106
0 <= node->data <= 2
 */
public class _05_sort_LL {
    public static void main(String[] args) {
        ListNode head = ListNode.get_LL(new int[] { 1, 2, 2, 1, 2, 0, 2, 2 });
        ListNode.print_LL(better(head));
        ListNode.print_LL(optimal(head));
    }

    // ----------------brute: O(2N)---O(1)
    static ListNode brute(ListNode head) {
        int nZeroes = 0, nOnes = 0, nTwos = 0;
        ListNode ptr = head;
        while (ptr != null) {
            if (ptr.val == 0)
                nZeroes++;
            else if (ptr.val == 1)
                nOnes++;
            else if (ptr.val == 2)
                nTwos++;
            ptr = ptr.next;
        }
        ptr = head;
        while (nZeroes != 0 || nOnes != 0 || nTwos != 0) {
            if (nZeroes != 0) {
                ptr.val = 0;
                nZeroes--;
            } else if (nOnes != 0) {
                ptr.val = 1;
                nOnes--;
            } else {
                ptr.val = 2;
                nTwos--;
            }
            ptr = ptr.next;
        }
        return head;
    }

    // --------------optimal in terms of time only
    static ListNode better(ListNode head) {// O(N)---O(N)
        // add your code here
        ListNode zero_tail = new ListNode(-1), zero_head = zero_tail;
        ListNode one_tail = new ListNode(-1), one_head = one_tail;
        ListNode two_tail = new ListNode(-1), two_head = two_tail;

        while (head != null) {
            int val = head.val;
            if (val == 0) {
                zero_tail.next = new ListNode(val);
                zero_tail = zero_tail.next;
            }
            if (val == 1) {
                one_tail.next = new ListNode(val);
                one_tail = one_tail.next;
            }
            if (val == 2) {
                two_tail.next = new ListNode(val);
                two_tail = two_tail.next;
            }
            head = head.next;
        }
        ListNode ans_head = new ListNode(-1), ans_tail = ans_head;
        if (zero_head.next != null) {
            ans_tail.next = zero_head.next;
            ans_tail = zero_tail;
        }
        if (one_head.next != null) {
            ans_tail.next = one_head.next;
            ans_tail = one_tail;
        }
        if (two_head.next != null) {
            ans_tail.next = two_head.next;
            ans_tail = two_tail;
        }
        return ans_head.next;
    }

    // --------------optimal: we just play with pointers in better instead of
    // creating new ListNode, hence no space will be used
    static ListNode optimal(ListNode head) {// O(N)---O(1)

        ListNode zero_tail = new ListNode(-1), zero_head = zero_tail;
        ListNode one_tail = new ListNode(-1), one_head = one_tail;
        ListNode two_tail = new ListNode(-1), two_head = two_tail;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zero_tail.next = curr;
                zero_tail = zero_tail.next;
            } else if (curr.val == 1) {
                one_tail.next = curr;
                one_tail = one_tail.next;
            } else if (curr.val == 2) {
                two_tail.next = curr;
                two_tail = two_tail.next;
            }
            curr = curr.next;
        }

        // ---HEART OF PROBLEM:
        two_tail.next = null;//otherwise it may create cycles

        // Connecting the three lists correctly
        if (one_head.next != null) {
            zero_tail.next = one_head.next; // Connect 0s to 1s
        } else {
            zero_tail.next = two_head.next; // If no 1s, connect 0s to 2s
        }

        one_tail.next = two_head.next; // Connect 1s to 2s

        return zero_head.next; // Return the new head (skip dummy node)
    }

}
