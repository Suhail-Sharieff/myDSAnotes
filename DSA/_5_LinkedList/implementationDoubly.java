package _5_LinkedList;
//The Doubly linked list provides this capability of traversing backward as well as forward through the list... the secret is that each link has two references to other links instead of 1 the 1st to the next link as in the ordinary lists the 2nd is to the previous link.The downside of doubly linked list is that every time you insert or delete the link you must deal with four links instead of 2 2 attachments for the previous link and two attachments to the following one also of course each link is a little bigger because of the extra reference
class createNodeForDoublyLL{
    public createNodeForDoublyLL previous;
    public createNodeForDoublyLL next;
    public int data;
    public createNodeForDoublyLL(int data){
        this.previous=null;
        this.next=null;
        this.data=data;
    }
    public void displayNode(){
        System.out.print(" <--> " +data);
    }
}
class createDoublyLL{
    public createNodeForDoublyLL first;
    public createNodeForDoublyLL last;
    public int size;
    public createDoublyLL(){
        this.first=null;
        this.last=null;
        this.size=0;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void insertFirst(int val){
        size++;
        createNodeForDoublyLL newNode=new createNodeForDoublyLL(val);
        if (isEmpty()) {
            last=newNode;
        }else{
            newNode.next=first;
            first.previous=newNode;
        }
        // first.previous=newNode;
        first=newNode;
    }
    public void insertLast(int val){
        size++;
        createNodeForDoublyLL newNode=new createNodeForDoublyLL(val);
        if (isEmpty()) {
            first=newNode;
        }else{
            last.next=newNode;
            newNode.previous=last;
        }
        last=newNode;
    }
    public void deleteFirst(){
        size--;
        if (first.next==null) {
            last=null;
        }else{
            //IMP
            //first ke next ke previous ko null ke tarf point kar do
            first.next.previous=null;
        }
        first=first.next;
    }
    public void deleteLast(){
        size--;
        if (first.next==null) {
            first=null;
        }else{
            last.previous.next=null;
        }
        last=last.previous;
    }
    //IMP
    public void insertAfter(int elemntAfterWhchToInsert,int elemntToInsert){
        createNodeForDoublyLL current=first;
        int cnt=0;
        while (current.data!=elemntAfterWhchToInsert) {
            cnt++;
            current=current.next;
            if (cnt==size) {//reached last and didnt find
                System.out.println("Elemnt after which u r requesting to insert not found");
                return;
            }
        }
        size++;
        createNodeForDoublyLL newNode=new createNodeForDoublyLL(elemntToInsert);
        if (current==last) {
            newNode.next=null;
            last=newNode;
        }else{
            newNode.next=current.next;
            current.next.previous=newNode;
        }
        newNode.previous=current;
        current.next=newNode;
    }
    public void delete(int val){
        createNodeForDoublyLL current=first;
        int cnt=0;
        while (current.data!=val) {
            cnt++;
            if (cnt==size) {
                System.out.println("Elemnt not found to delete");
                return;
            }
            current=current.next;
        }
        size--;
        if (current==first) {
            first=current.next;
        }else{
            current.previous.next=current.next;
        }
        if (current==last) {
            last=current.previous;
        }else{
            current.next.previous=current.previous;
        }
        System.out.println("Deleted "+val+ " succesfully");
    }
    public void displayFirstToLastInserted(){
        createNodeForDoublyLL temp=first;
        while (temp!=null) {
            temp.displayNode();
            temp=temp.next;
        }
        System.out.println();
    }
    public void displayLastToFirstInserted(){
        createNodeForDoublyLL temp=last;
        while (temp!=null) {
            temp.displayNode();
            temp=temp.previous;
        }
        System.out.println();
    }
}

public class implementationDoubly {
    public static void main(String[] args) {
      createDoublyLL deLL=new createDoublyLL();
      deLL.insertFirst(11);
      deLL.insertLast(12);
      deLL.displayFirstToLastInserted();
      deLL.displayLastToFirstInserted();
      deLL.insertAfter(12, 99);
      deLL.displayFirstToLastInserted();
      deLL.delete(11);
      deLL.displayFirstToLastInserted();
    }
}
