package _2_Stack;
class createNodestack{
    public int data;
    public createNodestack next;
    public createNodestack(int data){
        this.next=null;
        this.data=data;
    }
    public void displayNode(){
        System.out.print(data+",");
    }
}
class stackUsingLL{
    private createNodestack first;
    public int  stackSize;
    public stackUsingLL(){
        this.first=null;
        this.stackSize=0;
    }
    public boolean isEmpty(){
        return (first==null);
    }
    public void push(int val){
        createNodestack newNode=new createNodestack(val);
        newNode.next=first;
        first=newNode;
        stackSize++;
    }
    public int pop(){
        createNodestack temp=first;
        first=first.next;
        stackSize--;
        return temp.data;
    }
    public void displayStack(){
        createNodestack temp=first;
        while (temp!=null) {
            temp.displayNode();;
            temp=temp.next;
        }
        System.out.println();
    }
    public int peek(){
        return first.data;
    }
}
public class impleUsingLL {
    public static void main(String[] args) {
        stackUsingLL st=new stackUsingLL();
        st.push(23);
        st.push(23123);
        st.push(2332);
        st.displayStack();
        st.pop();
        st.peek();
        st.displayStack();
    }    
}
