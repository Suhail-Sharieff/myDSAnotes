import java.util.Scanner;

public class sorting {

    //MERGE SORT: Merge sort only works when we have two sorted arrays... the basic idea is that two sorted arrays are merged into a single array and its elements are copied into the final array
    public static void mergeSort(int a[],int b[]){
        int aSize=a.length;
        int bSize=b.length;
        int aIdx=0,bIdx=0;
        int ans[]=new int[aSize+bSize];
        int ansIdx=0;
        while (aIdx<aSize&&bIdx<bSize) {//copy smaller elemnt among a[i]&b[i] in ans
            if (a[aIdx]<b[bIdx]) {
                ans[ansIdx++]=a[aIdx++];
            }else{
                ans[ansIdx++]=b[bIdx++];
            }
        }
        //if b is exhausted and a still has elemnts
        while (aIdx<aSize) {
            ans[ansIdx++]=a[aIdx++];
        }
        // if a is exhausted and b still has elemnts
        while (bIdx<bSize) {
            ans[ansIdx++]=b[bIdx++];
        }
        System.out.println("Final merged and sorted array is: ");
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            // Move elements of array[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        System.out.println("Sorted array is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("Sorted array is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
public static void bubbleSort(int array[]){
    int size=array.length;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size-i-1; j++) {
            if (array[j] > array[j + 1]) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
    System.out.println("Sorted array is: ");
    for (int i = 0; i < size; i++) {
        System.out.print(array[i]+" ");
    }
    System.out.println();
}

    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter array size:");
      int size=sc.nextInt();
        int arr[]=new int[size];
        System.out.println("Enter "+size+" elements:");
        for (int i = 0; i < size; i++) {
            arr[i]=sc.nextInt();
        } 
        System.out.println("Your array is: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]+" ");
        } 
        System.out.println();
        System.out.println("USING BUBBLE SORT");
        bubbleSort(arr);
        System.out.println("USING SELECTION SORT");
        selectionSort(arr);
        System.out.println("USING INSERTION SORT");
        insertionSort(arr);
        sc.close();
        int a[]={1,3,5,7,9},b[]={2,4,6,8,10};
        System.out.println("MERGE SORTING A & B");
        mergeSort(a, b);
    }
}
