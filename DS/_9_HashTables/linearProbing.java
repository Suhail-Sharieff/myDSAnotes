package _9_HashTables;

import java.util.Scanner;

/**
 * linearProbing refers to the process of probing in which we add the element which is getting collided or forming the cluster into the place which is having the vacancy
 */

// lets store the data in the form of objects rather than of form dirrect int
// array,coz suppose in future we want to store floats and not ints, then we can
// just modify in this class
class dataItem {
    public int data;

    public dataItem(int data) {
        this.data = data;
    }
}

class hashTableUsingLinearProbe {
    public dataItem[] hashArray;
    public int arrSize;
    public dataItem nonItem;// we will assign it as -1 to help deletion

    public hashTableUsingLinearProbe(int size) {
        this.arrSize = size;
        this.hashArray = new dataItem[size];
        nonItem = new dataItem(-1);
    }

    public void displayTable() {
        for (int i = 0; i < arrSize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i].data + " ");
            } else {
                System.out.print(null + " ");
            }
        }
        System.out.println();
    }

    public int hasHFunction(int valToInsert) {// returns index where it should we inserted in array
        return valToInsert % arrSize;
    }

    public void insert(dataItem val) {
        // assumes table is not full,otherwise implement wrap around also
        int key = val.data;
        int indexToInsert = hasHFunction(key);
        // insert in a place which is empty and not equal to -1(which means it was
        // deleted place)
        while (hashArray[indexToInsert] != null && hashArray[indexToInsert].data != -1) {
            ++indexToInsert;
            // wrap around if necessary
            indexToInsert  %= arrSize;
        }
        hashArray[indexToInsert] = val;
        System.out.println("Inserted successfully, modified hs is:");
        displayTable();

    }

    public void delete(int val) {
        int indexOfVal = hasHFunction(val);
        while (hashArray[indexOfVal] != null) {// until found some empty cell
            if (hashArray[indexOfVal].data == val) {
                hashArray[indexOfVal] = nonItem;
                System.out.println("Deleted " + val + " successfully");
                System.out.println("Modified table");
                displayTable();
                return;// found
            }
            ++indexOfVal;
            // wrap around if necessary
            indexOfVal  %= arrSize;
        }
        System.out.println("Element " + val + " not found to delete");
        System.out.println("Modified table");
        displayTable(); // Display table even if element is not found
    }
    
    public void find(int val) {
        int indexOfVal = hasHFunction(val);
        while (hashArray[indexOfVal] != null) {
            if (hashArray[indexOfVal].data == val) {
                System.out.println(val + " found at " + indexOfVal);
                return;
            }
            ++indexOfVal;
            // wrap around if necessary
            indexOfVal  %= arrSize;
        }
        System.out.println(val + " not found");
    }
}

public class linearProbing {
    public static void main(String[] args) {
        System.out.println("Enter max size computer can store");
        Scanner sc = new Scanner(System.in);
        int maxSize = sc.nextInt();
        hashTableUsingLinearProbe hs = new hashTableUsingLinearProbe(maxSize);
        System.out.println("Enter POSITIVE" + maxSize + " elements");
        for (int i = 0; i < maxSize; i++) {
            int e = sc.nextInt();
            hs.insert(new dataItem(e));
        }

        hs.delete(4);
        hs.find(1);

        sc.close();
    }
}