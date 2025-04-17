/*
2537. Count the Number of Good Subarrays
Solved
Medium
Topics
Companies
Hint
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i], k <= 109
 */

 package _10_Sliding_Window._02_dynamic_window;
 import java.util.*;
 
 public class _08_nOfGood_subarr {
 
    public static void main(String[] args) {
        
    }

    public long countGood(int[] nums, int k) {

        HashMap<Integer,Integer> freq=new HashMap<>();


        int i=0,j=0;

        long ans=0;

        long nPairsInWindow=0;

        while(j<nums.length){
            
            nPairsInWindow+=freq.getOrDefault(nums[j],0);
            freq.put(nums[j],freq.getOrDefault(nums[j],0)+1);

            if(nPairsInWindow>=k){
                while(nPairsInWindow>=k){ //shrink
                     ans+=(nums.length-j);//including that window and with each further elemnt
                    freq.put(nums[i],freq.get(nums[i])-1);
                    nPairsInWindow-=freq.get(nums[i]);
                    i++;
                }
            }

            j++;

        }


        return ans;
    }
 }