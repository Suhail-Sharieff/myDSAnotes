package _9_Linked_List;

import java.util.HashMap;

public class _02_delete_without_head {
    /*
    There is a singly-linked list head and we want to delete a node node in it.

You are given the node to be deleted node. You will not be given access to the first node of head.

All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

The value of the given node should not exist in the linked list.
The number of nodes in the linked list should decrease by one.
All the values before node should be in the same order.
All the values after node should be in the same order.
Custom testing:

For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
We will build the linked list and pass the node to your function.
The output will be the entire list after calling your function.
 

Example 1:


Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:


Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 

Constraints:

The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node.
     */
    public void del(ListNode target){
        target.val=target.next.val;
        target.next=target.next.next;
    }


    //--------------------------FOLLOW UP:
    /*
    19. Remove Nth Node From End of List
Solved
Medium
Topics
Companies
Hint
Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?
     */
    public ListNode bruteForce(ListNode head, int n) {//O(2N)
        int nNodes=nNodes(head);
        int idx=nNodes-n;//0 based
        int k=0;
        ListNode prev=null;
        ListNode curr=head;
        while(k<nNodes && k!=idx){
            prev=curr;
            curr=curr.next;
            k++;
        }
        if(prev==null) return head.next;//we wnt to delete head
        prev.next=curr.next;
        return head;
    }
    public int nNodes(ListNode head){
        ListNode root=head;
        int cnt=0;
        while(root!=null){
            root=root.next;
            cnt++;
        }
        return cnt;
    }


    //-------------------optimal:O(N)--single pass
    static ListNode optimal(ListNode head,int n){
        HashMap<Integer,ListNode>hs=new HashMap<>();
        int nodeNumber=0;
        ListNode curr=head;
        while(curr!=null){
            hs.put(nodeNumber++,curr);
            curr=curr.next;
        }
        int idx=nodeNumber-n;//0 based idx
        if(idx==0) return head.next;
        ListNode prev=hs.get(idx-1);
        prev.next=prev.next.next;
        return head;
    }



    public static void main(String[] args) {
        ListNode head=ListNode.get_LL(new int[]{1,2,3,4,5});
        ListNode.print_LL(head);
        int n=2;
        System.out.println("After deleting last "+n+"th element");
        ListNode.print_LL(optimal(head, n));
    }
}
