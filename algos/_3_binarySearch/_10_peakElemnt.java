package _3_binarySearch;
/*

WATCH COMPULOSRY EXPLAINED USING GRAPH:https://www.youtube.com/watch?v=cXxmbemS6XM&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=53


A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 

Constraints:

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.
 */
public class _10_peakElemnt {
    /*
    //SOLUTION IS PURELY BASED ON OBSERVATION:

    Given an array, we need to find the peak element.
As, the subportions of the array are increasing/decreasing ( only then we would be able to find peak ), there are subportions of array which are sorted, so we could use binary search to get this problem done. But exactly how ?

This is an interesting part.

For a mid element, there could be three possible cases :


Case 1 : mid lies on the right of our result peak ( Observation : Our peak element search space is leftside )
Case 2 : mid is equal to the peak element ( Observation : mid element is greater than its neighbors )
Case 3 : mid lies on the left. ( Observation : Our peak element search space is rightside )
    */    

    //also see from constraints that no adjacent elmts can be equal,so we dont need to deal with the = sign or duplicates
    public int findPeakElement(int[] nums) {
        //first lets deal with first and last elemnt which have only one neighbour

        //MOST IMPORTANT OBSERVATION :
        //if nums[mid]>nums[mid-1], the peak alsways lies on right side of mid, so make low=mid+1 and viceversa 
        int low=1,high=nums.length-2;
        if(nums.length==1){return 0;}
        if(nums.length==2){
            if(nums[0]>nums[1]){return 0;}else{return 1;}
        }
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1]){//thas peak elemnt
                return mid;
            }
            if(nums[mid]>nums[mid-1]){//mid is greater than prev, then peak lies on  right of mid
                low=mid+1;
            }else{//else on left of mid
                high=mid-1;
            }
        }
        //the program reaches here if it couldnt find any peak elemnts between the idx 1 & nums.length-1 (both inclusive), means we have our peak elemnt either on idx 0 or on last idx
        if(nums[0]>nums[1]){
            return 0;
        }
        if(nums[nums.length-1]>nums[nums.length-2]){
            return nums.length-1;
        }
        return -1;
    }
}
