package _9_Linked_List;

import java.util.HashMap;

/*
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

 

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 

Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */
public class _07_find_intersection {
    
    public ListNode brute_force(ListNode l1, ListNode l2) {//O(N)---O(nNodes(l1))
        if(l1==null || l2==null) return null;
        HashMap<ListNode,Boolean>hs=new HashMap<>();
        ListNode first_ptr=l1,second_ptr=l2;
        while(first_ptr!=null){
            hs.put(first_ptr,true);
            first_ptr=first_ptr.next;
        }
        while(second_ptr!=null){
            if(hs.get(second_ptr)!=null){
                 return second_ptr;
            }
            second_ptr=second_ptr.next;
        }
        return null;
    }

    //-----optimal: idea: bring 2 pointers at same vertical level, ie start trvaersing from the point in longerLL such that shorter and longerLL have same vertical levels
    //https://www.youtube.com/watch?v=0DYoPz2Tpt4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=248&ab_channel=takeUforward
    public ListNode better(ListNode l1, ListNode l2) {//O(n1+n2+|n1-n2|)-----O(1)
        if(l1==null || l2==null) return null;
        int len1=nNodes(l1);//O(n1)
        int len2=nNodes(l2);//o(n2)
        ListNode shorterLL=null,longerLL=null;
        if(len1<=len2){
            shorterLL=l1;
            longerLL=l2;
        }
        else{
            shorterLL=l2;
            longerLL=l1;
        }
        int diff=Math.abs(len1-len2);
        int idx=0;
        while(idx!=diff){
            longerLL=longerLL.next;
            idx++;
        }
        while(shorterLL!=null && longerLL!=null){
            if(shorterLL==longerLL) return shorterLL;
            shorterLL=shorterLL.next;
            longerLL=longerLL.next;
        }
        return null;
    }

    public int nNodes(ListNode root){
        int c=0;
        ListNode temp=root;
        while(temp!=null){
            c++;
            temp=temp.next;
        }
        return c;
    }

    //---------------optimal:O(n1+n2)----O(1)
    //Short explaination: say the length from intersection point is 'x'
    //SAy length of l1 till common point(excluding) be 'a'
    //SAy length of l1 till common point(excluding) be 'b'
    /*
    Now if we some how bring 2 pointers each in one of l1 and l2 at same vertical level, we can just trverse for remining untill both pointers get equal.

    Say ptr1 is traveelling via l1 and ptr2 via l2. Say ptr1 has reached last node after intersection point ie it must have travelled (a+x) distance, similarly ptr2 must have travelled (b+x) distance, now "some how making them to reach at same vertical level", mathematically means that we need to make (a+x) equal to (b+x), to achive it so, add b to first term(means move ptr1 to l2 head) and a to second term(move ptr2 to l1 head), we get first term as (a+x+b) and second term as (b+x+a),aah they r equal , meaning they would arrive at same point , so now we can do a normal trversal and check for common point

     */
    /*
Visualization of this solution:
Case 1 (Have Intersection & Same Len):

       a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
       b
            a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
            b
                 a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
                 b
A:     a1 → a2 → a3
                   ↘ a
                     c1 → c2 → c3 → null
                   ↗ b            
B:     b1 → b2 → b3
Since a == b is true, end loop while(a != b), return the intersection node a = c1.

Case 2 (Have Intersection & Different Len):

            a
A:          a1 → a2
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
       b
                 a
A:          a1 → a2
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
            b
A:          a1 → a2
                   ↘ a
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
                 b
A:          a1 → a2
                   ↘      a
                     c1 → c2 → c3 → null
                   ↗ b           
B:     b1 → b2 → b3
A:          a1 → a2
                   ↘           a
                     c1 → c2 → c3 → null
                   ↗      b           
B:     b1 → b2 → b3
A:          a1 → a2
                   ↘                a = null, then a = b1
                     c1 → c2 → c3 → null
                   ↗           b           
B:     b1 → b2 → b3
A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗                b = null, then b = a1 
B:     b1 → b2 → b3
       a
            b         
A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗
B:     b1 → b2 → b3
            a
                 b         
A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗ 
B:     b1 → b2 → b3
                 a
A:          a1 → a2
                   ↘ b
                     c1 → c2 → c3 → null
                   ↗ a
B:     b1 → b2 → b3
Since a == b is true, end loop while(a != b), return the intersection node a = c1.

Case 3 (Have No Intersection & Same Len):

       a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
       b
            a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
            b
                 a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
                 b
                      a = null
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
                      b = null
Since a == b is true (both refer to null), end loop while(a != b), return a = null.

Case 4 (Have No Intersection & Different Len):

       a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
       b
            a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
            b
                 a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                 b
                      a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                      b = null, then b = a1
       b                   a = null, then a = b1
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
            b                   
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
       a
                 b
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
            a
                      b
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                 a
                           b = null
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                      a = null
Since a == b is true (both refer to null), end loop while(a != b), return a = null.

Notice that if list A and list B have the same length, this solution will terminate in no more than 1 traversal; if both lists have different lengths, this solution will terminate in no more than 2 traversals -- in the second traversal, swapping a and b synchronizes a and b before the end of the second traversal. By synchronizing a and b I mean both have the same remaining steps in the second traversal so that it's guaranteed for them to reach the first intersection node, or reach null at the same time (technically speaking, in the same iteration) -- see Case 2 (Have Intersection & Different Len) and Case 4 (Have No Intersection & Different Len).
     */
//https://www.youtube.com/watch?v=0DYoPz2Tpt4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=248&ab_channel=takeUforward
    public ListNode optimal(ListNode l1,ListNode l2){//O(n1+n2)----O(1)
        if(l1==null || l2==null) return null;
        ListNode h1=l1,h2=l2;//store heads of both
        ListNode ptr1=l1,ptr2=l2;
        while (ptr1!=ptr2) {
            ptr1=ptr1.next;
            ptr2=ptr2.next;
            if(ptr1==ptr2) return ptr1;
            if(ptr1==null) ptr1=h2;
            if(ptr2==null) ptr2=h1;
        }
        return ptr1;//or can return ptr2 also
    }
     
}
