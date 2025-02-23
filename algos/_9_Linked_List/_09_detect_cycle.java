package _9_Linked_List;

import java.util.HashMap;

public class _09_detect_cycle {
    //=---------gieven that the nodeVal is in range [-1e5,+1e5], so i will change all visited nodes aas integer.max, we we encounter any node having that val(ie is revisited), return true since then there is cycle, NOT SUITABLE SOLUTION IF WE R NOT SUPPOSED TO CHANGE NODE VALUES
    public boolean brute_force(ListNode head) {//O(N)
        ListNode ptr=head;
        while(ptr!=null){
            if(ptr.val==Integer.MAX_VALUE) return true;
            ptr.val=Integer.MAX_VALUE;
            ptr=ptr.next;
        }
        return false;
    }

    //------------optimal:
    //intution: first consider a straight line , if start slow and fast ptrs from 0, moving slow by +1 each time and fast by +2 each time, say diff=pos(fast)-pos(slow), for every operation observe that the d increases by +1, suppose after 1st opr, the diff was d, after second, it would be (d+1), now join the end points of this striaght line thereby convering into a cycle/loop. If we see from slow towards fast ptr, d increases, but since its a loop say of circumference 'L', if we see from backwrds ie from fast towards slow and measure diff, it decreses coz on opposite way sight its increasing, hence since its a loop, ther will be a point always where the forward diff becomes equal to 'L' or in other words the backwards distance will become (L-L) ie 0, ie they intersect, this can be possible only in a loop, so if fast==slow, there will be loop
    public boolean optimal(ListNode head) {//O(N)---O(1)
        if(head==null) return false;
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
         slow=slow.next;//MISTAKE: wrote if stmt befre this(U CAN DO THAT BUT THEN U NEED TO INITIALIZE FAST AS HEAD.NEXT)
         fast=fast.next.next;
         if(slow==fast) {
             return true;
         }
        }
        return false;
    }


    //----------------------follow up: Find loop ending/starting point
    /*
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

 

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.
 

Follow up: Can you solve it using O(1) (i.e. constant) memory?
     */
    public ListNode brute(ListNode head) {
        if(head==null) return null;
        HashMap<ListNode,Boolean>hs=new HashMap<>();
        ListNode ptr=head;
        while(ptr!=null){
            if(hs.containsKey(ptr)) return ptr;
            hs.put(ptr,true);
            ptr=ptr.next;
        }
        return null;
    }

    public ListNode optimal2(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) break;
        }

        if (fast == null || fast.next == null) return null;//what if single node is ther

        fast = head;//reset to find where the cycle starts
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;        
    }


    //------------------------follow up: find length of loop
    /*
    Given the head of a linked list, determine whether the list contains a loop. If a loop is present, return the number of nodes in the loop, otherwise return 0.



Note: 'c' is the position of the node which is the next pointer of the last node of the linkedlist. If c is 0, then there is no loop.

Examples:

Input: LinkedList: 25->14->19->33->10->21->39->90->58->45, c = 4
Output: 7
Explanation: The loop is from 33 to 45. So length of loop is 33->10->21->39-> 90->58->45 = 7. 
The number 33 is connected to the last node of the linkedlist to form the loop because according to the input the 4th node from the beginning(1 based indexing) 
will be connected to the last node for the loop.
 
Input: LinkedList: 5->4, c = 0
Output: 0
Explanation: There is no loop.

Expected Time Complexity: O(n)
Expected Auxiliary Space: O(1)

Constraints:
1 <= no. of nodes <= 106
0 <= node.data <=106
0 <= c<= n-1
     */
    public int length_of_cycle(ListNode head){
        ListNode slow = head, fast = head;

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                if(fast==null || fast.next==null) return 0;
                int len=1;
                while(fast.next!=slow){
                    len++;
                    fast=fast.next;
                }
                return len;
            }
        }
        return 0;
    }

}
