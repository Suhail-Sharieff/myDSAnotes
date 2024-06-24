package _5_LinkedList;

//adding left side for insertFirst from first
//then pointing to right
 class createNode {
    public int data;
    public createNode next;

    public createNode(int data) {
        this.data = data;
        this.next = null;
    }

    public void displayNode() {
        System.out.print(" --> " + data);
    }
}

class createLinkedList {
    private createNode first;
    private int size = 0;

    public createLinkedList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int data) {
        createNode newNode = new createNode(data);
        newNode.next = first;
        first = newNode;
        size++;
    }

    public createNode deleteFirst() {
        createNode temp = first;
        first = first.next;
        size--;
        return temp;
    }

    public void displayLinkedList() {
        System.out.println("Your linked list from first to last is:");
        createNode link = first;
        while (link != null) {
            link.displayNode();
            link = link.next;
        }
        System.out.println();
    }

    public int indexOf(int val) {
        createNode temp = first;
        int index = 0;
        while (temp.data != val) {
            temp = temp.next;
            index++;
            if (index == size) {//not found 
                return -1;
            }
        }
        return index;
    }
    public int sizeOfLL() {
        return size;
    }
    public void delete(int val){
        createNode current=first;
        createNode previous=first;
        int count=0;
        while (current.data!=val) {
            count++;
            if(count==size){
                System.out.println("Element "+val+" not found to delete");
                return ;
            }
            previous=current;
            current=current.next;
        }
        //if first link,change it
        if (current==first) {
            first=first.next;
        }
        else{
            previous.next=current.next;
        }
        System.out.println("Element "+val+" deleted succesfully");
    }
    public boolean isPresent(int val){
        if (indexOf(val)!=-1) {
            return false;
        }
        return true;
    }
}

public class implementationSingly {
    public static void main(String[] args) {
        createLinkedList LL = new createLinkedList();
        LL.insertFirst(23);
        LL.insertFirst(123);
        LL.insertFirst(243);
        LL.insertFirst(253);
        LL.displayLinkedList();
        LL.delete(123);
        LL.displayLinkedList();
    }
}
