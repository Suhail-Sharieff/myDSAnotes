
package _1_recursion.sorting;

public class _5_heap_sort {
     static void heapSort(int arr[]){
        for(int i=arr.length/2-1;i>=0;i--) heapify(arr, i);
        for(int i=arr.length-1;i>=0;i--){
            swap(arr, 0, i);
            heapify(arr, 0);
        }
    }
    static void heapify(int arr[],int i){
        int left=2*i+1;
        int right=2*i+2;
        int largestElemntKaIdx=i;
        if(left<arr.length && arr[left]>arr[largestElemntKaIdx]) largestElemntKaIdx=left;
        if(right<arr.length && arr[right]>arr[largestElemntKaIdx]) largestElemntKaIdx=right;
        if(largestElemntKaIdx!=i){
            swap(arr, i, largestElemntKaIdx);
            heapify(arr, largestElemntKaIdx);
        }
    }

    static void swap(int arr[],int i,int j){int t=arr[i];arr[i]=arr[j];arr[j]=t;}

}
