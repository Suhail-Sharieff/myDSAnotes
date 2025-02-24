package _9_Linked_List;

import java.util.PriorityQueue;

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



    //-----------------------FOLLOW UP:
    /*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.

     */

     //brute force can be while(k--) head=merge(head,next_list)--O(NK)--O(1)


     public ListNode optimal(ListNode[] lists) {//O(N*KLogK)---O(K)
        PriorityQueue<ListNode>pq=new PriorityQueue<>((x,y)->x.val-y.val);
        for(var e:lists) if(e!=null) pq.offer(e);
        ListNode ans=new ListNode(-1),ptr=ans;
        while(!pq.isEmpty()){//K
            ListNode top=pq.poll();
            ptr.next=top;
            ptr=ptr.next;
            if(top.next!=null)pq.offer(top.next);//LogK
        }
        return ans.next;
    }
}
