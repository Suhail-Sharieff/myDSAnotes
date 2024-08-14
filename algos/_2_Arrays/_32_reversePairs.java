/*
 * 
Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:

0 <= i < j < nums.length and
nums[i] > 2 * nums[j].
 

Example 1:

Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
Example 2:

Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
(2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 

Constraints:

1 <= nums.length <= 5 * 104
-231 <= nums[i] <= 231 - 1
 */

 package _2_Arrays;

import java.math.BigInteger;

/**
  * _32_reversePairs
  */
 public class _32_reversePairs {
 
    public static int GLOBAL_CNT=0;
    public static void main(String[] args) {
        int nums[]={2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        sort(nums, 0, nums.length-1);
        System.out.println(GLOBAL_CNT);
        
    }

    public static int brute(int nums[]){//used BigInteger coz the nums values r huge ----passes only 32/140 testCases
        int cnt=0;
        int len=nums.length;;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                BigInteger x=BigInteger.valueOf(nums[i]);
                BigInteger y=BigInteger.valueOf(nums[j]);
                BigInteger v=y.multiply(BigInteger.TWO);
                int l=x.compareTo(v);
                if (l==1) {
                    cnt++;
                }
            }
        }
        return(cnt);
    }

    //OPTIMAL SOLN IS MANUPULATION IN MERGE SORT:
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

            cntPairs(arr, l, m, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    static void cntPairs(int nums[],int low,int mid,int high){
        int right=mid+1;
        for (int i = low; i <=mid+1; i++) {
            while (right<=high&&nums[i]>(long)(2*nums[right])) {
                right++;
            }
            GLOBAL_CNT++;
        }

    }
 }