package _3_CircularQueue;
class createNodequeue{
    public int data;
    public createNodequeue next;
    public createNodequeue(int val){
        this.data=val;
        this.next=null;
    }
    public void displayNode(){
        System.out.print(data+" ");
    }
}
class queueUsingLL{
    //create last also coz to implement wrap around
    private createNodequeue first;
    private createNodequeue last;
    public queueUsingLL(){
        this.first=null;
        this.last=null;
    }
    public boolean isEmpty(){
        return (first==null);
    }
    //elemnt inserted at last as its queue
    public void insert(int e){
        createNodequeue newNode=new createNodequeue(e);
        //IMP
        if (isEmpty()) {
            first=newNode;
        }else{
            last.next=newNode;            
        }
        last=newNode;
    }
    public void removeFirst(){
        if (first.next==null) {
            last=null;
        }
        first=first.next;
    }
    public void displayQueue(){
        createNodequeue temp=first;
        while (temp!=null) {
            temp.displayNode();
            temp=temp.next;
        }
        System.out.println();
    }

}

public class impleUsingLL {
    public static void main(String[] args) {
        queueUsingLL q=new queueUsingLL();
        q.insert(21);
        q.insert(90);
        q.insert(11231);
        q.displayQueue();
        q.removeFirst();
        q.displayQueue();//FIFO
    }
}
