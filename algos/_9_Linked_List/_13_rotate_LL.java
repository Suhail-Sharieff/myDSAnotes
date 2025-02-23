package _9_Linked_List;

public class _13_rotate_LL {
    public static void main(String[] args) {
        ROTATE_LEFT.main(args);
    }


    static class ROTATE_RIGHT{
        //-----------------brute force, while k--!=0, just trnsfer last node to first
        public ListNode brute_force(ListNode head, int k) {
            int nNodes=nNodes(head);
            if(nNodes==0 || nNodes==1) return head;
            k%=nNodes;
            while(k--!=0){
                ListNode[] pair=get_last(head);
                pair[0].next=null;
                pair[1].next=head;
                head=pair[1];
            }
            return head;
        }
        public ListNode[] get_last(ListNode head){
            ListNode ptr=head,prev=null;
            while(ptr.next!=null){
                prev=ptr;
                ptr=ptr.next;
            }
            return new ListNode[]{prev,ptr};
        }
        public int nNodes(ListNode h) {
            int i = 0;
            ListNode r = h;
            while (r != null) {
                i++;
                r = r.next;
            }
            return i;
        }

        //--------------optimal:
        //first convert LL to circular LL, go till nNodes-k idx,pre.next=null,ans is ptr
        public ListNode rotateRight(ListNode head, int k) {
            int nNodes=nNodes(head);
            if(nNodes==0 || nNodes==1) return head;
            k%=nNodes;
            ListNode last_node=get_last2(head);
            last_node.next=head;
            int idx_of_ans_head=nNodes-k;
            ListNode ptr=head,prev=null;
            while(ptr!=null){
                if(idx_of_ans_head==0){
                    prev.next=null;
                    return ptr;
                }
                prev=ptr;
                ptr=ptr.next;
                idx_of_ans_head--;
            }
            return null;
        }
        public ListNode get_last2(ListNode head){
            ListNode ptr=head;
            while(ptr.next!=null){
                ptr=ptr.next;
            }
            return ptr;
        }
        
    }

    protected class ROTATE_LEFT {
        /*
        Given the head of a singly linked list, your task is to left rotate the linked list k times.

Examples:

Input: head = 10 -> 20 -> 30 -> 40 -> 50, k = 4
Output: 50 -> 10 -> 20 -> 30 -> 40
Explanation:
Rotate 1: 20 -> 30 -> 40 -> 50 -> 10
Rotate 2: 30 -> 40 -> 50 -> 10 -> 20
Rotate 3: 40 -> 50 -> 10 -> 20 -> 30
Rotate 4: 50 -> 10 -> 20 -> 30 -> 40

Input: head = 10 -> 20 -> 30 -> 40 , k = 6
Output: 30 -> 40 -> 10 -> 20 
 
Constraints:

1 <= number of nodes <= 105
0 <= k <= 109
0 <= data of node <= 109

         */
        // Concept used: to left rotate an array by k places, reverse(0,k), then
        // reverse(K+1,len), then reverse(0,n)
        public static void main(String[] args) {
            ListNode head = ListNode.get_LL(new int[] { 1, 2, 3, 4, 5 });
            ListNode left_rotated_node = func(head, 2);
            ListNode.print_LL(left_rotated_node);
        }

        static ListNode func(ListNode head, int k) {// O(N)
            if (head == null || head.next == null)
                return head;
            int nListNodes = nListNodes(head);
            if (k % nListNodes == 0)
                return head;
            ListNode ans = rotate_left(head, k % nListNodes);
            return rev(ans);

        }

        static ListNode rotate_left(ListNode head, int k) {
            ListNode ptr = head;
            int i = 1;
            while (ptr != null) {
                if (i == k) {
                    ListNode next_part_head = ptr.next;
                    if (next_part_head == null)
                        return rev(head);
                    ptr.next = null;
                    ListNode newHead = rev(head);
                    head.next = rev(next_part_head);
                    return newHead;
                }
                i++;
                ptr = ptr.next;
            }
            return null;
        }

        static ListNode rev(ListNode head) {
            ListNode ptr = head, prev = null;
            while (ptr != null) {
                ListNode next = ptr.next;
                ptr.next = prev;
                prev = ptr;
                ptr = next;
            }
            return prev;
        }

        static int nListNodes(ListNode h) {
            int i = 0;
            ListNode r = h;
            while (r != null) {
                i++;
                r = r.next;
            }
            return i;
        }
    }
}
