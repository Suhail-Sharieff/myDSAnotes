package _2_Arrays;
/*
Problem statement
There is an integer array ‘A’ of size ‘N’.



Number of inversions in an array can be defined as the number of pairs of ‘i’, ‘j’ such that ‘i’ < ‘j’ and ‘A[i]’ > ‘A[j]’.

You must return the number of inversions in the array.



For example,
Input:
A = [5, 3, 2, 1, 4], N = 5
Output:
7
Explanation: 
The pairs satisfying the condition for inversion are (1, 2), (1, 3), (1, 4), (1, 5), (2, 3), (2, 4), and (3, 4). 
The number of inversions in the array is 7.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
4 3 2 1
Sample Output 1:
6
Explanation Of Sample Input 1:
Input:
A = [4, 3, 2, 1], N = 4
Output:
6
Explanation: 
The pairs satisfying the condition for inversion are (1, 2), (1, 3), (1, 4), (2, 3), (2, 4), and (3, 4).    
The number of inversions in the array is 6.
Sample Input 2:
5
1 20 6 4 5
Sample Output 2:
5
Constraints:
1 <= N <= 10^5
1 <= A[i] <= 10^9
Time Limit: 1 sec
 */
public class _31_count_Inversions {
    public static void main(String[] args) {
        int nums[]={4, 3, 2, 1};
        System.out.println(brute(nums));
        System.out.println(optimal(nums));
    }
    public static int brute(int nums[]){//O(n^2)---O(1)
        int cnt=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]>nums[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static int cnt=0;
    public static int  optimal(int nums[]){//JUST MANIPULATE MERGE SORT
        //see for exp:
        //https://www.youtube.com/watch?v=AseUmwVNaoY&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=42
        sort(nums, 0, nums.length-1);
        //see line number 102 for key step in mergeSort
        return cnt;
    }
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                
                i++;
            }
            else {
                arr[k] = R[j];
                //KEYSTEP 
                cnt+=n1-i;
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

}
