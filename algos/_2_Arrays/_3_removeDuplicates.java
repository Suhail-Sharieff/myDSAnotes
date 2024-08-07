package _2_Arrays;

import java.util.ArrayList;
import java.util.List;

public class _3_removeDuplicates {
    public static void removeDuplicatesFromSorted(int arr[]) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < arr.length - 1) {
            if (arr[i] != arr[i + 1]) {
                ans.add(arr[i]);
            }
            i++;
        }
        ans.add(arr[i]);
        System.out.println(ans);
    }

    public static void removeDuplicatesFromAnything(int arr[]) {
        int freq[] = new int[100001];
        List<Integer> ans = new ArrayList<>();
        for (int e : arr) {
            if (freq[e] == 0) {
                ans.add(e);
            }
            freq[e]++;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int sorted[] = { 1, 1, 2, 3, 4, 4, 5, 5 };
        int[] unsorted = { 5, 2, 8, 3, 7, 5, 3, 9, 2, 5, 7 };

        removeDuplicatesFromSorted(sorted);
        removeDuplicatesFromAnything(unsorted);

    }
}
