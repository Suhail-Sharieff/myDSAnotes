package _2_Stack;
import java.util.Scanner;

class myStack{
    private int top;
    private int arr[];
    private  int maxSize;
    public myStack(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
        top=-1;
    }
    public void push(int value){
        arr[++top]=value;
    }
    public int peek(){
        if (top==-1) {
            System.out.println("Stack is empty");
            return 0;
        }
        return arr[top];
    }
    public void display(){
        if (top==-1) {
            System.out.println("Stack is Empty");return ;
        }
        int temp=top;
        System.out.println("Stack Elemnts are:");
        while (temp!=-1) {
            System.out.print(arr[temp]+" ");
            temp--;
        }
        System.out.println();
    }
    public int pop(){
        return arr[top--];
    }
    public boolean isEmpty(){
        return (top==-1);
    }
    public boolean isFull(){
        return(top==maxSize-1);
    }
}
public class implementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter max size of Statck your computer can hold: ");
        int maxSize = sc.nextInt();
        myStack st=new myStack(maxSize);
        System.out.println("Enter no of elemnts u wish to push:");
        int size=sc.nextInt();
        if (size>maxSize) {
            System.out.println("No of elemnts u r tryin to insert is exceeding your computer's max capacity !");sc.close();return ;
        }
        System.out.println("Enter "+size+" elements: ");
        for (int i = 0; i < size; i++) {
            int scan=sc.nextInt();
            st.push(scan);
        }
        
        System.out.println("Before popping:");
        st.display();

       System.out.println("if popped, the popped number is "+st.pop());
       System.out.println("After popping:");
       st.display();

       sc.close();
    }
}
