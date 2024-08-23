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
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted correctly or invalid input.");

    }

}
