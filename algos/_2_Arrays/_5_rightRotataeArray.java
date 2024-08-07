package _2_Arrays;

public class _5_rightRotataeArray {

    public static void rightRotateByOnePlace(int[] arr) {
        int last = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }


    public static void rightRotateByKPlaces1(int[] arr, int k) {
        while (k-- > 0) {
            rightRotateByOnePlace(arr);
        }
    }


    public static void rightRotateByKPlaces2(int[] arr, int k) {
        k = k % arr.length;
        while (k-- > 0) {
            rightRotateByOnePlace(arr);
        }
    }


    public static void rightRotateByKPlaces3(int[] arr, int k) {
        k = k % arr.length;
        int[] temp = new int[k];
        
        // Store the last `k` elements in the temporary array `temp`
        for (int i = 0; i < k; i++) {
            temp[i] = arr[arr.length - k + i];
        }
        
        // Shift elements in `arr` to the right by `k` positions
        for (int i = arr.length - 1; i >= k; i--) {
            arr[i] = arr[i - k];
        }
        
        // Copy elements from `temp` back to the start of `arr`
        for (int i = 0; i < k; i++) {
            arr[i] = temp[i];
        }
    }
    
    public static void rightRotateByKPlaces4(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        int[] arr = {11, 3, 4, 2, 1, 2, 3, 8};
        int k = 2;
        rightRotateByKPlaces4(arr, k);
        
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
}
