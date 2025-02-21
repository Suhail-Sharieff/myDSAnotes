package _9_Linked_List;

public class ListNode {
    public int val;
    ListNode next;
    public ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
    @Override
    public String toString() {
        return val+"->";
    }
    
    static void print_LL(ListNode root){
        ListNode temp=root;
        while (temp!=null) {
            System.out.print(temp);
            temp=temp.next;
        }
        System.out.println("NULL");
    }

    static void reverse_LL(ListNode root){
        ListNode prev=null;
        ListNode curr=root;
        while (curr!=null) {
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
    }

    static ListNode get_LL(int arr[]){//O(N)
        ListNode root=new ListNode(arr[0],null);
        ListNode tail=root;
        for(int i=1;i<arr.length;i++){
            tail.next=new ListNode(arr[i],null);
            tail=tail.next;
        }
        return root;
    }


    public static void main(String[] args) {
        int arr[]={1,2,3,4,5};
        ListNode root=get_LL(arr);
        ListNode.print_LL(root);
        // ListNode.reverse_LL(root);
        ListNode.print_LL(root);
    }
}
