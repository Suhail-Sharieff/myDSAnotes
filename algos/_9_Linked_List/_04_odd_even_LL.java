package _9_Linked_List;
/*
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

 

Example 1:


Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:


Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
 

Constraints:

The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
 */
public class _04_odd_even_LL {


    ///-------------------brute force O(N)---O(N)
    public ListNode brute_force(ListNode head) {
        if(head==null) return null;//no nodes
        if(head.next==null) return head;//just 1 node

        ListNode odd_tail=new ListNode(head.val);
        ListNode odd_head=odd_tail;

        ListNode even_tail=new ListNode(head.next.val);
        ListNode even_head=even_tail;

        head=head.next.next;//coz we have handled already 1st 2 nodes
        int cnt=1;
        while(head!=null){
            if((cnt&1) != 0){
                //odd idx
                odd_tail.next=new ListNode(head.val);
                odd_tail=odd_tail.next;
            }else{
                even_tail.next=new ListNode(head.val);
                even_tail=even_tail.next;
            }
            cnt++;
            head=head.next;
        }
        odd_tail.next=even_head;
        return odd_head;
    }

    //-----------------optimal:O(n)---O(1)---no extra space
    public ListNode optimal(ListNode head){
        if(head==null) return null;//no nodes
        if(head.next==null) return head;//just 1 node

        ListNode odd_tail=head;
        ListNode odd_head=odd_tail;

        ListNode even_tail=head.next;
        ListNode even_head=even_tail;

        while(odd_tail.next!=null && even_tail.next!=null){
            odd_tail.next=even_tail.next;
            even_tail.next=odd_tail.next.next;
            odd_tail=odd_tail.next;
            even_tail=even_tail.next;
        }
        odd_tail.next=even_head;
        return odd_head;

    }
}
