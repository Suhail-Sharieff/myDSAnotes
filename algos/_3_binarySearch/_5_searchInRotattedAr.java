package _3_binarySearch;

/*
-------------------------func1------------------------------------------------
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
 */
public class _5_searchInRotattedAr {
    public static void main(String[] args) {
        //WATCH https://www.youtube.com/watch?v=5qGrJbHhqFs&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=48
        //func 1 when we have disticnt elemnts
    }

    public static int func1(int nums[],int target){//check for the sorted portion and do wrt it
        int low=0,high=nums.length-1;
        while (low<=high) {
            int mid=(low+high)/2;
            if (nums[mid]==target) {
                return mid;
            }
            //check if left side has sorted portion,then vice versa case of binary for right sorted ie normal sorted
            if (nums[low]<=nums[mid]) {
                if (nums[low]<=target&&target<=nums[mid]) {
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else{//if right potion we have sorted 
                if (nums[mid]<=target&&target<=nums[high]) {//same as normal
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        return -1;
    }


    //------------------------------------------------func2--------with duplicates in rotSortedArr------------------------------------------------

/*
 * 
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

 

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 

Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */

    public static boolean func2(int nums[],int target){//same as func1 but we have to handle the case where nums[mid]==nums[low]==nums[high] for ex in [1,0,1,1,1]
        int low=0,high=nums.length-1;
        while (low<=high) {
            int mid=(low+high)/2;
            if (nums[mid]==target) {
                return true;
            }
            //edge case IMPORTANCE OF THIS PROBLEM:
            else if(nums[low]==nums[mid]&&nums[mid]==nums[high]){
                //check with the idx next to it for low,and bfr it for high
                low=low+1;
                high=high-1;
            }else if(nums[low]<=nums[mid]){
                if (nums[low]<=target&&target<=nums[mid]) {
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else{
                if (nums[mid]<=target&&target<=nums[high]) {
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            
        }
        return false;
    }

}
