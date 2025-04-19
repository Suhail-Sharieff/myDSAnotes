
package _3_binarySearch;

import java.util.Arrays;

/*
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
 

Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).
 

Constraints:

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */
public class _30_find_n_of_fair_pairs {
    public long countFairPairs(int[] nums, int lower, int upper) {
        //0 1 4 4 5 7
        //let x be number of pairs with sum less than lower, y be number of pairs with sum with sum<=higher, then number of pairs with sum which is >=lower and <=higher is (y-x)


        //https://www.youtube.com/watch?v=r3EnymXRC9A
        /*
         * We have an array called nums with n elements, along with two integers, lower and upper. Our task is to find out how many pairs of indices (i, j) exist in the array such that the sum of the elements at these indices, nums[i] + nums[j], falls between lower and upper. Plus, we need to make sure that i is less than j.

Given that the number of elements in the array can be as large as 10 
5
 , we need to think about an efficient solution—something that works in linear or log-linear time.

If you're feeling stuck, it might help to look at this similar problem before diving deeper.

Since we’re dealing with specific lower and upper bounds, it’s natural to think about using binary search. However, for binary search to be effective, we need to sort the array first. You might wonder if sorting will mess up our index requirements. The good news is that it won’t! Sorting the array allows us to find pairs easily because the order of addition doesn’t change the sum; that is, nums[i] + nums[j] is the same as nums[j] + nums[i].

So, our goal is to count unique pairs where i is not equal to j while ensuring their sums fall within the specified range.
         */

    Arrays.sort(nums);

    long ans=0l;

    for(int i=0;i<nums.length;i++){
        long amount_need_to_achive_atleast_lower=lower-nums[i];
        long amount_need_to_achive_atleast_upper=upper-nums[i];

        //// find the first index >= loTarget in nums[i+1…]
        int x=first_idx_where_elemnt_greater_or_equal_to_x(i+1,nums,amount_need_to_achive_atleast_lower);//lower bound, a[i] ke ssath kya add karna hai taaki wo lower se bada ho ya eqaul ho jaye
        // find the first index > hiTarget in nums[i+1…]
        int y=first_idx_where_elemnt_greater_than_x(i+1,nums,amount_need_to_achive_atleast_upper);//upper bound, a[i] ke ssath kya add karna hai taaki wo upper se bada ya equal ho jaye

        ans+=(y-x);
    }


    return ans;


    }


    public int first_idx_where_elemnt_greater_or_equal_to_x(int from_idx,int nums[],long x){
        int low=from_idx,high=nums.length-1;
        int ans=nums.length;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]>=x){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    public int first_idx_where_elemnt_greater_than_x(int from_idx,int nums[],long x){
        int low=from_idx,high=nums.length-1;
        int ans=nums.length;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]>x){
                ans=mid;
                high=mid-1;//we need first, so move left
            }else{
                low=mid+1;
            }
        }
        return ans;
    }


}