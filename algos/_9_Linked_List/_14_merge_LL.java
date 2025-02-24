package _9_Linked_List;

public class _14_merge_LL {

    //------O(N)---O(N)
    public ListNode brute(ListNode list1, ListNode list2) {
        if(list1==null && list2==null) return null;
        if(list1==null) return list2;
        if(list2==null) return list1;
        ListNode h=new ListNode(-1),ans=h;
        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                ans.next=new ListNode(list1.val);
                list1=list1.next;
            }else if(list1.val>list2.val){
                ans.next=new ListNode(list2.val);
                list2=list2.next;
            }else{
                ans.next=new ListNode(list1.val);
                ans=ans.next;
                ans.next=new ListNode(list2.val);
                list1=list1.next;
                list2=list2.next;
            }
            ans=ans.next;
        }
        while(list1!=null){
            ans.next=new ListNode(list1.val);
            ans=ans.next;
            list1=list1.next;
        }
        while(list2!=null){
            ans.next=new ListNode(list2.val);
            ans=ans.next;
            list2=list2.next;
        }
        return h.next;
    }

    //-------optimal: O(N)--O(1)---VVIMP:teaches how u can save mememoru using ptrs
    public ListNode optimal(ListNode l1,ListNode l2){
        ListNode ans=new ListNode(-1);
        ListNode ptr=ans;
        while (l1!=null && l2!=null) {
            if(l1.val<l2.val){
                ptr.next=l1;
                l1=l1.next;
            }else{
                ptr.next=l2;
                l2=l2.next;
            }
            ptr=ptr.next;
        }
        //by this point any one of l1 or l2 must have become null
        if(l1!=null) ptr.next=l1;
        if(l2!=null) ptr.next=l2;
        return ans.next;
    }
}
