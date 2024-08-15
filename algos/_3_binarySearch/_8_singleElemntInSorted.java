package _3_binarySearch;

import _9_HashTables.linearProbing;

/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
 */
public class _8_singleElemntInSorted {
    public static void main(String[] args) {
        // int nums[] = { 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9 };

    }

    public static int brute(int nums[]) {// using properties : a ^ a=0 && 0 ^ x = x
        int xor = 0;
        for (int i : nums) {
            xor ^= nums[i];
        }
        return xor;
    }

    // optimal:


    //striver optimal1:

    public static int optimal1(int nums[]){
        // obs 1:  all idx pairs bfr missing r in format (evenIdx,oddIdx) and vice versa after the oddIdx of missing Elemnt
        //obs 2: if some elemnt is the misssing elemnt,it should have elemnts both diffreent than that elemnt on its left and right, or else if its not the missing elemnt, any one of right or left should be equal to it, more particularly if we r looking at some even indexed elemnt, it should have same elemnt on right and for odd its on left,
        int low=1,high=nums.length-2;//read next line to understand why 1 is taken as low and len-2 as high
        //now,since we r comparing for some idx, values at idx-1&&idx+1 ie left and right,lets handle first and last case separately and also the case where nums.length==1
        if (nums.length==1) {
            return nums[0];//its pair is missing obviously
        }
        //handle first number in nums
        if (nums[0]!=nums[1]) {
            return nums[0];
        }
        //handle last elemnt in nums
        if (nums[nums.length-1]!=nums[nums.length-2]) {
            return nums[nums.length-1];
        }
        //now we can deal with other elemnts , keeping in mind that bfr missing we have (evenIDx,oddIdx) format and after viveversa
        while (low<=high) {
            int mid=(low+high)/2;
            //missing elemnt always has elemnts not equal to it on its RHS & LHS
            if (nums[mid-1]!=nums[mid]&&nums[mid]!=nums[mid+1]) {
                return nums[mid];
            }
            //if midiDx is odd,then if we have same elemnt as on mid on left , it means we(curr pos of mid) r on (even,odd) side, so ans would lie on the other side obviosuly,so
            if ((mid%2!=0&&nums[mid]==nums[mid-1])||(mid%2==0&&nums[mid]==nums[mid+1])) {//we r on (even,odd) side,move to latter side
                low=mid+1;
            }else{//if we r on evn idx mid, then obviously its prev elemnt must be diff (but next elemnt must be same) ie we r on (odd,even) side and ans would lie on left side ie towards (even,odd) side,so
                high=mid-1;

            }
        }
        return -1;

    }

    /*
     * 
     * Since every element in the sorted array appears exactly twice except for the
     * single element, we know that if we take any element at an even index
     * (0-indexed), the next element should be the same. Similarly, if we take any
     * element at an odd index, the previous element should be the same. Therefore,
     * we can use binary search to compare the middle element with its adjacent
     * elements to determine which side of the array the single element is on.
     * 
     * If the middle element is at an even index, then the single element must be on
     * the right side of the array, since all the elements on the left side should
     * come in pairs. Similarly, if the middle element is at an odd index, then the
     * single element must be on the left side of the array.
     * 
     * We can continue this process by dividing the search range in half each time,
     * until we find the single element.
     * 
     * Another interesting observation you might have made is that this algorithm
     * will still work even if the array isn't fully sorted. As long as pairs are
     * always grouped together in the array (for example, [10, 10, 4, 4, 7, 11, 11,
     * 12, 12, 2, 2]), it doesn't matter what order they're in. Binary search worked
     * for this problem because we knew the subarray with the single number is
     * always odd-lengthed, not because the array was fully sorted numerically
     * 
     * NOTE - PLEASE READ APPROACH FIRST THEN SEE THE CODE. YOU WILL DEFINITELY
     * UNDERSTAND THE CODE LINE BY LINE AFTER SEEING THE APPROACH.
     * 
     * Approach for this Problem:
     * Initialize two pointers, left and right, to the first and last indices of the
     * input array, respectively.
     * While the left pointer is less than the right pointer:
     * a. Compute the index of the middle element by adding left and right and
     * dividing by 2.
     * b. If the index of the middle element is odd, subtract 1 to make it even.
     * c. Compare the middle element with its adjacent element on the right:
     * i. If the middle element is not equal to its right neighbor, the single
     * element must be on the left side of the array, so update the right pointer to
     * be the current middle index.
     * ii. Otherwise, the single element must be on the right side of the array, so
     * update the left pointer to be the middle index plus 2.
     * When the left and right pointers converge to a single element, return that
     * element.
     */

    public static int optimal2(int nums[]){
        int low=0,high=nums.length-1;
        while (low<=high) {
            int mid=(low+high)/2;
            //wkt every even idx (starting from 0) will have its pair at odd idx,except that missing elemnt, missing elemnt will always be present at odd idx and its next and prev elemnt will never be equal to it....one more obs is that all idx pairs bfr missing r in format (evenIdx,oddIdx) and vice versa after the oddIdx of missing Elemnt


            if (mid%2!=0) {//mid is oddd, then make it temporarily even , then if nums[mid] where mid is even now, is not missing elemnt, it should have same number on its right,so
                mid--;
            }//now check if now even midIdx has pair on right since every evenIdx has pair on its right
            if (nums[mid]!=nums[mid+1]) {
                //dont return the ans, since here only we could return only if mid was odd and we made it even by mid--, but we didnt do it:
                // ----if mid was already even, then ofc this if loop will not execute since for all nums[evenMidIdx]==nums[evenMidIdx+1]
                //------if mid was odd and we did mid--,now the missing number must lie in (even,odd) group,so
                high=mid;

            }else{
                low=mid+1;//coz we did mid--
            }
        }
        return nums[low];
     }
}
