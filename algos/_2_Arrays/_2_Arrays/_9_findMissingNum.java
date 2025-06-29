package _2_Arrays;

import java.util.Arrays;

public class _9_findMissingNum {

    public static void main(String[] args) {
        int arr[] = new int[] { 1, 2, 4, 4, 5 };
        System.out.println(bruteForce(arr));
        System.out.println(optimal(arr));
    }

    public static int bruteForce(int arr[]) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] != arr[i] + 1) {
                return arr[i] + 1;
            }
        }
        return arr[arr.length - 1] + 1;
    }

   
   

    public static int optimal(int arr[]) {
        int n = arr.length-1; // because one number is missing
        int xor1 = 0, xor2 = 0;
        for (int i = 0; i < n; i++) {
            xor2 = xor2 ^ arr[i];
            xor1 = xor1 ^ (i + 1);
        }
        xor1^=n;
        return xor1 ^ xor2;
    }
}
