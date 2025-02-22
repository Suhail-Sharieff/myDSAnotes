package _9_Linked_List;

public class _01_reverse_LL {
    public static void main(String[] args) {
        ListNode root=ListNode.get_LL(new int[]{1,2,3,4,5});
        // rev(root);
        ListNode.print_LL(root=dfs(root,null));
    }
    static ListNode rev(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        ListNode.print_LL(prev);
        return prev;
    }


    //----------------------recursive
    static ListNode dfs(ListNode root,ListNode prev){
        if(root==null) return prev;
        ListNode next_node=root.next;
        root.next=prev;
        return dfs(next_node, root);
    }


    static class DLL_Node{
        int val;
        DLL_Node next;
        DLL_Node prev;
        public DLL_Node(int val){
            this.val=val;
            this.next=null;
            this.prev=null;
        }
        static void print_DLL(DLL_Node head){
            DLL_Node temp=head;
            while (temp!=null) {
                System.out.print(temp.val+"<->");
                temp=temp.next;
            }
            System.out.println();
        }

        static void reverse_DLL(DLL_Node head){
            DLL_Node curr=head;
            DLL_Node prev=null;
            while(curr!=null){
                DLL_Node next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
            print_DLL(prev);
        }

        static DLL_Node get_DLL(int arr[]){
            DLL_Node root=new DLL_Node(arr[0]);
            DLL_Node tail=root;
            for(int i=1;i<arr.length;i++){
                tail.next=new DLL_Node(arr[i]);
                tail=tail.next;
            }
            return root;
        }

        public static void main(String[] args) {
            int arr[]={1,2,34,5};
            // DLL_Node.print_DLL(DLL_Node.get_DLL(arr));
            DLL_Node.reverse_DLL(DLL_Node.get_DLL(arr));
        }
    }
}
