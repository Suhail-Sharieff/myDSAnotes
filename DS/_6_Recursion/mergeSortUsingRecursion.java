package _6_Recursion;

import java.util.Scanner;

public class mergeSortUsingRecursion {
    private  int arr[];
    private int nElments;
    public mergeSortUsingRecursion(int max){
        arr=new int[max];
        nElments=0;
    }
    public void insert(int val){
        arr[nElments++]=val;
    }
    public void mergeSort(int size){
        int[]workSpace=new int[size];
        mergeSort(workSpace, 0, size-1);

    }
    public  void mergeSort(int workSpace[],int lowerBound,int upperBound){
        if (lowerBound==upperBound) {
            return;
        }else{
            int mid=(lowerBound+upperBound)/2;
            //sort first half array:
            mergeSort(workSpace, lowerBound, mid);
            //sort next half array:
            mergeSort(workSpace, mid+1, upperBound);
            //merge both:
            merge(workSpace,lowerBound,mid+1,upperBound);
        }
    }
    public  void merge(int workSpace[],int lowPtr,int midPtr,int highPtr){
        int i=0;
        int lowerBound=lowPtr;
        int mid=midPtr-1;
        int size=highPtr-lowPtr+1;
        while (lowPtr<=mid&&midPtr<=highPtr) {
            if (arr[lowPtr]<arr[midPtr]) {
                workSpace[i++]=arr[lowPtr++];
            }else{
                workSpace[i++]=arr[midPtr++];
            }
        }
        while (lowPtr<=mid) {
            workSpace[i++]=arr[lowPtr++];
        }
        while (midPtr<=highPtr) {
            workSpace[i++]=arr[midPtr++];
        }
        for (int j = 0; j < size; j++) {
            arr[lowerBound+j]=workSpace[j];
        }
    }
    public  void display(){
        System.out.println("Your array is: ");
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("Enter no of elemnts u wish to insert in array for sorting:");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();int i=n;
        mergeSortUsingRecursion arr=new mergeSortUsingRecursion(n);
        System.out.println("Enter elemnts: ");
        while (i--!=0) {
            int val=sc.nextInt();
            arr.insert(val);
        }
        arr.display();
        arr.mergeSort(n);
        System.out.println("After sorting:");
        arr.display();
        sc.close();
       
    }
}
