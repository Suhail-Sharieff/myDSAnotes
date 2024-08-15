package _3_binarySearch;
/*
Medium
Topics
Companies
Hint
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

 

Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:

Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
Example 3:

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 

Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times
 */
public class _6_findMinInRotArr {
    public static void main(String[] args) {
        ////READ THIS VERY GOOD EXP:https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solutions/5149850/fastest-100-easiest-shortest-binary-search-multiple-langs

    }
    public static void findMin(int nums[]){
        int low=0,high=nums.length-1;
        int min=Integer.MAX_VALUE;
        while (low<=high) {
            int mid=(low+high)/2;
            if (nums[mid]<=nums[high]) {//ie arr is correclty sorted withouth any rotation
                //so our min elemnt may be present bfr mid
                min=Math.min(min, nums[mid]);
                high=mid-1;
            }else if(nums[mid]>nums[high]){//ir its rotated by some k, and we would get min elemnt on right of mid
                low=mid+1;
                min=Math.min(min, nums[high]);
            }
        }
    }
}
