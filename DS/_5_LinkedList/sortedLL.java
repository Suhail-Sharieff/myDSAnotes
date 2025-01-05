package _5_LinkedList;

class createSortedLL{
    public createNode first;
    public createSortedLL(){
        this.first=null;
    }
    public boolean isEmpty(){
        return first==null;
    }
    //elemnts are automatically inserted in soerted manner

    //IMP
    public void insert(int val){
        createNode newNode=new createNode(val);
        createNode current=first;
        createNode previous=null;
        //as soon as a value greater than val is found insert there
        while (current!=null&&current.data<val) {
            previous=current;
            current=current.next;
        }
        if (previous==null) {//if elemnt is found at first ie the elment to insert is smaller than all existing elemnts
            first=newNode;            
        }else{
            previous.next=newNode;
        }
        newNode.next=current;
    }
    public void removeFirst(){
        first=first.next;
    }
    public void displaySortedLL(){
        createNode temp=first;
        while (temp!=null) {
            temp.displayNode();
            temp=temp.next;
        }
        System.out.println();
    }
    
}

public class sortedLL {
    public static void main(String[] args) {
        createSortedLL sLL=new createSortedLL();
        sLL.insert(999);
        sLL.insert(88);
        sLL.insert(100);
        sLL.displaySortedLL();
        sLL.removeFirst();
        sLL.displaySortedLL();
    }
}
