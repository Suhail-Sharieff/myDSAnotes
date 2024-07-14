package _1_recursion.sorting;

public class _2_quickSort {


    //tc: O(nlogn)
    //sc:O(1)//coz no extra space used unlike merged sort
    public static void func(int arr[], int lowIdx, int highIdx){
        if (lowIdx >= highIdx) {
            return;
        }
        int pivotIdx = placeCorrectly(arr, lowIdx, highIdx);
        func(arr, lowIdx, pivotIdx - 1);
        func(arr, pivotIdx + 1, highIdx);
    }
    
    public static int placeCorrectly(int arr[], int lowIdx, int highIdx) {
        int pivotE = arr[lowIdx];
        int i = lowIdx + 1;  // start from the next element after pivot
        int j = highIdx;
        
        while (i <= j) {
            // finding element >= pivot from start
            while (i <= highIdx && arr[i] <= pivotE) {
                i++;
            }
            // finding element < pivot from end
            while (arr[j] > pivotE) {
                j--;
            }
            // swap if i and j have not crossed each other
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        
        // place the pivot element in its correct position
        int temp = arr[lowIdx];
        arr[lowIdx] = arr[j];
        arr[j] = temp;

        // return the pivot index
        return j;
    }

    public static void main(String[] args) {
        int arr[] = {8, 4, 1, 3, 5, 0, 2, 3};
        func(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
