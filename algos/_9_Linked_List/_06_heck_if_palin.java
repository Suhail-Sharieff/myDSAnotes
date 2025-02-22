package _9_Linked_List;

/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

 

Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false
 

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
 

Follow up: Could you do it in O(n) time and O(1) space?
Seen this question in a real interview before?
1/5
Yes
No
Accepted
2.3M
Submissions
4.1M
Acceptance Rate
55.1%
 */
public class _06_heck_if_palin {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8,9,10};
        ListNode head=ListNode.get_LL(arr);
        optimal(head);
    }

    public boolean brute_force(ListNode head) {// O(n)---O(n)
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        // System.out.println(sb+" "+sb.reverse());
        return sb.toString().equals(sb.reverse().toString());
    }


    static boolean optimal(ListNode head){
        //idea: reach middle, revrese part next to midlle, now start checking from left end and left end of reversed part
        if(head==null || head.next==null) return true;
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while (fastPtr.next!=null && fastPtr.next.next!=null) {
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }

        ListNode second_part_to_check=reverse(slowPtr.next, null);

        while (second_part_to_check!=null) {
            if(head.val!=second_part_to_check.val) return false;
            head=head.next;
            second_part_to_check=second_part_to_check.next;
        }
        return true;
    }

    static ListNode reverse(ListNode root,ListNode prev){
        if(root==null) return prev;
        ListNode next_node=root.next;
        root.next=prev;
        return reverse(next_node, root);
    }
}
