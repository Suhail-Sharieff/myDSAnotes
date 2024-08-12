package _2_Arrays;

import java.util.HashMap;

/*
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

 

Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
Example 2:

Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 

Constraints:

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
 

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */
public class _30_findMissing {
    public static void main(String[] args) {
        int nums[]={9,6,4,2,3,5,7,0,1};
        System.out.println(brute(nums));
        System.out.println(optimal(nums));
    }
    public static int brute(int[] nums) {//O(n)----O(n)
        HashMap<Integer,Integer>hs=new HashMap<>();
        for(int e:nums){
            hs.put(e,hs.getOrDefault(e,0)+1);
        }
        for(int i=0;i<=nums.length+1;i++){
            if(hs.getOrDefault(i,0)==0){
                return i;
            }
        }
        System.out.println(hs);
        return -1;
        
    }
    public static int optimal(int[] nums) {
        int numsLength = nums.length;
        // initialize the cursor
        int i = 0;

        // phase 1: sort the numbers with cyclic sort
        // move the cursor through the entire list
        while (i < numsLength) {
            int valAtI = nums[i];

            // does the value belong in the range of the list?
            // if it doesn't, we get an out of bounds error
            // when we try to access nums[valAtI] later
            boolean belongsInRange = valAtI < numsLength;

            if (belongsInRange && valAtI != nums[valAtI]) {
                int temp = nums[i];
                nums[i] = nums[valAtI];
                nums[valAtI] = temp;
            } else {
                // move the cursor to the next index
                i++;
            }
        }

        // phase 2: find the missing number
        for (int x = 0; x < numsLength; x++) {
            if (x != nums[x]) {
                return x;
            }
        }

        // if all the numbers are already present,
        // the missing number is the length of the list
        return numsLength;
    }
}
