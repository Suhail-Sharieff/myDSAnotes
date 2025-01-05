package _5_LinkedList;

class createDoubleEndedLL{
    //Here the difference between the normal linked list and the double ended link list is that element can be easily inserted at the end  of the linked list very easily unlike the singly linked list in which we had to search for the end for inserting an element at the end . This is achieved by storing the reference to the first and last of the linked list.
    public createNode first;
    public createNode last;
    public createDoubleEndedLL(){
        this.first=null;
        this.last=null;
    }
    public boolean isEmpty(){
        return (first==null);
    }
    public void insertFirst(int val){
        createNode newNode=new createNode(val);
        //when insering first time
        if(isEmpty()){
            last=newNode;
        }
        newNode.next=first;
        first=newNode;
    }
    public void insertLast(int val){
        createNode newNode=new createNode(val);
        if (isEmpty()) {
            first=newNode;
        }
        last.next=newNode;
        last=newNode;
    }
    public void deleteFirst(){
        //if only one item
        if(first.next==null){
            last=null;//to take care of last ref also
        }
        first=first.next;
    }
    public void displayDoubleEndedLL(){
        createNode temp=first;
        System.out.print("Last Inserted (first elemnt) ");
        while (temp!=null) {
            temp.displayNode();;
            temp=temp.next;
        }
        System.out.println(" <-- First Inserted (Last elemnt) ");
    }
}
public class implementationDouble_Ended_LL {
    public static void main(String[] args) {
        createDoubleEndedLL dLL=new createDoubleEndedLL();
        dLL.insertFirst(23432);
        dLL.insertFirst(45);
        dLL.insertLast(999);
        dLL.displayDoubleEndedLL();
        dLL.deleteFirst();
        dLL.displayDoubleEndedLL();
    }
}
