package _3_CircularQueue;

import java.util.Scanner;

class myQueue{
    private int nElemnts;
    private int arr[];
    private int front;
    private int rear;
    private int maxSize;
    public myQueue(int maxSize){
        this.maxSize=maxSize;
        front=0;
        rear=-1;
        arr=new int[maxSize];
        nElemnts=0;
    }
    public void insert(int e){
        //wrap arround
        if (rear==maxSize-1) {
            rear=-1;
        }
        arr[++rear]=e;
        nElemnts++;
    }
    public int removeFirst(){
        //this removes first element and returns it
        int e=arr[front++];
        if (front==maxSize) {
            front=0;
        }
        nElemnts--;
       return e;
    }
    public void display(){
        int temp=nElemnts;
        System.out.println("Queue elemnts are:");
        while (temp--!=0) {
            System.out.print(arr[temp]+" ");
        }
        System.out.println();
    }
    public int peek(){
        return arr[front];
    }
    public boolean isEmpty(){
        return (nElemnts==0);
    }
    public boolean isFull(){
        return (nElemnts==maxSize);
    }
    public int size(){
        return nElemnts;
    }
}
public class implementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter max size of Statck your computer can hold: ");
        int maxSize = sc.nextInt();
        myQueue q=
        
        new myQueue(maxSize);
        System.out.println("Enter no of elemnts u wish to push:");
        int size=sc.nextInt();
        if (size>maxSize) {
            System.out.println("No of elemnts u r tryin to insert is exceeding your computer's max capacity !");sc.close();return ;
        }
        System.out.println("Enter "+size+" elements: ");
        for (int i = 0; i < size; i++) {
            int scan=sc.nextInt();
            q.insert(scan);
        }
        q.display();
        sc.close();
    }
}
