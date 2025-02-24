package _9_Linked_List;

import java.util.PriorityQueue;

/*
Given the head of a linked list, return the list after sorting it in ascending order.

 

Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []
 

Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105
 

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?


 */
public class _16_sort_LL {
    public static void main(String[] args) {
        ListNode head=ListNode.get_LL(new int[]{2,1,4,6,7,3,4,6,9,3});
        ListNode ans=optimal(head);
        ListNode.print_LL(ans);
    }

    //--------------brute: O(NLogN)---O(N)
    static ListNode brute(ListNode head) {
        if(head==null) return null;
        PriorityQueue<ListNode>pq=new PriorityQueue<>((x,y)->x.val-y.val);
        ListNode curr=head;
        while(curr!=null){
            pq.offer(curr);
            curr=curr.next;
        }
        ListNode ans=new ListNode(-1),ptr=ans;
        while(!pq.isEmpty()){//N
            ListNode top=pq.poll();
            ptr.next=top;
            ptr=ptr.next;

            //disconnect the node, or else it will create cycles
            top.next=null;//----iMP
        }
        return ans.next;
    }


    //---------------optimal--MERGE SORT IN LINKED LIST:
    /*
     uick recap of merge sot:
     void rec(int nums[],int from,int till){
        if(low==high) return nums[low];//just 1 elemnt
        int mid=(low+high)/2;
        rec(nums,from,mid);
        rec(nums,mid+1,high);
        merge(nums,from,till)
     }
     */
    static ListNode optimal(ListNode head){
        ListNode ptr=head;
        return merge_sort(ptr, ptr, null);//O(NLogN)
    }   

    static ListNode merge_sort(ListNode head,ListNode from,ListNode till){

        if (from == null || from == till) return from;
        
        ListNode mid=get_mid(from,till);
        ListNode first_part_head=from,next_part_head=mid.next;
        mid.next=null;
        
        first_part_head=merge_sort(head, from, mid);
        next_part_head=merge_sort(head, next_part_head, till);
        
        return merge(first_part_head, next_part_head);
    }

    static ListNode get_last(ListNode head){
        ListNode ptr=head,prev=null;
        while (ptr!=null) {
            prev=ptr;
            ptr=ptr.next;
        }
        return prev;
    }
    static ListNode get_mid(ListNode from, ListNode till) {
        ListNode slow = from, fast = from;
        while (fast != till && fast.next != till) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode merge(ListNode l1,ListNode l2){
        if(l1==null && l2==null) return null;
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode ans=new ListNode(-1),ptr=ans;
        while (l1!=null && l2!=null) {
            if(l1.val<=l2.val){
                ptr.next=l1;
                l1=l1.next;
            }
            else{
                ptr.next=l2;
                l2=l2.next;
            }
            ptr=ptr.next;
        }
        if(l1!=null) ptr.next=l1;
        if(l2!=null) ptr.next=l2;
        return ans.next;
    }
}
