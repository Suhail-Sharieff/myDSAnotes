package _4_PriorityQueue;

import java.util.Scanner;

//queue implementation having min elemens at TOP and max Elemnts at bottom(ascending prority queue)
class myPriorityQueue {
    private int maxSize;
    private int arr[];
    private int nElemts;

    public myPriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        nElemts = 0;
    }

    public void insert(int e) {
        if (nElemts == 0) {
            arr[nElemts++] = e;
        } else {
            int i;//coz u need i later
            for (i = nElemts - 1; i >= 0; i--) {
                // if item inserted is larger,it needs to be inserted at most bottom,so shift
                // all elemnts
                if (e > arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i+1]=e;
            nElemts++;
        }
    }

    public int remove() {
        // remove min (ie top) elemnt ie nElemnts-1 index
        return (arr[--nElemts]);
    }

    public int peek() {
        return arr[nElemts - 1];
    }

    public boolean isEmpty() {
        return (nElemts == 0);
    }

    public boolean isFull() {
        return (nElemts == maxSize);
    }

    public void display() {
        System.out.println("Ascending Priority Queue elemnts are:");
        int temp = nElemts;
        while (temp-- != 0) {
            System.out.print(arr[temp] + " ");
        }
        System.out.println();
    }
}

public class Ascendingimplementation {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        System.out.println("Enter max size of Queue your computer can hold: ");
        int maxSize = sc.nextInt();
        myPriorityQueue mpq=new myPriorityQueue(maxSize);
        System.out.println("Enter no of elemnts u wish to push in ANY order :");
        int size=sc.nextInt();
        if (size>maxSize) {
            System.out.println("No of elemnts u r tryin to insert is exceeding your computer's max capacity !");sc.close();return ;
        }
        System.out.println("Enter "+size+" elements: ");
        for (int i = 0; i < size; i++) {
            int scan=sc.nextInt();
            mpq.insert(scan);
        }
        mpq.display();
        sc.close();
    }
}
