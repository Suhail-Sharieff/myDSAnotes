package _9_Linked_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
Problem statement
A doubly-linked list is a data structure that consists of sequentially linked nodes, and the nodes have reference to both the previous and the next nodes in the sequence of nodes.



You are given a sorted doubly linked list of size 'n', consisting of distinct positive integers, and a number 'k'.



Find out all the pairs in the doubly linked list with sum equal to 'k'.



Example :
Input: Linked List: 1 <-> 2 <-> 3 <-> 4 <-> 9 and 'k' = 5

Output: (1, 4) and (2, 3)

Explanation: There are 2 pairs in the linked list having sum 'k' = 5.
Detailed explanation ( Input/output format, Notes, Images )
Sample input 1:
5
1 2 3 4 9
5


Sample output 1
2
1 4
2 3


Explanation for sample input 1 :
There are 2 pairs in the linked list having sum equal to 'k' (= 5).


Sample input 2:
5
1 10 11 12 27
7


Sample output 2:
0


Explanation for sample output 2
There is no pair in the linked list with sum equal to 'k' (= 7).


Expected time complexity :
The expected time complexity is O('n').


Constraints:
2 <= 'n' <= 10 ^ 4
1 <= 'data' in any node <= 10 ^ 4
1 <= 'k' <= 10 ^ 4

'data' in every node is distinct.

Time limit: 1 second
 */
public class _11_pairs_with_given_sum_in_DLL {
    public static List<int[]> brute(ListNode head, int k) {//if LL is gievn rather than doubly LL, then this is optimal approach

        HashSet<Integer>hs=new HashSet<>();
        List<int[]>li=new ArrayList<>();
        ListNode ptr=head;
        while(ptr!=null){
            int val=ptr.val;
            ptr=ptr.next;
            if(val>k) return li;//since the list is sorted, further values will obviously be higher, so no use to check
            int req=k-val;
            if(hs.contains(req)) li.add(new int[]{val,req});
            else hs.add(val);
        }
        return li;  
    }

    //optimal: ONLY for DLL:
    /*
    Start a left ptr from head, rightPtr from last node, since DLL is given as sorted, whenever sum of nodes at left and right exceeds k, move left the rightPtr,if equal add to ans and move right leftPtr, if less than k, move right leftPtr
     */
    
    public static void main(String[] args) {
        ListNode head=ListNode.get_LL(new int[]{1,2,3,4,9});
        var e=(brute(head, 5));
        for(var k:e)System.out.println(Arrays.toString(k));
    }
}
