package _3_binarySearch;
/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */
public class _4_first_last_pos {
    public static void main(String[] args) {
        System.out.print("[");
        for (int e : searchRange(new int[]{5,7,7,8,8,10}, 6)) {
            System.out.print(e);
        }
        System.out.println("]");
    }
    public static int[] searchRange(int[] nums, int target) {

        //PREREQUISITES TO KNOW: binary search (if dupliactes of say x , r present in that array) will find any random index of the x and DOESNT GUARRENTEE TO RETURN EXACTLY FIRST/LAST idx of target

        //see,how we manipultae binary search here means we try to find that particular elemnt is present, but we doent know if its first idx or last idx of that particular elemnt,so the instatnce we get any random idx of that elemnt, its possible that the first idx may be that idx itself or may be present before it and similarly that idx may be last idx or may be it lies after that idx, so to find that first idx of that elemnt we move  backwards and similarly forwards to get last idx of that elemnt this is the manipulation

        int low=0,high=nums.length-1;
        int ans[]={-1,-1};
        while(low<=high){
            int mid=(low+high)/2; 
            if(nums[mid]==target){
                int forward=mid;
                while(forward<nums.length&&nums[forward]==target){
                    ans[1]=forward;
                    forward++;
                }
                int backward=mid;
                while(backward>=0&&nums[backward]==target){
                    ans[0]=backward;
                    backward--;
                }
                
                break;
            }
            else if(nums[mid]<=target){
                low=mid+1;
            }else{
                high=mid-1;
            }

        }
        return ans;
    }
}
