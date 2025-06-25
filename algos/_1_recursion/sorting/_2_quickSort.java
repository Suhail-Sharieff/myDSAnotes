package _1_recursion.sorting;

public class _2_quickSort {


    //tc: O(nlogn)
    //sc:O(1)//coz no extra space used unlike merged sort
    static void quickSort(int arr[],int low,int high){
        if(low>=high) return;
        int pivotIdx=low;
        int i=low+1;
        int j=high;
        while(i<=j){
            while(i<=high&&arr[i]<=arr[pivotIdx]) i++;
            while(j>low&&arr[j]>arr[pivotIdx]) j--;
            if(i<=j) swap(arr, i, j);
        }
        swap(arr, low, j);
        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }

    static void swap(int arr[],int i,int j){int temp=arr[i];arr[i]=arr[j];arr[j]=temp;}

    public static void main(String[] args) {
        int arr[] = {8, 4, 1, 3, 5, 0, 2, 3};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
