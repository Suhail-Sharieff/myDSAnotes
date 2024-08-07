package _2_Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _11_longestSubarrWithSumK {//contigous only

//https://www.youtube.com/watch?v=frf7qxiN2qU&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=20

    public static void main(String[] args) {
        int arr[]=new int[]{1,2,3,1,1,1,1,4,2,3};
        int requiredSum=3;
        bruteForce(arr, requiredSum);
        better(arr, requiredSum);
        optimal(arr, requiredSum);
      
    }
    

    public static void bruteForce(int arr[],int requiredSum){//O(n^3)---O(1)
        //to get all subarrays in order using 2 loops and then finding of each's sum is equal to requiredSum
        int len=arr.length;
        int maxLen=0;
        for (int i = 0; i <len; i++) {
            for (int j = i; j < len; j++) {
                int sum=0;
                for (int k = i; k < j; k++) {
                    sum+=arr[k];
                }
                if (sum==requiredSum) {
                    System.out.print("Possible subArr: ");
                    //print that subarr
                    for (int k = i; k < j; k++) {
                        System.out.print(arr[k]+" ");
                    }
                    System.out.println();
                    maxLen=Math.max(maxLen, j-i);
                }
            }
        }
        System.out.println("maxLen : "+maxLen);
    }


    //this is optimal if arr has -ve also
    public static void better(int[] arr, int requiredSum) {//O(nLogn)---O(n)
         //this is based on the idea that for ith elemnt in array ,if the subarray till i containes sum sumTilli-reqsum then we have got one more subarray with sum reqsum 
        // watch yt TUF using the link for clarity
        // Use HashMap for efficient prefix sum storage
       Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, -1); // Handle empty subarray with sum 0

        long currentSum = 0;
        int maxLength = 0;
        List<Integer> currentSubarray = new ArrayList<>(); // Track current subarray

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            currentSubarray.add(arr[i]);  // Add current element to subarray

            // Check if subarray with sum k is found
            if (prefixSumMap.containsKey(currentSum - requiredSum)) {
                int startIndex = prefixSumMap.get(currentSum - requiredSum) + 1;
                int endIndex = i;

                // Print the subarray
                System.out.println("Subarray with sum " + requiredSum + " idx : " + startIndex + " to " + endIndex);

                // Update maxLength if a longer subarray is found
                maxLength = Math.max(maxLength, endIndex - startIndex + 1);
            }

            // Store prefix sum and corresponding index for potential future use
            prefixSumMap.putIfAbsent(currentSum, i);

            // Clear subarray for next iteration (optional, depends on desired behavior)
            currentSubarray.clear(); // Clear current subarray after finding a match
        }

        if (maxLength == 0) {
            System.out.println("No subarray found with sum " + requiredSum);
            return;
        }
        System.out.println("maxlen: "+maxLength);
    }

    //optimal only if arr has +ves & 0s
    //2 pointer and greedy approach
    public static void optimal(int arr[],int requiredSum){//O(2n)---O(1)
        //start summing from idx 0, the point where the sum exceeds the reqSum,we trim the subarray we had considered by remving the first elemnt in that subarray
        int len=arr.length;
        int maxLen=0;
        int rightPtr=0,leftPtr=0;
        long sum=0;
        while (rightPtr<len) {
            //if sum exceeds reqSum we remove first in that subarr and increse leftPtr
            while (leftPtr<=rightPtr&&sum>requiredSum) {
                sum-=arr[leftPtr];
                leftPtr++;
            }
            if (sum==requiredSum) {
                maxLen=Math.max(maxLen, rightPtr-leftPtr);
                System.out.println("Subarr from idx :"+leftPtr+" to "+rightPtr);
            }
            //move right and then add 
            rightPtr++;
            if (rightPtr<len) {
                sum+=arr[rightPtr];
            }
        }

        System.out.println("max len : "+maxLen);
    }
   
}
