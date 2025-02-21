package _9_Linked_List;

public class _01_reverse_LL {
    public static void main(String[] args) {
        ListNode root=ListNode.get_LL(new int[]{1,2,3,4,5});
        rev(root);
    }
    static ListNode rev(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        ListNode.print_LL(prev);
        return prev;
    }
}
