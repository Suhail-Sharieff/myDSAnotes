package _9_Linked_List;

public class _12_remove_duplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr=head;
        while(ptr!=null && ptr.next!=null){
            if(ptr.val==ptr.next.val){
                ptr.next=ptr.next.next;
            }else{
                ptr=ptr.next;
            }
        }
        return head;
    }
}
