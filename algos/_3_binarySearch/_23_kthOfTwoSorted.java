/*
Problem statement
You're given two sorted arrays 'arr1' and 'arr2' of size 'n' and 'm' respectively and an element 'k'.



Find the element that would be at the 'kth' position of the combined sorted array.



Position 'k' is given according to 1 - based indexing, but arrays 'arr1' and 'arr2' are using 0 - based indexing.



For example :
Input: 'arr1' = [2, 3, 45], 'arr2' = [4, 6, 7, 8] and 'k' = 4
Output: 6
Explanation: The merged array will be [2, 3, 4, 6, 7, 8, 45]. The element at position '4' of this array is 6. Hence we return 6.
 */

package _3_binarySearch;

import java.util.ArrayList;

/**
 * _23_kthOfTwoSorted
 */
public class _23_kthOfTwoSorted {

     public static int brute(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m, int k) {//O(M+N)--O(M+N)
        // Write your coder here
         // make a combined array from both sorted arrays and then find median
        int z[] = new int[arr1.size() + arr2.size()];
        int i = 0, j = 0;
        int q = 0;
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                z[q++] = arr1.get(i++);
            } else {
                z[q++] = arr2.get(j++);
            }
        }
        while (i < arr1.size()) {
            z[q++] = arr1.get(i++);
        }
        while (j < arr2.size()) {
            z[q++] = arr2.get(j++);
        }
        return z[k-1];
    }

    //its just modification of find median of two sorted problem, changes in low,mid and condition
    public static int optimal(ArrayList<Integer> nums1, ArrayList<Integer> nums2, int n, int m, int k){
        // Ensure nums1 is the smaller array
        if (n > m) {
            return optimal(nums2, nums1, m, n, k);
        }

        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = k - mid1;

            // Get values for boundary conditions
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1.get(mid1 - 1);
            int r1 = (mid1 == n) ? Integer.MAX_VALUE : nums1.get(mid1);
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2.get(mid2 - 1);
            int r2 = (mid2 == m) ? Integer.MAX_VALUE : nums2.get(mid2);

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }

        return -1; // In case there is no valid k-th element (although this should not happen with correct inputs)
    }
}

    
