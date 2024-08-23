package _3_binarySearch;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * 
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 * 
 * Constraints:
 * 
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class _22_medianOfSorted {

    public static double brute(int[] nums1, int[] nums2) {// O(M+N)-----O(M+N)

        // make a combined array from both sorted arrays and then find median
        int k[] = new int[nums1.length + nums2.length];
        int i = 0, j = 0;
        int q = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                k[q++] = nums1[i++];
            } else {
                k[q++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            k[q++] = nums1[i++];
        }
        while (j < nums2.length) {
            k[q++] = nums2[j++];
        }

        if (k.length % 2 != 0) {
            return k[k.length / 2];
        }
        return (double) ((k[k.length / 2] + k[(k.length / 2) - 1]) / 2d);
    }


    /*
The approach to finding the median of two sorted arrays is based on binary search. The main idea is to partition the two arrays into two halves in such a way that the elements in the left half are less than or equal to the elements in the right half. By doing so, we ensure that the median lies either at the boundary of the two halves or within the halves.

Here's a step-by-step explanation of the approach:

Ensure nums1 is the smaller array: To simplify the algorithm, we choose the smaller array (nums1) for binary search. If nums2 is smaller, we swap the arrays and their sizes.

Initialize variables: We initialize left and right as the search bounds within nums1. The variable mid represents the midpoint of the combined array of nums1 and nums2, which is calculated as (m + n + 1) / 2, where m is the size of nums1, and n is the size of nums2.

Binary search: We perform a binary search on nums1. In each iteration, we calculate partition1 as the midpoint of the current search interval [left, right]. We also calculate partition2 as mid - partition1, which corresponds to the partition point in nums2.

Determine the elements at the partitions: We calculate maxLeft1, minRight1, maxLeft2, and minRight2 to represent the elements around the partitions in both arrays.

maxLeft1 is the maximum element to the left of partition1 in nums1.
minRight1 is the minimum element to the right of partition1 in nums1.
maxLeft2 is the maximum element to the left of partition2 in nums2.
minRight2 is the minimum element to the right of partition2 in nums2.
Check the partition conditions: We compare these elements to determine if we have found the correct partitions such that maxLeft1 <= minRight2 and maxLeft2 <= minRight1. If these conditions hold, we have found the correct partitions, and we can calculate the median based on the parity of the combined array's length.

If the combined array has an even length, the median is the average of the maximum of the left elements (maxLeft1 and maxLeft2) and the minimum of the right elements (minRight1 and minRight2).
If the combined array has an odd length, the median is simply the maximum of the left elements (maxLeft1 and maxLeft2).
Adjust the search bounds: If the conditions are not met, we adjust the search bounds based on the comparison results. If maxLeft1 is greater than minRight2, we move the right boundary to the left (right = partition1). Otherwise, we move the left boundary to the right (left = partition1 + 1).

Repeat the binary search until we find the correct partitions or the search interval [left, right] is exhausted.

Return the median: Finally, we return the calculated median
     */
    public static double optimal(int nums1[], int nums2[]) {//O(log(m+n))----O(1)
        int len1 = nums1.length;
        int len2 = nums2.length;

        // Ensure nums1 is the smaller array
        if (len1 > len2) {
            return optimal(nums2, nums1);
        }

        int low = 0, high = len1;
        int halfLength = (len1 + len2 + 1) / 2;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = halfLength - mid1;

            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int r1 = (mid1 == len1) ? Integer.MAX_VALUE : nums1[mid1];
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r2 = (mid2 == len2) ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                // Found the correct partition
                if ((len1 + len2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = mid1 - 1;//take less number of elemnts from first arr
            } else {
                low = mid1 + 1;//take more
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted correctly or invalid input.");

    }

}
