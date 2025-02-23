package _9_Linked_List;

import java.util.HashSet;
import java.util.Set;

/*
You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 

Example 1:


Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node. 
Example 2:


Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:


Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.
 

Constraints:

The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105
 */


public class _10_delete_middle {
    public ListNode deleteMiddle(ListNode head) {
        if(head.next==null) return null;
        ListNode slow=head,fast=head,prev=null;
        while(fast!=null && fast.next!=null){
            prev=slow;slow=slow.next;
            fast=fast.next.next;
        }
        prev.next=prev.next.next;
        slow.next=null;
        return head;
    }


    //-----------------------------FOLLOW UP:
    /*
    Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

 

Example 1:


Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
Example 2:

Input: head = [], val = 1
Output: []
Example 3:

Input: head = [7,7,7,7], val = 7
Output: []
 

Constraints:

The number of nodes in the list is in the range [0, 104].
1 <= Node.val <= 50
0 <= val <= 50
     */
    public ListNode optimal(ListNode head, int val) {//same code works for DoublyLL also
        if(head==null) return null;
        ListNode ptr=head,prev=null;
    
        while(ptr!=null){
            if(ptr.val==val){
                //HEart:
                if(prev==null){//what if TC is [7,7,7,7] AND target=7
                    ptr=head.next;
                    head=head.next;
                    continue;
                }
                prev.next=prev.next.next;
                ptr.next=null;
                ptr=prev.next;
                continue;
            }
            prev=ptr;
            ptr=ptr.next;
        }
        return head;
    }

    //----------------FOLLOW UP:
    /*
    You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

 

Example 1:

Input: nums = [1,2,3], head = [1,2,3,4,5]

Output: [4,5]

Explanation:



Remove the nodes with values 1, 2, and 3.

Example 2:

Input: nums = [1], head = [1,2,1,2,1,2]

Output: [2,2,2]

Explanation:



Remove the nodes with value 1.

Example 3:

Input: nums = [5], head = [1,2,3,4]

Output: [1,2,3,4]

Explanation:



No node has value 5.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are unique.
The number of nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
The input is generated such that there is at least one node in the linked list that has a value not present in nums.
     */
    public ListNode optimal(int[] nums, ListNode head) {//O(N) ---- O(N)
        Set<Integer>hs=new HashSet<>();
        for(int e:nums)hs.add(e);
        ListNode ptr=head;
        ListNode prev=null;
        while(ptr!=null){
            if(hs.contains(ptr.val)){//O(1) time
                if(prev==null){
                    head=head.next;
                    ptr=head;
                    continue;
                }
                prev.next=prev.next.next;
                ptr.next=null;
                ptr=prev.next;
                continue;
            }
            prev=ptr;
            ptr=ptr.next;
        }
        return head;
    }
}
