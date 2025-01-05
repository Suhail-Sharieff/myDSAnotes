package _9_HashTables;

import java.util.Scanner;

//The main problem with Twitter the linear probing processor is that it is not that much significant when large number of clusters or large number of elements are going to form the cluster in a linear hash table so the basic idea in order to insert the element having the same summation value is basically to shift it instead of the nearest vacant cell we can shift it to the squares of the numbers probe length that is if it is having the same summation value instead of shifting that particular element into the nearest vACANT cell we put shifted into a distance of 1 if it is if it is not empty then shifted the distance of two square that is 4 and even if it is not empty then 3 square that is 9 and so on.....This helps to deal with even large load factor

//LOAD FACTOR=NoOfItems/arrSize


//But this has another limitation that is if the number of elements are the keys which are hashing to the same index are extremely large then again and again we are checking at the problance 1,4,9,16 etc

// So So there needs to be a solution for this and it can be obtained by using the double hashing process

// So in this particular method we generate the probe sequences that is 1 416 that depend upon the key instead of being same for every key, then numbers with different keys that hash to the same index will use different probe sequences

// So the secondary hash function must have certain characteristics
//1)It must not be the same as that of the primary hash function
//2)It must never output a zero that would lead to every probe landing on the same cell and an infinite loop

//secondary hash function of this type works well :
//stepSizeToJump=constant-(key%constant), where constant is primeConstantLessThanArraySize
//ex: stepSizeToJump=5-(key%5)

//this can also be used to search a key in hashTable ,instaed of searching evry key,we jump by that key's step size to search


public class quadraticProbing {
    public dataItem[] hashArray;
    public int arrSize;
    public dataItem nonItem;// we will assign it as -1 to help deletion

    public quadraticProbing(int size) {
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
    public int hasHFunction1(int valToInsert) {// returns index where it should we inserted in array
        return valToInsert % arrSize;
    }
    public int hasHFunction2(int valToInsert) {// returns index where it should we inserted in array whose size must be relatiely prime to 5,4,3,2
        return 5-valToInsert % 5;
    }
    public void insert(int key,dataItem val) {
        // assumes table is not full,otherwise implement wrap around also
        int indexToInsert=hasHFunction1(key);
        int stepSize=hasHFunction2(key);
        // insert in a place which is empty and not equal to -1(which means it was
        // deleted place)
        while (hashArray[indexToInsert] != null && hashArray[indexToInsert].data != -1) {
           indexToInsert+=stepSize;
           indexToInsert%=arrSize;//wrap around
        }
        hashArray[indexToInsert] = val;
        System.out.println("Inserted successfully, modified hs is:");
        displayTable();

    }
    public void delete(int val) {
        int indexOfVal = hasHFunction1(val);
        int stepSize=hasHFunction2(val);
        while (hashArray[indexOfVal] != null) {// until found some empty cell
            if (hashArray[indexOfVal].data == val) {
                hashArray[indexOfVal] = nonItem;
                System.out.println("Deleted " + val + " successfully");
                System.out.println("Modified table");
                displayTable();
                return;// found
            }
            indexOfVal+=stepSize;
            indexOfVal%=arrSize;//wrap around
        }
        System.out.println("Element " + val + " not found to delete");
        System.out.println("Modified table");
        displayTable(); // Display table even if element is not found
    }
    public void find(int val) {
        int indexOfVal = hasHFunction1(val);
        int stepSize=hasHFunction2(val);
        while (hashArray[indexOfVal] != null) {
            if (hashArray[indexOfVal].data == val) {
                System.out.println(val + " found at " + indexOfVal);
                return;
            }
            indexOfVal+=stepSize;
            indexOfVal%=arrSize;//wrap around
        }
        System.out.println(val + " not found");
    }
    public static void main(String[] args) {
         System.out.println("Enter max size computer can store");
        Scanner sc = new Scanner(System.in);
        int maxSize = sc.nextInt();
        quadraticProbing hs = new quadraticProbing(maxSize);
        System.out.println("Enter POSITIVE" + maxSize + " elements");
        for (int i = 0; i < maxSize; i++) {
            int e = sc.nextInt();
            hs.insert((int)java.lang.Math.random()*2*maxSize,new dataItem(e));
        }

        hs.delete(4);
        hs.find(1);

        sc.close();
    }
}
