package _9_Linked_List;

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= ListNode.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class _03_add_2_numbers {
    public ListNode normal_way(ListNode l1, ListNode l2) {

        ListNode tail = new ListNode(0);
        ListNode head = tail;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            int sum = (v1 + v2 + carry);
            carry = sum / 10;
            sum %= 10;
            tail.next = new ListNode(sum);
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l2 == null) {
            while (l1 != null) {
                int sum = l1.val + carry;
                carry = sum / 10;
                sum %= 10;
                tail.next = new ListNode(sum);
                tail = tail.next;
                l1 = l1.next;
            }
            if (carry != 0)
                tail.next = new ListNode(carry);
        } else {
            while (l2 != null) {
                int sum = l2.val + carry;
                carry = sum / 10;
                sum %= 10;
                tail.next = new ListNode(sum);
                tail = tail.next;
                l2 = l2.next;
            }
            if (carry != 0)
                tail.next = new ListNode(carry);
        }
        return head.next;//because our head was carrying 0
    }


    //Better way: same logic as that of normal way, but we avoid wrting that if(l1==null) and l2==null and deal separtly
    public ListNode better_way(ListNode l1,ListNode l2){
        ListNode tail=new ListNode(0);
        ListNode head=tail;
        int carry=0;
        while (l1!=null || l2!=null) {//change & to |
            int v1=(l1==null)?0:l1.val;
            int v2=(l2==null)?0:l2.val;
            int sum=v1+v2+carry;
            carry=sum/10;
            sum%=10;
            tail.next=new ListNode(sum);
            tail=tail.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next; 
        }
        if(carry!=0) tail.next=new ListNode(carry);
        return head.next;
    }



    //-------------------------------FOLLOW UP:
    /*
    You are given a linked list where each element in the list is a node and have an integer val. You need to add 1 to the number formed by concatinating all the list node numbers together and return the head of the modified linked list. 

Note: The head represents the first element of the given array.

Examples :

Input: LinkedList: 9->9->9
Output: 1->0->0->0

Explanation: 9->9->9 represents 999 and when 1 is added it becomes 1000. 
Input: LinkedList: 9
Output: 10->NULL
 
Explanation:  9 represents 9 and when 1 is added it becomes 10. 
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(1)

Constraints:
1 <= len(list) <= 105
0 <= list[i] <= 9
     */
    public ListNode addOne(ListNode head) {//O(N)---O(1)
        //solution: reverse the LL, add 1 as same as the way u did above, return reverse
        if(head.next==null) return new ListNode(head.val+1);
        // code here.
        ListNode x=new ListNode(1);
        ListNode rev=rev(head,null);
        ListNode ptr=rev;
        ListNode prev=null;
        // System.out.println(ptr.val);
        int carry=0;
        while(ptr!=null){
            prev=ptr;
            int v1=ptr.val;
            int v2=(x!=null)?x.val:0;
            int sum=v1+v2+carry;
            carry=sum/10;
            sum%=10;
            ptr.val=sum;
            ptr=ptr.next;
            x=null;
        }
        if(carry!=0) prev.next=new ListNode(carry);
        return rev(rev,null);
        
    }
    public ListNode rev(ListNode head,ListNode prev){
        if(head==null) return prev;
        ListNode n=head.next;
        head.next=prev;
        return rev(n,head);
    }
}
